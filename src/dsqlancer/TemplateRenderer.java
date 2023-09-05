package dsqlancer;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import dsqlancer.AST.*;

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
                InputStream in = getClass().getResourceAsStream(filename);
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
    private String replace_tag(String template, String key, String value){
        String tag = "<"+key+"/>";
        int length = tag.length()+value.length();
        for (int cursor = 0; cursor<template.length()-tag.length(); cursor++){
            if (template.substring(cursor, cursor+tag.length()).equals(tag)){
                template = template.substring(0,cursor) + value + template.substring(cursor);
                cursor += value.length();
            }
        }
        return template;
    }

    // This is a rather simple String matching (no parsing here)
    // This procedure will look for each occurence of the string "\>" and search back forward for the nearest "<", remove what's between
    // Also for now spaces in tags are disallowed so any occurence of space before finding the corresponding left bracket will be considered as syntax error
    private String strip_tags(String template){
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

    public String render(GrammarGraph graph, List<Stage> stages, List<DBMSOption> options){
        return null; //TODO
    }
}
