grammar MiniMySQL;

alterTable
    : error['exist'] ALTER TABLE t = table.any
     ( alterSpecification )**random[1, 6, ','] SC
    ;

test 
    : c+=1+3>=5
    ;

createTable
    : CREATE error['A BLOB field is not allowed in partition function', 'is of a not allowed type for this type of partitioning'] 
      ( 90% ' '  | TEMPORARY error['Cannot create temporary table with partitions'] ) TABLE 
        ifNotExists? table.new
        (
            90% LB ( cn+=column.new columnDefinition)**random[1, 5, ',', 10] RB 
            ( 80% ' '  |
                    ' ENGINE ' EQ (' MyISAM ' | ' InnoDB ' ) |
                    PARTITION BY (LINEAR)? error['allowed type']
                    ( 
                        'HASH(' cn.any ')' |
                        ' KEY ' ( 'ALGORITHM=' ('1'|'2'))? '(' cn.any ')'
                    )
            )
            | LIKE table.any
        )  SC
    ;