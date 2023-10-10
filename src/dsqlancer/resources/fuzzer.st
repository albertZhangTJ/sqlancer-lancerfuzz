//FUZZER
//SINCE THIS CODE IS AUTOMATICALLY GENERATED, IT IS HARD TO DO SOME RESETTING
//THUS EACH OBJECT OF THIS CLASS IS "ONE-TIME"
//IE EACH OBJECT CAN ONLY GENERATE ONE TEST CASE
//FOR GETTING MULTIPLE TEST CASES, NEW MULTIPLE INSTANCES OF THIS CLASS
import java.util.*;
import java.sql.*;
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

    //A flag that can be inserted into the output to indicate an error has occured
    //Usually due to an identifier has yet to be initialized
    //This pattern is extremely unlikely to appear in a normal output
    public static final String ERROR_FLAG = "@#$%^&*!.,?()[]{}+-_="; 

<DBMS_OPTIONS/>

    public <graph_name/>Fuzzer(SQLConnection con, int fuzzing_depth, int loop_threshold){
        this.con = con;
        this.test_case_buffer = new ArrayList<>();
        this.test_case_cursor = 0;
        this.expected_error_buffer = new ArrayList<>();
        this.fuzzing_depth = fuzzing_depth;
        this.loop_threshold = loop_threshold;
        this.identifier_cache_stack = new ArrayList<>();
        this.identifier_cache = new HashMap<>();
    }

    public String get_next_statement(){
        if (this.test_case_buffer.size()>this.test_case_cursor){
            String res = this.test_case_buffer.get(this.test_case_cursor);
            this.test_case_cursor++;
            return res;
        }
        return "";
    }

    public void push_identifier_cache(){
        this.identifier_cache_stack.add(this.identifier_cache);
        this.identifier_cache = new HashMap<>();
    }

    public void pop_identifier_cache(){
        if (this.identifier_cache_stack.size()==0){
            System.out.println("ERROR: identifier stack underflow");
            System.exit(1);
        }
        this.identifier_cache = this.identifier_cache_stack.get(this.identifier_cache_stack.size()-1);
        this.identifier_cache_stack.remove(this.identifier_cache_stack.size()-1);
    }

    public List<String> get_test_case(){
        return this.test_case_buffer;
    }

    public List<String> get_expected_errors(){
        return this.expected_error_buffer;
    }


    private int random(int min, int max){
        return ((int)(Math.random()*(max-min)))+min;
    }

    public void generate(){
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

