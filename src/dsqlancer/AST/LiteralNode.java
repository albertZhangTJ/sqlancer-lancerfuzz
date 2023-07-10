package dsqlancer.AST;

@SuppressWarnings("unused")
public class LiteralNode extends Node{
    private String src;
    public LiteralNode(String src){
        this.src = src;
    }
}
