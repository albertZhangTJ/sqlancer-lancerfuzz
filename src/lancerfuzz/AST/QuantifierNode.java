package lancerfuzz.AST;

import lancerfuzz.Utils;

//Defines how many repetition a rule should have
public class QuantifierNode extends Node{
    private int type; //0 for ?, 1 for +, 2 for *, 3 for **
    
    public QuantifierNode(String suffix){
        if (suffix.equals("?")){
            this.type = 0;
        }
        else if (suffix.equals("+")){
            this.type = 1;
        }
        else if (suffix.equals("*")){
            this.type = 2;
        }
        else if (suffix.equals("**")){
            this.type = 3;
        }
        Utils.panic("QuantifierNode::QuantifierNode : unrecognized suffix "+suffix);
    }

    public int get_type(){
        return this.type;
    }
    
}
