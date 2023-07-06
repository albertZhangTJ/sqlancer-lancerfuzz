 grammar test;
 

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

update_stmt
 : K_UPDATE ( K_OR K_ROLLBACK
                         | K_OR K_ABORT
                         | K_OR K_REPLACE
                         | K_OR K_FAIL
                         | K_OR K_IGNORE )? qualified_table_name
   K_SET column_name '=' expr ( ',' column_name '=' expr )* ( K_WHERE expr )?
 ;


 select_stmt
 : K_SELECT ( K_DISTINCT | K_ALL )? result_column ( ',' result_column )*
   ( K_FROM ( table_or_subquery ( ',' table_or_subquery )* | join_clause ) )?
   ( K_WHERE expr )?
   ( K_GROUP K_BY expr ( ',' expr )* ( K_HAVING expr )? )?
 | K_VALUES '(' expr ( ',' expr )* ')' ( ',' '(' expr ( ',' expr )* ')' )*
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

qualified_table_name : table_or_subquery ;
column_name : result_column ;
result_column : C DIGIT SPACE;
table_or_subquery : T DIGIT SPACE;
expr : T R U E SPACE;





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