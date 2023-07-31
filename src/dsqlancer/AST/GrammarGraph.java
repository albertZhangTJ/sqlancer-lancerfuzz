package dsqlancer.AST;

import java.util.List;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import dsqlancer.Utils;

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

}