package lancerfuzz.AST;

import java.util.List;

import java.util.ArrayList;
import lancerfuzz.Utils;

public class WeightDeclNode extends Node {
    private List<Double> weights;
    public WeightDeclNode(String decl){
        this.weights = parse_decl(decl);
    }

    private static List<Double> parse_decl(String decl){
        String[] numbers = decl.split(",");
        List<Double> weights = new ArrayList<>();
        try{
            for (String number : numbers){
                weights.add(Double.valueOf(number));
            }
        }
        catch (Exception e){
            Utils.panic("WeightDeclNode::parse_decl : Error during parsing weight declaration, cannot parse "+decl);
        }
        return weights;
    }

    public List<Double> get_weights(){
        return Utils.copy_list(this.weights);
    }
}
