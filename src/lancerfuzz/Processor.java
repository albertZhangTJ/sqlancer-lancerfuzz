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

import lancerfuzz.ANTLR.ANTLRv4Lexer;
import lancerfuzz.ANTLR.ANTLRv4Parser;
import lancerfuzz.ANTLR.ANTLRv4Parser.DelegateGrammarContext;
import lancerfuzz.ANTLR.ANTLRv4Parser.GrammarSpecContext;
import lancerfuzz.ANTLR.ANTLRv4Parser.IdentifierContext;
import lancerfuzz.ANTLR.ANTLRv4Parser.PrequelConstructContext;
import lancerfuzz.ANTLR.ANTLRv4Parser.RuleSpecContext;
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
                CharStream fis = new ANTLRFileStream(grammar_file);
                ANTLRv4Lexer antlr_lexer = new ANTLRv4Lexer(fis);
                TokenStream t_stream = new CommonTokenStream(antlr_lexer);
                ANTLRv4Parser antlr_parser = new ANTLRv4Parser(t_stream);
                GrammarSpecContext current_root = antlr_parser.grammarSpec();
                // what if there is a syntax error in the grammar file?
                // looked for antlr_parser._syntaxError as in the python version
                // didn't find anything
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
            if (grammar.endsWith(".g4")){
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
                Utils.panic("Expecting ANTLRv4 grammar file(s) with .g4 file extension");
            }
        }

        GrammarGraph graph = GrammarGraphBuilder.build_grammar_graph(lexer_root, parser_root, options);
        graph.handle_schema_locals();
        Utils.log("Schema locals processed");
        graph.handle_expr_locals();
        Utils.log("Expression locals processed");
        graph.check_imag_rules();
        Utils.log("ImagRules verified");
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

        List<String> template_files = new ArrayList<>();
        template_files.add("action_node.st");
        template_files.add("alternation_node_sub_option.st");
        template_files.add("alternation_node.st");
        template_files.add("alternative_node.st");
        template_files.add("call_rule_name.st");
        template_files.add("charset_node.st");
        template_files.add("expr_core_call_children.st");
        template_files.add("expr_core.st");
        template_files.add("expr_node.st");
        template_files.add("fuzzer.st");
        template_files.add("lambda_node.st");
        template_files.add("literal_node.st");
        template_files.add("quantifier_node.st");
        template_files.add("schema_node.st");
        template_files.add("stage_serialize_rule.st");
        template_files.add("stage.st");
        template_files.add("unlexer_rule_node.st");
        template_files.add("unparser_call_children.st");
        template_files.add("unparser_rule_node.st");
        
        

        Utils.log("Rendering fuzzer");

        TemplateRenderer template = new TemplateRenderer(template_files);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(graph.get_name()+"Fuzzer.java"));
            writer.write(template.render(graph, stages, dbms_options));
            writer.close();
            Utils.log_stage("Fuzzer rendered");
        }
        catch (IOException e) {
            Utils.panic("Processor::generate_fuzzer : IOException, cannot write to file\n"+e.toString());
        }
        


    }
}
