package lancerfuzz.AST;
import lancerfuzz.Utils;
public class RepetitionDeclNode extends Node {
    private int min;
    private int max;
    private int decay; //since currently LancerFuzz does not support 
    private boolean uniform;

    public RepetitionDeclNode(int min, int max){
        this.min = min;
        this.max = max;
        this.decay = 0;
        this.uniform = true;
    }

    public RepetitionDeclNode(int min, int max, int decay){
        this.min = min;
        this.max = max;
        this.decay = decay;
        this.uniform = false;
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
