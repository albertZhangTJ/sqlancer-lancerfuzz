//STAGE_CALL_RULE
            if (index==<INDEX/>){
                String stmt = <RULE_NAME/>(fuzzing_depth);
                System.out.println(stmt);
                this.test_case_buffer.add(stmt);
                con.createStatement().execute(stmt);
            }

