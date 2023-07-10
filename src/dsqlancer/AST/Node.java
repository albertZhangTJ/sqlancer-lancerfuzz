package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;

import dsqlancer.Utils;

public class Node {
    private static int nodes_count = 0;

    private int id;
    private List<Edge> outward_edges;

    public Node(){
        this.id = nodes_count;
        nodes_count++;
        this.outward_edges = new ArrayList<>();
    }

    public int get_id(){
        return this.id;
    }

    public void add_outward_edge(Edge edge){
        if (this.outward_edges.contains(edge)){
            Utils.oops("Node::add_outward_edge : Edge "+edge.toString()+" already exists for node "+this.toString());
        }
        this.outward_edges.add(edge);
    }
}
