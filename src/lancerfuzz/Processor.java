package lancerfuzz;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.ObjectInputFilter.Config;
import java.util.regex.Pattern;
import java.io.BufferedWriter;
import java.io.FileWriter;

import org.antlr.v4.runtime.*;
import org.json.JSONObject;

import lancerfuzz.parser.SGLLexer;
import lancerfuzz.parser.SGLParser;
import lancerfuzz.parser.SGLParser.DelegateGrammarContext;
import lancerfuzz.parser.SGLParser.GrammarSpecContext;
import lancerfuzz.parser.SGLParser.IdentifierContext;
import lancerfuzz.parser.SGLParser.PrequelConstructContext;
import lancerfuzz.parser.SGLParser.RuleSpecContext;
import lancerfuzz.AST.GrammarGraph;
import lancerfuzz.AST.GrammarGraphBuilder;

@SuppressWarnings("unused")
public class Processor {

    // Find any grammar file name that are imported by the root in base_dir
    public static List<String> collect_imports(GrammarSpecContext root, String base_dir){
        List<String> import_list = new ArrayList<>();
        for (PrequelConstructContext prerequisite: root.prequelConstruct()){
            if (prerequisite.delegateGrammars() != null && 
                    prerequisite.delegateGrammars().delegateGrammar().size()>0){
                List<DelegateGrammarContext> delegate_grammars = prerequisite.delegateGrammars().delegateGrammar();
                for (DelegateGrammarContext delegate_grammar: delegate_grammars){
                    IdentifierContext identifier = delegate_grammar.identifier(0);
                    String file_name = "";
                    if (identifier.RULE_REF()!=null){
                        file_name = identifier.RULE_REF().toString() + ".g4";
                    }
                    else {
                        file_name = identifier.TOKEN_REF().toString() + ".g4";
                    }
                    Path b_dir = Paths.get(base_dir).toAbsolutePath();
                    Path file_path = b_dir.resolve(file_name);
                    import_list.add(file_path.toString());
                }
            }
        }

        return import_list;
    }


    // Parse a grammar file
    // Scan the current working directory (cwd is cmdline parameters) for any dependency
    public static GrammarSpecContext parse_grammar(List<String> grammar_files, Options options){
        GrammarSpecContext root = null;
        for (String grammar_file: grammar_files){
            try {
                @SuppressWarnings("deprecation")
                SGLParser parser = new SGLParser(new CommonTokenStream(new SGLLexer(new ANTLRFileStream(grammar_file))));
                GrammarSpecContext current_root = parser.grammarSpec();
                if (root == null){
                    root = current_root;
                }
                else {
                    for (RuleSpecContext rule: current_root.rules().ruleSpec()){
                        root.rules().addChild(rule);
                    }
                }

                Utils.in_place_merge(grammar_files, collect_imports(current_root, options.cwd));
            }
            catch (IOException e) {
                Utils.panic("Processor::parse_grammar : Failed to open "+grammar_file);
            }
        }
        return root;
    }

    @SuppressWarnings("unused")
    public static void generate_fuzzer(Options options){
        GrammarSpecContext lexer_root = null;
        GrammarSpecContext parser_root = null;

        for (String grammar: options.grammarRules){
            if (grammar.endsWith(".sgl")){
                List<String> work_list = new ArrayList<>();
                work_list.add(grammar);
                GrammarSpecContext root = parse_grammar(work_list, options);
                if (root.grammarDecl().grammarType().LEXER()!=null || root.grammarDecl().grammarType().PARSER()==null){
                    lexer_root = root;
                }
                else {
                    parser_root = root;
                }
            }
            else {
                Utils.panic("Expecting SGL grammar file(s) with .sgl file extension");
            }
        }

        GrammarGraph graph = GrammarGraph.build(parser_root, lexer_root, options);
        graph.check_for_duplicate_identifier();
        Utils.log_stage("Grammar graph sanity checked passed");
        graph.calc_depth();
        Utils.log("Grammar graph depth calculated");
        //graph.walk_print(); //for debugging

        JSONObject config_file = ConfigProcessor.read_json_file(options.config);
        List<Stage> stages = ConfigProcessor.get_stages(config_file, graph.get_defaut_rule()==null ? null : graph.get_defaut_rule().get_identifier());
        List<DBMSOption> dbms_options = ConfigProcessor.get_options(config_file);
        ConfigProcessor.sanity_check(graph, stages);
        Utils.log_stage("Configuration sanity checked passed");
        
        

        Utils.log("Rendering fuzzer");
        String template = "";
        // The template files are expected to come within the jar file instead of being provided by the user
        try{
            InputStream in = getClass().getResourceAsStream("/fuzzer.st");
            if (in==null){
                Utils.panic("Processor::generate_fuzzer : cannot get input stream for template fuzzer.st");
            }
            template = new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
        catch (IOException e){
            Utils.panic("TemplateRenderer::initialize : Error reading template files\n"+e.toString());
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Fuzzer.java"));
            writer.write(graph.render(template));
            writer.close();
            Utils.log_stage("Fuzzer rendered");
        }
        catch (IOException e) {
            Utils.panic("Processor::generate_fuzzer : IOException, cannot write to file\n"+e.toString());
        }
        


    }
}
