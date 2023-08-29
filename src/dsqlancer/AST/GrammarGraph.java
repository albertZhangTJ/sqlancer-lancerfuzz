package dsqlancer.AST;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Pattern;

import dsqlancer.Utils;
import dsqlancer.ANTLR.ANTLRv4Parser;

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
            Utils.oops("GrammarGraph::add_node : node with id "+node.get_id()+"already exists, replacing");
        }
        this.vertices.put(node.get_id(), node);
        return node.get_id();
    }

    public String get_name(){
        if (this.name.equals("")){
            return null;
        }
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
        for (Integer key : this.vertices.keySet()){
            if (Utils.null_safe_equals(identifier, this.vertices.get(key).get_identifier())){
                return key;
            }
        }
        return -1;
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

     // Going through all ActionNodes and add the expected errors to the parent nodes
    // Remove the expected error specifications from the source in the ActionNode
    // For simplicity, we do not remove any ActionNode left empty after the removal
    public void process_expected_errors(){
        for (Integer idx : this.vertices.keySet()){
            if (this.vertices.get(idx) instanceof ActionNode){
                ActionNode an = (ActionNode)this.vertices.get(idx);
                List<String> res = AstUtils.get_expected_errors(an.get_src());
                if (res==null || res.size()<1){
                    continue;
                }
                an.update_src(res.get(0));
                if (res.size()>1){
                    Node parent = this.parent_of(an);
                    for (int i=1; i<res.size(); i++){
                        parent.add_expected_errors(res.get(i));
                    }
                }
            }
        }
    }

    public void process_weights(){
        for (Integer idx : this.vertices.keySet()){
            if (this.vertices.get(idx) instanceof ActionNode){
                ActionNode an = (ActionNode)this.vertices.get(idx);
                List<String> res = AstUtils.get_weight_decl(an.get_src());
                if (res==null || res.size()<1){
                    continue;
                }
                an.update_src(res.get(0));
                if (res.size()==2){
                    Node alt = this.parent_of(an);
                    Node alter = this.parent_of(alt);
                    if (!(alt instanceof AlternativeNode) || !(alter instanceof AlternationNode)){
                        Utils.panic("GrammarGraph::process_weights : expect parent node of the weight definition to be an AlternativeNode");
                    }
                    ((AlternationNode)alter).set_weight(Double.valueOf(res.get(1)), alt);
                }
            }
        }
    }

    public void handle_schema_locals(){
        for (Integer idx : this.vertices.keySet()){
            if (this.vertices.get(idx) instanceof RuleNode){
                RuleNode rn = (RuleNode)this.vertices.get(idx);
                HashMap<String, String> locals = rn.get_locals();
                Set<String> key_set = locals.keySet();
                Pattern pq = Pattern.compile("String\\s{1,}query");
                Pattern pp = Pattern.compile("String\\s{1,}parent_type");
                Pattern ps = Pattern.compile("boolean\\s{1,}is_schema");
                String parent_type = null;
                String query = null;
                boolean is_schema = false;
                for (String key : key_set){
                    if (ps.matcher(key.strip()).find()){
                        is_schema = true;
                    }
                    if (pq.matcher(key.strip()).find()){
                        query = locals.get(key);
                        if (query!=null){
                            query = query.strip();
                            if (query.length()>=2 && query.charAt(0)=='"' && query.charAt(query.length()-1)=='"'){
                                query=query.substring(1, query.length()-1);
                            }
                        }
                    }
                    if (pp.matcher(key.strip()).find()){
                        parent_type = locals.get(key).strip();
                        if (parent_type==null){
                            Utils.panic("GrammarGraph::handle_schema_locals : if parent_type is defined, it must be set to a value that matches another schema reference rule");
                        }
                        if (parent_type.length()>=2 && parent_type.charAt(0)=='"' && parent_type.charAt(parent_type.length()-1)=='"'){
                            parent_type=parent_type.substring(1, parent_type.length()-1);
                        }
                        if (!this.contains_node_with_identifier(parent_type)){
                            Utils.panic("GrammarGraph::handle_schema_locals : Cannot find parent type rule "+parent_type);
                        }
                        
                    }
                }
                if (is_schema){
                    if (query==null || query.length()==0){
                        Utils.panic("GrammarGraph::handle_schema_locals : For each schema reference rule, a query SQL statement must be provided");
                    }
                    rn.set_schema_reference(parent_type, query);
                }
            }
        }
    }
}