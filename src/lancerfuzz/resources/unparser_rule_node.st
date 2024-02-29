//UNPARSER_RULE_NODE
//MIN_DEPTH = <min_depth/>
    public String <name/>(int depth) throws Exception{
<ee/>
        this.push_context();

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
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
                    this.log("ERROR: <name/> cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    System.exit(1);
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }