package lancerfuzz.AST;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Pattern;

import lancerfuzz.Options;

import lancerfuzz.Utils;
import lancerfuzz.parser.SGLParser.*;

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

    private static List<String> rule_names;
    //these are the parser rules that do not come with fragment keyword
    //directly callable from outside
    private static List<String> callable_rule_names; 

    public GrammarGraph(){
        this.vertices = new LinkedHashMap<>();
        this.options = new LinkedHashMap<>();
        this.encoding_set = 0;
        this.lambda_id = -1;
    }

    public static boolean add_rule_name(String name){
        if (rule_names==null){
            rule_names = new ArrayList<>();
        }
        if (rule_names.contains(name)){
            return false;
        }
        rule_names.add(name);
        return true;
    }

    public static void add_callable_rule_name(String name){
        if (callable_rule_names==null){
            callable_rule_names = new ArrayList<>();
        }
        callable_rule_names.add(name);
    }
    public static GrammarGraph build(List<GrammarSpecContext> roots, Options opt){
        GrammarGraph graph = new GrammarGraph();
        for (GrammarSpecContext root: roots){
            RulesContext rules = root.rules();
            graph.set_name(root.grammarDecl().identifier().getText());
            for (RuleSpecContext rulespec : rules.ruleSpec()){
                RuleNode.build(graph, rulespec);
            }
        }
        Set<Integer> key_set = graph.vertices.keySet();
        for (Integer key : key_set){
            graph.vertices.get(key).post_process();
        }
        return graph;
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

    public void add_edge(Node source, Node destination){
        if (this.vertices.get(source.get_id())==null){
            Utils.panic("GrammarGraph::add_edge : source node "+source.toString()+" does not exist");
        }
        if (this.vertices.get(destination.get_id())==null){
            Utils.panic("GrammarGraph::add_edge : destination node "+destination.toString()+" does not exist");
        }
        
        Edge edge = new Edge(this.vertices.get(destination.get_id()));

        this.vertices.get(source.get_id()).add_outward_edge(edge);
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

   

    // for debugging purpose
    public void walk_print(Node root, String parent_identifier){
        if (root.walked){
            return;
        }
        System.out.print(parent_identifier+" ==> ");
        System.out.println(root);
        root.walked=true;
        for (Edge e : root.get_outward_edges()){
            walk_print(e.get_dest(), ""+root.get_identifier()+" "+root.get_id());
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
                if (!(a.get_identifier()==null || b.get_identifier()==null) &&
                        a.get_identifier().equals(b.get_identifier())){
                    Utils.panic("GrammarGraph::check_for_duplicate_identifier : Redefinition of rule "+a.get_identifier());
                }
            }
        }
    }

    public String render(String template){
        return "TODO";
    }
}