//UNLEXER_RULE_NODE
//MIN_DEPTH = <min_depth/>
    public String <name/>(int depth) throws Exception{
        if (<dependent/>){
            this.restore_context();
        }
        else {
            this.push_context();
        }
<ee/>
        String ans="";
<call_children/>
        this.pop_context();
        return ans;
    }