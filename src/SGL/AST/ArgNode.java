// An argument will be either an ExpressionNode or a VariableNode
package SGL.AST;

import SGL.parser.SGLParser.ArgContext;

public class ArgNode extends Node{
    // private ExpressionNode a;
    // private VariableNode b;
    // private boolean is_expr;

    //We die-die (singlish for definitely, to whoever is not working in SG) don't want to have the whole depth of the parse tree reflected in rendering
    //so this might short-circuit all the way down to a VariableNode
    //or in the worst case, be the root of a very complicated ExpressionNode
    //we don't know yet
    public static Node build(GrammarGraph graph, ArgContext arg){
        Node actual = ExpressionNode.build(graph, arg.expression());
        return actual;
    }

    // public String render(List<String> function_list, String padding, boolean print){
    //     return is_expr ?
    //             a.render(function_list, padding, print) :
    //             b.render(function_list, padding, print) ;
    // } 
    
}
