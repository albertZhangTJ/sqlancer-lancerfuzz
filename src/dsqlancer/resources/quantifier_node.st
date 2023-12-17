//QUANTIFIER_NODE
//MIN_DEPTH = <MIN_DEPTH/>
    public String <NAME/>(int depth) throws Exception{
<ee/>
        String ans = "";
        int count = -1;
        String rpid = <RPID/>;
        if (rpid==null){
                count = exp_decay_random(<QUAN_MIN/>, <QUAN_MAX/>);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(<QUAN_MIN/>, <QUAN_MAX/>);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
<CHILD_NODE_REF/>
        }
        return ans;
    }
