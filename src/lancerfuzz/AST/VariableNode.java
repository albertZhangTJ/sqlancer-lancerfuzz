package lancerfuzz.AST;

import java.util.List;

import lancerfuzz.Utils;
import lancerfuzz.parser.SGLParser.VariableContext;

public class VariableNode extends ExpressionNode{
    private CompIdentifierNode identifierNode;
    private String strLiteral;
    private int intLiteral;
    private boolean boolLiteral;
    private int type;

    public static VariableNode build(GrammarGraph graph, VariableContext var){
        //TODO
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
                res = "buf.add(" + res + ")";
            }
            return res;
        }
        if (this.type == 3){
            String res = "Variable.factory("+this.intLiteral+")";
            if (print){
                res = "buf.add(" + res + ")";
            }
            return res;
        }
        if (this.type == 4){
            String res = "Variable.factory("+this.boolLiteral+ ")";
            if (print){
                res = "buf.add(" + res + ")";
            }
            return res;
        }
        Utils.panic("VariableNode::render : unrecognized type index "+this.type);
        return "";
    }

}
