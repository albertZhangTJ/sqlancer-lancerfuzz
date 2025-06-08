package sqlancer.any;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import java.util.Arrays;

import java.lang.IllegalArgumentException;
import java.lang.NumberFormatException;
import sqlancer.SQLConnection;
import java.sql.*;
public class Fuzzer{

    public static long query_time = 0;
    public static long dispatch_time = 0;
    public static long generation_time = 0;
    public static long turn_time = 0;
    public static class UnavailableException extends Exception {
        private static final long serialVersionUID = 1L;
        public boolean isUndefined; //the variable/attr requested does not exist
        public boolean isUninitialized; //the variable/attr requests exists but has no value associated
        public boolean isOut; //the variable/attr requested exists but is insufficient in numbers (no more unique ones exists)
        public UnavailableException(String message, boolean isUndefined, boolean isUninitialized, boolean isOut){
            super(message);
            this.isUndefined = isUndefined;
            this.isUninitialized = isUninitialized;
            this.isOut = isOut;
        }
    }

    public static class DeadEndException extends Exception {
        private static final long serialVersionUID = 1L;
        public DeadEndException(String message){
            super(message);
        }
    }

    public static class Rand{
        public static <T> T random_from_list(List<T> l){
            int idx = (int)(Math.random()*l.size());
            return l.get(idx);
        }
        public static <T> T random_from_list_weighted(List<T> l, List<Integer> weights) throws DeadEndException{
            int total = 0;
            if (l.size()==0){
                throw new DeadEndException("Rand::random_from_list_weighted : no available options");
            }
            for (int w: weights){
                total = total + w;
            }
            total = (int)(total * Math.random());
            for (int i=0; i<l.size(); i++){
                if (total<weights.get(i)){
                    return l.get(i);
                }
                total = total - weights.get(i);
            }
            return l.get(l.size()-1);
        }
        public static int random(int min, int max){
            return (int)(min + (max-min+1)*Math.random());
        }
    }

    public static class Buffer {
        private String content; //only for terminals
        //only for non-terminals, or temporary leaf-nodes whose children are not yet built
        private List<Buffer> children; 

        public Buffer(){
            this.content = null;
            this.children = new ArrayList<>();
        }

        public Buffer(String content){
            if (content==null){
                throw new IllegalArgumentException("ERROR : Buffer::Buffer : Terminal buffer nodes should not have null content. For a placeholder, use empty string \"\" instead");
            }
            this.content = content;
        }

        public void add(Buffer child){
            this.children.add(child);
        }

        public Variable add(Variable terminal) throws Exception{
            if (terminal==null || !terminal.isSingleValued){
                return terminal;
            }
            this.children.add(new Buffer(terminal.getValue()));
            return terminal;
        }

        public void set(int index, Buffer child){
            while (this.children.size()<=index){
                this.children.add(new Buffer());
            }
            this.children.set(index, child);
        }

        public int size(){
            return this.children==null ? 0 : this.children.size() ;
        }

        public Buffer remove(int idx) throws Exception{
            if (this.children==null){
                throw new Exception("Buffer::remove : remove cannot be called on a terminal node");
            }
            if (idx>=this.children.size()){
                throw new Exception("Buffer::remove : invalid index "+idx+", children size "+this.children.size());
            }
            return this.children.remove(idx);
        }
        public String toString(){
            if (this.content!=null){
                return this.content;
            }
            String res = "";
            for (Buffer child : this.children){
                res = res + child.toString();
            }
            return res;
        }

    }

    public static class Context {
        //global variables
        private HashMap<String, Variable> globalSymbols = new HashMap<>();
        //local variables
        private HashMap<String, Variable> symbols;
        private List<HashMap<String, Variable>> symbolStack;
        public List<Variable> args;
        private List<String> errors;
        //return value from a grammar function call
        private Variable result;
        //JDBC connection to the DB under test, only used by the query built-in function
        private Statement stmt;
        //This is the list of supported operators by SGL
        public static final List<String> OPERATORS = Collections.unmodifiableList(Arrays.asList("=", "+=", "$+", "$-", "==", "!=", ">", "<", ">=", "<="));
        //used by the "new" function for making sure all symbols in the target language scope is unique
        //for example, the first call to new["table"] will return 'table1', second call will be 'table2' and so on
        private HashMap<String, Integer> idCount; 
        
        private static List<String> rules = new ArrayList<>();

        public static void add_rule(String name){
            rules.add(name);
        }

        public static void clear_rules(){
            rules = new ArrayList<>();
        }


        public Context(Statement stmt){
            this.symbols = new HashMap<>();
            this.symbolStack = new ArrayList<>();
            this.errors = new ArrayList<>();
            this.stmt = stmt;
            this.idCount = new HashMap<>();
        }

        public void addError(Variable v) throws Exception{
            try {
                this.errors.add(v.getValue());
            }
            catch (IllegalArgumentException e){
                throw new java.lang.IllegalArgumentException("ERROR: Fuzzer.Context.addError :: variable passed to expected error declaration is not single-valued, check your expected error declarations", e);
            }
        }

        public void resetError(){
            this.errors = new ArrayList<>();
        }

        public boolean isExpectedError(String msg){
            for (String err: this.errors){
                //System.out.println("-- Expected error: "+err);
                if (msg.contains(err)){
                    return true;
                }
            }
            //System.out.println("-- Msg not found: "+msg);
            return false;
        }

        //the calling convention in SGL is:
        //The caller evaluates the arguments and pass its own buffer, the name of the callee, and the arguments here
        //This function will will push the passed arguments to this.args (think arguments passed using registers)
        //Then the control is passed to the callee
        //If the function is not a fragment
        //The callee will create a new stack frame of its own
        //arg_decls will first be evaluated in the callee frame (which is empty up until now)
        //Then the callee will use enter() to move the arguments to its own stack frame (only itself knows how to do that)
        //After completing its business, the callee will push its return value to this.result (again, think register) and restore the stack frame for caller
        //The call() function will add the returned buffer to the caller one and return this.result (so that the behavior is consistent)
        public Variable call(Buffer buf, String rule, List<Variable> args) throws Exception{
            this.push_args(args);
            buf.add(Fuzzer.dispatch(this, rule));
            return this.result;
        }

        public void push_args(List<Variable> args){
            this.args = args;
        }

        public void push_frame(){
            HashMap<String, Variable> newFrame = new HashMap<>();
            //preserve current symbols
            this.symbolStack.add(this.symbols);
            this.symbols = newFrame;

            //set the return value to null to avoid confusion
            this.result = null;
        }
        // arg_decls is the parameter list declared in the lhs of the current/callee rule
        // it will first be evaluated in the callee frame (which is empty up until now)
        // in this pass, the variables without default values will be assigned with placeholders
        // those with default values will be assigned with their corrsponding defaults
        // then a second pass will assign the passed arguments positionally, overwriting exisiting placeholders and defaults
        // finally, we will check for placeholders in the parameter list
        // If any is found, then we will throw an exception as it is not covered by either default or passed
        public void enter(List<Variable> arg_decls) throws Exception{
            if (args!=null && args.size()>0){
                if (args.size()>arg_decls.size()){
                    String msg = "args.size() == "+args.size()+"\n";
                    throw new IllegalArgumentException("Fuzzer.Context.enter :: size of provided argument list exceeds that in the rule signature\n" + msg);
                }
                for (int i=0; i<this.args.size(); i++){
                    arg_decls.get(i).clone(this.args.get(i));    
                }
            }
            for (Variable v : arg_decls){
                if (v.isPlaceHolder()){
                    throw new IllegalArgumentException("Fuzzer.Context.enter :: an argument must either be assigned through default value or passed parameter");
                }
            }
            this.args = null;
        }


        public void ret(Variable result) throws Exception{
            //move the return value into the cache slot
            this.result= result;

            //restore context for caller
            this.symbols = this.symbolStack.get(this.symbolStack.size()-1);
            this.symbolStack.remove(this.symbolStack.size()-1);
        }


        // the symbol here might be either a variable or a function
        // that is why the args arg this there, it won't even be looked at if it is actually a variable
        public Variable getSymbol(Buffer buf, String symbol, List<Variable> args) throws Exception{
            if (symbol==null){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.getSymbol :: the symbol accessed is null, check your grammar");
            }
            if (symbol.equals("query")){
                return this.query(args);
            }
            if (symbol.equals("random")){
                return this.random(args);
            }
            if (symbol.equals("error")){
                return this.addExpectedError(args);
            }
            if (symbol.equals("new")){
                return this.new_id(args);
            }
            if (rules.contains(symbol)){
                return this.call(buf, symbol, args);
            }
            if (this.symbols.get(symbol)==null && this.globalSymbols.get(symbol)==null){
                if (Character.isUpperCase(symbol.charAt(0))){
                    this.globalSymbols.put(symbol, Variable.placeholder());
                }
                else if (Character.isLowerCase(symbol.charAt(0))){
                    this.symbols.put(symbol, Variable.placeholder());
                }
                else {
                    throw new Exception("Variable.getSymbol : a user-defined symbol must start with either lower case letter (local) or upper case letter (global)");
                }
            }
            return this.symbols.get(symbol)==null ? this.globalSymbols.get(symbol) : this.symbols.get(symbol);
        }

        //random generator 
        // handles the following cases
        //random[fix_number, delimiter]
        //random[min, max]
        //random[min, delimiter, decay_spec]
        //random[min, max, delimiter]
        //random[min, max, delimiter, decay_spec]
        // decay_spec should be an interger ranging 0 to 99, default 50
        // 0 for uniform distribution, uniform distribution without a max is not allowed
        // Otherwise, the probability of getting x is (decay_spec/100)*(1-decay_spec/100)^(x-1)
        public Variable random(List<Variable> args) throws Exception{
            try{
                if (args.size()==2){
                    //random[fix, delimiter]
                    if (args.get(0).isNumerical() && args.get(1).isString()){
                        int val = args.get(0).getNumerical();
                        String delimiter = args.get(1).getValue();
                        Variable res = Variable.factory(val);
                        res.setAttr("delimiter", Variable.factory(delimiter));
                        return res;
                    }
                    //random[min, max]
                    if (args.get(0).isNumerical() && args.get(1).isNumerical()){
                        int min = args.get(0).getNumerical();
                        int max = args.get(1).getNumerical();
                        double dr = 0.5;
                        int val = min;
                        while (true){
                            if (Math.random()<dr){
                                break;
                            }
                            val++;
                            //wrap around when overflow
                            if (val>max){
                                val = min;
                            }
                        }
                        return Variable.factory(val);
                    }
                }
                if (args.size()==3){
                    //random[min, delimiter, decay_spec]
                    if (args.get(0).isNumerical() && args.get(1).isString() && args.get(2).isNumerical()){
                        int min = args.get(0).getNumerical();
                        String delimiter = args.get(1).getValue();
                        int ds = args.get(2).getNumerical();
                        if (ds==0){
                            throw new IllegalArgumentException("ERROR : Fuzzer.Context.random :: random function cannot be called with no max AND uniform distribution");
                        }
                        if (ds>99 || ds<0){
                            throw new IllegalArgumentException("ERROR : Fuzzer.Context.random :: unrecognizable decay_spec, accepted value are integers in range [0,99]");
                        }
                        int val = min;
                        double dr = ds/100.0;
                        while (true){
                            if (Math.random()<dr){
                                break;
                            }
                            val++;
                        }
                        Variable res = Variable.factory(val);
                        res.setAttr("delimiter", Variable.factory(delimiter));
                        return res;
                    }
                    //random[min,max,decay_spec]
                    if (args.get(0).isNumerical() && args.get(1).isNumerical() && args.get(2).isNumerical()){
                        int min = args.get(0).getNumerical();
                        int max = args.get(1).getNumerical();
                        int ds = args.get(2).getNumerical();
                        if (ds>99 || ds<0){
                            throw new IllegalArgumentException("ERROR : Fuzzer.Context.random :: unrecognizable decay_spec, accepted value are integers in range [0,99]");
                        }
                        int val = min;
                        double dr = ds/100.0;
                        if (ds==0){
                            val += (int)((max-min+1)*Math.random());
                        }
                        else {
                            while (true){
                                if (Math.random()<dr){
                                    break;
                                }
                                val++;
                                //wrap around when overflow
                                if (val>max){
                                    val = min;
                                }
                            }
                        }
                        Variable res = Variable.factory(val);
                        return res;
                    }
                    //random[min,max,delimiter]
                    if (args.get(0).isNumerical() && args.get(1).isNumerical() && args.get(2).isString()){
                        int min = args.get(0).getNumerical();
                        int max = args.get(1).getNumerical();
                        String delimiter = args.get(2).getValue();
                        int val = min;
                        double dr = 0.75;
                        while (true){
                            if (Math.random()<dr){
                                break;
                            }
                            val++;
                            //wrap around when overflow
                            if (val>max){
                                val = min;
                            }
                        }
                        Variable res = Variable.factory(val);
                        res.setAttr("delimiter", Variable.factory(delimiter));
                        return res;
                    }
                }
                if (args.size()==4){
                    if (args.get(0).isNumerical() && args.get(1).isNumerical() && args.get(2).isString() && args.get(3).isNumerical()){
                        int min = args.get(0).getNumerical();
                        int max = args.get(1).getNumerical();
                        String delimiter = args.get(2).getValue();
                        int ds = args.get(3).getNumerical();
                        if (ds>99 || ds<0){
                            throw new IllegalArgumentException("ERROR : Fuzzer.Context.random :: unrecognizable decay_spec, accepted value are integers in range [0,99]");
                        }
                        int val = min;
                        if (ds==0){
                            val += (int)((max-min+1)*Math.random());
                        }
                        else {
                            double dr = ds/100.0;
                            while (true){
                                if (Math.random()<dr){
                                    break;
                                }
                                val++;
                                //wrap around when overflow
                                if (val>max){
                                    val = min;
                                }
                            }
                        }
                        Variable res = Variable.factory(val);
                        res.setAttr("delimiter", Variable.factory(delimiter));
                        return res;
                    }
                }
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.random :: Expecting 2, 3, or 4 arguments, got "+args.size());
            }
            catch (IllegalArgumentException e){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.random :: arguments passed do not match any know signatures\n"+
                                                    "random[fix_number, delimiter]\n" + 
                                                    "random[min, max]\n" +
                                                    "random[min, delimiter, decay_spec]\n" + 
                                                    "random[min, max, delimiter]\n" +
                                                    "random[min, max, delimiter, decay_spec]\n" + 
                                                    "decay_spec should be an interger ranging 0 to 99, default 50\n" + 
                                                    "0 for uniform distribution, uniform distribution without a max is not allowed\n", e);
            }
        }

        //returning a variable object containing an empty string since
        //all calls to a function is expected to return something
        //so we are using this as a placeholder
        public Variable addExpectedError(List<Variable> args) throws Exception{
            for (Variable arg : args){
                this.errors.add(arg.getValue());
            }
            return Variable.factory("");
        }

        //
        public Variable eval(Variable a, String operator, Variable b) throws Exception{
            if (operator==null){
                throw new IllegalArgumentException("ERROR : Variable.eval :: operator cannot be null");
            }
            //this is for the author's own use
            //if an operator is illegal, it shouldn't be recognized by the parser in the first place
            //this almost for sure means there is some kinda implementation error
            if (!OPERATORS.contains(operator)){
                throw new IllegalArgumentException("ERROR : Context.eval :: "+operator+" is not a recognized operator. Internal error, you shouldn't have reached here.");
            }
            if (operator.equals("=")){
                a.clone(b);
                return a;
            }
            if (operator.equals("+=")){
                a.addEntry(b);
                return b;
            }
            if (operator.equals("$+")){
                if (a.isNumerical() && b.isNumerical()){
                    return Variable.factory(a.getNumerical() + b.getNumerical());
                }
                return Variable.factory(a.getValue()+b.getValue());
            }
            if (operator.equals("$-")){
                if (a.isNumerical() && b.isNumerical()){
                    if (a.getNumerical() - b.getNumerical() < 0){
                        throw new Exception("ERROR : Context.eval :: Integer underflow, negative values are not supported in SGL");
                    }
                    return Variable.factory(a.getNumerical() - b.getNumerical());
                }
                throw new IllegalArgumentException("ERROR : Context.eval :: the operator \"-\" is not applicable when either side of the operation is not numerical");
            }
            return Variable.factory(a.compare(operator, b));
        }


        public void setSymbol(String symbol, Variable v) throws Exception{
            if (symbol==null || symbol.length()==0 || !((symbol.charAt(0)>=65 && symbol.charAt(0)<=90) || (symbol.charAt(0)>=97 && symbol.charAt(0)<=122))){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.setSymbol :: symbol must be non-empty and start with an ASCII letter");
            }
            if (Fuzzer.rules.contains(symbol)){
                throw new Exception("Context::setSymbol : symbol conflict with rule "+symbol);
            }
            // starts with a capital letter
            if (symbol.charAt(0)<92){
                this.globalSymbols.put(symbol, v);
            }
            else {
                this.symbols.put(symbol, v);
            }
        }
        //requires 2 or more arguments
        //the 
        public Variable query(List<Variable> args) throws Exception{
            long stime = System.currentTimeMillis();
            if (this.stmt==null){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.query :: JDBC Connection to target DBMS is not set or set to null, query function not available");
            }
            if (args.size()<2){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.query :: a call to the query function must contain at least 2 arguments: query and column name");
            }
            String query = args.get(0).getValue();
            String col = args.get(1).getValue();
            Variable v = Variable.factory();
            ResultSet rs = this.stmt.executeQuery(query);
            while (rs.next()){
                Variable r = Variable.factory(rs.getString(col));
                for (int i=2; i<args.size(); i=i+2){
                    String attrCol = args.get(i).getValue();
                    String attr = args.get(i+1).getValue();
                    r.setAttr(attr, Variable.factory(rs.getString(attrCol)));
                }
                v.addEntry(r);
            }
            rs.close();
            Fuzzer.query_time += System.currentTimeMillis()-stime;
            return v;
        }
        //for the "new" built-in function 
        public Variable new_id(List<Variable> args) throws Exception{
            if (args.size()!=1){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.new_id :: a call to the new function expects exactly 1 argument: the prefix of the id to be generated");
            }
            String prefix = args.get(0).getValue();
            if (this.idCount.containsKey(prefix)){
                this.idCount.put(prefix, this.idCount.get(prefix)+1);
            }
            else {
                this.idCount.put(prefix, 1);
            }
            return new Variable(prefix+this.idCount.get(prefix));
        }
    }

    public static class Variable {
        public boolean isSingleValued;
        private String value;
        private int numerical;
        private boolean bool;
        private boolean containsNumerical;
        private boolean containsBoolean;
        private boolean containsString;
        private List<Variable> entries;
        private List<Integer> uniqueUsageCount;
        private HashMap<String, Variable> attributes;
        private int cursor; //non-decreasing, modulus entries.size() will be used for extracting index 
        private boolean isUninitialized;

        public static final List<String> RESERVED_ATTR = Collections.unmodifiableList(Arrays.asList("new", "any", "next", "len", "unique_any", "query", "filter", "cur"));
        
        private Variable(){
            this.isSingleValued = false;
            this.containsNumerical = false;
            this.containsBoolean = false;
            this.containsString = false;
            this.entries = new ArrayList<>();
            this.uniqueUsageCount = new ArrayList<>();
            this.attributes = new HashMap<>();
            this.cursor = 0;
            this.isUninitialized = false;
        }
        private Variable(String value){
            this.value = value;
            this.isSingleValued = true;
            this.containsNumerical = false;
            this.containsBoolean = false;
            this.containsString = true;
            this.entries = new ArrayList<>();
            this.uniqueUsageCount = new ArrayList<>();
            this.attributes = new HashMap<>();
            this.cursor = 0;
            this.isUninitialized = false;
        }
        private Variable(int numerical){
            this.value = ""+numerical;
            this.numerical = numerical;
            this.isSingleValued = true;
            this.containsNumerical = true;
            this.containsBoolean = false;
            this.containsString = false;
            this.entries = new ArrayList<>();
            this.uniqueUsageCount = new ArrayList<>();
            this.attributes = new HashMap<>();
            this.cursor = 0;
            this.isUninitialized = false;
        }
        private Variable(boolean bool){
            this.value = ""+bool;
            this.bool = bool;
            this.isSingleValued = true;
            this.containsNumerical = false;
            this.containsBoolean = true;
            this.containsString = false;
            this.entries = new ArrayList<>();
            this.uniqueUsageCount = new ArrayList<>();
            this.attributes = new HashMap<>();
            this.cursor = 0;
            this.isUninitialized = false;
        }

        //just need a new signature, those arguments are not actually used
        private Variable(boolean placeholder, boolean p){
            this.isUninitialized = true;
        }
        //by returning this at the end of the setter, we can use this class in an FP way
        public Variable setAttr(String attr_name, Variable value) throws IllegalArgumentException{
            if (RESERVED_ATTR.contains(attr_name)){
                throw new IllegalArgumentException("ERROR: "+attr_name+" is a reserved attribute name, please use another name instead");
            }
            this.attributes.put(attr_name, value);
            return this;
        }

        // factory method for a Variable of list type
        public static Variable factory(){
            return new Variable();
        }

        // factory method for a Variable of string type
        public static Variable factory(String value){
            return new Variable(value);
        }

        // factory method for a Variable of numerical type
        public static Variable factory(int numerical){
            return new Variable(numerical);
        }

        // factory method for a Variable of boolean type
        public static Variable factory(boolean bool){
            return new Variable(bool);
        }

        // factory method for a placeholder Variable
        public static Variable placeholder(){
            return new Variable(true, true);
        }
        
        //make the current variable a shallow-copy of other
        //the cursor will be set to 0
        //mainly used for assignment (i.e. a=b will be context.getSymbol("a").clone(context.getSymbol("b")))
        @SuppressWarnings("unused")
        public void clone(Variable other) throws Exception{
            if (other.isUninitialized){
                throw new UnavailableException("An uninitialized variable should not be cloned, check your grammar file for assignments using these", false, true, false);
            }
            this.isUninitialized = false;
            this.isSingleValued = other.isSingleValued;
            this.value = other.value;
            this.numerical = other.numerical;
            this.bool = other.bool;
            this.containsNumerical = other.containsNumerical;
            this.containsBoolean = other.containsBoolean;
            this.entries = copy_list(other.entries);
            this.uniqueUsageCount = new ArrayList<>();
            for (Variable v: this.entries){
                this.uniqueUsageCount.add(0);
            }
            this.attributes = copy_map(other.attributes);
            this.cursor = 0;
        }
        
        // new, query, getColumn, withColumnAsAttr will not be handled as those are not attributes but functions
        // those will be implemented in the Context class
        public Variable getAttr(String name, List<Variable> args) throws Exception{
            if (name.equals("any")){
                if (this.isSingleValued){
                    throw new IllegalArgumentException("Fuzzer.Variable.getAttr :: attribute any is not applicable to single-valued variables");
                }
                if (this.entries.size()==0){
                    throw new UnavailableException("", false, true, false);
                }
                return Rand.random_from_list(entries);
            }
            else if (name.equals("unique_any")){
                if (this.isSingleValued){
                    throw new IllegalArgumentException("Fuzzer.Variable.getAttr :: attribute unique_any is not applicable to single-valued variables");
                }
                if (this.entries.size()==0){
                    throw new UnavailableException("", false, true, false);
                }
                List<Integer> avail_idx = new ArrayList<>();
                for (int i=0; i<this.uniqueUsageCount.size(); i++){
                    if (this.uniqueUsageCount.get(i)==0){
                        avail_idx.add(i);
                    }
                }
                if (avail_idx.size()==0){
                    throw new UnavailableException("", false, false, true);
                }
                int idx = Rand.random_from_list(avail_idx);
                this.uniqueUsageCount.set(idx, 1);
                return this.entries.get(idx);
            }
            else if (name.equals("len")){
                if (this.isSingleValued){
                    return new Variable(1);
                }
                return new Variable(this.entries.size());
            }
            else if (name.equals("next")){
                if (this.isSingleValued){
                    throw new IllegalArgumentException("Fuzzer.Variable.getAttr :: attribute next is not applicable to single-valued variables");
                }
                if (this.entries.size()==0){
                    throw new UnavailableException("", false, true, false);
                }
                this.cursor += 1;
                return this.entries.get((this.cursor-1)%this.entries.size());
            }
            else if (name.equals("cur")){
                if (this.isSingleValued){
                    throw new IllegalArgumentException("Fuzzer.Variable.getAttr :: attribute cur is not applicable to single-valued variables");
                }
                if (this.entries.size()==0){
                    throw new UnavailableException("", false, true, false);
                }
                return this.entries.get(this.cursor%this.entries.size());
            }
            else if (name.equals("filter")){
                if (args.size()!=3){
                    throw new IllegalArgumentException("Fuzzer.Variable.getAttr :: filter function expects 3 arguments: attribute to be filtered, comparator, and a pivot value. "+args.size()+" are given");
                }
                return this.filter(args.get(0), args.get(1).getValue(), args.get(2));
            }
            if (this.attributes.get(name)==null){
                this.attributes.put(name, Variable.placeholder());
            }
            return this.attributes.get(name);

        }
        private Variable filter(Variable attr, String comparator, Variable target) throws Exception{
            if (this.isSingleValued){
                throw new IllegalArgumentException("Fuzzer.Variable.filter :: filter operation is not allowed on single-valued variables");
            }
            Variable result = new Variable();
            for (Variable v : this.entries){
                if (v.getAttr(attr.getValue(), null).compare(comparator, target)){
                    result.addEntry(v);
                }
            }
            return result;
        }
        public boolean compare(String comparator, Variable other) throws Exception{
            if (comparator.equals("==")){
                return this.equals(other);
            }
            if (comparator.equals("!=")){
                return !this.equals(other);
            }

            if (comparator.equals(">=")){
                try {
                    Integer operand_a = Integer.valueOf(this.value);
                    Integer operand_b = Integer.valueOf(other.value);
                    return operand_a.intValue()>=operand_b.intValue();
                }
                catch (NumberFormatException e){
                    throw new NumberFormatException(
                        "Fuzzer.Variable.compare :: >= comparator expects both variables to contain numerical value\n"+e.getMessage()
                    );
                }
            }
            if (comparator.equals("<=")){
                try {
                    Integer operand_a = Integer.valueOf(this.value);
                    Integer operand_b = Integer.valueOf(other.value);
                    return operand_a.intValue()<=operand_b.intValue();
                }
                catch (NumberFormatException e){
                    throw new NumberFormatException(
                        "Fuzzer.Variable.compare :: <= comparator expects both variables to contain numerical value\n"+e.getMessage()
                    );
                }
            }
            if (comparator.equals(">")){
                try {
                    Integer operand_a = Integer.valueOf(this.value);
                    Integer operand_b = Integer.valueOf(other.value);
                    return operand_a.intValue()>operand_b.intValue();
                }
                catch (NumberFormatException e){
                    throw new NumberFormatException(
                        "Fuzzer.Variable.compare :: > comparator expects both variables to contain numerical value\n"+e.getMessage()
                    );
                }
            }
            if (comparator.equals("<")){
                try {
                    Integer operand_a = Integer.valueOf(this.value);
                    Integer operand_b = Integer.valueOf(other.value);
                    return operand_a.intValue()<operand_b.intValue();
                }
                catch (NumberFormatException e){
                    throw new NumberFormatException(
                        "Fuzzer.Variable.compare :: < comparator expects both variables to contain numerical value\n"+e.getMessage()
                    );
                }
            }
            throw new IllegalArgumentException("Fuzzer.Variable.compare :: comparator "+comparator+" is not recognizable");
        }

        //getValue returns the content of the current variable regardless if it is of string type or not
        //roughly equals to toString
        public String getValue() throws Exception{
            if (this.isUninitialized){
                throw new UnavailableException("Fuzzer.Variable.getValue :: the current variable is not initialized", false, true, false);
            }
            if (!this.isSingleValued){
                throw new IllegalArgumentException("Fuzzer.Variable.getValue :: getValue is not applicable to multi-valued variable");
            }
            return this.value;
        }

        public boolean isString() throws UnavailableException{
            if (this.isUninitialized){
                throw new UnavailableException("Fuzzer.Variable.isString :: the current variable is not initialized", false, true, false);
            }
            return this.containsString;
        }

        public boolean isPlaceHolder(){
            return this.isUninitialized;
        }
        public boolean isNumerical() throws UnavailableException{
            if (this.isUninitialized){
                throw new UnavailableException("Fuzzer.Variable.isNumerical :: the current variable is not initialized", false, true, false);
            }
            return this.containsNumerical;
        }

        public int getNumerical() throws UnavailableException{
            if (this.isUninitialized){
                throw new UnavailableException("Fuzzer.Variable.getNumerical :: the current variable is not initialized", false, true, false);
            }
            return this.numerical;
        }
        public boolean isBoolean() throws UnavailableException{
            if (this.isUninitialized){
                throw new UnavailableException("Fuzzer.Variable.isBoolean :: the current variable is not initialized", false, true, false);
            }
            return this.containsBoolean;
        }
        public boolean getBoolean() throws UnavailableException{
            if (this.isUninitialized){
                throw new UnavailableException("Fuzzer.Variable.getBoolean :: the current variable is not initialized", false, true, false);
            }
            if (!this.containsBoolean){
                throw new IllegalArgumentException("Fuzzer.Variable.getBoolean :: the current variable is not a boolean one");
            }
            return this.bool;
        }

        //by returning this at the end of the setter, we can use this class in an FP way
        public Variable addEntry(Variable v) throws Exception{
            if (this.isUninitialized){
                this.clone(factory());
            }
            if (this.isSingleValued){
                throw new IllegalArgumentException("Fuzzer.Variable.addEntry :: addEntry is not applicable to single-valued variable");
            }
            this.entries.add(v);
            this.uniqueUsageCount.add(0);
            return this;
        }
        public Variable getEntry(int idx)throws Exception{
            if (this.isUninitialized){
                throw new UnavailableException("Fuzzer.Variable.getEntry :: the current variable is not initialized", false, true, false);
            }
            if (this.isSingleValued){
                throw new IllegalArgumentException("Fuzzer.Variable.getEntry :: addEntry is not applicable to single-valued variable");
            }
            return this.entries.get(idx);
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

        public static <T> List<T> copy_list(List<T> ori){
            if (ori==null){
                return null;
            }
            List<T> res = new ArrayList<>();
            for (T item: ori){
                res.add(item);
            }
            return res;
        }
    
        public static <T, U> HashMap<T, U> copy_map(HashMap<T, U> ori){
            if (ori==null){
                return null;
            }
            HashMap<T, U> res = new HashMap<>();
            for (HashMap.Entry<T, U> entry: ori.entrySet()){
                res.put(entry.getKey(), entry.getValue());
            }
            return res;
        }
    }

    public static class CharSet{
        // Implementation acquired from https://stackoverflow.com/q/220547
        // with modifications
        public static boolean is_printable(char c){
            Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
            return c==0 || //Allows for generating null bytes
                    "\r\t\n".contains(""+c) || 
                    (
                        (!Character.isISOControl(c)) &&
                        block != null &&
                        block != Character.UnicodeBlock.SPECIALS
                    );
        }

        // return the ranges of printable characters (represented as Integers)
        // between a lower bound and an upper bound (upper bound not inclusive)
        // The length of the result List is guaranteed to be even
        // The values stored at even indices represents the beginning of a range (inclusive)
        // The value stored after that represents the end of that range (not inclusive)
        public static List<Integer> printable_ranges(int lower_bound, int upper_bound) throws Exception{
            if (lower_bound<0 || upper_bound<0 || upper_bound<lower_bound){
                throw new Exception("AstUtils::printable_ranges : invalid range "+lower_bound+", "+upper_bound);
            }
            List<Integer> ranges = new ArrayList<Integer>();
            int start = -1;
            for (int i=lower_bound; i<upper_bound; i++){
                if (start==-1 && is_printable((char)i)){
                    start = i;
                }
                else if (start!=-1 && !is_printable((char)i)){
                    ranges.add(start);
                    ranges.add(i);
                    start = -1;
                }
            }

            if (start!=-1){
                ranges.add(start);
                ranges.add(upper_bound);
            }

            return ranges;
        }
        public static int get_random_character_from_set(List<Integer> set) throws Exception{
            if (set.size()==0){
                throw new Exception("CharSet::get_random_character_from_set : Cannot handle empty set");
            }
            if (set.size()%2!=0){
                throw new Exception("CharSet::get_random_character_from_set : size of the set must be even, actual size: "+set.size());
            }
    
            //randomly select a range
            int ranges = set.size()/2;
            int range = (int)(Math.random()*ranges);
            int start = set.get(range*2);
            int end = set.get(range*2+1);
    
            //return a random number from that range
            return (int)(Math.random()*(end-start)+start);
        }
    }

    public static class Options {
        private List<Integer> indices;
        private List<Double> weights;
        public Options(){
            this.indices = new ArrayList<>();
            this.weights = new ArrayList<>();
        }
        public void addOption(int index, double weight){
            this.indices.add(index);
            this.weights.add(weight);
        }

        public int randomly() throws DeadEndException{
            double total = 0;
            if (indices.size()==0){
                throw new DeadEndException("Options::randomly : no available options");
            }
            for (double w: this.weights){
                total = total + w;
            }
            total = total * Math.random();
            for (int i=0; i<this.indices.size(); i++){
                if (total<this.weights.get(i)){
                    return this.indices.get(i);
                }
                total = total - this.weights.get(i);
            }
            return this.indices.get(this.indices.size()-1);
        }
    }

    public static class Stages {
        private List<List<String>> rules;
        private List<List<Integer>> weights;
        private List<List<Integer>> counts;
        private int tickets_current_stage;
        private int current_stage;
        public Stages(){
            this.tickets_current_stage = -1;
            this.current_stage = 0;
            this.rules = new ArrayList<>();
            this.weights = new ArrayList<>();
            this.counts = new ArrayList<>();
            List<String> rule = new ArrayList<>();
            List<Integer> weight = new ArrayList<>();
            List<Integer> count = new ArrayList<>();
<STAGES/>
        }

        public String get_next_rule() throws Exception{
            if (this.current_stage>=this.rules.size()){
                //System.out.println("Outta stages");
                return null;
            }
            if (this.tickets_current_stage==-1){
                List<Integer> count = this.counts.get(this.current_stage);
                this.tickets_current_stage = Rand.random(count.get(0), count.get(1));
            }
            //select the rule
            String result = Rand.random_from_list_weighted(this.rules.get(this.current_stage), this.weights.get(this.current_stage));
            this.tickets_current_stage -= 1;
            if (this.tickets_current_stage==0){
                this.current_stage++;
                this.tickets_current_stage = -1;
            }
            //System.out.println("Current stage: "+ this.current_stage +"   Total stage: "+this.rules.size()+ "   Tickets for this stage: "+this.tickets_current_stage+"   Rule: "+result);
            return result;
        }
    }

    public static List<Variable> packList(Variable... args){
        List<Variable> l = new ArrayList<>();
        for (Variable v: args){
            l.add(v);
        }
        return l;
    }

    public static List<String> rules;
    public static final int DEFAULT_MAX_REP = 8;
    public static SQLConnection connection = null;
    public static Statement stmt = null;
    public static Stages stages = null;
    public static Context context;

    public static void init(SQLConnection conn) throws Exception{
        if (Fuzzer.connection!=null){
            Fuzzer.stmt.close();
            Fuzzer.connection.close();
        }
        if (conn != null){
            Fuzzer.connection = conn;
            Fuzzer.stmt = conn.createStatement();
            Fuzzer.stmt.setQueryTimeout(10);
        }
        else if ("<JDBC/>".length()>0){
            //<JDBC_CLASS/>
            Fuzzer.connection = new SQLConnection(DriverManager.getConnection("<JDBC/>")); 
            Fuzzer.stmt = Fuzzer.connection.createStatement();
            Fuzzer.stmt.setQueryTimeout(10);
        }
        else {
            throw new Exception("Fuzzer.init :: Fuzzer must be initialized with either compile-time set JDBC string or runtime set SQLConnection, none found");
        }
        Fuzzer.rules = new ArrayList<>();
        Fuzzer.context = new Context(Fuzzer.stmt);
        Context.clear_rules();
        Fuzzer.stages = new Stages();
        // EXAMPLE:
        // Fuzzer.rules.add("createTable");
        // Context.add_rule("createTable");
<INIT_RULES/>
    }

    public static String fuzz_next() throws Exception{
        try {
            String rule = Fuzzer.stages.get_next_rule();
            if (rule==null){
                return null;
            }
            //System.out.println("-- Generating for rule: " + rule);
            return fuzz(rule);
        }
        catch (Exception e){
            Fuzzer.context.resetError();
            throw e;
        }
    }

    public static String remove_tagged(String s, String o, String c) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        
        while (i < s.length()) {
            int openingIndex = s.indexOf(o, i);
            
            // If no more opening tags, add the rest and finish
            if (openingIndex == -1) {
                result.append(s.substring(i));
                break;
            }
            
            // Add text before the opening tag
            result.append(s.substring(i, openingIndex));
            
            // Find the next closing tag
            int closingIndex = s.indexOf(c, openingIndex + o.length());
            
            // If no matching closing tag, add the rest and finish
            if (closingIndex == -1) {
                result.append(s.substring(i));
                break;
            }
            
            // Skip to after the closing tag
            i = closingIndex + c.length();
        }
        
        return result.toString();
    }

    public static int count_row(ResultSet resultSet) throws SQLException {
        // Store original position
        int res = 0;
        while (resultSet.next()){
            res++;
        }
        resultSet.close();
        return res;
    }


    public static String test_TLP(String stmt) throws Exception{
        String base = remove_tagged(stmt, "/*BEGIN_COND*/", "/*END_COND*/");
        stmt = stmt.replace("/*BEGIN_COND*/", "").replace("/*END_COND*/", "");
        String t = stmt.replace("/*BEGIN_PRED*/", "(").replace("/*END_PRED*/", ")");
        String f = stmt.replace("/*BEGIN_PRED*/", " NOT (").replace("/*END_PRED*/", ")");
        String n = stmt.replace("/*BEGIN_PRED*/", "(").replace("/*END_PRED*/", ") IS NULL ");
        Statement statement = Fuzzer.stmt;
        //statement.setQueryTimeout(30);
        int base_count = count_row(statement.executeQuery(base));
        base = base + " -- BASE QUERY: " + base_count + " rows\n";
        int t_count = count_row(statement.executeQuery(t));
        t = t + " -- TRUE QUERY: " + t_count + " rows\n";
        int f_count = count_row(statement.executeQuery(f));
        f = f + " -- FALSE QUERY: " + f_count + " rows\n";
        int n_count = count_row(statement.executeQuery(n));
        n = n + " -- NULL QUERY: " + n_count + " rows\n";
        String test = base + t + f + n;
        if (base_count!=t_count + f_count + n_count){
            throw new Exception("-- CARDINALITY MISMATCH!!!\n"+ test);
        }
        return test;
    }

    public static String fuzz_next_and_execute() throws Exception{
        
        try {
            long stime = System.currentTimeMillis();
            String stmt = fuzz_next();
            Fuzzer.generation_time += System.currentTimeMillis() - stime;
            if (stmt==null || stmt.contains("-- [Internal Exception, moving on]")){
                return stmt;
            }
            try {
                Statement statement = null;
                try {
                    statement = Fuzzer.stmt;
                    statement.execute(stmt);
                    if (stmt.contains("BEGIN_PRED")){
                        return test_TLP(stmt);
                    }
                }
                catch (Exception e){
                    throw new Exception(stmt+" -- ERROR! " + e.getMessage(), e);
                }
                Fuzzer.context.resetError();
                Fuzzer.turn_time += System.currentTimeMillis() - stime;
                return stmt;
            }
            catch (Exception se){
                //System.out.println(stmt + " -- ERROR!");
                if (Fuzzer.context.isExpectedError(se.getMessage())){
                    Fuzzer.context.resetError();
                    Fuzzer.turn_time += System.currentTimeMillis() - stime;
                    return "-- [Expected Error]:" + se.getMessage();
                }
                throw se;
            }
        }
        catch (UnavailableException | DeadEndException ue){
            Fuzzer.context.resetError();
            //ue.printStackTrace();
            return "-- [Unavailable Error]: don't worry if you see this occasionally";
        }
    }
    // at compile time, each standalone rule (without the fragment modifier)
    // will register itself here
    public static Buffer dispatch(Context ctx, String rule) throws Exception {
        // EXAMPLE:
        // if (rule.equals("createTable")){
        //     return createTable(ctx);
        // }
        long stime = System.currentTimeMillis();
<DISPATCH_RULES/>        
        throw new Exception("Fuzzer::dispatch : there is no rule registered with name "+rule);
    }

    // this is the entry point
    public static String fuzz(String rule) throws Exception{
        //This is a workaround
        //If the a previous statement failed on-entering
        //Its args might be carried over forever
        Fuzzer.context.args = null;
        Buffer buf = dispatch(Fuzzer.context, rule);
        return buf.toString();
    }

<RULES/>

}