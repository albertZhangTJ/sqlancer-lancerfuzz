package SGL;

import java.util.List;

public class Stage{

    private String name;
    private List<String> rules;
    private List<Double> weights;
    private int min_statements;
    private int max_statements;

    public Stage(String name, List<String> rules, List<Double> weights, int min_statements, int max_statements){
        this.name = name;
        this.rules = rules;
        this.weights = weights;
        this.min_statements = min_statements;
        this.max_statements = max_statements;
    }

    public String get_name(){
        return this.name;
    }

    public List<String> get_rules(){
        return Utils.copy_list(this.rules);
    }

    public List<Double> get_weights(){
        return Utils.copy_list(weights);
    }

    public double get_total_weight(){
        double ans = 0;
        for (Double w : this.weights){
            ans = ans + w.doubleValue();
        }
        return ans;
    }

    public int get_min(){
        return this.min_statements;
    }

    public int get_max(){
        return this.max_statements;
    }

    public int get_num_rules(){
        return this.rules.size();
    }

    public String toString(){
        return "[Stage]\n    name: "+this.name+"\n    rules: "+this.rules+"\n    min: "+this.min_statements+"\n    max: "+this.max_statements;
    }
}