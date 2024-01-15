package lancerfuzz.AST;

import lancerfuzz.Utils;

public class ImagRuleNode extends Node{
    private int real_id = -1;
    public ImagRuleNode(String identifier){
        super(identifier);
        if (identifier==null){
            Utils.panic("ImagRuleNode::ImagRuleNode : identifier for ImagRuleNode cannot be null");
        }
    }

    public void set_real_id(int id){
        this.real_id = id;
    }

    public int get_real_id(){
        if (real_id==-1){
            Utils.panic("ImagRuleNode::get_real_id : real_id not set for ImagRuleNode: "+super.get_identifier());
        }
        return real_id;
    }
}
