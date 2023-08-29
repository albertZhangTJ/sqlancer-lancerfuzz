package dsqlancer.AST;

import java.util.HashMap;

import dsqlancer.Utils;

// This represents a directed Edge in the AST
public class Edge {

    //kinda duplicate knowledge as Edges will be stored in a list of each node
    //maintained for easiness of comparison
    private Node source;

    private Node destination;
    private HashMap<String, String> args;

    public Edge(Node source, Node destination, HashMap<String, String> args){
        this.source = source;
        this.destination = destination;
        this.args = args;
    }

    public Node get_src(){
        return this.source;
    }

    public Node get_dest(){
        return this.destination;
    }

    public HashMap<String, String> get_args(){
        return Utils.copy_map(this.args);
    }
    public boolean equals(Object other){
        if (other!=null && other instanceof Edge){
            Edge other_edge = (Edge)other;
            return Utils.null_safe_equals(this.source, other_edge.source) && 
                    Utils.null_safe_equals(this.destination, other_edge.destination) && 
                    Utils.null_safe_equals(this.args, other_edge.args);
        }
        return false;
    }

    public String toString(){
        return "[Edge from: "+source.toString()+" to: "+destination.toString()+"]";
    }

}
