//UNPARSER_CALL_CHILDREN
            if (cache.size()<=<index/> || cache.get(<index/>).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = <call_child/>;
                if (cache.size()<=<index/>){
                    cache.add(tmp);
                }
                else {
                    cache.set(<index/>, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }