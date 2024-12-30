// An argument will be either an ExpressionNode or a VariableNode
package lancerfuzz.AST;
import java.util.List;

public class ArgNode extends Node{
    private ExpressionNode a;
    private VariableNode b;
    private boolean is_expr;

    public String render(List<String> function_list, String padding, boolean print){
        return is_expr ?
                a.render(function_list, padding, print) :
                b.render(function_list, padding, print) ;
    } 
    
}
