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
// This is a prototype tester for DSQLancer generated fuzzers

import sqlancer.MainOptions;

@SuppressWarnings("unused")
public class ProtoEntry {
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
        Class.forName("com.mysql.cj.jdbc.Driver");

        //extract the needed info from MainOption class
        int depth_limit = 50;
        boolean log_each_select = options.logEachSelect();
        String username = options.getUserName();
        String password = options.getPassword();
        String host = options.getHost()==null ? "localhost" : options.getHost();
        int port = options.getPort();
        long seed = options.getRandomSeed();
        String db_prefix = options.getDatabasePrefix();
        String conn_str = "jdbc:($db-prefix$)://($host$):($port$)";
        int max_test_cases = options.getMaxGeneratedDatabases()<1 ? Integer.MAX_VALUE : options.getMaxGeneratedDatabases();

        Fuzzer.init();
        init_logger();

        //TODO: also allow searching from rendered fuzzer code for DBMS specific options
        // Priority: CLI parameter > DBMS options in renderred fuzzer > MainOptions default
        // TODO: How to differentiate whether the value stored in MainOptions is a default or CLI parameter?
        if (Fuzzer.get_dbms_option("username")!=null && username.equals("sqlancer")){
            username = Fuzzer.get_dbms_option("username");
        }
        if (Fuzzer.get_dbms_option("password")!=null && password.equals("sqlancer")){
            password = Fuzzer.get_dbms_option("password");
        }
        if (Fuzzer.get_dbms_option("host")!=null && host.equals("localhost")){
            host = Fuzzer.get_dbms_option("host");
        }
        if (Fuzzer.get_dbms_option("port")!=null && port == MainOptions.NO_SET_PORT){
            port = Integer.valueOf(Fuzzer.get_dbms_option("port"));
        }
        if (Fuzzer.get_dbms_option("db_prefix")!=null && db_prefix.equals("database")){
            db_prefix = Fuzzer.get_dbms_option("db_prefix");
        }
        if (Fuzzer.get_dbms_option("conn_str")!=null){
            conn_str = Fuzzer.get_dbms_option("conn_str");
        }



        String url = null; //TODO automatically generate url with correct format
        conn_str = conn_str.replace("($db_prefix$)", db_prefix);
        conn_str = conn_str.replace("($username$)", username);
        conn_str = conn_str.replace("($password$)", password);
        conn_str = conn_str.replace("($port$)", ""+port);
        conn_str = conn_str.replace("($host$)", host);
        url = conn_str;
        SQLConnection con = null;
        try{
            con = new SQLConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306", "new_user", "password"));
        }
        catch (SQLException e){
            System.out.println("Error when establishing connection to the DBMS");
            e.printStackTrace();
            System.exit(1);
        }

        //loop over test cases
        for (int i=0; i<max_test_cases; i++){
            try{
                System.out.println("====================================================");
                Fuzzer fz = new Fuzzer(con, depth_limit, 1000);
                fz.generate();
            }
            //if an error is thrown, check if it is an expected error
            //if not, log as a failed case
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
