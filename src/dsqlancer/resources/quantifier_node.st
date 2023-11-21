//QUANTIFIER_NODE
//MIN_DEPTH = <MIN_DEPTH/>
    public String <NAME/>(int depth){
        push_used_identifier();
<ee/>
        String ans = "";
        int count = exp_decay_random(<QUAN_MIN/>, <QUAN_MAX/>+1);
        for (int i=0; i<count; i++){
<CHILD_NODE_REF/>
        }
        pop_used_identifier();
        return ans;
    }
