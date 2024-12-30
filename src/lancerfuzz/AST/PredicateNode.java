package lancerfuzz.AST;
// Expect a child variable node with type of boolean
public class PredicateNode extends Node {
    private ActionNode src; // if it is {src}?
    private ArgNode argument; // if it is <argument>
    private boolean is_grammar_side;
    public PredicateNode(){
    }

    public String render(List<String> function_list, String padding, boolean print){
        if (is_grammar_side){
            return argument.render(function_list, "", false);
        }
        return src.render(function_list, "", false);
    }
    
}
