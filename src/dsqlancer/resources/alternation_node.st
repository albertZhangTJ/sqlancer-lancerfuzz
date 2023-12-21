//ALTERNATION_NODE
//MIN_DEPTH = <MIN_DEPTH/>
    public String <name/>(int depth) throws Exception{
<ee/>
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
