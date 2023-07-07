package dsqlancer.AST;

// This represents a directed Edge in the AST
public class Edge {
    public Node destination;
    public List<String> args;

    public Edge(Node destination, List<String> args){
        this.destination = destination;
        this.args = args;
    }

}
