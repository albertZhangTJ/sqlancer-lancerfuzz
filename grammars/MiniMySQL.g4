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
    : {E_ERR("exist");} ALTER TABLE tableName[boolean is_new=false, String sup=null, String sub="t", String iid=null]
    alterSpecification (',' alterSpecification)* SC
    ;

alterSpecification locals [boolean is_component=true]
    : 
    ADD COLUMN? columnName[boolean is_new=true, String sup=null, String sub=null, String iid=null] columnDefinition FIRST?    
    | ADD COLUMN? '(' columnName[boolean is_new=true, String sup=null, String sub=null, String iid=null] columnDefinition (',' columnName[boolean is_new=true, String sup=null, String sub=null, String iid=null] columnDefinition)* ')' 
    | DROP COLUMN? columnName[boolean is_new=false, String sup="t", String sub=null, String iid="a"] {E_ERR("can't delete all"); E_ERR("has a partitioning function dependency");}
    | DROP PRIMARY KEY {E_ERR("primary");}
    | RENAME ( TO | AS ) tableName[boolean is_new=true, String sup=null, String sub=null, String iid=null]
    | RENAME COLUMN columnName[boolean is_new=false, String sup="t", String sub=null, String iid="a"] TO columnName[boolean is_new=true, String sup=null, String sub=null, String iid=null]
    ;

columnDefinition
    : ' FLOAT ' | ' INT ' | ' TEXT '
    ;

dropDatabase
    : DROP DATABASE ifExists ( {STATIC_VAR("db");} | dbName[boolean is_new=true, String sup=null, String sub=null, String iid=null]) SC
    ;

dropSchema
    : DROP SCHEMA ifExists ( {STATIC_VAR("db");} | dbName[boolean is_new=true, String sup=null, String sub=null, String iid=null]) SC
    ;


createDatabase
    : CREATE (DATABASE | SCHEMA) ifNotExists? ( {STATIC_VAR("db");} | dbName[boolean is_new=true, String sup=null, String sub=null, String iid=null]) SC
    ;

useDatabase
    : USE ( {STATIC_VAR("db");} | dbName[boolean is_new=true, String sup=null, String sub=null, String iid=null]) SC
    ;

createTable
    : CREATE {E_ERR("A BLOB field is not allowed in partition function"); E_ERR("is of a not allowed type for this type of partitioning");} (' ' {BRANCH_W(9);} | TEMPORARY {E_ERR("Cannot create temporary table with partitions");}) TABLE ifNotExists? tableName[boolean is_new=true, 
            String sup=null, 
            String sub=null,
            String iid="b"] (LB
    	( { VAR("cn");} | columnName[boolean is_new=true, 
            String sup=null, 
            String sub=null,
            String iid="a"]) columnDefinition (',' columnName[boolean is_new=true, 
            String sup=null, 
            String sub=null,
            String iid="a"] columnDefinition { RP_LIMIT(1,6, true, 0.1); })* RB 
            (' ' {BRANCH_W(8);} |
                    PARTITION BY (LINEAR)? 
                    ( 
                        'HASH(' ( { VAR("cn");} | columnName[boolean is_new=true, String sup=null, String sub=null, String iid="a"]) ')' |
                        ' KEY ' ( 'ALGORITHM=' ('1'|'2'))? '(' ( { VAR("cn");} | columnName[boolean is_new=true, String sup=null, String sub=null, String iid="a"]) ')'
                    )
            ) {BRANCH_W(9);}
            | LIKE 
            tableName[boolean is_new=false, 
            String sup=null, 
            String sub=null,
            String iid="b"])  SC
    ;

truncateTable : TRUNCATE TABLE tableName[boolean is_new=false, String sup=null, String sub="t", String iid=null] SC ;
    
insertStatement
    : (REPLACE | INSERT ((LOW_PRIORITY | DELAYED | HIGH_PRIORITY))? IGNORE? ) INTO? tableName[boolean is_new=false, 
            String sup=null, 
            String sub="t",
            String iid=null]  (
       '(' columnName[boolean is_new=false, 
            String sup="t", 
            String sub="c",
            String iid="id1"] ( ',' columnName[boolean is_new=false, 
            String sup="t", 
            String sub="c",
            String iid="id1"] { RP_LIMIT(0, 5); RP_ID("a"); })* ')' VALUES '(' expr[String sup="c"] (',' expr[String sup="c"] { RP_LIMIT(0, 5); RP_ID("a"); })* ')'
    ) SC
    ;

updateStatement
    : UPDATE LOW_PRIORITY? IGNORE? tableName[boolean is_new=false, 
            String sup=null, 
            String sub="t",
            String iid=null] SET columnName[boolean is_new=false, 
            String sup="t", 
            String sub="c",
            String iid="id1"] '=' expr[String sup="c"] (
        ',' columnName[boolean is_new=false, 
            String sup="t", 
            String sub="c",
            String iid="id1"] '=' expr[String sup="c"]
            {RP_LIMIT(0, 5); }
    )* (WHERE (NOT)? columnName[boolean is_new=false, 
            String sup="t", 
            String sub="cc",
            String iid=null] '=' expr[String sup="cc"])? SC
    ;

expr locals [boolean is_expr=true, String query="SHOW COLUMNS FROM $parent_name0$ WHERE Field='$parent_name1$';", String attribute_name="Type"] : ( int_expr {E_TYPE("INT");} | text_val {E_TYPE("TEXT");} | int_expr {E_TYPE("FLOAT");} | least | greatest );

selectStatement 
    : SELECT  columnName[boolean is_new=false, 
            String sup="t", 
            String sub="c",
            String iid="id1"] (
        ',' columnName[boolean is_new=false, 
            String sup="t", 
            String sub="c",
            String iid="id1"]
            {RP_LIMIT(0, 5); }
    )*  FROM tableName[boolean is_new=false, 
            String sup=null, 
            String sub="t",
            String iid=null]
    ;

pre locals [boolean is_dependent=true] : ('(' columnName[boolean is_new=false, 
            String sup="t", 
            String sub="cc",
            String iid="id1"] comparison expr[String sup="cc"] ')' {BRANCH_W(5);}
            | '(' columnName[boolean is_new=false, 
            String sup="t", 
            String sub="cc",
            String iid="id1"] comparison expr ')' {BRANCH_W(3);}
            | expr comparison expr
            | ifnull )
        ;

comparison : ( LT | GT | EQ | LT EQ | GT EQ );

waitNowaitClause
    : WAIT float_val
    | NOWAIT
    ;

abs : ' ABS(' (float_expr | int_expr ) ')' ;
bit_count : ' BIT_COUNT(' int_expr ')';
coalesce : ' COALESCE(' expr ( ',' expr )* ')';
ifnull : ' IFNULL(' expr ', ' expr ') ';
greatest : ' GREATEST(' expr ( ', ' expr )+ ')';
least : ' LEAST(' expr ( ', ' expr )+ ')';

float_expr : ( float_val {BRANCH_W(2);
    System.exit(134);
    //aabbcc
    } | abs  | NULL ) ;
float_val : int_val ('.' int_val )? ;
int_expr : ( int_val {BRANCH_W(2);} | bit_count | NULL );
int_val :  (DIGIT {RP_LIMIT(1,5, false, 0.5); })+ ;
text_val : ( DQ ( (CH | DIGIT) {RP_LIMIT(1,100, false, 0.1); })+ DQ | NULL);

dbName locals [boolean is_schema=true, String query="SHOW DATABASES;", String attribute_name="Database"] : STUB ;
tableName locals [boolean is_schema=true, String query="SHOW TABLES;", String attribute_name="Tables_in_$STATIC_VAR("db")$"] : STUB;
columnName locals [boolean is_schema=true, String query="SHOW COLUMNS FROM $parent_name0$;", String attribute_name="Field"] : STUB;


    
ifNotExists : IF NOT EXISTS;
ifExists : IF EXISTS;

ADD : SPACE A D D SPACE;
ALTER : SPACE A L T E R SPACE;
AS : SPACE A S SPACE;
BY : SPACE B Y SPACE;
COLUMN : SPACE C O L U M N SPACE;
CREATE : SPACE C R E A T E SPACE;
DATABASE : SPACE D A T A B A S E SPACE;
DELAYED : SPACE D E L A Y E D SPACE;
DROP : SPACE D R O P SPACE;
EXISTS : SPACE E X I S T S SPACE;
FIRST : SPACE F I R S T SPACE;
FLOAT : SPACE F L O A T SPACE;
FROM : SPACE F R O M SPACE;
HASH : SPACE H A S H SPACE;
HIGH_PRIORITY : SPACE H I G H US P R I O R I T Y SPACE;
IF : SPACE I F SPACE;
IGNORE : SPACE I G N O R E SPACE;
INSERT : SPACE I N S E R T SPACE;
INT : SPACE I N T SPACE;
INTO : SPACE I N T O SPACE;
KEY : SPACE K E Y SPACE;
LIKE : SPACE L I K E SPACE;
LINEAR : SPACE L I N E A R SPACE;
LOW_PRIORITY : SPACE L O W US P R I O R I T Y SPACE;
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
TABLE : SPACE T A B L E SPACE;
TEMPORARY : SPACE T E M P O R A R Y SPACE;
TEXT : SPACE T E X T SPACE;
TO : SPACE T O SPACE;
TRUNCATE : SPACE T R U N C A T E SPACE;
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

