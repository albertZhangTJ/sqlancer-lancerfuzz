package dsqlancer.AST;

import java.util.List;

@SuppressWarnings("unused")
public class AlternationNode extends Node {
    private int rule_id;
    private int index;
    private List<String> conditions;
    private int min_depth;

    public AlternationNode(int rule_id, int index, List<String> conditions){
        this.rule_id = rule_id;
        this.index = index;
        this.conditions = conditions;
        this.min_depth = -1;
    }

    // TODO: translate simple_alternatives 
    // 
}
