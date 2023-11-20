//SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int <rule_name/>_count = 0;
    private String <rule_name/>(int depth, boolean is_new, String sup, String sub){
<ee/>
        String ans = "";
        if (is_new){
            <rule_name/>_count++;
            ans = "<rule_name/>"+<rule_name/>_count;
        }
        else {
            String parent_name = "";
            if (sup!=null){
                parent_name = this.identifier_cache.get(sup);
                if (parent_name==null){
                    return ERROR_FLAG;
                }
            }
            ResultSet rs = con.createStatement().executeQuery(<query/>);
            List<String> values = new ArrayList<>();
            while (rs.next()){
                values.add(rs.getString("<attribute_name/>"));
            }
            for (int i = 0; i < loop_threshold; i++){
                ans = random_from_list(values);
                if (this.used_identifier!=null && this.used_identifier.contains(ans)){
                    if (i==loop_threshold-1){
                        return ""; //gave up, usually because the number of repetitions is greater than the number available identifiers
                    }
                    continue;
                }
                else {
                    break;
                }
            }
            
        }
        if (sub!=null){
            this.identifier_cache.put(sub, ans);
        }
        return ans;
    }


    