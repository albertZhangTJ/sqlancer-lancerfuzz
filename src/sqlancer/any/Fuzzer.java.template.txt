package sqlancer.any;
//Template code to allow things compile when an actual fuzzer is not present
//should never be executed 

import java.util.*;
import sqlancer.SQLConnection;

public class Fuzzer {
    public Fuzzer(SQLConnection con, int fuzzing_depth, int loop_threshold){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
    }

    public static void init(){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
    }

    public String get_next_statement(){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
        return "";
    }

    public static String get_dbms_option(String key){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
        return "";
    }

    public void push_identifier_cache(){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
    }

    public void pop_identifier_cache(){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
    }

    public void push_used_identifier(){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
    }

    public void pop_used_identifier(){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
    }

    public List<String> get_test_case(){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
        return null;
    }

    public List<String> get_expected_errors(){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
        return null;
    }

    public static String get_static_variable(String key){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
        return null;
    }

    public static void set_static_variable(String key, String value){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
    }

    public void generate(){
        System.out.println("This is template code, to use grammar based fuzzer, generate fuzzer code with DSQLancer first");
        System.exit(1);
    }
}
