package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

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

    public RuleNode(String name, String label, RuleNodeType type){
        super(name, label);
        this.name = name;
        this.type = type;

        this.labels = new HashMap<String, String>();
        this.args = new HashMap<String, String>();
        this.locals = new HashMap<String, String>();
        this.returns = new HashMap<String, String>();
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
}
