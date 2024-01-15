package lancerfuzz.AST;

public class VariableNode extends Node{
    private String name;
    private boolean is_list;

    public VariableNode(String name, boolean is_list){
        this.name = name;
        this.is_list = is_list;
    }

    public String get_name(){
        return this.name;
    }

    public boolean get_is_list(){
        return this.is_list;
    }
}
