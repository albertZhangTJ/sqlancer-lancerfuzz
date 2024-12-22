package lancerfuzz.AST;

import java.util.List;
import java.util.ArrayList;

import lancerfuzz.Utils;

public class AlternationNode extends Node {
    private String rule_id;
    private int index;
    private List<String> conditions;
    private List<Double> weights;
    private int var_index; //if one of the branches is a var_ref, this is the index to that branch, 0-indexed

    private boolean is_expr;


    public AlternationNode(String rule_id, int index, List<String> conditions){
        this.rule_id = rule_id;
        this.index = index;
        this.conditions = conditions;
        this.weights = new ArrayList<>();
        this.var_index = -1;
    }

    public String get_rule_id(){
        return this.rule_id;
    }

    public int get_index(){
        return this.index;
    }

    public List<String> get_conditions(){
        return Utils.copy_list(this.conditions);
    }
    // Since weights are added during post-processing
    // We only setup a placeholder list of default weights here
    @Override
    public void add_outward_edge(Edge edge){
        super.add_outward_edge(edge);
        weights.add(Double.valueOf(1));
    }

    public void set_weight(double weight, Node child){
        List<Edge> children = super.get_outward_edges();
        for (int i=0; i<children.size(); i++){
            if (children.get(i).get_dest().equals(child)){
                this.weights.set(i, weight);
                return;
            }
        }
        Utils.panic("AlternationNode::set_weight : Internal Error, Cannot find child "+child.toString());
    }

    public void set_var_index(Node child){
        if (this.var_index!=-1){    
            Utils.panic("AlternationNode::set_var_index : One alternation node can only have one variable reference");
        }
        List<Edge> children = super.get_outward_edges();
        for (int i=0; i<children.size(); i++){
            if (children.get(i).get_dest().equals(child)){
                this.var_index=i;
                return;
            }
        }
        Utils.panic("AlternationNode::set_var_index : Internal Error, Cannot find child "+child.toString());
    }
    

    public List<Double> get_weights(){
        return Utils.copy_list(this.weights);
    }

    public Double get_total_weight(){
        double ans = 0;
        for (Double d : this.weights){
            ans = ans + d;
        }
        return ans;
    }

    public void set_expr(){
        this.is_expr = true;
    }

    public boolean get_is_expr(){
        return this.is_expr;
    }

    public String toString(){
        return super.toString() + "\n    type: AlternationNode\n    weights: "+this.weights.toString();
    }

    public List<String> render(List<String> function_list, List<String> args, String padding, boolean print){
        String handle = padding + "buf.add(node"+this.get_id()+"(ctx));\n";
        List<String> ans = new ArrayList<>();
        ans.add(handle);

        //this our own function to be added to the function list
        String indentation = "    ";
        String code = indentation + "public Buffer node"+this.get_id()+"(Context ctx){\n}";
        code = code + indentation + indentation + "Buffer buf = new Buffer();\n";
        code = code + indentation + indentation + "Options opt = new Options();\n";
        

    }

    // @Override
    // public int get_min_depth(){
    //     return super.get_min_depth()-1;
    // }
}
