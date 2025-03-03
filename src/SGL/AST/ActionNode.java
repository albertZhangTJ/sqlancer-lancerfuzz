package SGL.AST;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import SGL.Utils;
import SGL.parser.SGLParser.ActionBlockContext;

public class ActionNode extends Node{
    private String src;

    public ActionNode(String src){
        this.src = src;
    }

    public String get_src(){
        return this.src;
    }

    public static ActionNode build(GrammarGraph graph, ActionBlockContext act){
        String src = "";
        for (TerminalNode t : act.ACTION_CONTENT()){
            src = src + t.getText();
        }
        ActionNode node = new ActionNode(src);
        graph.add_node(node);
        return node;
    }

    public void update_src(String source){
        if (this.src.length()<source.length()){
            Utils.oops("ActionNode::update_src : updated source is longer than original source, seems like unexpected behavior. Proceeding.");
        }
        this.src=source;
    }

    public String render(List<String> function_list, List<String> args, String padding){
        return padding + this.src + "\n";
    }
    
    public String toString(){
        return super.toString()+"\n    type: ActionNode\n    src:\n"+this.src;
    }
}
