package dsqlancer.AST;

public class UnlexerRuleNode extends RuleNode{
    public UnlexerRuleNode(String name){
        super(name, null, RuleNodeType.UNLEXER);
    }
}
