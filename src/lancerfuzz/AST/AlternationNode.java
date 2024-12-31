package lancerfuzz.AST;

import java.util.List;
import java.util.ArrayList;

import lancerfuzz.Utils;

public class AlternationNode extends Node {
    private List<PredicateNode> predicates;
    private List<Double> weights;

    public AlternationNode(){
        this.predicates = new ArrayList<>();
        this.weights = new ArrayList<>();
    }

    public void post_process(){
        super.post_process();
        //step 1: get all the explicitly declared weights and predicates
        List<Edge> children = super.get_outward_edges();
        for (int i=0; i<children.size(); i++){
            if (children.get(i).get_dest() instanceof AlternativeNode){
                AlternativeNode child = (AlternativeNode)(children.get(i).get_dest());
                this.predicates.add(child.get_predicate());
                this.weights.add(child.get_weight());
            }
            else {
                Utils.panic("AlternationNode::post_process : internal error, a direct child of an AlternationNode should always be an AlternativeNode");
            }
        }
        //step 2: check if the declared weights are valid (add up to no more than 100)
        double total_declared_weight = 0;
        int undeclared_count = 0;
        for (Double w : this.weights){
            if (w>=0){
                total_declared_weight += w;
            }
            else {
                undeclared_count++;
            }
        }
        if (total_declared_weight>100){
            Utils.panic("AlternationNode::post_process : total declared weight exceeds 100%, at "+total_declared_weight+"%");
        }
        // handles divide-by-zero situations, under which this value won't be needed
        double default_weight = (100-total_declared_weight)/(undeclared_count==0 ? 1 : undeclared_count);
        for (int i=0; i<this.weights.size(); i++){
            if (this.weights.get(i)>0){
                this.weights.set(i, this.weights.get(i)/100);
            }
            else {
                this.weights.set(i, default_weight);
            }
        }
    }


    public String toString(){
        return super.toString() + "\n    type: AlternationNode\n    weights: "+this.weights.toString();
    }

    //the print flag is ignored here, as there is no realistic reason why some section of a function should be suppressed
    public String render(List<String> function_list, String padding, boolean print){
        String handle = padding + "buf.add(node"+this.get_id()+"(ctx));\n";

        //this our own function to be added to the function list
        String indentation = "    ";
        String code = indentation + "public Buffer node"+this.get_id()+"(Context ctx){\n";
        code = code + indentation + indentation + "Buffer buf = new Buffer();\n";
        code = code + indentation + indentation + "Options opt = new Options();\n";
        
        for (int i=0; i<this.weights.size(); i++){
            if (this.predicates.get(i)==null){
                code = code + indentation + indentation + "opt.addOption("+i+", "+this.weights.get(i).doubleValue()+");\n";
            }
            else {
                code = code + indentation + indentation + "if("+this.predicates.get(i).render(function_list, "", false)+".getBoolean()){\n";
                code = code + indentation + indentation + indentation + "opt.addOption("+i+", "+this.weights.get(i).doubleValue()+");\n";
                code = code + indentation + indentation + "}\n";
            }
        }
        code = code + indentation + indentation + "int index = opt.randomly();\n";
        for (int i=0; i<this.weights.size(); i++){
            code = code + indentation + indentation + "if(index=="+i+"){\n";
            code = code + this.get_outward_edges().get(i).get_dest().render(function_list, indentation+indentation+indentation, true);
            code = code + indentation + indentation + "}\n";
        }
        code = code + indentation + indentation + "return buf;\n" + indentation + "}\n";
        function_list.add(code);
        return handle;
    }
}
