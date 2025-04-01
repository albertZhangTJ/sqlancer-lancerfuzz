package sqlancer.any;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
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

    public static boolean createFile(String name, String content) {
        try {
            FileWriter writer = new FileWriter(name);
            writer.write(content);
            writer.close();
            System.out.println("Successfully wrote to the file: " + name);
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
            return false;
        }
    }
    public static String format_stat(long start_camp, long start_case, long total_stmt, int case_stmt, int case_count, int crash_count, long ee_total, int ee_case){
        String result = "\n-- ============================================\n";
        result = result + "-- Time since start of campaign: " + (System.currentTimeMillis() - start_camp)/1000.0 + "s\n";
        result = result + "-- Time since start of test case: " + (System.currentTimeMillis() - start_case)/1000.0 + "s\n";
        result = result + "-- Total number of statements in campaign: " + total_stmt + "\n";
        result = result + "-- Number of statements in test case: " + case_stmt + "\n";
        result = result + "-- Campaign average throughput: " + total_stmt/((System.currentTimeMillis() - start_camp)/1000.0) + " statements/s\n";
        result = result + "-- Test case average throughput: " + case_stmt/((System.currentTimeMillis() - start_case)/1000.0) + " statements/s\n";
        result = result + "-- Total number of expected errors in campaign: " + ee_total + "\n";
        result = result + "-- Number of expected errors in test case: " + ee_case + "\n";
        result = result + "-- Campaign success rate: " + ((total_stmt-ee_total)*1.0)/total_stmt*100 + "%\n";
        result = result + "-- Case success rate: " + ((case_stmt-ee_case)*1.0)/case_stmt*100 + "%\n";
        result = result + "-- Number of statements in test case: " + case_stmt + "\n";
        result = result + "-- Total number of test cases: " + case_count + "\n";
        result = result + "-- Total number of failed cases: " + crash_count + "\n";
        result = result + "-- ============================================\n";
        return result;
    }
    public static String short_stat(long start_camp, long total_stmt, long crash_count){
        String result = "\n-- ============================================\n";
        result = result + "-- total time: " + (System.currentTimeMillis() - start_camp)/1000.0 + "s   ";
        result = result + "total stmt: " + total_stmt + "   ";
        result = result + "crashes: " + crash_count + "\n";
        result = result + "-- ============================================\n";
        return result;
    }
    //TODO
    public static void test(MainOptions options) throws Exception{
        int crash_count = 0;
        long start_time_camp = System.currentTimeMillis();
        long stmt_count_total = 0;
        int case_count = 0;
        long expected_count = 0;
        while (true){
            case_count++;
            long start_time_case = System.currentTimeMillis();
            int stmt_count_case = 0;
            int expected_count_case = 0;
            String test = "";
            try{
                Fuzzer.init(null);
                while (true){
                    String next = Fuzzer.fuzz_next_and_execute();
                    if (next==null){
                        break;
                    }
                    if (!next.startsWith("-- [Unavailable Error]:")){
                        stmt_count_case++;
                        stmt_count_total++;
                        test = test + next + "\n";
                        if (next.startsWith("-- [Expected Error]:")){
                            expected_count++;
                            expected_count_case++;
                        }
                        
                    }
                    System.out.println(next);
                    if (stmt_count_case%200 == 0){
                        System.out.println(short_stat(start_time_camp, stmt_count_total, crash_count));
                    }
                }
            }
            catch (Exception e){
                crash_count++;
                test = test + "-- " + e.getMessage() + "\n";
                test = test + e.getStackTrace().toString() + "\n";
                test = test + format_stat(start_time_camp, start_time_case, stmt_count_total, stmt_count_case, case_count, crash_count, expected_count, expected_count_case);
                String name = "logs/crash"+crash_count+".log";
                if (!createFile(name, test)){
                    break;
                }
                e.printStackTrace();
            }
            System.out.println(format_stat(start_time_camp, start_time_case, stmt_count_total, stmt_count_case, case_count, crash_count, expected_count, expected_count_case));
        }
        return;
    }
}
