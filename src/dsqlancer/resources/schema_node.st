//SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int <rule_name/>_count = 0;
    private String <rule_name/>(int depth, boolean is_new, String sup, String sub, String iid) throws Exception{
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
            ResultSet rs = con.createStatement().executeQuery("<query/>");
            List<String> values = new ArrayList<>();
            while (rs.next()){
                if (iid==null || this.is_identifier_available(iid, rs.getString("<attribute_name/>") )){
                    values.add(rs.getString("<attribute_name/>"));
                }
            }
            if (values.size()==0){
                throw new Exception("IgnoreMe: quantifier node multiplier cannot be satisfied");
            }
            ans = random_from_list(values);
            if (iid!=null){
                this.mark_identifier_used(iid, ans);
            }
        }
        if (sub!=null){
            this.identifier_cache.put(sub, ans);
        }
        return ans;
    }


    