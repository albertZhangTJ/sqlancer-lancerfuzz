//ALTERNATIVE_NODE
//MIN_DEPTH = <min_depth/>
    public String <name/>(int depth) throws Exception{
<ee/>
        String ans=null;
        if (<IS_VAR/>){
            if (<IS_STATIC/>){
                ans = this.get_static_variable(<VAR_ID/>);
            }
            else {
                ans = this.get_variable("<VAR_ID/>")
            }
        }
        else {
            ans="";
            <call_children/>
        }
        return ans;
    }
