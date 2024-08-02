package lancerfuzz.AST;

import java.util.*;
import lancerfuzz.Utils;

public class CompIdentifierNode extends Node {
    //a[b].c.d
    //anything in the tail (c.d) will be stored in the child nodes which is linked using outward edges
    private String id; // a
    private List<String> param; // the ones passed in using [...], b

    // if head, then this.id is either a rule/token ref or a variable name
    // otherwise it will either be an attribute (if param is null) or a function (if param is not)
    private boolean head; 

    public CompIdentifierNode(String ID, List<String> param, boolean is_head){
        this.id = ID;
        this.param = Utils.copy_list(param);
        this.head = is_head;
    }

    public String get_id(){
        return this.id;
    }

    public List<String> get_param(){
        return Utils.copy_list(this.param);
    }

    public boolean is_head(){
        return this.head;
    }

}
