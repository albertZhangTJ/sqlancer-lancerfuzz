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
    : {ERR("exist");} ALTER TABLE tableName[def=t]
    alterSpecification (',' alterSpecification)* SC
    ;

alterSpecification locals [is_component]
    : 
    ADD COLUMN? columnName[is_new] columnDefinition FIRST?    
    | ADD COLUMN? '(' columnName[is_new] columnDefinition (',' columnName[is_new] columnDefinition)* ')' 
    | DROP COLUMN? columnName[ref=t, uni=a] {ERR("can't delete all"); ERR("has a partitioning function dependency");}
    | DROP PRIMARY KEY {ERR("primary");}
    | RENAME ( TO | AS ) tableName[is_new]
    | RENAME COLUMN columnName[ref=t, uni=a] TO columnName[is_new] { ERR("has a partitioning function dependency and cannot be dropped or renamed");}
    ;

columnDefinition
    : ' FLOAT ' | ' INT ' | ' TEXT '
    ;

dropDatabase
    : DROP DATABASE ifExists ( {STATIC_VAR("db");} | dbName[is_new]) SC
    ;

dropSchema
    : DROP SCHEMA ifExists ( {STATIC_VAR("db");} | dbName[is_new]) SC
    ;


createDatabase
    : CREATE (DATABASE | SCHEMA) ifNotExists? ( {STATIC_VAR("db");} | dbName[is_new]) SC
    ;

useDatabase
    : USE ( {STATIC_VAR("db");} | dbName[is_new]) SC
    ;

createTable
    : CREATE {ERR("A BLOB field is not allowed in partition function"); ERR("is of a not allowed type for this type of partitioning");} (' ' {BRANCH_W(9);} | TEMPORARY {ERR("Cannot create temporary table with partitions");}) TABLE 
        ifNotExists? tableName[is_new, uni=b] 
        (LB
    	( { VAR("cn");} | columnName[is_new, uni=a]) columnDefinition (',' columnName[is_new, uni=a] columnDefinition { RP_LIMIT(1,6, true, 0.1); })* RB 
            (' ' {BRANCH_W(8);} |
                    ' ENGINE ' EQ (' MyISAM ' | ' InnoDB ' ) |
                    PARTITION BY (LINEAR)? {ERR("allowed type");}
                    ( 
                        'HASH(' ( { VAR("cn");} | columnName[is_new, uni=a]) ')' |
                        ' KEY ' ( 'ALGORITHM=' ('1'|'2'))? '(' ( { VAR("cn");} | columnName[is_new, uni=a]) ')'
                    )
            ) {BRANCH_W(9);}
            | LIKE tableName[uni=b]
        )  SC
    ;

createIndex
    : {ERR("used in key specification without a key length");} CREATE  (UNIQUE {ERR("Duplicate"); ERR("A UNIQUE INDEX must include all columns in the table's partitioning function");} | FULLTEXT {ERR("cannot be part of"); ERR("The used table type doesn't support FULLTEXT indexes");} | {ERR("A SPATIAL index may only contain a geometrical type column"); BRANCH_W(0.01); } SPATIAL)? INDEX indexName[is_new]
        ON tableName[def=t] '(' columnName[ref=t, uni=a] (', ' columnName[ref=t, uni=a] {RP_LIMIT(0, 3);})* ')'
        (
            ALGORITHM EQ (DEFAULT | INPLACE | COPY)
            | LOCK EQ (DEFAULT | NONE | SHARED | EXCLUSIVE)
        ) {ERR("is not supported");}
        SC
    ;

truncateTable : TRUNCATE TABLE tableName[def=t] SC ;
    
insertStatement
    : (REPLACE | INSERT ((LOW_PRIORITY | DELAYED | HIGH_PRIORITY))? IGNORE? ) INTO? {ERR("Duplicate");} tableName[def=t]  (
       '(' columnName[ref=t, def=c, uni=id1] ( ',' columnName[ref=t, def=c, uni=id1] { RP_LIMIT(0, 5); RP_ID("a"); })* ')' VALUES '(' expr[ref=c] (',' expr[ref=c] { RP_LIMIT(0, 5); RP_ID("a"); })* ')'
    ) SC
    ;

updateStatement
    : UPDATE {ERR("Duplicate");} LOW_PRIORITY? IGNORE? tableName[def=t] SET columnName[ref=t, def=c, uni=id1] '=' expr[ref=c] (
        ',' columnName[ref=t, def=c, uni=id1] '=' expr[ref=c]
            {RP_LIMIT(0, 5); }
    )* (WHERE (NOT)? columnName[ref=t, def=cc] '=' expr[ref=cc])? SC
    ;

expr locals [is_expr, query="SHOW COLUMNS FROM $parent0$ WHERE Field='$parent1$';", attr="Type"] : ( int_expr {TYPE("INT");} | text_expr {TYPE("TEXT");} | float_expr {TYPE("FLOAT");} | least | greatest | if_func);

selectStatement 
    : SELECT  columnName[ref=t, def=c, uni=id1] (
        ',' columnName[ref=t, def=c, uni=id1]
            {RP_LIMIT(0, 5); }
    )*  FROM tableName[def=t]
    ;

pre locals [is_dependent] : ('(' columnName[ref=t, def=cc, uni=id1] comparison expr[ref=cc] ')' {BRANCH_W(5);}
            | '(' columnName[ref=t, def=cc, uni=id1] comparison expr ')' {BRANCH_W(3);}
            | expr comparison expr
            | ifnull 
            | if_func)
        ;

comparison : ( LT | GT | EQ | LT EQ | GT EQ );

waitNowaitClause
    : WAIT float_val
    | NOWAIT
    ;

abs : ' ABS(' (float_expr | int_expr ) ')' ;
bit_count : ' BIT_COUNT(' int_expr ')';
coalesce : ' COALESCE(' expr ( ',' expr )* ')';
if_func : ' IF(' (expr comparison expr | ifnull) ', ' expr ', ' expr ') '; 
ifnull : ' IFNULL(' expr ', ' expr ') ';
greatest : ' GREATEST(' expr ( ', ' expr )+ ') ';
least : ' LEAST(' expr ( ', ' expr )+ ') ';
strcmp : ' STRCMP(' text_expr ', ' text_expr ') ';
substr : ' SUBSTR(' text_expr ', ' int_expr ', ' int_expr ') ';
substring : ' SUBSTRING(' text_expr ', ' int_expr ', ' int_expr ') ';
trim : ' TRIM(' text_expr') ';
lcase : ' LCASE(' text_expr ') ';
ucase : ' UCASE(' text_expr ') ';
space : ' SPACE(' int_expr ') ';
last_insert_id : ' LAST_INSERT_ID() ';

float_expr : ( float_val {BRANCH_W(2);} | abs  | NULL ) ;
float_val : int_val ('.' int_val )? ;
int_expr : ( (DS {RP_LIMIT(0,1,true, 0.9); } )? int_val {BRANCH_W(4);} | bit_count | strcmp | last_insert_id | NULL );
int_val :  (DIGIT {RP_LIMIT(1,5, false, 0.5); })+ ;
text_expr : ( text_val {BRANCH_W(7);} | substr | substring | lcase | ucase | space | trim | NULL );
text_val :  DQ ( (CH | DIGIT) {RP_LIMIT(1,100, false, 0.1); })+ DQ ;

dbName locals [is_schema, query="SHOW DATABASES;", attr="Database"] : STUB ;
tableName locals [is_schema, query="SHOW TABLES;", attr="Tables_in_$STATIC_VAR("db")$"] : STUB;
columnName locals [is_schema, query="SHOW COLUMNS FROM $parent0$;", attr="Field"] : STUB;
indexName locals [is_schema, query="SHOW INDEX FROM $parent0$", attr="Key_name"] : STUB;


    
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
fragment DQ : ["];

