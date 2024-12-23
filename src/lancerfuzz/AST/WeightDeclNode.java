package lancerfuzz.AST;

import java.util.List;

import java.util.ArrayList;
import lancerfuzz.Utils;

public class WeightDeclNode extends Node {
    private int weight;

    public int get_weight(){
        return this.weight;
    }

    // Nodes of this type will be post-processed into alternation nodes
    // No rendering required
}
