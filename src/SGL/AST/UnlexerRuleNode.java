package SGL.AST;

import java.util.List;

import SGL.Utils;
import SGL.parser.SGLParser.LexerRuleSpecContext;

public class UnlexerRuleNode extends RuleNode{
    public UnlexerRuleNode(String name){
        super(name, RuleNodeType.UNLEXER);
    }

    public static UnlexerRuleNode build(GrammarGraph graph, LexerRuleSpecContext ruleSpec){
        // we do not need to process the fragment keyword
        // as lexer rules don't have their own stack frame in the first place
        String name = ruleSpec.TOKEN_REF().getText();
        if (!graph.add_rule_name(name)){
            Utils.oops("UnlexerRuleNode::build : rule "+name+" has been defined, keeping the first definition");
            return null;
        }
        UnlexerRuleNode rule = new UnlexerRuleNode(name);
        graph.add_node(rule);
        graph.add_edge(rule, AlternationNode.build(graph, ruleSpec.lexerAltList()));
        return rule;
    }

    //basically the same as UnparserRuleNode
    //There do not have arguments or returns, they don't need stack frame
    //Essentially context-free
    //supports CharSetNode (which are not allowed in UnparserRuleNode)
    public String render(List<String> function_list, String padding, boolean print){
        String handle = this.get_identifier()+"(ctx)";
        if (print){
            handle = padding +"buf.add(" + handle + ");\n";
        }

        String indent = "    ";
        String code = indent + "public static Buffer " + this.get_identifier() + "(Context ctx) throws Exception{\n";
        code = code + indent + indent + "Buffer buf = new Buffer();\n";
        for (Edge e : this.get_outward_edges()){
            Node child = e.get_dest();
            code = code + child.render(function_list, indent+indent, true) +"\n";
        }
        code = code + indent + indent + "return buf;\n";
        code = code + indent + "}\n";
        function_list.add(code);
        return handle;
    }
}
