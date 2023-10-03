//SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int <rule_name/>_count = 0;
    private String <rule_name/>(int depth, boolean is_new, String parent_type, String sup, String sub){
        String ans = "";
        if (is_new){
            <rule_name/>_count++;
            ans = "<rule_name/>"+<rule_name/>_count;
        }
        else {
            if (parent_type!=null && sup==null){
                System.out.println("ERROR : parent_type specified without specifying super identifier, check grammar file");
            }
            String parent_name = "";
            if (parent_type!=null){
                HashMap<String, String> parent_cache = this.identifier_cache.get(parent_type);
                if (parent_cache==null){
                    return ERROR_FLAG;
                }
                parent_name = parent_cache.get(sup);
                if (parent_name==null){
                    return ERROR_FLAG;
                }
            }
            ResultSet rs = con.createStatement().executeQuery(<query/>);
            List<String> values = new ArrayList<>();
            while (rs.next()){
                values.add(rs.getString("<attribute_name/>"));
            }
            ans = Randomly.fromList(values);
        }
        if (sub!=null){
            HashMap<String, String> my_cache = this.identifier_cache.get("<rule_name/>");
            if (my_cache==null){
                my_cache = new HashMap<String, String>();
                this.identifier_cache.put("<rule_name/>", my_cache);
            }
            my_cache.put(sub, ans);
        }
        return ans;
    }


    