package lancerfuzz.AST;

import java.util.List;

import java.util.ArrayList;
import lancerfuzz.Utils;

// I think we can skip having this separately processed in IR
// Instead, the builder for AlternativeNodes can direcly handle this
@Deprecated
public class WeightDeclNode extends Node {
    private int weight;

    public int get_weight(){
        return this.weight;
    }

    // Nodes of this type will be post-processed into alternation nodes
    // No rendering required
}
