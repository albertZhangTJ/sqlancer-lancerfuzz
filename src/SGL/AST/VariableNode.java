package SGL.AST;

import java.util.List;

import SGL.Utils;
import SGL.parser.SGLParser.VariableContext;

public class VariableNode extends ExpressionNode{
    private CompIdentifierNode identifierNode;
    private String strLiteral;
    private int intLiteral;
    private boolean boolLiteral;
    private int type;

    public static VariableNode build(GrammarGraph graph, VariableContext var){
        if (var.compIdentifier()!=null){
            VariableNode node = new VariableNode(CompIdentifierNode.build(graph, var.compIdentifier(), true));
            graph.add_node(node);
            node.lines = var.getStart().getLine();
            return node;
        }
        if (var.STRING_LITERAL()!=null){
            VariableNode node = new VariableNode(var.STRING_LITERAL().getText().substring(1, var.STRING_LITERAL().getText().length()-1));
            graph.add_node(node);
            node.lines = var.getStart().getLine();
            return node;
        }
        if (var.INT_LITERAL()!=null){
            VariableNode node = new VariableNode(Integer.valueOf(var.INT_LITERAL().getText()));
            graph.add_node(node);
            node.lines = var.getStart().getLine();
            return node;
        }
        if (var.BOOL_LITERAL()!=null){
            VariableNode node = new VariableNode(var.BOOL_LITERAL().getText().equals("true"));
            graph.add_node(node);
            node.lines = var.getStart().getLine();
            return node;
        }
        Utils.panic("VariableNode::build : Internal error");
        return null;
    }

    public VariableNode(CompIdentifierNode identifierNode){
        this.identifierNode = identifierNode;
        this.type = 1;
    }

    public VariableNode(String s){
        this.strLiteral = s;
        this.type = 2;
    }

    public VariableNode(int intLiteral){
        this.intLiteral = intLiteral;
        this.type = 3;
    }

    public VariableNode(boolean b){
        this.boolLiteral = b;
        this.type = 4;
    }

    @Override
    public String render(List<String> function_list, String padding, boolean print){
        if (this.type == 1){
            return identifierNode.render(function_list, padding, print);
        }
        if (this.type == 2){
            String res = "Variable.factory(\""+this.strLiteral+"\")";
            if (print){
                res = padding + this.debugging + this.lines + "\n" + padding +"buf.add(" + res + ");\n";
            }
            return res;
        }
        if (this.type == 3){
            String res = "Variable.factory("+this.intLiteral+")";
            if (print){
                res = padding + this.debugging + this.lines + "\n" + padding +"buf.add(" + res + ");\n";
            }
            return res;
        }
        if (this.type == 4){
            String res = "Variable.factory("+this.boolLiteral+ ")";
            if (print){
                res = padding + this.debugging + this.lines + "\n" + padding +"buf.add(" + res + ");\n";
            }
            return res;
        }
        Utils.panic("VariableNode::render : unrecognized type index "+this.type);
        return "";
    }

}
