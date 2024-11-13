grammar MiniMySQL;

test 
    : c+=1+3>=5
    ;

createTable
    : CREATE _e('A BLOB field is not allowed in partition function \"test )', 'is of a not allowed type for this type of partitioning') 
      ( 90% ' '  | TEMPORARY _e('Cannot create temporary table with partitions') ) TABLE 
        ifNotExists? table.new
        (
            90% LB (cn+=column.new columnDefinition)**_r(1, 5, 10) RB 
            ( 80% ' '  |
                    ' ENGINE ' EQ (' MyISAM ' | ' InnoDB ' ) |
                    PARTITION BY (LINEAR)? _e('allowed type')
                    ( 
                        'HASH(' cn.any ')' |
                        ' KEY ' ( 'ALGORITHM=' ('1'|'2'))? '(' cn.any ')'
                    )
            )
            | LIKE table.any
        )  SC
    ;