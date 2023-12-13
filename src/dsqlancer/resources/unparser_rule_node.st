//UNPARSER_RULE_NODE
//MIN_DEPTH = <min_depth/>
    public String <name/>(int depth){
        //save previous quantifier context
        int prev_a = rep_a;
        int prev_b = rep_b;
        int prev_c = rep_c;
        rep_a = exp_decay_random(0, 1);
        rep_b = exp_decay_random(0, 8);
        rep_c = exp_decay_random(1, 8);
<ee/>
        //save previous identifier context
        this.push_identifier_cache();
        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        while (!is_completed){
<unparser_call_children/>
            is_completed = true;
            for (String s: cache){
                if (s.indexOf(ERROR_FLAG)!=-1){
                    is_completed = false;
                    break;
                }
            }
            counter++;
            if (counter>this.loop_threshold){
                System.out.println("ERROR: <name/> cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                System.exit(1);
            }
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        //restore previous identifier context
        this.pop_identifier_cache();
        //restore quantifier context
        rep_a = prev_a;
        rep_b = prev_b;
        rep_c = prev_c;
        return ans;
    }