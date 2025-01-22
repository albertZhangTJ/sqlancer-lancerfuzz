package lancerfuzz.AST;

import java.util.List;

import lancerfuzz.Utils;
import lancerfuzz.parser.SGLParser.RuleSpecContext;

public class RuleNode extends Node{
    public static enum RuleNodeType {UNLEXER, UNPARSER};
    private RuleNodeType type;
    
    public RuleNode(String name, RuleNodeType type){
        super(name);
        this.type = type;
    } 

    public RuleNodeType get_type(){
        return this.type;
    }

    public static void build(GrammarGraph graph, RuleSpecContext rulespec){
        if (rulespec.parserRuleSpec()!=null){
            UnparserRuleNode.build(graph, rulespec.parserRuleSpec());
        }
        else {
            UnlexerRuleNode.build(graph, rulespec.lexerRuleSpec());
        }
    }

    //This is just a placeholder for render function
    //Should not be executed in real life
    //Did not set to abstract to save some work for temporary IR nodes
    public String render(List<String> function_list, String paddng, boolean print){
        Utils.panic("RuleNode::render : Internal error, you should never have got here");
        return null;
    }
}
