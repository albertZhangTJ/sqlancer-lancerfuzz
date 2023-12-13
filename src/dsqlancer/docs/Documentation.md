# Documentation for DSQLancer

DSQLancer is a grammar-based fuzzing framework for SQLancer. 
It takes in a grammar file in ANTLR syntax, a configuration file and outputs a fuzzer than can be used by SQLancer to test DBMSs.

This project is still work in progress at relatively early stage.
Features described might still be un-implemented or buggy.  


## Quick Start

TODO



## Grammar File

DSQLancer takes in a grammar file in ANTLR syntax.
For more details about ANTLR, see [ANTLRv4 Documentation](https://github.com/antlr/antlr4/blob/master/doc/index.md).
For list of ANTLR grammar files for different SQL dialects, see [ANTLRv4 GitHub repo](https://github.com/antlr/grammars-v4/tree/master/sql).

In order to improve semantic validity of the generated test cases, DSQLancer needs more than mere plain grammar files.

The extra semantic information shall be provided in [ANTLR Action](https://github.com/antlr/antlr4/blob/master/doc/actions.md). Details described below.


### Schema References

In order to generate SQL statements with correct identifiers (e.g. column names), DSQLancer needs to know how to query the target DBMS for list of available identifiers, as well as other information such as relationship between identifiers.

Schema references shall be declared as parser rules, with a special list of parameters.
A rule declared for schema references must have the parameter `boolean is_schema` set to `true`. Parameter `String query` must be set to a SQL query that allows DSQLancer to query the target DBMS for list of available identifiers. A related parameter `String attribute_name` must also be provided for DSQLancer to get the correct column out of the `ResultSet` returned by the execution of the previous statement.

When the current schema reference belongs to some parent reference (e.g. a column belongs to a table), the name of the parent reference can be accessed using `$parent_name$` in the `query` parameter.

Below is an example of declaring table name references for SQLite.

```
table_name locals [boolean is_schema=true, 
    String query="
        SELECT name, type as category, sql
        FROM sqlite_master 
        UNION
        SELECT name, 'temp_table' as category, sql 
        FROM sqlite_temp_master
        WHERE type='table'
        UNION
        SELECT name, 'view' as category, sql
        FROM sqlite_temp_master
        WHERE type='view' GROUP BY name;",
    String attribute_name="name"] : K_STUB;
```

When such a schema reference rule is referred to in other rules, several parameters needs to be provided. `boolean is_new` must be specified to indicate whether the identifier shall be generated (e.g. table name for create table statement) or queried from target DBMS (e.g. table name for insert statement). Another two parameters `String sup` and `String sub` are used to specify the relationship between different identifiers within the same rule. `sup` is used to declare the current reference as parent of other references. `sub` is used to declare the current reference as child of other references. For all references to schema rules, these two parameters needs to be explicitly set to null when they are not used.

Do notice that multiple parent SQL identifiers can be mapped to a single grammar identifier (declared using `sub`). When this happens, the parent will be randomly evaluated to one of the mapped SQL identifiers at runtime.

Below is an example of referring to schema reference rules.

```
insert_stmt : with_clause?  
    ( K_INSERT { BRANCH_W(10); }
                | K_REPLACE
                | K_INSERT K_OR K_IGNORE ) 
    K_INTO
    table_name[boolean is_new=false, 
            String sup=null, 
            String sub="t"]
    ( '(' 
        column_name[boolean is_new=false, 
                    String sup="t", 
                    String sub=null] 
        ( ',' 
        column_name[boolean is_new=false, 
                    String sup="t", 
                    String sub=null] 
        )* 
    ')' )?
    ( K_VALUES '(' expr ( ',' expr )* ')' 
        ( ',' '(' expr ( ',' expr )* ')' )* 
        | K_DEFAULT K_VALUES 
    );
```

In this example `"t"` is to specify `table_name` as parent of `column_name`. 

### Branch Weights

Some parts of the AST might need to be generated more often than others. To specify this kind of weighted branching, the tester can use a reserved function in ANTLR Action `BRANCH_W(double)`. If a branch has no weight specified, the weight will be set to default value 1.

At runtime, the possibility of going down a branch is $$\frac{current\_ weight}{total\_ weight}$$. Therefore, the larger the weight is, the higher the possibility that the branch is walked.

Below is an example of specifying branch weights.

```
insert_stmt : with_clause?  
    ( K_INSERT { BRANCH_W(10); }
                | K_REPLACE { BRANCH_W(0.5); }
                | K_INSERT K_OR K_IGNORE ) 
    K_INTO
    table_name[boolean is_new=false, 
            String sup=null, 
            String sub="t"]
    ( '(' 
        column_name[boolean is_new=false, 
                    String sup="t", 
                    String sub=null] 
        ( ',' 
        column_name[boolean is_new=false, 
                    String sup="t", 
                    String sub=null] 
        )* 
    ')' )?
    ( K_VALUES '(' expr ( ',' expr )* ')' 
        ( ',' '(' expr ( ',' expr )* ')' )* 
        | K_DEFAULT K_VALUES 
    );
```

### Expected Errors

TODO


## Configuration File

TODO

### DBMS-Specific Options

TODO

### Testing Stages