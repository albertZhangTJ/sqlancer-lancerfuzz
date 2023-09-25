package dsqlancer.AST;


//Defines how many repetition a rule should have
public class QuantifierNode extends Node{
    private int rule_id;
    private int index;
    private int min;
    private int max;
    private int min_depth;
    
    public QuantifierNode(int rule_id, int index, int min, int max){
        this.rule_id = rule_id;
        this.index = index;
        this.min = min;
        this.max = max;
        this.min_depth = -1;
    }

    public int get_rule_id(){
        return this.rule_id;
    }

    public int get_index(){
        return this.index;
    }

    public int get_min(){
        return this.min;
    }

    public int get_max(){
        return this.max;
    }

    public int get_min_depth(){
        return this.min_depth;
    }

    // @Override
    // public int get_min_depth(){
    //     return super.get_min_depth()-1;
    // }
    
}
