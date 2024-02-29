//EXPR_CORE
//MIN_DEPTH = <MIN_DEPTH/>
    public String <name/>(int depth, String type) throws Exception{
<ee/>
        boolean is_typeless = (type==null || type.equals(""));
        if (!is_typeless){
<expr_core_call_children/>
        }
        String ans = null;
        <call_var_ref/>
        if (ans!=null){
            return ans;
        }
        double total_weight = <TOTAL_WEIGHT/>;
        if (depth < <MIN_DEPTH/>){
            return <call_min_child/>;
        }
        while (true){
            double r = Math.random()*total_weight;
<sub_option/>
        }

    }