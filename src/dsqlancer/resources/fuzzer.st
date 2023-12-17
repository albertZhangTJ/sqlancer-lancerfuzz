//FUZZER
package sqlancer.any;
//SINCE THIS CODE IS AUTOMATICALLY GENERATED, IT IS HARD TO DO SOME RESETTING
//THUS EACH OBJECT OF THIS CLASS IS "ONE-TIME"
//IE EACH OBJECT CAN ONLY GENERATE ONE TEST CASE
//FOR GETTING MULTIPLE TEST CASES, NEW MULTIPLE INSTANCES OF THIS CLASS
import java.util.*;
import sqlancer.SQLConnection;
@SuppressWarnings("unused")
public class <graph_name/>Fuzzer {
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

    public <graph_name/>Fuzzer(SQLConnection con, int fuzzing_depth, int loop_threshold){
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
<DBMS_OPTIONS/>
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
<STAGE/>
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
<RULE/>
}

