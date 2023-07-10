package dsqlancer.AST;

public class UnparserRuleNode extends RuleNode{
    public UnparserRuleNode(String name){
        super(name, null, RuleNodeType.UNPARSER);
    }
}
