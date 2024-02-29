//EXPR_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private String <rule_name/>(int depth) throws Exception{
        return <rule_name/>(depth, null);
    }
    private String <rule_name/>(int depth, String sup) throws Exception{
<ee/>
        String ans = "";
        List<String> parent_name = null;
        String type = "";
        if (sup!=null){
            parent_name = this.get_next_parent_hierarchy(sup);
            if (parent_name==null || parent_name.size()==0){
                return ERROR_FLAG;
            }
            String query = "<query/>";
            ResultSet rs = this.query_dbms(query);
            List<String> values = new ArrayList<>();
            type = values.get(0);
        }
        // sub nodes of an expression are not expected to fail
<expr_call_children/>
        return ans;
    }


    