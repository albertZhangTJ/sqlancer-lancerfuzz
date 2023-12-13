//UNLEXER_RULE_NODE
//MIN_DEPTH = <min_depth/>
    public String <name/>(int depth){
        int prev_a = rep_a;
        int prev_b = rep_b;
        int prev_c = rep_c;
        rep_a = exp_decay_random(0, 1);
        rep_b = exp_decay_random(0, 8);
        rep_c = exp_decay_random(1, 8);
<ee/>
        String ans="";
<call_children/>
        rep_a = prev_a;
        rep_b = prev_b;
        rep_c = prev_c;
        return ans;
    }