//FUZZER
package sqlancer.any;
//SINCE THIS CODE IS AUTOMATICALLY GENERATED, IT IS HARD TO DO SOME RESETTING
//THUS EACH OBJECT OF THIS CLASS IS "ONE-TIME"
//IE EACH OBJECT CAN ONLY GENERATE ONE TEST CASE
//FOR GETTING MULTIPLE TEST CASES, NEW MULTIPLE INSTANCES OF THIS CLASS
import java.util.*;
import java.sql.*;
import sqlancer.SQLConnection;
@SuppressWarnings("unused")
public class Fuzzer {
    private SQLConnection con;
    private List<String> test_case_buffer;
    private int test_case_cursor;
    private List<String> expected_error_buffer;

    //depth limit, passed in by the tester at fuzzing time
    private int fuzzing_depth;
    private int loop_threshold;

    //Rule-wise identifier cache, must be resetted at the beginning of each rule call
    private List<HashMap<String, String>> identifier_cache_stack;
    private HashMap<String, String> identifier_cache;

    private List<HashMap<String, Integer>> rpid_stack;
    private HashMap<String, Integer> rpid_cache;

    private List<HashMap<String, List<String>>> used_identifier_stack;
    private HashMap<String, List<String>> used_identifier; 
    private HashMap<String, List<String>> used_identifier_snapshot;

    //A flag that can be inserted into the output to indicate an error has occured
    //Usually due to an identifier has yet to be initialized
    //This pattern is extremely unlikely to appear in a normal output
    public static final String ERROR_FLAG = "PbEMuNhfsedBcVpUrBb2ZkWzpQkCkM5jTOCnUCTrYE9LgUpR2nZU45UgGHb3OS9NOrAUnWgrfvwUfGGmE0wdagXkOjv6psM4U9QnMWu75iFkgR5icHxm6xHtlpvQTy1OscMVNISBbaFLsNmYqzcHlpovwFtLJOlx0t7paPhOyTXQWuFjoGBgiQhWENvt116uaXQK9P0YO8ZkIVYOUcb1GzDh9G5Arns5guX8cqdTbDJdzWfvCalMevPYVXB7bAtwH8ZcyCNfabjt1ot8Wpj6xuCsn6u9WFuYao9enmV2tevt2DCqGlKfzA4ZZGuFev9M"; 

    private static HashMap<String, String> dbms_options = new HashMap<>();

    public Fuzzer(SQLConnection con, int fuzzing_depth, int loop_threshold){
        this.con = con;
        this.test_case_buffer = new ArrayList<>();
        this.test_case_cursor = 0;
        this.expected_error_buffer = new ArrayList<>();
        this.fuzzing_depth = fuzzing_depth;
        this.loop_threshold = loop_threshold;
        this.identifier_cache_stack = new ArrayList<>();
        this.identifier_cache = new HashMap<>();
        this.used_identifier_stack = new ArrayList<>();
        this.used_identifier = new HashMap<>();
        this.rpid_stack = new ArrayList<>();
        this.rpid_cache = new HashMap<>();
    }

    public static void init(){
        dbms_options.put("host","localhost");
        dbms_options.put("port","3306");
        dbms_options.put("username","Username");
        dbms_options.put("password","Password");
        dbms_options.put("db_prefix","mysql");
        dbms_options.put("conn_str","jdbc:($db_prefix$)://($host$):($port$)");

    }

    public String get_next_statement(){
        if (this.test_case_buffer.size()>this.test_case_cursor){
            String res = this.test_case_buffer.get(this.test_case_cursor);
            this.test_case_cursor++;
            return res;
        }
        return "";
    }

    public static String get_dbms_option(String key){
        return dbms_options.get(key);
    }

    private void push_identifier_cache(){
        this.identifier_cache_stack.add(this.identifier_cache);
        this.identifier_cache = new HashMap<>();
    }

    private void pop_identifier_cache(){
        if (this.identifier_cache_stack.size()==0){
            System.out.println("ERROR: identifier stack underflow");
            System.exit(1);
        }
        this.identifier_cache = this.identifier_cache_stack.get(this.identifier_cache_stack.size()-1);
        this.identifier_cache_stack.remove(this.identifier_cache_stack.size()-1);
    }

    private void push_rpid(){
        this.rpid_stack.add(this.rpid_cache);
        this.rpid_cache = new HashMap<>();
    }

    private void pop_rpid(){
        if (this.rpid_stack.size()==0){
            System.out.println("ERROR: rpid stack underflow");
            System.exit(1);
        }
        this.rpid_cache = this.rpid_stack.get(this.rpid_stack.size()-1);
        this.rpid_stack.remove(this.rpid_stack.size()-1);
    }

    private void push_used_identifier(){
        this.used_identifier_stack.add(this.used_identifier);
        this.used_identifier = new HashMap<>();
    }

    private void pop_used_identifier(){
        if (this.used_identifier_stack.size()==0){
            System.out.println("ERROR: used identifier stack underflow");
            System.exit(1);
        }
        this.used_identifier = this.used_identifier_stack.get(this.used_identifier_stack.size()-1);
        this.used_identifier_stack.remove(this.used_identifier_stack.size()-1);
    }

    private boolean is_identifier_available(String iid, String identifier){
        if (this.used_identifier.get(iid)==null || !this.used_identifier.get(iid).contains(identifier)){
            return true;
        }
        return false;
    }

    private void mark_identifier_used(String iid, String identifier){
        if (this.used_identifier.get(iid)==null){
            this.used_identifier.put(iid, new ArrayList<>());
        }
        this.used_identifier.get(iid).add(identifier);
    }

    private static <T> List<T> copy_list(List<T> ori){
        if (ori==null){
            return null;
        }
        List<T> res = new ArrayList<>();
        for (T item: ori){
            res.add(item);
        }
        return res;
    }

    private void snapshot_used_identifier(){
        this.used_identifier_snapshot = new HashMap<>();
        for (HashMap.Entry<String, List<String>> entry: this.used_identifier.entrySet()){
            this.used_identifier_snapshot.put(entry.getKey(), copy_list(entry.getValue()));
        }
    }

    private void rollback_used_identifier(){
        this.used_identifier = this.used_identifier_snapshot;
    }

    public List<String> get_test_case(){
        return this.test_case_buffer;
    }

    public List<String> get_expected_errors(){
        return this.expected_error_buffer;
    }

    private int random(int min, int max){
        return (int)(Math.random()*(max-min))+min;
    }

    private int exp_decay_random(int min, int max){
        if (max-min==2){
            return Math.random() > 0.5 ? min : min+1;
        }
        int i=min;
        while (i<max){
            if (Math.random()<0.9){
                return i;
            }
            i = i + 1;
        }
        return max-1;
    }

    private <T> T random_from_list(List<T> l){
        return l.get(random(0, l.size()));
    }

    public void generate() throws Exception{
        int count = -1;
//STAGE
//create
        count = random(1, 3+1);
        for (int i=0; i<count; i++){
            int index = random(0, 1);
//STAGE_CALL_RULE
            if (index==0){
                this.test_case_buffer.add(createTable(fuzzing_depth));
            }


        }
//STAGE
//insert
        count = random(5, 7+1);
        for (int i=0; i<count; i++){
            int index = random(0, 1);
//STAGE_CALL_RULE
            if (index==0){
                this.test_case_buffer.add(insertStatement(fuzzing_depth));
            }


        }
//STAGE
//update
        count = random(1, 5+1);
        for (int i=0; i<count; i++){
            int index = random(0, 1);
//STAGE_CALL_RULE
            if (index==0){
                this.test_case_buffer.add(updateStatement(fuzzing_depth));
            }


        }

    }

    /*
    public void reset(){
        this.test_case_buffer = new ArrayList<>();
        this.test_case_cursor = 0;
        this.expected_error_buffer = new ArrayList<>();
    }
    */

    public void reset_identifier_cache(){
        this.identifier_cache.clear();
    }

//This works as a flag to indicate where to insert the method for regular rule nodes
//The flag here will be removed during post-processing
//LAMBDA_NODE
//MIN_DEPTH = 1
    //lambda nodes are served as placeholders, no need to actually return anything
    public String Node0(int depth) throws Exception{

        return "";
    }
//UNLEXER_RULE_NODE
//MIN_DEPTH = 1
    public String EOF(int depth) throws Exception{
        this.push_rpid();

        String ans="";

        this.pop_rpid();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 3
    public String createDatabase(int depth) throws Exception{

        //save previous identifier context
        this.push_identifier_cache();
        this.push_rpid();
        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = CREATE(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node73(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node76(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = dbName(depth-1, null, true, null, null);
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=4 || cache.get(4).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SC(depth-1);
                if (cache.size()<=4){
                    cache.add(tmp);
                }
                else {
                    cache.set(4, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }
            is_completed = true;
            for (String s: cache){
                if (s.indexOf(ERROR_FLAG)!=-1){
                    is_completed = false;
                    break;
                }
            }
            counter++;
            if (counter>this.loop_threshold){
                System.out.println("ERROR: createDatabase cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                System.exit(1);
            }
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        //restore previous identifier context
        this.pop_identifier_cache();
        this.pop_rpid();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 3
    public String createTable(int depth) throws Exception{

        //save previous identifier context
        this.push_identifier_cache();
        this.push_rpid();
        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = CREATE(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node77(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = TABLE(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node78(depth-1);
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=4 || cache.get(4).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = tableName(depth-1, null, true, null, null);
                if (cache.size()<=4){
                    cache.add(tmp);
                }
                else {
                    cache.set(4, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=5 || cache.get(5).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = LB(depth-1);
                if (cache.size()<=5){
                    cache.add(tmp);
                }
                else {
                    cache.set(5, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=6 || cache.get(6).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = columnName(depth-1, "a", true, null, null);
                if (cache.size()<=6){
                    cache.add(tmp);
                }
                else {
                    cache.set(6, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=7 || cache.get(7).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = INT(depth-1);
                if (cache.size()<=7){
                    cache.add(tmp);
                }
                else {
                    cache.set(7, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=8 || cache.get(8).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node79(depth-1);
                if (cache.size()<=8){
                    cache.add(tmp);
                }
                else {
                    cache.set(8, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=9 || cache.get(9).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = RB(depth-1);
                if (cache.size()<=9){
                    cache.add(tmp);
                }
                else {
                    cache.set(9, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=10 || cache.get(10).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SC(depth-1);
                if (cache.size()<=10){
                    cache.add(tmp);
                }
                else {
                    cache.set(10, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }
            is_completed = true;
            for (String s: cache){
                if (s.indexOf(ERROR_FLAG)!=-1){
                    is_completed = false;
                    break;
                }
            }
            counter++;
            if (counter>this.loop_threshold){
                System.out.println("ERROR: createTable cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                System.exit(1);
            }
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        //restore previous identifier context
        this.pop_identifier_cache();
        this.pop_rpid();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 2
    public String insertStatement(int depth) throws Exception{

        //save previous identifier context
        this.push_identifier_cache();
        this.push_rpid();
        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = INSERT(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node82(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node87(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node88(depth-1);
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=4 || cache.get(4).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = tableName(depth-1, null, false, "t", null);
                if (cache.size()<=4){
                    cache.add(tmp);
                }
                else {
                    cache.set(4, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=5 || cache.get(5).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node89(depth-1);
                if (cache.size()<=5){
                    cache.add(tmp);
                }
                else {
                    cache.set(5, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=6 || cache.get(6).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = VALUES(depth-1);
                if (cache.size()<=6){
                    cache.add(tmp);
                }
                else {
                    cache.set(6, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=7 || cache.get(7).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node95(depth-1);
                if (cache.size()<=7){
                    cache.add(tmp);
                }
                else {
                    cache.set(7, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=8 || cache.get(8).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = INT_VAL(depth-1);
                if (cache.size()<=8){
                    cache.add(tmp);
                }
                else {
                    cache.set(8, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=9 || cache.get(9).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node96(depth-1);
                if (cache.size()<=9){
                    cache.add(tmp);
                }
                else {
                    cache.set(9, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=10 || cache.get(10).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SC(depth-1);
                if (cache.size()<=10){
                    cache.add(tmp);
                }
                else {
                    cache.set(10, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }
            is_completed = true;
            for (String s: cache){
                if (s.indexOf(ERROR_FLAG)!=-1){
                    is_completed = false;
                    break;
                }
            }
            counter++;
            if (counter>this.loop_threshold){
                System.out.println("ERROR: insertStatement cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                System.exit(1);
            }
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        //restore previous identifier context
        this.pop_identifier_cache();
        this.pop_rpid();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 2
    public String updateStatement(int depth) throws Exception{

        //save previous identifier context
        this.push_identifier_cache();
        this.push_rpid();
        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = UPDATE(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node99(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node100(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = tableName(depth-1, null, false, "t", null);
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=4 || cache.get(4).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SET(depth-1);
                if (cache.size()<=4){
                    cache.add(tmp);
                }
                else {
                    cache.set(4, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=5 || cache.get(5).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = columnName(depth-1, "id1", false, null, "t");
                if (cache.size()<=5){
                    cache.add(tmp);
                }
                else {
                    cache.set(5, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=6 || cache.get(6).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node101(depth-1);
                if (cache.size()<=6){
                    cache.add(tmp);
                }
                else {
                    cache.set(6, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=7 || cache.get(7).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = INT_VAL(depth-1);
                if (cache.size()<=7){
                    cache.add(tmp);
                }
                else {
                    cache.set(7, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=8 || cache.get(8).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node102(depth-1);
                if (cache.size()<=8){
                    cache.add(tmp);
                }
                else {
                    cache.set(8, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=9 || cache.get(9).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node105(depth-1);
                if (cache.size()<=9){
                    cache.add(tmp);
                }
                else {
                    cache.set(9, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }
            is_completed = true;
            for (String s: cache){
                if (s.indexOf(ERROR_FLAG)!=-1){
                    is_completed = false;
                    break;
                }
            }
            counter++;
            if (counter>this.loop_threshold){
                System.out.println("ERROR: updateStatement cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                System.exit(1);
            }
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        //restore previous identifier context
        this.pop_identifier_cache();
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String INT_VAL(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node107(depth-1);
        
        this.pop_rpid();
        return ans;
    }//SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int dbName_count = 0;
    private String dbName(int depth, String iid, boolean is_new, String sub, String sup) throws Exception{

        String ans = "";
        if (is_new){
            dbName_count++;
            ans = "dbName"+dbName_count;
        }
        else {
            String parent_name = "";
            if (sup!=null){
                parent_name = this.identifier_cache.get(sup);
                if (parent_name==null){
                    return ERROR_FLAG;
                }
            }
            ResultSet rs = con.createStatement().executeQuery("todo");
            List<String> values = new ArrayList<>();
            while (rs.next()){
                if (iid==null || this.is_identifier_available(iid, rs.getString("name") )){
                    values.add(rs.getString("name"));
                }
            }
            if (values.size()==0){
                throw new Exception("IgnoreMe: quantifier node multiplier cannot be satisfied");
            }
            ans = random_from_list(values);
            if (iid!=null){
                this.mark_identifier_used(iid, ans);
            }
        }
        if (sub!=null){
            this.identifier_cache.put(sub, ans);
        }
        return ans;
    }


    //SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int tableName_count = 0;
    private String tableName(int depth, String iid, boolean is_new, String sub, String sup) throws Exception{

        String ans = "";
        if (is_new){
            tableName_count++;
            ans = "tableName"+tableName_count;
        }
        else {
            String parent_name = "";
            if (sup!=null){
                parent_name = this.identifier_cache.get(sup);
                if (parent_name==null){
                    return ERROR_FLAG;
                }
            }
            ResultSet rs = con.createStatement().executeQuery("todo");
            List<String> values = new ArrayList<>();
            while (rs.next()){
                if (iid==null || this.is_identifier_available(iid, rs.getString("name") )){
                    values.add(rs.getString("name"));
                }
            }
            if (values.size()==0){
                throw new Exception("IgnoreMe: quantifier node multiplier cannot be satisfied");
            }
            ans = random_from_list(values);
            if (iid!=null){
                this.mark_identifier_used(iid, ans);
            }
        }
        if (sub!=null){
            this.identifier_cache.put(sub, ans);
        }
        return ans;
    }


    //SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int columnName_count = 0;
    private String columnName(int depth, String iid, boolean is_new, String sub, String sup) throws Exception{

        String ans = "";
        if (is_new){
            columnName_count++;
            ans = "columnName"+columnName_count;
        }
        else {
            String parent_name = "";
            if (sup!=null){
                parent_name = this.identifier_cache.get(sup);
                if (parent_name==null){
                    return ERROR_FLAG;
                }
            }
            ResultSet rs = con.createStatement().executeQuery("todo");
            List<String> values = new ArrayList<>();
            while (rs.next()){
                if (iid==null || this.is_identifier_available(iid, rs.getString("name") )){
                    values.add(rs.getString("name"));
                }
            }
            if (values.size()==0){
                throw new Exception("IgnoreMe: quantifier node multiplier cannot be satisfied");
            }
            ans = random_from_list(values);
            if (iid!=null){
                this.mark_identifier_used(iid, ans);
            }
        }
        if (sub!=null){
            this.identifier_cache.put(sub, ans);
        }
        return ans;
    }


    //UNPARSER_RULE_NODE
//MIN_DEPTH = 4
    public String ifNotExists(int depth) throws Exception{

        //save previous identifier context
        this.push_identifier_cache();
        this.push_rpid();
        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = IF(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = NOT(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_used_identifier();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = EXISTS(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of expected get_expected_errors
                // thus we remove the expected errors added in this branch
                // We will also revert the used identifier list to the state before this branch was called
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_used_identifier();
                    while (this.expected_error_buffer.size() > eeb_size){
                        this.expected_error_buffer.remove(this.expected_error_buffer.size()-1);
                    }
                }
            }
            is_completed = true;
            for (String s: cache){
                if (s.indexOf(ERROR_FLAG)!=-1){
                    is_completed = false;
                    break;
                }
            }
            counter++;
            if (counter>this.loop_threshold){
                System.out.println("ERROR: ifNotExists cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                System.exit(1);
            }
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        //restore previous identifier context
        this.pop_identifier_cache();
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String AS(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + A(depth-1);
                ans = ans + S(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String CREATE(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + C(depth-1);
                ans = ans + R(depth-1);
                ans = ans + E(depth-1);
                ans = ans + A(depth-1);
                ans = ans + T(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String DATABASE(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + D(depth-1);
                ans = ans + A(depth-1);
                ans = ans + T(depth-1);
                ans = ans + A(depth-1);
                ans = ans + B(depth-1);
                ans = ans + A(depth-1);
                ans = ans + S(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String DELAYED(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + D(depth-1);
                ans = ans + E(depth-1);
                ans = ans + L(depth-1);
                ans = ans + A(depth-1);
                ans = ans + Y(depth-1);
                ans = ans + E(depth-1);
                ans = ans + D(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String EXISTS(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + E(depth-1);
                ans = ans + X(depth-1);
                ans = ans + I(depth-1);
                ans = ans + S(depth-1);
                ans = ans + T(depth-1);
                ans = ans + S(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String FROM(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + F(depth-1);
                ans = ans + R(depth-1);
                ans = ans + O(depth-1);
                ans = ans + M(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String HIGH_PRIORITY(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + H(depth-1);
                ans = ans + I(depth-1);
                ans = ans + G(depth-1);
                ans = ans + H(depth-1);
                ans = ans + US(depth-1);
                ans = ans + P(depth-1);
                ans = ans + R(depth-1);
                ans = ans + I(depth-1);
                ans = ans + O(depth-1);
                ans = ans + R(depth-1);
                ans = ans + I(depth-1);
                ans = ans + T(depth-1);
                ans = ans + Y(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String IF(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + I(depth-1);
                ans = ans + F(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String IGNORE(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + I(depth-1);
                ans = ans + G(depth-1);
                ans = ans + N(depth-1);
                ans = ans + O(depth-1);
                ans = ans + R(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String INSERT(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + I(depth-1);
                ans = ans + N(depth-1);
                ans = ans + S(depth-1);
                ans = ans + E(depth-1);
                ans = ans + R(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String INT(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + I(depth-1);
                ans = ans + N(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String INTO(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + I(depth-1);
                ans = ans + N(depth-1);
                ans = ans + T(depth-1);
                ans = ans + O(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String LIKE(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + L(depth-1);
                ans = ans + I(depth-1);
                ans = ans + K(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String LOW_PRIORITY(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + L(depth-1);
                ans = ans + O(depth-1);
                ans = ans + W(depth-1);
                ans = ans + US(depth-1);
                ans = ans + P(depth-1);
                ans = ans + R(depth-1);
                ans = ans + I(depth-1);
                ans = ans + O(depth-1);
                ans = ans + R(depth-1);
                ans = ans + I(depth-1);
                ans = ans + T(depth-1);
                ans = ans + Y(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String NOT(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + N(depth-1);
                ans = ans + O(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String ON(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + O(depth-1);
                ans = ans + N(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String SCHEMA(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + S(depth-1);
                ans = ans + C(depth-1);
                ans = ans + H(depth-1);
                ans = ans + E(depth-1);
                ans = ans + M(depth-1);
                ans = ans + A(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String SELECT(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + S(depth-1);
                ans = ans + E(depth-1);
                ans = ans + L(depth-1);
                ans = ans + E(depth-1);
                ans = ans + C(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String SET(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + S(depth-1);
                ans = ans + E(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String TABLE(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + T(depth-1);
                ans = ans + A(depth-1);
                ans = ans + B(depth-1);
                ans = ans + L(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String TEMPORARY(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + T(depth-1);
                ans = ans + E(depth-1);
                ans = ans + M(depth-1);
                ans = ans + P(depth-1);
                ans = ans + O(depth-1);
                ans = ans + R(depth-1);
                ans = ans + A(depth-1);
                ans = ans + R(depth-1);
                ans = ans + Y(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String UPDATE(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + U(depth-1);
                ans = ans + P(depth-1);
                ans = ans + D(depth-1);
                ans = ans + A(depth-1);
                ans = ans + T(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String VALUES(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + V(depth-1);
                ans = ans + A(depth-1);
                ans = ans + L(depth-1);
                ans = ans + U(depth-1);
                ans = ans + E(depth-1);
                ans = ans + S(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String VIEW(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + V(depth-1);
                ans = ans + I(depth-1);
                ans = ans + E(depth-1);
                ans = ans + W(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String WHERE(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + W(depth-1);
                ans = ans + H(depth-1);
                ans = ans + E(depth-1);
                ans = ans + R(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String STUB(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + S(depth-1);
                ans = ans + T(depth-1);
                ans = ans + U(depth-1);
                ans = ans + B(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String LB(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node109(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String RB(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node110(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String LT(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node111(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String GT(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node112(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String EQ(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node113(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String SC(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node114(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String US(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node115(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String DIGIT(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node116(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String SPACE(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node117(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String NL(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node118(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String A(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node119(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String B(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node120(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String C(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node121(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String D(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node122(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String E(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node123(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String F(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node124(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String G(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node125(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String H(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node126(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String I(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node127(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String J(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node128(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String K(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node129(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String L(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node130(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String M(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node131(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String N(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node132(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String O(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node133(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String P(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node134(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String Q(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node135(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String R(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node136(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String S(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node137(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String T(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node138(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String U(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node139(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String V(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node140(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String W(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node141(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String X(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node142(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String Y(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node143(depth-1);
        
        this.pop_rpid();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String Z(int depth) throws Exception{
        this.push_rpid();

        String ans="";
        ans = ans + Node144(depth-1);
        
        this.pop_rpid();
        return ans;
    }//ALTERNATION_NODE
//MIN_DEPTH = 5
    public String Node73(int depth) throws Exception{

        double total_weight = 2.0;
        if (depth < 5){
            return Node74(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                return Node74(depth-1);
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                return Node75(depth-1);
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node74(int depth) throws Exception{

        String ans="";
        ans = ans + DATABASE(depth-1);
        
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node75(int depth) throws Exception{

        String ans="";
        ans = ans + SCHEMA(depth-1);
        
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 5
    public String Node76(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(0, 1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + ifNotExists(depth-1);

        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node77(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(0, 1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + TEMPORARY(depth-1);

        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 5
    public String Node78(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(0, 1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + ifNotExists(depth-1);

        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node79(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(1, 3);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(1, 3);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + Node80(depth-1);
            ans = ans + columnName(depth-1, "a", true, null, null);
            ans = ans + INT(depth-1);
            ans = ans + Node81(depth-1);

        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node80(int depth){

        return ","; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node81(int depth){
          
        return "";
    }//QUANTIFIER_NODE
//MIN_DEPTH = 6
    public String Node82(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(0, 1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + Node83(depth-1);

        }
        return ans;
    }
//ALTERNATION_NODE
//MIN_DEPTH = 5
    public String Node83(int depth) throws Exception{

        double total_weight = 3.0;
        if (depth < 5){
            return Node84(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                return Node84(depth-1);
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                return Node85(depth-1);
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                return Node86(depth-1);
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node84(int depth) throws Exception{

        String ans="";
        ans = ans + LOW_PRIORITY(depth-1);
        
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node85(int depth) throws Exception{

        String ans="";
        ans = ans + DELAYED(depth-1);
        
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node86(int depth) throws Exception{

        String ans="";
        ans = ans + HIGH_PRIORITY(depth-1);
        
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node87(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(0, 1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + IGNORE(depth-1);

        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node88(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(0, 1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + INTO(depth-1);

        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node89(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(0, 1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + Node90(depth-1);
            ans = ans + columnName(depth-1, "id1", false, null, "t");
            ans = ans + Node91(depth-1);
            ans = ans + Node94(depth-1);

        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node90(int depth){

        return "("; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node91(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = "a";
        if (rpid==null){
                count = exp_decay_random(0, 2);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 2);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + Node92(depth-1);
            ans = ans + columnName(depth-1, "id1", false, null, "t");
            ans = ans + Node93(depth-1);

        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node92(int depth){

        return ","; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node93(int depth){
           
        return "";
    }//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node94(int depth){

        return ")"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node95(int depth){

        return "("; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node96(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = "a";
        if (rpid==null){
                count = exp_decay_random(0, 2);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 2);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + Node97(depth-1);
            ans = ans + INT_VAL(depth-1);
            ans = ans + Node98(depth-1);

        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node97(int depth){

        return ","; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node98(int depth){
           
        return "";
    }//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node99(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(0, 1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + LOW_PRIORITY(depth-1);

        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node100(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(0, 1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + IGNORE(depth-1);

        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node101(int depth){

        return "="; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node102(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(0, 8);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 8);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + Node103(depth-1);
            ans = ans + columnName(depth-1, "id1", false, null, "t");
            ans = ans + Node104(depth-1);
            ans = ans + INT_VAL(depth-1);

        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node103(int depth){

        return ","; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node104(int depth){

        return "="; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node105(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(0, 1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(0, 1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + WHERE(depth-1);
            ans = ans + columnName(depth-1, null, false, null, "t");
            ans = ans + Node106(depth-1);
            ans = ans + INT_VAL(depth-1);

        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node106(int depth){

        return "="; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node107(int depth) throws Exception{

        String ans = "";
        int count = -1;
        String rpid = null;
        if (rpid==null){
                count = exp_decay_random(1, 4);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = exp_decay_random(1, 4);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
        }
        for (int i=0; i<count; i++){
            ans = ans + DIGIT(depth-1);
            ans = ans + Node108(depth-1);

        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node108(int depth){
         
        return "";
    }//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node109(int depth){

        return "("; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node110(int depth){

        return ")"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node111(int depth){

        return "<"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node112(int depth){

        return ">"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node113(int depth){

        return "="; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node114(int depth){

        return ";"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node115(int depth){

        return "_"; 
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node116(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(48);
        indices.add(58);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node117(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(32);
        indices.add(33);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node118(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(10);
        indices.add(11);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node119(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(65);
        indices.add(66);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node120(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(66);
        indices.add(67);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node121(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(67);
        indices.add(68);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node122(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(68);
        indices.add(69);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node123(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(69);
        indices.add(70);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node124(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(70);
        indices.add(71);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node125(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(71);
        indices.add(72);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node126(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(72);
        indices.add(73);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node127(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(73);
        indices.add(74);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node128(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(74);
        indices.add(75);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node129(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(75);
        indices.add(76);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node130(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(76);
        indices.add(77);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node131(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(77);
        indices.add(78);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node132(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(78);
        indices.add(79);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node133(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(79);
        indices.add(80);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node134(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(80);
        indices.add(81);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node135(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(81);
        indices.add(82);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node136(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(82);
        indices.add(83);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node137(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(83);
        indices.add(84);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node138(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(84);
        indices.add(85);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node139(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(85);
        indices.add(86);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node140(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(86);
        indices.add(87);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node141(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(87);
        indices.add(88);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node142(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(88);
        indices.add(89);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node143(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(89);
        indices.add(90);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node144(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(90);
        indices.add(91);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }

}

