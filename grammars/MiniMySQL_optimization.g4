/*
MySQL (Positive Technologies) grammar
The MIT License (MIT).
Copyright (c) 2015-2017, Ivan Kochurkin (kvanttt@gmail.com), Positive Technologies.
Copyright (c) 2017, Ivan Khudyashev (IHudyashov@ptsecurity.com)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/
grammar MiniMySQL;

alterTable
    : _e("exist") ALTER TABLE t = table.any
    ( alterSpecification )**_r(1, 5) SC
    ;

fragment alterSpecification
    : 
    ADD COLUMN? column.new columnDefinition FIRST?    
    | ADD COLUMN? '(' (column.new columnDefinition){delimiter=","} ')' 
    | DROP COLUMN? column[t].unique_any _e("delete all", "has a partitioning function dependency")
    | DROP PRIMARY KEY _e("primary")
    | RENAME ( TO | AS ) table.new
    | RENAME COLUMN column[t].unique_any TO column.unique_any _e("has a partitioning function dependency and cannot be dropped or renamed")
    ;

fragment columnDefinition
    : ' FLOAT ' | ' INT ' | ' TEXT '
    ;

dropDatabase
    : DROP DATABASE ifExists DB = db.new SC
    ;

dropSchema
    : DROP SCHEMA ifExists DB SC
    ;


createDatabase
    : CREATE (DATABASE | SCHEMA) ifNotExists? DB SC
    ;

useDatabase
    : USE DB SC
    ;

createTable
    : CREATE _e("A BLOB field is not allowed in partition function", "is of a not allowed type for this type of partitioning") 
      ( 90% ' '  | TEMPORARY _e("Cannot create temporary table with partitions") ) TABLE 
        ifNotExists? table.new
        (
            90% LB (cn+=column.new columnDefinition)**_r(1, 5, 10) RB 
            ( 80% ' '  |
                    ' ENGINE ' EQ (' MyISAM ' | ' InnoDB ' ) |
                    PARTITION BY (LINEAR)? _e("allowed type")
                    ( 
                        'HASH(' cn.any ')' |
                        ' KEY ' ( 'ALGORITHM=' ('1'|'2'))? '(' cn.any ')'
                    )
            )
            | LIKE table.any
        )  SC
    ;

createIndex
    : _e("used in key specification without a key length") CREATE  
    ((
       49% UNIQUE _e("Duplicate", "A UNIQUE INDEX must include all columns in the ") | 
        49% FULLTEXT _e("cannot be part of"," support FULLTEXT indexes") | 
        _e("A SPATIAL index may only contain a geometrical type column")  SPATIAL
    ) )**_r(0, 1, 90)
    INDEX index.new
    ON t=table.any c=$column[t] '(' ( c.unique_any )**_r(1, 4) ')'
    (
        ALGORITHM EQ (DEFAULT | INPLACE | COPY)
        | LOCK EQ (DEFAULT | NONE | SHARED | EXCLUSIVE)
    ) _e("is not supported")
    SC
    ;

truncateTable : TRUNCATE TABLE table.any SC ;
    
insertStatement
    : (REPLACE | INSERT ((LOW_PRIORITY | DELAYED | HIGH_PRIORITY))? IGNORE? ) INTO? _e("Duplicate") t=table.any
    '('  ( c+=column[t] )**_r(1, 6) ')' 
    VALUES '(' ( expression[c.next] )**_r(c.len) ')'
    SC
    ;

updateStatement
    : UPDATE _e("Duplicate") LOW_PRIORITY? IGNORE? t=tableName 
    SET (cc=columnName[t].any '=' expression[cc])**_r(1,6) (WHERE (NOT)? cc=columnName[t].any '=' expression[cc])? SC
    ;

expression [column_name] locals [type=column_name.type] 
    : int_expr <type=="INT">
    | text_expr <type=="TEXT"> 
    | float_expr <type=="FLOAT">
    | least 
    | greatest 
    | if_func;

query_core [rep=_r(1,5)] locals [is_statement] returns [c] :
    @2
	SELECT (
        90% (   (tt=t.any DOT | tt=$t.any) c+=tt.c.unique_any
            | column_expression
        )**rep
        | ASTERISK
    ) 
    @1
	FROM ( tt=table_name.any tt.c=$column_name[tt] t+=$tt | '(' cc=query_core ')' AS tt=table_name.new tt.c=$cc t+=$tt)
	//no separate needed since it is directly positioned after the FROM clause
    (
		JOIN ( tt=table_name tt.c=$column_name[tt] t+=$tt | '(' cc=query_core ')' AS tt=table_name.new tt.c=$cc t+=$tt)
	)?
    @3
    where_predicate?
	( 
		( UNION | INTERSECT ) query_core[rep=_r(c.len)]
	)?
	;
	
where_predicate:
	WHERE predicate
	| WHERE c IN '(' query_core[rep=_r(1)] ')'
	| WHERE NOT? EXISTS '(' query_core ')'
	;

predicate : ( 50%'(' pivot=$t.any.c.any ( expression[pivot] | (tt=t DOT | tt=$t )  cc=tt.c.filter[type==pivot.type].any ) 
                    comparison 
                    ( expression[cc] | (tt=t DOT | tt=$t )  cc=tt.c.filter[type=pivot.type].any ) ')' 
            | 30% ifnull 
            | if_func)
        ;

comparison : ( LT | GT | EQ | LT EQ | GT EQ );

waitNowaitClause
    : WAIT float_val
    | NOWAIT
    ;

abs : ' ABS(' (float_expr | int_expr ) ')' ;
bit_count : ' BIT_COUNT(' int_expr ')';
coalesce : ' COALESCE(' expression ( ',' expression )* ')';
if_func : ' IF(' (expression comparison expression | ifnull) ', ' expression ', ' expression ') '; 
ifnull : ' IFNULL(' expression ', ' expression ') ';
greatest : ' GREATEST(' expression ( ', ' expression )+ ') ';
least : ' LEAST(' expression ( ', ' expression )+ ') ';
strcmp : ' STRCMP(' text_expr ', ' text_expr ') ';
substr : ' SUBSTR(' text_expr ', ' int_expr ', ' int_expr ') ';
substring : ' SUBSTRING(' text_expr ', ' int_expr ', ' int_expr ') ';
trim : ' TRIM(' text_expr') ';
lcase : ' LCASE(' text_expr ') ';
ucase : ' UCASE(' text_expr ') ';
space : ' SPACE(' int_expr ') ';
last_insert_id : ' LAST_INSERT_ID() ';

float_expr : ( 50% float_val | abs  | NULL ) ;
float_val : int_val ('.' int_val )? ;
int_expr : ( 50% (DS)**_r(0, 1) int_val | bit_count | strcmp | last_insert_id | NULL );
int_val :  (DIGIT)**_r(1, 5, uniform=true) ;
text_expr : ( 70% text_val | substr | substring | lcase | ucase | space | trim | NULL );
text_val :  DQ ( (CH | DIGIT) )**_r(1, 100) DQ ;

db returns [d] : d=$query["SHOW DATABASES;", "Database"] ;
table returns [t] : t=$query["SHOW TABLES;", "Tables_in_"+DB];
column [table] returns [c] : c=$query["SHOW COLUMNS FROM "+table, "Field"];
index [table] returns [i] : i=$query["SHOW INDEX FROM "+table, "Key_name"];


    
ifNotExists : IF NOT EXISTS;
ifExists : IF EXISTS;

ADD : SPACE A D D SPACE;
ALGORITHM : SPACE A L G O R I T H M SPACE;
ALTER : SPACE A L T E R SPACE;
AS : SPACE A S SPACE;
BY : SPACE B Y SPACE;
COLUMN : SPACE C O L U M N SPACE;
COPY : SPACE C O P Y SPACE;
CREATE : SPACE C R E A T E SPACE;
DATABASE : SPACE D A T A B A S E SPACE;
DEFAULT : SPACE D E F A U L T SPACE;
DELAYED : SPACE D E L A Y E D SPACE;
DROP : SPACE D R O P SPACE;
EXCLUSIVE : SPACE E X C L U S I V E SPACE;
EXISTS : SPACE E X I S T S SPACE;
FIRST : SPACE F I R S T SPACE;
FLOAT : SPACE F L O A T SPACE;
FROM : SPACE F R O M SPACE;
FULLTEXT : SPACE F U L L T E X T SPACE;
HASH : SPACE H A S H SPACE;
HIGH_PRIORITY : SPACE H I G H US P R I O R I T Y SPACE;
IF : SPACE I F SPACE;
IGNORE : SPACE I G N O R E SPACE;
INDEX : SPACE I N D E X SPACE;
INPLACE : SPACE I N P L A C E SPACE;
INSERT : SPACE I N S E R T SPACE;
INT : SPACE I N T SPACE;
INTO : SPACE I N T O SPACE;
KEY : SPACE K E Y SPACE;
LIKE : SPACE L I K E SPACE;
LINEAR : SPACE L I N E A R SPACE;
LOCK : SPACE L O C K SPACE;
LOW_PRIORITY : SPACE L O W US P R I O R I T Y SPACE;
NONE : SPACE N O N E SPACE;
NOT : SPACE N O T SPACE;
NOWAIT : SPACE N O W A I T SPACE;
NULL : SPACE N U L L SPACE;
OFFLINE : SPACE O F F L I N E SPACE;
ON : SPACE O N SPACE;
ONLINE : SPACE O N L I N E SPACE;
PARTITION : SPACE P A R T I T I O N SPACE;
PRIMARY : SPACE P R I M A R Y SPACE;
RENAME : SPACE R E N A M E SPACE;
REPLACE : SPACE R E P L A C E SPACE;
SCHEMA : SPACE S C H E M A SPACE;
SELECT : SPACE S E L E C T SPACE;
SET : SPACE S E T SPACE;
SHARED : SPACE S H A R E D SPACE;
SPATIAL : SPACE S P A T I A L SPACE;
TABLE : SPACE T A B L E SPACE;
TEMPORARY : SPACE T E M P O R A R Y SPACE;
TEXT : SPACE T E X T SPACE;
TO : SPACE T O SPACE;
TRUNCATE : SPACE T R U N C A T E SPACE;
UNIQUE : SPACE U N I Q U E SPACE;
UPDATE : SPACE U P D A T E SPACE;
USE : SPACE U S E SPACE;
VALUES : SPACE V A L U E S SPACE;
VIEW : SPACE V I E W SPACE;
WAIT : SPACE W A I T SPACE;
WHERE : SPACE W H E R E SPACE;


STUB : SPACE S T U B SPACE;



LB : '(';
RB : ')';
LT : '<';
GT : '>';
EQ : '=';
SC : ';';
US : '_';
DS : '-';
ASTERISK : '*';
DQ : '\"';

fragment DIGIT : [0-9];
fragment SPACE : [\u0020];
fragment NL : [\n];
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
fragment CH : [A-Z];

