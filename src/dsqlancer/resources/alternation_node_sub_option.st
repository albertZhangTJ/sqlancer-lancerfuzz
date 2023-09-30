//ALTERNATION_NODE_SUB_OPTION
            if (r < <WEIGHT/>){
                if (depth-1 < <child_depth/>){
                    continue;
                }
                return <call_child/>;
            }
            r = r - <WEIGHT/>;
