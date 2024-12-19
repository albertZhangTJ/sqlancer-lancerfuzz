# SQL Generation Language
<img src="fig/seagull_logo.jpg" alt="logo" width="160"/>

Notice: the current version of this tool is still work-in-progress as of now.

SQL Generation Language (SGL) is a metasyntax language designed to effectively and efficiently specify SQL dialects for fuzzing purposes.
SGL has an EBNF-like design based on [ANTLR](www.antlr.org). Beyond typical context-free grammar features, SGL has a variable system for maintaining context for test case generation. SGL also features a predicate system on the grammar level to enforce semantic constraints in the target languages.

This repository contains a tool `Seagull` to compile SGL grammars into Java test case generators.

Seagull takes in a series of SGL grammar files and a single JSON configuration file as input and output a single Java class for the test case generator.

This tool is inspired by [Grammarinator](https://github.com/renatahodovan/grammarinator) and [StringTemplate](https://github.com/antlr/stringtemplate4/tree/master).

## Quick Start

Find the example grammar `.g4` files and configuration `.json` files under `grammars/`.
Modify them to fit your target DBMS.

Start fuzzing by executing `./scripts/run.sh <path to grammar> <path to config>`.

## Terms Used in this Documentation

- Grammar: the grammar of the language of the test cases, written in SGL
- Target language: the language of the test cases (SQL dialects)
- Compile: the process of generating executable test case generators from a grammar
- Run time: the time when a test case generator is executed
- Turnaround time: the time from the test case generator is called to a test case is produced
- Throughput: the amount of test case that can be generated in a given time limit (one minute unless otherwise specified)

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
Each rule has a left-hand side consisting of an identifier and optional modifiers (elaborated in the variable section), a separating `:`, a right-hand side (rhs) describing how the rule can be expanded, and an enclosing semicolon.
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
A variable can have any number of attributes, each can hold another variable, allowing for representation of more complicated structures.

### Predicate

### Weight

### Expansion Order

### Expected Error