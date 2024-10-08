import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import sqlancer.SQLConnection;
public class Rig{
    public static class UnavailableException extends Exception {
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

        public void set(int index, Buffer child){
            this.children.set(index, child);
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
        private HashMap<String, Variable> globalSymbols;
        private HashMap<String, Variable> symbols;
        private List<HashMap<String, Variable>> symbolStack;
        private List<Variable> args;
        private List<String> errors;
        private Variable result;
        private SQLConnection conn;
        public static final List<String> OPERATORS = Collections.unmodifiableList(Arrays.asList("=", "+=", "+", "-", "==", "!=", ">", "<", ">=", "<="));

        private Buffer out;

        public Context(SQLConnection conn){
            this.symbols = new HashMap<>();
            this.symbolStack = new ArrayList<>();
            this.errors = new ArrayList<>();
            this.conn = conn;
        }

        public void addError(Variable v) throws IllegalArgumentException{
            try {
                this.errors.add(v.getValue());
            }
            catch (IllegalArgumentException e){
                throw new java.lang.IllegalArgumentException("ERROR: Fuzzer.Context.addError :: variable passed to expected error declaration is not single-valued, check your expected error declarations", e)
            }
        }

        public void push_args(List<Variable> args){
            this.args = args;
        }

        public void enter(List<String> arg_symbols, List<Variable> defaults) throws Exception{
            HashMap<String, Variable> newFrame = new HashMap<>();
            if (arg_symbols.size()!=defaults.size()){
                throw new IllegalArgumentException("ERROR: Fuzzer.Context.call :: internal error, argument list size does not match that of default list");
            }
            for (int i=0; i<arg_symbols.size(); i++){
                String symbol = arg_symbols.get(i);
                if (this.get_var(symbol)==null){
                    throw new UnavailableException("ERROR: Fuzzer.Context.call :: cannot find symbol "+symbol, true, false, false);
                }
                //TODO: complete the logic of selecting between pushed args and default, throw error if both unavailable
                newFrame.add(this.get_var(symbol));
            }

            //preserve current symbols
            this.symbolStack.add(this.symbols);
            this.symbols = newFrame;

            //set the return value to null to avoid confusion
            this.result = null;
        }


        public void ret(String returnSymbol){
            //move the return value into the cache slot
            if (returnSymbol!=null){
                this.result = this.getSymbol(returnSymbol);
            }

            //restore context for caller
            this.symbols = this.symbolStack.get(this.symbolStack.size()-1);
            this.symbolStack.remove(this.symbolStack.size()-1);
        }


        // the symbol here might be either a variable or a function
        // that is why the args arg this there, it won't even be looked at if it is actually a variable
        public Variable getSymbol(String symbol, List<String> args) throws IllegalArgumentException{
            if (symbol==null){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.getSymbol :: the symbol accessed is null, check your grammar");
            }
            if (symbol.equals("query")){
                return this.query(args);
            }
            if (this.symbols.get(symbol)==null && this.globalSymbols.get(symbol)==null){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.getSymbol :: the symbol \"" + symbol + "\" accessed does not exist or has not yet been initialized\n"+
                                                    "If you are using customized expansion order, please check your order specification\n"+
                                                    "If not, please make sure the symbol is there and consider specify the expansion ordering since the auto-scheduler is only best-effort");
            }
            return this.symbols.get(symbol)==null ? this.globalSymbols.get(symbol) : this.symbols.get(symbol);
        }

        //
        public Variable eval(Variable a, String operator, Variable b) throws IllegalArgumentException{
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
                return a;
            }
            if (operator.equals("+")){
                if (a.isNumerical() && b.isNumerical()){
                    return Variable.factory(a.getNumerical() + b.getNumerical());
                }
                return Variable.factory(a.getValue()+b.getValue());
            }
            if (operator.equals("-")){
                if (a.isNumerical() && b.isNumerical()){
                    return Variable.factory(a.getNumerical() - b.getNumerical());
                }
                throw new IllegalArgumentException("ERROR : Context.eval :: the operator \"-\" is not applicable when either side of the operation is not numerical");
            }
            return Variable.factory(a.compare(operator, b));
        }


        public void setSymbol(String symbol, Variable v){
            if (symbol==null || symbol.size()==0 || !((symbol.charAt(0)>=65 && symbol.charAt(0)<=90) || (symbol.charAt(0)>=97 && symbol.charAt(0)<=122))){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.setSymbol :: symbol must be non-empty and start with an ASCII letter");
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
        public Variable query(List<Variable> args){
            if (args.size()<2){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.query :: a call to the query function must contain at least 2 arguments: query and column name");
            }
            String query = args.get(0).getValue();
            String col = args.get(1).getValue();
            Variable v = Variable.of();
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                Variable r = Variable.of(rs.getString(col));
                for (int i=2; i<args.size(); i++){
                    Variable pair = args.get(i);
                    String attrCol = pair.getEntry(0);
                    String attr = pair.getEntry(1);
                    r.setAttr(attr, Variable.of(rs.getString(attrCol)));
                }
                v.addEntry(r);
            }
            return v;
        }
    }

    public static class Variable {
        public boolean isSingleValued;
        private String value;
        private int numerical;
        private boolean bool;
        private boolean containsNumerical;
        private boolean containsBoolean;
        private List<Variable> entries;
        private List<Integer> uniqueUsageCount;
        private HashMap<String, Variable> attributes;
        private int cursor; //non-decreasing, modulus entries.size() will be used for extracting index 

        public static final List<String> RESERVED_ATTR = Collections.unmodifiableList(Arrays.asList("new", "any", "next", "len", "unique_any", "query", "filter", "cur"));
        
        public Variable(){
            this.isSingleValued = false;
            this.containsNumerical = false;
            this.containsBoolean = false;
            this.entries = new ArrayList<>();
            this.uniqueUsageCount = new ArrayList<>();
            this.attributes = new HashMap<>();
            this.cursor = 0;
        }
        public Variable(String value){
            this.values = value;
            this.isSingleValued = true;
            this.containsNumerical = false;
            this.containsBoolean = false;
            this.entries = new ArrayList<>();
            this.uniqueUsageCount = new ArrayList<>();
            this.attributes = new HashMap<>();
            this.cursor = 0;
        }
        public Variable(int numerical){
            this.value = ""+numerical;
            this.numerical = numerical;
            this.isSingleValued = true;
            this.containsNumerical = true;
            this.containsBoolean = false;
            this.entries = new ArrayList<>();
            this.uniqueUsageCount = new ArrayList<>();
            this.attributes = new HashMap<>();
            this.cursor = 0;
        }
        public Variable(boolean bool){
            this.value = ""+bool;
            this.bool = bool;
            this.isSingleValued = true;
            this.containsNumerical = false;
            this.containsBoolean = true;
            this.entries = new ArrayList<>();
            this.uniqueUsageCount = new ArrayList<>();
            this.attributes = new HashMap<>();
            this.cursor = 0;
        }
        //by returning this at the end of the setter, we can use this class in an FP way
        public Variable setAttr(String attr_name, Variable value) throws IllegalArgumentException{
            if (RESERVED_ATTR.contains(attr_name)){
                throw new IllegalArgumentException("ERROR: "+attr_name+" is a reserved attribute name, please use another name instead");
            }
            this.attributes.put(attr_name, value);
            return this;
        }

        public static Variable factory(){
            return new Variable();
        }

        public static Variable factory(String value){
            return new Variable(value);
        }
        public static Variable factory(int numerical){
            return new Variable(numerical);
        }
        public static Variable factory(boolean bool){
            return new Variable(bool);
        }
        
        //make the current variable a shallow-copy of other
        //the cursor will be set to 0
        //mainly used for assignment (i.e. a=b will be context.getSymbol("a").clone(context.getSymbol("b")))
        public void clone(Variable other){
            this.isSingleValued = other.isSingleValued;
            this.value = other.value;
            this.numerical = other.numerical;
            this.bool = other.bool;
            this.containsNumerical = other.containsNumerical;
            this.containsBoolean = other.containsBoolean;
            this.entries = copy_list(other.entries);
            this.uniqueUsageCount = ArrayList<>();
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
                return random_from_list(entries);
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
                int idx = random_from_list(avail_idx);
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
                return this.filter(args.get(0).getValue(), args.get(1).getValue(), args.get(2));
            }

        }
        private Variable filter(String attr, String comparator, Variable target) throws Exception{
            if (this.isSingleValued){
                throw new IllegalArgumentException("Fuzzer.Variable.filter :: filter operation is not allowed on single-valued variables");
            }
            Variable result = new Variable();
            for (Variable v : this.entries){
                if (v.getAttr(attr).compare(comparator, target)){
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
                        "Fuzzer.Variable.compare :: >= comparator expects both variables to contain numerical value\n"+e.getMessage(), 
                        e
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
                        "Fuzzer.Variable.compare :: <= comparator expects both variables to contain numerical value\n"+e.getMessage(), 
                        e
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
                        "Fuzzer.Variable.compare :: > comparator expects both variables to contain numerical value\n"+e.getMessage(), 
                        e
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
                        "Fuzzer.Variable.compare :: < comparator expects both variables to contain numerical value\n"+e.getMessage(), 
                        e
                    );
                }
            }
            throw new IllegalArgumentException("Fuzzer.Variable.compare :: comparator "+comparator+" is not recognizable");
        }
        public String getValue() throws IllegalArgumentException{
            if (!this.isSingleValued){
                throw new IllegalArgumentException("Fuzzer.Variable.getValue :: getValue is not applicable to multi-valued variable");
            }
            return this.value;
        }
        public boolean isNumerical(){
            return this.containsNumerical;
        }

        public int getNumerical(){
            return thus.numerical;
        }
        public boolean isBoolean(){
            return this.containsBoolean;
        }
        public boolean getBoolean(){
            if (!this.containsBoolean){
                throw new IllegalArgumentException("Fuzzer.Variable.getBoolean :: the current variable is not a boolean one");
            }
            return this.bool;
        }

        //by returning this at the end of the setter, we can use this class in an FP way
        public Variable addEntry(Variable v) throws IllegalArgumentException{
            if (this.isSingleValued){
                throw new IllegalArgumentException("Fuzzer.Variable.addEntry :: addEntry is not applicable to single-valued variable");
            }
            this.entries.add(v);
            this.uniqueUsageCount.add(0);
            return this;
        }
        public Variable getEntry(int idx)throws IllegalArgumentException{
            if (this.isSingleValued){
                throw new IllegalArgumentException("Fuzzer.Variable.addEntry :: addEntry is not applicable to single-valued variable");
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
}