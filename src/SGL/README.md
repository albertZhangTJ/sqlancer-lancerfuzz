# SQL Generation Language
<img src="fig/seagull_logo.jpg" alt="logo" width="160"/>

Notice: the current version of this tool is still work-in-progress as of now.

SQL Generation Language (SGL, previously LancerFuzz) is a metasyntax language designed to effectively and efficiently specify SQL dialects for fuzzing purposes.
SGL has an EBNF-like design based on [ANTLR](www.antlr.org). Beyond typical context-free grammar features, SGL has a variable system for maintaining context for test case generation. SGL also features a predicate system on the grammar level to enforce semantic constraints in the target languages.

This repository contains a tool `Seagull` to compile SGL grammars into Java test case generators.

Seagull takes in a series of SGL grammar files and a single JSON configuration file as input and output a single Java class for the test case generator.

This tool is inspired by [Grammarinator](https://github.com/renatahodovan/grammarinator) and [StringTemplate](https://github.com/antlr/stringtemplate4/tree/master).

## Quick Start

Find the example grammar `.sgl` files and configuration `.json` files under `grammars/`.
Modify them to fit your target DBMS.

Start fuzzing by executing `./scripts/run.sh <path to grammar> <path to config>`.

## Terms Used in this Documentation

- Grammar: the grammar of the language of the test cases, written in SGL
- Target language: the language of the test cases (SQL dialects)
- Compile: the process of generating test case generators source code from a grammar file
- Run time: the time when a test case generator is executed
- Turnaround time: the time from the test case generator is called to a test case is produced, might also be dependent on the target DBMS when the `query` function is used inside the grammar
- Throughput: the amount of test case that can be generated in a given time limit (statements/minute unless otherwise specified)
- Output buffer: A tree-structured buffer containing the partially generated test case, which will be serialized to produce the test case

## SGL

SGL is a metasyntax designed based on ANTLR, which is commonly used for generating parsers.
SGL uses ANTLR language constructs to specify the syntactical structures of the target language.
For maintaining context, SGL employs a variable system inspired by common general-purpose programming languages.
SGL also supports both grammar-space and target language-space predicates to ensure semantic constraints that cannot be captured otherwise.
To support the declarative nature of query languages, SGL allows manual specification of expansion order.
To increase the likelihood of generating "interesting" test cases, SGL supports weighted random branching.

### Basic Syntax
The basic syntax of SGL is the same as that of ANTLR's.

A grammar in SGL consists of a sequence of rules.
Each rule has a left-hand side consisting of an identifier and optional modifiers (elaborated in the variable section),
a separating `:`,
a right-hand side (rhs) describing how the rule can be expanded, and an enclosing semicolon.
```
identifier : rhs ;
```

The rhs of a rule can consist of the following elements:

Terminal - A terminal string in the syntax tree
```
A : 'A' ;
```

Alternation - one of the specified branches will be expanded ("or")
```
id : A | B ; // A or B will be expanded
```

Repetition - the element will be expanded for a number of times
```
id : A? ; // A will be expanded 0 or 1 time
id : A* ; // A will be expanded 0 or more times
id : A+ ; // A will be expanded 1 or more times
id : A**var ; // A will be expanded var times, possibly delimited (elaborated in variable section)
```

### Variable
To pass context around different parts of the grammar, SGL supports a variable system.

A variable in SGL supports these primitive types: `int`, `string`, `bool`, and `list`.
Strings in SGL are single quoted, for example `'string content'`.
A list in SGL is type-agnostic, it can hold all types of other variables together.
A variable can have any number of attributes, each can hold another variable,
allowing for representation of more complicated data structures.

A variable can be written to using the following operators: `=`, `+=`.
Variables can be operated on using the following operators: `$+`, `$-`, `==`, `!=`, `>`, `<`, `>=`, `<=`.
The semantics of `$+` depends on the operands, and is generally similar to the Java `+` operator, as for the `$-` operator.
The semantics of the `+=` operator is list appending instead of increment.
Other than that, the operator semantics are similar to that in common programming languages.

A variable can have any numbers of user defined attributes, accessible using `.`.

The default semantics of accessing variable includes a side effect of printing the string representation of the variable to the output buffer.
This printing behavior can be suppressed through an operator `$`. 
```
// create v, assigning int 3 to v, printing "3" to output buffer
id : v = 3 ;

// if v is a list, append 3 to the list
// otherwise, create v as a list and append
// Note the semantic here is different than in other languages
id : v += 3 ; 

id : v = 3$+2 ; //assign 5 to v 

id : v = 3==2 ; // assign false to v

// assign int 3 to v, set the attribute "delimiter" to ,
id : v = 3   v.delimiter = ',' ;

// assign int 3 to v, WITHOUT printing anything to buffer
id : v = $3 ;
```

The variable ~~scoop~~ scope comes in 2 flavors: local and global.
Local variables are accessible from within the rule, while global variables are accessible anywhere within the grammar file.
Local variables have identifiers start with a lower case letter, global variables should have identifiers start with an upper case letter.

Rules can accept variables as arguments and return a variable, similar to functions in common programming languages.

There is a special modifier `fragment`, representing that the rule does not have its own namespace.
Instead, it should be expanded simply by text-substituting its id with its rhs.
```
id [arg1, arg2] returns [r] : r = arg1 + arg2 + arg3 ;
```
SGL also provides a few built-in rules (or better say "functions") to provide commonly used features.
Specifically, there is random number generator `random[]`, and a query function `query[]`.
By default, these functions do not have the side effect of printing to output buffer.
```
// A random number generator, typically used alongside the ** operator for repetition
// returns an int variable with an attribute "delimiter"
// random[fix_number, delimiter]
// random[min, max]
// random[min, delimiter, decay_spec]
// random[min, max, delimiter]
// random[min, max, delimiter, decay_spec]
// decay_spec should be an interger ranging 0 to 99, default 50
// 0 for uniform distribution, uniform distribution without a max is not allowed
// Otherwise, the probability of getting x is (decay_spec/100)*(1-decay_spec/100)^(x-1)
id : rep=random[1, 4, ',', 75] A**rep ; // could possibly be 3A,A,A (notice the rep value 3 is also part of the output)

// The query function is used for getting information from the SUT
// For example, getting the list of tables in a database
table returns [t] : t=$query['SHOW TABLES;', 'Tables_in_'+DB];
```
Below is an example of how these features play together to produce meaningful test cases in SQL.
```
insertStatement
    : ( REPLACE | INSERT ) INTO? t=table.any
    '('  ( c+=column[t] )**random[1, 6, ',', 3] ')' 
    VALUES '(' ( expression[c.next] )**random[c.len,','] ')'
    SC
    ;
```

### Predicate
To enable the enforcement of certain semantic constraints, SGL provides a predicate mechanism that is used alongside alternations.

A predicate can be evaluated either in the grammar namespace `<expression in SGL>` or in the target language (e.g. Java) namespace `{expression in Java}?`.

At expansion time, all branches of an alternation will be filtered based on its predicate.
Those whose predicate evaluates to true will match have a chance to be selected.
Branches with no associated predicate will always evaluate to true.

This feature can be used to enforce type correctness
```
expression [type]
    : int_expr <type=='INT'>
    | text_expr <type=='TEXT'> 
    | float_expr <type=='FLOAT'>
    ;
```
and more
```
createTable locals [temp=0]
    : CREATE 
    ( ' '  | TEMPORARY temp=$1 ) TABLE 
    new[tables]
    LB ( cn+=new[columns] columnDefinition)**random[1, 5, ','] RB 
    (
        ' '  |
        <temp==0> PARTITION BY 'HASH(' cn.any ')'
    ) SC
    ;
```

### Weight
Some elements in the syntax tree might be more "interesting" than others in the sense that they trigger more complicated behavior.
It is desirable to generate these elements more often to increase the fuzzing efficiency.

To this end, SGL allows for attaching weights to alternation branches.
In the following example, the first branch will be expanded $50\%$ of the times, while the rest of the branches split the rest of the weight evenly.
```
predicate 
    : 50% comparison
    | ifnull 
    | if_func
    ;
```

### Expansion Order
SQL is declarative in nature.
Generating test cases in a left to right order might lead to some use-before-define issue, especially with `SELECT` statement, where the list of columns comes before the `FROM` clause in position.

SGL mitigates this issue by allowing manual specification of expansion order.
For example, the following grammar snippet for generating a SQL query shall be expanded in the order of `FROM` clause -> `SELECT` clause -> `WHERE` clause.
```
selectStatement [rep=random[1,5]] returns [c] :
    @2
	SELECT 
    ( tt=t.any DOT c+=tt.c.unique_any )**rep
    @1
	FROM tt=table_name.any tt.c=$column_name[tt] t+=$tt
    @3
    where_predicate
	;
```
### Expected Error
Sometimes it might be reasonable to not capture certain semantic constraints in grammar files due to complexity (for example, foreign key constraint and unique constraint).
Instead, it is easier to let these errors happen and filter them out afterwards.

To this end, SGL provides the expected error mechanism `error[]`.
Expected errors are error messages that are likely caused by issue in the test cases themselves instead of the SUT.
The fuzzer will silently ignore error messages that are expected, without reporting them to the tester.
```
insertStatement
    : INSERT INTO error['Duplicate'] t=table.any
    '('  ( c+=column[t] )**random[1, 6, ',', 75] ')' 
    VALUES '(' ( expression[c.next] )**random[c.len,','] ')'
    SC
    ;
```

## Seagull
Seagull is a work-in-progress tool that compiles SGL specifications into test case generator source code.

Seagull has a set of pre-defined template code for each language constructs in SGL.
At compile time, SGL will populate these templates with information from the input specification to generate fully executable source code.

As of now, we only aim to generate test case generators in Java.
More target languages might be supported in the future.

### Configuration File
Aside from the grammar file(s), Seagull also need a configuration file to work.

One assumption we have in the design of SGL is that we can rely on the target DBMS for symbol tables.
(Side note: it's not exactly that we have to do this, in-memory symbol tables implemented using SGL global variables can be achieved and is actually faster considering the IO overhead.
However, doing that will make the grammar file much more complicated since you have to explicitly book-keep everything.
Tester's discretion.)
To this end, have to send each statement to the DBMS for execution right after it is generated so that the symbols are actually there when we later query for it.

This has two implications: test case generators generated by SGL needs a way to talk to the target DBMS, and we can't just have a single start symbol as in "textbook" context-free grammars. Both of these are addressed using this configuration file.

The configuration file can optionally contain a JDBC connection string for the generator to establish a connection to the target DBMS.
This can also be passed in as a parameter when instantiating the fuzzer instead.

The configuration file must contain a series of "stages" that describe the sequence of rules to be generated.
For example, a test case must begin with a `CREATE DATABASE` stage, followed by some `CREATE TABLE`s, etc.

An example of the configuration file can be found at TODO.
