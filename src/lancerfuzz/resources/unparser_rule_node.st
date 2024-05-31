//UNPARSER_RULE_NODE
//MIN_DEPTH = <min_depth/>
    public String <name/>(int depth) throws Exception{
<ee/>
        boolean is_component = false;
        <set_component/>
        if (<dependent/>){
            this.restore_context();
        }
        else if (!is_component){
            this.push_context();
        }

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
                    throw new Exception();
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
        
        if (!is_component){
            this.pop_context();
        }
        return ans;
    }