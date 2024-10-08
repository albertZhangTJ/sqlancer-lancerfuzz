package lancerfuzz.AST;
import java.util.List;
import java.util.ArrayList;

import lancerfuzz.Utils;
public class ErrorDeclNode extends Node {
    private List<String> errors;
    public ErrorDeclNode(List<String> errors){
        this.errors = errors;
    }
    public List<String> get_errors(){
        return Utils.copy_list(this.errors);
    }
}
