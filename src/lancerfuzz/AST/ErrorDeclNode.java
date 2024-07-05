package lancerfuzz.AST;
import java.util.List;
import java.util.ArrayList;

import lancerfuzz.Utils;
public class ErrorDeclNode extends Node {
    private List<String> errors;
    public ErrorDeclNode(String decl){
        this.errors = parse_decl(decl);
    }
    private static List<String> parse_decl(String decl){
        String[] errors = decl.split(",");
        List<String> res = new ArrayList<>();
        try{
            for (String error : errors){
                error = error.strip();
                if (error.length()>1 && ((error.charAt(0)=='\"' && error.charAt(error.length()-1)=='\"') || (error.charAt(0)=='\'' && error.charAt(error.length()-1)=='\''))){
                    error = error.substring(1,error.length()-1);
                }
                res.add(error);
            }
        }
        catch (Exception e){
            Utils.panic("ErrorDeclNode::parse_decl : Error during parsing expected errors declaration, cannot parse "+decl);
        }
        return res;
    }
    public List<String> get_errors(){
        return Utils.copy_list(this.errors);
    }
}
