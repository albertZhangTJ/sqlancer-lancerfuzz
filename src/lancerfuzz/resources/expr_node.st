//EXPR_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private String <rule_name/>(int depth) throws Exception{
        return <rule_name/>(depth, null);
    }
    private String <rule_name/>(int depth, String refer) throws Exception{
<ee/>
        String ans = "";
        List<String> parent_name = null;
        String type = "";
        if (refer!=null){
            parent_name = this.get_next_parent_hierarchy(refer);
            if (parent_name==null || parent_name.size()==0){
                return ERROR_FLAG;
            }
            String query = "<query/>";
            //this.log("Executing query: "+query);
            ResultSet rs = this.query_dbms(query);
            List<String> values = new ArrayList<>();
            while (rs.next()){
                values.add(rs.getString("<attribute_name/>"));
                this.log("Type added: "+rs.getString("<attribute_name/>")+" for parent: "+parent_name.toString());
            }
            type = values.get(0);
        }
        // sub nodes of an expression are not expected to fail
<expr_call_children/>
        return ans;
    }


    