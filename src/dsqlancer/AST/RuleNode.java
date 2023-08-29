package dsqlancer.AST;

import java.util.List;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import dsqlancer.Utils;

public class RuleNode extends Node{
    public static enum RuleNodeType {UNLEXER, UNPARSER};
    private String name;
    private RuleNodeType type;
    private int min_depth;


    // Not entirely sure what these are for and what type should they have
    // TODO: further investigation needed here
    private HashMap<String, String> labels;
    private HashMap<String, String> args;
    private HashMap<String, String> locals;
    private HashMap<String, String> returns; 

    private boolean is_schema; // whether the current rule is a schema reference
    private String parent_type;
    private String query;

    public RuleNode(String name, String label, RuleNodeType type){
        super(name, label);
        this.name = name;
        this.type = type;

        this.labels = new HashMap<String, String>();
        this.args = new HashMap<String, String>();
        this.locals = new HashMap<String, String>();
        this.returns = new HashMap<String, String>();

        this.is_schema = false;
        this.parent_type = null;
        this.query = null;
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

    public int get_min_depth(){
        return this.min_depth;
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
        Pattern pq = Pattern.compile("String\\s{1,}query");
        Pattern pp = Pattern.compile("String\\s{1,}parent_type");
        Pattern ps = Pattern.compile("boolean\\s{1,}is_schema");
        for (String key : key_set){
            if (pq.matcher(key.strip()).find() || pp.matcher(key.strip()).find() || ps.matcher(key.strip()).find()){
                this.locals.remove(key);
            }
        }
    }

    // The handling of find these is done at the graph level since we also want a sanity check
    // to make sure that parent_type exists in the AST
    public void set_schema_reference(String parent_type, String schema_query){
        this.is_schema = true;
        this.parent_type = parent_type;
        this.query = schema_query;
        this.remove_schema_locals();
    }

    public boolean is_schema_ref(){
        return this.is_schema;
    }

    public String get_parent_type(){
        return this.parent_type;
    }

    public String get_query_stmt(){
        return this.query;
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
