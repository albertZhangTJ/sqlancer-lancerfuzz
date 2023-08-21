package dsqlancer.AST;

import dsqlancer.Utils;

public class ActionNode extends Node{
    private String src;

    public ActionNode(String src){
        this.src = src;
    }

    public String get_src(){
        return this.src;
    }

    public void update_src(String source){
        if (this.src.length()<source.length()){
            Utils.oops("ActionNode::update_src : updated source is longer than original source, seems like unexpected behavior. Proceeding.");
        }
        this.src=source;
    }
    
    public String toString(){
        return super.toString()+"\n    type: ActionNode\n    src:\n"+this.src;
    }
}
