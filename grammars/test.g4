grammar MiniMySQL;

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
    (where_predicate)?
	( 
		( UNION | INTERSECT ) query_core[rep=_r(c.len)]
	)?
	;