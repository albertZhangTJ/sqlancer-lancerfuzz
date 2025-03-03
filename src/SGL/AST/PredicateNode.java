package SGL.AST;

import SGL.Utils;

import java.util.List;

import SGL.parser.SGLParser.PredicateContext;

// Expect a child variable node with type of boolean
public class PredicateNode extends Node {
    private ActionNode src; // if it is {src}?
    private Node argument; // if it is <argument>
    private boolean is_grammar_side;
    public PredicateNode(ActionNode src){
        this.src = src;
        this.is_grammar_side = false;
    }

    public PredicateNode(Node argument, boolean grammar){
        this.argument = argument;
        this.is_grammar_side = true;
    }

    public static PredicateNode build(GrammarGraph graph, PredicateContext pred){
        if (pred.arg()!=null){
            PredicateNode node = new PredicateNode(ArgNode.build(graph, pred.arg()), true);
            graph.add_node(node);
            return node;
        }
        if (pred.actionBlock()!=null){
            PredicateNode node = new PredicateNode(ActionNode.build(graph, pred.actionBlock()));
            graph.add_node(node);
            return node;
        }
        Utils.panic("PredicateNode::build : Internal error");
        return null;
    }
    public String render(List<String> function_list, String padding, boolean print){
        if (is_grammar_side){
            return argument.render(function_list, "", false);
        }
        return src.render(function_list, "", false);
    }
    
}
