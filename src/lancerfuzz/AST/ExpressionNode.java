package lancerfuzz.AST;

import lancerfuzz.Utils;

public class ExpressionNode extends Node {
    private VariableNode lhs;
    private String operator;
    private VariableNode rhs;

    public ExpressionNode(VariableNode lhs, String operator, VariableNode rhs){
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
    }

    public VariableNode get_lhs(){
        return this.lhs;
    }

    public String get_operator(){
        return this.operator;
    }

    public VariableNode get_rhs(){
        return this.rhs;
    }

    public String render(List<String> function_list, String padding, boolean print){
        String handle = "context.eval("+lhs.render(function_list, "", false)+", \""+operator+"\", "+rhs.render(function_list, "", false)+")";
        if (print){
            handle = padding +"buf.add("+handle+");\n";
        }
        return handle;
    }

}
