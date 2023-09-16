//SCHEMA_NODE
//MIN_DEPTH = 1
    private int <rule_name/>_count = 0;
    private String <rule_name/>(SQLConnection con, boolean is_new, <parent_name/>){
        if (is_new){
            <rule_name/>_count++;
            return "<rule_name/>"+<rule_name/>_count;
        }
        ResultSet rs = con.createStatement().executeQuery(<query/>);
        List<String> values = new ArrayList<>();
        while (rs.next()){
            values.add(rs.getString("<attribute_name/>"));
        }
        return Randomly.fromList(values);
    }


    