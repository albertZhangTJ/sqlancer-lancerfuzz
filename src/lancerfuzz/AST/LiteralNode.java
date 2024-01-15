package lancerfuzz.AST;

public class LiteralNode extends Node{
    private String src;
    public LiteralNode(String src){
        this.src = src;
    }

    public String get_src(){
        return this.src;
    }
}
