//UNLEXER_RULE_NODE
//MIN_DEPTH = <min_depth/>
    public String <name/>(int depth) throws Exception{
        boolean is_component = false;
        <set_component/>
        if (<dependent/>){
            this.restore_context();
        }
        else if (!is_component) {
            this.push_context();
        }
<ee/>
        String ans="";
<call_children/>
        if (!is_component){
            this.pop_context();
        }
        return ans;
    }