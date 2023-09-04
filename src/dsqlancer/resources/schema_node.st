//SCHEMA_NODE
    private String <rule_name\>(SQLConnection con<parent_name\>){
        ResultSet rs = con.createStatement().executeQuery(<query\>);
        List<String> values = new ArrayList<>();
        while (rs.next()){
            values.add(rs.getString("<attribute_name\>"));
        }
        return Randomly.fromList(values);
    }


    