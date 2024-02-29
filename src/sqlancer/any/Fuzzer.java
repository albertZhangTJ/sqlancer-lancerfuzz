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
    private HashMap<String, List<List<String>>> identifier_cache;
    private HashMap<String, List<List<String>>> identifier_cache_snapshot;

    private List<HashMap<String, Integer>> rpid_stack;
    private HashMap<String, Integer> rpid_cache;
    private HashMap<String, Integer> rpid_cache_snapshot;

    private List<HashMap<String, List<String>>> used_identifier_stack;
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


    public Fuzzer(SQLConnection con, int fuzzing_depth, int loop_threshold){
        this.con = con;
        this.expected_error_buffer = new ArrayList<>();
        this.rules_call_series = new ArrayList<>();
        this.rules_call_index = 0;
        this.fuzzing_depth = fuzzing_depth;
        this.loop_threshold = loop_threshold;
        this.identifier_cache_stack = new ArrayList<>();
        this.identifier_cache = new HashMap<>();
        this.used_identifier_stack = new ArrayList<>();
        this.used_identifier = new HashMap<>();
        this.rpid_stack = new ArrayList<>();
        this.rpid_cache = new HashMap<>();
        this.member_var_ref = new HashMap<>();
        this.var_ref = new HashMap<>();
        this.var_ref_stack = new ArrayList<>();
    }



    public static void init(){
        static_var_ref = new HashMap<>();
        dbms_options.put("host","localhost");
        dbms_options.put("port","3306");
        dbms_options.put("username","new_user");
        dbms_options.put("password","password");
        dbms_options.put("db_prefix","sqlite");
        dbms_options.put("conn_str","jdbc:sqlite:");
        dbms_options.put("jdbc_class","org.sqlite.JDBC");

    }


    private String translate_rule_call(String rule_name) throws Exception{
        //System.out.println("Translating: "+rule_name);
//CALL_RULE_NAME
        if (rule_name.equals("create_table_stmt")){
            return create_table_stmt(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("create_view_stmt")){
            return create_view_stmt(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("insert_stmt")){
            return insert_stmt(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("update_stmt")){
            return update_stmt(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("vacuum_stmt")){
            return vacuum_stmt(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("reindex_stmt")){
            return reindex_stmt(this.fuzzing_depth);
        }//CALL_RULE_NAME
        if (rule_name.equals("drop_table_stmt")){
            return drop_table_stmt(this.fuzzing_depth);
        }
        System.out.println("ERROR: rule name "+rule_name+" has no corresponding function call generated");
        System.exit(1);
        return null;
    }

    public String get_next_statement() throws Exception{
        if (this.rules_call_index<this.rules_call_series.size()){
            this.expected_error_buffer = new ArrayList<>();
            String res = this.translate_rule_call(this.rules_call_series.get(this.rules_call_index));
            this.rules_call_index++;
            return res;
        }
        return ERROR_FLAG;
    }

    public String regenerate_last_statement() throws Exception{
        this.rules_call_index--;
        this.expected_error_buffer = new ArrayList<>();
        String res = this.translate_rule_call(this.rules_call_series.get(this.rules_call_index));
        this.rules_call_index++;
        return res;
    }

    public static String get_dbms_option(String key){
        return dbms_options.get(key);
    }

    private ResultSet query_dbms(String query) throws Exception{
        try {
            ResultSet ans = this.con.createStatement().executeQuery(query);
            return ans;
        }
        catch (Exception e){
            System.out.println("Error encountered when executing query: "+query);
            throw e;
        }
    }

    public void generate(){
        int count = -1;
//STAGE
//create

        count = random(10, 30+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*1.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<1.0){
                this.rules_call_series.add("create_table_stmt");
            }


        }
//STAGE
//create_view

        count = random(3, 5+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*1.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<1.0){
                this.rules_call_series.add("create_view_stmt");
            }


        }
//STAGE
//insert

        count = random(50, 70+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*1.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<1.0){
                this.rules_call_series.add("insert_stmt");
            }


        }
//STAGE
//update

        count = random(15, 50+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*1.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<1.0){
                this.rules_call_series.add("update_stmt");
            }


        }
//STAGE
//mix

        count = random(100, 500+1);
        for (int i=0; i<count; i++){
            double index = Math.random()*11.0;
//STAGE_SERIALIZE_RULE
            if (index>=0.0 && index<1.0){
                this.rules_call_series.add("create_table_stmt");
            }

//STAGE_SERIALIZE_RULE
            if (index>=1.0 && index<4.0){
                this.rules_call_series.add("insert_stmt");
            }

//STAGE_SERIALIZE_RULE
            if (index>=4.0 && index<7.0){
                this.rules_call_series.add("update_stmt");
            }

//STAGE_SERIALIZE_RULE
            if (index>=7.0 && index<8.0){
                this.rules_call_series.add("vacuum_stmt");
            }

//STAGE_SERIALIZE_RULE
            if (index>=8.0 && index<9.0){
                this.rules_call_series.add("reindex_stmt");
            }

//STAGE_SERIALIZE_RULE
            if (index>=9.0 && index<10.0){
                this.rules_call_series.add("drop_table_stmt");
            }

//STAGE_SERIALIZE_RULE
            if (index>=10.0 && index<11.0){
                this.rules_call_series.add("create_view_stmt");
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

    private void set_parent(String sub, List<String> hierarchy){
        List<List<String>> id_val = this.identifier_cache.get(sub);
        if (id_val==null){
            List<List<String>> id_val_n = new ArrayList<>();
            id_val_n.add(hierarchy);
            this.identifier_cache.put(sub, id_val_n);
        }
        else {
            id_val.add(hierarchy);
        }
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

    //it is the callee's responsibility to create new context for itself at entry
    //and restore context for caller when exiting
    private void push_context(){
        this.push_identifier_cache();
        this.push_rpid();
        this.push_used_identifier();
    }
    private void pop_context(){
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
        this.identifier_cache = this.identifier_cache_snapshot;
        this.used_identifier = this.used_identifier_snapshot;
        this.rpid_cache = this.rpid_cache_snapshot;
        this.expected_error_buffer = this.expected_error_buffer_snapshot;
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
        this.push_context();

        String ans="";

        this.pop_context();
        return ans;
    }//UNPARSER_RULE_NODE
//MIN_DEPTH = 2
    public String create_table_stmt(int depth) throws Exception{

        this.push_context();

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = K_CREATE(depth-1);
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
                String tmp = Node75(depth-1);
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
                String tmp = K_TABLE(depth-1);
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
                String tmp = Node79(depth-1);
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
                String tmp = table_name(depth-1, null, true, null, null);
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
                String tmp = Node80(depth-1);
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
                String tmp = Node81(depth-1);
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
                String tmp = Node86(depth-1);
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
                String tmp = Node89(depth-1);
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
                String tmp = Node108(depth-1);
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
                    System.out.println("ERROR: create_table_stmt cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    System.exit(1);
                }
            }
        }
        catch (Exception e){
            System.out.println(cache);
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
    public String update_stmt(int depth) throws Exception{
        this.expected_error_buffer.add("SQLITE_CONSTRAINT");
        this.expected_error_buffer.add("PRIMARY");
        this.expected_error_buffer.add("UNIQUE");

        this.push_context();

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = K_UPDATE(depth-1);
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
                String tmp = Node110(depth-1);
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
                String tmp = table_name(depth-1, null, false, "t", null);
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
                String tmp = K_SET(depth-1);
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
                String tmp = column_name(depth-1, "s", false, null, "t");
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
                String tmp = Node117(depth-1);
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
                String tmp = expr(depth-1);
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
                String tmp = Node118(depth-1);
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
                String tmp = Node119(depth-1);
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
                    System.out.println("ERROR: update_stmt cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    System.exit(1);
                }
            }
        }
        catch (Exception e){
            System.out.println(cache);
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
    public String insert_stmt(int depth) throws Exception{
        this.expected_error_buffer.add("Type mismatch");
        this.expected_error_buffer.add("PRIMARY");
        this.expected_error_buffer.add("UNIQUE");

        this.push_context();

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node120(depth-1);
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
                String tmp = Node121(depth-1);
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
                String tmp = K_INTO(depth-1);
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
                String tmp = table_name(depth-1, null, false, "t", null);
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
                String tmp = Node127(depth-1);
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
                String tmp = column_name(depth-1, "id", false, null, "t");
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
                String tmp = Node128(depth-1);
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
                String tmp = Node131(depth-1);
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
                String tmp = K_VALUES(depth-1);
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
                String tmp = Node132(depth-1);
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
                String tmp = expr(depth-1);
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
                String tmp = Node133(depth-1);
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
                String tmp = Node136(depth-1);
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
                    System.out.println("ERROR: insert_stmt cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    System.exit(1);
                }
            }
        }
        catch (Exception e){
            System.out.println(cache);
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
    public String vacuum_stmt(int depth) throws Exception{

        this.push_context();

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = K_VACUUM(depth-1);
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
                String tmp = Node137(depth-1);
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
                    System.out.println("ERROR: vacuum_stmt cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    System.exit(1);
                }
            }
        }
        catch (Exception e){
            System.out.println(cache);
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
    public String reindex_stmt(int depth) throws Exception{

        this.push_context();

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = K_REINDEX(depth-1);
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
                String tmp = Node138(depth-1);
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
                    System.out.println("ERROR: reindex_stmt cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    System.exit(1);
                }
            }
        }
        catch (Exception e){
            System.out.println(cache);
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
    public String drop_table_stmt(int depth) throws Exception{
        this.expected_error_buffer.add("no such table");

        this.push_context();

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node139(depth-1);
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
                String tmp = K_DROP(depth-1);
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
                String tmp = K_TABLE(depth-1);
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
                String tmp = Node140(depth-1);
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
                String tmp = table_name(depth-1, null, false, null, null);
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
                String tmp = Node141(depth-1);
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
                    System.out.println("ERROR: drop_table_stmt cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    System.exit(1);
                }
            }
        }
        catch (Exception e){
            System.out.println(cache);
            throw e;
        }
        String ans = "";
        for (String s: cache){
            ans = ans + s;
        }
        
        this.pop_context();
        return ans;
    }//SCHEMA_NODE
//MIN_DEPTH = 1
//IT IS THE TESTER'S RESPONSIBILITY TO ENSURE THE PROPER PARAMETERS ARE SPECIFIED IN THE GRAMMAR FILE
    private int table_name_count = 0;
    private String table_name(int depth, String iid, boolean is_new, String sub, String sup) throws Exception{

        String ans = "";
        List<String> parent_name = null;
        if (is_new){
            table_name_count++;
            ans = "table_name"+table_name_count;
        }
        else {
            if (sup!=null){
                parent_name = this.get_parent_hierarchy(sup);
                if (parent_name==null || parent_name.size()==0){
                    return ERROR_FLAG;
                }
            }
            String query = "SELECT name FROM sqlite_master WHERE name NOT LIKE '%sqlite%' AND TYPE='table' UNION SELECT name FROM sqlite_temp_master WHERE type='table' AND name NOT LIKE '%sqlite%';";
            ResultSet rs = this.query_dbms(query);
            List<String> values = new ArrayList<>();
            int cnt=0;
            while (rs.next()){
                cnt++;
                //System.out.println(rs.getString("name"));
                if (iid==null || this.is_identifier_available(iid, rs.getString("name") )){
                    //System.out.println("Added");
                    values.add(rs.getString("name"));
                }
            }
            if (values.size()==0){
                System.out.println("Queried target DBMS with: "+query);
                System.out.println("ResultSet has length: "+cnt);
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
    private int view_name_count = 0;
    private String view_name(int depth, String iid, boolean is_new, String sub, String sup) throws Exception{

        String ans = "";
        List<String> parent_name = null;
        if (is_new){
            view_name_count++;
            ans = "view_name"+view_name_count;
        }
        else {
            if (sup!=null){
                parent_name = this.get_parent_hierarchy(sup);
                if (parent_name==null || parent_name.size()==0){
                    return ERROR_FLAG;
                }
            }
            String query = "SELECT name FROM sqlite_master WHERE name NOT LIKE '%sqlite%' AND TYPE='view' UNION SELECT name FROM sqlite_temp_master WHERE type='view' AND name NOT LIKE '%sqlite%';";
            ResultSet rs = this.query_dbms(query);
            List<String> values = new ArrayList<>();
            int cnt=0;
            while (rs.next()){
                cnt++;
                //System.out.println(rs.getString("name"));
                if (iid==null || this.is_identifier_available(iid, rs.getString("name") )){
                    //System.out.println("Added");
                    values.add(rs.getString("name"));
                }
            }
            if (values.size()==0){
                System.out.println("Queried target DBMS with: "+query);
                System.out.println("ResultSet has length: "+cnt);
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
    private int column_name_count = 0;
    private String column_name(int depth, String iid, boolean is_new, String sub, String sup) throws Exception{

        String ans = "";
        List<String> parent_name = null;
        if (is_new){
            column_name_count++;
            ans = "column_name"+column_name_count;
        }
        else {
            if (sup!=null){
                parent_name = this.get_parent_hierarchy(sup);
                if (parent_name==null || parent_name.size()==0){
                    return ERROR_FLAG;
                }
            }
            String query = "SELECT name FROM pragma_table_info('" + parent_name.get(0) + "');";
            ResultSet rs = this.query_dbms(query);
            List<String> values = new ArrayList<>();
            int cnt=0;
            while (rs.next()){
                cnt++;
                //System.out.println(rs.getString("name"));
                if (iid==null || this.is_identifier_available(iid, rs.getString("name") )){
                    //System.out.println("Added");
                    values.add(rs.getString("name"));
                }
            }
            if (values.size()==0){
                System.out.println("Queried target DBMS with: "+query);
                System.out.println("ResultSet has length: "+cnt);
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
//MIN_DEPTH = 2
    public String create_view_stmt(int depth) throws Exception{

        this.push_context();

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = K_CREATE(depth-1);
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
                String tmp = Node142(depth-1);
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
                String tmp = K_VIEW(depth-1);
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
                String tmp = Node146(depth-1);
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
                String tmp = view_name(depth-1, null, true, null, null);
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
                String tmp = K_AS(depth-1);
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
                String tmp = select_stmt(depth-1);
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
                String tmp = Node147(depth-1);
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
                    System.out.println("ERROR: create_view_stmt cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    System.exit(1);
                }
            }
        }
        catch (Exception e){
            System.out.println(cache);
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
    public String select_stmt(int depth) throws Exception{

        this.push_context();

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = K_SELECT(depth-1);
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
                String tmp = Node148(depth-1);
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
                String tmp = column_name(depth-1, "id", false, null, "t");
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
                String tmp = Node149(depth-1);
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
                String tmp = Node152(depth-1);
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
                String tmp = K_FROM(depth-1);
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
                String tmp = Node153(depth-1);
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
                    System.out.println("ERROR: select_stmt cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    System.exit(1);
                }
            }
        }
        catch (Exception e){
            System.out.println(cache);
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
    public String expr(int depth) throws Exception{

        this.push_context();

        List<String> cache = new ArrayList<>();
        boolean is_completed = false;
        int counter = 0;
        try{
            while (!is_completed){
//UNPARSER_CALL_CHILDREN
            if (cache.size()<=0 || cache.get(0).indexOf(ERROR_FLAG)!=-1){
                this.snapshot_context();
                int eeb_size = this.expected_error_buffer.size();
                String tmp = Node158(depth-1);
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
                    System.out.println("ERROR: expr cannot deliver a valid result after "+this.loop_threshold+" passes. Aborting");
                    System.exit(1);
                }
            }
        }
        catch (Exception e){
            System.out.println(cache);
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
    public String K_ABORT(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + A(depth-1);
                ans = ans + B(depth-1);
                ans = ans + O(depth-1);
                ans = ans + R(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_ALL(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + A(depth-1);
                ans = ans + L(depth-1);
                ans = ans + L(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_AS(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + A(depth-1);
                ans = ans + S(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_CREATE(int depth) throws Exception{
        this.push_context();

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
    public String K_DEFAULT(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + D(depth-1);
                ans = ans + E(depth-1);
                ans = ans + F(depth-1);
                ans = ans + A(depth-1);
                ans = ans + U(depth-1);
                ans = ans + L(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_DISTINCT(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + D(depth-1);
                ans = ans + I(depth-1);
                ans = ans + S(depth-1);
                ans = ans + T(depth-1);
                ans = ans + I(depth-1);
                ans = ans + N(depth-1);
                ans = ans + C(depth-1);
                ans = ans + T(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_DROP(int depth) throws Exception{
        this.push_context();

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
    public String K_EXISTS(int depth) throws Exception{
        this.push_context();

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
    public String K_FAIL(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + F(depth-1);
                ans = ans + A(depth-1);
                ans = ans + I(depth-1);
                ans = ans + L(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_FROM(int depth) throws Exception{
        this.push_context();

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
    public String K_IF(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + I(depth-1);
                ans = ans + F(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_IGNORE(int depth) throws Exception{
        this.push_context();

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
    public String K_INSERT(int depth) throws Exception{
        this.push_context();

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
    public String K_INTO(int depth) throws Exception{
        this.push_context();

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
    public String K_KEY(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + K(depth-1);
                ans = ans + E(depth-1);
                ans = ans + Y(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_NOT(int depth) throws Exception{
        this.push_context();

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
    public String K_OR(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + O(depth-1);
                ans = ans + R(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_PRIMARY(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + P(depth-1);
                ans = ans + R(depth-1);
                ans = ans + I(depth-1);
                ans = ans + M(depth-1);
                ans = ans + A(depth-1);
                ans = ans + R(depth-1);
                ans = ans + Y(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_REINDEX(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + R(depth-1);
                ans = ans + E(depth-1);
                ans = ans + I(depth-1);
                ans = ans + N(depth-1);
                ans = ans + D(depth-1);
                ans = ans + E(depth-1);
                ans = ans + X(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_REPLACE(int depth) throws Exception{
        this.push_context();

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
    public String K_ROLLBACK(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + R(depth-1);
                ans = ans + O(depth-1);
                ans = ans + L(depth-1);
                ans = ans + L(depth-1);
                ans = ans + B(depth-1);
                ans = ans + A(depth-1);
                ans = ans + C(depth-1);
                ans = ans + K(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_SELECT(int depth) throws Exception{
        this.push_context();

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
    public String K_SET(int depth) throws Exception{
        this.push_context();

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
    public String K_TABLE(int depth) throws Exception{
        this.push_context();

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
    public String K_TEMP(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + T(depth-1);
                ans = ans + E(depth-1);
                ans = ans + M(depth-1);
                ans = ans + P(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_TEMPORARY(int depth) throws Exception{
        this.push_context();

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
    public String K_UNIQUE(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + U(depth-1);
                ans = ans + N(depth-1);
                ans = ans + I(depth-1);
                ans = ans + Q(depth-1);
                ans = ans + U(depth-1);
                ans = ans + E(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_UPDATE(int depth) throws Exception{
        this.push_context();

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
    public String K_VACUUM(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + SPACE(depth-1);
                ans = ans + V(depth-1);
                ans = ans + A(depth-1);
                ans = ans + C(depth-1);
                ans = ans + U(depth-1);
                ans = ans + U(depth-1);
                ans = ans + M(depth-1);
                ans = ans + SPACE(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 3
    public String K_VALUES(int depth) throws Exception{
        this.push_context();

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
    public String K_VIEW(int depth) throws Exception{
        this.push_context();

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
    public String K_WHERE(int depth) throws Exception{
        this.push_context();

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
//MIN_DEPTH = 2
    public String DIGIT(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node160(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String SPACE(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node161(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String NL(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node162(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String A(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node163(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String B(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node164(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String C(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node165(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String D(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node166(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String E(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node167(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String F(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node168(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String G(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node169(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String H(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node170(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String I(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node171(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String J(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node172(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String K(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node173(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String L(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node174(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String M(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node175(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String N(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node176(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String O(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node177(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String P(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node178(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String Q(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node179(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String R(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node180(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String S(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node181(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String T(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node182(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String U(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node183(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String V(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node184(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String W(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node185(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String X(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node186(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String Y(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node187(depth-1);
        
        this.pop_context();
        return ans;
    }//UNLEXER_RULE_NODE
//MIN_DEPTH = 2
    public String Z(int depth) throws Exception{
        this.push_context();

        String ans="";
        ans = ans + Node188(depth-1);
        
        this.pop_context();
        return ans;
    }//QUANTIFIER_NODE
//MIN_DEPTH = 6
    public String Node75(int depth) throws Exception{

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
            ans = ans + Node76(depth-1);

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
    public String Node76(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 5){
            return Node77(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node77(depth-1);
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
                String temp = Node78(depth-1);
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
    public String Node77(int depth) throws Exception{

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
            ans = ans + K_TEMP(depth-1);
        
        }
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node78(int depth) throws Exception{

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
            ans = ans + K_TEMPORARY(depth-1);
        
        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node79(int depth) throws Exception{

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
            ans = ans + K_IF(depth-1);
            ans = ans + K_NOT(depth-1);
            ans = ans + K_EXISTS(depth-1);

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
    public String Node80(int depth){

        return " ("; 
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node81(int depth) throws Exception{

        String ans = null;
                ans = Node82(depth-1);
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node85(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node82(depth-1);
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
                String temp = Node85(depth-1);
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
    public String Node82(int depth) throws Exception{

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
            ans = ans + Node83(depth-1);
        ans = ans + Node84(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node83(int depth){

        return " "; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node84(int depth){
        
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 5
    public String Node85(int depth) throws Exception{

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
            ans = ans + column_name(depth-1, null, true, null, null);
        
        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node86(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(5, 8) : exp_decay_random(5, 8, 0.1);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(5, 8) : exp_decay_random(5, 8, 0.1);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + Node87(depth-1);
            ans = ans + column_name(depth-1, null, true, null, null);
            ans = ans + Node88(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 5){
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
    public String Node87(int depth){

        return ", "; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node88(int depth){
          
        return "";
    }//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node89(int depth) throws Exception{

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
            ans = ans + Node90(depth-1);
            ans = ans + Node91(depth-1);

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
    public String Node90(int depth){

        return ", "; 
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node91(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node92(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node92(depth-1);
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
                String temp = Node100(depth-1);
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
    public String Node92(int depth) throws Exception{

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
            ans = ans + Node93(depth-1);
        ans = ans + Node94(depth-1);
        ans = ans + Node99(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node93(int depth){

        return "PRIMARY KEY("; 
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node94(int depth) throws Exception{

        String ans = null;
                ans = Node95(depth-1);
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node98(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node95(depth-1);
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
                String temp = Node98(depth-1);
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
    public String Node95(int depth) throws Exception{

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
            ans = ans + Node96(depth-1);
        ans = ans + Node97(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node96(int depth){

        return " "; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node97(int depth){
        
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 5
    public String Node98(int depth) throws Exception{

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
            ans = ans + column_name(depth-1, null, true, null, null);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node99(int depth){

        return ")"; 
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
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
            ans = ans + Node101(depth-1);
        ans = ans + Node102(depth-1);
        ans = ans + Node107(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node101(int depth){

        return "UNIQUE("; 
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node102(int depth) throws Exception{

        String ans = null;
                ans = Node103(depth-1);
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 3){
            return Node106(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node103(depth-1);
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
                String temp = Node106(depth-1);
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
    public String Node103(int depth) throws Exception{

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
            ans = ans + Node104(depth-1);
        ans = ans + Node105(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node104(int depth){

        return " "; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node105(int depth){
        
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 5
    public String Node106(int depth) throws Exception{

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
            ans = ans + column_name(depth-1, null, true, null, null);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node107(int depth){

        return ")"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node108(int depth){

        return ");"; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node109(int depth){
           
        return "";
    }//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node110(int depth) throws Exception{

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
            ans = ans + Node111(depth-1);

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
    public String Node111(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 3.1;
        if (depth < 3){
            return Node114(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node112(depth-1);
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
                String temp = Node113(depth-1);
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
            if (r < 0.1){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node114(depth-1);
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
            r = r - 0.1;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node116(depth-1);
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
            ans = ans + K_OR(depth-1);
        ans = ans + K_ROLLBACK(depth-1);
        
        }
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node113(int depth) throws Exception{

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
            ans = ans + K_OR(depth-1);
        ans = ans + K_ABORT(depth-1);
        
        }
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node114(int depth) throws Exception{

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
            ans = ans + K_OR(depth-1);
        ans = ans + K_FAIL(depth-1);
        ans = ans + Node115(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node115(int depth){
          
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node116(int depth) throws Exception{

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
            ans = ans + K_OR(depth-1);
        ans = ans + K_IGNORE(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node117(int depth){

        return "="; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node118(int depth) throws Exception{

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
            ans = ans + K_WHERE(depth-1);
            ans = ans + expr(depth-1);

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
    public String Node119(int depth){

        return ";"; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node120(int depth){
           
        return "";
    }//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node121(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 16.0;
        if (depth < 3){
            return Node122(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 10.0){
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
            r = r - 10.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 5.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node124(depth-1);
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
            r = r - 5.0;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node126(depth-1);
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
            ans = ans + K_INSERT(depth-1);
        ans = ans + Node123(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node123(int depth){
          
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node124(int depth) throws Exception{

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
            ans = ans + K_REPLACE(depth-1);
        ans = ans + Node125(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node125(int depth){
          
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 4
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
            ans = ans + K_INSERT(depth-1);
        ans = ans + K_OR(depth-1);
        ans = ans + K_IGNORE(depth-1);
        
        }
        return ans;
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node127(int depth){

        return "("; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node128(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = "s";
        if (rpid==null){
                count = true ? random(1, 3) : exp_decay_random(1, 3, 0.9);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = true ? random(1, 3) : exp_decay_random(1, 3, 0.9);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + Node129(depth-1);
            ans = ans + column_name(depth-1, "id", false, null, "t");
            ans = ans + Node130(depth-1);

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
    public String Node129(int depth){

        return ","; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node130(int depth){
         
        return "";
    }//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node131(int depth){

        return ")"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node132(int depth){

        return "("; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node133(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = "s";
        if (rpid==null){
                count = false ? random(2, 4) : exp_decay_random(2, 4, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(2, 4) : exp_decay_random(2, 4, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + Node134(depth-1);
            ans = ans + expr(depth-1);
            ans = ans + Node135(depth-1);

                }
                catch (Exception e){
                        //if we have already generated more than minimum quantity of sub-childs
                        //and we don't have rpid or that rpid is determined by us
                        //change the rpid to amount generated
                        //and return what we got
                        if (is_det && e.toString().contains("quantifier") && i >= 2){
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
    public String Node134(int depth){

        return ","; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node135(int depth){
         
        return "";
    }//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node136(int depth){

        return ");"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node137(int depth){

        return ";"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node138(int depth){

        return ";"; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node139(int depth){
        
        return "";
    }//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node140(int depth) throws Exception{

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
            ans = ans + K_IF(depth-1);
            ans = ans + K_EXISTS(depth-1);

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
    public String Node141(int depth){

        return ";"; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 6
    public String Node142(int depth) throws Exception{

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
            ans = ans + Node143(depth-1);

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
    public String Node143(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 2.0;
        if (depth < 5){
            return Node144(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 1.0){
                if (depth-1 < 4){
                    continue;
                }
                String temp = Node144(depth-1);
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
                String temp = Node145(depth-1);
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
    public String Node144(int depth) throws Exception{

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
            ans = ans + K_TEMP(depth-1);
        
        }
        return ans;
    }
//ALTERNATIVE_NODE
//MIN_DEPTH = 4
    public String Node145(int depth) throws Exception{

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
            ans = ans + K_TEMPORARY(depth-1);
        
        }
        return ans;
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 4
    public String Node146(int depth) throws Exception{

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
            ans = ans + K_IF(depth-1);
            ans = ans + K_NOT(depth-1);
            ans = ans + K_EXISTS(depth-1);

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
    public String Node147(int depth){

        return ";"; 
    }
//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node148(int depth){

        return "("; 
    }
//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node149(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(1, 4) : exp_decay_random(1, 4, 0.75);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(1, 4) : exp_decay_random(1, 4, 0.75);
                this.rpid_cache.put(rpid, Integer.valueOf(count));
        }
        else {
                count = this.rpid_cache.get(rpid).intValue();
                is_det = false;
        }
        for (int i=0; i<count; i++){
                String prev_ans = ans;
                try {
            ans = ans + Node150(depth-1);
            ans = ans + column_name(depth-1, "id", false, null, "t");
            ans = ans + Node151(depth-1);

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
    public String Node150(int depth){

        return ", "; 
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node151(int depth){
          
        return "";
    }//LITERAL_NODE
//MIN_DEPTH = 1
    public String Node152(int depth){

        return ")"; 
    }
//ALTERNATION_NODE
//MIN_DEPTH = 3
    public String Node153(int depth) throws Exception{

        String ans = null;
        
        if (ans!=null){
            return ans;
        }
        double total_weight = 10.0;
        if (depth < 3){
            return Node154(depth-1);
        }
        while (true){
            double r = Math.random()*total_weight;
//ALTERNATION_NODE_SUB_OPTION
            if (r < 9.0){
                if (depth-1 < 2){
                    continue;
                }
                String temp = Node154(depth-1);
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
                String temp = Node156(depth-1);
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
    public String Node154(int depth) throws Exception{

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
            ans = ans + table_name(depth-1, null, false, "t", null);
        ans = ans + Node155(depth-1);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node155(int depth){
        
        return "";
    }//ALTERNATIVE_NODE
//MIN_DEPTH = 2
    public String Node156(int depth) throws Exception{
        this.expected_error_buffer.add("no such table");

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
            ans = ans + Node157(depth-1);
        ans = ans + view_name(depth-1, null, false, "t", null);
        
        }
        return ans;
    }
//ACTION_NODE
//MIN_DEPTH = 1
    public String Node157(int depth){
          
        return "";
    }//QUANTIFIER_NODE
//MIN_DEPTH = 2
    public String Node158(int depth) throws Exception{

        String ans = "";
        int count = -1;
        boolean is_det = true;
        String rpid = null;
        if (rpid==null){
                count = false ? random(1, 6) : exp_decay_random(1, 6, 0.3);
        }
        else if (this.rpid_cache.get(rpid)==null){
                count = false ? random(1, 6) : exp_decay_random(1, 6, 0.3);
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
            ans = ans + Node159(depth-1);

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
    public String Node159(int depth){
          
        return "";
    }//CHARSET_NODE
//MIN_DEPTH = 1
    public String Node160(int depth) throws Exception{

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
    public String Node161(int depth) throws Exception{

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
    public String Node162(int depth) throws Exception{

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
    public String Node163(int depth) throws Exception{

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
    public String Node164(int depth) throws Exception{

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
    public String Node165(int depth) throws Exception{

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
    public String Node166(int depth) throws Exception{

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
    public String Node167(int depth) throws Exception{

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
    public String Node168(int depth) throws Exception{

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
    public String Node169(int depth) throws Exception{

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
    public String Node170(int depth) throws Exception{

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
    public String Node171(int depth) throws Exception{

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
    public String Node172(int depth) throws Exception{

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
    public String Node173(int depth) throws Exception{

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
    public String Node174(int depth) throws Exception{

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
    public String Node175(int depth) throws Exception{

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
    public String Node176(int depth) throws Exception{

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
    public String Node177(int depth) throws Exception{

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
    public String Node178(int depth) throws Exception{

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
    public String Node179(int depth) throws Exception{

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
    public String Node180(int depth) throws Exception{

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
    public String Node181(int depth) throws Exception{

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
    public String Node182(int depth) throws Exception{

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
    public String Node183(int depth) throws Exception{

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
    public String Node184(int depth) throws Exception{

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
    public String Node185(int depth) throws Exception{

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
    public String Node186(int depth) throws Exception{

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
    public String Node187(int depth) throws Exception{

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
    public String Node188(int depth) throws Exception{

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

