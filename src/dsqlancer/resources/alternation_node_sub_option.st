//ALTERNATION_NODE_SUB_OPTION
            if (r < <WEIGHT/>){
                if (depth-1 < <child_depth/>){
                    continue;
                }
                String temp = <call_child/>;
                if (temp!=null){
                    return temp;
                }
            }
            r = r - <WEIGHT/>;
