package lancerfuzz.AST;

public class CharsetNode extends Node{
    private int rule_id;
    private int index;
    private int charset;

    public CharsetNode(int rule_id, int index, int charset){
        this.rule_id = rule_id;
        this.index = index;
        this.charset = charset;
    }

    public int get_rule_id(){
        return this.rule_id;
    }

    public int get_index(){
        return this.index;
    }

    public int get_charset(){
        return this.charset;
    }
}
