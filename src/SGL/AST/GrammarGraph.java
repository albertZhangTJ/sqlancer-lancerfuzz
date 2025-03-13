package SGL.AST;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.regex.Pattern;

import SGL.DBMSOption;
import SGL.Options;
import SGL.Stage;
import SGL.Utils;
import SGL.parser.SGLParser.*;

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

    private List<String> rule_names;
    //these are the parser rules that do not come with fragment keyword
    //directly callable from outside
    private List<String> callable_rule_names; 

    public GrammarGraph(){
        this.vertices = new LinkedHashMap<>();
        this.options = new LinkedHashMap<>();
        this.encoding_set = 0;
        this.lambda_id = -1;
    }

    public boolean add_rule_name(String name){
        if (rule_names==null){
            rule_names = new ArrayList<>();
        }
        if (rule_names.contains(name)){
            return false;
        }
        rule_names.add(name);
        return true;
    }

    public void add_callable_rule_name(String name){
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

    public Node get_node_with_identifier(String identifier){
        for (Integer key : this.vertices.keySet()){
            if (Utils.null_safe_equals(identifier, this.vertices.get(key).get_identifier())){
                return this.vertices.get(key);
            }
        }
        Utils.panic("GrammarGraph::get_node_with_identifier : cannot find node with identifier "+identifier);
        return null;
    }

    private static String render_tag(String template, String key, String value){
        String tag = "<"+key.toUpperCase()+"/>";
        int length = tag.length()+value.length();
        for (int cursor = 0; cursor<template.length()-tag.length(); cursor++){
            if (template.substring(cursor, cursor+tag.length()).toUpperCase().equals(tag)){
                template = template.substring(0,cursor) + value + template.substring(cursor);
                cursor += value.length();
            }
        }
        return template;
    }

    //TODO: enhance the robustness here
    // This is a rather simple String matching (no parsing here)
    // This procedure will look for each occurence of the string "\>" and search back forward for the nearest "<", remove what's between
    // Also for now spaces in tags are disallowed so any occurence of space before finding the corresponding left bracket will be considered as syntax error
    private static String strip_tags(String template){
        String left_bracket = "<";
        String right_bracket = "/>";
        while (template.indexOf(right_bracket)!=-1){
            int right_idx = template.indexOf(right_bracket);
            int left_idx = template.substring(0, right_idx).lastIndexOf(left_bracket);
            // just a bit of sanity check here, no guarantee
            if (left_idx==-1){
                Utils.panic("GrammarGraph::strip_tags : cannot find left bracket for tag");
            }
            if (template.substring(left_idx, right_idx).indexOf(" ")!=-1){
                Utils.panic("GrammarGraph::strip_tags : found a tag name with space");
            }

            template = template.substring(0, left_idx) + template.substring(right_idx+2);
        }
        return template;
    }

    public String render(String template, List<Stage> stages, List<DBMSOption> options){
        //step 1: render all rules in the current graph
        //replace the <RULES/> tab in the template
        List<String> func_list = new ArrayList<>();
        for (String rule_name: this.rule_names){
            String handle = this.get_node_with_identifier(rule_name).render(func_list, "", false);
            String dispatch = "        if (rule.equals(\""+rule_name+"\")){\n" +
                                "            return "+handle+";\n" +
                                "        }\n";
            String register = "        Fuzzer.rules.add(\""+rule_name+"\");\n" +
                                "        Context.add_rule(\""+rule_name+"\");\n";
            template = render_tag(template, "DISPATCH_RULES", dispatch);
            template = render_tag(template, "INIT_RULES", register);
        }
        for (String rule: func_list){
            template = render_tag(template, "RULES", rule);
        }
        for (Stage stage: stages){
            template = render_tag(template, "STAGES", stage.render());
        }
        for (DBMSOption opt: options){
            if (opt.get_name().equals("JDBC")){
                template = render_tag(template, "JDBC", opt.get_default());
            }
            if (opt.get_name().equals("JDBC_CLASS")){
                template = render_tag(template, "JDBC_CLASS", "Class.forName(\"" + opt.get_default() + "\");\n");
            }
        }
        return strip_tags(template);
    }
}