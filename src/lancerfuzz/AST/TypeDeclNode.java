package lancerfuzz.AST;
public class TypeDeclNode extends Node {
    private String type;
    public TypeDeclNode(String typ){
        String type = typ.strip();
        if (type.length()>1 && type.charAt(0)=='"' && type.charAt(type.length()-1)=='"'){
            this.type = type.substring(1,type.length()-1);
        }
        else {
            this.type = type;
        }
    }
    public String get_type(){
        return this.type;
    }
}
