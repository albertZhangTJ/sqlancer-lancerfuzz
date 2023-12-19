//UNPARSER_RULE_NODE
//MIN_DEPTH = <min_depth/>
    public String <name/>(int depth) throws Exception{
<ee/>
        this.push_rpid();
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
        this.pop_rpid();
        return ans;
    }