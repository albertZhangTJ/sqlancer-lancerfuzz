package lancerfuzz.AST;

import java.util.List;

import lancerfuzz.Utils;

public class VariableNode extends Node{
    private CompIdentifierNode identifierNode;
    private String strLiteral;
    private int intLiteral;
    private boolean boolLiteral;
    private int type;


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
    public String render(List<String> function_list, String padding){
        if (this.type == 1){
            return identifierNode.render();
        }
        if (this.type == 2){
            return "Variable.factory(\""+this.strLiteral+"\")";
        }
        if (this.type == 3){
            return "Variable.factory("+this.intLiteral+")";
        }
        if (this.type == 4){
            return "Variable.factory("+this.boolLiteral+")";
        }
        Utils.panic("VariableNode::render : unrecognized type index "+this.type);
        return "";
    }

}
