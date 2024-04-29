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
    private List<String> expected_error_buffer;
    private List<String> expected_error_buffer_snapshot;

    private List<String> rules_call_series;
    private int rules_call_index;

    //depth limit, passed in by the tester at fuzzing time
    private int fuzzing_depth;
    private int loop_threshold;

    //Rule-wise identifier cache, must be resetted at the beginning of each rule call
    private List<HashMap<String, List<List<String>>>> identifier_cache_stack;
    private int identifier_cache_stack_pointer;
    private HashMap<String, List<List<String>>> identifier_cache;
    private HashMap<String, List<List<String>>> identifier_cache_snapshot;
    
    private HashMap<String, Integer> identifier_cache_cursor;
    private List<HashMap<String, Integer>> identifier_cache_cursor_stack;
    private int identifier_cache_cursor_stack_pointer;
    private HashMap<String, Integer> identifier_cache_cursor_snapshot;

    private List<HashMap<String, Integer>> rpid_stack;
    private int rpid_stack_pointer;
    private HashMap<String, Integer> rpid_cache;
    private HashMap<String, Integer> rpid_cache_snapshot;

    private List<HashMap<String, List<String>>> used_identifier_stack;
    private int used_identifier_stack_pointer;
    private HashMap<String, List<String>> used_identifier; 
    private HashMap<String, List<String>> used_identifier_snapshot;

    private static HashMap<String, String> static_var_ref;
    private HashMap<String, String> member_var_ref;
    private HashMap<String, String> var_ref;
    private List<HashMap<String, String>> var_ref_stack;

    //A flag that can be inserted into the output to indicate an error has occured
    //Usually due to an identifier has yet to be initialized
    //This pattern is extremely unlikely to appear in a normal output
    public static final String ERROR_FLAG = "PbEMuNhfsedBcVpUrBb2ZkWzpQkCkM5jTOCnUCTrYE9LgUpR2nZU45UgGHb3OS9NOrAUnWgrfvwUfGGmE0wdagXkOjv6psM4U9QnMWu75iFkgR5icHxm6xHtlpvQTy1OscMVNISBbaFLsNmYqzcHlpovwFtLJOlx0t7paPhOyTXQWuFjoGBgiQhWENvt116uaXQK9P0YO8ZkIVYOUcb1GzDh9G5Arns5guX8cqdTbDJdzWfvCalMevPYVXB7bAtwH8ZcyCNfabjt1ot8Wpj6xuCsn6u9WFuYao9enmV2tevt2DCqGlKfzA4ZZGuFev9M"; 

    private static HashMap<String, String> dbms_options = new HashMap<>();

    private String crash_log;


    public Fuzzer(SQLConnection con, int fuzzing_depth, int loop_threshold){
        this.con = con;
        this.expected_error_buffer = new ArrayList<>();
        this.rules_call_series = new ArrayList<>();
        this.rules_call_index = 0;
        this.fuzzing_depth = fuzzing_depth;
        this.loop_threshold = loop_threshold;

        this.identifier_cache_stack = new ArrayList<>();
        this.identifier_cache_stack_pointer = 0;
        this.identifier_cache = new HashMap<>();

        this.identifier_cache_cursor = new HashMap<>();
        this.identifier_cache_cursor_stack = new ArrayList<>();
        this.identifier_cache_cursor_stack_pointer = 0;
        
        this.used_identifier_stack = new ArrayList<>();
        this.used_identifier_stack_pointer = 0;
        this.used_identifier = new HashMap<>();

        this.rpid_stack = new ArrayList<>();
        this.rpid_stack_pointer = 0;
        this.rpid_cache = new HashMap<>();

        this.member_var_ref = new HashMap<>();
        this.var_ref = new HashMap<>();
        this.var_ref_stack = new ArrayList<>();
    }

    public String get_crash_log(){
        return this.crash_log;
    }

    private void log(Object entry){
        this.crash_log = this.crash_log + entry.toString() +"\n";
    }

    public static void init(){
        static_var_ref = new HashMap<>();
        dbms_options.put("host","localhost");
        dbms_options.put("port","3306");
        dbms_options.put("username","new_user");
        dbms_options.put("password","password");
        dbms_options.put("db_prefix","mysql");
        dbms_options.put("conn_str","jdbc:($db_prefix$)://($host$):($port$)");
        dbms_options.put("jdbc_class","com.mysql.cj.jdbc.Driver");

    }


    public String generate_rule(String rule_name) throws Exception{
        //this.log("Translating: "+rule_name);
//CALL_RULE_NAME
        if (rule_name.equals("dropDatabase")){
            return dropDatabase(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("dropSchema")){
            return dropSchema(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("createDatabase")){
            return createDatabase(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("useDatabase")){
            return useDatabase(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("createTable")){
            return createTable(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("insertStatement")){
            return insertStatement(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("updateStatement")){
            return updateStatement(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("truncateTable")){
            return truncateTable(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("EOF")){
            return EOF(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("selectStatement")){
            return selectStatement(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("pre")){
            return pre(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("INT_VAL")){
            return INT_VAL(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("TEXT_VAL")){
            return TEXT_VAL(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("ifNotExists")){
            return ifNotExists(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("ifExists")){
            return ifExists(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("AS")){
            return AS(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("BY")){
            return BY(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("CREATE")){
            return CREATE(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("DATABASE")){
            return DATABASE(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("DELAYED")){
            return DELAYED(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("DROP")){
            return DROP(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("EXISTS")){
            return EXISTS(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("FROM")){
            return FROM(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("HASH")){
            return HASH(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("HIGH_PRIORITY")){
            return HIGH_PRIORITY(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("IF")){
            return IF(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("IGNORE")){
            return IGNORE(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("INSERT")){
            return INSERT(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("INT")){
            return INT(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("INTO")){
            return INTO(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("LIKE")){
            return LIKE(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("LINEAR")){
            return LINEAR(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("LOW_PRIORITY")){
            return LOW_PRIORITY(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("NOT")){
            return NOT(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("ON")){
            return ON(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("PARTITION")){
            return PARTITION(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("REPLACE")){
            return REPLACE(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("SCHEMA")){
            return SCHEMA(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("SELECT")){
            return SELECT(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("SET")){
            return SET(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("TABLE")){
            return TABLE(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("TEMPORARY")){
            return TEMPORARY(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("TEXT")){
            return TEXT(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("TRUNCATE")){
            return TRUNCATE(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("UPDATE")){
            return UPDATE(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("USE")){
            return USE(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("VALUES")){
            return VALUES(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("VIEW")){
            return VIEW(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("WHERE")){
            return WHERE(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("STUB")){
            return STUB(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("LB")){
            return LB(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("RB")){
            return RB(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("LT")){
            return LT(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("GT")){
            return GT(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("EQ")){
            return EQ(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("SC")){
            return SC(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("US")){
            return US(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("DIGIT")){
            return DIGIT(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("SPACE")){
            return SPACE(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("NL")){
            return NL(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("A")){
            return A(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("B")){
            return B(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("C")){
            return C(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("D")){
            return D(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("E")){
            return E(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("F")){
            return F(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("G")){
            return G(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("H")){
            return H(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("I")){
            return I(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("J")){
            return J(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("K")){
            return K(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("L")){
            return L(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("M")){
            return M(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("N")){
            return N(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("O")){
            return O(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("P")){
            return P(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("Q")){
            return Q(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("R")){
            return R(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("S")){
            return S(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("T")){
            return T(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("U")){
            return U(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("V")){
            return V(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("W")){
            return W(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("X")){
            return X(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("Y")){
            return Y(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("Z")){
            return Z(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("CH")){
            return CH(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("DQ")){
            return DQ(this.fuzzing_depth);
        }
        this.log("ERROR: rule name "+rule_name+" has no corresponding function call generated");
        throw new Exception();
    }

    public String get_next_statement() throws Exception{
        if (this.rules_call_index<this.rules_call_series.size()){
            this.expected_error_buffer = new ArrayList<>();
            this.crash_log = "";
            try {
                String res = this.generate_rule(this.rules_call_series.get(this.rules_call_index));
                this.rules_call_index++;
                return res;
            }
            catch (Exception e){
                //this.log(crash_log);
                throw e;
            }
        }
        return ERROR_FLAG;
    }

    public String regenerate_last_statement() throws Exception{
        this.rules_call_index--;
        this.expected_error_buffer = new ArrayList<>();
        String res = this.generate_rule(this.rules_call_series.get(this.rules_call_index));
        this.rules_call_index++;
        return res;
    }

    public static String get_dbms_option(String key){
        return dbms_options.get(key);
    }

    private ResultSet query_dbms(String query) throws Exception{
        try {
            this.log("Queried DBMS with: "+query);
            ResultSet ans = this.con.createStatement().executeQuery(query);
            //this.log("Got ans: "+ans.toString());
            return ans;
        }
        catch (Exception e){
            this.log("Error encountered when executing query: "+query);
            throw e;
        }
    }

    public void generate(){
        int count = -1;
//STAGE
//dropDatabase

        count = random(1, 1+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*1.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<1.0){
                this.rules_call_series.add("dropDatabase");
            }


        }
//STAGE
//dropSchema

        count = random(1, 1+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*1.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<1.0){
                this.rules_call_series.add("dropSchema");
            }


        }
//STAGE
//createDatabase

        count = random(1, 1+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*1.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<1.0){
                this.rules_call_series.add("createDatabase");
            }


        }
//STAGE
//useDatabase

        count = random(1, 1+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*1.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<1.0){
                this.rules_call_series.add("useDatabase");
            }


        }
//STAGE
//create

        count = random(10, 30+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*1.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<1.0){
                this.rules_call_series.add("createTable");
            }


        }
//STAGE
//insert

        count = random(50, 70+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*1.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<1.0){
                this.rules_call_series.add("insertStatement");
            }


        }
//STAGE
//update

        count = random(15, 50+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*1.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<1.0){
                this.rules_call_series.add("updateStatement");
            }


        }
//STAGE
//mix

        count = random(100, 500+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*13.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<2.0){
                this.rules_call_series.add("createTable");
            }

//STAGE_SERIALIZE_RULE
            if (index>=2.0 && index<7.0){
                this.rules_call_series.add("insertStatement");
            }

//STAGE_SERIALIZE_RULE
            if (index>=7.0 && index<12.0){
                this.rules_call_series.add("updateStatement");
            }

//STAGE_SERIALIZE_RULE
            if (index>=12.0 && index<13.0){
                this.rules_call_series.add("truncateTable");
            }


        }

    }

    public List<String> get_expected_errors(){
        return this.expected_error_buffer;
    }


    private String get_parent_name(String sup){
        List<List<String>> id_val = this.identifier_cache.get(sup);
        if (id_val==null || id_val.size()==0){
            return null;
        }
        List<String> parent_hier = random_from_list(id_val);
        return parent_hier.get(parent_hier.size()-1);
    }

    private List<String> get_parent_hierarchy(String sup){
        List<List<String>> id_val = this.identifier_cache.get(sup);
        if (id_val==null || id_val.size()==0){
            return null;
        }
        return copy_list(random_from_list(id_val));
    }

    private int get_parent_hierarchy_cursor(String sup){
        return this.identifier_cache_cursor.get(sup);
    }
    
    private void set_parent_hierarchy_cursor(String sup, int new_val){
        this.identifier_cache_cursor.put(sup, new_val);
    }

    private List<String> get_next_parent_hierarchy(String sup){
        List<List<String>> id_val = this.identifier_cache.get(sup);
        if (id_val==null || id_val.size()==0){
            return null;
        }
        
        List<String> ans = copy_list(id_val.get(this.identifier_cache_cursor.get(sup)%id_val.size()));
        this.log("get_next_parent_hierarchy: sup="+sup+"  index="+this.identifier_cache_cursor.get(sup)+"  ans="+ans.toString());
        this.identifier_cache_cursor.put(sup, this.identifier_cache_cursor.get(sup)+1);
        this.log("Identifier index updated to: "+this.identifier_cache_cursor.get(sup));
        return ans;
    }

    private void set_parent(String sub, List<String> hierarchy){
        List<List<String>> id_val = this.identifier_cache.get(sub);
        if (id_val==null){
            List<List<String>> id_val_n = new ArrayList<>();
            id_val_n.add(hierarchy);
            this.identifier_cache.put(sub, id_val_n);
            this.identifier_cache_cursor.put(sub, 0);
        }
        else {
            id_val.add(hierarchy);
        }
    }

    private void push_identifier_cache(){
        this.identifier_cache = new HashMap<>();
        this.identifier_cache_stack.add(this.identifier_cache_stack_pointer, this.identifier_cache);
        this.identifier_cache_stack_pointer++;
        
        this.identifier_cache_cursor = new HashMap<>();
        this.identifier_cache_cursor_stack.add(this.identifier_cache_cursor_stack_pointer, this.identifier_cache_cursor);
        this.identifier_cache_cursor_stack_pointer++;
    }

    private void restore_identifier_cache(){
        this.identifier_cache = this.identifier_cache_stack.get(this.identifier_cache_stack_pointer);
        this.identifier_cache_stack_pointer++;

        this.identifier_cache_cursor = this.identifier_cache_cursor_stack.get(this.identifier_cache_cursor_stack_pointer);
        this.identifier_cache_cursor_stack_pointer++;
    }

    private void pop_identifier_cache() throws Exception{
        if (this.identifier_cache_stack.size()==0){
            this.log("ERROR: identifier stack underflow");
            throw new Exception("ERROR: identifier stack underflow");
        }
        this.identifier_cache_stack_pointer--;
        if (this.identifier_cache_stack_pointer==0){
            this.identifier_cache = new HashMap<>();
        }
        else {
            this.identifier_cache = this.identifier_cache_stack.get(this.identifier_cache_stack_pointer-1);
        }

        if (this.identifier_cache_cursor_stack.size()==0){
            this.log("ERROR: identifier cursor stack underflow");
        }
        this.identifier_cache_cursor_stack_pointer--;
        if (this.identifier_cache_cursor_stack_pointer==0){
            this.identifier_cache_cursor = new HashMap<>();
        }
        else {
            this.identifier_cache_cursor = this.identifier_cache_cursor_stack.get(this.identifier_cache_cursor_stack_pointer-1);
        }
    }

    private void push_rpid(){
        //once we try to push something, all the potentially restorable stack frames should be deemed invalid
        while(this.rpid_stack.size()>this.rpid_stack_pointer){
            this.rpid_stack.remove(this.rpid_stack.size()-1);
        }
        this.rpid_cache = new HashMap<>();
        this.rpid_stack.add(this.rpid_stack_pointer, this.rpid_cache);
        this.rpid_stack_pointer++;
    }

    //this is like push, but without invalidating all further stack frames
    //also the cache will be given a on-stack frame instead of a new frame
    private void restore_rpid(){
        this.rpid_cache = this.rpid_stack.get(this.rpid_stack_pointer);
        this.rpid_stack_pointer++;
    }

    private void pop_rpid() throws Exception{
        if (this.rpid_stack_pointer==0){
            this.log("ERROR: rpid stack underflow");
            throw new Exception();
        }
        this.rpid_stack_pointer--;
        if (this.rpid_stack_pointer==0){
            this.rpid_cache = new HashMap<>();
        }
        else {
            this.rpid_cache = this.rpid_stack.get(this.rpid_stack_pointer-1);
        }
    }

    private void push_used_identifier(){
        //once we try to push something, all the potentially restorable stack frames should be deemed invalid
        while(this.used_identifier_stack.size()>this.used_identifier_stack_pointer){
            this.used_identifier_stack.remove(this.used_identifier_stack.size()-1);
        }
        this.used_identifier = new HashMap<>();
        this.used_identifier_stack.add(this.used_identifier_stack_pointer, this.used_identifier);
        this.used_identifier_stack_pointer++;
    }

    //this is like push, but without invalidating all further stack frames
    //also the cache will be given a on-stack frame instead of a new frame
    private void restore_used_identifier(){
        this.used_identifier = this.used_identifier_stack.get(this.used_identifier_stack_pointer);
        this.used_identifier_stack_pointer++;
    }
    private void pop_used_identifier() throws Exception{
        if (this.used_identifier_stack_pointer==0){
            this.log("ERROR: used identifier stack underflow");
            throw new Exception();
        }
        this.used_identifier_stack_pointer--;
        if (this.used_identifier_stack_pointer==0){
            this.used_identifier = new HashMap<>();
        }
        else{
            this.used_identifier = this.used_identifier_stack.get(this.used_identifier_stack_pointer-1);
        }
    }

    //it is the callee's responsibility to create new context for itself at entry
    //and restore context for caller when exiting
    private void push_context(){
        this.push_identifier_cache();
        this.push_rpid();
        this.push_used_identifier();
    }
    private void restore_context(){
        //check if all stacks have something to restore
        //these stacks SHOULD have the same size, but just to be safe we will check every one
        if (this.identifier_cache_stack.size()>this.identifier_cache_stack_pointer &&
                this.identifier_cache_cursor_stack.size()>this.identifier_cache_cursor_stack_pointer &&
                this.rpid_stack.size()>this.rpid_stack_pointer &&
                this.used_identifier_stack.size()>this.used_identifier_stack_pointer){
            this.log("Restoring stack frame");
            this.restore_identifier_cache();
            this.restore_rpid();
            this.restore_used_identifier();
        }
    }
    private void pop_context() throws Exception{
        this.pop_used_identifier();
        this.pop_rpid();
        this.pop_identifier_cache();
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

    private void snapshot_context(){
        // snapshot relationship identifiers
        this.identifier_cache_snapshot = new HashMap<>();
        for (HashMap.Entry<String, List<List<String>>> entry: this.identifier_cache.entrySet()){
            this.identifier_cache_snapshot.put(entry.getKey(), copy_list(entry.getValue()));
        }

        //snapshot the cursor for the identifiers above
        this.identifier_cache_cursor_snapshot = new HashMap<>();
        for (HashMap.Entry<String, Integer> entry: this.identifier_cache_cursor.entrySet()){
            this.identifier_cache_cursor_snapshot.put(entry.getKey(), entry.getValue());
        }

        // snapshot used identifiers
        this.used_identifier_snapshot = new HashMap<>();
        for (HashMap.Entry<String, List<String>> entry: this.used_identifier.entrySet()){
            this.used_identifier_snapshot.put(entry.getKey(), copy_list(entry.getValue()));
        }

        // snapshot rpid cache
        this.rpid_cache_snapshot = new HashMap<>();
        for (HashMap.Entry<String, Integer> entry: this.rpid_cache.entrySet()){
            this.rpid_cache_snapshot.put(entry.getKey(), entry.getValue());
        }

        // snapshot expected errors
        this.expected_error_buffer_snapshot = copy_list(this.expected_error_buffer);
    }

    private void rollback_context(){
        //this.log("Rolling back");
        this.identifier_cache = this.identifier_cache_snapshot;
        this.identifier_cache_cursor = this.identifier_cache_cursor_snapshot;
        this.used_identifier = this.used_identifier_snapshot;
        this.rpid_cache = this.rpid_cache_snapshot;
        this.expected_error_buffer = this.expected_error_buffer_snapshot;

        //for the contexts that resides on the context stacks
        //update the pointers on the context stacks as well
        this.identifier_cache_stack.set(this.identifier_cache_stack_pointer-1, this.identifier_cache_snapshot);
        this.identifier_cache_cursor_stack.set(this.identifier_cache_cursor_stack_pointer-1, this.identifier_cache_cursor_snapshot);
        this.rpid_stack.set(this.rpid_stack_pointer-1, this.rpid_cache_snapshot);
        this.used_identifier_stack.set(this.used_identifier_stack_pointer-1, this.used_identifier_snapshot);
        //this.log("Rolled back");
    }

    private String get_variable(String key){
        return this.var_ref.get(key);
    }

    private String get_member_variable(String key){
        return this.member_var_ref.get(key);
    }

    public static String get_static_variable(String key){
        return static_var_ref.get(key);
    }

    private void set_variable(String key, String value){
        this.var_ref.put(key, value);
    }

    private void set_member_variable(String key, String value){
        member_var_ref.put(key, value);
    }

    public static void set_static_variable(String key, String value){
        static_var_ref.put(key, value);
    }

    

    private int random(int min, int max){
        return (int)(Math.random()*(max-min))+min;
    }

    private int exp_decay_random(int min, int max){
        return this.exp_decay_random(min, max, 0.75);
    }

    private int exp_decay_random(int min, int max, double decay_rate){
        int i=min;
        while (i<max){
            if (Math.random()<decay_rate){
                return i;
            }
            i = i + 1;
        }
        return max-1;
    }

    @SuppressWarnings("unchecked")
    private static <T> List<T> copy_list(List<T> ori){
        if (ori==null){
            return null;
        }
        List<T> res = new ArrayList<>();
        for (T item: ori){
            if (item instanceof List){
                res.add((T)copy_list((List<?>)item));
            }
            else{
                res.add(item);
            }
        }
        return res;
    }

    private <T> T random_from_list(List<T> l){
        return l.get(random(0, l.size()));
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
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";

        this.pop_context();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 3
    public String dropDatabase(int depth) throws Exception{

        if (false){
            this.restore_context();
        }
        else{
            this.push_context();
        }

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = DROP(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = DATABASE(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = ifExists(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node93(depth-1);
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=4 || cache.get(4).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SC(depth-1);
                if (cache.size()<=4){
                    cache.add(tmp);
                }
                else {
                    cache.set(4, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
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
                    this.log("ERROR: dropDatabase cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    throw new Exception();
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 3
    public String dropSchema(int depth) throws Exception{

        if (false){
            this.restore_context();
        }
        else{
            this.push_context();
        }

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = DROP(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SCHEMA(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = ifExists(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node97(depth-1);
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=4 || cache.get(4).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SC(depth-1);
                if (cache.size()<=4){
                    cache.add(tmp);
                }
                else {
                    cache.set(4, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
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
                    this.log("ERROR: dropSchema cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    throw new Exception();
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 3
    public String createDatabase(int depth) throws Exception{

        if (false){
            this.restore_context();
        }
        else{
            this.push_context();
        }

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = CREATE(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node101(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node104(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node105(depth-1);
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=4 || cache.get(4).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SC(depth-1);
                if (cache.size()<=4){
                    cache.add(tmp);
                }
                else {
                    cache.set(4, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
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
                    this.log("ERROR: createDatabase cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    throw new Exception();
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 3
    public String useDatabase(int depth) throws Exception{

        if (false){
            this.restore_context();
        }
        else{
            this.push_context();
        }

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = USE(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node109(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SC(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
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
                    this.log("ERROR: useDatabase cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    throw new Exception();
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 2
    public String createTable(int depth) throws Exception{
        this.expected_error_buffer.add("A BLOB field is not allowed in partition function");

        if (false){
            this.restore_context();
        }
        else{
            this.push_context();
        }

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = CREATE(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node113(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node114(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = TABLE(depth-1);
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=4 || cache.get(4).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node120(depth-1);
                if (cache.size()<=4){
                    cache.add(tmp);
                }
                else {
                    cache.set(4, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=5 || cache.get(5).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = tableName(depth-1, "b", true, null, null);
                if (cache.size()<=5){
                    cache.add(tmp);
                }
                else {
                    cache.set(5, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=6 || cache.get(6).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node121(depth-1);
                if (cache.size()<=6){
                    cache.add(tmp);
                }
                else {
                    cache.set(6, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=7 || cache.get(7).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SC(depth-1);
                if (cache.size()<=7){
                    cache.add(tmp);
                }
                else {
                    cache.set(7, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
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
                    this.log("ERROR: createTable cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    throw new Exception();
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 3
    public String truncateTable(int depth) throws Exception{

        if (false){
            this.restore_context();
        }
        else{
            this.push_context();
        }

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = TRUNCATE(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = TABLE(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = tableName(depth-1, null, false, "t", null);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SC(depth-1);
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
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
                    this.log("ERROR: truncateTable cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    throw new Exception();
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 2
    public String insertStatement(int depth) throws Exception{

        if (false){
            this.restore_context();
        }
        else{
            this.push_context();
        }

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node167(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node176(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = tableName(depth-1, null, false, "t", null);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node177(depth-1);
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=4 || cache.get(4).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = columnName(depth-1, "id1", false, "c", "t");
                if (cache.size()<=4){
                    cache.add(tmp);
                }
                else {
                    cache.set(4, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=5 || cache.get(5).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node178(depth-1);
                if (cache.size()<=5){
                    cache.add(tmp);
                }
                else {
                    cache.set(5, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=6 || cache.get(6).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node181(depth-1);
                if (cache.size()<=6){
                    cache.add(tmp);
                }
                else {
                    cache.set(6, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=7 || cache.get(7).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = VALUES(depth-1);
                if (cache.size()<=7){
                    cache.add(tmp);
                }
                else {
                    cache.set(7, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=8 || cache.get(8).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node182(depth-1);
                if (cache.size()<=8){
                    cache.add(tmp);
                }
                else {
                    cache.set(8, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=9 || cache.get(9).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = expr(depth-1, "c");
                if (cache.size()<=9){
                    cache.add(tmp);
                }
                else {
                    cache.set(9, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=10 || cache.get(10).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node183(depth-1);
                if (cache.size()<=10){
                    cache.add(tmp);
                }
                else {
                    cache.set(10, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=11 || cache.get(11).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node186(depth-1);
                if (cache.size()<=11){
                    cache.add(tmp);
                }
                else {
                    cache.set(11, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=12 || cache.get(12).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SC(depth-1);
                if (cache.size()<=12){
                    cache.add(tmp);
                }
                else {
                    cache.set(12, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
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
                    this.log("ERROR: insertStatement cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    throw new Exception();
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 2
    public String updateStatement(int depth) throws Exception{

        if (false){
            this.restore_context();
        }
        else{
            this.push_context();
        }

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = UPDATE(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node187(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node188(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = tableName(depth-1, null, false, "t", null);
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=4 || cache.get(4).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SET(depth-1);
                if (cache.size()<=4){
                    cache.add(tmp);
                }
                else {
                    cache.set(4, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=5 || cache.get(5).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = columnName(depth-1, "id1", false, "c", "t");
                if (cache.size()<=5){
                    cache.add(tmp);
                }
                else {
                    cache.set(5, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=6 || cache.get(6).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node189(depth-1);
                if (cache.size()<=6){
                    cache.add(tmp);
                }
                else {
                    cache.set(6, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=7 || cache.get(7).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = expr(depth-1, "c");
                if (cache.size()<=7){
                    cache.add(tmp);
                }
                else {
                    cache.set(7, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=8 || cache.get(8).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node190(depth-1);
                if (cache.size()<=8){
                    cache.add(tmp);
                }
                else {
                    cache.set(8, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=9 || cache.get(9).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node194(depth-1);
                if (cache.size()<=9){
                    cache.add(tmp);
                }
                else {
                    cache.set(9, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=10 || cache.get(10).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SC(depth-1);
                if (cache.size()<=10){
                    cache.add(tmp);
                }
                else {
                    cache.set(10, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
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
                    this.log("ERROR: updateStatement cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    throw new Exception();
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//EXPR_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private String expr(int depth) throws Exception{
        return expr(depth, null);
    }
    private String expr(int depth, String sup) throws Exception{

        String ans = "";
        List<String> parent_name = null;
        String type = "";
        if (sup!=null){
            parent_name = this.get_next_parent_hierarchy(sup);
            if (parent_name==null || parent_name.size()==0){
                return ERROR_FLAG;
            }
            String query = "SHOW COLUMNS FROM " + parent_name.get(0) + " WHERE Field='" + parent_name.get(1) + "';";
            //this.log("Executing query: "+query);
            ResultSet rs = this.query_dbms(query);
            List<String> values = new ArrayList<>();
            while (rs.next()){
                values.add(rs.getString("Type"));
                this.log("Type added: "+rs.getString("Type")+" for parent: "+parent_name.toString());
            }
            type = values.get(0);
        }
        // sub nodes of an expression are not expected to fail
        ans = ans + Node197(depth-1, type);

        return ans;
    }


    //UNPARSER_RULE_NODE
//MIN_DEPTH = 3
    public String selectStatement(int depth) throws Exception{

        if (false){
            this.restore_context();
        }
        else{
            this.push_context();
        }

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = SELECT(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = columnName(depth-1, "id1", false, "c", "t");
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node202(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = FROM(depth-1);
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=4 || cache.get(4).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = tableName(depth-1, null, false, "t", null);
                if (cache.size()<=4){
                    cache.add(tmp);
                }
                else {
                    cache.set(4, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
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
                    this.log("ERROR: selectStatement cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    throw new Exception();
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 2
    public String pre(int depth) throws Exception{

        if (true){
            this.restore_context();
        }
        else{
            this.push_context();
        }

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node205(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = columnName(depth-1, "id1", false, "cc", "t");
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node206(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=3 || cache.get(3).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = expr(depth-1, "cc");
                if (cache.size()<=3){
                    cache.add(tmp);
                }
                else {
                    cache.set(3, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=4 || cache.get(4).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node207(depth-1);
                if (cache.size()<=4){
                    cache.add(tmp);
                }
                else {
                    cache.set(4, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
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
                    this.log("ERROR: pre cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    throw new Exception();
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String INT_VAL(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node208(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String TEXT_VAL(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + DQ(depth-1);
                ans = ans + Node210(depth-1);
                ans = ans + DQ(depth-1);
        
        this.pop_context();
        return ans;
    }//SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int dbName_count = 0;
    private String dbName(int depth, String iid, boolean is_new, String sub, String sup) throws Exception{

        String ans = "";
        List<String> parent_name = null;
        if (is_new){
            dbName_count++;
            ans = "dbName"+dbName_count;
        }
        else {
            if (sup!=null){
                parent_name = this.get_parent_hierarchy(sup);
                if (parent_name==null || parent_name.size()==0){
                    return ERROR_FLAG;
                }
            }
            String query = "SHOW DATABASES;";
            ResultSet rs = this.query_dbms(query);
            List<String> values = new ArrayList<>();
            int cnt=0;
            while (rs.next()){
                cnt++;
                //this.log(rs.getString("Database"));
                if (iid==null || this.is_identifier_available(iid, rs.getString("Database") )){
                    //this.log("Added");
                    values.add(rs.getString("Database"));
                }
            }
            if (values.size()==0){
                this.log("Queried target DBMS with: "+query);
                this.log("ResultSet has length: "+cnt);
                throw new Exception("IgnoreMe: quantifier node multiplier cannot be satisfied");
            }
            ans = random_from_list(values);
            if (iid!=null){
                this.mark_identifier_used(iid, ans);
            }
        }
        if (sub!=null){
            if (parent_name==null){
                List<String> hierarchy = new ArrayList<>();
                hierarchy.add(ans);
                this.set_parent(sub, hierarchy);
            }
            else {
                List<String> hierarchy = copy_list(parent_name);
                hierarchy.add(ans);
                this.set_parent(sub, hierarchy);
            }
        }
        return ans;
    }


    //SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int tableName_count = 0;
    private String tableName(int depth, String iid, boolean is_new, String sub, String sup) throws Exception{

        String ans = "";
        List<String> parent_name = null;
        if (is_new){
            tableName_count++;
            ans = "tableName"+tableName_count;
        }
        else {
            if (sup!=null){
                parent_name = this.get_parent_hierarchy(sup);
                if (parent_name==null || parent_name.size()==0){
                    return ERROR_FLAG;
                }
            }
            String query = "SHOW TABLES;";
            ResultSet rs = this.query_dbms(query);
            List<String> values = new ArrayList<>();
            int cnt=0;
            while (rs.next()){
                cnt++;
                //this.log(rs.getString("Tables_in_" + get_static_variable("db") + ""));
                if (iid==null || this.is_identifier_available(iid, rs.getString("Tables_in_" + get_static_variable("db") + "") )){
                    //this.log("Added");
                    values.add(rs.getString("Tables_in_" + get_static_variable("db") + ""));
                }
            }
            if (values.size()==0){
                this.log("Queried target DBMS with: "+query);
                this.log("ResultSet has length: "+cnt);
                throw new Exception("IgnoreMe: quantifier node multiplier cannot be satisfied");
            }
            ans = random_from_list(values);
            if (iid!=null){
                this.mark_identifier_used(iid, ans);
            }
        }
        if (sub!=null){
            if (parent_name==null){
                List<String> hierarchy = new ArrayList<>();
                hierarchy.add(ans);
                this.set_parent(sub, hierarchy);
            }
            else {
                List<String> hierarchy = copy_list(parent_name);
                hierarchy.add(ans);
                this.set_parent(sub, hierarchy);
            }
        }
        return ans;
    }


    //SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int columnName_count = 0;
    private String columnName(int depth, String iid, boolean is_new, String sub, String sup) throws Exception{

        String ans = "";
        List<String> parent_name = null;
        if (is_new){
            columnName_count++;
            ans = "columnName"+columnName_count;
        }
        else {
            if (sup!=null){
                parent_name = this.get_parent_hierarchy(sup);
                if (parent_name==null || parent_name.size()==0){
                    return ERROR_FLAG;
                }
            }
            String query = "SHOW COLUMNS FROM " + parent_name.get(0) + ";";
            ResultSet rs = this.query_dbms(query);
            List<String> values = new ArrayList<>();
            int cnt=0;
            while (rs.next()){
                cnt++;
                //this.log(rs.getString("Field"));
                if (iid==null || this.is_identifier_available(iid, rs.getString("Field") )){
                    //this.log("Added");
                    values.add(rs.getString("Field"));
                }
            }
            if (values.size()==0){
                this.log("Queried target DBMS with: "+query);
                this.log("ResultSet has length: "+cnt);
                throw new Exception("IgnoreMe: quantifier node multiplier cannot be satisfied");
            }
            ans = random_from_list(values);
            if (iid!=null){
                this.mark_identifier_used(iid, ans);
            }
        }
        if (sub!=null){
            if (parent_name==null){
                List<String> hierarchy = new ArrayList<>();
                hierarchy.add(ans);
                this.set_parent(sub, hierarchy);
            }
            else {
                List<String> hierarchy = copy_list(parent_name);
                hierarchy.add(ans);
                this.set_parent(sub, hierarchy);
            }
        }
        return ans;
    }


    //UNPARSER_RULE_NODE
//MIN_DEPTH = 4
    public String ifNotExists(int depth) throws Exception{

        if (false){
            this.restore_context();
        }
        else{
            this.push_context();
        }

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = IF(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = NOT(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=2 || cache.get(2).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = EXISTS(depth-1);
                if (cache.size()<=2){
                    cache.add(tmp);
                }
                else {
                    cache.set(2, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
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
                    this.log("ERROR: ifNotExists cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    throw new Exception();
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 4
    public String ifExists(int depth) throws Exception{

        if (false){
            this.restore_context();
        }
        else{
            this.push_context();
        }

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = IF(depth-1);
                if (cache.size()<=0){
                    cache.add(tmp);
                }
                else {
                    cache.set(0, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
                }
            }//UNPARSER_CALL_CHILDREN
            if (cache.size()<=1 || cache.get(1).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = EXISTS(depth-1);
                if (cache.size()<=1){
                    cache.add(tmp);
                }
                else {
                    cache.set(1, tmp);
                }
                // since this branch contains ERROR_FLAG
                // it will be rewalked with a possibly different set of outputs & side effects
                // thus any change in context shall be reverted
                if (tmp.indexOf(ERROR_FLAG)!=-1){
                    this.rollback_context();
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
                    this.log("ERROR: ifExists cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    throw new Exception();
                }
            }
        }
        catch (Exception e){
            this.log(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String AS(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + A(depth-1);
                ans = ans + S(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String BY(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + B(depth-1);
                ans = ans + Y(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String CREATE(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + C(depth-1);
                ans = ans + R(depth-1);
                ans = ans + E(depth-1);
                ans = ans + A(depth-1);
                ans = ans + T(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String DATABASE(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

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
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String DELAYED(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

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
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String DROP(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + D(depth-1);
                ans = ans + R(depth-1);
                ans = ans + O(depth-1);
                ans = ans + P(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String EXISTS(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + E(depth-1);
                ans = ans + X(depth-1);
                ans = ans + I(depth-1);
                ans = ans + S(depth-1);
                ans = ans + T(depth-1);
                ans = ans + S(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String FROM(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + F(depth-1);
                ans = ans + R(depth-1);
                ans = ans + O(depth-1);
                ans = ans + M(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String HASH(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + H(depth-1);
                ans = ans + A(depth-1);
                ans = ans + S(depth-1);
                ans = ans + H(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String HIGH_PRIORITY(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

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
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String IF(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + I(depth-1);
                ans = ans + F(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String IGNORE(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + I(depth-1);
                ans = ans + G(depth-1);
                ans = ans + N(depth-1);
                ans = ans + O(depth-1);
                ans = ans + R(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String INSERT(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + I(depth-1);
                ans = ans + N(depth-1);
                ans = ans + S(depth-1);
                ans = ans + E(depth-1);
                ans = ans + R(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String INT(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + I(depth-1);
                ans = ans + N(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String INTO(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + I(depth-1);
                ans = ans + N(depth-1);
                ans = ans + T(depth-1);
                ans = ans + O(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String LIKE(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + L(depth-1);
                ans = ans + I(depth-1);
                ans = ans + K(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String LINEAR(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + L(depth-1);
                ans = ans + I(depth-1);
                ans = ans + N(depth-1);
                ans = ans + E(depth-1);
                ans = ans + A(depth-1);
                ans = ans + R(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String LOW_PRIORITY(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

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
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String NOT(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + N(depth-1);
                ans = ans + O(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String ON(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + O(depth-1);
                ans = ans + N(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String PARTITION(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + P(depth-1);
                ans = ans + A(depth-1);
                ans = ans + R(depth-1);
                ans = ans + T(depth-1);
                ans = ans + I(depth-1);
                ans = ans + T(depth-1);
                ans = ans + I(depth-1);
                ans = ans + O(depth-1);
                ans = ans + N(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String REPLACE(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + R(depth-1);
                ans = ans + E(depth-1);
                ans = ans + P(depth-1);
                ans = ans + L(depth-1);
                ans = ans + A(depth-1);
                ans = ans + C(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String SCHEMA(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + S(depth-1);
                ans = ans + C(depth-1);
                ans = ans + H(depth-1);
                ans = ans + E(depth-1);
                ans = ans + M(depth-1);
                ans = ans + A(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String SELECT(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + S(depth-1);
                ans = ans + E(depth-1);
                ans = ans + L(depth-1);
                ans = ans + E(depth-1);
                ans = ans + C(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String SET(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + S(depth-1);
                ans = ans + E(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String TABLE(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + T(depth-1);
                ans = ans + A(depth-1);
                ans = ans + B(depth-1);
                ans = ans + L(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String TEMPORARY(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

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
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String TEXT(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + T(depth-1);
                ans = ans + E(depth-1);
                ans = ans + X(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String TRUNCATE(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + T(depth-1);
                ans = ans + R(depth-1);
                ans = ans + U(depth-1);
                ans = ans + N(depth-1);
                ans = ans + C(depth-1);
                ans = ans + A(depth-1);
                ans = ans + T(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String UPDATE(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + U(depth-1);
                ans = ans + P(depth-1);
                ans = ans + D(depth-1);
                ans = ans + A(depth-1);
                ans = ans + T(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String USE(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + U(depth-1);
                ans = ans + S(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String VALUES(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + V(depth-1);
                ans = ans + A(depth-1);
                ans = ans + L(depth-1);
                ans = ans + U(depth-1);
                ans = ans + E(depth-1);
                ans = ans + S(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String VIEW(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + V(depth-1);
                ans = ans + I(depth-1);
                ans = ans + E(depth-1);
                ans = ans + W(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String WHERE(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + W(depth-1);
                ans = ans + H(depth-1);
                ans = ans + E(depth-1);
                ans = ans + R(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String STUB(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + S(depth-1);
                ans = ans + T(depth-1);
                ans = ans + U(depth-1);
                ans = ans + B(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String LB(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node215(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String RB(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node216(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String LT(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node217(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String GT(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node218(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String EQ(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node219(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String SC(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node220(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String US(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node221(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String DIGIT(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node222(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String SPACE(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node223(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String NL(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node224(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String A(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node225(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String B(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node226(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String C(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node227(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String D(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node228(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String E(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node229(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String F(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node230(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String G(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node231(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String H(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node232(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String I(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node233(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String J(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node234(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String K(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node235(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String L(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node236(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String M(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node237(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String N(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node238(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String O(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node239(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String P(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node240(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String Q(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node241(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String R(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node242(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String S(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node243(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String T(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node244(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String U(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node245(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String V(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node246(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String W(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node247(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String X(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node248(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String Y(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node249(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String Z(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node250(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String CH(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node251(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String DQ(int depth) throws Exception{
        if (false){
            this.restore_context();
        }
        else {
            this.push_context();
        }

        String ans="";
        ans = ans + Node252(depth-1);
        
        this.pop_context();
        return ans;
    }//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node93(int depth) throws Exception{

        String ans = null;
                ans = Node94(depth-1);
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node96(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node94(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (true){
                            set_static_variable("db", temp);
                        }
                        else if (false){
                            this.set_member_variable("db", temp);
                        }
                        else {
                            this.set_variable("db", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 5){
                    continue;
                }
                String temp = Node96(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (true){
                            set_static_variable("db", temp);
                        }
                        else if (false){
                            this.set_member_variable("db", temp);
                        }
                        else {
                            this.set_variable("db", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node94(int depth) throws Exception{

        String ans=null;
        if (true){
            if (true){
                ans = get_static_variable("db");
            }
            else if (false){
                ans = this.get_member_variable("db");
            }
            else {
                ans = this.get_variable("db");
            }
        }
        else {
            ans="";
            ans = ans + Node95(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node95(int depth){
        
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 5
    public String Node96(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + dbName(depth-1, null, true, null, null);
        
        }
        return ans;
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node97(int depth) throws Exception{

        String ans = null;
                ans = Node98(depth-1);
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node100(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node98(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (true){
                            set_static_variable("db", temp);
                        }
                        else if (false){
                            this.set_member_variable("db", temp);
                        }
                        else {
                            this.set_variable("db", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 5){
                    continue;
                }
                String temp = Node100(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (true){
                            set_static_variable("db", temp);
                        }
                        else if (false){
                            this.set_member_variable("db", temp);
                        }
                        else {
                            this.set_variable("db", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node98(int depth) throws Exception{

        String ans=null;
        if (true){
            if (true){
                ans = get_static_variable("db");
            }
            else if (false){
                ans = this.get_member_variable("db");
            }
            else {
                ans = this.get_variable("db");
            }
        }
        else {
            ans="";
            ans = ans + Node99(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node99(int depth){
        
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 5
    public String Node100(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + dbName(depth-1, null, true, null, null);
        
        }
        return ans;
    }
//ALTERNATION_NODE
//MIN_DEPTH = 5
    public String Node101(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 5){
            return Node102(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node102(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node103(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node102(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + DATABASE(depth-1);
        
        }
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node103(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + SCHEMA(depth-1);
        
        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 5
    public String Node104(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + ifNotExists(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node105(int depth) throws Exception{

        String ans = null;
                ans = Node106(depth-1);
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node108(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node106(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (true){
                            set_static_variable("db", temp);
                        }
                        else if (false){
                            this.set_member_variable("db", temp);
                        }
                        else {
                            this.set_variable("db", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 5){
                    continue;
                }
                String temp = Node108(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (true){
                            set_static_variable("db", temp);
                        }
                        else if (false){
                            this.set_member_variable("db", temp);
                        }
                        else {
                            this.set_variable("db", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node106(int depth) throws Exception{

        String ans=null;
        if (true){
            if (true){
                ans = get_static_variable("db");
            }
            else if (false){
                ans = this.get_member_variable("db");
            }
            else {
                ans = this.get_variable("db");
            }
        }
        else {
            ans="";
            ans = ans + Node107(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node107(int depth){
        
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 5
    public String Node108(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + dbName(depth-1, null, true, null, null);
        
        }
        return ans;
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node109(int depth) throws Exception{

        String ans = null;
                ans = Node110(depth-1);
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node112(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node110(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (true){
                            set_static_variable("db", temp);
                        }
                        else if (false){
                            this.set_member_variable("db", temp);
                        }
                        else {
                            this.set_variable("db", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 5){
                    continue;
                }
                String temp = Node112(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (true){
                            set_static_variable("db", temp);
                        }
                        else if (false){
                            this.set_member_variable("db", temp);
                        }
                        else {
                            this.set_variable("db", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node110(int depth) throws Exception{

        String ans=null;
        if (true){
            if (true){
                ans = get_static_variable("db");
            }
            else if (false){
                ans = this.get_member_variable("db");
            }
            else {
                ans = this.get_variable("db");
            }
        }
        else {
            ans="";
            ans = ans + Node111(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node111(int depth){
        
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 5
    public String Node112(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + dbName(depth-1, null, true, null, null);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node113(int depth){
        
        return "";
    }//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node114(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 10.0;
        if (depth < 3){
            return Node115(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 9.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node115(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 9.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node118(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node115(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + Node116(depth-1);
        ans = ans + Node117(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node116(int depth){

        return " "; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node117(int depth){
        
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node118(int depth) throws Exception{
        this.expected_error_buffer.add("Cannot create temporary table with partitions");

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + TEMPORARY(depth-1);
        ans = ans + Node119(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node119(int depth){
        
        return "";
    }//QUANTIFIER_NODE
//MIN_DEPTH = 5
    public String Node120(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + ifNotExists(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node121(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 10.0;
        if (depth < 3){
            return Node122(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 9.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node122(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 9.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node166(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node122(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + LB(depth-1);
        ans = ans + Node123(depth-1);
        ans = ans + Node127(depth-1);
        ans = ans + Node130(depth-1);
        ans = ans + RB(depth-1);
        ans = ans + Node136(depth-1);
        ans = ans + Node165(depth-1);
        
        }
        return ans;
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node123(int depth) throws Exception{

        String ans = null;
                ans = Node124(depth-1);
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node126(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node124(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (false){
                            set_static_variable("cn", temp);
                        }
                        else if (false){
                            this.set_member_variable("cn", temp);
                        }
                        else {
                            this.set_variable("cn", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 5){
                    continue;
                }
                String temp = Node126(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (false){
                            set_static_variable("cn", temp);
                        }
                        else if (false){
                            this.set_member_variable("cn", temp);
                        }
                        else {
                            this.set_variable("cn", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node124(int depth) throws Exception{

        String ans=null;
        if (true){
            if (false){
                ans = get_static_variable("cn");
            }
            else if (false){
                ans = this.get_member_variable("cn");
            }
            else {
                ans = this.get_variable("cn");
            }
        }
        else {
            ans="";
            ans = ans + Node125(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node125(int depth){
         
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 5
    public String Node126(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + columnName(depth-1, "a", true, null, null);
        
        }
        return ans;
    }
//ALTERNATION_NODE
//MIN_DEPTH = 5
    public String Node127(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 5){
            return Node128(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node128(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node129(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node128(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + INT(depth-1);
        
        }
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node129(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + TEXT(depth-1);
        
        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node130(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = true ? random(1, 6) : exp_decay_random(1, 6, 0.1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = true ? random(1, 6) : exp_decay_random(1, 6, 0.1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + Node131(depth-1);
            ans = ans + columnName(depth-1, "a", true, null, null);
            ans = ans + Node132(depth-1);
            ans = ans + Node135(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 1){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node131(int depth){

        return ","; 
    }
//ALTERNATION_NODE
//MIN_DEPTH = 5
    public String Node132(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 5){
            return Node133(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node133(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node134(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node133(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + INT(depth-1);
        
        }
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node134(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + TEXT(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node135(int depth){
          
        return "";
    }//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node136(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 9.0;
        if (depth < 3){
            return Node137(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 8.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node137(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 8.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node140(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node137(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + Node138(depth-1);
        ans = ans + Node139(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node138(int depth){

        return " "; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node139(int depth){
        
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node140(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + PARTITION(depth-1);
        ans = ans + BY(depth-1);
        ans = ans + Node141(depth-1);
        ans = ans + Node142(depth-1);
        
        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node141(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + LINEAR(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node142(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node143(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node143(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node150(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node143(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + Node144(depth-1);
        ans = ans + Node145(depth-1);
        ans = ans + Node149(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node144(int depth){

        return "HASH("; 
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node145(int depth) throws Exception{

        String ans = null;
                ans = Node146(depth-1);
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node148(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node146(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (false){
                            set_static_variable("cn", temp);
                        }
                        else if (false){
                            this.set_member_variable("cn", temp);
                        }
                        else {
                            this.set_variable("cn", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 5){
                    continue;
                }
                String temp = Node148(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (false){
                            set_static_variable("cn", temp);
                        }
                        else if (false){
                            this.set_member_variable("cn", temp);
                        }
                        else {
                            this.set_variable("cn", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node146(int depth) throws Exception{

        String ans=null;
        if (true){
            if (false){
                ans = get_static_variable("cn");
            }
            else if (false){
                ans = this.get_member_variable("cn");
            }
            else {
                ans = this.get_variable("cn");
            }
        }
        else {
            ans="";
            ans = ans + Node147(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node147(int depth){
         
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 5
    public String Node148(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + columnName(depth-1, "a", true, null, null);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node149(int depth){

        return ")"; 
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node150(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + Node151(depth-1);
        ans = ans + Node152(depth-1);
        ans = ans + Node159(depth-1);
        ans = ans + Node160(depth-1);
        ans = ans + Node164(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node151(int depth){

        return " KEY "; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node152(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + Node153(depth-1);
            ans = ans + Node154(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node153(int depth){

        return "ALGORITHM="; 
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node154(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node155(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node155(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node157(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node155(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + Node156(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node156(int depth){

        return "1"; 
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node157(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + Node158(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node158(int depth){

        return "2"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node159(int depth){

        return "("; 
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node160(int depth) throws Exception{

        String ans = null;
                ans = Node161(depth-1);
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node163(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node161(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (false){
                            set_static_variable("cn", temp);
                        }
                        else if (false){
                            this.set_member_variable("cn", temp);
                        }
                        else {
                            this.set_variable("cn", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 5){
                    continue;
                }
                String temp = Node163(depth-1);
                if (temp!=null && temp.length()>0){
                    if (true){
                        if (false){
                            set_static_variable("cn", temp);
                        }
                        else if (false){
                            this.set_member_variable("cn", temp);
                        }
                        else {
                            this.set_variable("cn", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node161(int depth) throws Exception{

        String ans=null;
        if (true){
            if (false){
                ans = get_static_variable("cn");
            }
            else if (false){
                ans = this.get_member_variable("cn");
            }
            else {
                ans = this.get_variable("cn");
            }
        }
        else {
            ans="";
            ans = ans + Node162(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node162(int depth){
         
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 5
    public String Node163(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + columnName(depth-1, "a", true, null, null);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node164(int depth){

        return ")"; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node165(int depth){
        
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node166(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + LIKE(depth-1);
        ans = ans + tableName(depth-1, "b", false, null, null);
        
        }
        return ans;
    }
//ALTERNATION_NODE
//MIN_DEPTH = 5
    public String Node167(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 5){
            return Node168(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node168(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node169(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node168(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + REPLACE(depth-1);
        
        }
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node169(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + INSERT(depth-1);
        ans = ans + Node170(depth-1);
        ans = ans + Node175(depth-1);
        
        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 6
    public String Node170(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + Node171(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//ALTERNATION_NODE
//MIN_DEPTH = 5
    public String Node171(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 3.0;
        if (depth < 5){
            return Node172(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node172(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node173(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node174(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node172(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + LOW_PRIORITY(depth-1);
        
        }
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node173(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + DELAYED(depth-1);
        
        }
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node174(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + HIGH_PRIORITY(depth-1);
        
        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node175(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + IGNORE(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node176(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + INTO(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node177(int depth){

        return "("; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node178(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = "a";
        if (rpid==null){
                count = false ? random(0, 5) : exp_decay_random(0, 5, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 5) : exp_decay_random(0, 5, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + Node179(depth-1);
            ans = ans + columnName(depth-1, "id1", false, "c", "t");
            ans = ans + Node180(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node179(int depth){

        return ","; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node180(int depth){
           
        return "";
    }//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node181(int depth){

        return ")"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node182(int depth){

        return "("; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node183(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = "a";
        if (rpid==null){
                count = false ? random(0, 5) : exp_decay_random(0, 5, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 5) : exp_decay_random(0, 5, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + Node184(depth-1);
            ans = ans + expr(depth-1, "c");
            ans = ans + Node185(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node184(int depth){

        return ","; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node185(int depth){
           
        return "";
    }//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node186(int depth){

        return ")"; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node187(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + LOW_PRIORITY(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node188(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + IGNORE(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node189(int depth){

        return "="; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node190(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 5) : exp_decay_random(0, 5, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 5) : exp_decay_random(0, 5, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + Node191(depth-1);
            ans = ans + columnName(depth-1, "id1", false, "c", "t");
            ans = ans + Node192(depth-1);
            ans = ans + expr(depth-1, "c");
            ans = ans + Node193(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node191(int depth){

        return ","; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node192(int depth){

        return "="; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node193(int depth){
         
        return "";
    }//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node194(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + WHERE(depth-1);
            ans = ans + Node195(depth-1);
            ans = ans + columnName(depth-1, null, false, "cc", "t");
            ans = ans + Node196(depth-1);
            ans = ans + expr(depth-1, "cc");

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node195(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 2) : exp_decay_random(0, 2, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + NOT(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node196(int depth){

        return "="; 
    }
//EXPR_CORE
//MIN_DEPTH = 3
    public String Node197(int depth, String type) throws Exception{

        boolean is_typeless = (type==null || type.equals(""));
        if (!is_typeless){
//EXPR_CORE_CALL_CHILDREN
            if ("INT".toLowerCase().equals(type.toLowerCase())){
                return Node198(depth-1);
            }//EXPR_CORE_CALL_CHILDREN
            if ("TEXT".toLowerCase().equals(type.toLowerCase())){
                return Node200(depth-1);
            }
        }
        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node198(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node198(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node200(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }

    }//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node198(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + INT_VAL(depth-1);
        ans = ans + Node199(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node199(int depth){
        
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node200(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + TEXT_VAL(depth-1);
        ans = ans + Node201(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node201(int depth){
        
        return "";
    }//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node202(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(0, 5) : exp_decay_random(0, 5, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(0, 5) : exp_decay_random(0, 5, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + Node203(depth-1);
            ans = ans + columnName(depth-1, "id1", false, "c", "t");
            ans = ans + Node204(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 0){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node203(int depth){

        return ","; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node204(int depth){
         
        return "";
    }//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node205(int depth){

        return "("; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node206(int depth){

        return "="; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node207(int depth){

        return ")"; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node208(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(1, 5) : exp_decay_random(1, 5, 0.5);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(1, 5) : exp_decay_random(1, 5, 0.5);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + DIGIT(depth-1);
            ans = ans + Node209(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 1){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node209(int depth){
         
        return "";
    }//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node210(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(1, 100) : exp_decay_random(1, 100, 0.1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(1, 100) : exp_decay_random(1, 100, 0.1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + Node211(depth-1);
            ans = ans + Node214(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 1){
                                this.rpid_cache.put(rpid, Integer.valueOf(i));
                                return prev_ans;
                        }
                        else {
                                throw e;
                        }
                }
        }
        return ans;
    }
//ALTERNATION_NODE
//MIN_DEPTH = 4
    public String Node211(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 4){
            return Node212(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 3){
                    continue;
                }
                String temp = Node212(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 3){
                    continue;
                }
                String temp = Node213(depth-1);
                if (temp!=null && temp.length()>0){
                    if (false){
                        if (false){
                            set_static_variable("", temp);
                        }
                        else if (false){
                            this.set_member_variable("", temp);
                        }
                        else {
                            this.set_variable("", temp);
                        }
                    }
                    return temp;
                }
            }
            r = r - 1.0;

        }
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 3
    public String Node212(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + CH(depth-1);
        
        }
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 3
    public String Node213(int depth) throws Exception{

        String ans=null;
        if (false){
            if (false){
                ans = get_static_variable("");
            }
            else if (false){
                ans = this.get_member_variable("");
            }
            else {
                ans = this.get_variable("");
            }
        }
        else {
            ans="";
            ans = ans + DIGIT(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node214(int depth){
         
        return "";
    }//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node215(int depth){

        return "("; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node216(int depth){

        return ")"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node217(int depth){

        return "<"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node218(int depth){

        return ">"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node219(int depth){

        return "="; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node220(int depth){

        return ";"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node221(int depth){

        return "_"; 
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node222(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(48);
        indices.add(58);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node223(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(32);
        indices.add(33);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node224(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(10);
        indices.add(11);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node225(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(65);
        indices.add(66);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node226(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(66);
        indices.add(67);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node227(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(67);
        indices.add(68);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node228(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(68);
        indices.add(69);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node229(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(69);
        indices.add(70);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node230(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(70);
        indices.add(71);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node231(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(71);
        indices.add(72);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node232(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(72);
        indices.add(73);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node233(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(73);
        indices.add(74);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node234(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(74);
        indices.add(75);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node235(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(75);
        indices.add(76);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node236(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(76);
        indices.add(77);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node237(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(77);
        indices.add(78);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node238(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(78);
        indices.add(79);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node239(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(79);
        indices.add(80);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node240(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(80);
        indices.add(81);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node241(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(81);
        indices.add(82);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node242(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(82);
        indices.add(83);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node243(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(83);
        indices.add(84);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node244(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(84);
        indices.add(85);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node245(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(85);
        indices.add(86);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node246(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(86);
        indices.add(87);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node247(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(87);
        indices.add(88);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node248(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(88);
        indices.add(89);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node249(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(89);
        indices.add(90);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node250(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(90);
        indices.add(91);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node251(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(65);
        indices.add(91);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node252(int depth) throws Exception{

        List<Integer> indices = new ArrayList<>();
        indices.add(34);
        indices.add(35);
        
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            this.log("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }

}

