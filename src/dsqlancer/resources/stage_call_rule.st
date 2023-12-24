//STAGE_CALL_RULE
            if (index==<INDEX/>){
                for (int attempt=0; attempt<10; attempt++){
                    try {
                        String stmt = <RULE_NAME/>(fuzzing_depth);
                        System.out.println(stmt);
                        this.test_case_buffer.add(stmt);
                        con.createStatement().execute(stmt);
                        break;
                    }
                    catch (Exception e){
                        if (!e.toString().contains("IgnoreMe")){
                            throw e;
                        }
                    }
                }

            }

