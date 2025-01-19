package lancerfuzz.AST;

import java.util.List;

import lancerfuzz.parser.SGLParser.ParserRuleSpecContext;

import java.util.ArrayList;

public class UnparserRuleNode extends RuleNode{
    private boolean is_fragment;

    private List<ArgNode> arg_list;
    private CompIdentifierNode ret_var;

    public UnparserRuleNode(String name){
        super(name, RuleNodeType.UNPARSER);
        this.is_fragment = false;
        this.arg_list = new ArrayList<>();
        this.ret_var = null;
    }

    public static UnparserRuleNode build(GrammarGraph graph, ParserRuleSpecContext ruleSpec){
        String name = ruleSpec.RULE_REF().getText();
        UnparserRuleNode rule = new UnparserRuleNode(name);
        graph.add_node(rule);
        if (ruleSpec.ruleModifier()!=null){
            rule.set_fragment();
        }
        graph.add_edge(rule, AlternationNode.build(graph, ruleSpec.altList()));
        
    }

    public void set_fragment(){
        this.is_fragment = true;
    }
    public String render(List<String> function_list, String padding, boolean print){
        String handle = this.get_identifier()+"(ctx)";
        if (print){
            handle = padding +"buf.add(" + handle + ");\n";
        }

        String indent = "    ";
        String code = indent + "public static Buffer " + this.get_identifier() + "(Context ctx){\n";
        code = code + indent + indent + "Buffer buf = new Buffer();\n";
        if (!is_fragment){
            code = code + indent + indent + "ctx.push_frame();\n";
            code = code + indent + indent + "List<Variable> arg_decls = new ArrayList<>();\n";
            for (ArgNode a : this.arg_list){
                code = code + indent + indent + "List<Variable> arg_decls.add(" + a.render(rules, "", false) + ");\n";
            }
            code = code + indent + indent + "ctx.enter(arg_decls);\n";
        }
        for (Edge e : this.get_outward_edges()){
            Node child = e.get_dest();
            code = code + child.render(function_list, indent+indent, true) +"\n";
        }
        if (is_fragment){
            code = code + indent + indent + "ctx.ret(" + ret_var==null ? "null" : ret_var.render(function_list, "", false) + ");\n";
        }
        code = code + indent + indent + "return buf;\n";
        code = code + indent + "}\n";
        function_list.add(code);
        return handle;
    }
}
