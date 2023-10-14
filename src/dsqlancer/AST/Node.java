package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import dsqlancer.Utils;

public class Node {
    private static int nodes_count = 0;

    private int id;
    private String identifier;
    private List<Edge> outward_edges;
    private List<String> expected_errors = new ArrayList<>();
    public boolean walked; //for debugging purpose only
    public boolean is_rendered=false; //eaiser for the fuzzer renderer to follow

    protected int min_depth=-1;
    protected boolean depth_visited = false;

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

    public List<String> get_expected_errors(){
        return Utils.copy_list(this.expected_errors);
    }

    public int get_min_depth(){
        if (this.min_depth!=-1 && this.depth_visited){
            return this.min_depth;
        }
        // cycles found
        if (this.depth_visited){
            return Integer.MAX_VALUE;
        }
        List<Integer> child_depths = new ArrayList<>();
        this.depth_visited = true;
        for (Edge e: this.outward_edges){
            child_depths.add(e.get_dest().get_min_depth());
        }
        Collections.sort(child_depths);
        //terminal node
        if (child_depths.size()==0){
            // System.out.println("Terminal Node found node "+this.id);
            this.min_depth = 1;
            return 1;
        }
        if (child_depths.get(0).intValue()==Integer.MAX_VALUE){
            Utils.panic("Node::get_min_depth : Cycle found on minimal expansion path, no valid finite expansion possible for Node "+this.toString());
        }
        this.min_depth = child_depths.get(0)+1;
        // System.out.println("Depth calculated for node "+this.id+" depth: "+this.min_depth);
        return this.min_depth;
    }
}
