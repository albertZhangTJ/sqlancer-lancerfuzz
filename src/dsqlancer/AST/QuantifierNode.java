package dsqlancer.AST;


//Defines how many repetition a rule should have
public class QuantifierNode extends Node{
    private int rule_id;
    private int index;
    private int min;
    private int max;
    
    public QuantifierNode(int rule_id, int index, int min, int max){
        this.rule_id = rule_id;
        this.index = index;
        this.min = min;
        this.max = max;
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

    // @Override
    // public int get_min_depth(){
    //     return super.get_min_depth()-1;
    // }
    
}
