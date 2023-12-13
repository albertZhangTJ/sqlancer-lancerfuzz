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

parser grammar MySqlParser;

createDatabase
    : CREATE (DATABASE | SCHEMA) ifNotExists? dbName SC
    ;

createTable
    : CREATE TEMPORARY? TABLE ifNotExists? tableName LB
    	columnName INT (',' columnName INT)* RB SC
    ;
    
insertStatement
    : INSERT (LOW_PRIORITY | DELAYED | HIGH_PRIORITY)? IGNORE? INTO? tableName  (
        ('(' columns = fullColumnNameList? ')')? insertStatementValue 
    ) SC
    ;

updateStatement
    : UPDATE priority = LOW_PRIORITY? IGNORE? tableName SET updatedElement (
        ',' updatedElement
    )* (WHERE expression)?
    ;
    
ifNotExists : IF NOT EXISTS;

AS : SPACE A S SPACE;
CREATE : SPACE C R E A T E SPACE;
DATABASE : SPACE D A T A B A S E SPACE;
DELAYED : SPACE D E L A Y E D SPACE;
EXISTS : SPACE E X I S T S SPACE;
FROM : SPACE F R O M SPACE;
HIGH_PRIORITY : SPACE H I G H US P R I O R I T Y SPACE;
IF : SPACE I F SPACE;
IGNORE : SPACE I G N O R E SPACE;
INSERT : SPACE I N S E R T SPACE;
INT : SPACE I N T SPACE;
INTO : SPACE I N T O SPACE;
LIKE : SPACE L I K E SPACE;
LOW_PRIORITY : SPACE L O W US P R I O R I T Y SPACE;
NOT : SPACE N O T SPACE;
ON : SPACE O N SPACE;
SCHEMA : SPACE S C H E M A SPACE;
SELECT : SPACE S E L E C T SPACE;
SET : SPACE S E T SPACE;
TABLE : SPACE T A B L E SPACE;
TEMPORARY : SPACE T E M P O R A R Y SPACE;
UPDATE : SPACE U P D A T E SPACE;
VALUES : SPACE V A L U E S SPACE;
VIEW : SPACE V I E W SPACE;
WHERE : SPACE W H E R E SPACE;


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


