grammar minimal;

createDatabase
    : CREATE DATABASE ( {STATIC_VAR("db");} | dbName[is_new]) SC
    ;

useDatabase
    : USE ( {STATIC_VAR("db");} | dbName[is_new]) SC
    ;

createTable
    : CREATE {ERR("A BLOB field is not allowed in partition function");} TABLE 
        tableName[is_new, uni=b] 
        (
            LB columnName[is_new, uni=a] (' INT ' | ' TEXT ') 
            (',' columnName[is_new, uni=a] (' INT ' | ' TEXT ') { RP_LIMIT(1,6, true, 0.1); })* RB {BRANCH_W(9);}
            | LIKE tableName[uni=b]
        ) SC
    ;
    
insertStatement
    : INSERT INTO? {ERR("Duplicate");} tableName[def=t]  (
       '(' columnName[ref=t, def=c, uni=id1] ( ',' columnName[ref=t, def=c, uni=id1] { RP_ID("a"); })* ')' 
       VALUES '(' expr[ref=c] (',' expr[ref=c] { RP_ID("a"); })* ')'
    ) SC
    ;

selectStatement 
    : SELECT  columnName[ref=t, def=c, uni=id1] 
      (',' columnName[ref=t, def=c, uni=id1])* FROM tableName[def=t]
    ;

comparison : ( LT | GT | EQ );

int_val :  (DIGIT )+ ;
text_val :  DQ ( (CH | DIGIT) )+ DQ ;

dbName locals [is_schema, query="SHOW DATABASES;", attr="Database"] : STUB ;
tableName locals [is_schema, query="SHOW TABLES;", attr="Tables_in_$STATIC_VAR("db")$"] : STUB;
columnName locals [is_schema, query="SHOW COLUMNS FROM $parent0$;", attr="Field"] : STUB;
expr locals [is_expr, query="SHOW COLUMNS FROM $parent0$ WHERE Field='$parent1$';", attr="Type"] : ( int_val {TYPE("INT");} | text_val {TYPE("TEXT");} );
pre locals [is_dependent] : '(' columnName[ref=t, def=cc, uni=id1] comparison expr ')' ;