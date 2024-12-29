package lancerfuzz.AST;

import lancerfuzz.Utils;

public class AlternativeNode extends Node{
    //these will be handled at the building stage
    private int weight;
    private PredicateNode predicate;

    public AlternativeNode(String rule_id, int alternative_index, int index){
        this.weight = -1;
        this.predicate = null;
    }

    public double get_weight(){
        return (double)this.weight;
    }

    public PredicateNode get_predicate(){
        return this.predicate;
    }

    public String render(List<String> function_list, String padding, boolean print){
        String handle = padding + "buf.add(node"+this.get_id()+"(ctx);\n";

        String indentation = "    ";
        String code = indentation + "public static Buffer node"+this.get_id()+"(Context ctx){\n";
        code = code + indentation + indentation + "Buffer buf = new Buffer();\n";
        for (Edge e : this.get_outward_edges()){
            Node child = e.get_dest();
            code = code + child.render(function_list, indentation+indentation, true) +"\n";
        }
        code = code + indentation + indentation + "return buf;\n";
        code = code + indentation + "}\n";
        function_list.add(code);
        return handle;
    }
}
