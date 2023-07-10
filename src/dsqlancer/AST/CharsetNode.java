package dsqlancer.AST;

@SuppressWarnings("unused")
public class CharsetNode extends Node{
    private int rule_id;
    private int index;
    private int charset;

    public CharsetNode(int rule_id, int index, int charset){
        this.rule_id = rule_id;
        this.index = index;
        this.charset = charset;
    }
}
