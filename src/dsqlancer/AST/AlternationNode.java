package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;

import dsqlancer.Utils;

public class AlternationNode extends Node {
    private String rule_id;
    private int index;
    private List<String> conditions;
    private List<Double> weights;
    private int min_depth;

    public AlternationNode(String rule_id, int index, List<String> conditions){
        this.rule_id = rule_id;
        this.index = index;
        this.conditions = conditions;
        this.min_depth = -1;
        this.weights = new ArrayList<>();
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

    public int get_min_depth(){
        return this.min_depth;
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
        Utils.oops("AlternationNode::set_weight : Cannot find child "+child.toString());
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
