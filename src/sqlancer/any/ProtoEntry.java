package sqlancer.any;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Writer;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.sql.*;
import java.util.*;
import java.nio.file.Paths;
import java.nio.file.Files;

import sqlancer.SQLConnection;
// This is a prototype tester for SGL generated fuzzers

import sqlancer.MainOptions;

@SuppressWarnings("unused")
public class ProtoEntry {
    private static final int ALLOWED_CONSECUTIVE_FAILS = 5;
    private static int failed_log_counter;
    private static void log_failed(String test_case, String error) throws Exception{
        // System.out.println(test_case);
        // System.out.println(error);
        String file_path = "log/database"+failed_log_counter+".log";
        File f = new File(file_path);
        f.createNewFile();
        Writer w = new PrintWriter(new FileOutputStream(f));
        w.write(error+"\n"+test_case);
        failed_log_counter++;
        w.close();
    }

    private static int case_log_counter;
    private static void log_case(String test_case) throws Exception{
        // System.out.println(test_case);
        String file_path = "log/database"+case_log_counter+"-passed.log";
        File f = new File(file_path);
        f.createNewFile();
        Writer w = new PrintWriter(new FileOutputStream(f));
        w.write(test_case);
        case_log_counter++;
        w.close();
    }

    private static void init_logger() throws Exception{
        failed_log_counter = 0;
        case_log_counter = 0;
        Files.createDirectory(Paths.get("./log"));
    }

    //TODO
    public static void test(MainOptions options) throws Exception{
        try{
            Fuzzer.init(null);
            while (true){
                String next = Fuzzer.fuzz_next_and_execute();
                if (next==null){
                    break;
                }
                System.out.println(next);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return;
        // //extract the needed info from MainOption class
        // int depth_limit = 50;
        // boolean log_each_select = options.logEachSelect();
        // String username = options.getUserName();
        // String password = options.getPassword();
        // String host = options.getHost()==null ? "localhost" : options.getHost();
        // int port = options.getPort();
        // long seed = options.getRandomSeed();
        // String db_prefix = options.getDatabasePrefix();
        // String conn_str = "jdbc:($db-prefix$)://($host$):($port$)";
        // int max_test_cases = options.getMaxGeneratedDatabases()<1 ? Integer.MAX_VALUE : options.getMaxGeneratedDatabases();

        // Fuzzer.init();
        // init_logger();

        // //TODO: also allow searching from rendered fuzzer code for DBMS specific options
        // // Priority: CLI parameter > DBMS options in renderred fuzzer > MainOptions default
        // // TODO: How to differentiate whether the value stored in MainOptions is a default or CLI parameter?
        // if (Fuzzer.get_dbms_option("username")!=null && username.equals("sqlancer")){
        //     username = Fuzzer.get_dbms_option("username");
        // }
        // if (Fuzzer.get_dbms_option("password")!=null && password.equals("sqlancer")){
        //     password = Fuzzer.get_dbms_option("password");
        // }
        // if (Fuzzer.get_dbms_option("host")!=null && host.equals("localhost")){
        //     host = Fuzzer.get_dbms_option("host");
        // }
        // if (Fuzzer.get_dbms_option("port")!=null && port == MainOptions.NO_SET_PORT){
        //     port = Integer.valueOf(Fuzzer.get_dbms_option("port"));
        // }
        // if (Fuzzer.get_dbms_option("db_prefix")!=null && db_prefix.equals("database")){
        //     db_prefix = Fuzzer.get_dbms_option("db_prefix");
        // }
        // if (Fuzzer.get_dbms_option("conn_str")!=null){
        //     conn_str = Fuzzer.get_dbms_option("conn_str");
        // }
        // if (Fuzzer.get_dbms_option("jdbc_class")!=null){
        //     Class.forName(Fuzzer.get_dbms_option("jdbc_class"));
        // }



        // String url = null; //TODO automatically generate url with correct format
        // conn_str = conn_str.replace("($db_prefix$)", db_prefix);
        // conn_str = conn_str.replace("($username$)", username);
        // conn_str = conn_str.replace("($password$)", password);
        // conn_str = conn_str.replace("($port$)", ""+port);
        // conn_str = conn_str.replace("($host$)", host);
        // url = conn_str;
        // SQLConnection con = null;

        // //loop over test cases
        // int succeeded_counter = 0;
        // int failed_counter = 0;
        // int q_counter = 0;
        // int bug_counter = 0;
        // int total_stmt = 0;
        // int failed_stmt = 0;
        // long start_time = System.currentTimeMillis();
        // long last_time = System.currentTimeMillis();
        // for (int i=0; i<1000; i++){
        //     try{
        //         con = new SQLConnection(DriverManager.getConnection(url, username, password)); //For MySQL impl
        //         //set up proper JDBC connection here if the DBMS does something very weird
        //         //con = new SQLConnection(DriverManager.getConnection(url+"db"+(i)+".db")); //For SQLite impl
        //         Fuzzer fz = new Fuzzer(con, depth_limit, 1000);
        //         System.out.println("====================================================");
        //         Fuzzer.set_static_variable("db", "dbName"+(i%100)); //For MySQL impl
        //         fz.generate();
        //         String test_case = "";
        //         boolean is_successful = true;
        //         int last_failed = 0;
        //         String stmt="";
        //         while (true){
        //             try {
        //                 stmt = fz.get_next_statement();
        //                 if (stmt.equals(Fuzzer.ERROR_FLAG)){
        //                     break;
        //                 }
        //                 total_stmt++;
        //                 test_case = test_case + stmt + "\n";
        //                 System.out.println(stmt);
        //                 con.createStatement().execute(stmt);
        //                 last_failed = 0;
        //             }
        //             catch (Exception e){
        //                 failed_stmt++;
        //                 boolean is_expected = false;
        //                 System.out.println("Got error");
        //                 for (String eerr : fz.get_expected_errors()){
        //                     //System.out.println("Checking if contains eerr: "+eerr);
        //                     if (e.toString().toLowerCase().contains(eerr.toLowerCase())){
        //                         is_expected = true;
        //                         break;
        //                     }
        //                 }
        //                 if ((!is_expected && !e.toString().contains("IgnoreMe")) || last_failed>=ALLOWED_CONSECUTIVE_FAILS){
        //                     System.out.println("===================================");
        //                     System.out.println(stmt);
        //                     System.out.println(fz.get_crash_log());
        //                     System.out.flush();
        //                     e.printStackTrace();
        //                     System.out.flush();
        //                     log_failed(test_case, e.toString());
        //                     failed_counter++;
        //                     is_successful = false;
        //                     System.out.println("Hard failed");
        //                     break;
        //                 }
        //                 else {
        //                     last_failed++;
        //                     continue;
        //                 }
        //             }
        //         }
        //         if (is_successful){
        //             log_case(test_case);
        //             succeeded_counter++;
        //             for (int k=0; k<10; k++){
        //                 try {
        //                     System.out.println("||||||||||||||||||||||||||||||||||||||||");
        //                     String base_query = fz.generate_rule("selectStatement");
        //                     System.out.println("Base query generated: "+base_query);
        //                     String base_predicate = fz.generate_rule("pre");
        //                     System.out.println("Base predicate generated: "+base_predicate);
                            
        //                     System.out.println("Base query: "+base_query+";");
        //                     ResultSet base_result = con.createStatement().executeQuery(base_query+";");

        //                     System.out.println("Positive query: "+base_query+" WHERE ("+ base_predicate + ");");
        //                     ResultSet true_result = con.createStatement().executeQuery(base_query+" WHERE ("+ base_predicate + ");");

        //                     System.out.println("Negative query: "+base_query+" WHERE NOT ("+ base_predicate + ");");
        //                     ResultSet false_result = con.createStatement().executeQuery(base_query+" WHERE NOT ("+ base_predicate + ");");

        //                     System.out.println("Null query: "+base_query+" WHERE ("+ base_predicate + ") IS NULL;");
        //                     ResultSet null_result = con.createStatement().executeQuery(base_query+" WHERE ("+ base_predicate + ") IS NULL;");
        //                     int base_counter = 0;
        //                     int tlp_counter = 0;
        //                     while (base_result.next()){
        //                         base_counter++;
        //                     }
        //                     while(true_result.next()){
        //                         tlp_counter++;
        //                     }
        //                     while(false_result.next()){
        //                         tlp_counter++;
        //                     }
        //                     while(null_result.next()){
        //                         tlp_counter++;
        //                     }
        //                     q_counter++;
        //                     System.out.println("Base counter: "+base_counter+"     TLP_counter: "+tlp_counter);
        //                     System.out.println("Executed "+q_counter+" comparisons, found "+bug_counter+" inconsistencies");
        //                     if (base_counter!=tlp_counter){
        //                         bug_counter++;
        //                         System.out.println("Potential bug found!");
        //                     }
        //                 }
        //                 catch (Exception e){
        //                     e.printStackTrace();
        //                 }
        //             }
        //         }
        //         con.close();
        //         System.out.println("Executed: "+(total_stmt)+" statements, hard failed "+failed_stmt+" statements, statement success rate: "+((total_stmt-failed_stmt)*100/total_stmt)+"%");
        //         System.out.println("Executed: "+(i+1)+" test cases, hard failed "+failed_counter+" test cases, test case success rate: "+(succeeded_counter*100/(i+1))+"%");
        //         long this_time = System.currentTimeMillis();
        //         System.out.println("Time elapsed since beginning: "+((this_time-start_time)/1000) + " seconds");
        //         System.out.println("Time elapsed since beginning of test case: "+((this_time-last_time)/1000) + " seconds");
        //         System.out.println("Average throughput: "+total_stmt/((this_time-start_time+0.001)/1000)+" statements/second");
        //         last_time = this_time;
                
        //     }
        //     catch (SQLException e){
        //         System.out.println("Error when establishing connection to the DBMS");
        //         e.printStackTrace();
        //         System.exit(1);
        //     }
    }
}
