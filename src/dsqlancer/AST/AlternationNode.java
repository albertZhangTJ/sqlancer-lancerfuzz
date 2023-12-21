package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;

import dsqlancer.Utils;

public class AlternationNode extends Node {
    private String rule_id;
    private int index;
    private List<String> conditions;
    private List<Double> weights;
    private int var_index; //if one of the branches is a var_ref, this is the index to that branch, 0-indexed


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

    public String toString(){
        return super.toString() + "\n    type: AlternationNode\n    weights: "+this.weights.toString();
    }

    // @Override
    // public int get_min_depth(){
    //     return super.get_min_depth()-1;
    // }
}
