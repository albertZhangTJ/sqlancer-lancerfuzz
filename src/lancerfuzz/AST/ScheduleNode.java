//this is the IR for @X notations
// during IR processing, all nodes with this expansion priority will be moved
// to be a child of this node
// the priority of the a default Node is 65536 before processing
// if any node has a ScheduleNode as child
// all its children must be ScheduleNodes before rendering
public class ScheduleNode extends Node{
    private int priority;
    public ScheduleNode(int priority){
        this.priority = priority;
    }
    public int get_priority(){
        return this.priority;
    }

    @Override
    public String render(List<String> function_list, String padding){
        //this is the handle for the caller to embed in its own function
        //this will be returned as the string
        String handle = padding + "buf.set("+ this.get_priority()+", node"+this.get_id()+"(ctx));\n";

        //this our own function to be added to the function list
        String indentation = "    ";
        String code = indentation + "public Buffer node"+this.get_id()+"(Context ctx){\n}";
        code = code + indentation + indentation + "Buffer buf = new Buffer();\n";
        for (Edge e: this.get_outward_edges()){
            Node child = e.get_dest();
            code = code + child.render(function_list, indentation + indentation);
        }
        code = code + indentation + indentation + "return buf;\n";
        code = code + indentation + "};";
        function_list.add(code);
        return handle;
    }
}
