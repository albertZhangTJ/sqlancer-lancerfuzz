//QUANTIFIER_NODE
//MIN_DEPTH = <MIN_DEPTH/>
    public String <NAME/>(int depth){
        String ans = "";
        int count = random(<QUAN_MIN/>, <QUAN_MAX/>+1);
        for (int i=0; i<count; i++){
            ans = ans + <CHILD_NODE_REF/>;
        }
        return ans;
    }
