//SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int <rule_name/>_count = 0;
    private String <rule_name/>(int depth, String iid, boolean is_new, String sub, String sup) throws Exception{
<ee/>
        String ans = "";
        List<String> parent_hier = null;
        if (is_new){
            <rule_name/>_count++;
            ans = "<rule_name/>"+<rule_name/>_count;
        }
        else {
            String parent_name = "";
            if (sup!=null){
                parent_hier = this.get_parent_hierarchy(sup);
                if (parent_hier==null || parent_hier.size()==0){
                    return ERROR_FLAG;
                }
                parent_name = parent_hier.get(parent_hier.size()-1);
            }
            String query = "<query/>";
            ResultSet rs = this.query_dbms(query);
            List<String> values = new ArrayList<>();
            int cnt=0;
            while (rs.next()){
                cnt++;
                //System.out.println(rs.getString("<attribute_name/>"));
                if (iid==null || this.is_identifier_available(iid, rs.getString("<attribute_name/>") )){
                    //System.out.println("Added");
                    values.add(rs.getString("<attribute_name/>"));
                }
            }
            if (values.size()==0){
                System.out.println("Queried target DBMS with: "+query);
                System.out.println("ResultSet has length: "+cnt);
                throw new Exception("IgnoreMe: quantifier node multiplier cannot be satisfied");
            }
            ans = random_from_list(values);
            if (iid!=null){
                this.mark_identifier_used(iid, ans);
            }
        }
        if (sub!=null){
            if (parent_hier==null){
                List<String> hierarchy = new ArrayList<>();
                hierarchy.add(ans);
                this.set_parent(sub, hierarchy);
            }
            else {
                List<String> hierarchy = copy_list(parent_hier);
                hierarchy.add(ans);
                this.set_parent(sub, hierarchy);
            }
        }
        return ans;
    }


    