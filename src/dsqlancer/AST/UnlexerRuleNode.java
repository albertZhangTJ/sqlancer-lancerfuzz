package dsqlancer.AST;

import java.util.List;

import dsqlancer.Utils;

public class UnlexerRuleNode extends RuleNode{
    private List<Integer> start_ranges;
    public UnlexerRuleNode(String name){
        super(name, null, RuleNodeType.UNLEXER);
        this.start_ranges = null;
    }

    public List<Integer> get_start_ranges(){
        return Utils.copy_list(this.start_ranges);
    }

    public void set_start_ranges(List<Integer> start_ranges){
        if (this.start_ranges!=null){
            Utils.oops("UnlexerRuleNode::set_start_ranges : start_ranges already been set, overwriting");
        }
        if (start_ranges==null || start_ranges.size()==0){
            Utils.oops("UnlexerRuleNode::set_start_ranges : New start_ranges is empty, ignored");
        }
        this.start_ranges = start_ranges;
    }

    public void append_start_ranges(List<Integer> start_range){
        for (Integer i : start_range){
            this.start_ranges.add(i);
        }
    }
}
