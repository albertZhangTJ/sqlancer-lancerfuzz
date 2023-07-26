package dsqlancer.AST;

public class UnparserRuleNode extends RuleNode{
    public UnparserRuleNode(String name){
        super(name, null, RuleNodeType.UNPARSER);
    }

    public UnparserRuleNode(String name, String label){
        super(name, label, RuleNodeType.UNPARSER);
    }
}
