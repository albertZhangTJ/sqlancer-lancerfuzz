package lancerfuzz.AST;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import lancerfuzz.Utils;
import lancerfuzz.ANTLR.ANTLRv4Parser;

@SuppressWarnings("unused")
public class GrammarGraph{
    private String name = "";
    private LinkedHashMap<Integer, Node> vertices;
    private LinkedHashMap<String, String> options;
    private int encoding_set;
    private String header = ""; //raw action code for header
    private String members = ""; //raw action code for member methods
    private Node default_rule = null;
    private int lambda_id;


    public GrammarGraph(){
        this.vertices = new LinkedHashMap<>();
        this.options = new LinkedHashMap<>();
        this.encoding_set = 0;
        this.lambda_id = -1;
    }

    public void set_lambda_id(int lambda_id){
        if (this.lambda_id!=-1){
            Utils.oops("GrammarGraph::add_node : attempting to overwrite existing lambda id, ignored");
        }
        else {
            this.lambda_id = lambda_id;
        }
    }

    public int get_lambda_id(){
        return this.lambda_id;
    }

    public boolean contains_node_with_id(int id){
        return this.vertices.containsKey(id);
    }
    
    public boolean contains_node_with_identifier(String identifier){
        if (identifier==null){
            return false;
        }
        Set<Integer> key_set = this.vertices.keySet();
        for (Integer key : key_set){
            if (Utils.null_safe_equals(identifier, this.vertices.get(key).get_identifier())){
                return true;
            }
        }
        return false;
    }


    public int add_node(Node node){
        if (this.vertices.get(node.get_id())!=null) {
            Utils.panic("GrammarGraph::add_node : node with id "+node.get_id()+" already exists, do not add the same node twice to the graph");
        }
        this.vertices.put(node.get_id(), node);
        return node.get_id();
    }

    public String get_name(){
        return this.name;
    }

    // effectively make this.name final
    // only changes when not set before
    public String set_name(String name){
        if (this.name.equals("")){
            this.name = name;
        }
        return this.name;
    }

    public void add_edge(Node source, Node destination, HashMap<String, String> args){
        if (this.vertices.get(source.get_id())==null){
            Utils.panic("GrammarGraph::add_edge : source node "+source.toString()+" does not exist");
        }
        if (this.vertices.get(destination.get_id())==null){
            Utils.panic("GrammarGraph::add_edge : destination node "+destination.toString()+" does not exist");
        }
        
        Edge edge = new Edge(this.vertices.get(source.get_id()), this.vertices.get(destination.get_id()), args);

        this.vertices.get(source.get_id()).add_outward_edge(edge);
    }

    public void add_edge(int source_id, int destination_id, HashMap<String, String> args){
        if (this.vertices.get(source_id)==null){
            Utils.panic("GrammarGraph::add_edge : source node with id "+source_id+" does not exist");
        }
        if (this.vertices.get(destination_id)==null){
            Utils.panic("GrammarGraph::add_edge : destination node with id "+destination_id+" does not exist");
        }
        
        Edge edge = new Edge(this.vertices.get(source_id), this.vertices.get(destination_id), args);

        this.vertices.get(source_id).add_outward_edge(edge);
    }

    public void add_option(String key, String value){
        if (this.options.get(key)!=null){
            Utils.oops("GrammarGraph::add_option : graph "+this.name+" already has an option "+
                    key+" with value "+this.options.get(key)+" , replacing with "+value);
        }
        this.options.put(key, value);
    }

    public String get_option(String key){
        return this.options.get(key);
    }

    public void append_members_code(String code){
        this.members = this.members + "\n" + code;
    }

    public String get_members(){
        return this.members;
    }

    public void append_header_code(String code){
        this.header = this.header + "\n" + code;
    }

    public String get_header(){
        return this.header;
    }

    public void set_encoding(int encoding){
        if (encoding<0 || encoding>2){
            Utils.panic("GrammarGraph::set_encoding : invalid option "+encoding+". Valid encodings are 0 for any ASCII characters, 1 for any ASCII letters, 2 for any Unicode characters");
        }
        this.encoding_set = encoding;
    }

    public int get_encoding(){
        return this.encoding_set;
    }

    public HashMap<Integer, Node> get_vertices(){
        return Utils.copy_map(this.vertices);
    }

    public int get_node_id_with_identifier(String identifier){
        int imag_id = -1;
        for (Integer key : this.vertices.keySet()){
            if (Utils.null_safe_equals(identifier, this.vertices.get(key).get_identifier())){
                if (this.vertices.get(key) instanceof ImagRuleNode){
                    imag_id = key;
                }
                else {
                    return key;
                }
            }
        }
        
        return imag_id;
    }

    public void set_default_rule(String rule_name){
        int rule_id = this.get_node_id_with_identifier(rule_name);
        if (rule_id==-1){
            Utils.panic("GrammarGraph::set_default_rule : cannot find rule with name "+rule_name);
        }
        this.default_rule=this.vertices.get(rule_id);
    }

   

    // for debugging purpose
    public void walk_print(Node root, String parent_identifier){
        if (root.walked){
            return;
        }
        System.out.print(parent_identifier+" ==> ");
        System.out.println(root);
        root.walked=true;
        for (Edge e : root.get_outward_edges()){
            walk_print(e.get_dest(), ""+root.get_identifier()+" "+root.get_id()+" "+(e.get_args()==null ? "null" : e.get_args().toString()));
        }
    }

    // for debugging purpose
    public void walk_print(){
        System.out.println("Header code:\n"+this.header+"\n");
        System.out.println("Member code:\n"+this.members+"\n");
        this.walk_print(this.default_rule, "ROOT");
    }

    // Used for post-processing the AST
    // Should not be called on a Node with multiple parents
    public Node parent_of(Node node){
        boolean parent_found = false;
        Node parent = null;
        for (Integer idx : this.vertices.keySet()){
            Node n = this.vertices.get(idx);
            for (Edge e : n.get_outward_edges()){
                if (e.get_dest().equals(node)){
                    if (parent_found){
                        Utils.panic("GrammarGraph::parent_of : "+node.toString()+" has multiple parents");
                    }
                    parent = n;
                    parent_found = true;
                }
            }
        }
        if (!parent_found){
            Utils.panic("GrammarGraph::parent_of : "+node.toString()+" has no parent");
        }
        return parent;
    }

    public Node get_defaut_rule(){
        return this.default_rule;
    }


    //calculate the min depth for each node
    //The result is stored in the each node object
    //pre-compute to detected unsolvable cycles early and save time for the rendering process
    public void calc_depth(){
        List<Node> relaxed = new ArrayList<>();
        List<Node> relaxing = new ArrayList<>();
        List<Node> unrelaxed = new ArrayList<>();
        for (Integer i: this.vertices.keySet()){
            unrelaxed.add(this.vertices.get(i));
        }
        for (int i=0; i<unrelaxed.size(); i++){
            if (unrelaxed.get(i).get_outward_edges().size()==0){
                unrelaxed.get(i).set_min_depth(0);
                relaxed.add(unrelaxed.remove(i));
                i--;
            }
        }
        int steps = 1;
        while (unrelaxed.size()>0){
            for (int i=0; i<unrelaxed.size(); i++){
                if (unrelaxed.get(i) instanceof AlternationNode){
                    for (int j=0; j<unrelaxed.get(i).get_outward_edges().size(); j++){
                        if (relaxed.contains(unrelaxed.get(i).get_outward_edges().get(j).get_dest())){
                            relaxing.add(unrelaxed.remove(i));
                            i--;
                            break;
                        }
                    }
                }
                else {
                    boolean all_children_relaxed = true;
                    for (int j=0; j<unrelaxed.get(i).get_outward_edges().size(); j++){
                        if (!relaxed.contains(unrelaxed.get(i).get_outward_edges().get(j).get_dest())){
                            all_children_relaxed = false;
                            break;
                        }
                    }
                    if (all_children_relaxed){
                        relaxing.add(unrelaxed.remove(i));
                        i--;
                    }
                }
            }
            if (relaxing.size()==0){
                Utils.panic("GrammarGraph::calc_depth : Unrelaxable nodes found, infinite loop?");
            }
            while (relaxing.size()>0){
                relaxing.get(0).set_min_depth(steps);
                relaxed.add(relaxing.remove(0));
            }
            steps++;
        }
    }

    //Image rule nodes are nodes that are references to rules defined outside the file
    //as this is just referring to an identifier in the grammar file
    //it should not have an children nodes
    //we will record the id of the actual rule node 
    //this should only be called after the entire graph is built so that the referred node is guaranteed to be there
    //but before rendering the fuzzer template so that the renderer can find the real node
    public void check_imag_rules(){
        for (Integer key : this.vertices.keySet()){
            if (this.vertices.get(key) instanceof ImagRuleNode){
                ImagRuleNode i_node = (ImagRuleNode)(this.vertices.get(key));
                int real_id = this.get_node_id_with_identifier(i_node.get_identifier());
                if (real_id==key.intValue()){
                    Utils.panic("GrammarGraph::check_imag_rules : cannot find referred node with identifier: " + i_node.get_identifier());
                }
                i_node.set_real_id(real_id);
            }
        }
    }

    public void check_for_duplicate_identifier(){
        //despite this is O(N^2), since the number of nodes are not expected to be large
        //this_is_fine.jpeg 
        List<Integer> indices = new ArrayList<>(this.vertices.keySet());
        for (int i=0; i<indices.size(); i++){
            for (int j=i+1; j<indices.size(); j++){
                Node a = this.vertices.get(indices.get(i));
                Node b = this.vertices.get(indices.get(j));
                //if at least one side is an ImagRuleNode then it is fine
                //as ImagRuleNodes are pointers to the actual RuleNode
                //For nodes without identifiers, the mechanism of Node will ensure no two nodes will have the same id
                if (!(a instanceof ImagRuleNode || b instanceof ImagRuleNode) && 
                        !(a.get_identifier()==null || b.get_identifier()==null) &&
                        a.get_identifier().equals(b.get_identifier())){
                    Utils.panic("GrammarGraph::check_for_duplicate_identifier : Redefinition of rule "+a.get_identifier());
                }
            }
        }
    }
}