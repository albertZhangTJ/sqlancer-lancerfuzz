//ALTERNATION_NODE_SUB_OPTION
            if (r < <WEIGHT/>){
                if (depth-1 < <child_depth/>){
                    continue;
                }
                String temp = <call_child/>;
                if (temp!=null){
                    if (<HAS_VAR/>){
                        if (<IS_STATIC/>){
                            this.set_static_variable("<VAR_ID/>", temp);
                        }
                        else {
                            this.set_variable("<VAR_ID/>", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - <WEIGHT/>;
