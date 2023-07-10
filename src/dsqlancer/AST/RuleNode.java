package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;

import dsqlancer.Utils;

@SuppressWarnings("unused")
public class RuleNode extends Node{
    public static enum RuleNodeType {UNLEXER, UNPARSER};
    private String name;
    private RuleNodeType type;
    private int min_depth;


    // Not entirely sure what these are for and what type should they have
    // TODO: further investigation needed here
    private List<String> labels;
    private List<String> args;
    private List<String> locals;
    private List<String> returns; 

    public RuleNode(String name, List<String> labels, RuleNodeType type){
        this.name = name;
        this.labels = labels;

        this.args = new ArrayList<>();
        this.locals = new ArrayList<>();
        this.returns = new ArrayList<>();
    } 

    public boolean has_var(){
        return (this.labels != null && this.labels.size()>0) || 
                this.args.size()>0 ||
                this.locals.size()>0 ||
                this.returns.size()>0;
    }

    public List<List<String>> attributes(){
        List<List<String>> ans = new ArrayList<>();
        ans.add(Utils.copy_list(args));
        ans.add(Utils.copy_list(locals));
        ans.add(Utils.copy_list(returns));

        return ans;
    }
}
