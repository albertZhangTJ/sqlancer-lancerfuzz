package lancerfuzz.AST;

import lancerfuzz.Utils;

//Defines how many repetition a rule should have
public class QuantifierNode extends Node{
    private int rule_id;
    private int index;
    private int type; //0 for ?, 1 for +, 2 for *, 3 for **
    
    public QuantifierNode(int rule_id, int index, int min, int max){
        this.rule_id = rule_id;
        this.index = index;
        this.min = min;
        this.max = max;
        rp_id = null;
        this.use_uniform = false;
        this.decay_rate = DEFAULT_DECAY_RATE;
    }

    public int get_rule_id(){
        return this.rule_id;
    }

    public int get_index(){
        return this.index;
    }

    public int get_type(){
        return this.type;
    }
    
}
