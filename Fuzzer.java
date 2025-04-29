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
                            //wrap around when overflow
                            if (val>max){
                                val = min;
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
                 // STAGE: dropDatabase
             rule = new ArrayList<>();
             weight = new ArrayList<>();
             count = new ArrayList<>();
             rule.add("dropDatabase");
             weight.add(1);
             count.add(1);
             count.add(1);
             rules.add(rule);
             weights.add(weight);
             counts.add(count);
                 // STAGE: dropSchema
             rule = new ArrayList<>();
             weight = new ArrayList<>();
             count = new ArrayList<>();
             rule.add("dropSchema");
             weight.add(1);
             count.add(1);
             count.add(1);
             rules.add(rule);
             weights.add(weight);
             counts.add(count);
                 // STAGE: createDatabase
             rule = new ArrayList<>();
             weight = new ArrayList<>();
             count = new ArrayList<>();
             rule.add("createDatabase");
             weight.add(1);
             count.add(1);
             count.add(1);
             rules.add(rule);
             weights.add(weight);
             counts.add(count);
                 // STAGE: useDatabase
             rule = new ArrayList<>();
             weight = new ArrayList<>();
             count = new ArrayList<>();
             rule.add("useDatabase");
             weight.add(1);
             count.add(1);
             count.add(1);
             rules.add(rule);
             weights.add(weight);
             counts.add(count);
                 // STAGE: create
             rule = new ArrayList<>();
             weight = new ArrayList<>();
             count = new ArrayList<>();
             rule.add("createTable");
             weight.add(1);
             count.add(35);
             count.add(50);
             rules.add(rule);
             weights.add(weight);
             counts.add(count);
                 // STAGE: insert
             rule = new ArrayList<>();
             weight = new ArrayList<>();
             count = new ArrayList<>();
             rule.add("insertStatement");
             weight.add(1);
             count.add(500);
             count.add(550);
             rules.add(rule);
             weights.add(weight);
             counts.add(count);
                 // STAGE: update
             rule = new ArrayList<>();
             weight = new ArrayList<>();
             count = new ArrayList<>();
             rule.add("updateStatement");
             weight.add(1);
             count.add(150);
             count.add(200);
             rules.add(rule);
             weights.add(weight);
             counts.add(count);
                 // STAGE: mix
             rule = new ArrayList<>();
             weight = new ArrayList<>();
             count = new ArrayList<>();
             rule.add("alterTable");
             rule.add("createTable");
             rule.add("insertStatement");
             rule.add("updateStatement");
             rule.add("truncateTable");
             rule.add("createIndex");
             rule.add("selectStatement");
             weight.add(2);
             weight.add(2);
             weight.add(2);
             weight.add(2);
             weight.add(1);
             weight.add(2);
             weight.add(10);
             count.add(500);
             count.add(750);
             rules.add(rule);
             weights.add(weight);
             counts.add(count);

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
        Fuzzer.rules = new ArrayList<>();
        if (conn != null){
            Fuzzer.connection = conn;
            Fuzzer.stmt = conn.createStatement();
            Fuzzer.stmt.setQueryTimeout(10);
        }
        else if ("jdbc:mysql://localhost:3306/?user=sqlancer&password=sqlancer".length()>0){
            Class.forName("com.mysql.cj.jdbc.Driver");

            Fuzzer.connection = new SQLConnection(DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=sqlancer&password=sqlancer")); 
            Fuzzer.stmt = Fuzzer.connection.createStatement();
            Fuzzer.stmt.setQueryTimeout(10);
        }
        else {
            throw new Exception("Fuzzer.init :: Fuzzer must be initialized with either compile-time set JDBC string or runtime set SQLConnection, none found");
        }
        Fuzzer.context = new Context(Fuzzer.stmt);
        Fuzzer.stages = new Stages();
        // EXAMPLE:
        // Fuzzer.rules.add("createTable");
        // Context.add_rule("createTable");
        Fuzzer.rules.add("alterTable");
        Context.add_rule("alterTable");
        Fuzzer.rules.add("alterSpecification");
        Context.add_rule("alterSpecification");
        Fuzzer.rules.add("columnDefinition");
        Context.add_rule("columnDefinition");
        Fuzzer.rules.add("dropDatabase");
        Context.add_rule("dropDatabase");
        Fuzzer.rules.add("dropSchema");
        Context.add_rule("dropSchema");
        Fuzzer.rules.add("createDatabase");
        Context.add_rule("createDatabase");
        Fuzzer.rules.add("useDatabase");
        Context.add_rule("useDatabase");
        Fuzzer.rules.add("createTable");
        Context.add_rule("createTable");
        Fuzzer.rules.add("createIndex");
        Context.add_rule("createIndex");
        Fuzzer.rules.add("truncateTable");
        Context.add_rule("truncateTable");
        Fuzzer.rules.add("insertStatement");
        Context.add_rule("insertStatement");
        Fuzzer.rules.add("updateStatement");
        Context.add_rule("updateStatement");
        Fuzzer.rules.add("expression");
        Context.add_rule("expression");
        Fuzzer.rules.add("selectStatement");
        Context.add_rule("selectStatement");
        Fuzzer.rules.add("where_predicate");
        Context.add_rule("where_predicate");
        Fuzzer.rules.add("predicate");
        Context.add_rule("predicate");
        Fuzzer.rules.add("comparison");
        Context.add_rule("comparison");
        Fuzzer.rules.add("waitNowaitClause");
        Context.add_rule("waitNowaitClause");
        Fuzzer.rules.add("abs");
        Context.add_rule("abs");
        Fuzzer.rules.add("bit_count");
        Context.add_rule("bit_count");
        Fuzzer.rules.add("if_func");
        Context.add_rule("if_func");
        Fuzzer.rules.add("ifnull");
        Context.add_rule("ifnull");
        Fuzzer.rules.add("greatest");
        Context.add_rule("greatest");
        Fuzzer.rules.add("least");
        Context.add_rule("least");
        Fuzzer.rules.add("strcmp");
        Context.add_rule("strcmp");
        Fuzzer.rules.add("substr");
        Context.add_rule("substr");
        Fuzzer.rules.add("substring");
        Context.add_rule("substring");
        Fuzzer.rules.add("trim");
        Context.add_rule("trim");
        Fuzzer.rules.add("lcase");
        Context.add_rule("lcase");
        Fuzzer.rules.add("ucase");
        Context.add_rule("ucase");
        Fuzzer.rules.add("space");
        Context.add_rule("space");
        Fuzzer.rules.add("last_insert_id");
        Context.add_rule("last_insert_id");
        Fuzzer.rules.add("acos");
        Context.add_rule("acos");
        Fuzzer.rules.add("asin");
        Context.add_rule("asin");
        Fuzzer.rules.add("atan");
        Context.add_rule("atan");
        Fuzzer.rules.add("atan2");
        Context.add_rule("atan2");
        Fuzzer.rules.add("avg");
        Context.add_rule("avg");
        Fuzzer.rules.add("ceil");
        Context.add_rule("ceil");
        Fuzzer.rules.add("concat");
        Context.add_rule("concat");
        Fuzzer.rules.add("cos");
        Context.add_rule("cos");
        Fuzzer.rules.add("cot");
        Context.add_rule("cot");
        Fuzzer.rules.add("crc32");
        Context.add_rule("crc32");
        Fuzzer.rules.add("degrees");
        Context.add_rule("degrees");
        Fuzzer.rules.add("exp");
        Context.add_rule("exp");
        Fuzzer.rules.add("left");
        Context.add_rule("left");
        Fuzzer.rules.add("ln");
        Context.add_rule("ln");
        Fuzzer.rules.add("log");
        Context.add_rule("log");
        Fuzzer.rules.add("log10");
        Context.add_rule("log10");
        Fuzzer.rules.add("log2");
        Context.add_rule("log2");
        Fuzzer.rules.add("lower");
        Context.add_rule("lower");
        Fuzzer.rules.add("lpad");
        Context.add_rule("lpad");
        Fuzzer.rules.add("md5");
        Context.add_rule("md5");
        Fuzzer.rules.add("mod");
        Context.add_rule("mod");
        Fuzzer.rules.add("pi");
        Context.add_rule("pi");
        Fuzzer.rules.add("pow");
        Context.add_rule("pow");
        Fuzzer.rules.add("radians");
        Context.add_rule("radians");
        Fuzzer.rules.add("rand");
        Context.add_rule("rand");
        Fuzzer.rules.add("replace");
        Context.add_rule("replace");
        Fuzzer.rules.add("right");
        Context.add_rule("right");
        Fuzzer.rules.add("round");
        Context.add_rule("round");
        Fuzzer.rules.add("sign");
        Context.add_rule("sign");
        Fuzzer.rules.add("sin");
        Context.add_rule("sin");
        Fuzzer.rules.add("sqrt");
        Context.add_rule("sqrt");
        Fuzzer.rules.add("tan");
        Context.add_rule("tan");
        Fuzzer.rules.add("truncate");
        Context.add_rule("truncate");
        Fuzzer.rules.add("upper");
        Context.add_rule("upper");
        Fuzzer.rules.add("numerical_operation");
        Context.add_rule("numerical_operation");
        Fuzzer.rules.add("double_expr");
        Context.add_rule("double_expr");
        Fuzzer.rules.add("double_val");
        Context.add_rule("double_val");
        Fuzzer.rules.add("int_expr");
        Context.add_rule("int_expr");
        Fuzzer.rules.add("int_val");
        Context.add_rule("int_val");
        Fuzzer.rules.add("text_expr");
        Context.add_rule("text_expr");
        Fuzzer.rules.add("text_val");
        Context.add_rule("text_val");
        Fuzzer.rules.add("db");
        Context.add_rule("db");
        Fuzzer.rules.add("table");
        Context.add_rule("table");
        Fuzzer.rules.add("column");
        Context.add_rule("column");
        Fuzzer.rules.add("index");
        Context.add_rule("index");
        Fuzzer.rules.add("ifNotExists");
        Context.add_rule("ifNotExists");
        Fuzzer.rules.add("ifExists");
        Context.add_rule("ifExists");
        Fuzzer.rules.add("ADD");
        Context.add_rule("ADD");
        Fuzzer.rules.add("ALGORITHM");
        Context.add_rule("ALGORITHM");
        Fuzzer.rules.add("ALL");
        Context.add_rule("ALL");
        Fuzzer.rules.add("ALTER");
        Context.add_rule("ALTER");
        Fuzzer.rules.add("AS");
        Context.add_rule("AS");
        Fuzzer.rules.add("BY");
        Context.add_rule("BY");
        Fuzzer.rules.add("COLUMN");
        Context.add_rule("COLUMN");
        Fuzzer.rules.add("COPY");
        Context.add_rule("COPY");
        Fuzzer.rules.add("CREATE");
        Context.add_rule("CREATE");
        Fuzzer.rules.add("DATABASE");
        Context.add_rule("DATABASE");
        Fuzzer.rules.add("DEFAULT");
        Context.add_rule("DEFAULT");
        Fuzzer.rules.add("DELAYED");
        Context.add_rule("DELAYED");
        Fuzzer.rules.add("DISTINCT");
        Context.add_rule("DISTINCT");
        Fuzzer.rules.add("DOUBLE");
        Context.add_rule("DOUBLE");
        Fuzzer.rules.add("DROP");
        Context.add_rule("DROP");
        Fuzzer.rules.add("EXCLUSIVE");
        Context.add_rule("EXCLUSIVE");
        Fuzzer.rules.add("EXISTS");
        Context.add_rule("EXISTS");
        Fuzzer.rules.add("FIRST");
        Context.add_rule("FIRST");
        Fuzzer.rules.add("FLOAT");
        Context.add_rule("FLOAT");
        Fuzzer.rules.add("FROM");
        Context.add_rule("FROM");
        Fuzzer.rules.add("FULLTEXT");
        Context.add_rule("FULLTEXT");
        Fuzzer.rules.add("HASH");
        Context.add_rule("HASH");
        Fuzzer.rules.add("HIGH_PRIORITY");
        Context.add_rule("HIGH_PRIORITY");
        Fuzzer.rules.add("IF");
        Context.add_rule("IF");
        Fuzzer.rules.add("IGNORE");
        Context.add_rule("IGNORE");
        Fuzzer.rules.add("IN");
        Context.add_rule("IN");
        Fuzzer.rules.add("INDEX");
        Context.add_rule("INDEX");
        Fuzzer.rules.add("INPLACE");
        Context.add_rule("INPLACE");
        Fuzzer.rules.add("INSERT");
        Context.add_rule("INSERT");
        Fuzzer.rules.add("INT");
        Context.add_rule("INT");
        Fuzzer.rules.add("INTO");
        Context.add_rule("INTO");
        Fuzzer.rules.add("JOIN");
        Context.add_rule("JOIN");
        Fuzzer.rules.add("KEY");
        Context.add_rule("KEY");
        Fuzzer.rules.add("LIKE");
        Context.add_rule("LIKE");
        Fuzzer.rules.add("LINEAR");
        Context.add_rule("LINEAR");
        Fuzzer.rules.add("LOCK");
        Context.add_rule("LOCK");
        Fuzzer.rules.add("LOW_PRIORITY");
        Context.add_rule("LOW_PRIORITY");
        Fuzzer.rules.add("NONE");
        Context.add_rule("NONE");
        Fuzzer.rules.add("NOT");
        Context.add_rule("NOT");
        Fuzzer.rules.add("NOWAIT");
        Context.add_rule("NOWAIT");
        Fuzzer.rules.add("NULL");
        Context.add_rule("NULL");
        Fuzzer.rules.add("OFFLINE");
        Context.add_rule("OFFLINE");
        Fuzzer.rules.add("ON");
        Context.add_rule("ON");
        Fuzzer.rules.add("ONLINE");
        Context.add_rule("ONLINE");
        Fuzzer.rules.add("PARTITION");
        Context.add_rule("PARTITION");
        Fuzzer.rules.add("PRIMARY");
        Context.add_rule("PRIMARY");
        Fuzzer.rules.add("RENAME");
        Context.add_rule("RENAME");
        Fuzzer.rules.add("REPLACE");
        Context.add_rule("REPLACE");
        Fuzzer.rules.add("SCHEMA");
        Context.add_rule("SCHEMA");
        Fuzzer.rules.add("SELECT");
        Context.add_rule("SELECT");
        Fuzzer.rules.add("SET");
        Context.add_rule("SET");
        Fuzzer.rules.add("SHARED");
        Context.add_rule("SHARED");
        Fuzzer.rules.add("SPATIAL");
        Context.add_rule("SPATIAL");
        Fuzzer.rules.add("TABLE");
        Context.add_rule("TABLE");
        Fuzzer.rules.add("TEMPORARY");
        Context.add_rule("TEMPORARY");
        Fuzzer.rules.add("TEXT");
        Context.add_rule("TEXT");
        Fuzzer.rules.add("TO");
        Context.add_rule("TO");
        Fuzzer.rules.add("TRUNCATE");
        Context.add_rule("TRUNCATE");
        Fuzzer.rules.add("UNIQUE");
        Context.add_rule("UNIQUE");
        Fuzzer.rules.add("UPDATE");
        Context.add_rule("UPDATE");
        Fuzzer.rules.add("USE");
        Context.add_rule("USE");
        Fuzzer.rules.add("VALUES");
        Context.add_rule("VALUES");
        Fuzzer.rules.add("VIEW");
        Context.add_rule("VIEW");
        Fuzzer.rules.add("WAIT");
        Context.add_rule("WAIT");
        Fuzzer.rules.add("WHERE");
        Context.add_rule("WHERE");
        Fuzzer.rules.add("STUB");
        Context.add_rule("STUB");
        Fuzzer.rules.add("LB");
        Context.add_rule("LB");
        Fuzzer.rules.add("RB");
        Context.add_rule("RB");
        Fuzzer.rules.add("LT");
        Context.add_rule("LT");
        Fuzzer.rules.add("GT");
        Context.add_rule("GT");
        Fuzzer.rules.add("EQ");
        Context.add_rule("EQ");
        Fuzzer.rules.add("SC");
        Context.add_rule("SC");
        Fuzzer.rules.add("US");
        Context.add_rule("US");
        Fuzzer.rules.add("DS");
        Context.add_rule("DS");
        Fuzzer.rules.add("ASTERISK");
        Context.add_rule("ASTERISK");
        Fuzzer.rules.add("DQ");
        Context.add_rule("DQ");
        Fuzzer.rules.add("COMMA");
        Context.add_rule("COMMA");
        Fuzzer.rules.add("DOT");
        Context.add_rule("DOT");
        Fuzzer.rules.add("DIGIT");
        Context.add_rule("DIGIT");
        Fuzzer.rules.add("SPACE");
        Context.add_rule("SPACE");
        Fuzzer.rules.add("NL");
        Context.add_rule("NL");
        Fuzzer.rules.add("A");
        Context.add_rule("A");
        Fuzzer.rules.add("B");
        Context.add_rule("B");
        Fuzzer.rules.add("C");
        Context.add_rule("C");
        Fuzzer.rules.add("D");
        Context.add_rule("D");
        Fuzzer.rules.add("E");
        Context.add_rule("E");
        Fuzzer.rules.add("F");
        Context.add_rule("F");
        Fuzzer.rules.add("G");
        Context.add_rule("G");
        Fuzzer.rules.add("H");
        Context.add_rule("H");
        Fuzzer.rules.add("I");
        Context.add_rule("I");
        Fuzzer.rules.add("J");
        Context.add_rule("J");
        Fuzzer.rules.add("K");
        Context.add_rule("K");
        Fuzzer.rules.add("L");
        Context.add_rule("L");
        Fuzzer.rules.add("M");
        Context.add_rule("M");
        Fuzzer.rules.add("N");
        Context.add_rule("N");
        Fuzzer.rules.add("O");
        Context.add_rule("O");
        Fuzzer.rules.add("P");
        Context.add_rule("P");
        Fuzzer.rules.add("Q");
        Context.add_rule("Q");
        Fuzzer.rules.add("R");
        Context.add_rule("R");
        Fuzzer.rules.add("S");
        Context.add_rule("S");
        Fuzzer.rules.add("T");
        Context.add_rule("T");
        Fuzzer.rules.add("U");
        Context.add_rule("U");
        Fuzzer.rules.add("V");
        Context.add_rule("V");
        Fuzzer.rules.add("W");
        Context.add_rule("W");
        Fuzzer.rules.add("X");
        Context.add_rule("X");
        Fuzzer.rules.add("Y");
        Context.add_rule("Y");
        Fuzzer.rules.add("Z");
        Context.add_rule("Z");
        Fuzzer.rules.add("CH");
        Context.add_rule("CH");

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
                }
                catch (Exception e){
                    throw new Exception(stmt+" -- ERROR! " + e.getMessage(), e);
                }
                Fuzzer.context.resetError();
                if (stmt.contains("BEGIN_PRED")){
                    return test_TLP(stmt);
                }
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
        if (rule.equals("alterTable")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return alterTable(ctx);
        }
        if (rule.equals("alterSpecification")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return alterSpecification(ctx);
        }
        if (rule.equals("columnDefinition")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return columnDefinition(ctx);
        }
        if (rule.equals("dropDatabase")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return dropDatabase(ctx);
        }
        if (rule.equals("dropSchema")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return dropSchema(ctx);
        }
        if (rule.equals("createDatabase")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return createDatabase(ctx);
        }
        if (rule.equals("useDatabase")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return useDatabase(ctx);
        }
        if (rule.equals("createTable")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return createTable(ctx);
        }
        if (rule.equals("createIndex")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return createIndex(ctx);
        }
        if (rule.equals("truncateTable")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return truncateTable(ctx);
        }
        if (rule.equals("insertStatement")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return insertStatement(ctx);
        }
        if (rule.equals("updateStatement")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return updateStatement(ctx);
        }
        if (rule.equals("expression")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return expression(ctx);
        }
        if (rule.equals("selectStatement")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return selectStatement(ctx);
        }
        if (rule.equals("where_predicate")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return where_predicate(ctx);
        }
        if (rule.equals("predicate")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return predicate(ctx);
        }
        if (rule.equals("comparison")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return comparison(ctx);
        }
        if (rule.equals("waitNowaitClause")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return waitNowaitClause(ctx);
        }
        if (rule.equals("abs")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return abs(ctx);
        }
        if (rule.equals("bit_count")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return bit_count(ctx);
        }
        if (rule.equals("if_func")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return if_func(ctx);
        }
        if (rule.equals("ifnull")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ifnull(ctx);
        }
        if (rule.equals("greatest")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return greatest(ctx);
        }
        if (rule.equals("least")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return least(ctx);
        }
        if (rule.equals("strcmp")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return strcmp(ctx);
        }
        if (rule.equals("substr")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return substr(ctx);
        }
        if (rule.equals("substring")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return substring(ctx);
        }
        if (rule.equals("trim")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return trim(ctx);
        }
        if (rule.equals("lcase")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return lcase(ctx);
        }
        if (rule.equals("ucase")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ucase(ctx);
        }
        if (rule.equals("space")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return space(ctx);
        }
        if (rule.equals("last_insert_id")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return last_insert_id(ctx);
        }
        if (rule.equals("acos")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return acos(ctx);
        }
        if (rule.equals("asin")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return asin(ctx);
        }
        if (rule.equals("atan")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return atan(ctx);
        }
        if (rule.equals("atan2")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return atan2(ctx);
        }
        if (rule.equals("avg")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return avg(ctx);
        }
        if (rule.equals("ceil")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ceil(ctx);
        }
        if (rule.equals("concat")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return concat(ctx);
        }
        if (rule.equals("cos")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return cos(ctx);
        }
        if (rule.equals("cot")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return cot(ctx);
        }
        if (rule.equals("crc32")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return crc32(ctx);
        }
        if (rule.equals("degrees")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return degrees(ctx);
        }
        if (rule.equals("exp")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return exp(ctx);
        }
        if (rule.equals("left")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return left(ctx);
        }
        if (rule.equals("ln")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ln(ctx);
        }
        if (rule.equals("log")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return log(ctx);
        }
        if (rule.equals("log10")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return log10(ctx);
        }
        if (rule.equals("log2")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return log2(ctx);
        }
        if (rule.equals("lower")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return lower(ctx);
        }
        if (rule.equals("lpad")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return lpad(ctx);
        }
        if (rule.equals("md5")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return md5(ctx);
        }
        if (rule.equals("mod")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return mod(ctx);
        }
        if (rule.equals("pi")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return pi(ctx);
        }
        if (rule.equals("pow")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return pow(ctx);
        }
        if (rule.equals("radians")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return radians(ctx);
        }
        if (rule.equals("rand")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return rand(ctx);
        }
        if (rule.equals("replace")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return replace(ctx);
        }
        if (rule.equals("right")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return right(ctx);
        }
        if (rule.equals("round")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return round(ctx);
        }
        if (rule.equals("sign")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return sign(ctx);
        }
        if (rule.equals("sin")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return sin(ctx);
        }
        if (rule.equals("sqrt")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return sqrt(ctx);
        }
        if (rule.equals("tan")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return tan(ctx);
        }
        if (rule.equals("truncate")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return truncate(ctx);
        }
        if (rule.equals("upper")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return upper(ctx);
        }
        if (rule.equals("numerical_operation")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return numerical_operation(ctx);
        }
        if (rule.equals("double_expr")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return double_expr(ctx);
        }
        if (rule.equals("double_val")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return double_val(ctx);
        }
        if (rule.equals("int_expr")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return int_expr(ctx);
        }
        if (rule.equals("int_val")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return int_val(ctx);
        }
        if (rule.equals("text_expr")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return text_expr(ctx);
        }
        if (rule.equals("text_val")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return text_val(ctx);
        }
        if (rule.equals("db")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return db(ctx);
        }
        if (rule.equals("table")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return table(ctx);
        }
        if (rule.equals("column")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return column(ctx);
        }
        if (rule.equals("index")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return index(ctx);
        }
        if (rule.equals("ifNotExists")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ifNotExists(ctx);
        }
        if (rule.equals("ifExists")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ifExists(ctx);
        }
        if (rule.equals("ADD")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ADD(ctx);
        }
        if (rule.equals("ALGORITHM")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ALGORITHM(ctx);
        }
        if (rule.equals("ALL")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ALL(ctx);
        }
        if (rule.equals("ALTER")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ALTER(ctx);
        }
        if (rule.equals("AS")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return AS(ctx);
        }
        if (rule.equals("BY")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return BY(ctx);
        }
        if (rule.equals("COLUMN")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return COLUMN(ctx);
        }
        if (rule.equals("COPY")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return COPY(ctx);
        }
        if (rule.equals("CREATE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return CREATE(ctx);
        }
        if (rule.equals("DATABASE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return DATABASE(ctx);
        }
        if (rule.equals("DEFAULT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return DEFAULT(ctx);
        }
        if (rule.equals("DELAYED")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return DELAYED(ctx);
        }
        if (rule.equals("DISTINCT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return DISTINCT(ctx);
        }
        if (rule.equals("DOUBLE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return DOUBLE(ctx);
        }
        if (rule.equals("DROP")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return DROP(ctx);
        }
        if (rule.equals("EXCLUSIVE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return EXCLUSIVE(ctx);
        }
        if (rule.equals("EXISTS")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return EXISTS(ctx);
        }
        if (rule.equals("FIRST")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return FIRST(ctx);
        }
        if (rule.equals("FLOAT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return FLOAT(ctx);
        }
        if (rule.equals("FROM")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return FROM(ctx);
        }
        if (rule.equals("FULLTEXT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return FULLTEXT(ctx);
        }
        if (rule.equals("HASH")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return HASH(ctx);
        }
        if (rule.equals("HIGH_PRIORITY")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return HIGH_PRIORITY(ctx);
        }
        if (rule.equals("IF")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return IF(ctx);
        }
        if (rule.equals("IGNORE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return IGNORE(ctx);
        }
        if (rule.equals("IN")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return IN(ctx);
        }
        if (rule.equals("INDEX")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return INDEX(ctx);
        }
        if (rule.equals("INPLACE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return INPLACE(ctx);
        }
        if (rule.equals("INSERT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return INSERT(ctx);
        }
        if (rule.equals("INT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return INT(ctx);
        }
        if (rule.equals("INTO")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return INTO(ctx);
        }
        if (rule.equals("JOIN")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return JOIN(ctx);
        }
        if (rule.equals("KEY")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return KEY(ctx);
        }
        if (rule.equals("LIKE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return LIKE(ctx);
        }
        if (rule.equals("LINEAR")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return LINEAR(ctx);
        }
        if (rule.equals("LOCK")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return LOCK(ctx);
        }
        if (rule.equals("LOW_PRIORITY")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return LOW_PRIORITY(ctx);
        }
        if (rule.equals("NONE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return NONE(ctx);
        }
        if (rule.equals("NOT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return NOT(ctx);
        }
        if (rule.equals("NOWAIT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return NOWAIT(ctx);
        }
        if (rule.equals("NULL")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return NULL(ctx);
        }
        if (rule.equals("OFFLINE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return OFFLINE(ctx);
        }
        if (rule.equals("ON")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ON(ctx);
        }
        if (rule.equals("ONLINE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ONLINE(ctx);
        }
        if (rule.equals("PARTITION")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return PARTITION(ctx);
        }
        if (rule.equals("PRIMARY")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return PRIMARY(ctx);
        }
        if (rule.equals("RENAME")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return RENAME(ctx);
        }
        if (rule.equals("REPLACE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return REPLACE(ctx);
        }
        if (rule.equals("SCHEMA")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return SCHEMA(ctx);
        }
        if (rule.equals("SELECT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return SELECT(ctx);
        }
        if (rule.equals("SET")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return SET(ctx);
        }
        if (rule.equals("SHARED")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return SHARED(ctx);
        }
        if (rule.equals("SPATIAL")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return SPATIAL(ctx);
        }
        if (rule.equals("TABLE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return TABLE(ctx);
        }
        if (rule.equals("TEMPORARY")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return TEMPORARY(ctx);
        }
        if (rule.equals("TEXT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return TEXT(ctx);
        }
        if (rule.equals("TO")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return TO(ctx);
        }
        if (rule.equals("TRUNCATE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return TRUNCATE(ctx);
        }
        if (rule.equals("UNIQUE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return UNIQUE(ctx);
        }
        if (rule.equals("UPDATE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return UPDATE(ctx);
        }
        if (rule.equals("USE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return USE(ctx);
        }
        if (rule.equals("VALUES")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return VALUES(ctx);
        }
        if (rule.equals("VIEW")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return VIEW(ctx);
        }
        if (rule.equals("WAIT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return WAIT(ctx);
        }
        if (rule.equals("WHERE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return WHERE(ctx);
        }
        if (rule.equals("STUB")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return STUB(ctx);
        }
        if (rule.equals("LB")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return LB(ctx);
        }
        if (rule.equals("RB")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return RB(ctx);
        }
        if (rule.equals("LT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return LT(ctx);
        }
        if (rule.equals("GT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return GT(ctx);
        }
        if (rule.equals("EQ")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return EQ(ctx);
        }
        if (rule.equals("SC")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return SC(ctx);
        }
        if (rule.equals("US")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return US(ctx);
        }
        if (rule.equals("DS")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return DS(ctx);
        }
        if (rule.equals("ASTERISK")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return ASTERISK(ctx);
        }
        if (rule.equals("DQ")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return DQ(ctx);
        }
        if (rule.equals("COMMA")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return COMMA(ctx);
        }
        if (rule.equals("DOT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return DOT(ctx);
        }
        if (rule.equals("DIGIT")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return DIGIT(ctx);
        }
        if (rule.equals("SPACE")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return SPACE(ctx);
        }
        if (rule.equals("NL")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return NL(ctx);
        }
        if (rule.equals("A")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return A(ctx);
        }
        if (rule.equals("B")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return B(ctx);
        }
        if (rule.equals("C")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return C(ctx);
        }
        if (rule.equals("D")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return D(ctx);
        }
        if (rule.equals("E")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return E(ctx);
        }
        if (rule.equals("F")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return F(ctx);
        }
        if (rule.equals("G")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return G(ctx);
        }
        if (rule.equals("H")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return H(ctx);
        }
        if (rule.equals("I")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return I(ctx);
        }
        if (rule.equals("J")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return J(ctx);
        }
        if (rule.equals("K")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return K(ctx);
        }
        if (rule.equals("L")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return L(ctx);
        }
        if (rule.equals("M")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return M(ctx);
        }
        if (rule.equals("N")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return N(ctx);
        }
        if (rule.equals("O")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return O(ctx);
        }
        if (rule.equals("P")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return P(ctx);
        }
        if (rule.equals("Q")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return Q(ctx);
        }
        if (rule.equals("R")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return R(ctx);
        }
        if (rule.equals("S")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return S(ctx);
        }
        if (rule.equals("T")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return T(ctx);
        }
        if (rule.equals("U")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return U(ctx);
        }
        if (rule.equals("V")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return V(ctx);
        }
        if (rule.equals("W")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return W(ctx);
        }
        if (rule.equals("X")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return X(ctx);
        }
        if (rule.equals("Y")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return Y(ctx);
        }
        if (rule.equals("Z")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return Z(ctx);
        }
        if (rule.equals("CH")){
            Fuzzer.dispatch_time +=  System.currentTimeMillis() - stime;
            return CH(ctx);
        }
        
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

    // The following lines are generated from line 29
    public static Buffer node23(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(1),Variable.factory(6),Variable.factory(","),Variable.factory(75)));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 29
            buf.add(ctx.getSymbol(buf, "alterSpecification", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 28
    public static Buffer node2(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 28
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("exist"))));

        // The following lines are generated from line 28
        buf.add(ctx.getSymbol(buf, "ALTER", new ArrayList<>()));

        // The following lines are generated from line 28
        buf.add(ctx.getSymbol(buf, "TABLE", new ArrayList<>()));

        // The following lines are generated from line 28
        buf.add(ctx.eval(ctx.getSymbol(buf, "t", new ArrayList<>()), "=", ctx.getSymbol(buf, "table", new ArrayList<>()).getAttr("any", new ArrayList<>())));

        // The following lines are generated from line 28
        ctx.eval(ctx.getSymbol(buf, "c", new ArrayList<>()), "=", ctx.getSymbol(buf, "column", packList(ctx.getSymbol(buf, "t", new ArrayList<>()))));

        // The following lines are generated from line 29
        buf.add(node23(ctx));

        // The following lines are generated from line 29
        ctx.eval(ctx.getSymbol(buf, "SCHEMA_CHANGE", new ArrayList<>()), "=", Variable.factory(1));

        // The following lines are generated from line 29
        buf.add(ctx.getSymbol(buf, "SC", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer alterTable(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 28
        buf.add(node2(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 34
    public static Buffer node47(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 34
            buf.add(ctx.getSymbol(buf, "COLUMN", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 34
    public static Buffer node55(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 34
            buf.add(ctx.getSymbol(buf, "FIRST", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 34
    public static Buffer node44(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 34
        buf.add(ctx.getSymbol(buf, "ADD", new ArrayList<>()));

        // The following lines are generated from line 34
        buf.add(node47(ctx));

        // The following lines are generated from line 34
        buf.add(ctx.getSymbol(buf, "new", packList(Variable.factory("column"))));

        // The following lines are generated from line 34
        buf.add(ctx.getSymbol(buf, "columnDefinition", new ArrayList<>()));

        // The following lines are generated from line 34
        buf.add(node55(ctx));

        return buf;
    }
    // The following lines are generated from line 35
    public static Buffer node61(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 35
            buf.add(ctx.getSymbol(buf, "COLUMN", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 35
    public static Buffer node67(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 35
        buf.add(ctx.getSymbol(buf, "new", packList(Variable.factory("column"))));

        // The following lines are generated from line 35
        buf.add(ctx.getSymbol(buf, "columnDefinition", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 35
    public static Buffer node65(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(1),Variable.factory(3),Variable.factory(","),Variable.factory(75)));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 35
            buf.add(node67(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 35
    public static Buffer node58(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 35
        buf.add(ctx.getSymbol(buf, "ADD", new ArrayList<>()));

        // The following lines are generated from line 35
        buf.add(node61(ctx));

        // The following lines are generated from line 35
        buf.add(Variable.factory("("));

        // The following lines are generated from line 35
        buf.add(node65(ctx));

        // The following lines are generated from line 35
        buf.add(Variable.factory(")"));

        return buf;
    }
    // The following lines are generated from line 36
    public static Buffer node83(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 36
            buf.add(ctx.getSymbol(buf, "COLUMN", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 36
    public static Buffer node80(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 36
        buf.add(ctx.getSymbol(buf, "DROP", new ArrayList<>()));

        // The following lines are generated from line 36
        buf.add(node83(ctx));

        // The following lines are generated from line 36
        buf.add(ctx.getSymbol(buf, "c", new ArrayList<>()).getAttr("unique_any", new ArrayList<>()));

        // The following lines are generated from line 36
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("delete all"),Variable.factory("has a partitioning function dependency"))));

        return buf;
    }
    // The following lines are generated from line 37
    public static Buffer node93(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 37
        buf.add(ctx.getSymbol(buf, "DROP", new ArrayList<>()));

        // The following lines are generated from line 37
        buf.add(ctx.getSymbol(buf, "PRIMARY", new ArrayList<>()));

        // The following lines are generated from line 37
        buf.add(ctx.getSymbol(buf, "KEY", new ArrayList<>()));

        // The following lines are generated from line 37
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("primary"))));

        return buf;
    }
    // The following lines are generated from line 38
    public static Buffer node106(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 38
            buf.add(ctx.getSymbol(buf, "TO", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 38
            buf.add(ctx.getSymbol(buf, "AS", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 38
    public static Buffer node103(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 38
        buf.add(ctx.getSymbol(buf, "RENAME", new ArrayList<>()));

        // The following lines are generated from line 38
        buf.add(node106(ctx));

        // The following lines are generated from line 38
        buf.add(ctx.getSymbol(buf, "new", packList(Variable.factory("table"))));

        return buf;
    }
    // The following lines are generated from line 39
    public static Buffer node116(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 39
        buf.add(ctx.getSymbol(buf, "RENAME", new ArrayList<>()));

        // The following lines are generated from line 39
        buf.add(ctx.getSymbol(buf, "COLUMN", new ArrayList<>()));

        // The following lines are generated from line 39
        buf.add(ctx.getSymbol(buf, "c", new ArrayList<>()).getAttr("unique_any", new ArrayList<>()));

        // The following lines are generated from line 39
        buf.add(ctx.getSymbol(buf, "TO", new ArrayList<>()));

        // The following lines are generated from line 39
        buf.add(ctx.getSymbol(buf, "new", packList(Variable.factory("column"))));

        // The following lines are generated from line 39
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("has a partitioning function dependency and cannot be dropped or renamed"))));

        return buf;
    }
    // The following lines are generated from line 40
    public static Buffer node136(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 25.0);
        opt.addOption(1, 25.0);
        opt.addOption(2, 25.0);
        opt.addOption(3, 25.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 40
            buf.add(Variable.factory("INSTANT"));
        }
        if(index==1){
            // The following lines are generated from line 40
            buf.add(Variable.factory("INPLACE"));
        }
        if(index==2){
            // The following lines are generated from line 40
            buf.add(Variable.factory("COPY"));
        }
        if(index==3){
            // The following lines are generated from line 40
            buf.add(Variable.factory("DEFAULT"));
        }
        return buf;
    }
    // The following lines are generated from line 40
    public static Buffer node132(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 40
        buf.add(ctx.getSymbol(buf, "ALGORITHM", new ArrayList<>()));

        // The following lines are generated from line 40
        buf.add(Variable.factory("="));

        // The following lines are generated from line 40
        buf.add(node136(ctx));

        // The following lines are generated from line 40
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("is not supported"))));

        return buf;
    }
    // The following lines are generated from line 34
    public static Buffer node43(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 14.285714285714286);
        opt.addOption(1, 14.285714285714286);
        opt.addOption(2, 14.285714285714286);
        opt.addOption(3, 14.285714285714286);
        opt.addOption(4, 14.285714285714286);
        opt.addOption(5, 14.285714285714286);
        opt.addOption(6, 14.285714285714286);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 34
            buf.add(node44(ctx));
        }
        if(index==1){
            // The following lines are generated from line 35
            buf.add(node58(ctx));
        }
        if(index==2){
            // The following lines are generated from line 36
            buf.add(node80(ctx));
        }
        if(index==3){
            // The following lines are generated from line 37
            buf.add(node93(ctx));
        }
        if(index==4){
            // The following lines are generated from line 38
            buf.add(node103(ctx));
        }
        if(index==5){
            // The following lines are generated from line 39
            buf.add(node116(ctx));
        }
        if(index==6){
            // The following lines are generated from line 40
            buf.add(node132(ctx));
        }
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer alterSpecification(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 34
        buf.add(node43(ctx));

        return buf;
    }
    // The following lines are generated from line 45
    public static Buffer node149(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 33.333333333333336);
        opt.addOption(1, 33.333333333333336);
        opt.addOption(2, 33.333333333333336);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 45
            buf.add(Variable.factory(" DOUBLE "));
        }
        if(index==1){
            // The following lines are generated from line 45
            buf.add(Variable.factory(" INT "));
        }
        if(index==2){
            // The following lines are generated from line 45
            buf.add(Variable.factory(" TEXT "));
        }
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer columnDefinition(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 45
        buf.add(node149(ctx));

        return buf;
    }
    // The following lines are generated from line 49
    public static Buffer node158(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 49
        buf.add(ctx.getSymbol(buf, "DROP", new ArrayList<>()));

        // The following lines are generated from line 49
        buf.add(ctx.getSymbol(buf, "DATABASE", new ArrayList<>()));

        // The following lines are generated from line 49
        buf.add(ctx.getSymbol(buf, "ifExists", new ArrayList<>()));

        // The following lines are generated from line 49
        buf.add(ctx.eval(ctx.getSymbol(buf, "DB", new ArrayList<>()), "=", ctx.getSymbol(buf, "new", packList(Variable.factory("database")))));

        // The following lines are generated from line 49
        buf.add(ctx.getSymbol(buf, "SC", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer dropDatabase(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 49
        buf.add(node158(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 53
    public static Buffer node175(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 53
        buf.add(ctx.getSymbol(buf, "DROP", new ArrayList<>()));

        // The following lines are generated from line 53
        buf.add(ctx.getSymbol(buf, "SCHEMA", new ArrayList<>()));

        // The following lines are generated from line 53
        buf.add(ctx.getSymbol(buf, "ifExists", new ArrayList<>()));

        // The following lines are generated from line 53
        buf.add(ctx.getSymbol(buf, "DB", new ArrayList<>()));

        // The following lines are generated from line 53
        buf.add(ctx.getSymbol(buf, "SC", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer dropSchema(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 53
        buf.add(node175(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 58
    public static Buffer node191(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 58
            buf.add(ctx.getSymbol(buf, "DATABASE", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 58
            buf.add(ctx.getSymbol(buf, "SCHEMA", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 58
    public static Buffer node198(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 58
            buf.add(ctx.getSymbol(buf, "ifNotExists", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 58
    public static Buffer node188(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 58
        buf.add(ctx.getSymbol(buf, "CREATE", new ArrayList<>()));

        // The following lines are generated from line 58
        buf.add(node191(ctx));

        // The following lines are generated from line 58
        buf.add(node198(ctx));

        // The following lines are generated from line 58
        buf.add(ctx.getSymbol(buf, "DB", new ArrayList<>()));

        // The following lines are generated from line 58
        ctx.eval(ctx.getSymbol(buf, "SCHEMA_CHANGE", new ArrayList<>()), "=", Variable.factory(1));

        // The following lines are generated from line 58
        buf.add(ctx.getSymbol(buf, "SC", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer createDatabase(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 58
        buf.add(node188(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 62
    public static Buffer node211(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 62
        buf.add(ctx.getSymbol(buf, "USE", new ArrayList<>()));

        // The following lines are generated from line 62
        buf.add(ctx.getSymbol(buf, "DB", new ArrayList<>()));

        // The following lines are generated from line 62
        buf.add(ctx.getSymbol(buf, "SC", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer useDatabase(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 62
        buf.add(node211(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 67
    public static Buffer node230(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 67
        buf.add(ctx.getSymbol(buf, "TEMPORARY", new ArrayList<>()));

        // The following lines are generated from line 67
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Cannot create temporary table with partitions"))));

        // The following lines are generated from line 67
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Cannot create FULLTEXT index on temporary InnoDB table"))));

        return buf;
    }
    // The following lines are generated from line 67
    public static Buffer node227(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 90.0);
        opt.addOption(1, 10.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 67
            buf.add(Variable.factory(" "));
        }
        if(index==1){
            // The following lines are generated from line 67
            buf.add(node230(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 68
    public static Buffer node241(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 68
            buf.add(ctx.getSymbol(buf, "ifNotExists", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 70
    public static Buffer node253(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 70
        buf.add(ctx.eval(ctx.getSymbol(buf, "cn", new ArrayList<>()), "+=", ctx.getSymbol(buf, "new", packList(Variable.factory("column")))));

        // The following lines are generated from line 70
        buf.add(ctx.getSymbol(buf, "columnDefinition", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 70
    public static Buffer node251(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(5),Variable.factory(10),Variable.factory(","),Variable.factory(30)));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 70
            buf.add(node253(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 72
    public static Buffer node277(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 72
            buf.add(Variable.factory(" MyISAM "));
        }
        if(index==1){
            // The following lines are generated from line 72
            buf.add(Variable.factory(" InnoDB "));
        }
        return buf;
    }
    // The following lines are generated from line 72
    public static Buffer node273(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 72
        buf.add(Variable.factory(" ENGINE "));

        // The following lines are generated from line 72
        buf.add(ctx.getSymbol(buf, "EQ", new ArrayList<>()));

        // The following lines are generated from line 72
        buf.add(node277(ctx));

        return buf;
    }
    // The following lines are generated from line 73
    public static Buffer node287(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 73
            buf.add(ctx.getSymbol(buf, "LINEAR", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 75
    public static Buffer node296(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 75
        buf.add(Variable.factory("HASH("));

        // The following lines are generated from line 75
        buf.add(ctx.getSymbol(buf, "cn", new ArrayList<>()).getAttr("any", new ArrayList<>()));

        // The following lines are generated from line 75
        buf.add(Variable.factory(")"));

        return buf;
    }
    // The following lines are generated from line 76
    public static Buffer node308(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 76
            buf.add(Variable.factory("1"));
        }
        if(index==1){
            // The following lines are generated from line 76
            buf.add(Variable.factory("2"));
        }
        return buf;
    }
    // The following lines are generated from line 76
    public static Buffer node306(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 76
        buf.add(Variable.factory("ALGORITHM="));

        // The following lines are generated from line 76
        buf.add(node308(ctx));

        return buf;
    }
    // The following lines are generated from line 76
    public static Buffer node304(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 76
            buf.add(node306(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 76
    public static Buffer node302(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 76
        buf.add(Variable.factory(" KEY "));

        // The following lines are generated from line 76
        buf.add(node304(ctx));

        // The following lines are generated from line 76
        buf.add(Variable.factory("("));

        // The following lines are generated from line 76
        buf.add(ctx.getSymbol(buf, "cn", new ArrayList<>()).getAttr("any", new ArrayList<>()));

        // The following lines are generated from line 76
        buf.add(Variable.factory(")"));

        return buf;
    }
    // The following lines are generated from line 75
    public static Buffer node295(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 75
            buf.add(node296(ctx));
        }
        if(index==1){
            // The following lines are generated from line 76
            buf.add(node302(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 73
    public static Buffer node282(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 73
        buf.add(ctx.getSymbol(buf, "PARTITION", new ArrayList<>()));

        // The following lines are generated from line 73
        buf.add(ctx.getSymbol(buf, "BY", new ArrayList<>()));

        // The following lines are generated from line 73
        buf.add(node287(ctx));

        // The following lines are generated from line 73
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("allowed type"))));

        // The following lines are generated from line 75
        buf.add(node295(ctx));

        return buf;
    }
    // The following lines are generated from line 71
    public static Buffer node270(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 60.0);
        opt.addOption(1, 20.0);
        opt.addOption(2, 20.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 71
            buf.add(Variable.factory(" "));
        }
        if(index==1){
            // The following lines are generated from line 72
            buf.add(node273(ctx));
        }
        if(index==2){
            // The following lines are generated from line 73
            buf.add(node282(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 70
    public static Buffer node248(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 70
        buf.add(ctx.getSymbol(buf, "LB", new ArrayList<>()));

        // The following lines are generated from line 70
        buf.add(node251(ctx));

        // The following lines are generated from line 70
        buf.add(ctx.getSymbol(buf, "RB", new ArrayList<>()));

        // The following lines are generated from line 71
        buf.add(node270(ctx));

        return buf;
    }
    // The following lines are generated from line 79
    public static Buffer node318(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 79
        buf.add(ctx.getSymbol(buf, "LIKE", new ArrayList<>()));

        // The following lines are generated from line 79
        buf.add(ctx.getSymbol(buf, "table", new ArrayList<>()).getAttr("any", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 70
    public static Buffer node247(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 80.0);
        opt.addOption(1, 20.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 70
            buf.add(node248(ctx));
        }
        if(index==1){
            // The following lines are generated from line 79
            buf.add(node318(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 66
    public static Buffer node220(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 66
        buf.add(ctx.getSymbol(buf, "CREATE", new ArrayList<>()));

        // The following lines are generated from line 66
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("A BLOB field is not allowed in partition function"),Variable.factory("is of a not allowed type for this type of partitioning"))));

        // The following lines are generated from line 67
        buf.add(node227(ctx));

        // The following lines are generated from line 67
        buf.add(ctx.getSymbol(buf, "TABLE", new ArrayList<>()));

        // The following lines are generated from line 68
        buf.add(node241(ctx));

        // The following lines are generated from line 68
        buf.add(ctx.getSymbol(buf, "new", packList(Variable.factory("table"))));

        // The following lines are generated from line 70
        buf.add(node247(ctx));

        // The following lines are generated from line 80
        ctx.eval(ctx.getSymbol(buf, "SCHEMA_CHANGE", new ArrayList<>()), "=", Variable.factory(1));

        // The following lines are generated from line 80
        buf.add(ctx.getSymbol(buf, "SC", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer createTable(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 66
        buf.add(node220(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 86
    public static Buffer node345(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 86
        buf.add(ctx.getSymbol(buf, "UNIQUE", new ArrayList<>()));

        // The following lines are generated from line 86
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Duplicate"),Variable.factory("A UNIQUE INDEX must include all columns in the "))));

        return buf;
    }
    // The following lines are generated from line 87
    public static Buffer node352(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 87
        buf.add(ctx.getSymbol(buf, "FULLTEXT", new ArrayList<>()));

        // The following lines are generated from line 87
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("cannot be part of"),Variable.factory(" support FULLTEXT indexes"))));

        return buf;
    }
    // The following lines are generated from line 88
    public static Buffer node359(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 88
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("A SPATIAL index may only contain a geometrical type column"))));

        // The following lines are generated from line 88
        buf.add(ctx.getSymbol(buf, "SPATIAL", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 86
    public static Buffer node344(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 49.0);
        opt.addOption(1, 49.0);
        opt.addOption(2, 2.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 86
            buf.add(node345(ctx));
        }
        if(index==1){
            // The following lines are generated from line 87
            buf.add(node352(ctx));
        }
        if(index==2){
            // The following lines are generated from line 88
            buf.add(node359(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 85
    public static Buffer node341(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(0),Variable.factory(1),Variable.factory(90)));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 86
            buf.add(node344(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 91
    public static Buffer node400(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 91
            buf.add(Variable.factory(" ASC "));
        }
        if(index==1){
            // The following lines are generated from line 91
            buf.add(Variable.factory(" DESC "));
        }
        return buf;
    }
    // The following lines are generated from line 91
    public static Buffer node399(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 91
        buf.add(node400(ctx));

        // The following lines are generated from line 91
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("support"))));

        // The following lines are generated from line 91
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("explicit index order"))));

        return buf;
    }
    // The following lines are generated from line 91
    public static Buffer node397(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 91
            buf.add(node399(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 91
    public static Buffer node393(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 91
        buf.add(ctx.getSymbol(buf, "c", new ArrayList<>()).getAttr("unique_any", new ArrayList<>()));

        // The following lines are generated from line 91
        buf.add(node397(ctx));

        return buf;
    }
    // The following lines are generated from line 91
    public static Buffer node391(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(1),Variable.factory(6),Variable.factory(","),Variable.factory(90)));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 91
            buf.add(node393(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 93
    public static Buffer node424(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 33.333333333333336);
        opt.addOption(1, 33.333333333333336);
        opt.addOption(2, 33.333333333333336);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 93
            buf.add(ctx.getSymbol(buf, "DEFAULT", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 93
            buf.add(ctx.getSymbol(buf, "INPLACE", new ArrayList<>()));
        }
        if(index==2){
            // The following lines are generated from line 93
            buf.add(ctx.getSymbol(buf, "COPY", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 93
    public static Buffer node419(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 93
        buf.add(ctx.getSymbol(buf, "ALGORITHM", new ArrayList<>()));

        // The following lines are generated from line 93
        buf.add(ctx.getSymbol(buf, "EQ", new ArrayList<>()));

        // The following lines are generated from line 93
        buf.add(node424(ctx));

        return buf;
    }
    // The following lines are generated from line 94
    public static Buffer node439(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 25.0);
        opt.addOption(1, 25.0);
        opt.addOption(2, 25.0);
        opt.addOption(3, 25.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 94
            buf.add(ctx.getSymbol(buf, "DEFAULT", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 94
            buf.add(ctx.getSymbol(buf, "NONE", new ArrayList<>()));
        }
        if(index==2){
            // The following lines are generated from line 94
            buf.add(ctx.getSymbol(buf, "SHARED", new ArrayList<>()));
        }
        if(index==3){
            // The following lines are generated from line 94
            buf.add(ctx.getSymbol(buf, "EXCLUSIVE", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 94
    public static Buffer node434(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 94
        buf.add(ctx.getSymbol(buf, "LOCK", new ArrayList<>()));

        // The following lines are generated from line 94
        buf.add(ctx.getSymbol(buf, "EQ", new ArrayList<>()));

        // The following lines are generated from line 94
        buf.add(node439(ctx));

        return buf;
    }
    // The following lines are generated from line 93
    public static Buffer node418(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 93
            buf.add(node419(ctx));
        }
        if(index==1){
            // The following lines are generated from line 94
            buf.add(node434(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 84
    public static Buffer node332(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 84
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("used in key specification without a key length"))));

        // The following lines are generated from line 84
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Too many key parts specified"))));

        // The following lines are generated from line 84
        buf.add(ctx.getSymbol(buf, "CREATE", new ArrayList<>()));

        // The following lines are generated from line 85
        buf.add(node341(ctx));

        // The following lines are generated from line 90
        buf.add(ctx.getSymbol(buf, "INDEX", new ArrayList<>()));

        // The following lines are generated from line 90
        buf.add(ctx.getSymbol(buf, "new", packList(Variable.factory("index"))));

        // The following lines are generated from line 91
        buf.add(ctx.getSymbol(buf, "ON", new ArrayList<>()));

        // The following lines are generated from line 91
        buf.add(ctx.eval(ctx.getSymbol(buf, "t", new ArrayList<>()), "=", ctx.getSymbol(buf, "table", new ArrayList<>()).getAttr("any", new ArrayList<>())));

        // The following lines are generated from line 91
        ctx.eval(ctx.getSymbol(buf, "c", new ArrayList<>()), "=", ctx.getSymbol(buf, "column", packList(ctx.getSymbol(buf, "t", new ArrayList<>()))));

        // The following lines are generated from line 91
        buf.add(Variable.factory("("));

        // The following lines are generated from line 91
        buf.add(node391(ctx));

        // The following lines are generated from line 91
        buf.add(Variable.factory(")"));

        // The following lines are generated from line 93
        buf.add(node418(ctx));

        // The following lines are generated from line 95
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("is not supported"))));

        // The following lines are generated from line 95
        ctx.eval(ctx.getSymbol(buf, "SCHEMA_CHANGE", new ArrayList<>()), "=", Variable.factory(1));

        // The following lines are generated from line 96
        buf.add(ctx.getSymbol(buf, "SC", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer createIndex(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 84
        buf.add(node332(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 99
    public static Buffer node463(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 99
        buf.add(ctx.getSymbol(buf, "TRUNCATE", new ArrayList<>()));

        // The following lines are generated from line 99
        buf.add(ctx.getSymbol(buf, "TABLE", new ArrayList<>()));

        // The following lines are generated from line 99
        buf.add(ctx.getSymbol(buf, "table", new ArrayList<>()).getAttr("any", new ArrayList<>()));

        // The following lines are generated from line 99
        ctx.eval(ctx.getSymbol(buf, "SCHEMA_CHANGE", new ArrayList<>()), "=", Variable.factory(1));

        // The following lines are generated from line 99
        buf.add(ctx.getSymbol(buf, "SC", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer truncateTable(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 99
        buf.add(node463(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 102
    public static Buffer node490(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 33.333333333333336);
        opt.addOption(1, 33.333333333333336);
        opt.addOption(2, 33.333333333333336);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 102
            buf.add(ctx.getSymbol(buf, "LOW_PRIORITY", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 102
            buf.add(ctx.getSymbol(buf, "DELAYED", new ArrayList<>()));
        }
        if(index==2){
            // The following lines are generated from line 102
            buf.add(ctx.getSymbol(buf, "HIGH_PRIORITY", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 102
    public static Buffer node487(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 102
            buf.add(node490(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 102
    public static Buffer node500(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 102
            buf.add(ctx.getSymbol(buf, "IGNORE", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 102
    public static Buffer node484(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 102
        buf.add(ctx.getSymbol(buf, "INSERT", new ArrayList<>()));

        // The following lines are generated from line 102
        buf.add(node487(ctx));

        // The following lines are generated from line 102
        buf.add(node500(ctx));

        return buf;
    }
    // The following lines are generated from line 102
    public static Buffer node480(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 102
            buf.add(ctx.getSymbol(buf, "REPLACE", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 102
            buf.add(node484(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 102
    public static Buffer node503(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 102
            buf.add(ctx.getSymbol(buf, "INTO", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 103
    public static Buffer node523(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(1),Variable.factory(6),Variable.factory(","),Variable.factory(75)));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 103
            buf.add(ctx.eval(ctx.getSymbol(buf, "c", new ArrayList<>()), "+=", ctx.getSymbol(buf, "cl", new ArrayList<>()).getAttr("unique_any", new ArrayList<>())));
        }
        return buf;
    }
    // The following lines are generated from line 104
    public static Buffer node542(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(ctx.getSymbol(buf, "c", new ArrayList<>()).getAttr("len", new ArrayList<>()),Variable.factory(",")));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 104
            buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "c", new ArrayList<>()).getAttr("next", new ArrayList<>()).getAttr("type", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 102
    public static Buffer node479(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 102
        buf.add(node480(ctx));

        // The following lines are generated from line 102
        buf.add(node503(ctx));

        // The following lines are generated from line 102
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Duplicate"))));

        // The following lines are generated from line 102
        buf.add(ctx.eval(ctx.getSymbol(buf, "t", new ArrayList<>()), "=", ctx.getSymbol(buf, "table", new ArrayList<>()).getAttr("any", new ArrayList<>())));

        // The following lines are generated from line 103
        buf.add(Variable.factory("("));

        // The following lines are generated from line 103
        ctx.eval(ctx.getSymbol(buf, "cl", new ArrayList<>()), "=", ctx.getSymbol(buf, "column", packList(ctx.getSymbol(buf, "t", new ArrayList<>()))));

        // The following lines are generated from line 103
        buf.add(node523(ctx));

        // The following lines are generated from line 103
        buf.add(Variable.factory(")"));

        // The following lines are generated from line 104
        buf.add(ctx.getSymbol(buf, "VALUES", new ArrayList<>()));

        // The following lines are generated from line 104
        buf.add(Variable.factory("("));

        // The following lines are generated from line 104
        buf.add(node542(ctx));

        // The following lines are generated from line 104
        buf.add(Variable.factory(")"));

        // The following lines are generated from line 105
        buf.add(ctx.getSymbol(buf, "SC", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer insertStatement(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 102
        buf.add(node479(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 109
    public static Buffer node568(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 109
            buf.add(ctx.getSymbol(buf, "LOW_PRIORITY", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 109
    public static Buffer node571(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 109
            buf.add(ctx.getSymbol(buf, "IGNORE", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 110
    public static Buffer node584(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 110
        buf.add(ctx.eval(ctx.getSymbol(buf, "cc", new ArrayList<>()), "=", ctx.getSymbol(buf, "column", packList(ctx.getSymbol(buf, "t", new ArrayList<>()))).getAttr("any", new ArrayList<>())));

        // The following lines are generated from line 110
        buf.add(Variable.factory("="));

        // The following lines are generated from line 110
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "cc", new ArrayList<>()).getAttr("type", new ArrayList<>()))));

        return buf;
    }
    // The following lines are generated from line 110
    public static Buffer node582(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(1),Variable.factory(6),Variable.factory(","),Variable.factory(75)));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 110
            buf.add(node584(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 110
    public static Buffer node610(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 110
            buf.add(ctx.getSymbol(buf, "NOT", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 110
    public static Buffer node607(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 110
        buf.add(ctx.getSymbol(buf, "WHERE", new ArrayList<>()));

        // The following lines are generated from line 110
        buf.add(node610(ctx));

        // The following lines are generated from line 110
        buf.add(ctx.eval(ctx.getSymbol(buf, "cc", new ArrayList<>()), "=", ctx.getSymbol(buf, "column", packList(ctx.getSymbol(buf, "t", new ArrayList<>()))).getAttr("any", new ArrayList<>())));

        // The following lines are generated from line 110
        buf.add(Variable.factory("="));

        // The following lines are generated from line 110
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "cc", new ArrayList<>()).getAttr("type", new ArrayList<>()))));

        return buf;
    }
    // The following lines are generated from line 110
    public static Buffer node605(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 110
            buf.add(node607(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 109
    public static Buffer node562(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 109
        buf.add(ctx.getSymbol(buf, "UPDATE", new ArrayList<>()));

        // The following lines are generated from line 109
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Duplicate"))));

        // The following lines are generated from line 109
        buf.add(node568(ctx));

        // The following lines are generated from line 109
        buf.add(node571(ctx));

        // The following lines are generated from line 109
        buf.add(ctx.eval(ctx.getSymbol(buf, "t", new ArrayList<>()), "=", ctx.getSymbol(buf, "table", new ArrayList<>()).getAttr("any", new ArrayList<>())));

        // The following lines are generated from line 110
        buf.add(ctx.getSymbol(buf, "SET", new ArrayList<>()));

        // The following lines are generated from line 110
        buf.add(node582(ctx));

        // The following lines are generated from line 110
        buf.add(node605(ctx));

        // The following lines are generated from line 110
        buf.add(ctx.getSymbol(buf, "SC", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer updateStatement(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 109
        buf.add(node562(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 119
    public static Buffer node683(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 33.333333333333336);
        opt.addOption(1, 33.333333333333336);
        opt.addOption(2, 33.333333333333336);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 119
            buf.add(ctx.getSymbol(buf, "least", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "$-", Variable.factory(1)))));
        }
        if(index==1){
            // The following lines are generated from line 120
            buf.add(ctx.getSymbol(buf, "greatest", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "$-", Variable.factory(1)))));
        }
        if(index==2){
            // The following lines are generated from line 121
            buf.add(ctx.getSymbol(buf, "if_func", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "$-", Variable.factory(1)))));
        }
        return buf;
    }
    // The following lines are generated from line 115
    public static Buffer node640(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        if(ctx.eval(ctx.getSymbol(buf, "type", new ArrayList<>()), "==", Variable.factory("int")).getBoolean()){
            opt.addOption(0, 31.666666666666668);
        }
        if(ctx.eval(ctx.getSymbol(buf, "type", new ArrayList<>()), "==", Variable.factory("text")).getBoolean()){
            opt.addOption(1, 31.666666666666668);
        }
        if(ctx.eval(ctx.getSymbol(buf, "type", new ArrayList<>()), "==", Variable.factory("double")).getBoolean()){
            opt.addOption(2, 31.666666666666668);
        }
        if(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), ">", Variable.factory(1)).getBoolean()){
            opt.addOption(3, 5.0);
        }
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 115
            buf.add(ctx.getSymbol(buf, "int_expr", packList(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "$-", Variable.factory(1)))));
        }
        if(index==1){
            // The following lines are generated from line 116
            buf.add(ctx.getSymbol(buf, "text_expr", packList(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "$-", Variable.factory(1)))));
        }
        if(index==2){
            // The following lines are generated from line 117
            buf.add(ctx.getSymbol(buf, "double_expr", packList(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "$-", Variable.factory(1)))));
        }
        if(index==3){
            // The following lines are generated from line 119
            buf.add(node683(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 114
    public static Buffer node633(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 114
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("out of range"))));

        // The following lines are generated from line 114
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Out of range value"))));

        // The following lines are generated from line 115
        buf.add(node640(ctx));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer expression(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "type", new ArrayList<>()));
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(4)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 114
        buf.add(node633(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 134
    public static Buffer node811(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 134
        buf.add(ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()), "=", ctx.getSymbol(buf, "at", new ArrayList<>()).getAttr("unique_any", new ArrayList<>())));

        // The following lines are generated from line 134
        ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()).getAttr("c", new ArrayList<>()), "=", ctx.getSymbol(buf, "column", packList(ctx.getSymbol(buf, "tt", new ArrayList<>()))));

        // The following lines are generated from line 134
        ctx.eval(ctx.getSymbol(buf, "t", new ArrayList<>()), "+=", ctx.getSymbol(buf, "tt", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 134
    public static Buffer node831(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 134
        buf.add(Variable.factory("("));

        // The following lines are generated from line 134
        ctx.eval(ctx.getSymbol(buf, "cc", new ArrayList<>()), "=", ctx.getSymbol(buf, "selectStatement", packList(Variable.factory(0))));

        // The following lines are generated from line 134
        buf.add(Variable.factory(")"));

        // The following lines are generated from line 134
        buf.add(ctx.getSymbol(buf, "AS", new ArrayList<>()));

        // The following lines are generated from line 134
        buf.add(ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()), "=", ctx.getSymbol(buf, "new", packList(Variable.factory("table")))));

        // The following lines are generated from line 134
        ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()).getAttr("c", new ArrayList<>()), "=", ctx.getSymbol(buf, "cc", new ArrayList<>()));

        // The following lines are generated from line 134
        ctx.eval(ctx.getSymbol(buf, "t", new ArrayList<>()), "+=", ctx.getSymbol(buf, "tt", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 134
    public static Buffer node810(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 70.0);
        opt.addOption(1, 30.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 134
            buf.add(node811(ctx));
        }
        if(index==1){
            // The following lines are generated from line 134
            buf.add(node831(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 137
    public static Buffer node865(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 137
        buf.add(ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()), "=", ctx.getSymbol(buf, "at", new ArrayList<>()).getAttr("unique_any", new ArrayList<>())));

        // The following lines are generated from line 137
        ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()).getAttr("c", new ArrayList<>()), "=", ctx.getSymbol(buf, "column", packList(ctx.getSymbol(buf, "tt", new ArrayList<>()))));

        // The following lines are generated from line 137
        ctx.eval(ctx.getSymbol(buf, "t", new ArrayList<>()), "+=", ctx.getSymbol(buf, "tt", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 137
    public static Buffer node885(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 137
        buf.add(Variable.factory("("));

        // The following lines are generated from line 137
        ctx.eval(ctx.getSymbol(buf, "cc", new ArrayList<>()), "=", ctx.getSymbol(buf, "selectStatement", packList(Variable.factory(0))));

        // The following lines are generated from line 137
        buf.add(Variable.factory(")"));

        // The following lines are generated from line 137
        buf.add(ctx.getSymbol(buf, "AS", new ArrayList<>()));

        // The following lines are generated from line 137
        buf.add(ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()), "=", ctx.getSymbol(buf, "new", packList(Variable.factory("table")))));

        // The following lines are generated from line 137
        ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()).getAttr("c", new ArrayList<>()), "=", ctx.getSymbol(buf, "cc", new ArrayList<>()));

        // The following lines are generated from line 137
        ctx.eval(ctx.getSymbol(buf, "t", new ArrayList<>()), "+=", ctx.getSymbol(buf, "tt", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 137
    public static Buffer node864(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 90.0);
        opt.addOption(1, 10.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 137
            buf.add(node865(ctx));
        }
        if(index==1){
            // The following lines are generated from line 137
            buf.add(node885(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 137
    public static Buffer node861(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 137
        buf.add(ctx.getSymbol(buf, "JOIN", new ArrayList<>()));

        // The following lines are generated from line 137
        buf.add(node864(ctx));

        return buf;
    }
    // The following lines are generated from line 136
    public static Buffer node859(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 137
            buf.add(node861(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 132
    public static Buffer node802(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 133
        ctx.eval(ctx.getSymbol(buf, "at", new ArrayList<>()), "=", ctx.getSymbol(buf, "table", new ArrayList<>()));
        // The following lines are generated from line 134
        buf.add(ctx.getSymbol(buf, "FROM", new ArrayList<>()));
        // The following lines are generated from line 134
        buf.add(node810(ctx));
        // The following lines are generated from line 136
        buf.add(node859(ctx));
        return buf;
    }
    // The following lines are generated from line 128
    public static Buffer node724(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        if(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "==", Variable.factory(0)).getBoolean()){
            opt.addOption(0, 50.0);
        }
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 128
            buf.add(ctx.getSymbol(buf, "DISTINCT", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 128
            buf.add(ctx.getSymbol(buf, "ALL", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 128
    public static Buffer node723(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 128
            buf.add(node724(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 129
    public static Buffer node748(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 129
        buf.add(ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()), "=", ctx.getSymbol(buf, "t", new ArrayList<>()).getAttr("any", new ArrayList<>())));

        // The following lines are generated from line 129
        buf.add(ctx.getSymbol(buf, "DOT", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 129
    public static Buffer node747(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 129
            buf.add(node748(ctx));
        }
        if(index==1){
            // The following lines are generated from line 129
            ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()), "=", ctx.getSymbol(buf, "t", new ArrayList<>()).getAttr("any", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 129
    public static Buffer node746(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 129
        buf.add(node747(ctx));

        // The following lines are generated from line 129
        buf.add(ctx.eval(ctx.getSymbol(buf, "c", new ArrayList<>()), "+=", ctx.getSymbol(buf, "tt", new ArrayList<>()).getAttr("c", new ArrayList<>()).getAttr("unique_any", new ArrayList<>())));

        return buf;
    }
    // The following lines are generated from line 129
    public static Buffer node744(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "rep", new ArrayList<>());
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 129
            buf.add(node746(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 130
    public static Buffer node785(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "tt", new ArrayList<>()).getAttr("c", new ArrayList<>()).getAttr("len", new ArrayList<>());
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 130
            ctx.eval(ctx.getSymbol(buf, "c", new ArrayList<>()), "+=", ctx.getSymbol(buf, "tt", new ArrayList<>()).getAttr("c", new ArrayList<>()).getAttr("next", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 130
    public static Buffer node778(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 130
        ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()), "=", ctx.getSymbol(buf, "t", new ArrayList<>()).getAttr("next", new ArrayList<>()));

        // The following lines are generated from line 130
        buf.add(node785(ctx));

        return buf;
    }
    // The following lines are generated from line 130
    public static Buffer node776(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "t", new ArrayList<>()).getAttr("len", new ArrayList<>());
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 130
            buf.add(node778(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 130
    public static Buffer node773(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 130
        buf.add(ctx.getSymbol(buf, "ASTERISK", new ArrayList<>()));

        // The following lines are generated from line 130
        buf.add(node776(ctx));

        return buf;
    }
    // The following lines are generated from line 129
    public static Buffer node742(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 90.0);
        opt.addOption(1, 10.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 129
            buf.add(node744(ctx));
        }
        if(index==1){
            // The following lines are generated from line 130
            buf.add(node773(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 127
    public static Buffer node720(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 128
        buf.add(ctx.getSymbol(buf, "SELECT", new ArrayList<>()));
        // The following lines are generated from line 128
        buf.add(node723(ctx));
        // The following lines are generated from line 128
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("is ambiguous"))));
        // The following lines are generated from line 128
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Duplicate column name"))));
        // The following lines are generated from line 129
        buf.add(node742(ctx));
        return buf;
    }
    // The following lines are generated from line 140
    public static Buffer node914(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        if(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "==", Variable.factory(1)).getBoolean()){
            opt.addOption(0, 50.0);
        }
        if(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "==", Variable.factory(0)).getBoolean()){
            opt.addOption(1, 50.0);
        }
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 140
            buf.add(Variable.factory(" /*BEGIN_COND*/ "));
        }
        if(index==1){
            // The following lines are generated from line 140
            buf.add(Variable.factory(" "));
        }
        return buf;
    }
    // The following lines are generated from line 141
    public static Buffer node929(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        if(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "==", Variable.factory(0)).getBoolean()){
            opt.addOption(1, 50.0);
        }
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 141
            buf.add(ctx.getSymbol(buf, "where_predicate", packList(ctx.getSymbol(buf, "t", new ArrayList<>()),ctx.getSymbol(buf, "c", new ArrayList<>()),ctx.getSymbol(buf, "outmost", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 141
            buf.add(Variable.factory(" "));
        }
        return buf;
    }
    // The following lines are generated from line 142
    public static Buffer node946(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        if(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "==", Variable.factory(1)).getBoolean()){
            opt.addOption(0, 50.0);
        }
        if(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "==", Variable.factory(0)).getBoolean()){
            opt.addOption(1, 50.0);
        }
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 142
            buf.add(Variable.factory(" /*END_COND*/ "));
        }
        if(index==1){
            // The following lines are generated from line 142
            buf.add(Variable.factory(" "));
        }
        return buf;
    }
    // The following lines are generated from line 144
    public static Buffer node961(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        if(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "==", Variable.factory(1)).getBoolean()){
            opt.addOption(0, 50.0);
        }
        if(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "==", Variable.factory(0)).getBoolean()){
            opt.addOption(1, 50.0);
        }
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 144
            buf.add(ctx.getSymbol(buf, "SC", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 144
            buf.add(Variable.factory(" "));
        }
        return buf;
    }
    // The following lines are generated from line 139
    public static Buffer node913(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 140
        buf.add(node914(ctx));
        // The following lines are generated from line 141
        buf.add(node929(ctx));
        // The following lines are generated from line 142
        buf.add(node946(ctx));
        // The following lines are generated from line 144
        buf.add(node961(ctx));
        return buf;
    }
    // The following lines are generated from line 127
    public static Buffer node719(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 132
        buf.set(1, node802(ctx));

        // The following lines are generated from line 127
        buf.set(0, node720(ctx));

        // The following lines are generated from line 139
        buf.set(2, node913(ctx));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer selectStatement(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "=", Variable.factory(1)));
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "rep", new ArrayList<>()), "=", ctx.getSymbol(buf, "random", packList(Variable.factory(1),Variable.factory(5),Variable.factory(",")))));
        ctx.enter(arg_decls);
        // The following lines are generated from line 127
        buf.add(node719(ctx));

        ctx.ret(ctx.getSymbol(buf, "c", new ArrayList<>()));
        return buf;
    }
    // The following lines are generated from line 149
    public static Buffer node996(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        if(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "==", Variable.factory(1)).getBoolean()){
            opt.addOption(0, 50.0);
        }
        if(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "==", Variable.factory(0)).getBoolean()){
            opt.addOption(1, 50.0);
        }
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 149
            buf.add(Variable.factory(" /*BEGIN_PRED*/ "));
        }
        if(index==1){
            // The following lines are generated from line 149
            buf.add(Variable.factory(" "));
        }
        return buf;
    }
    // The following lines are generated from line 152
    public static Buffer node1019(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 152
        buf.add(ctx.getSymbol(buf, "c", new ArrayList<>()).getAttr("any", new ArrayList<>()));

        // The following lines are generated from line 152
        buf.add(ctx.getSymbol(buf, "IN", new ArrayList<>()));

        // The following lines are generated from line 152
        buf.add(Variable.factory("("));

        // The following lines are generated from line 152
        buf.add(ctx.getSymbol(buf, "selectStatement", packList(Variable.factory(0),ctx.getSymbol(buf, "random", packList(Variable.factory(1),Variable.factory(","))))));

        // The following lines are generated from line 152
        buf.add(Variable.factory(")"));

        // The following lines are generated from line 152
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Operand should contain 1 column(s)"))));

        return buf;
    }
    // The following lines are generated from line 153
    public static Buffer node1038(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 153
            buf.add(ctx.getSymbol(buf, "NOT", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 153
    public static Buffer node1037(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 153
        buf.add(node1038(ctx));

        // The following lines are generated from line 153
        buf.add(ctx.getSymbol(buf, "EXISTS", new ArrayList<>()));

        // The following lines are generated from line 153
        buf.add(Variable.factory("("));

        // The following lines are generated from line 153
        buf.add(ctx.getSymbol(buf, "selectStatement", packList(Variable.factory(0))));

        // The following lines are generated from line 153
        buf.add(Variable.factory(")"));

        return buf;
    }
    // The following lines are generated from line 151
    public static Buffer node1011(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 60.0);
        opt.addOption(1, 20.0);
        opt.addOption(2, 20.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 151
            buf.add(ctx.getSymbol(buf, "predicate", packList(ctx.getSymbol(buf, "t", new ArrayList<>()),ctx.getSymbol(buf, "c", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 152
            buf.add(node1019(ctx));
        }
        if(index==2){
            // The following lines are generated from line 153
            buf.add(node1037(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 155
    public static Buffer node1048(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        if(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "==", Variable.factory(1)).getBoolean()){
            opt.addOption(0, 50.0);
        }
        if(ctx.eval(ctx.getSymbol(buf, "outmost", new ArrayList<>()), "==", Variable.factory(0)).getBoolean()){
            opt.addOption(1, 50.0);
        }
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 155
            buf.add(Variable.factory(" /*END_PRED*/ "));
        }
        if(index==1){
            // The following lines are generated from line 155
            buf.add(Variable.factory(" "));
        }
        return buf;
    }
    // The following lines are generated from line 148
    public static Buffer node993(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 148
        buf.add(ctx.getSymbol(buf, "WHERE", new ArrayList<>()));

        // The following lines are generated from line 149
        buf.add(node996(ctx));

        // The following lines are generated from line 151
        buf.add(node1011(ctx));

        // The following lines are generated from line 155
        buf.add(node1048(ctx));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer where_predicate(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "t", new ArrayList<>()));
        arg_decls.add(ctx.getSymbol(buf, "c", new ArrayList<>()));
        arg_decls.add(ctx.getSymbol(buf, "outmost", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 148
        buf.add(node993(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 158
    public static Buffer node1092(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 158
        buf.add(ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()), "=", ctx.getSymbol(buf, "t", new ArrayList<>()).getAttr("any", new ArrayList<>())));

        // The following lines are generated from line 158
        buf.add(ctx.getSymbol(buf, "DOT", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 158
    public static Buffer node1091(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 158
            buf.add(node1092(ctx));
        }
        if(index==1){
            // The following lines are generated from line 158
            ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()), "=", ctx.getSymbol(buf, "t", new ArrayList<>()).getAttr("any", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 158
    public static Buffer node1090(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 158
        buf.add(node1091(ctx));

        // The following lines are generated from line 158
        buf.add(ctx.eval(ctx.getSymbol(buf, "cc", new ArrayList<>()), "=", ctx.getSymbol(buf, "tt", new ArrayList<>()).getAttr("c", new ArrayList<>()).getAttr("filter", packList(Variable.factory("type"),Variable.factory("=="),ctx.getSymbol(buf, "pivot", new ArrayList<>()).getAttr("type", new ArrayList<>()))).getAttr("any", new ArrayList<>())));

        return buf;
    }
    // The following lines are generated from line 158
    public static Buffer node1083(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 158
            buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "pivot", new ArrayList<>()).getAttr("type", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 158
            buf.add(node1090(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 160
    public static Buffer node1132(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 160
        buf.add(ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()), "=", ctx.getSymbol(buf, "t", new ArrayList<>()).getAttr("any", new ArrayList<>())));

        // The following lines are generated from line 160
        buf.add(ctx.getSymbol(buf, "DOT", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 160
    public static Buffer node1131(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 160
            buf.add(node1132(ctx));
        }
        if(index==1){
            // The following lines are generated from line 160
            ctx.eval(ctx.getSymbol(buf, "tt", new ArrayList<>()), "=", ctx.getSymbol(buf, "t", new ArrayList<>()).getAttr("any", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 160
    public static Buffer node1130(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 160
        buf.add(node1131(ctx));

        // The following lines are generated from line 160
        buf.add(ctx.eval(ctx.getSymbol(buf, "cc", new ArrayList<>()), "=", ctx.getSymbol(buf, "tt", new ArrayList<>()).getAttr("c", new ArrayList<>()).getAttr("filter", packList(Variable.factory("type"),Variable.factory("=="),ctx.getSymbol(buf, "pivot", new ArrayList<>()).getAttr("type", new ArrayList<>()))).getAttr("any", new ArrayList<>())));

        return buf;
    }
    // The following lines are generated from line 160
    public static Buffer node1123(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 160
            buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "pivot", new ArrayList<>()).getAttr("type", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 160
            buf.add(node1130(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 158
    public static Buffer node1073(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 158
        buf.add(Variable.factory("("));

        // The following lines are generated from line 158
        ctx.eval(ctx.getSymbol(buf, "pivot", new ArrayList<>()), "=", ctx.getSymbol(buf, "t", new ArrayList<>()).getAttr("any", new ArrayList<>()).getAttr("c", new ArrayList<>()).getAttr("any", new ArrayList<>()));

        // The following lines are generated from line 158
        buf.add(node1083(ctx));

        // The following lines are generated from line 159
        buf.add(ctx.getSymbol(buf, "comparison", new ArrayList<>()));

        // The following lines are generated from line 160
        buf.add(node1123(ctx));

        // The following lines are generated from line 160
        buf.add(Variable.factory(")"));

        return buf;
    }
    // The following lines are generated from line 158
    public static Buffer node1072(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 30.0);
        opt.addOption(2, 20.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 158
            buf.add(node1073(ctx));
        }
        if(index==1){
            // The following lines are generated from line 161
            buf.add(ctx.getSymbol(buf, "ifnull", packList(ctx.getSymbol(buf, "t", new ArrayList<>()).getAttr("any", new ArrayList<>()).getAttr("c", new ArrayList<>()).getAttr("any", new ArrayList<>()).getAttr("type", new ArrayList<>()),Variable.factory(1))));
        }
        if(index==2){
            // The following lines are generated from line 162
            buf.add(ctx.getSymbol(buf, "if_func", packList(ctx.getSymbol(buf, "t", new ArrayList<>()).getAttr("any", new ArrayList<>()).getAttr("c", new ArrayList<>()).getAttr("any", new ArrayList<>()).getAttr("type", new ArrayList<>()),Variable.factory(1))));
        }
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer predicate(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "t", new ArrayList<>()));
        arg_decls.add(ctx.getSymbol(buf, "c", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 158
        buf.add(node1072(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 165
    public static Buffer node1199(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 165
        buf.add(ctx.getSymbol(buf, "LT", new ArrayList<>()));

        // The following lines are generated from line 165
        buf.add(ctx.getSymbol(buf, "EQ", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 165
    public static Buffer node1204(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 165
        buf.add(ctx.getSymbol(buf, "GT", new ArrayList<>()));

        // The following lines are generated from line 165
        buf.add(ctx.getSymbol(buf, "EQ", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 165
    public static Buffer node1189(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 20.0);
        opt.addOption(1, 20.0);
        opt.addOption(2, 20.0);
        opt.addOption(3, 20.0);
        opt.addOption(4, 20.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 165
            buf.add(ctx.getSymbol(buf, "LT", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 165
            buf.add(ctx.getSymbol(buf, "GT", new ArrayList<>()));
        }
        if(index==2){
            // The following lines are generated from line 165
            buf.add(ctx.getSymbol(buf, "EQ", new ArrayList<>()));
        }
        if(index==3){
            // The following lines are generated from line 165
            buf.add(node1199(ctx));
        }
        if(index==4){
            // The following lines are generated from line 165
            buf.add(node1204(ctx));
        }
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer comparison(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 165
        buf.add(node1189(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 168
    public static Buffer node1211(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 168
        buf.add(ctx.getSymbol(buf, "WAIT", new ArrayList<>()));

        // The following lines are generated from line 168
        buf.add(ctx.getSymbol(buf, "double_val", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 168
    public static Buffer node1210(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 168
            buf.add(node1211(ctx));
        }
        if(index==1){
            // The following lines are generated from line 169
            buf.add(ctx.getSymbol(buf, "NOWAIT", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer waitNowaitClause(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 168
        buf.add(node1210(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 172
    public static Buffer node1223(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 172
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 172
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 172
    public static Buffer node1221(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 172
        buf.add(Variable.factory(" ABS("));

        // The following lines are generated from line 172
        buf.add(node1223(ctx));

        // The following lines are generated from line 172
        buf.add(Variable.factory(")"));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer abs(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 172
        buf.add(node1221(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 173
    public static Buffer node1241(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 173
        buf.add(Variable.factory(" BIT_COUNT("));

        // The following lines are generated from line 173
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 173
        buf.add(Variable.factory(")"));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer bit_count(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 173
        buf.add(node1241(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 175
    public static Buffer node1256(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 175
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 175
        buf.add(ctx.getSymbol(buf, "comparison", new ArrayList<>()));

        // The following lines are generated from line 175
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        return buf;
    }
    // The following lines are generated from line 175
    public static Buffer node1255(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 175
            buf.add(node1256(ctx));
        }
        if(index==1){
            // The following lines are generated from line 175
            buf.add(ctx.getSymbol(buf, "ifnull", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 175
    public static Buffer node1253(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 175
        buf.add(Variable.factory(" IF("));

        // The following lines are generated from line 175
        buf.add(node1255(ctx));

        // The following lines are generated from line 175
        buf.add(Variable.factory(", "));

        // The following lines are generated from line 175
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 175
        buf.add(Variable.factory(", "));

        // The following lines are generated from line 175
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 175
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer if_func(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "type", new ArrayList<>()));
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 175
        buf.add(node1253(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 176
    public static Buffer node1299(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 176
        buf.add(Variable.factory(" IFNULL("));

        // The following lines are generated from line 176
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 176
        buf.add(Variable.factory(", "));

        // The following lines are generated from line 176
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 176
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer ifnull(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "type", new ArrayList<>()));
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 176
        buf.add(node1299(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 177
    public static Buffer node1331(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 177
        buf.add(Variable.factory(", "));

        // The following lines are generated from line 177
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        return buf;
    }
    // The following lines are generated from line 177
    public static Buffer node1329(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(1, Fuzzer.DEFAULT_MAX_REP);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 177
            buf.add(node1331(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 177
    public static Buffer node1321(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 177
        buf.add(Variable.factory(" GREATEST("));

        // The following lines are generated from line 177
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 177
        buf.add(node1329(ctx));

        // The following lines are generated from line 177
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer greatest(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "type", new ArrayList<>()));
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 177
        buf.add(node1321(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 178
    public static Buffer node1356(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 178
        buf.add(Variable.factory(", "));

        // The following lines are generated from line 178
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        return buf;
    }
    // The following lines are generated from line 178
    public static Buffer node1354(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(1, Fuzzer.DEFAULT_MAX_REP);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 178
            buf.add(node1356(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 178
    public static Buffer node1346(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 178
        buf.add(Variable.factory(" LEAST("));

        // The following lines are generated from line 178
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 178
        buf.add(node1354(ctx));

        // The following lines are generated from line 178
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer least(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "type", new ArrayList<>()));
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 178
        buf.add(node1346(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 179
    public static Buffer node1371(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 179
        buf.add(Variable.factory(" STRCMP("));

        // The following lines are generated from line 179
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 179
        buf.add(Variable.factory(", "));

        // The following lines are generated from line 179
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 179
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer strcmp(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 179
        buf.add(node1371(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 180
    public static Buffer node1389(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 180
        buf.add(Variable.factory(" SUBSTR("));

        // The following lines are generated from line 180
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 180
        buf.add(Variable.factory(", "));

        // The following lines are generated from line 180
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 180
        buf.add(Variable.factory(", "));

        // The following lines are generated from line 180
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 180
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer substr(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 180
        buf.add(node1389(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 181
    public static Buffer node1413(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 181
        buf.add(Variable.factory(" SUBSTRING("));

        // The following lines are generated from line 181
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 181
        buf.add(Variable.factory(", "));

        // The following lines are generated from line 181
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 181
        buf.add(Variable.factory(", "));

        // The following lines are generated from line 181
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 181
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer substring(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 181
        buf.add(node1413(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 182
    public static Buffer node1437(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 182
        buf.add(Variable.factory(" TRIM("));

        // The following lines are generated from line 182
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 182
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer trim(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 182
        buf.add(node1437(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 183
    public static Buffer node1449(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 183
        buf.add(Variable.factory(" LCASE("));

        // The following lines are generated from line 183
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 183
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer lcase(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 183
        buf.add(node1449(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 184
    public static Buffer node1461(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 184
        buf.add(Variable.factory(" UCASE("));

        // The following lines are generated from line 184
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 184
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer ucase(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 184
        buf.add(node1461(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 185
    public static Buffer node1473(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 185
        buf.add(Variable.factory(" SPACE("));

        // The following lines are generated from line 185
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 185
        buf.add(Variable.factory(") "));

        // The following lines are generated from line 185
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("was larger than"))));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer space(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "depth", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 185
        buf.add(node1473(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer last_insert_id(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 186
        buf.add(Variable.factory(" LAST_INSERT_ID() "));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 187
    public static Buffer node1494(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 187
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 187
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 187
    public static Buffer node1492(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 187
        buf.add(Variable.factory(" ACOS("));

        // The following lines are generated from line 187
        buf.add(node1494(ctx));

        // The following lines are generated from line 187
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer acos(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 187
        buf.add(node1492(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 188
    public static Buffer node1516(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 188
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 188
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 188
    public static Buffer node1514(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 188
        buf.add(Variable.factory(" ASIN("));

        // The following lines are generated from line 188
        buf.add(node1516(ctx));

        // The following lines are generated from line 188
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer asin(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 188
        buf.add(node1514(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 189
    public static Buffer node1539(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 189
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 189
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 189
    public static Buffer node1538(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(1),Variable.factory(2),Variable.factory(",")));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 189
            buf.add(node1539(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 189
    public static Buffer node1536(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 189
        buf.add(Variable.factory(" ATAN("));

        // The following lines are generated from line 189
        buf.add(node1538(ctx));

        // The following lines are generated from line 189
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer atan(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 189
        buf.add(node1536(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 190
    public static Buffer node1567(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 190
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 190
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 190
    public static Buffer node1566(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(2),Variable.factory(",")));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 190
            buf.add(node1567(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 190
    public static Buffer node1564(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 190
        buf.add(Variable.factory(" ATAN2("));

        // The following lines are generated from line 190
        buf.add(node1566(ctx));

        // The following lines are generated from line 190
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer atan2(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 190
        buf.add(node1564(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 191
    public static Buffer node1591(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 191
        buf.add(Variable.factory(" AVG("));

        // The following lines are generated from line 191
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 191
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer avg(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "type", new ArrayList<>()));
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 191
        buf.add(node1591(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 192
    public static Buffer node1609(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 192
            buf.add(Variable.factory(" CEIL("));
        }
        if(index==1){
            // The following lines are generated from line 192
            buf.add(Variable.factory(" CEILING("));
        }
        return buf;
    }
    // The following lines are generated from line 192
    public static Buffer node1614(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 192
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 192
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 192
    public static Buffer node1608(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 192
        buf.add(node1609(ctx));

        // The following lines are generated from line 192
        buf.add(node1614(ctx));

        // The following lines are generated from line 192
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer ceil(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 192
        buf.add(node1608(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 193
    public static Buffer node1637(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 70.0);
        opt.addOption(1, 20.0);
        opt.addOption(2, 10.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 193
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 193
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==2){
            // The following lines are generated from line 193
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 193
    public static Buffer node1636(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(2),Variable.factory(5),Variable.factory(",")));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 193
            buf.add(node1637(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 193
    public static Buffer node1634(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 193
        buf.add(Variable.factory(" CONCAT("));

        // The following lines are generated from line 193
        buf.add(node1636(ctx));

        // The following lines are generated from line 193
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer concat(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 193
        buf.add(node1634(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 194
    public static Buffer node1670(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 194
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 194
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 194
    public static Buffer node1668(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 194
        buf.add(Variable.factory(" COS("));

        // The following lines are generated from line 194
        buf.add(node1670(ctx));

        // The following lines are generated from line 194
        buf.add(Variable.factory(") "));

        // The following lines are generated from line 194
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("range"))));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer cos(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 194
        buf.add(node1668(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 195
    public static Buffer node1695(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 195
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 195
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 195
    public static Buffer node1693(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 195
        buf.add(Variable.factory(" COT("));

        // The following lines are generated from line 195
        buf.add(node1695(ctx));

        // The following lines are generated from line 195
        buf.add(Variable.factory(") "));

        // The following lines are generated from line 195
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("range"))));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer cot(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 195
        buf.add(node1693(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 196
    public static Buffer node1720(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 15.0);
        opt.addOption(1, 70.0);
        opt.addOption(2, 15.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 196
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 196
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==2){
            // The following lines are generated from line 196
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 196
    public static Buffer node1718(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 196
        buf.add(Variable.factory(" CRC32("));

        // The following lines are generated from line 196
        buf.add(node1720(ctx));

        // The following lines are generated from line 196
        buf.add(Variable.factory(")/4 "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer crc32(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 196
        buf.add(node1718(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 197
    public static Buffer node1748(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 197
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 197
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 197
    public static Buffer node1746(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 197
        buf.add(Variable.factory(" DEGREES("));

        // The following lines are generated from line 197
        buf.add(node1748(ctx));

        // The following lines are generated from line 197
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer degrees(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 197
        buf.add(node1746(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 198
    public static Buffer node1770(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 198
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 198
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 198
    public static Buffer node1768(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 198
        buf.add(Variable.factory(" EXP("));

        // The following lines are generated from line 198
        buf.add(node1770(ctx));

        // The following lines are generated from line 198
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer exp(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 198
        buf.add(node1768(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 199
    public static Buffer node1790(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 199
        buf.add(Variable.factory(" LEFT("));

        // The following lines are generated from line 199
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 199
        buf.add(Variable.factory(","));

        // The following lines are generated from line 199
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 199
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer left(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 199
        buf.add(node1790(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 200
    public static Buffer node1812(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 70.0);
        opt.addOption(1, 30.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 200
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 200
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 200
    public static Buffer node1810(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 200
        buf.add(Variable.factory(" LN("));

        // The following lines are generated from line 200
        buf.add(node1812(ctx));

        // The following lines are generated from line 200
        buf.add(Variable.factory(") "));

        // The following lines are generated from line 200
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Invalid argument"))));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer ln(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 200
        buf.add(node1810(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 201
    public static Buffer node1838(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 70.0);
        opt.addOption(1, 30.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 201
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 201
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 201
    public static Buffer node1837(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(1),Variable.factory(2),Variable.factory(",")));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 201
            buf.add(node1838(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 201
    public static Buffer node1835(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 201
        buf.add(Variable.factory(" LOG("));

        // The following lines are generated from line 201
        buf.add(node1837(ctx));

        // The following lines are generated from line 201
        buf.add(Variable.factory(") "));

        // The following lines are generated from line 201
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Invalid argument"))));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer log(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 201
        buf.add(node1835(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 202
    public static Buffer node1868(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 70.0);
        opt.addOption(1, 30.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 202
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 202
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 202
    public static Buffer node1866(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 202
        buf.add(Variable.factory(" LOG10("));

        // The following lines are generated from line 202
        buf.add(node1868(ctx));

        // The following lines are generated from line 202
        buf.add(Variable.factory(") "));

        // The following lines are generated from line 202
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Invalid argument"))));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer log10(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 202
        buf.add(node1866(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 203
    public static Buffer node1893(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 70.0);
        opt.addOption(1, 30.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 203
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 203
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 203
    public static Buffer node1891(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 203
        buf.add(Variable.factory(" LOG2("));

        // The following lines are generated from line 203
        buf.add(node1893(ctx));

        // The following lines are generated from line 203
        buf.add(Variable.factory(") "));

        // The following lines are generated from line 203
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Invalid argument"))));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer log2(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 203
        buf.add(node1891(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 204
    public static Buffer node1916(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 204
        buf.add(Variable.factory(" LOWER("));

        // The following lines are generated from line 204
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 204
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer lower(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 204
        buf.add(node1916(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 205
    public static Buffer node1930(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 205
        buf.add(Variable.factory(" LPAD("));

        // The following lines are generated from line 205
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 205
        buf.add(Variable.factory(","));

        // The following lines are generated from line 205
        buf.add(ctx.getSymbol(buf, "int_val", new ArrayList<>()));

        // The following lines are generated from line 205
        buf.add(Variable.factory(","));

        // The following lines are generated from line 205
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 205
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer lpad(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 205
        buf.add(node1930(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 206
    public static Buffer node1953(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 206
        buf.add(Variable.factory(" MD5("));

        // The following lines are generated from line 206
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 206
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer md5(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 206
        buf.add(node1953(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 207
    public static Buffer node1967(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 207
        buf.add(Variable.factory(" MOD("));

        // The following lines are generated from line 207
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 207
        buf.add(Variable.factory(","));

        // The following lines are generated from line 207
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 207
        buf.add(Variable.factory(") "));

        // The following lines are generated from line 207
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Division by"))));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer mod(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "type", new ArrayList<>()));
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 207
        buf.add(node1967(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer pi(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(0)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 208
        buf.add(Variable.factory(" PI() "));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 209
    public static Buffer node2002(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 209
        buf.add(Variable.factory(" POW("));

        // The following lines are generated from line 209
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 209
        buf.add(Variable.factory(","));

        // The following lines are generated from line 209
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 209
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer pow(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "type", new ArrayList<>()));
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 209
        buf.add(node2002(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 210
    public static Buffer node2026(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 210
        buf.add(Variable.factory(" RADIANS("));

        // The following lines are generated from line 210
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 210
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer radians(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 210
        buf.add(node2026(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 211
    public static Buffer node2042(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(0),Variable.factory(1)));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 211
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 211
    public static Buffer node2040(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 211
        buf.add(Variable.factory(" RAND("));

        // The following lines are generated from line 211
        buf.add(node2042(ctx));

        // The following lines are generated from line 211
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer rand(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 211
        buf.add(node2040(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 212
    public static Buffer node2061(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 212
        buf.add(Variable.factory(" REPLACE("));

        // The following lines are generated from line 212
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 212
        buf.add(Variable.factory(","));

        // The following lines are generated from line 212
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 212
        buf.add(Variable.factory(","));

        // The following lines are generated from line 212
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 212
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer replace(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 212
        buf.add(node2061(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 213
    public static Buffer node2087(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 213
        buf.add(Variable.factory(" RIGHT("));

        // The following lines are generated from line 213
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 213
        buf.add(Variable.factory(","));

        // The following lines are generated from line 213
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 213
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer right(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 213
        buf.add(node2087(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 214
    public static Buffer node2117(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 214
        buf.add(Variable.factory(","));

        // The following lines are generated from line 214
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        return buf;
    }
    // The following lines are generated from line 214
    public static Buffer node2115(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 214
            buf.add(node2117(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 214
    public static Buffer node2107(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 214
        buf.add(Variable.factory(" ROUND("));

        // The following lines are generated from line 214
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 214
        buf.add(node2115(ctx));

        // The following lines are generated from line 214
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer round(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "type", new ArrayList<>()));
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 214
        buf.add(node2107(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 215
    public static Buffer node2135(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 215
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 215
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 215
    public static Buffer node2133(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 215
        buf.add(Variable.factory(" SIGN("));

        // The following lines are generated from line 215
        buf.add(node2135(ctx));

        // The following lines are generated from line 215
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer sign(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 215
        buf.add(node2133(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 216
    public static Buffer node2157(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 216
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 216
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 216
    public static Buffer node2155(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 216
        buf.add(Variable.factory(" SIN("));

        // The following lines are generated from line 216
        buf.add(node2157(ctx));

        // The following lines are generated from line 216
        buf.add(Variable.factory(") "));

        // The following lines are generated from line 216
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("range"))));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer sin(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 216
        buf.add(node2155(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 217
    public static Buffer node2182(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 217
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 217
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 217
    public static Buffer node2180(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 217
        buf.add(Variable.factory(" SQRT("));

        // The following lines are generated from line 217
        buf.add(node2182(ctx));

        // The following lines are generated from line 217
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer sqrt(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 217
        buf.add(node2180(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 218
    public static Buffer node2204(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 30.0);
        opt.addOption(1, 70.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 218
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 218
            buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 218
    public static Buffer node2202(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 218
        buf.add(Variable.factory(" TAN("));

        // The following lines are generated from line 218
        buf.add(node2204(ctx));

        // The following lines are generated from line 218
        buf.add(Variable.factory(") "));

        // The following lines are generated from line 218
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("range"))));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer tan(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 218
        buf.add(node2202(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 219
    public static Buffer node2227(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 219
        buf.add(Variable.factory(" TRUNCATE("));

        // The following lines are generated from line 219
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 219
        buf.add(Variable.factory(","));

        // The following lines are generated from line 219
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 219
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer truncate(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 219
        buf.add(node2227(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 220
    public static Buffer node2247(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 220
        buf.add(Variable.factory(" UPPER("));

        // The following lines are generated from line 220
        buf.add(ctx.getSymbol(buf, "expression", packList(Variable.factory("text"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 220
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer upper(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 220
        buf.add(node2247(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 222
    public static Buffer node2276(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 222
        buf.add(Variable.factory("/"));

        // The following lines are generated from line 222
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Division by"))));

        return buf;
    }
    // The following lines are generated from line 222
    public static Buffer node2269(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 25.0);
        opt.addOption(1, 25.0);
        opt.addOption(2, 25.0);
        if(ctx.eval(ctx.getSymbol(buf, "type", new ArrayList<>()), "!=", Variable.factory("int")).getBoolean()){
            opt.addOption(3, 25.0);
        }
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 222
            buf.add(Variable.factory("+"));
        }
        if(index==1){
            // The following lines are generated from line 222
            buf.add(Variable.factory("-"));
        }
        if(index==2){
            // The following lines are generated from line 222
            buf.add(Variable.factory("*"));
        }
        if(index==3){
            // The following lines are generated from line 222
            buf.add(node2276(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 222
    public static Buffer node2261(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 222
        buf.add(Variable.factory(" ("));

        // The following lines are generated from line 222
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 222
        buf.add(node2269(ctx));

        // The following lines are generated from line 222
        buf.add(ctx.getSymbol(buf, "expression", packList(ctx.getSymbol(buf, "type", new ArrayList<>()),ctx.getSymbol(buf, "depth", new ArrayList<>()))));

        // The following lines are generated from line 222
        buf.add(Variable.factory(") "));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer numerical_operation(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "type", new ArrayList<>()));
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(1)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 222
        buf.add(node2261(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 227
    public static Buffer node2310(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 4.0);
        opt.addOption(1, 4.0);
        opt.addOption(2, 4.0);
        opt.addOption(3, 4.0);
        opt.addOption(4, 4.0);
        opt.addOption(5, 4.0);
        opt.addOption(6, 4.0);
        opt.addOption(7, 4.0);
        opt.addOption(8, 4.0);
        opt.addOption(9, 4.0);
        opt.addOption(10, 4.0);
        opt.addOption(11, 4.0);
        opt.addOption(12, 4.0);
        opt.addOption(13, 4.0);
        opt.addOption(14, 4.0);
        opt.addOption(15, 4.0);
        opt.addOption(16, 4.0);
        opt.addOption(17, 4.0);
        opt.addOption(18, 4.0);
        opt.addOption(19, 4.0);
        opt.addOption(20, 4.0);
        opt.addOption(21, 4.0);
        opt.addOption(22, 4.0);
        opt.addOption(23, 4.0);
        opt.addOption(24, 4.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 227
            buf.add(ctx.getSymbol(buf, "abs", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 228
            buf.add(ctx.getSymbol(buf, "acos", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==2){
            // The following lines are generated from line 229
            buf.add(ctx.getSymbol(buf, "asin", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==3){
            // The following lines are generated from line 230
            buf.add(ctx.getSymbol(buf, "atan", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==4){
            // The following lines are generated from line 231
            buf.add(ctx.getSymbol(buf, "atan2", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==5){
            // The following lines are generated from line 233
            buf.add(ctx.getSymbol(buf, "cos", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==6){
            // The following lines are generated from line 234
            buf.add(ctx.getSymbol(buf, "cot", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==7){
            // The following lines are generated from line 235
            buf.add(ctx.getSymbol(buf, "degrees", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==8){
            // The following lines are generated from line 236
            buf.add(ctx.getSymbol(buf, "exp", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==9){
            // The following lines are generated from line 237
            buf.add(ctx.getSymbol(buf, "ln", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==10){
            // The following lines are generated from line 238
            buf.add(ctx.getSymbol(buf, "log", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==11){
            // The following lines are generated from line 239
            buf.add(ctx.getSymbol(buf, "log10", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==12){
            // The following lines are generated from line 240
            buf.add(ctx.getSymbol(buf, "log2", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==13){
            // The following lines are generated from line 241
            buf.add(ctx.getSymbol(buf, "mod", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==14){
            // The following lines are generated from line 242
            buf.add(ctx.getSymbol(buf, "pi", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==15){
            // The following lines are generated from line 243
            buf.add(ctx.getSymbol(buf, "pow", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==16){
            // The following lines are generated from line 244
            buf.add(ctx.getSymbol(buf, "radians", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==17){
            // The following lines are generated from line 245
            buf.add(ctx.getSymbol(buf, "rand", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==18){
            // The following lines are generated from line 246
            buf.add(ctx.getSymbol(buf, "round", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==19){
            // The following lines are generated from line 247
            buf.add(ctx.getSymbol(buf, "sin", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==20){
            // The following lines are generated from line 248
            buf.add(ctx.getSymbol(buf, "sqrt", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==21){
            // The following lines are generated from line 249
            buf.add(ctx.getSymbol(buf, "tan", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==22){
            // The following lines are generated from line 250
            buf.add(ctx.getSymbol(buf, "truncate", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==23){
            // The following lines are generated from line 251
            buf.add(ctx.getSymbol(buf, "NULL", new ArrayList<>()));
        }
        if(index==24){
            // The following lines are generated from line 252
            buf.add(ctx.getSymbol(buf, "numerical_operation", packList(Variable.factory("double"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 225
    public static Buffer node2300(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        if(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), ">", Variable.factory(0)).getBoolean()){
            opt.addOption(1, 50.0);
        }
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 225
            buf.add(ctx.getSymbol(buf, "double_val", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 227
            buf.add(node2310(ctx));
        }
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer double_expr(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(2)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 225
        buf.add(node2300(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 254
    public static Buffer node2445(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(0),Variable.factory(1),Variable.factory(""),Variable.factory(90)));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 254
            buf.add(ctx.getSymbol(buf, "DS", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 254
    public static Buffer node2460(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 254
        buf.add(Variable.factory("."));

        // The following lines are generated from line 254
        buf.add(ctx.getSymbol(buf, "int_val", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 254
    public static Buffer node2458(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        int rep = Rand.random(0, 1);
        String delimiter = "";
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 254
            buf.add(node2460(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 254
    public static Buffer node2444(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 254
        buf.add(node2445(ctx));

        // The following lines are generated from line 254
        buf.add(ctx.getSymbol(buf, "int_val", new ArrayList<>()));

        // The following lines are generated from line 254
        buf.add(node2458(ctx));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer double_val(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 254
        buf.add(node2444(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 260
    public static Buffer node2475(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 9.090909090909092);
        opt.addOption(1, 9.090909090909092);
        opt.addOption(2, 9.090909090909092);
        opt.addOption(3, 9.090909090909092);
        opt.addOption(4, 9.090909090909092);
        opt.addOption(5, 9.090909090909092);
        opt.addOption(6, 9.090909090909092);
        opt.addOption(7, 9.090909090909092);
        opt.addOption(8, 9.090909090909092);
        opt.addOption(9, 9.090909090909092);
        opt.addOption(10, 9.090909090909092);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 260
            buf.add(ctx.getSymbol(buf, "bit_count", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 261
            buf.add(ctx.getSymbol(buf, "strcmp", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==2){
            // The following lines are generated from line 262
            buf.add(ctx.getSymbol(buf, "last_insert_id", new ArrayList<>()));
        }
        if(index==3){
            // The following lines are generated from line 263
            buf.add(ctx.getSymbol(buf, "ceil", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==4){
            // The following lines are generated from line 264
            buf.add(ctx.getSymbol(buf, "crc32", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==5){
            // The following lines are generated from line 265
            buf.add(ctx.getSymbol(buf, "mod", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==6){
            // The following lines are generated from line 266
            buf.add(ctx.getSymbol(buf, "pow", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==7){
            // The following lines are generated from line 267
            buf.add(ctx.getSymbol(buf, "round", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==8){
            // The following lines are generated from line 268
            buf.add(ctx.getSymbol(buf, "sign", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==9){
            // The following lines are generated from line 269
            buf.add(ctx.getSymbol(buf, "NULL", new ArrayList<>()));
        }
        if(index==10){
            // The following lines are generated from line 270
            buf.add(ctx.getSymbol(buf, "numerical_operation", packList(Variable.factory("int"),ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        return buf;
    }
    // The following lines are generated from line 257
    public static Buffer node2465(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        if(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), ">", Variable.factory(0)).getBoolean()){
            opt.addOption(1, 50.0);
        }
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 257
            buf.add(ctx.getSymbol(buf, "int_val", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 260
            buf.add(node2475(ctx));
        }
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer int_expr(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(2)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 257
        buf.add(node2465(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 272
    public static Buffer node2538(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(0),Variable.factory(1),Variable.factory(""),Variable.factory(90)));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 272
            buf.add(ctx.getSymbol(buf, "DS", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 272
    public static Buffer node2549(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(1),Variable.factory(5)));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 272
            buf.add(ctx.getSymbol(buf, "DIGIT", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 272
    public static Buffer node2537(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 272
        buf.add(node2538(ctx));

        // The following lines are generated from line 272
        buf.add(node2549(ctx));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer int_val(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 272
        buf.add(node2537(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 277
    public static Buffer node2569(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 6.666666666666667);
        opt.addOption(1, 6.666666666666667);
        opt.addOption(2, 6.666666666666667);
        opt.addOption(3, 6.666666666666667);
        opt.addOption(4, 6.666666666666667);
        opt.addOption(5, 6.666666666666667);
        opt.addOption(6, 6.666666666666667);
        opt.addOption(7, 6.666666666666667);
        opt.addOption(8, 6.666666666666667);
        opt.addOption(9, 6.666666666666667);
        opt.addOption(10, 6.666666666666667);
        opt.addOption(11, 6.666666666666667);
        opt.addOption(12, 6.666666666666667);
        opt.addOption(13, 6.666666666666667);
        opt.addOption(14, 6.666666666666667);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 277
            buf.add(ctx.getSymbol(buf, "concat", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==1){
            // The following lines are generated from line 278
            buf.add(ctx.getSymbol(buf, "substr", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==2){
            // The following lines are generated from line 279
            buf.add(ctx.getSymbol(buf, "substring", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==3){
            // The following lines are generated from line 280
            buf.add(ctx.getSymbol(buf, "lcase", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==4){
            // The following lines are generated from line 281
            buf.add(ctx.getSymbol(buf, "ucase", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==5){
            // The following lines are generated from line 282
            buf.add(ctx.getSymbol(buf, "space", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==6){
            // The following lines are generated from line 283
            buf.add(ctx.getSymbol(buf, "md5", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==7){
            // The following lines are generated from line 284
            buf.add(ctx.getSymbol(buf, "trim", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==8){
            // The following lines are generated from line 285
            buf.add(ctx.getSymbol(buf, "left", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==9){
            // The following lines are generated from line 286
            buf.add(ctx.getSymbol(buf, "lower", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==10){
            // The following lines are generated from line 287
            buf.add(ctx.getSymbol(buf, "lpad", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==11){
            // The following lines are generated from line 288
            buf.add(ctx.getSymbol(buf, "replace", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==12){
            // The following lines are generated from line 289
            buf.add(ctx.getSymbol(buf, "right", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==13){
            // The following lines are generated from line 290
            buf.add(ctx.getSymbol(buf, "upper", packList(ctx.getSymbol(buf, "depth", new ArrayList<>()))));
        }
        if(index==14){
            // The following lines are generated from line 291
            buf.add(ctx.getSymbol(buf, "NULL", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 276
    public static Buffer node2563(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 277
        buf.add(node2569(ctx));

        // The following lines are generated from line 292
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("Data too long"))));

        // The following lines are generated from line 292
        buf.add(ctx.getSymbol(buf, "error", packList(Variable.factory("was larger than"))));

        return buf;
    }
    // The following lines are generated from line 275
    public static Buffer node2559(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        if(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), ">", Variable.factory(0)).getBoolean()){
            opt.addOption(1, 50.0);
        }
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 275
            buf.add(ctx.getSymbol(buf, "text_val", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 276
            buf.add(node2563(ctx));
        }
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer text_expr(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.eval(ctx.getSymbol(buf, "depth", new ArrayList<>()), "=", Variable.factory(2)));
        ctx.enter(arg_decls);
        // The following lines are generated from line 275
        buf.add(node2559(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 293
    public static Buffer node2661(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        opt.addOption(0, 50.0);
        opt.addOption(1, 50.0);
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 293
            buf.add(ctx.getSymbol(buf, "CH", new ArrayList<>()));
        }
        if(index==1){
            // The following lines are generated from line 293
            buf.add(ctx.getSymbol(buf, "DIGIT", new ArrayList<>()));
        }
        return buf;
    }
    // The following lines are generated from line 293
    public static Buffer node2658(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Variable v = ctx.getSymbol(buf, "random", packList(Variable.factory(1),Variable.factory(100)));
        int rep = v.getNumerical();
        String delimiter = v.getAttr("delimiter", null).isPlaceHolder() ? "" : v.getAttr("delimiter", null).getValue();
        for (int i=0; i<rep; i++){
            if (i!=0){
                buf.add(Variable.factory(delimiter));
            }
            // The following lines are generated from line 293
            buf.add(node2661(ctx));
        }
        return buf;
    }
    // The following lines are generated from line 293
    public static Buffer node2655(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 293
        buf.add(ctx.getSymbol(buf, "DQ", new ArrayList<>()));

        // The following lines are generated from line 293
        buf.add(node2658(ctx));

        // The following lines are generated from line 293
        buf.add(ctx.getSymbol(buf, "DQ", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer text_val(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 293
        buf.add(node2655(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer db(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 295
        ctx.eval(ctx.getSymbol(buf, "d", new ArrayList<>()), "=", ctx.getSymbol(buf, "query", packList(Variable.factory("SHOW DATABASES;"),Variable.factory("Database"))));

        ctx.ret(ctx.getSymbol(buf, "d", new ArrayList<>()));
        return buf;
    }
    // The following lines are generated from line 296
    public static Buffer node2690(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 296
        ctx.eval(ctx.getSymbol(buf, "TABLE_CACHE", new ArrayList<>()), "=", ctx.getSymbol(buf, "query", packList(Variable.factory("SHOW TABLES;"),ctx.eval(Variable.factory("Tables_in_"), "$+", ctx.getSymbol(buf, "DB", new ArrayList<>())))));

        // The following lines are generated from line 296
        ctx.eval(ctx.getSymbol(buf, "SCHEMA_CHANGE", new ArrayList<>()), "=", Variable.factory(0));

        return buf;
    }
    // The following lines are generated from line 296
    public static Buffer node2689(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        Options opt = new Options();
        if(ctx.eval(ctx.getSymbol(buf, "SCHEMA_CHANGE", new ArrayList<>()), "==", Variable.factory(1)).getBoolean()){
            opt.addOption(0, 50.0);
        }
        if(ctx.eval(ctx.getSymbol(buf, "SCHEMA_CHANGE", new ArrayList<>()), "==", Variable.factory(0)).getBoolean()){
            opt.addOption(1, 50.0);
        }
        int index = opt.randomly();
        if(index==0){
            // The following lines are generated from line 296
            buf.add(node2690(ctx));
        }
        if(index==1){
            // The following lines are generated from line 296
            buf.add(Variable.factory(""));
        }
        return buf;
    }
    // The following lines are generated from line 296
    public static Buffer node2688(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 296
        buf.add(node2689(ctx));

        // The following lines are generated from line 296
        ctx.eval(ctx.getSymbol(buf, "t", new ArrayList<>()), "=", ctx.getSymbol(buf, "TABLE_CACHE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer table(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 296
        buf.add(node2688(ctx));

        ctx.ret(ctx.getSymbol(buf, "t", new ArrayList<>()));
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer column(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "t", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 298
        ctx.eval(ctx.getSymbol(buf, "c", new ArrayList<>()), "=", ctx.getSymbol(buf, "query", packList(ctx.eval(Variable.factory("SHOW COLUMNS FROM "), "$+", ctx.getSymbol(buf, "t", new ArrayList<>())),Variable.factory("Field"),Variable.factory("Type"),Variable.factory("type"))));

        ctx.ret(ctx.getSymbol(buf, "c", new ArrayList<>()));
        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer index(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        arg_decls.add(ctx.getSymbol(buf, "t", new ArrayList<>()));
        ctx.enter(arg_decls);
        // The following lines are generated from line 299
        ctx.eval(ctx.getSymbol(buf, "i", new ArrayList<>()), "=", ctx.getSymbol(buf, "query", packList(ctx.eval(Variable.factory("SHOW INDEX FROM "), "$+", ctx.getSymbol(buf, "t", new ArrayList<>())),Variable.factory("Key_name"))));

        ctx.ret(ctx.getSymbol(buf, "i", new ArrayList<>()));
        return buf;
    }
    // The following lines are generated from line 303
    public static Buffer node2762(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 303
        buf.add(ctx.getSymbol(buf, "IF", new ArrayList<>()));

        // The following lines are generated from line 303
        buf.add(ctx.getSymbol(buf, "NOT", new ArrayList<>()));

        // The following lines are generated from line 303
        buf.add(ctx.getSymbol(buf, "EXISTS", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer ifNotExists(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 303
        buf.add(node2762(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 304
    public static Buffer node2771(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 304
        buf.add(ctx.getSymbol(buf, "IF", new ArrayList<>()));

        // The following lines are generated from line 304
        buf.add(ctx.getSymbol(buf, "EXISTS", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line -1
    public static Buffer ifExists(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        ctx.push_frame();
        List<Variable> arg_decls = new ArrayList<>();
        ctx.enter(arg_decls);
        // The following lines are generated from line 304
        buf.add(node2771(ctx));

        ctx.ret(null);
        return buf;
    }
    // The following lines are generated from line 306
    public static Buffer node2778(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 306
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 306
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 306
        buf.add(ctx.getSymbol(buf, "D", new ArrayList<>()));

        // The following lines are generated from line 306
        buf.add(ctx.getSymbol(buf, "D", new ArrayList<>()));

        // The following lines are generated from line 306
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 306
    public static Buffer ADD(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 306
        buf.add(node2778(ctx));

        return buf;
    }
    // The following lines are generated from line 307
    public static Buffer node2791(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 307
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 307
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 307
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 307
        buf.add(ctx.getSymbol(buf, "G", new ArrayList<>()));

        // The following lines are generated from line 307
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 307
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 307
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 307
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 307
        buf.add(ctx.getSymbol(buf, "H", new ArrayList<>()));

        // The following lines are generated from line 307
        buf.add(ctx.getSymbol(buf, "M", new ArrayList<>()));

        // The following lines are generated from line 307
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 307
    public static Buffer ALGORITHM(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 307
        buf.add(node2791(ctx));

        return buf;
    }
    // The following lines are generated from line 308
    public static Buffer node2816(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 308
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 308
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 308
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 308
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 308
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 308
    public static Buffer ALL(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 308
        buf.add(node2816(ctx));

        return buf;
    }
    // The following lines are generated from line 309
    public static Buffer node2829(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 309
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 309
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 309
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 309
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 309
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 309
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 309
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 309
    public static Buffer ALTER(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 309
        buf.add(node2829(ctx));

        return buf;
    }
    // The following lines are generated from line 310
    public static Buffer node2846(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 310
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 310
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 310
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 310
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 310
    public static Buffer AS(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 310
        buf.add(node2846(ctx));

        return buf;
    }
    // The following lines are generated from line 311
    public static Buffer node2857(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 311
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 311
        buf.add(ctx.getSymbol(buf, "B", new ArrayList<>()));

        // The following lines are generated from line 311
        buf.add(ctx.getSymbol(buf, "Y", new ArrayList<>()));

        // The following lines are generated from line 311
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 311
    public static Buffer BY(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 311
        buf.add(node2857(ctx));

        return buf;
    }
    // The following lines are generated from line 312
    public static Buffer node2868(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 312
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 312
        buf.add(ctx.getSymbol(buf, "C", new ArrayList<>()));

        // The following lines are generated from line 312
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 312
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 312
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 312
        buf.add(ctx.getSymbol(buf, "M", new ArrayList<>()));

        // The following lines are generated from line 312
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 312
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 312
    public static Buffer COLUMN(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 312
        buf.add(node2868(ctx));

        return buf;
    }
    // The following lines are generated from line 313
    public static Buffer node2887(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 313
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 313
        buf.add(ctx.getSymbol(buf, "C", new ArrayList<>()));

        // The following lines are generated from line 313
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 313
        buf.add(ctx.getSymbol(buf, "P", new ArrayList<>()));

        // The following lines are generated from line 313
        buf.add(ctx.getSymbol(buf, "Y", new ArrayList<>()));

        // The following lines are generated from line 313
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 313
    public static Buffer COPY(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 313
        buf.add(node2887(ctx));

        return buf;
    }
    // The following lines are generated from line 314
    public static Buffer node2902(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 314
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 314
        buf.add(ctx.getSymbol(buf, "C", new ArrayList<>()));

        // The following lines are generated from line 314
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 314
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 314
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 314
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 314
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 314
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 314
    public static Buffer CREATE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 314
        buf.add(node2902(ctx));

        return buf;
    }
    // The following lines are generated from line 315
    public static Buffer node2921(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 315
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 315
        buf.add(ctx.getSymbol(buf, "D", new ArrayList<>()));

        // The following lines are generated from line 315
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 315
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 315
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 315
        buf.add(ctx.getSymbol(buf, "B", new ArrayList<>()));

        // The following lines are generated from line 315
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 315
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 315
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 315
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 315
    public static Buffer DATABASE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 315
        buf.add(node2921(ctx));

        return buf;
    }
    // The following lines are generated from line 316
    public static Buffer node2944(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 316
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 316
        buf.add(ctx.getSymbol(buf, "D", new ArrayList<>()));

        // The following lines are generated from line 316
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 316
        buf.add(ctx.getSymbol(buf, "F", new ArrayList<>()));

        // The following lines are generated from line 316
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 316
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 316
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 316
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 316
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 316
    public static Buffer DEFAULT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 316
        buf.add(node2944(ctx));

        return buf;
    }
    // The following lines are generated from line 317
    public static Buffer node2965(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 317
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 317
        buf.add(ctx.getSymbol(buf, "D", new ArrayList<>()));

        // The following lines are generated from line 317
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 317
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 317
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 317
        buf.add(ctx.getSymbol(buf, "Y", new ArrayList<>()));

        // The following lines are generated from line 317
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 317
        buf.add(ctx.getSymbol(buf, "D", new ArrayList<>()));

        // The following lines are generated from line 317
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 317
    public static Buffer DELAYED(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 317
        buf.add(node2965(ctx));

        return buf;
    }
    // The following lines are generated from line 318
    public static Buffer node2986(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 318
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 318
        buf.add(ctx.getSymbol(buf, "D", new ArrayList<>()));

        // The following lines are generated from line 318
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 318
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 318
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 318
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 318
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 318
        buf.add(ctx.getSymbol(buf, "C", new ArrayList<>()));

        // The following lines are generated from line 318
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 318
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 318
    public static Buffer DISTINCT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 318
        buf.add(node2986(ctx));

        return buf;
    }
    // The following lines are generated from line 319
    public static Buffer node3009(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 319
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 319
        buf.add(ctx.getSymbol(buf, "D", new ArrayList<>()));

        // The following lines are generated from line 319
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 319
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 319
        buf.add(ctx.getSymbol(buf, "B", new ArrayList<>()));

        // The following lines are generated from line 319
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 319
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 319
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 319
    public static Buffer DOUBLE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 319
        buf.add(node3009(ctx));

        return buf;
    }
    // The following lines are generated from line 320
    public static Buffer node3028(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 320
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 320
        buf.add(ctx.getSymbol(buf, "D", new ArrayList<>()));

        // The following lines are generated from line 320
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 320
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 320
        buf.add(ctx.getSymbol(buf, "P", new ArrayList<>()));

        // The following lines are generated from line 320
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 320
    public static Buffer DROP(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 320
        buf.add(node3028(ctx));

        return buf;
    }
    // The following lines are generated from line 321
    public static Buffer node3043(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 321
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 321
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 321
        buf.add(ctx.getSymbol(buf, "X", new ArrayList<>()));

        // The following lines are generated from line 321
        buf.add(ctx.getSymbol(buf, "C", new ArrayList<>()));

        // The following lines are generated from line 321
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 321
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 321
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 321
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 321
        buf.add(ctx.getSymbol(buf, "V", new ArrayList<>()));

        // The following lines are generated from line 321
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 321
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 321
    public static Buffer EXCLUSIVE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 321
        buf.add(node3043(ctx));

        return buf;
    }
    // The following lines are generated from line 322
    public static Buffer node3068(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 322
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 322
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 322
        buf.add(ctx.getSymbol(buf, "X", new ArrayList<>()));

        // The following lines are generated from line 322
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 322
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 322
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 322
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 322
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 322
    public static Buffer EXISTS(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 322
        buf.add(node3068(ctx));

        return buf;
    }
    // The following lines are generated from line 323
    public static Buffer node3087(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 323
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 323
        buf.add(ctx.getSymbol(buf, "F", new ArrayList<>()));

        // The following lines are generated from line 323
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 323
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 323
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 323
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 323
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 323
    public static Buffer FIRST(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 323
        buf.add(node3087(ctx));

        return buf;
    }
    // The following lines are generated from line 324
    public static Buffer node3104(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 324
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 324
        buf.add(ctx.getSymbol(buf, "F", new ArrayList<>()));

        // The following lines are generated from line 324
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 324
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 324
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 324
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 324
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 324
    public static Buffer FLOAT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 324
        buf.add(node3104(ctx));

        return buf;
    }
    // The following lines are generated from line 325
    public static Buffer node3121(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 325
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 325
        buf.add(ctx.getSymbol(buf, "F", new ArrayList<>()));

        // The following lines are generated from line 325
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 325
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 325
        buf.add(ctx.getSymbol(buf, "M", new ArrayList<>()));

        // The following lines are generated from line 325
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 325
    public static Buffer FROM(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 325
        buf.add(node3121(ctx));

        return buf;
    }
    // The following lines are generated from line 326
    public static Buffer node3136(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 326
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 326
        buf.add(ctx.getSymbol(buf, "F", new ArrayList<>()));

        // The following lines are generated from line 326
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 326
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 326
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 326
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 326
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 326
        buf.add(ctx.getSymbol(buf, "X", new ArrayList<>()));

        // The following lines are generated from line 326
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 326
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 326
    public static Buffer FULLTEXT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 326
        buf.add(node3136(ctx));

        return buf;
    }
    // The following lines are generated from line 327
    public static Buffer node3159(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 327
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 327
        buf.add(ctx.getSymbol(buf, "H", new ArrayList<>()));

        // The following lines are generated from line 327
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 327
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 327
        buf.add(ctx.getSymbol(buf, "H", new ArrayList<>()));

        // The following lines are generated from line 327
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 327
    public static Buffer HASH(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 327
        buf.add(node3159(ctx));

        return buf;
    }
    // The following lines are generated from line 328
    public static Buffer node3174(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "H", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "G", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "H", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "US", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "P", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "Y", new ArrayList<>()));

        // The following lines are generated from line 328
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 328
    public static Buffer HIGH_PRIORITY(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 328
        buf.add(node3174(ctx));

        return buf;
    }
    // The following lines are generated from line 329
    public static Buffer node3207(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 329
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 329
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 329
        buf.add(ctx.getSymbol(buf, "F", new ArrayList<>()));

        // The following lines are generated from line 329
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 329
    public static Buffer IF(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 329
        buf.add(node3207(ctx));

        return buf;
    }
    // The following lines are generated from line 330
    public static Buffer node3218(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 330
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 330
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 330
        buf.add(ctx.getSymbol(buf, "G", new ArrayList<>()));

        // The following lines are generated from line 330
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 330
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 330
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 330
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 330
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 330
    public static Buffer IGNORE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 330
        buf.add(node3218(ctx));

        return buf;
    }
    // The following lines are generated from line 331
    public static Buffer node3237(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 331
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 331
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 331
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 331
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 331
    public static Buffer IN(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 331
        buf.add(node3237(ctx));

        return buf;
    }
    // The following lines are generated from line 332
    public static Buffer node3248(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 332
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 332
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 332
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 332
        buf.add(ctx.getSymbol(buf, "D", new ArrayList<>()));

        // The following lines are generated from line 332
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 332
        buf.add(ctx.getSymbol(buf, "X", new ArrayList<>()));

        // The following lines are generated from line 332
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 332
    public static Buffer INDEX(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 332
        buf.add(node3248(ctx));

        return buf;
    }
    // The following lines are generated from line 333
    public static Buffer node3265(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 333
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 333
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 333
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 333
        buf.add(ctx.getSymbol(buf, "P", new ArrayList<>()));

        // The following lines are generated from line 333
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 333
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 333
        buf.add(ctx.getSymbol(buf, "C", new ArrayList<>()));

        // The following lines are generated from line 333
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 333
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 333
    public static Buffer INPLACE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 333
        buf.add(node3265(ctx));

        return buf;
    }
    // The following lines are generated from line 334
    public static Buffer node3286(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 334
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 334
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 334
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 334
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 334
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 334
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 334
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 334
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 334
    public static Buffer INSERT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 334
        buf.add(node3286(ctx));

        return buf;
    }
    // The following lines are generated from line 335
    public static Buffer node3305(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 335
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 335
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 335
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 335
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 335
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 335
    public static Buffer INT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 335
        buf.add(node3305(ctx));

        return buf;
    }
    // The following lines are generated from line 336
    public static Buffer node3318(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 336
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 336
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 336
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 336
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 336
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 336
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 336
    public static Buffer INTO(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 336
        buf.add(node3318(ctx));

        return buf;
    }
    // The following lines are generated from line 337
    public static Buffer node3333(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 337
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 337
        buf.add(ctx.getSymbol(buf, "J", new ArrayList<>()));

        // The following lines are generated from line 337
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 337
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 337
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 337
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 337
    public static Buffer JOIN(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 337
        buf.add(node3333(ctx));

        return buf;
    }
    // The following lines are generated from line 338
    public static Buffer node3348(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 338
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 338
        buf.add(ctx.getSymbol(buf, "K", new ArrayList<>()));

        // The following lines are generated from line 338
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 338
        buf.add(ctx.getSymbol(buf, "Y", new ArrayList<>()));

        // The following lines are generated from line 338
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 338
    public static Buffer KEY(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 338
        buf.add(node3348(ctx));

        return buf;
    }
    // The following lines are generated from line 339
    public static Buffer node3361(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 339
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 339
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 339
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 339
        buf.add(ctx.getSymbol(buf, "K", new ArrayList<>()));

        // The following lines are generated from line 339
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 339
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 339
    public static Buffer LIKE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 339
        buf.add(node3361(ctx));

        return buf;
    }
    // The following lines are generated from line 340
    public static Buffer node3376(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 340
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 340
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 340
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 340
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 340
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 340
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 340
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 340
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 340
    public static Buffer LINEAR(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 340
        buf.add(node3376(ctx));

        return buf;
    }
    // The following lines are generated from line 341
    public static Buffer node3395(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 341
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 341
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 341
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 341
        buf.add(ctx.getSymbol(buf, "C", new ArrayList<>()));

        // The following lines are generated from line 341
        buf.add(ctx.getSymbol(buf, "K", new ArrayList<>()));

        // The following lines are generated from line 341
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 341
    public static Buffer LOCK(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 341
        buf.add(node3395(ctx));

        return buf;
    }
    // The following lines are generated from line 342
    public static Buffer node3410(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "W", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "US", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "P", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "Y", new ArrayList<>()));

        // The following lines are generated from line 342
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 342
    public static Buffer LOW_PRIORITY(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 342
        buf.add(node3410(ctx));

        return buf;
    }
    // The following lines are generated from line 343
    public static Buffer node3441(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 343
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 343
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 343
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 343
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 343
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 343
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 343
    public static Buffer NONE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 343
        buf.add(node3441(ctx));

        return buf;
    }
    // The following lines are generated from line 344
    public static Buffer node3456(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 344
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 344
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 344
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 344
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 344
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 344
    public static Buffer NOT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 344
        buf.add(node3456(ctx));

        return buf;
    }
    // The following lines are generated from line 345
    public static Buffer node3469(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 345
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 345
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 345
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 345
        buf.add(ctx.getSymbol(buf, "W", new ArrayList<>()));

        // The following lines are generated from line 345
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 345
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 345
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 345
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 345
    public static Buffer NOWAIT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 345
        buf.add(node3469(ctx));

        return buf;
    }
    // The following lines are generated from line 346
    public static Buffer node3488(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 346
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 346
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 346
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 346
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 346
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 346
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 346
    public static Buffer NULL(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 346
        buf.add(node3488(ctx));

        return buf;
    }
    // The following lines are generated from line 347
    public static Buffer node3503(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 347
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 347
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 347
        buf.add(ctx.getSymbol(buf, "F", new ArrayList<>()));

        // The following lines are generated from line 347
        buf.add(ctx.getSymbol(buf, "F", new ArrayList<>()));

        // The following lines are generated from line 347
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 347
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 347
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 347
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 347
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 347
    public static Buffer OFFLINE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 347
        buf.add(node3503(ctx));

        return buf;
    }
    // The following lines are generated from line 348
    public static Buffer node3524(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 348
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 348
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 348
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 348
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 348
    public static Buffer ON(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 348
        buf.add(node3524(ctx));

        return buf;
    }
    // The following lines are generated from line 349
    public static Buffer node3535(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 349
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 349
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 349
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 349
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 349
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 349
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 349
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 349
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 349
    public static Buffer ONLINE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 349
        buf.add(node3535(ctx));

        return buf;
    }
    // The following lines are generated from line 350
    public static Buffer node3554(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 350
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 350
        buf.add(ctx.getSymbol(buf, "P", new ArrayList<>()));

        // The following lines are generated from line 350
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 350
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 350
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 350
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 350
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 350
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 350
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 350
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 350
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 350
    public static Buffer PARTITION(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 350
        buf.add(node3554(ctx));

        return buf;
    }
    // The following lines are generated from line 351
    public static Buffer node3579(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 351
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 351
        buf.add(ctx.getSymbol(buf, "P", new ArrayList<>()));

        // The following lines are generated from line 351
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 351
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 351
        buf.add(ctx.getSymbol(buf, "M", new ArrayList<>()));

        // The following lines are generated from line 351
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 351
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 351
        buf.add(ctx.getSymbol(buf, "Y", new ArrayList<>()));

        // The following lines are generated from line 351
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 351
    public static Buffer PRIMARY(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 351
        buf.add(node3579(ctx));

        return buf;
    }
    // The following lines are generated from line 352
    public static Buffer node3600(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 352
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 352
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 352
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 352
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 352
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 352
        buf.add(ctx.getSymbol(buf, "M", new ArrayList<>()));

        // The following lines are generated from line 352
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 352
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 352
    public static Buffer RENAME(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 352
        buf.add(node3600(ctx));

        return buf;
    }
    // The following lines are generated from line 353
    public static Buffer node3619(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 353
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 353
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 353
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 353
        buf.add(ctx.getSymbol(buf, "P", new ArrayList<>()));

        // The following lines are generated from line 353
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 353
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 353
        buf.add(ctx.getSymbol(buf, "C", new ArrayList<>()));

        // The following lines are generated from line 353
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 353
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 353
    public static Buffer REPLACE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 353
        buf.add(node3619(ctx));

        return buf;
    }
    // The following lines are generated from line 354
    public static Buffer node3640(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 354
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 354
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 354
        buf.add(ctx.getSymbol(buf, "C", new ArrayList<>()));

        // The following lines are generated from line 354
        buf.add(ctx.getSymbol(buf, "H", new ArrayList<>()));

        // The following lines are generated from line 354
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 354
        buf.add(ctx.getSymbol(buf, "M", new ArrayList<>()));

        // The following lines are generated from line 354
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 354
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 354
    public static Buffer SCHEMA(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 354
        buf.add(node3640(ctx));

        return buf;
    }
    // The following lines are generated from line 355
    public static Buffer node3659(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 355
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 355
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 355
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 355
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 355
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 355
        buf.add(ctx.getSymbol(buf, "C", new ArrayList<>()));

        // The following lines are generated from line 355
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 355
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 355
    public static Buffer SELECT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 355
        buf.add(node3659(ctx));

        return buf;
    }
    // The following lines are generated from line 356
    public static Buffer node3678(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 356
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 356
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 356
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 356
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 356
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 356
    public static Buffer SET(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 356
        buf.add(node3678(ctx));

        return buf;
    }
    // The following lines are generated from line 357
    public static Buffer node3691(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 357
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 357
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 357
        buf.add(ctx.getSymbol(buf, "H", new ArrayList<>()));

        // The following lines are generated from line 357
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 357
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 357
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 357
        buf.add(ctx.getSymbol(buf, "D", new ArrayList<>()));

        // The following lines are generated from line 357
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 357
    public static Buffer SHARED(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 357
        buf.add(node3691(ctx));

        return buf;
    }
    // The following lines are generated from line 358
    public static Buffer node3710(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 358
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 358
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 358
        buf.add(ctx.getSymbol(buf, "P", new ArrayList<>()));

        // The following lines are generated from line 358
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 358
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 358
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 358
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 358
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 358
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 358
    public static Buffer SPATIAL(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 358
        buf.add(node3710(ctx));

        return buf;
    }
    // The following lines are generated from line 359
    public static Buffer node3731(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 359
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 359
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 359
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 359
        buf.add(ctx.getSymbol(buf, "B", new ArrayList<>()));

        // The following lines are generated from line 359
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 359
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 359
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 359
    public static Buffer TABLE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 359
        buf.add(node3731(ctx));

        return buf;
    }
    // The following lines are generated from line 360
    public static Buffer node3748(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 360
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 360
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 360
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 360
        buf.add(ctx.getSymbol(buf, "M", new ArrayList<>()));

        // The following lines are generated from line 360
        buf.add(ctx.getSymbol(buf, "P", new ArrayList<>()));

        // The following lines are generated from line 360
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 360
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 360
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 360
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 360
        buf.add(ctx.getSymbol(buf, "Y", new ArrayList<>()));

        // The following lines are generated from line 360
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 360
    public static Buffer TEMPORARY(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 360
        buf.add(node3748(ctx));

        return buf;
    }
    // The following lines are generated from line 361
    public static Buffer node3773(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 361
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 361
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 361
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 361
        buf.add(ctx.getSymbol(buf, "X", new ArrayList<>()));

        // The following lines are generated from line 361
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 361
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 361
    public static Buffer TEXT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 361
        buf.add(node3773(ctx));

        return buf;
    }
    // The following lines are generated from line 362
    public static Buffer node3788(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 362
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 362
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 362
        buf.add(ctx.getSymbol(buf, "O", new ArrayList<>()));

        // The following lines are generated from line 362
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 362
    public static Buffer TO(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 362
        buf.add(node3788(ctx));

        return buf;
    }
    // The following lines are generated from line 363
    public static Buffer node3799(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 363
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 363
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 363
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 363
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 363
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 363
        buf.add(ctx.getSymbol(buf, "C", new ArrayList<>()));

        // The following lines are generated from line 363
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 363
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 363
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 363
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 363
    public static Buffer TRUNCATE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 363
        buf.add(node3799(ctx));

        return buf;
    }
    // The following lines are generated from line 364
    public static Buffer node3822(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 364
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 364
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 364
        buf.add(ctx.getSymbol(buf, "N", new ArrayList<>()));

        // The following lines are generated from line 364
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 364
        buf.add(ctx.getSymbol(buf, "Q", new ArrayList<>()));

        // The following lines are generated from line 364
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 364
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 364
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 364
    public static Buffer UNIQUE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 364
        buf.add(node3822(ctx));

        return buf;
    }
    // The following lines are generated from line 365
    public static Buffer node3841(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 365
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 365
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 365
        buf.add(ctx.getSymbol(buf, "P", new ArrayList<>()));

        // The following lines are generated from line 365
        buf.add(ctx.getSymbol(buf, "D", new ArrayList<>()));

        // The following lines are generated from line 365
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 365
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 365
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 365
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 365
    public static Buffer UPDATE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 365
        buf.add(node3841(ctx));

        return buf;
    }
    // The following lines are generated from line 366
    public static Buffer node3860(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 366
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 366
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 366
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 366
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 366
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 366
    public static Buffer USE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 366
        buf.add(node3860(ctx));

        return buf;
    }
    // The following lines are generated from line 367
    public static Buffer node3873(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 367
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 367
        buf.add(ctx.getSymbol(buf, "V", new ArrayList<>()));

        // The following lines are generated from line 367
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 367
        buf.add(ctx.getSymbol(buf, "L", new ArrayList<>()));

        // The following lines are generated from line 367
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 367
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 367
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 367
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 367
    public static Buffer VALUES(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 367
        buf.add(node3873(ctx));

        return buf;
    }
    // The following lines are generated from line 368
    public static Buffer node3892(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 368
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 368
        buf.add(ctx.getSymbol(buf, "V", new ArrayList<>()));

        // The following lines are generated from line 368
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 368
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 368
        buf.add(ctx.getSymbol(buf, "W", new ArrayList<>()));

        // The following lines are generated from line 368
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 368
    public static Buffer VIEW(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 368
        buf.add(node3892(ctx));

        return buf;
    }
    // The following lines are generated from line 369
    public static Buffer node3907(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 369
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 369
        buf.add(ctx.getSymbol(buf, "W", new ArrayList<>()));

        // The following lines are generated from line 369
        buf.add(ctx.getSymbol(buf, "A", new ArrayList<>()));

        // The following lines are generated from line 369
        buf.add(ctx.getSymbol(buf, "I", new ArrayList<>()));

        // The following lines are generated from line 369
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 369
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 369
    public static Buffer WAIT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 369
        buf.add(node3907(ctx));

        return buf;
    }
    // The following lines are generated from line 370
    public static Buffer node3922(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 370
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 370
        buf.add(ctx.getSymbol(buf, "W", new ArrayList<>()));

        // The following lines are generated from line 370
        buf.add(ctx.getSymbol(buf, "H", new ArrayList<>()));

        // The following lines are generated from line 370
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 370
        buf.add(ctx.getSymbol(buf, "R", new ArrayList<>()));

        // The following lines are generated from line 370
        buf.add(ctx.getSymbol(buf, "E", new ArrayList<>()));

        // The following lines are generated from line 370
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 370
    public static Buffer WHERE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 370
        buf.add(node3922(ctx));

        return buf;
    }
    // The following lines are generated from line 373
    public static Buffer node3939(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 373
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        // The following lines are generated from line 373
        buf.add(ctx.getSymbol(buf, "S", new ArrayList<>()));

        // The following lines are generated from line 373
        buf.add(ctx.getSymbol(buf, "T", new ArrayList<>()));

        // The following lines are generated from line 373
        buf.add(ctx.getSymbol(buf, "U", new ArrayList<>()));

        // The following lines are generated from line 373
        buf.add(ctx.getSymbol(buf, "B", new ArrayList<>()));

        // The following lines are generated from line 373
        buf.add(ctx.getSymbol(buf, "SPACE", new ArrayList<>()));

        return buf;
    }
    // The following lines are generated from line 373
    public static Buffer STUB(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 373
        buf.add(node3939(ctx));

        return buf;
    }
    // The following lines are generated from line 377
    public static Buffer LB(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 377
        buf.add(Variable.factory("("));

        return buf;
    }
    // The following lines are generated from line 378
    public static Buffer RB(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 378
        buf.add(Variable.factory(")"));

        return buf;
    }
    // The following lines are generated from line 379
    public static Buffer LT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 379
        buf.add(Variable.factory("<"));

        return buf;
    }
    // The following lines are generated from line 380
    public static Buffer GT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 380
        buf.add(Variable.factory(">"));

        return buf;
    }
    // The following lines are generated from line 381
    public static Buffer EQ(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 381
        buf.add(Variable.factory("="));

        return buf;
    }
    // The following lines are generated from line 382
    public static Buffer SC(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 382
        buf.add(Variable.factory(";"));

        return buf;
    }
    // The following lines are generated from line 383
    public static Buffer US(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 383
        buf.add(Variable.factory("_"));

        return buf;
    }
    // The following lines are generated from line 384
    public static Buffer DS(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 384
        buf.add(Variable.factory("-"));

        return buf;
    }
    // The following lines are generated from line 385
    public static Buffer ASTERISK(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 385
        buf.add(Variable.factory("*"));

        return buf;
    }
    // The following lines are generated from line 386
    public static Buffer DQ(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 386
        buf.add(Variable.factory("\""));

        return buf;
    }
    // The following lines are generated from line 387
    public static Buffer COMMA(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 387
        buf.add(Variable.factory(","));

        return buf;
    }
    // The following lines are generated from line 388
    public static Buffer DOT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 388
        buf.add(Variable.factory("."));

        return buf;
    }
    // The following lines are generated from line 390
    public static Buffer node4003(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(48);
        s.add(58);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 390
    public static Buffer DIGIT(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 390
        buf.add(node4003(ctx));

        return buf;
    }
    // The following lines are generated from line 391
    public static Buffer node4007(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(32);
        s.add(33);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 391
    public static Buffer SPACE(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 391
        buf.add(node4007(ctx));

        return buf;
    }
    // The following lines are generated from line 392
    public static Buffer node4011(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(10);
        s.add(11);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 392
    public static Buffer NL(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 392
        buf.add(node4011(ctx));

        return buf;
    }
    // The following lines are generated from line 393
    public static Buffer node4015(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(65);
        s.add(66);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 393
    public static Buffer A(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 393
        buf.add(node4015(ctx));

        return buf;
    }
    // The following lines are generated from line 394
    public static Buffer node4019(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(66);
        s.add(67);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 394
    public static Buffer B(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 394
        buf.add(node4019(ctx));

        return buf;
    }
    // The following lines are generated from line 395
    public static Buffer node4023(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(67);
        s.add(68);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 395
    public static Buffer C(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 395
        buf.add(node4023(ctx));

        return buf;
    }
    // The following lines are generated from line 396
    public static Buffer node4027(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(68);
        s.add(69);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 396
    public static Buffer D(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 396
        buf.add(node4027(ctx));

        return buf;
    }
    // The following lines are generated from line 397
    public static Buffer node4031(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(69);
        s.add(70);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 397
    public static Buffer E(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 397
        buf.add(node4031(ctx));

        return buf;
    }
    // The following lines are generated from line 398
    public static Buffer node4035(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(70);
        s.add(71);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 398
    public static Buffer F(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 398
        buf.add(node4035(ctx));

        return buf;
    }
    // The following lines are generated from line 399
    public static Buffer node4039(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(71);
        s.add(72);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 399
    public static Buffer G(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 399
        buf.add(node4039(ctx));

        return buf;
    }
    // The following lines are generated from line 400
    public static Buffer node4043(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(72);
        s.add(73);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 400
    public static Buffer H(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 400
        buf.add(node4043(ctx));

        return buf;
    }
    // The following lines are generated from line 401
    public static Buffer node4047(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(73);
        s.add(74);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 401
    public static Buffer I(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 401
        buf.add(node4047(ctx));

        return buf;
    }
    // The following lines are generated from line 402
    public static Buffer node4051(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(74);
        s.add(75);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 402
    public static Buffer J(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 402
        buf.add(node4051(ctx));

        return buf;
    }
    // The following lines are generated from line 403
    public static Buffer node4055(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(75);
        s.add(76);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 403
    public static Buffer K(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 403
        buf.add(node4055(ctx));

        return buf;
    }
    // The following lines are generated from line 404
    public static Buffer node4059(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(76);
        s.add(77);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 404
    public static Buffer L(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 404
        buf.add(node4059(ctx));

        return buf;
    }
    // The following lines are generated from line 405
    public static Buffer node4063(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(77);
        s.add(78);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 405
    public static Buffer M(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 405
        buf.add(node4063(ctx));

        return buf;
    }
    // The following lines are generated from line 406
    public static Buffer node4067(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(78);
        s.add(79);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 406
    public static Buffer N(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 406
        buf.add(node4067(ctx));

        return buf;
    }
    // The following lines are generated from line 407
    public static Buffer node4071(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(79);
        s.add(80);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 407
    public static Buffer O(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 407
        buf.add(node4071(ctx));

        return buf;
    }
    // The following lines are generated from line 408
    public static Buffer node4075(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(80);
        s.add(81);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 408
    public static Buffer P(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 408
        buf.add(node4075(ctx));

        return buf;
    }
    // The following lines are generated from line 409
    public static Buffer node4079(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(81);
        s.add(82);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 409
    public static Buffer Q(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 409
        buf.add(node4079(ctx));

        return buf;
    }
    // The following lines are generated from line 410
    public static Buffer node4083(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(82);
        s.add(83);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 410
    public static Buffer R(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 410
        buf.add(node4083(ctx));

        return buf;
    }
    // The following lines are generated from line 411
    public static Buffer node4087(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(83);
        s.add(84);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 411
    public static Buffer S(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 411
        buf.add(node4087(ctx));

        return buf;
    }
    // The following lines are generated from line 412
    public static Buffer node4091(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(84);
        s.add(85);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 412
    public static Buffer T(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 412
        buf.add(node4091(ctx));

        return buf;
    }
    // The following lines are generated from line 413
    public static Buffer node4095(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(85);
        s.add(86);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 413
    public static Buffer U(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 413
        buf.add(node4095(ctx));

        return buf;
    }
    // The following lines are generated from line 414
    public static Buffer node4099(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(86);
        s.add(87);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 414
    public static Buffer V(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 414
        buf.add(node4099(ctx));

        return buf;
    }
    // The following lines are generated from line 415
    public static Buffer node4103(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(87);
        s.add(88);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 415
    public static Buffer W(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 415
        buf.add(node4103(ctx));

        return buf;
    }
    // The following lines are generated from line 416
    public static Buffer node4107(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(88);
        s.add(89);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 416
    public static Buffer X(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 416
        buf.add(node4107(ctx));

        return buf;
    }
    // The following lines are generated from line 417
    public static Buffer node4111(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(89);
        s.add(90);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 417
    public static Buffer Y(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 417
        buf.add(node4111(ctx));

        return buf;
    }
    // The following lines are generated from line 418
    public static Buffer node4115(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(90);
        s.add(91);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 418
    public static Buffer Z(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 418
        buf.add(node4115(ctx));

        return buf;
    }
    // The following lines are generated from line 419
    public static Buffer node4119(Context ctx) throws Exception{
        List<Integer> s = new ArrayList<>();
        s.add(65);
        s.add(91);
        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));
    }
    // The following lines are generated from line 419
    public static Buffer CH(Context ctx) throws Exception{
        Buffer buf = new Buffer();
        // The following lines are generated from line 419
        buf.add(node4119(ctx));

        return buf;
    }


}