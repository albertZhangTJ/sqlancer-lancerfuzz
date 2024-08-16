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
public class <graph_name/>Fuzzer {
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

    public static class UnavailableException extends Exception {
        public boolean is_undefined; //the variable/attr requested does not exist
        public boolean is_uninitialized; //the variable/attr requests exists but has no value associated
        public boolean is_out; //the variable/attr requested exists but is insufficient in numbers (no more unique ones exists)
        public UnavailableException(String message, boolean is_undefined, boolean is_uninitialized, boolean is_out){
            super(message);
            this.is_undefined = is_undefined;
            this.is_uninitialized = is_uninitialized;
            this.is_out = is_out;
        }
    }

    public static class Buffer {
        private String content; //only for terminals
        //only for non-terminals, or temporary leaf-nodes whose children are not yet built
        private List<Buffer> children; 
        private int size;

        public Buffer(int size){
            this.children = new ArrayList<>();
            this.size = size;

        }
        public Buffer(String content){
            this.content = content;
        }

        public void add(int index, Buffer child){
            this.children.add(index, child);
        }


    }

    public static class Context {
        private HashMap<String, Variable> 
        private HashMap<String, Variable> symbols;
        private List<HashMap<String, Variable>> symbol_stack;
        private Variable result;
        private SQLConnection conn;
        
        public Context(SQLConnection conn){
            this.symbols = new HashMap<>();
            this.symbol_stack = new ArrayList<>();
            this.conn = conn;
        }

        public void call(List<String> arg_symbols){
            HashMap<String, Variable> newFrame = new HashMap<>();
            for (String symbol: arg_symbols){
                if (this.get_var(symbol)==null){
                    System.out.println("ERROR: Fuzzer.Context.call :: cannot find symbol "+symbol);
                    System.exit(1);
                }
                newFrame.add(this.get_var(symbol));
            }

            //preserve current symbols
            this.symbol_stack.add(this.symbols);
            this.symbols = newFrame;

            //set the return value to null to avoid confusion
            //wait actually we can not override it to allow for more performant operation chaining 
            //not sure ..... emmm
            //update: doesn't matter, that performance hit does not matter
            this.result = null;
        }

        public void ret(String returnSymbol){
            //move the return value into the cache slot
            if (returnSymbol!=null){
                this.result = this.get_var(returnSymbol);
            }

            //restore context for caller
            this.symbols = this.symbol_stack.get(this.symbol_stack.size()-1);
            this.symbol_stack.remove(this.symbol_stack.size()-1);
        }


        // the symbol here might be either a variable or a function
        // that is why the args arg this there, it won't even be looked at if it is actually a variable
        public Variable get_symbol(String symbol, List<String> args) throws IllegalArgumentException{
            if (symbol==null){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.get_var :: the symbol accessed is null, check your grammar");
            }
            if (symbol.equals("query")){
                return this.query(args);
            }
            if (this.symbols.get(symbol)==null){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.get_var :: the symbol " + symbol + " accessed does not exists or has not yet been initialized\n"+
                                                    "If you are using customized expansion order, please check your order specification\n"+
                                                    "If not, please make sure the symbol is there and consider specify the expansion ordering since the auto-scheduler is only bast-effort");
            }
            return this.symbols.get(symbol);
        }
    }

    public static class Variable {
        public boolean is_single_valued;
        private String value;
        private List<String> entries;
        private List<Integer> unique_usage_count;
        private HashMap<String, Variable> attributes;
        private int cursor; //non-decreasing, modulus entries.size() will be used for extracting index 
        public static final List<String> RESERVED_ATTR = Collections.unmodifiableList(Arrays.asList("new", "any", "next", "len", "unique_any", "query", "filter", "cur"));

        public Variable(){
            this.is_single_valued = false;
            this.entries = new ArrayList<>();
            this.unique_usage_count = new ArrayList<>();
            this.attributes = new HashMap<>();
            this.cursor = 0;
        }
        public Variable(String value){
            this.values = value;
            this.is_single_valued = true;
            this.entries = new ArrayList<>();
            this.unique_usage_count = new ArrayList<>();
            this.attributes = new HashMap<>();
            this.cursor = 0;
        }
        public void set_attribute(String attr_name, Variable value){
            if (RESERVED_ATTR.contains(attr_name)){
                System.out.println("ERROR: "+attr_name+" is a reserved attribute name, please use another name instead");
                System.exit(1);
            }
            this.attributes.put(attr_name, value);
        }

        // new, query, getColumn, withColumnAsAttr will not be handled as those are not attributes but functions
        // those will be implemented in the Context class
        public Variable get_attr(String name, List<String> args) throws Exception{
            if (name.equals("any")){
                if (this.is_single_valued){
                    throw new IllegalArgumentException("Fuzzer.Variable.get_attr :: attribute any is not applicable to single-valued variables");
                }
                if (this.entries.size()==0){
                    throw new UnavailableException("", false, true, false);
                }
                return random_from_list(entries);
            }
            else if (name.equals("unique_any")){
                if (this.is_single_valued){
                    throw new IllegalArgumentException("Fuzzer.Variable.get_attr :: attribute unique_any is not applicable to single-valued variables");
                }
                if (this.entries.size()==0){
                    throw new UnavailableException("", false, true, false);
                }
                List<Integer> avail_idx = new ArrayList<>();
                for (int i=0; i<this.unique_usage_count.size(); i++){
                    if (this.unique_usage_count.get(i)==0){
                        avail_idx.add(i);
                    }
                }
                if (avail_idx.size()==0){
                    throw new UnavailableException("", false, false, true);
                }
                int idx = random_from_list(avail_idx);
                this.unique_usage_count.set(idx, 1);
                return this.entries.get(idx);
            }
            else if (name.equals("len")){
                if (this.is_single_valued){
                    return new Variable(String.valueOf(1));
                }
                return new Variable(String.valueOf(this.entries.size()));
            }
            else if (name.equals("next")){
                if (this.is_single_valued){
                    throw new IllegalArgumentException("Fuzzer.Variable.get_attr :: attribute next is not applicable to single-valued variables");
                }
                if (this.entries.size()==0){
                    throw new UnavailableException("", false, true, false);
                }
                this.cursor += 1;
                return this.entries.get((this.cursor-1)%this.entries.size());
            }
            else if (name.equals("cur")){
                if (this.is_single_valued){
                    throw new IllegalArgumentException("Fuzzer.Variable.get_attr :: attribute cur is not applicable to single-valued variables");
                }
                if (this.entries.size()==0){
                    throw new UnavailableException("", false, true, false);
                }
                return this.entries.get(this.cursor%this.entries.size());
            }
            else if (name.equals("filter")){
                if (args.size()!=3){
                    throw new IllegalArgumentException("Fuzzer.Variable.get_attr :: filter function expects 3 arguments: attribute to be filtered, comparator, and a pivot value. "+args.size()+" are given");
                }
                return this.filter(args.get(0), args.get(1), args.get(2));
            }

        }
        private Variable filter(String attr, String comparator, Variable target) throws Exception{
            if (this.is_single_valued){
                throw new IllegalArgumentException("Fuzzer.Variable.filter :: filter operation is not allowed on single-valued variables");
            }
            Variable result = new Variable();
            for (Variable v : this.entries){
                if (v.get_attr(attr).compare(comparator, target)){
                    result.add_entry(v);
                }
            }
            return result;
        }
        public boolean compare(String comparator, Variable other) throws IllegalArgumentException{
            if (comparator.equals("==")){
                return this.equals(other);
            }
            if (comparator.equals("!=")){
                return !this.equals(other);
            }

            if (comparator.equals(">=")){
                try {
                    Double operand_a = Double.valueOf(this.value);
                    Double operand_b = Double.valueOf(other.value);
                    return operand_a.doubleValue()>=operand_b.doubleValue();
                }
                catch (NumberFormatException e){
                    throw new NumberFormatException(
                        "Fuzzer.Variable.compare :: >= comparator expects both variables to contain numerical value\n"+e.getMessage(), 
                        e
                    );
                }
            }
            if (comparator.equals("<=")){
                try {
                    Double operand_a = Double.valueOf(this.value);
                    Double operand_b = Double.valueOf(other.value);
                    return operand_a.doubleValue()<=operand_b.doubleValue();
                }
                catch (NumberFormatException e){
                    throw new NumberFormatException(
                        "Fuzzer.Variable.compare :: <= comparator expects both variables to contain numerical value\n"+e.getMessage(), 
                        e
                    );
                }
            }
            if (comparator.equals(">")){
                try {
                    Double operand_a = Double.valueOf(this.value);
                    Double operand_b = Double.valueOf(other.value);
                    return operand_a.doubleValue()>operand_b.doubleValue();
                }
                catch (NumberFormatException e){
                    throw new NumberFormatException(
                        "Fuzzer.Variable.compare :: > comparator expects both variables to contain numerical value\n"+e.getMessage(), 
                        e
                    );
                }
            }
            if (comparator.equals("<")){
                try {
                    Double operand_a = Double.valueOf(this.value);
                    Double operand_b = Double.valueOf(other.value);
                    return operand_a.doubleValue()<operand_b.doubleValue();
                }
                catch (NumberFormatException e){
                    throw new NumberFormatException(
                        "Fuzzer.Variable.compare :: < comparator expects both variables to contain numerical value\n"+e.getMessage(), 
                        e
                    );
                }
            }
            throw new IllegalArgumentException("Fuzzer.Variable.compare :: comparator "+comparator+" is not recognizable");
        }
        public String get_value(){
            if (!this.is_single_valued){
                throw new IllegalArgumentException("Fuzzer.Variable.get_value :: get_value is not applicable to multi-valued variable");
            }
            return this.value;
        }
        public void add_entry(Variable v){
            if (this.is_single_valued){
                throw new IllegalArgumentException("Fuzzer.Variable.add_entry :: add_entry is not applicable to single-valued variable");
            }
            this.entries.add(v);
            this.unique_usage_count.add(0);
        }
        // making sure this.value and this.entries are the same
        // does not check for reference counters and cursor (differences in those will be considered as same variable in different state)
        @Override
        public boolean equals(Object another){
            if (another instanceof Variable){
                Variable other = (Variable)another;
                if (this.value==other.value || (this.value!=null && this.value.equals(other.value))){
                    if (this.entries.size()==other.entries.size()){
                        for (int i=0; i<this.entries.size(); i++){
                            if (!this.entries.get(i).equals(other.entries.get(i))){
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }
            return false;
        }
    }


    public <graph_name/>Fuzzer(SQLConnection con, int fuzzing_depth, int loop_threshold){
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
<DBMS_OPTIONS/>
    }


    public String generate_rule(String rule_name) throws Exception{
        //this.log("Translating: "+rule_name);
<CALL_RULE_NAME/>
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
<STAGE/>
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
<RULE/>
}

