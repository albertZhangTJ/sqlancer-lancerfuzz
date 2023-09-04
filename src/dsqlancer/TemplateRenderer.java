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

    public String render(GrammarGraph graph, List<Stage> stages, List<DBMSOption> options){
        return null; //TODO
    }
}
