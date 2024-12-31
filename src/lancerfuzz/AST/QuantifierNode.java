package lancerfuzz.AST;

import lancerfuzz.Utils;

//Defines how many repetition a rule should have
public class QuantifierNode extends Node{
    private int type; //0 for ?, 1 for +, 2 for *, 3 for **
    private ArgNode parameter;
    
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
    public void set_param(ArgNode arg){
        this.parameter = arg;
    }
    
    public String render(List<String> function_list, String padding, boolean print){
        String handle = padding + "buf.add(node"+this.get_id()+"(ctx));\n";
        
        //this our own function to be added to the function list
        String indentation = "    ";
        String code = indentation + "public Buffer node"+this.get_id()+"(Context ctx){\n";
        code = code + indentation + indentation + "Buffer buf = new Buffer();\n";
        code = code + indentation + indentation + "Options opt = new Options();\n";
        if (this.get_type() == 0){
            code = code + indentation + indentation + "int rep = Rand.random(0, 1);\n";
            code = code + indentation + indentation + "String delimiter = \"\";\n";
        }
        if (this.get_type() == 1){
            code = code + indentation + indentation + "int rep = Rand.random(1, Fuzzer.DEFAULT_MAX_REP);\n";
            code = code + indentation + indentation + "String delimiter = \"\";\n";
        }
        if (this.get_type() == 2){
            code = code + indentation + indentation + "int rep = Rand.random(0, Fuzzer.DEFAULT_MAX_REP);\n";
            code = code + indentation + indentation + "String delimiter = \"\";\n";
        }
        if (this.get_type() == 3){
            code = code + indentation + indentation + "Variable v = " + this.parameter.render(rules, "", false) + ";\n";
            code = code + indentation + indentation + "int rep = v.getNumerical();\n";
            code = code + indentation + indentation + "String delimiter = v.getAttr(\"delimiter\", null)==null ? "" : v.getAttr(\"delimiter\", null).getValue();\n";

        }
        code = code + indentation + indentation + "for (int i=0; i<rep; i++){\n";
        code = code + indentation + indentation + indentation + "if (i!=0){\n";
        code = code + indentation + indentation + indentation + indentation + "buf.add(delimiter);\n";
        code = code + indentation + indentation + indentation + "}\n";
        for (Edge e : this.get_outward_edges()){
            Node child = e.get_dest();
            code = code + child.render(rules, indentation+indentation+indentation, true);
        }
        code = code + indentation + indentation + "}\n";
        code = code + indentation + indentation + "return buf;\n" + indentation + "}\n";
        function_list.add(code);
        return handle;
    }
}
