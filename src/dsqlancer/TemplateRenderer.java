package dsqlancer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import dsqlancer.AST.*;
import io.questdb.std.Hash;
import net.sf.jsqlparser.statement.alter.Alter;

@SuppressWarnings("unused")
public class TemplateRenderer {
    private HashMap<String, String> templates;
    private List<String> template_files;
    private String src;

    public TemplateRenderer(List<String> template_files){
        if (template_files==null || template_files.size()==0){
            Utils.panic("TemplateRenderer::TemplateRenderer : template files cannot be empty");
        }
        this.template_files = template_files;
        this.templates = new HashMap<>();
        this.src = "";
        this.initialize();
    }

    private void initialize(){
        try {
            for (String filename : this.template_files){
                // The template files are expected to come within the jar file instead of being provided by the user
                InputStream in = getClass().getResourceAsStream("/"+filename);
                if (in==null){
                    Utils.panic("TemplateRenderer::initialize : cannot get input stream for template "+filename);
                }
                String file_content = new String(in.readAllBytes(), StandardCharsets.UTF_8);
                String template_name = file_content.substring(2, file_content.indexOf('\n')).strip();
                this.templates.put(template_name, file_content);
            }
            if (this.templates.containsKey("FUZZER")){
                this.src = this.templates.get("FUZZER");
            }
            else {
                Utils.panic("TemplateRenderer::initialize : Expecting a template named FUZZER, template name should be specified on the first line of the source file as a Java single line comment");
            }
        }
        catch (IOException e){
            Utils.panic("TemplateRenderer::initialize : Error reading template files\n"+e.toString());
        }
    }

    // The name is kinda misleading but i cannot come up with a better one for the moment being
    // It's not "replacing" rather prepending value to each tag, the tags will be removed after the whole template is 
    // The reason for not directly removing the tag is that one tag might be assigned multiple values 
    // e.g. the <SCHEMA_NODE\> tag in the fuzzer template corresponds to not one but multiple functions
    private static String replace_tag(String template, String key, String value){
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
                Utils.panic("TemplateRenderer::strip_tags : cannot find left bracket for tag");
            }
            if (template.substring(left_idx, right_idx).indexOf(" ")!=-1){
                Utils.panic("TemplateRenderer::strip_tags : found a tag name with space");
            }

            template = template.substring(0, left_idx) + template.substring(right_idx+2);
        }
        return template;
    }

    public static String gen_function_call(Node callee, Edge call){
        //for simplicity reasons, we are not doing a name matching for parameters
        //instead, we are just providing the parameters in the order specified
        String name = "";
        //ImagRuleNodes are guaranteed to have non-null identifiers
        if (callee instanceof ImagRuleNode){
            name  = callee.get_identifier();
        }
        else{
            name  = callee.get_identifier()==null ? "Node"+callee.get_id() : callee.get_identifier();
        }
        name = name + "(depth-1";
        HashMap<String, String> args = call.get_args();
        if (args==null || args.size()==0){
            name = name +")";
            return name;
        }
        for (String key: args.keySet()){
            String val = args.get(key);
            if (val==null){
                val = "null";
            }
            name = name + ", " + val;
        }
        name = name + ")";
        return name;
    }

    public String render(Node node){
        node.is_rendered = true;
        //schema nodes are expected to be terminal nodes
        if (node instanceof RuleNode && ((RuleNode)node).is_schema_ref()){
            String template = this.templates.get("SCHEMA_NODE");
            if (template==null){
                Utils.panic("TemplateRenderer::render : No template found for schema nodes");
            }
            for (String ee : node.get_expected_errors()){
                template = replace_tag(template, "ee", "        this.expected_error_buffer.add(\""+ee+"\");\n");
            }
            template = replace_tag(template, "rule_name", ((RuleNode)node).get_name());
            String query = ((RuleNode)node).get_query_stmt();
            while (query.contains("$parent_name$")){
                query = query.substring(0, query.indexOf("$parent_name$")) +"\" + parent_name + \"" + query.substring(query.indexOf("$parent_name$")+"$parent_name$".length());
            }
            template = replace_tag(template, "query", query);
            template = replace_tag(template, "attribute_name", ((RuleNode)node).get_attribute_name());
            template = strip_tags(template);
            return template;
        }
        if (node instanceof ActionNode){
            String template = this.templates.get("ACTION_NODE");
            if (template==null){
                Utils.panic("TemplateRenderer::render : No template found for action nodes");
            }
            template = replace_tag(template, "name", node.get_identifier()==null ? "Node"+node.get_id() : node.get_identifier());
            template = replace_tag(template, "src", ((ActionNode)node).get_src());
            return strip_tags(template);
        }
        if (node instanceof AlternationNode){
            String template = this.templates.get("ALTERNATION_NODE");
            if (template==null){
                Utils.panic("TemplateRenderer::render : No template found for alternation nodes");
            }
            for (String ee : node.get_expected_errors()){
                template = replace_tag(template, "ee", "        this.expected_error_buffer.add(\""+ee+"\");\n");
            }
            template = replace_tag(template, "name", node.get_identifier()==null ? "Node"+node.get_id() : node.get_identifier());
            template = replace_tag(template, "MIN_DEPTH", ""+node.get_min_depth());
            AlternationNode anode = (AlternationNode)node;
            template = replace_tag(template, "TOTAL_WEIGHT", ""+anode.get_total_weight());
            // select one sub-paths for minimal expansion
            // This is not optimal but since this is only a corner case (where the user provided an unreasonable depth limit value)
            // For simplicity reasons we will only do a rendering time designation
            // The rationale here is that there must exist at least one node whose min expansion depth is 
            for (Edge e : anode.get_outward_edges()){
                if (e.get_dest().get_min_depth() == anode.get_min_depth()-1){
                    template = replace_tag(template, "call_min_child", gen_function_call(e.get_dest(), e));
                    break; //there might be multiple possible min-expansions, however we just need one
                }
            }
            List<Edge> branches = anode.get_outward_edges();
            List<Double> weights = anode.get_weights();
            if (branches.size()!=weights.size()){
                Utils.panic("TemplateRenderer::render : alternation node weights vector size does not match ");
            }
            for (int i=0; i<branches.size(); i++){
                String s_template = this.templates.get("ALTERNATION_NODE_SUB_OPTION");
                if (s_template==null){
                    Utils.panic("TemplateRenderer::render : No template found for alternation nodes sub options");
                }
                s_template = replace_tag(s_template, "WEIGHT", ""+weights.get(i));
                s_template = replace_tag(s_template, "child_depth", ""+branches.get(i).get_dest().get_min_depth());
                s_template = replace_tag(s_template, "call_child", gen_function_call(branches.get(i).get_dest(), branches.get(i)));
                template = replace_tag(template, "sub_option", strip_tags(s_template));
            }
            return strip_tags(template);
        }
        if (node instanceof AlternativeNode){
            String template = this.templates.get("ALTERNATIVE_NODE");
            if (template==null){
                Utils.panic("TemplateRenderer::render : No template found for alternative nodes");
            }
            for (String ee : node.get_expected_errors()){
                template = replace_tag(template, "ee", "        this.expected_error_buffer.add(\""+ee+"\");\n");
            }
            template = replace_tag(template, "name", node.get_identifier()==null ? "Node"+node.get_id() : node.get_identifier());
            for (Edge e : node.get_outward_edges()){
                template = replace_tag(template, "call_children", "ans = ans + " + gen_function_call(e.get_dest(), e) + ";\n        ");
            }
            template = replace_tag(template, "MIN_DEPTH", ""+node.get_min_depth());
            return strip_tags(template);
        }
        if (node instanceof CharsetNode){
            String template = this.templates.get("CHARSET_NODE");
            if (template==null){
                Utils.panic("TemplateRenderer::render : No template found for charset nodes");
            }
            template = replace_tag(template, "name", node.get_identifier()==null ? "Node"+node.get_id() : node.get_identifier());
            CharsetNode cnode = (CharsetNode)node;
            for (Integer idx : CharSet.get_encoding_characters(cnode.get_charset())){
                template = replace_tag(template, "add_indices", "indices.add("+idx+");\n        ");
            }
            return strip_tags(template);
        }
        //ImagRuleNode are just placeholders
        //there will be another node with same identifier 
        //no need to do anything here
        //the call generator will wire the function call to the actual node
        if (node instanceof ImagRuleNode){
            return "";
        }
        if (node instanceof LambdaNode){
            String template = this.templates.get("LAMBDA_NODE");
            if (template==null){
                Utils.panic("TemplateRenderer::render : No template found for lambda nodes");
            }
            for (String ee : node.get_expected_errors()){
                template = replace_tag(template, "ee", "        this.expected_error_buffer.add(\""+ee+"\");\n");
            }
            template = replace_tag(template, "name", node.get_identifier()==null ? "Node"+node.get_id() : node.get_identifier());
            return strip_tags(template);
        }
        if (node instanceof LiteralNode){
            String template = this.templates.get("LITERAL_NODE");
            if (template==null){
                Utils.panic("TemplateRenderer::render : No template found for literal nodes");
            }
            for (String ee : node.get_expected_errors()){
                template = replace_tag(template, "ee", "        this.expected_error_buffer.add(\""+ee+"\");\n");
            }
            template = replace_tag(template, "NAME", node.get_identifier()==null ? "Node"+node.get_id() : node.get_identifier());
            template = replace_tag(template, "SRC", ((LiteralNode)node).get_src());
            return strip_tags(template);
        }
        if (node instanceof QuantifierNode){
            QuantifierNode n = (QuantifierNode)node;
            String template = this.templates.get("QUANTIFIER_NODE");
            if (template==null){
                Utils.panic("TemplateRenderer::render : No template found for quantifier nodes");
            }
            for (String ee : node.get_expected_errors()){
                template = replace_tag(template, "ee", "        this.expected_error_buffer.add(\""+ee+"\");\n");
            }
            template = replace_tag(template, "NAME", n.get_identifier()==null ? "Node"+n.get_id() : n.get_identifier());
            template = replace_tag(template, "MIN_DEPTH", ""+n.get_min_depth());
            template = replace_tag(template, "QUAN_MIN", ""+n.get_min());
            template = replace_tag(template, "QUAN_MAX", ""+n.get_max());

            for (Edge e : n.get_outward_edges()){
                template = replace_tag(template, "CHILD_NODE_REF", "            ans = ans + " + gen_function_call(e.get_dest(), e)+ ";\n");
            }

            return strip_tags(template);
        }
        if (node instanceof UnlexerRuleNode){
            String template = this.templates.get("UNLEXER_RULE_NODE");
            if (template==null){
                Utils.panic("TemplateRenderer::render : No template found for unlexer rule nodes");
            }
            for (String ee : node.get_expected_errors()){
                template = replace_tag(template, "ee", "        this.expected_error_buffer.add(\""+ee+"\");\n");
            }
            template = replace_tag(template, "name", node.get_identifier()==null ? "Node"+node.get_id() : node.get_identifier());
            for (Edge e : node.get_outward_edges()){
                template = replace_tag(template, "call_children", "        ans = ans + " + gen_function_call(e.get_dest(), e) + ";\n        ");
            }
            template = replace_tag(template, "MIN_DEPTH", ""+node.get_min_depth());
            return strip_tags(template);
        }
        if (node instanceof UnparserRuleNode){
            String template = this.templates.get("UNPARSER_RULE_NODE");
            if (template==null){
                Utils.panic("TemplateRenderer::render : No template found for unparser rule nodes");
            }
            for (String ee : node.get_expected_errors()){
                template = replace_tag(template, "ee", "        this.expected_error_buffer.add(\""+ee+"\");\n");
            }
            template = replace_tag(template, "name", node.get_identifier()==null ? "Node"+node.get_id() : node.get_identifier());
            template = replace_tag(template, "MIN_DEPTH", ""+node.get_min_depth());
            int index = 0;
            for (Edge e: node.get_outward_edges()){
                String ct = this.templates.get("UNPARSER_CALL_CHILDREN");
                if (ct==null){
                    Utils.panic("TemplateRenderer::render : No template found for unparser rule calling children");
                }
                ct = replace_tag(ct, "index", ""+index);
                ct = replace_tag(ct, "call_child", gen_function_call(e.get_dest(), e));
                template = replace_tag(template, "unparser_call_children", strip_tags(ct));
                index++;
            }
            return strip_tags(template);
        }
        return "";
    }

    public String render(GrammarGraph graph, List<Stage> stages, List<DBMSOption> options){
        String template = this.templates.get("FUZZER");
        if (template==null){
            Utils.panic("TemplateRenderer::render : No template found for FUZZER");
        }
        template = replace_tag(template, "graph_name", graph.get_name());
        for (DBMSOption opt : options){
            String val = "        dbms_options.put(\""+opt.get_name()+"\",";
            if (opt.get_default()!=null){
                val = val + "\"" + opt.get_default()+"\")";
            }
            else {
                val = val + "null)";
            }
            val = val + ";\n";
            template = replace_tag(template, "DBMS_OPTIONS", val);
        }
        for (Stage stage : stages){
            String st_template = this.templates.get("STAGE");
            if (st_template==null){
                Utils.panic("TemplateRenderer::render : No template found for stage");
            }
            st_template = replace_tag(st_template, "NAME", stage.get_name());
            st_template = replace_tag(st_template, "STAGE_MIN", ""+stage.get_min());
            st_template = replace_tag(st_template, "STAGE_MAX", ""+stage.get_max());
            st_template = replace_tag(st_template, "NUM_RULES", ""+stage.get_num_rules());
            List<String> rules = stage.get_rules();
            for (int i=0; i<rules.size(); i++){
                String stc_template = this.templates.get("STAGE_CALL_RULE");
                if (stc_template==null){
                    Utils.panic("TemplateRenderer::render : No template found for stage calling rule");
                }
                stc_template = replace_tag(stc_template, "INDEX", ""+i);
                stc_template = replace_tag(stc_template, "RULE_NAME", rules.get(i));
                stc_template = strip_tags(stc_template);
                st_template = replace_tag(st_template, "STAGE_CALL_RULE", stc_template);
            }
            template = replace_tag(template, "STAGE", strip_tags(st_template));
        }
        HashMap<Integer, Node> vertices = graph.get_vertices();
        for (Integer i : vertices.keySet()){
            template = replace_tag(template, "RULE", render(vertices.get(i)));
        }
        return strip_tags(template); //TODO
    }
}
