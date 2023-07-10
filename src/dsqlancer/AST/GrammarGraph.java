package dsqlancer.AST;

import java.util.List;
import java.util.LinkedHashMap;

import dsqlancer.Utils;

@SuppressWarnings("unused")
public class GrammarGraph{
    private String name = "";
    private LinkedHashMap<Integer, Node> vertices;
    private String header = "";
    private String members = "";
    private Node default_rule = null;

    public GrammarGraph(){
        this.vertices = new LinkedHashMap<>();

    }

    public void add_node(Node node){
        if (this.vertices.get(node.get_id())!=null) {
            Utils.oops("GrammarGraph::add_node : node with id "+node.get_id()+"already exists");
        }
        this.vertices.put(node.get_id(), node);
    }

    public void add_edge(Node source, Node destination, List<String> args){
        if (this.vertices.get(source.get_id())==null){
            Utils.panic("GrammarGraph::add_edge : source node "+source.toString()+" does not exist");
        }
        if (this.vertices.get(destination.get_id())==null){
            Utils.panic("GrammarGraph::add_edge : destination node "+destination.toString()+" does not exist");
        }
        
        Edge edge = new Edge(this.vertices.get(source.get_id()), this.vertices.get(destination.get_id()), args);

        this.vertices.get(source.get_id()).add_outward_edge(edge);
    }

    public void add_edge(int source_id, int destination_id, List<String> args){
        if (this.vertices.get(source_id)==null){
            Utils.panic("GrammarGraph::add_edge : source node with id "+source_id+" does not exist");
        }
        if (this.vertices.get(destination_id)==null){
            Utils.panic("GrammarGraph::add_edge : destination node with id "+destination_id+" does not exist");
        }
        
        Edge edge = new Edge(this.vertices.get(source_id), this.vertices.get(destination_id), args);

        this.vertices.get(source_id).add_outward_edge(edge);
    }
}