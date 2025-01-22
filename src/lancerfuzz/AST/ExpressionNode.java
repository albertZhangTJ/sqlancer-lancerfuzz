package lancerfuzz.AST;

import java.util.List;

import lancerfuzz.parser.SGLParser.ExpressionContext;
import lancerfuzz.parser.SGLParser.MexprContext;
import lancerfuzz.parser.SGLParser.LexprContext;

public class ExpressionNode extends Node {
    private ExpressionNode lhs;
    private String operator;
    private ExpressionNode rhs;
    private boolean suppressed;

    public ExpressionNode(ExpressionNode lhs, String operator, ExpressionNode rhs, boolean suppressed){
        this.lhs = lhs;
        this.operator = operator;
        this.rhs = rhs;
        this.suppressed = suppressed;
    }

    public ExpressionNode(){
        this.lhs = null;
        this.rhs = null;
        this.operator = null;
        this.suppressed = false;
    }

    // first class operators are not expected to be chained
    // e.g. a=b=1 is not valid, same for a+=b=1
    public static ExpressionNode build(GrammarGraph graph, ExpressionContext expr){
        //if this happens, we have an assignment at the current level
        if (expr.mexpr().size()>1){
            ExpressionNode node = new ExpressionNode(
                ExpressionNode.build(graph, expr.mexpr().get(0)), 
                expr.expr_op().ASSIGN()!=null ? "=" : "+=", 
                ExpressionNode.build(graph, expr.mexpr().get(1)), 
                expr.expr_op().DOLLAR()!=null
            );
            graph.add_node(node);
            return node;
        }
        return ExpressionNode.build(graph, expr.mexpr().get(0));
    }
    //TODO: mexpr and lexpr
    public static ExpressionNode build(GrammarGraph graph, MexprContext mexpr){
        if (mexpr.lexpr().size()==1){
            return ExpressionNode.build(graph, mexpr.lexpr().get(0));
        }
        ExpressionNode root = new ExpressionNode();
        ExpressionNode last = root;
        for (int i=0; i<mexpr.lexpr().size()-1; i++){
            last.set_lhs(ExpressionNode.build(graph, mexpr.lexpr().get(i)));
            graph.add_node(last);
            last.set_operator(mexpr.mexpr_op().get(i).getText().strip());
            if (i==mexpr.lexpr().size()-2){
                last.set_rhs(ExpressionNode.build(graph, mexpr.lexpr().get(i+1)));    
            }
            else {
                last.set_rhs(new ExpressionNode());
                last=last.get_rhs();
            }
        }
        return root;
    }
    public static ExpressionNode build(GrammarGraph graph, LexprContext lexpr){
        if (lexpr.variable().size()==1){
            return VariableNode.build(graph, lexpr.variable().get(0));
        }
        ExpressionNode root = new ExpressionNode();
        ExpressionNode last = root;
        for (int i=0; i<lexpr.variable().size()-1; i++){
            last.set_lhs(VariableNode.build(graph, lexpr.variable().get(i)));
            graph.add_node(last);
            last.set_operator(lexpr.lexpr_op().get(i).getText().strip());
            if (i==lexpr.variable().size()-2){
                last.set_rhs(VariableNode.build(graph, lexpr.variable().get(i+1)));    
            }
            else {
                last.set_rhs(new ExpressionNode());
                last=last.get_rhs();
            }
        }
        return root;
    }

    public void set_lhs(ExpressionNode lhs){
        this.lhs = lhs;
    }

    public void set_rhs(ExpressionNode rhs){
        this.rhs = rhs;
    }

    public void set_operator(String operator){
        this.operator = operator;
    }
    
    public void set_suppressed(boolean suppressed){
        this.suppressed = suppressed;
    }

    public ExpressionNode get_lhs(){
        return this.lhs;
    }

    public String get_operator(){
        return this.operator;
    }

    public ExpressionNode get_rhs(){
        return this.rhs;
    }

    @Override
    public String render(List<String> function_list, String padding, boolean print){
        String handle = "ctx.eval("+lhs.render(function_list, "", false)+", \""+operator+"\", "+rhs.render(function_list, "", false)+")";
        if (print && !suppressed){
            handle = padding +"buf.add("+handle+");\n";
        }
        else if (print){
            handle = padding + handle +";\n";
        }
        return handle;
    }

}
