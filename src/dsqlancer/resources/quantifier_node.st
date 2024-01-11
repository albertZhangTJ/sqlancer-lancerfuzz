//QUANTIFIER_NODE
//MIN_DEPTH = <MIN_DEPTH/>
    public String <NAME/>(int depth) throws Exception{
<ee/>
        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = <RPID/>;
        if (rpid==null){
                count = <use_uniform/> ? random(<QUAN_MIN/>, <QUAN_MAX/>) : exp_decay_random(<QUAN_MIN/>, <QUAN_MAX/>, <DECAY_RATE/>);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = <use_uniform/> ? random(<QUAN_MIN/>, <QUAN_MAX/>) : exp_decay_random(<QUAN_MIN/>, <QUAN_MAX/>, <DECAY_RATE/>);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
<CHILD_NODE_REF/>
                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= <QUAN_MIN/>){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
