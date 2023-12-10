package sqlancer.any;

import java.sql.*;
import java.util.*;
// This is a prototype tester for DSQLancer generated fuzzers

import sqlancer.MainOptions;

public class ProtoEntry {
    public static void log_failed(String test_case, String error){
        //TODO
    }

    public static void log_case(String test_case){
        //TODO
    }

    //TODO
    public static void test(MainOptions options){
        //extract the needed info from MainOption class
        int depth_limit = options.getMaxExpressionDepth();
        boolean log_each_select = options.logEachSelect();
        String username = options.getUserName();
        String password = options.getPassword();
        String host = options.getHost()==null ? "localhost" : options.getHost();
        long seed = options.getRandomSeed();
        String db_prefix = options.getDatabasePrefix();
        int max_test_cases = options.getMaxGeneratedDatabases()<1 ? Integer.MAX_VALUE : options.getMaxGeneratedDatabases();

        //TODO: also allow searching from rendered fuzzer code for DBMS specific options

        String url = null; //TODO automatically generate url with correct format
        Connection con = null;
        try{
            con = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException e){
            System.out.println("Error when establishing connection to the DBMS");
            e.printStackTrace();
            System.exit(1);
        }

        //loop over test cases
        for (int i=0; i<max_test_cases; i++){
            Fuzzer fz = new Fuzzer(con, depth_limit, 1000);
            fz.generate();
            List<String> test_case = fz.get_test_case();
            List<String> eerrs = fz.get_expected_errors();
            String executed = "";
            try {
                for (String stmt : test_case){
                    executed = executed + stmt + "\n";
                    con.createStatement().execute(stmt);
                }
                if (log_each_select){
                    log_case(executed);
                }
            }
            catch (Exception e){
                boolean is_expected = false;
                String content = e.toString();
                for (String er : eerrs){
                    if (content.contains(er)){
                        is_expected = true;
                        break;
                    }
                }
                if (!is_expected){
                    log_failed(executed, content);
                }
            }
        }
    }
}
