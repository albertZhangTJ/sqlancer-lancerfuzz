//UNPARSER_CALL_CHILDREN
            if (cache.size()<=<index/> || cache.get(<index/>).indexOf(ERROR_FLAG)!=-1){
                int eeb_size = this.expected_error_buffer.size();
                String tmp = <call_child/>;
                if (cache.size()<=<index/>){
                    cache.add(tmp);
                }
                else {
                    cache.set(<index/>, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }