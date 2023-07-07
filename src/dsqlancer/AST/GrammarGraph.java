package dsqlancer.AST;

import java.util.LinkedHashMap;

public class GrammarGraph{
    public String name = "";
    public LinkedHashMap<Integer, Node> vertices;
    public String header = "";
    public String members = "";
    public Node default_rule = null;

    public GrammarGraph(){
        this.vertices = new LinkedHashMap<>();

    }

    public void add_node(Node node){
        if (this.vertices.get(node.id)!=null) {
            System.out.println("WARNING: GrammarGraph::add_node, node with id "+node.id+"already exists");
        }
        this.vertices.put(node.id, node);
    }

    public void add_edge(Node source, Node destination, List<String> args){
        if (this.vertices.get(source.id)==null){
            System.out.println("ERROR: GrammarGraph::add_edge, source node "+source.toString()+" does not exist");
            System.exit(1);
        }
        if (this.vertices.get(destination.id)==null){
            System.out.println("ERROR: GrammarGraph::add_edge, destination node "+dest.toString()+" does not exist");
            System.exit(1);
        }
    }
}