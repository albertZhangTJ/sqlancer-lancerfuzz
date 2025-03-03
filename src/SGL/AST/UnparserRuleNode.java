package SGL.AST;

import java.util.List;

import SGL.Utils;
import SGL.parser.SGLParser.ArgContext;
import SGL.parser.SGLParser.ParserRuleSpecContext;

import java.util.ArrayList;

public class UnparserRuleNode extends RuleNode{
    private boolean is_fragment;

    private List<Node> arg_list;
    private Node ret_var;

    public UnparserRuleNode(String name){
        super(name, RuleNodeType.UNPARSER);
        this.is_fragment = false;
        this.arg_list = new ArrayList<>();
        this.ret_var = null;
    }

    public static UnparserRuleNode build(GrammarGraph graph, ParserRuleSpecContext ruleSpec){
        String name = ruleSpec.RULE_REF().getText();
        if (!graph.add_rule_name(name)){
            Utils.oops("UnparserRuleNode::build : rule "+name+" has been defined, keeping the first definition");
            return null;
        }
        UnparserRuleNode rule = new UnparserRuleNode(name);
        graph.add_node(rule);
        if (ruleSpec.ruleModifier()!=null){
            rule.set_fragment();
        }
        else {
            graph.add_callable_rule_name(name);
        }
        graph.add_edge(rule, AlternationNode.build(graph, ruleSpec.altList()));
        List<Node> params = new ArrayList<>();
        if (ruleSpec.argActionBlock()!=null){
            for (ArgContext arg: ruleSpec.argActionBlock().arg()){
                params.add(ArgNode.build(graph, arg));
            }   
        }
        rule.set_params(params);
        if (ruleSpec.ruleReturns()!=null){
            if (ruleSpec.ruleReturns().argActionBlock().arg().size()!=1){
                Utils.panic("UnparserRuleNode::build : parser rules can only return 1 variable, "+ruleSpec.ruleReturns().argActionBlock().arg().size()+" found for "+name);
            }
            rule.set_ret(ArgNode.build(graph, ruleSpec.ruleReturns().argActionBlock().arg().get(0)));
        }
        return rule;
    }

    public void set_fragment(){
        this.is_fragment = true;
    }
    public void set_params(List<Node> params){
        this.arg_list = Utils.copy_list(params);
    }
    public void set_ret(Node ret){
        this.ret_var = ret;
    }
    public String render(List<String> function_list, String padding, boolean print){
        String handle = this.get_identifier()+"(ctx)";
        if (print){
            handle = padding +"buf.add(" + handle + ");\n";
        }

        String indent = "    ";
        String code = indent + "public static Buffer " + this.get_identifier() + "(Context ctx) throws Exception{\n";
        code = code + indent + indent + "Buffer buf = new Buffer();\n";
        if (!is_fragment){
            code = code + indent + indent + "ctx.push_frame();\n";
            code = code + indent + indent + "List<Variable> arg_decls = new ArrayList<>();\n";
            for (Node a : this.arg_list){
                code = code + indent + indent + "arg_decls.add(" + a.render(rules, "", false) + ");\n";
            }
            code = code + indent + indent + "ctx.enter(arg_decls);\n";
        }
        for (Edge e : this.get_outward_edges()){
            Node child = e.get_dest();
            code = code + child.render(function_list, indent+indent, true) +"\n";
        }
        if (!is_fragment){
            String ret = "null";
            if (ret_var!=null){
                ret = ret_var.render(function_list, "", false);
            }
            code = code + indent + indent + "ctx.ret(" + ret + ");\n";
        }
        code = code + indent + indent + "return buf;\n";
        code = code + indent + "}\n";
        function_list.add(code);
        return handle;
    }
}
