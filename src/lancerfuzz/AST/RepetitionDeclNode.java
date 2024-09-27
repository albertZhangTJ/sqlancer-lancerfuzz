package lancerfuzz.AST;
import lancerfuzz.Utils;
public class RepetitionDeclNode extends Node {
    private int min;
    private int max;
    private double decay;
    private boolean uniform;

    public RepetitionDeclNode(String decl){
        this.process_decl(decl);
    }

    private void process_decl(String decl){

    }

    private boolean process_parameter(int position, String param, boolean seen_named){
        if (seen_named && !param.contains("=")){
            Utils.panic("RepetitionDeclNode::process_parameter : Unable to process \""+param+"\" positional argument cannot be accepted after named argument");
        }
        if (seen_named || param.contains("=")){
            process_named_parameter(param);
            return true;
        }
        process_positional_parameter(param, position);
        return false;
    }
    public int get_min(){
        return this.min;
    }

    public int get_max(){
        return this.max;
    }

    public double get_decay(){
        return this.decay;
    }

    public boolean is_uniform(){
        return this.uniform;
    }

}
