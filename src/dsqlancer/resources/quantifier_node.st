//QUANTIFIER_NODE
//MIN_DEPTH = <MIN_DEPTH/>
    public String <NAME/>(int depth){
        push_used_identifier();
<ee/>
        String ans = "";
        int count = -1;
        if (<QUAN_MIN/> == 0 && <QUAN_MAX/> == 1){
                count = rep_a;
        }
        if (<QUAN_MIN/> == 0 && <QUAN_MAX/> > 1){
                count = rep_b;
        }
        if (<QUAN_MIN/> > 0 && <QUAN_MAX/> > 1){
                count = rep_c;
        }
        for (int i=0; i<count; i++){
<CHILD_NODE_REF/>
        }
        pop_used_identifier();
        return ans;
    }
