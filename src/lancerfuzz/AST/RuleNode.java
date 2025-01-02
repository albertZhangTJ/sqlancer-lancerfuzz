package lancerfuzz.AST;

import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import lancerfuzz.Utils;

public class RuleNode extends Node{
    public static enum RuleNodeType {UNLEXER, UNPARSER};
    private String name;
    private RuleNodeType type;
    
    private HashMap<String, String> labels;
    private HashMap<String, String> args;
    private HashMap<String, String> locals;
    private HashMap<String, String> returns; 

    private boolean is_schema; // whether the current rule is a schema reference
    private String query;
    private String attribute_name;


    private boolean is_expr; //If the current node is an expression definition
    
    private boolean is_dependent; //will restore the stack frame of the previous statement

    private boolean is_component; //within the same statement, do not create a new stack frame when calling
    
    public RuleNode(String name, String label, RuleNodeType type){
        super(name, label);
        this.name = name;
        this.type = type;

        this.labels = new HashMap<String, String>();
        this.args = new HashMap<String, String>();
        this.locals = new HashMap<String, String>();
        this.returns = new HashMap<String, String>();

        this.is_schema = false;
        this.query = null;
        this.attribute_name = null;

        this.is_expr = false;

        this.is_dependent = false;
    } 

    public boolean has_var(){
        return (this.labels != null && this.labels.size()>0) || 
                this.args.size()>0 ||
                this.locals.size()>0 ||
                this.returns.size()>0;
    }

    public List<HashMap<String, String>> attributes(){
        List<HashMap<String, String>> ans = new ArrayList<>();
        ans.add(Utils.copy_map(args));
        ans.add(Utils.copy_map(locals));
        ans.add(Utils.copy_map(returns));

        return ans;
    }

    public String get_name(){
        return this.name;
    }

    public RuleNodeType get_type(){
        return this.type;
    }


    public HashMap<String, String> get_labels(){
        return Utils.copy_map(this.labels);
    }

    public HashMap<String, String> get_args(){
        return Utils.copy_map(this.args);
    }

    public HashMap<String, String> get_locals(){
        return Utils.copy_map(this.locals);
    }

    public HashMap<String, String> get_returns(){
        return Utils.copy_map(this.returns);
    }

    public void set_args(HashMap<String, String> args){
        this.args = args;
    }

    public void set_locals(HashMap<String, String> locals){
        this.locals = locals;
    }

    public void set_returns(HashMap<String, String> returns){
        this.returns = returns;
    }

    public void add_label(String key, String value){
        this.labels.put(key, value);
    }


    private void remove_schema_locals(){
        Set<String> key_set = this.locals.keySet();
        Pattern pq = Pattern.compile("query");
        Pattern pa = Pattern.compile("attribute_name");
        Pattern ps = Pattern.compile("is_schema");
        List<String> keys_to_remove = new ArrayList<>();
        for (String key : key_set){
            if (pq.matcher(key.strip()).find() || ps.matcher(key.strip()).find() || pa.matcher(key.strip()).find()){
                keys_to_remove.add(key);
            }
        }
        for (String key : keys_to_remove){
            this.locals.remove(key);
        }
    }

    private void remove_expr_locals(){
        Set<String> key_set = this.locals.keySet();
        Pattern pq = Pattern.compile("query");
        Pattern pa = Pattern.compile("attribute_name");
        Pattern ps = Pattern.compile("is_expr");
        List<String> keys_to_remove = new ArrayList<>();
        for (String key : key_set){
            if (pq.matcher(key.strip()).find() || ps.matcher(key.strip()).find() || pa.matcher(key.strip()).find()){
                keys_to_remove.add(key);
            }
        }
        for (String key : keys_to_remove){
            this.locals.remove(key);
        }
    }

    private void remove_dependent_locals(){
        Set<String> key_set = this.locals.keySet();
        Pattern p = Pattern.compile("is_dependent");
        List<String> keys_to_remove = new ArrayList<>();
        for (String key : key_set){
            if (p.matcher(key.strip()).find()){
                keys_to_remove.add(key);
            }
        }
        for (String key : keys_to_remove){
            this.locals.remove(key);
        }
    }

    private void remove_component_local(){
        Set<String> key_set = this.locals.keySet();
        Pattern p = Pattern.compile("is_component");
        List<String> keys_to_remove = new ArrayList<>();
        for (String key : key_set){
            if (p.matcher(key.strip()).find()){
                keys_to_remove.add(key);
            }
        }
        for (String key : keys_to_remove){
            this.locals.remove(key);
        }
    }
    
    public void set_schema_reference(String schema_query, String attribute_name){
        this.is_schema = true;
        this.query = schema_query;
        //Utils.log("Query set: "+schema_query);
        this.attribute_name = attribute_name;
        //Utils.log("Attribute name set: "+attribute_name);
        this.remove_schema_locals();
    }

    public void set_expr_reference(String query, String attribute_name){
        this.is_expr = true;
        this.query = query;
        //Utils.log("Query set: "+schema_query);
        this.attribute_name = attribute_name;
        //Utils.log("Attribute name set: "+attribute_name);
        this.remove_expr_locals();
    }

    public void set_is_dependent(boolean is_dependent){
        this.is_dependent = is_dependent;
        this.remove_dependent_locals();
    }

    public void set_is_component(boolean is_component){
        this.is_component = is_component;
        this.remove_component_local();
    }

    public boolean is_schema_ref(){
        return this.is_schema;
    }

    public boolean is_expr(){
        return this.is_expr;
    }

    public boolean get_is_dependent(){
        return this.is_dependent;
    }

    public boolean get_is_component(){
        return this.is_component;
    }
    
    public String get_query_stmt(){
        return this.query;
    }

    public String get_attribute_name(){
        return this.attribute_name;
    }

    //This is just a placeholder for render function
    //Should not be executed in real life
    //Did not set to abstract to save some work for temporary IR nodes
    public String render(List<String> function_list, String paddng, boolean print){
        Utils.panic("RuleNode::render : Internal error, you should never have got here");
        return null;
    }

    public String toString(){
        String label="\n";
        String arg="\n";
        String local="\n";
        String ret="\n";
        for (String k : this.labels.keySet()){
            label = label + "        " + k + ": " + this.labels.get(k)+"\n";
        }
        for (String k : this.args.keySet()){
            arg = arg + "        " + k + ": " + this.args.get(k)+"\n";
        }
        for (String k : this.locals.keySet()){
            local = local + "        " + k + ": " + this.locals.get(k)+"\n";
        }
        for (String k : this.returns.keySet()){
            ret = ret + "        " + k + ": " + this.returns.get(k)+"\n";
        }
        return super.toString()+"\n"+"    type: RuleNode\n    name: "+this.name+"\n    labels: "+label+"    args: "+arg+"    local: "+local+"    returns: "+ret;
    }
}
