//FUZZER
public class <graph_name\>Fuzzer {
    private SQLConnection con;
    private List<String> test_case_buffer;
    private int test_case_cursor;
    private List<String> expected_error_buffer;

    public <graph_name\>Fuzzer(SQLConnection con){
        this.con = con;
        this.test_case_buffer = new ArrayList<>();
        this.test_case_cursor = 0;
        this.expected_error_buffer = new ArrayList<>();
    }

    public String get_next_statement(){
        if (this.test_case_buffer.size()>this.test_case_cursor){
            String res = this.test_case_buffer.get(this.test_case_cursor);
            this.test_case_cursor++;
            return res;
        }
        return "";
    }

    public List<String> get_test_case(){
        return this.test_case_buffer;
    }

    public List<String> get_expected_errors(){
        return this.expected_error_buffer;
    }

    //This works as a flag to indicate where to insert the methods for schema reference nodes
    //This flag will be commented out during post-processing
    <SCHEMA_NODE\>

    //This works as a flag to indicate where to insert the method for regular rule nodes
    //This flag will be commented out during post-processing
    <RULE_NODE\>
}