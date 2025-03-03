package SGL.AST;

//this is the IR for @X notations
// during IR processing, all nodes with this expansion priority will be moved
// to be a child of this node
// the priority of the a default Node is 65536 before processing
// if any node has a ScheduleNode as child
// all its children must be ScheduleNodes before rendering

import java.util.List;

import SGL.parser.SGLParser.PrecedenceContext;

public class ScheduleNode extends Node{
    private int priority;
    private int order;
    public ScheduleNode(int priority){
        this.priority = priority;
    }
    public int get_priority(){
        return this.priority;
    }

    public void set_order(int order){
        this.order = order;
    }

    public int get_order(){
        return this.order;
    }

    public static ScheduleNode build(GrammarGraph graph, PrecedenceContext pre){
        int precedence = Integer.valueOf(pre.INT_LITERAL().getText());
        ScheduleNode node = new ScheduleNode(precedence);
        graph.add_node(node);
        return node;
    }

    @Override
    public String render(List<String> function_list, String padding, boolean print){
        //this is the handle for the caller to embed in its own function
        String handle = padding + "buf.set("+ this.get_order()+", node"+this.get_id()+"(ctx));\n";

        //this our own function to be added to the function list
        String indentation = "    ";
        String code = indentation + "public static Buffer node"+this.get_id()+"(Context ctx) throws Exception{\n";
        code = code + indentation + indentation + "Buffer buf = new Buffer();\n";
        for (Edge e: this.get_outward_edges()){
            Node child = e.get_dest();
            code = code + child.render(function_list, indentation + indentation, true);
        }
        code = code + indentation + indentation + "return buf;\n";
        code = code + indentation + "}\n";
        function_list.add(code);
        return handle;
    }
}
