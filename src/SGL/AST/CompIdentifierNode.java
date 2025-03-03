package SGL.AST;

import java.util.*;
import SGL.Utils;
import SGL.parser.SGLParser.ArgContext;
import SGL.parser.SGLParser.CompIdentifierContext;

public class CompIdentifierNode extends Node {
    //a[b].c.d
    //anything in the tail (c.d) will be stored in the child nodes which is linked using outward edges
    private String identifier; // a
    private List<Node> param; // the ones passed in using [...], b
    private CompIdentifierNode attr;

    // if head, then this.id is either a rule/token ref or a variable name
    // otherwise it will either be an attribute (if param is null) or a function (if param is not)
    private boolean head; 

    public CompIdentifierNode(String ID, List<Node> param, boolean is_head, CompIdentifierNode attr){
        this.identifier = ID;
        this.param = Utils.copy_list(param);
        this.head = is_head;
        this.attr = attr;
    }

    public static CompIdentifierNode build(GrammarGraph graph, CompIdentifierContext id, boolean is_head){
        String identifier = id.identifier().getText();
        List<Node> params = new ArrayList<>();
        if (id.argActionBlock()!=null){
            for (ArgContext arg: id.argActionBlock().arg()){
                params.add(ArgNode.build(graph, arg));
            }   
        }
        CompIdentifierNode attr = null;
        if (id.compIdentifier()!=null){
            attr = CompIdentifierNode.build(graph, id.compIdentifier(), false);
        }
        CompIdentifierNode node = new CompIdentifierNode(identifier, params, is_head, attr);
        graph.add_node(node);
        return node;
    }

    public List<Node> get_param(){
        return Utils.copy_list(this.param);
    }

    public boolean is_head(){
        return this.head;
    }

    public String render(List<String> function_list, String padding, boolean print){
        if (print && !this.is_head()){
            Utils.panic("CompIdentifierNode::render : some really weird stuff happened, a non-head identifier node should never be rendered with print set to true");
        }
        String res = "";
        if (this.is_head() && this.param!=null && this.param.size()>0){
            res = "ctx.getSymbol(buf, \""+this.identifier +"\", packList(";
            for (Node arg : this.param){
                res = res + arg.render(function_list, "", false) + ",";
            }
            res = res.substring(0, res.length()-1) + "))";
        }
        else if (this.is_head()){
            res = "ctx.getSymbol(buf, \""+this.identifier +"\", new ArrayList<>())";
        }
        else if (this.param!=null && this.param.size()>0){
            res = ".getAttr(\""+this.identifier +"\", packList(";
            for (Node arg : this.param){
                res = res + arg.render(function_list, "", false) + ",";
            }
            res = res.substring(0, res.length()-1) + "))";
        }
        else {
            res = ".getAttr(\""+this.identifier +"\", new ArrayList<>())";
        }
        if (this.attr!=null){
            res = res + this.attr.render(function_list, "", false);
        }
        if (print){
            res = padding + "buf.add(" + res + ");\n";
        }
        return res;
    }
}
