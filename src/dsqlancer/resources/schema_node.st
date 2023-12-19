//SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int <rule_name/>_count = 0;
    private String <rule_name/>(int depth, boolean is_list, boolean is_new, String rp_id, int rp_limit_h, int rp_limit_l, String sub, String sup) throws Exception{
<ee/>
        String ans = "";
        int count=1;
        if (is_list){
            if (rp_id==null){
                count = exp_decay_random(rp_limit_l, rp_limit_h);
            }
            else if (this.rpid_cache.get(rp_id)==null){
                count = exp_decay_random(rp_limit_l, rp_limit_h);
                this.rpid_cache.put(rp_id, Integer.valueOf(count));
            }
        }
        if (is_new){
            for (int i=0; i<count; i++){
                <rule_name/>_count++;
                ans = "<rule_name/>"+<rule_name/>_count+delimiter;
            }
            //remove the last delimiter at the end
            ans = ans.substring(0, ans.length()-delimiter.length())
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
                values.add(rs.getString("<attribute_name/>"));
            }
            for (int i=0; i<count; i++){
                if (values.size()==0){
                    throw new Exception("IgnoreMe: quantifier node multiplier cannot be satisfied");
                }
                String ide = random_from_list(values);
                values.remove(ide);
                ans = ans + ide + delimiter;
                if (sub!=null){
                    this.identifier_cache.put(sub, ide);
                }
            }
            ans = ans.substring(0, ans.length()-delimiter.length());
        }
        
        return ans;
    }


    