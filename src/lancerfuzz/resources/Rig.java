import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
        private Variable result;
        private SQLConnection conn;
        
        public Context(SQLConnection conn){
            this.symbols = new HashMap<>();
            this.symbolStack = new ArrayList<>();
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
            this.symbolStack.add(this.symbols);
            this.symbols = newFrame;

            //set the return value to null to avoid confusion
            this.result = null;
        }

        public void ret(String returnSymbol){
            //move the return value into the cache slot
            if (returnSymbol!=null){
                this.result = this.get_var(returnSymbol);
            }

            //restore context for caller
            this.symbols = this.symbolStack.get(this.symbolStack.size()-1);
            this.symbolStack.remove(this.symbolStack.size()-1);
        }


        // the symbol here might be either a variable or a function
        // that is why the args arg this there, it won't even be looked at if it is actually a variable
        public Variable getSymbol(String symbol, List<String> args) throws IllegalArgumentException{
            if (symbol==null){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.get_var :: the symbol accessed is null, check your grammar");
            }
            if (symbol.equals("query")){
                return this.query(args);
            }
            if (this.symbols.get(symbol)==null && this.globalSymbols.get(symbol)==null){
                throw new IllegalArgumentException("ERROR : Fuzzer.Context.get_var :: the symbol " + symbol + " accessed does not exists or has not yet been initialized\n"+
                                                    "If you are using customized expansion order, please check your order specification\n"+
                                                    "If not, please make sure the symbol is there and consider specify the expansion ordering since the auto-scheduler is only best-effort");
            }
            return this.symbols.get(symbol)==null ? this.globalSymbols.get(symbol) : this.symbols.get(symbol);
        }

        public Variable query(List<String> args){
            Variable res = new Variable();
            ResultSet ans = this.con.createStatement().executeQuery(query);
        }
    }

    public static class Variable {
        public boolean isSingleValued;
        private String value;
        private List<String> entries;
        private List<Integer> uniqueUsageCount;
        private HashMap<String, Variable> attributes;
        private int cursor; //non-decreasing, modulus entries.size() will be used for extracting index 
        public static final List<String> RESERVED_ATTR = Collections.unmodifiableList(Arrays.asList("new", "any", "next", "len", "unique_any", "query", "filter", "cur"));

        public Variable(){
            this.isSingleValued = false;
            this.entries = new ArrayList<>();
            this.uniqueUsageCount = new ArrayList<>();
            this.attributes = new HashMap<>();
            this.cursor = 0;
        }
        public Variable(String value){
            this.values = value;
            this.isSingleValued = true;
            this.entries = new ArrayList<>();
            this.uniqueUsageCount = new ArrayList<>();
            this.attributes = new HashMap<>();
            this.cursor = 0;
        }
        public void setAttr(String attr_name, Variable value){
            if (RESERVED_ATTR.contains(attr_name)){
                System.out.println("ERROR: "+attr_name+" is a reserved attribute name, please use another name instead");
                System.exit(1);
            }
            this.attributes.put(attr_name, value);
        }

        // new, query, getColumn, withColumnAsAttr will not be handled as those are not attributes but functions
        // those will be implemented in the Context class
        public Variable getAttr(String name, List<String> args) throws Exception{
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
                    return new Variable(String.valueOf(1));
                }
                return new Variable(String.valueOf(this.entries.size()));
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
                return this.filter(args.get(0), args.get(1), args.get(2));
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
        public String getValue(){
            if (!this.isSingleValued){
                throw new IllegalArgumentException("Fuzzer.Variable.getValue :: getValue is not applicable to multi-valued variable");
            }
            return this.value;
        }
        public void addEntry(Variable v){
            if (this.isSingleValued){
                throw new IllegalArgumentException("Fuzzer.Variable.addEntry :: addEntry is not applicable to single-valued variable");
            }
            this.entries.add(v);
            this.uniqueUsageCount.add(0);
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
}