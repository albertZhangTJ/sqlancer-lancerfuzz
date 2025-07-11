package SGL.AST;

import java.util.List;

import SGL.Utils;
import SGL.parser.SGLParser.ArgContext;
import SGL.parser.SGLParser.EbnfContext;
import SGL.parser.SGLParser.EbnfSuffixContext;
import SGL.parser.SGLParser.LexerAltListContext;
import SGL.parser.SGLParser.LexerAtomContext;

//Defines how many repetition a rule should have
public class QuantifierNode extends Node{
    private int type; //0 for ?, 1 for +, 2 for *, 3 for **
    private Node parameter;
    
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
        else{
            Utils.panic("QuantifierNode::QuantifierNode : unrecognized suffix "+suffix);
        }
    }

    public static QuantifierNode build(GrammarGraph graph, LexerAtomContext atom, EbnfSuffixContext suffix){
        QuantifierNode node = new QuantifierNode(suffix.getText());
        graph.add_node(node);
        node.lines = atom.getStart().getLine();
        graph.add_edge(node, CharSetNode.build(graph, atom));
        return node;
    }

    public static QuantifierNode build(GrammarGraph graph, ArgContext arg, EbnfSuffixContext suffix){
        QuantifierNode node = new QuantifierNode(suffix.getText());
        graph.add_node(node);
        node.lines = arg.getStart().getLine();
        graph.add_edge(node, ArgNode.build(graph, arg));
        return node;
    }

    public static QuantifierNode build(GrammarGraph graph, LexerAltListContext altlist, EbnfSuffixContext suffix){
        QuantifierNode node = new QuantifierNode(suffix.getText());
        graph.add_node(node);
        node.lines = altlist.getStart().getLine();
        graph.add_edge(node, AlternationNode.build(graph, altlist));
        return node;
    }

    public static Node build(GrammarGraph graph, EbnfContext ebnf){
        if (ebnf.ebnfSuffix()!=null){
            QuantifierNode node = new QuantifierNode(ebnf.ebnfSuffix().getText());
            graph.add_node(node);
            node.lines = ebnf.getStart().getLine();
            graph.add_edge(node, AlternationNode.build(graph, ebnf.block().altList()));
            return node;
        }
        return AlternationNode.build(graph, ebnf.block().altList());
    }

    public int get_type(){
        return this.type;
    }
    public boolean set_param(Node arg){
        if (this.parameter!=null){
            return false;
        }
        this.parameter = arg;
        return true;
    }
    public boolean arged(){
        return this.parameter!=null;
    }
    
    public String render(List<String> function_list, String padding, boolean print){
        String handle = padding + this.debugging + this.lines + "\n" + padding + "buf.add(node"+this.get_id()+"(ctx));\n";
        
        //this our own function to be added to the function list
        String indentation = "    ";
        String code = indentation + this.debugging + this.lines + "\n" + indentation + "public static Buffer node"+this.get_id()+"(Context ctx) throws Exception{\n";
        code = code + indentation + indentation + "Buffer buf = new Buffer();\n";
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
            code = code + indentation + indentation + "String delimiter = v.getAttr(\"delimiter\", null).isPlaceHolder() ? \"\" : v.getAttr(\"delimiter\", null).getValue();\n";

        }
        code = code + indentation + indentation + "for (int i=0; i<rep; i++){\n";
        code = code + indentation + indentation + indentation + "if (i!=0){\n";
        code = code + indentation + indentation + indentation + indentation + "buf.add(Variable.factory(delimiter));\n";
        code = code + indentation + indentation + indentation + "}\n";
        for (Edge e : this.get_outward_edges()){
            Node child = e.get_dest();
            code = code + child.render(function_list, indentation+indentation+indentation, true);
        }
        code = code + indentation + indentation + "}\n";
        code = code + indentation + indentation + "return buf;\n" + indentation + "}\n";
        function_list.add(code);
        return handle;
    }
}
