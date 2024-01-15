//ALTERNATIVE_NODE
//MIN_DEPTH = <min_depth/>
    public String <name/>(int depth) throws Exception{
<ee/>
        String ans=null;
        if (<IS_VAR/>){
            if (<IS_STATIC/>){
                ans = get_static_variable("<VAR_ID/>");
            }
            else if (<IS_MEMBER/>){
                ans = this.get_member_variable("<VAR_ID/>");
            }
            else {
                ans = this.get_variable("<VAR_ID/>");
            }
        }
        else {
            ans="";
            <call_children/>
        }
        return ans;
    }
