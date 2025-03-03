package SGL.AST;

import java.util.List;

import SGL.parser.SGLParser.AlternativeContext;
import SGL.parser.SGLParser.ElementContext;
import SGL.parser.SGLParser.LexerAltContext;
import SGL.parser.SGLParser.LexerElementContext;

public class AlternativeNode extends Node{
    //these will be handled at the building stage
    private int weight;
    private PredicateNode predicate;

    public AlternativeNode(){
        this.weight = -1;
        this.predicate = null;
    }

    public static AlternativeNode build(GrammarGraph graph, AlternativeContext alter){
        AlternativeNode node = new AlternativeNode();
        graph.add_node(node);
        for (ElementContext element: alter.element()){
            if (element.actionBlock()!=null){
                graph.add_edge(node, ActionNode.build(graph, element.actionBlock()));
            }
            if (element.predicate()!=null){
                node.set_predicate(PredicateNode.build(graph, element.predicate()));
            }
            if (element.weightage()!=null){
                node.set_weight(Integer.valueOf(element.weightage().INT_LITERAL().getText()));
            }
            if (element.precedence()!=null){
                graph.add_edge(node, ScheduleNode.build(graph, element.precedence()));
            }
            if (element.arg()!=null){
                if (element.ebnfSuffix()!=null){
                    graph.add_edge(node, QuantifierNode.build(graph, element.arg(), element.ebnfSuffix()));
                }
                else {
                    graph.add_edge(node, ArgNode.build(graph, element.arg()));
                }
            }
            if (element.ebnf()!=null){
                graph.add_edge(node, QuantifierNode.build(graph, element.ebnf()));
            }
        }
        return node;
    }

    public static AlternativeNode build(GrammarGraph graph, LexerAltContext alter){
        AlternativeNode node = new AlternativeNode();
        graph.add_node(node);
        for (LexerElementContext element : alter.lexerElement()){
            if (element.actionBlock()!=null){
                graph.add_edge(node, ActionNode.build(graph, element.actionBlock()));
            }
            if (element.predicate()!=null){
                node.set_predicate(PredicateNode.build(graph, element.predicate()));
            }
            if (element.expression()!=null){
                graph.add_edge(node, ExpressionNode.build(graph, element.expression()));
            }
            if (element.lexerAtom()!=null){
                if (element.ebnfSuffix()!=null){
                    graph.add_edge(node, QuantifierNode.build(graph, element.lexerAtom(), element.ebnfSuffix()));
                }
                else {
                    graph.add_edge(node, CharSetNode.build(graph, element.lexerAtom()));
                }
            }
            if (element.lexerBlock()!=null){
                if (element.ebnfSuffix()!=null){
                    graph.add_edge(node, QuantifierNode.build(graph, element.lexerBlock().lexerAltList(), element.ebnfSuffix()));
                }
                else {
                    graph.add_edge(node, AlternationNode.build(graph, element.lexerBlock().lexerAltList()));
                }
            }
            
        }
        return node;
    }

    public double get_weight(){
        return (double)this.weight;
    }

    public PredicateNode get_predicate(){
        return this.predicate;
    }

    public void set_predicate(PredicateNode pred){
        this.predicate = pred;
    }

    public void set_weight(int weight){
        this.weight = weight;
    }

    public String render(List<String> function_list, String padding, boolean print){
        if (this.get_outward_edges().size()==1){
            return this.get_outward_edges().get(0).get_dest().render(function_list, padding, true);
        }
        String handle = padding + "buf.add(node"+this.get_id()+"(ctx));\n";

        String indentation = "    ";
        String code = indentation + "public static Buffer node"+this.get_id()+"(Context ctx) throws Exception{\n";
        code = code + indentation + indentation + "Buffer buf = new Buffer();\n";
        for (Edge e : this.get_outward_edges()){
            Node child = e.get_dest();
            code = code + child.render(function_list, indentation+indentation, true) +"\n";
        }
        code = code + indentation + indentation + "return buf;\n";
        code = code + indentation + "}\n";
        function_list.add(code);
        return handle;
    }
}
