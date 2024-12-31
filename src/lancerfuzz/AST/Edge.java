package lancerfuzz.AST;

import java.util.HashMap;

import lancerfuzz.Utils;

// This represents a directed Edge in the AST
public class Edge {

    private Node destination;

    public Edge(Node destination){
        this.destination = destinationp;
    }

    public Node get_dest(){
        return this.destination;
    }

    public boolean equals(Object other){
        if (other!=null && other instanceof Edge){
            Edge other_edge = (Edge)other;
            return Utils.null_safe_equals(this.destination, other_edge.destination)
        }
        return false;
    }

    public String toString(){
        return "[Edge to: "+destination.toString()+"]";
    }

}
