package SGL;

import java.util.List;

public class Stage{

    private String name;
    private List<String> rules;
    private List<Integer> weights;
    private int min_statements;
    private int max_statements;

    public Stage(String name, List<String> rules, List<Integer> weights, int min_statements, int max_statements){
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

    public List<Integer> get_weights(){
        return Utils.copy_list(weights);
    }

    // public int get_total_weight(){
    //     int ans = 0;
    //     for (Integer w : this.weights){
    //         ans = ans + w.intValue();
    //     }
    //     return ans;
    // }

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

    public String render(){
        String result = "                 // STAGE: " + this.name + "\n";
        result = result + "             rule = new ArrayList<>();\n"+
                          "             weight = new ArrayList<>();\n" +
                          "             count = new ArrayList<>();\n";
        for (String rule : this.rules){
            result = result + "             rule.add(\"" + rule +"\");\n";
        }
        for (Integer weight : this.weights){
            result = result + "             weight.add(" + weight +");\n";
        }
        result = result + "             count.add(" + this.min_statements +");\n";
        result = result + "             count.add(" + this.max_statements +");\n";
        result = result + "             rules.add(rule);\n";
        result = result + "             weights.add(weight);\n";
        result = result + "             counts.add(count);\n";
        return result;
    }
}