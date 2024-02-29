//SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int <rule_name/>_count = 0;
    private String <rule_name/>(int depth, String iid, boolean is_new, String sub, String sup) throws Exception{
<ee/>
        String ans = "";
        List<String> parent_name = null;
        if (is_new){
            <rule_name/>_count++;
            ans = "<rule_name/>"+<rule_name/>_count;
        }
        else {
            if (sup!=null){
                parent_name = this.get_parent_hierarchy(sup);
                if (parent_name==null || parent_name.size()==0){
                    return ERROR_FLAG;
                }
            }
            String query = "<query/>";
            ResultSet rs = this.query_dbms(query);
            List<String> values = new ArrayList<>();
            int cnt=0;
            while (rs.next()){
                cnt++;
                //this.log(rs.getString("<attribute_name/>"));
                if (iid==null || this.is_identifier_available(iid, rs.getString("<attribute_name/>") )){
                    //this.log("Added");
                    values.add(rs.getString("<attribute_name/>"));
                }
            }
            if (values.size()==0){
                this.log("Queried target DBMS with: "+query);
                this.log("ResultSet has length: "+cnt);
                throw new Exception("IgnoreMe: quantifier node multiplier cannot be satisfied");
            }
            ans = random_from_list(values);
            if (iid!=null){
                this.mark_identifier_used(iid, ans);
            }
        }
        if (sub!=null){
            if (parent_name==null){
                List<String> hierarchy = new ArrayList<>();
                hierarchy.add(ans);
                this.set_parent(sub, hierarchy);
            }
            else {
                List<String> hierarchy = copy_list(parent_name);
                hierarchy.add(ans);
                this.set_parent(sub, hierarchy);
            }
        }
        return ans;
    }


    