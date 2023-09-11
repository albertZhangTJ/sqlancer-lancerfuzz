package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;

import dsqlancer.Utils;

public class Node {
    private static int nodes_count = 0;

    private int id;
    private String identifier;
    private List<Edge> outward_edges;
    private List<String> expected_errors = new ArrayList<>();
    public boolean walked; //for debugging purpose only
    public boolean is_rendered=false; //eaiser for the fuzzer renderer to follow

    public Node(){
        this.id = nodes_count;
        nodes_count++;
        this.outward_edges = new ArrayList<>();
        this.identifier = null;
        this.walked=false;
    }

    public Node(String identifier){
        this.id = nodes_count;
        nodes_count++;
        this.outward_edges = new ArrayList<>();
        this.identifier = identifier;
        this.walked=false;
    }

    public Node(String name, String label){
        this.id = nodes_count;
        nodes_count++;
        this.outward_edges = new ArrayList<>();
        if (label==null){
            this.identifier = name;
        }
        else {
            this.identifier = name + "_" + label;
        }
        this.walked=false;
    }

    public int get_id(){
        return this.id;
    }

    public void add_outward_edge(Edge edge){
        if (this.outward_edges.contains(edge)){
            //Utils.oops("Node::add_outward_edge : Edge "+edge.toString()+" already exists for node "+this.toString());
        }
        this.outward_edges.add(edge);
    }

    public List<Edge> get_outward_edges(){
        return Utils.copy_list(this.outward_edges);
    }

    public static int total_nodes(){
        return nodes_count;
    }

    public String get_identifier(){
        return this.identifier;
    }

    public String toString(){
        return "\n[NODE]\n"+
                "    id: "+this.id+"\n"+
                "    identifier: "+this.identifier+"\n"+
                "    Expected_errors: "+this.expected_errors.toString();
    }

    public void add_expected_errors(String content){
        if (content!=null && content.length()>0){
            this.expected_errors.add(content);
        }
    }
}
