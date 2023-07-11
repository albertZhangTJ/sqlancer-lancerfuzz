package dsqlancer.AST;

public class AlternativeNode extends Node{
    private int rule_id;
    private int alternative_index;
    private int index;

    public AlternativeNode(int rule_id, int alternative_index, int index){
        this.rule_id = rule_id;
        this.alternative_index = alternative_index;
        this.index = index;
    }

    public int get_rule_id(){
        return this.rule_id;
    }

    public int get_alternative_index(){
        return this.alternative_index;
    }

    public int get_index(){
        return this.index;
    }
}
