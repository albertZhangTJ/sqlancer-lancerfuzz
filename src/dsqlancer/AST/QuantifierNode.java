package dsqlancer.AST;

import dsqlancer.Utils;

//Defines how many repetition a rule should have
public class QuantifierNode extends Node{
    private int rule_id;
    private int index;
    private int min;
    private int max;
    private String rp_id;

    // The max repetition when RP_LIMIT is not set
    // Not a hard upper limit (as in, upper limit set by RP_LIMIT can be higher than this)
    public static final int DEFAULT_MAX_REP = 8; 
    
    public QuantifierNode(int rule_id, int index, int min, int max){
        this.rule_id = rule_id;
        this.index = index;
        this.min = min;
        this.max = max;
        rp_id = null;
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

    public void update_repetition(int min, int max){
        if (min<this.min){
            Utils.panic("QuantifierNode::update_repetition : repetition lower limit set by RP_LIMIT is smaller than original one");
        }
        if (this.max==1 && max>this.max){
            Utils.panic("QuantifierNode::update_repetition : Original repetition upper limit is 1, new upper limit is "+max+", which is likely a violation of the EBNF suffix");

        }
        this.min = min;
        this.max = max;
    }

    public void set_rp_id(String rp_id){
        if (this.rp_id!=null){
            Utils.panic("QuantifierNode::set_rp_id : Redefinition of RP_ID, new RP_ID is \""+rp_id+"\", original one was \""+this.rp_id+"\"");
        }
        this.rp_id = rp_id;

    }

    public String get_rp_id(){
        return this.rp_id;
    }
    // @Override
    // public int get_min_depth(){
    //     return super.get_min_depth()-1;
    // }
    
}
