/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 by Bart Kiers
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 *
 * Project      : sqlite-parser; an ANTLR4 grammar for SQLite
 *                https://github.com/bkiers/sqlite-parser
 * Developed by : Bart Kiers, bart@big-o.nl
 */
grammar SQLite;

parse
 : ( sql_stmt_list | error )* EOF
 ;

error
 : UNEXPECTED_CHAR
 ;

sql_stmt_list
 : sql_stmt ( ';'+ '\n' + sql_stmt )* ';'*
 ;

sql_stmt
 : ( K_EXPLAIN ( K_QUERY K_PLAN )? )? ( alter_table_stmt
                                      | analyze_stmt
                                      | attach_stmt
                                      | begin_stmt
                                      | commit_stmt
                                      | compound_select_stmt
                                      | create_index_stmt
                                      | create_table_stmt
                                      | create_trigger_stmt
                                      | create_view_stmt
                                      | create_virtual_table_stmt
                                      | delete_stmt
                                      | delete_stmt_limited
                                      | detach_stmt
                                      | drop_index_stmt
                                      | drop_table_stmt
                                      | drop_trigger_stmt
                                      | drop_view_stmt
                                      | factored_select_stmt
                                      | insert_stmt
                                      | pragma_stmt
                                      | reindex_stmt
                                      | release_stmt
                                      | rollback_stmt
                                      | savepoint_stmt
                                      | simple_select_stmt
                                      | select_stmt
                                      | update_stmt
                                      | update_stmt_limited
                                      | vacuum_stmt )
 ;

alter_table_stmt
 : K_ALTER K_TABLE ( database_name '.' )? table_name
   ( K_RENAME K_TO new_table_name
   | K_ADD K_COLUMN? column_def
   )
 ;

analyze_stmt
 : K_ANALYZE ( database_name | table_or_index_name | database_name '.' table_or_index_name )?
 ;

attach_stmt
 : K_ATTACH K_DATABASE? expr K_AS database_name
 ;

begin_stmt
 : K_BEGIN ( K_DEFERRED | K_IMMEDIATE | K_EXCLUSIVE )? ( K_TRANSACTION transaction_name? )?
 ;

commit_stmt
 : ( K_COMMIT | K_END ) ( K_TRANSACTION transaction_name? )?
 ;

compound_select_stmt
 : with_clause?
   select_core ( ( K_UNION K_ALL? | K_INTERSECT | K_EXCEPT ) select_core )+
   ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
   ( K_LIMIT expr ( ( K_OFFSET | ',' ) expr )? )?
 ;

create_index_stmt
 : K_CREATE K_UNIQUE? K_INDEX ( K_IF K_NOT K_EXISTS )?
   ( database_name '.' )? index_name K_ON table_name '(' indexed_column ( ',' indexed_column )* ')'
   ( K_WHERE expr )?
 ;

create_table_stmt
 : K_CREATE ( K_TEMP | K_TEMPORARY )? K_TABLE ( K_IF K_NOT K_EXISTS )?
   ( database_name '.' )? table_name
   ( '(' column_def ( ',' column_def )*? ( ',' table_constraint )* ')' ( K_WITHOUT IDENTIFIER )?
   | K_AS select_stmt 
   )
 ;

create_trigger_stmt
 : K_CREATE ( K_TEMP | K_TEMPORARY )? K_TRIGGER ( K_IF K_NOT K_EXISTS )?
   ( database_name '.' )? trigger_name ( K_BEFORE  | K_AFTER | K_INSTEAD K_OF )? 
   ( K_DELETE | K_INSERT | K_UPDATE ( K_OF column_name ( ',' column_name )* )? ) K_ON ( database_name '.' )? table_name
   ( K_FOR K_EACH K_ROW )? ( K_WHEN expr )?
   K_BEGIN ( ( update_stmt | insert_stmt | delete_stmt | select_stmt ) ';' )+ K_END
 ;

create_view_stmt
 : K_CREATE ( K_TEMP | K_TEMPORARY )? K_VIEW ( K_IF K_NOT K_EXISTS )?
   ( database_name '.' )? view_name K_AS select_stmt
 ;

create_virtual_table_stmt
 : K_CREATE K_VIRTUAL K_TABLE ( K_IF K_NOT K_EXISTS )?
   ( database_name '.' )? table_name
   K_USING module_name ( '(' module_argument ( ',' module_argument )* ')' )?
 ;

delete_stmt
 : with_clause? K_DELETE K_FROM qualified_table_name 
   ( K_WHERE expr )?
 ;

delete_stmt_limited
 : with_clause? K_DELETE K_FROM qualified_table_name 
   ( K_WHERE expr )?
   ( ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
     K_LIMIT expr ( ( K_OFFSET | ',' ) expr )?
   )?
 ;

detach_stmt
 : K_DETACH K_DATABASE? database_name
 ;

drop_index_stmt
 : K_DROP K_INDEX ( K_IF K_EXISTS )? ( database_name '.' )? index_name
 ;

drop_table_stmt
 : K_DROP K_TABLE ( K_IF K_EXISTS )? ( database_name '.' )? table_name
 ;

drop_trigger_stmt
 : K_DROP K_TRIGGER ( K_IF K_EXISTS )? ( database_name '.' )? trigger_name
 ;

drop_view_stmt
 : K_DROP K_VIEW ( K_IF K_EXISTS )? ( database_name '.' )? view_name
 ;

factored_select_stmt
 : with_clause?
   select_core ( compound_operator select_core )*
   ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
   ( K_LIMIT expr ( ( K_OFFSET | ',' ) expr )? )?
 ;

insert_stmt
 : with_clause? ( K_INSERT 
                | K_REPLACE
                | K_INSERT K_OR K_REPLACE
                | K_INSERT K_OR K_ROLLBACK
                | K_INSERT K_OR K_ABORT
                | K_INSERT K_OR K_FAIL
                | K_INSERT K_OR K_IGNORE ) K_INTO
   ( database_name '.' )? table_name ( '(' column_name ( ',' column_name )* ')' )?
   ( K_VALUES '(' expr ( ',' expr )* ')' ( ',' '(' expr ( ',' expr )* ')' )*
   | select_stmt
   | K_DEFAULT K_VALUES
   )
 ;

pragma_stmt
 : K_PRAGMA ( database_name '.' )? pragma_name ( '=' pragma_value
                                               | '(' pragma_value ')' )?
 ;

reindex_stmt
 : K_REINDEX ( collation_name
             | ( database_name '.' )? ( table_name | index_name )
             )?
 ;

release_stmt
 : K_RELEASE K_SAVEPOINT? savepoint_name
 ;

rollback_stmt
 : K_ROLLBACK ( K_TRANSACTION transaction_name? )? ( K_TO K_SAVEPOINT? savepoint_name )?
 ;

savepoint_stmt
 : K_SAVEPOINT savepoint_name
 ;

simple_select_stmt
 : with_clause?
   select_core ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
   ( K_LIMIT expr ( ( K_OFFSET | ',' ) expr )? )?
 ;

select_stmt
 : with_clause?
   select_or_values ( compound_operator select_or_values )*
   ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
   ( K_LIMIT expr ( ( K_OFFSET | ',' ) expr )? )?
 ;

select_or_values
 : K_SELECT ( K_DISTINCT | K_ALL )? result_column ( ',' result_column )*
   ( K_FROM ( table_or_subquery ( ',' table_or_subquery )* | join_clause ) )?
   ( K_WHERE expr )?
   ( K_GROUP K_BY expr ( ',' expr )* ( K_HAVING expr )? )?
 | K_VALUES '(' expr ( ',' expr )* ')' ( ',' '(' expr ( ',' expr )* ')' )*
 ;

update_stmt
 : with_clause? K_UPDATE ( K_OR K_ROLLBACK
                         | K_OR K_ABORT
                         | K_OR K_REPLACE
                         | K_OR K_FAIL
                         | K_OR K_IGNORE )? qualified_table_name
   K_SET column_name '=' expr ( ',' column_name '=' expr )* ( K_WHERE expr )?
 ;

update_stmt_limited
 : with_clause? K_UPDATE ( K_OR K_ROLLBACK
                         | K_OR K_ABORT
                         | K_OR K_REPLACE
                         | K_OR K_FAIL
                         | K_OR K_IGNORE )? qualified_table_name
   K_SET column_name '=' expr ( ',' column_name '=' expr )* ( K_WHERE expr )?
   ( ( K_ORDER K_BY ordering_term ( ',' ordering_term )* )?
     K_LIMIT expr ( ( K_OFFSET | ',' ) expr )? 
   )?
 ;

vacuum_stmt
 : K_VACUUM
 ;

column_def
 : column_name type_name? column_constraint*
 ;

type_name
 : name+? ( '(' signed_number ')'
         | '(' signed_number ',' signed_number ')' )?
 ;

column_constraint
 : ( K_CONSTRAINT name )?
   ( K_PRIMARY K_KEY ( K_ASC | K_DESC )? conflict_clause K_AUTOINCREMENT?
   | K_NOT? K_NULL conflict_clause
   | K_UNIQUE conflict_clause
   | K_CHECK '(' expr ')'
   | K_DEFAULT (signed_number | literal_value | '(' expr ')')
   | K_COLLATE collation_name
   | foreign_key_clause
   )
 ;

conflict_clause
 : ( K_ON K_CONFLICT ( K_ROLLBACK
                     | K_ABORT
                     | K_FAIL
                     | K_IGNORE
                     | K_REPLACE
                     )
   )?
 ;

/*
    SQLite understands the following binary operators, in order from highest to
    lowest precedence:

    ||
    *    /    %
    +    -
    <<   >>   &    |
    <    <=   >    >=
    =    ==   !=   <>   IS   IS NOT   IN   LIKE   GLOB   MATCH   REGEXP
    AND
    OR
*/
expr
 : literal_value
 | BIND_PARAMETER
 | ( ( database_name '.' )? table_name '.' )? column_name
 | unary_operator expr
 | expr '||' expr
 | expr ( '*' | '/' | '%' ) expr
 | expr ( '+' | '-' ) expr
 | expr ( '<<' | '>>' | '&' | '|' ) expr
 | expr ( '<' | '<=' | '>' | '>=' ) expr
 | expr ( '=' | '==' | '!=' | '<>' ) expr
 | expr K_NOT? K_IN ( '(' ( select_stmt
                          | expr ( ',' expr )*
                          )? 
                      ')'
                    | ( database_name '.' )? table_name )
 | expr K_AND expr
 | expr K_OR expr
 | function_name '(' ( K_DISTINCT? expr ( ',' expr )* | '*' )? ')'
 | '(' expr ')'
 | K_CAST '(' expr K_AS type_name ')'
 | expr K_COLLATE collation_name
 | expr K_NOT? ( K_LIKE | K_GLOB | K_REGEXP | K_MATCH ) expr ( K_ESCAPE expr )?
 | expr ( K_ISNULL | K_NOTNULL | K_NOT K_NULL )
 | expr K_IS K_NOT? expr
 | expr K_NOT? K_BETWEEN expr K_AND expr
 | ( ( K_NOT )? K_EXISTS )? '(' select_stmt ')'
 | K_CASE expr? ( K_WHEN expr K_THEN expr )+ ( K_ELSE expr )? K_END
 | raise_function
 ;

foreign_key_clause
 : K_REFERENCES foreign_table ( '(' column_name ( ',' column_name )* ')' )?
   ( ( K_ON ( K_DELETE | K_UPDATE ) ( K_SET K_NULL
                                    | K_SET K_DEFAULT
                                    | K_CASCADE
                                    | K_RESTRICT
                                    | K_NO K_ACTION )
     | K_MATCH name
     ) 
   )*
   ( K_NOT? K_DEFERRABLE ( K_INITIALLY K_DEFERRED | K_INITIALLY K_IMMEDIATE )? )?
 ;

raise_function
 : K_RAISE '(' ( K_IGNORE 
               | ( K_ROLLBACK | K_ABORT | K_FAIL ) ',' error_message )
           ')'
 ;

indexed_column
 : column_name ( K_COLLATE collation_name )? ( K_ASC | K_DESC )?
 ;

table_constraint
 : ( K_CONSTRAINT name )?
   ( ( K_PRIMARY K_KEY | K_UNIQUE ) '(' indexed_column ( ',' indexed_column )* ')' conflict_clause
   | K_CHECK '(' expr ')'
   | K_FOREIGN K_KEY '(' column_name ( ',' column_name )* ')' foreign_key_clause
   )
 ;

with_clause
 : K_WITH K_RECURSIVE? common_table_expression ( ',' common_table_expression )*
 ;

qualified_table_name
 : ( database_name '.' )? table_name ( K_INDEXED K_BY index_name
                                     | K_NOT K_INDEXED )?
 ;

ordering_term
 : expr ( K_COLLATE collation_name )? ( K_ASC | K_DESC )?
 ;

pragma_value
 : signed_number
 | name
 | STRING_LITERAL
 ;

common_table_expression
 : table_name ( '(' column_name ( ',' column_name )* ')' )? K_AS '(' select_stmt ')'
 ;

result_column
 : '*'
 | table_name '.' '*'
 | expr ( K_AS? column_alias )?
 ;

table_or_subquery
 : ( schema_name '.' )? table_name ( K_AS? table_alias )?
   ( K_INDEXED K_BY index_name
   | K_NOT K_INDEXED )?
 | ( schema_name '.' )? table_function_name '(' ( expr ( ',' expr )* )? ')' ( K_AS? table_alias )?
 | '(' ( table_or_subquery ( ',' table_or_subquery )*
       | join_clause )
   ')'
 | '(' select_stmt ')' ( K_AS? table_alias )?
 ;

join_clause
 : table_or_subquery ( join_operator table_or_subquery join_constraint )*
 ;

join_operator
 : ','
 | K_NATURAL? ( K_LEFT K_OUTER? | K_INNER | K_CROSS )? K_JOIN
 ;

join_constraint
 : ( K_ON expr
   | K_USING '(' column_name ( ',' column_name )* ')' )?
 ;

select_core
 : K_SELECT ( K_DISTINCT | K_ALL )? result_column ( ',' result_column )*
   ( K_FROM ( table_or_subquery ( ',' table_or_subquery )* | join_clause ) )?
   ( K_WHERE expr )?
   ( K_GROUP K_BY expr ( ',' expr )* ( K_HAVING expr )? )?
 | K_VALUES '(' expr ( ',' expr )* ')' ( ',' '(' expr ( ',' expr )* ')' )*
 ;

compound_operator
 : K_UNION
 | K_UNION K_ALL
 | K_INTERSECT
 | K_EXCEPT
 ;

signed_number
 : ( '+' | '-' )? NUMERIC_LITERAL
 ;

literal_value
 : NUMERIC_LITERAL
 | STRING_LITERAL
 | BLOB_LITERAL
 | K_NULL
 | K_CURRENT_TIME
 | K_CURRENT_DATE
 | K_CURRENT_TIMESTAMP
 ;

unary_operator
 : '-'
 | '+'
 | '~'
 | K_NOT
 ;

error_message
 : STRING_LITERAL
 ;

module_argument // TODO check what exactly is permitted here
 : expr
 | column_def
 ;

column_alias
 : IDENTIFIER
 | STRING_LITERAL
 ;

keyword
 : K_ABORT
 | K_ACTION
 | K_ADD
 | K_AFTER
 | K_ALL
 | K_ALTER
 | K_ANALYZE
 | K_AND
 | K_AS
 | K_ASC
 | K_ATTACH
 | K_AUTOINCREMENT
 | K_BEFORE
 | K_BEGIN
 | K_BETWEEN
 | K_BY
 | K_CASCADE
 | K_CASE
 | K_CAST
 | K_CHECK
 | K_COLLATE
 | K_COLUMN
 | K_COMMIT
 | K_CONFLICT
 | K_CONSTRAINT
 | K_CREATE
 | K_CROSS
 | K_CURRENT_DATE
 | K_CURRENT_TIME
 | K_CURRENT_TIMESTAMP
 | K_DATABASE
 | K_DEFAULT
 | K_DEFERRABLE
 | K_DEFERRED
 | K_DELETE
 | K_DESC
 | K_DETACH
 | K_DISTINCT
 | K_DROP
 | K_EACH
 | K_ELSE
 | K_END
 | K_ESCAPE
 | K_EXCEPT
 | K_EXCLUSIVE
 | K_EXISTS
 | K_EXPLAIN
 | K_FAIL
 | K_FOR
 | K_FOREIGN
 | K_FROM
 | K_FULL
 | K_GLOB
 | K_GROUP
 | K_HAVING
 | K_IF
 | K_IGNORE
 | K_IMMEDIATE
 | K_IN
 | K_INDEX
 | K_INDEXED
 | K_INITIALLY
 | K_INNER
 | K_INSERT
 | K_INSTEAD
 | K_INTERSECT
 | K_INTO
 | K_IS
 | K_ISNULL
 | K_JOIN
 | K_KEY
 | K_LEFT
 | K_LIKE
 | K_LIMIT
 | K_MATCH
 | K_NATURAL
 | K_NO
 | K_NOT
 | K_NOTNULL
 | K_NULL
 | K_OF
 | K_OFFSET
 | K_ON
 | K_OR
 | K_ORDER
 | K_OUTER
 | K_PLAN
 | K_PRAGMA
 | K_PRIMARY
 | K_QUERY
 | K_RAISE
 | K_RECURSIVE
 | K_REFERENCES
 | K_REGEXP
 | K_REINDEX
 | K_RELEASE
 | K_RENAME
 | K_REPLACE
 | K_RESTRICT
 | K_RIGHT
 | K_ROLLBACK
 | K_ROW
 | K_SAVEPOINT
 | K_SELECT
 | K_SET
 | K_TABLE
 | K_TEMP
 | K_TEMPORARY
 | K_THEN
 | K_TO
 | K_TRANSACTION
 | K_TRIGGER
 | K_UNION
 | K_UNIQUE
 | K_UPDATE
 | K_USING
 | K_VACUUM
 | K_VALUES
 | K_VIEW
 | K_VIRTUAL
 | K_WHEN
 | K_WHERE
 | K_WITH
 | K_WITHOUT
 ;

// TODO check all names below

name
 : N DIGIT
 ;

function_name
 : F DIGIT
 ;

database_name
 : D DIGIT
 ;

schema_name
 : S DIGIT
 ;

table_function_name
 : T F DIGIT
 ;

table_name 
 : T DIGIT
 ;

table_or_index_name 
 : T I DIGIT
 ;

new_table_name 
 : N T DIGIT
 ;

column_name 
 : C DIGIT
 ;

collation_name 
 : C O L L DIGIT
 ;

foreign_table 
 : F T DIGIT
 ;

index_name 
 : I DIGIT
 ;

trigger_name
 : T R I G DIGIT
 ;

view_name 
 : V DIGIT
 ;

module_name 
 : any_name
 ;

pragma_name 
 : any_name
 ;

savepoint_name 
 : any_name
 ;

table_alias
 : IDENTIFIER
 | STRING_LITERAL
 | '(' table_alias ')'
 ;

transaction_name
 : any_name
 ;

any_name
 : IDENTIFIER 
 | keyword
 | STRING_LITERAL
 | '(' any_name ')'
 ;

SCOL : ';';
DOT : '.';
OPEN_PAR : '(';
CLOSE_PAR : ')';
COMMA : ',';
ASSIGN : '=';
STAR : '*';
PLUS : '+';
MINUS : '-';
TILDE : '~';
PIPE2 : '||';
DIV : '/';
MOD : '%';
LT2 : '<<';
GT2 : '>>';
AMP : '&';
PIPE : '|';
LT : '<';
LT_EQ : '<=';
GT : '>';
GT_EQ : '>=';
EQ : '==';
NOT_EQ1 : '!=';
NOT_EQ2 : '<>';

// http://www.sqlite.org/lang_keywords.html
K_ABORT : SPACE A B O R T SPACE;
K_ACTION : SPACE A C T I O N SPACE;
K_ADD : SPACE A D D SPACE;
K_AFTER : SPACE A F T E R SPACE;
K_ALL : SPACE A L L SPACE;
K_ALTER : SPACE A L T E R SPACE;
K_ANALYZE : SPACE A N A L Y Z E SPACE;
K_AND : SPACE A N D SPACE;
K_AS : SPACE A S SPACE;
K_ASC : SPACE A S C SPACE;
K_ATTACH : SPACE A T T A C H SPACE;
K_AUTOINCREMENT : SPACE A U T O I N C R E M E N T SPACE;
K_BEFORE : SPACE B E F O R E SPACE;
K_BEGIN : SPACE B E G I N SPACE;
K_BETWEEN : SPACE B E T W E E N SPACE;
K_BY : SPACE B Y SPACE;
K_CASCADE : SPACE C A S C A D E SPACE;
K_CASE : SPACE C A S E SPACE;
K_CAST : SPACE C A S T SPACE;
K_CHECK : SPACE C H E C K SPACE;
K_COLLATE : SPACE C O L L A T E SPACE;
K_COLUMN : SPACE C O L U M N SPACE;
K_COMMIT : SPACE C O M M I T SPACE;
K_CONFLICT : SPACE C O N F L I C T SPACE;
K_CONSTRAINT : SPACE C O N S T R A I N T SPACE;
K_CREATE : SPACE C R E A T E SPACE;
K_CROSS : SPACE C R O S S SPACE;
K_CURRENT_DATE : SPACE C U R R E N T '_' D A T E SPACE;
K_CURRENT_TIME : SPACE C U R R E N T '_' T I M E SPACE;
K_CURRENT_TIMESTAMP : SPACE C U R R E N T '_' T I M E S T A M P SPACE;
K_DATABASE : SPACE D A T A B A S E SPACE;
K_DEFAULT : SPACE D E F A U L T SPACE;
K_DEFERRABLE : SPACE D E F E R R A B L E SPACE;
K_DEFERRED : SPACE D E F E R R E D SPACE;
K_DELETE : SPACE D E L E T E SPACE;
K_DESC : SPACE D E S C SPACE;
K_DETACH : SPACE D E T A C H SPACE;
K_DISTINCT : SPACE D I S T I N C T SPACE;
K_DROP : SPACE D R O P SPACE;
K_EACH : SPACE E A C H SPACE;
K_ELSE : SPACE E L S E SPACE;
K_END : SPACE E N D SPACE;
K_ESCAPE : SPACE E S C A P E SPACE;
K_EXCEPT : SPACE E X C E P T SPACE;
K_EXCLUSIVE : SPACE E X C L U S I V E SPACE;
K_EXISTS : SPACE E X I S T S SPACE;
K_EXPLAIN : SPACE E X P L A I N SPACE;
K_FAIL : SPACE F A I L SPACE;
K_FOR : SPACE F O R SPACE;
K_FOREIGN : SPACE F O R E I G N SPACE;
K_FROM : SPACE F R O M SPACE;
K_FULL : SPACE F U L L SPACE;
K_GLOB : SPACE G L O B SPACE;
K_GROUP : SPACE G R O U P SPACE;
K_HAVING : SPACE H A V I N G SPACE;
K_IF : SPACE I F SPACE;
K_IGNORE : SPACE I G N O R E SPACE;
K_IMMEDIATE : SPACE I M M E D I A T E SPACE;
K_IN : SPACE I N SPACE;
K_INDEX : SPACE I N D E X SPACE;
K_INDEXED : SPACE I N D E X E D SPACE;
K_INITIALLY : SPACE I N I T I A L L Y SPACE;
K_INNER : SPACE I N N E R SPACE;
K_INSERT : SPACE I N S E R T SPACE;
K_INSTEAD : SPACE I N S T E A D SPACE;
K_INTERSECT : SPACE I N T E R S E C T SPACE;
K_INTO : SPACE I N T O SPACE;
K_IS : SPACE I S SPACE;
K_ISNULL : SPACE I S N U L L SPACE;
K_JOIN : SPACE J O I N SPACE;
K_KEY : SPACE K E Y SPACE;
K_LEFT : SPACE L E F T SPACE;
K_LIKE : SPACE L I K E SPACE;
K_LIMIT : SPACE L I M I T SPACE;
K_MATCH : SPACE M A T C H SPACE;
K_NATURAL : SPACE N A T U R A L SPACE;
K_NO : SPACE N O SPACE;
K_NOT : SPACE N O T SPACE;
K_NOTNULL : SPACE N O T N U L L SPACE;
K_NULL : SPACE N U L L SPACE;
K_OF : SPACE O F SPACE;
K_OFFSET : SPACE O F F S E T SPACE;
K_ON : SPACE O N SPACE;
K_OR : SPACE O R SPACE;
K_ORDER : SPACE O R D E R SPACE;
K_OUTER : SPACE O U T E R SPACE;
K_PLAN : SPACE P L A N SPACE;
K_PRAGMA : SPACE P R A G M A SPACE;
K_PRIMARY : SPACE P R I M A R Y SPACE;
K_QUERY : SPACE Q U E R Y SPACE;
K_RAISE : SPACE R A I S E SPACE;
K_RECURSIVE : SPACE R E C U R S I V E SPACE;
K_REFERENCES : SPACE R E F E R E N C E S SPACE;
K_REGEXP : SPACE R E G E X P SPACE;
K_REINDEX : SPACE R E I N D E X SPACE;
K_RELEASE : SPACE R E L E A S E SPACE;
K_RENAME : SPACE R E N A M E SPACE;
K_REPLACE : SPACE R E P L A C E SPACE;
K_RESTRICT : SPACE R E S T R I C T SPACE;
K_RIGHT : SPACE R I G H T SPACE;
K_ROLLBACK : SPACE R O L L B A C K SPACE;
K_ROW : SPACE R O W SPACE;
K_SAVEPOINT : SPACE S A V E P O I N T SPACE;
K_SELECT : SPACE S E L E C T SPACE;
K_SET : SPACE S E T SPACE;
K_TABLE : SPACE T A B L E SPACE;
K_TEMP : SPACE T E M P SPACE;
K_TEMPORARY : SPACE T E M P O R A R Y SPACE;
K_THEN : SPACE T H E N SPACE;
K_TO : SPACE T O SPACE;
K_TRANSACTION : SPACE T R A N S A C T I O N SPACE;
K_TRIGGER : SPACE T R I G G E R SPACE;
K_UNION : SPACE U N I O N SPACE;
K_UNIQUE : SPACE U N I Q U E SPACE;
K_UPDATE : SPACE U P D A T E SPACE;
K_USING : SPACE U S I N G SPACE;
K_VACUUM : SPACE V A C U U M SPACE;
K_VALUES : SPACE V A L U E S SPACE;
K_VIEW : SPACE V I E W SPACE;
K_VIRTUAL : SPACE V I R T U A L SPACE;
K_WHEN : SPACE W H E N SPACE;
K_WHERE : SPACE W H E R E SPACE;
K_WITH : SPACE W I T H SPACE;
K_WITHOUT : SPACE W I T H O U T SPACE;

IDENTIFIER
 : '"' (~'"' | '""')* '"'
 | '`' (~'`' | '``')* '`'
 | '[' ~']'* ']'
 | [a-zA-Z_] [a-zA-Z_0-9]* // TODO check: needs more chars in set
 ;

NUMERIC_LITERAL
 : DIGIT+ ( '.' DIGIT* )? ( E [-+]? DIGIT+ )?
 | '.' DIGIT+ ( E [-+]? DIGIT+ )?
 ;

BIND_PARAMETER
 : '?' DIGIT*
 | [:@$] IDENTIFIER
 ;

STRING_LITERAL
 : '\'' ( ~'\'' | '\'\'' )* '\''
 ;

BLOB_LITERAL
 : X STRING_LITERAL
 ;



UNEXPECTED_CHAR
 : .
 ;

EOL
 : ';' '\n'
 ;

fragment DIGIT : [0-9];
fragment SPACE : [\u0020]
fragment A : [A];
fragment B : [B];
fragment C : [C];
fragment D : [D];
fragment E : [E];
fragment F : [F];
fragment G : [G];
fragment H : [H];
fragment I : [I];
fragment J : [J];
fragment K : [K];
fragment L : [L];
fragment M : [M];
fragment N : [N];
fragment O : [O];
fragment P : [P];
fragment Q : [Q];
fragment R : [R];
fragment S : [S];
fragment T : [T];
fragment U : [U];
fragment V : [V];
fragment W : [W];
fragment X : [X];
fragment Y : [Y];
fragment Z : [Z];
