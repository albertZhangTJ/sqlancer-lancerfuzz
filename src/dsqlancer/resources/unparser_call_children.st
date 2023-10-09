//UNPARSER_CALL_CHILDREN
            if (cache.size()<=<index/> || cache.get(<index/>).indexOf(ERROR_FLAG)!=-1){
                String tmp = <call_child/>;
                if (cache.size()<=<index/>){
                    cache.add(tmp);
                }
                else {
                    cache.set(<index/>, tmp);
                }
            }