package lancerfuzz.AST;
public class PredicateNode extends Node {
    private String src;
    public PredicateNode(String src){
        this.src = src;
    }
    public String get_src(){
        return this.src;
    }

    
}
