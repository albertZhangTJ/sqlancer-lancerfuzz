package lancerfuzz.AST;
import java.util.List;
import java.util.ArrayList;

import lancerfuzz.Utils;
@Deprecated
public class ErrorDeclNode extends Node {
    private List<String> errors;
    public ErrorDeclNode(List<String> errors){
        this.errors = errors;
    }
    public List<String> get_errors(){
        return Utils.copy_list(this.errors);
    }

    @Override
    public String render(List<String> function_list, String padding, boolean print){
        String res = "";
        for (String err : this.errors){
            res = res + padding + "ctx.addError(Variable.factory(\"" + err +"\"));\n";
        } 
        return res;
    }
}
