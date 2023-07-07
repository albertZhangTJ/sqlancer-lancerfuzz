package dsqlancer.AST;

import java.util.ArrayList;

public class Node {
    public static int nodes_count = 0;

    public int id;
    public List<Edge> outward_edges;

    public Node(){
        this.id = nodes_count;
        nodes_count++;
        this.outward_edges = new ArrayList<>();
    }

    
}
