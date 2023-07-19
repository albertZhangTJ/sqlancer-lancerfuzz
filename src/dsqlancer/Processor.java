package dsqlancer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import org.antlr.v4.runtime.*;

import dsqlancer.ANTLR.ANTLRv4Lexer;
import dsqlancer.ANTLR.ANTLRv4Parser;
import dsqlancer.ANTLR.ANTLRv4Parser.DelegateGrammarContext;
import dsqlancer.ANTLR.ANTLRv4Parser.GrammarSpecContext;
import dsqlancer.ANTLR.ANTLRv4Parser.IdentifierContext;
import dsqlancer.ANTLR.ANTLRv4Parser.PrequelConstructContext;
import dsqlancer.ANTLR.ANTLRv4Parser.RuleSpecContext;
import dsqlancer.AST.GrammarGraph;
import dsqlancer.AST.GrammarGraphBuilder;


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

        // TODO: one thing i don't really get here in the grammarinator implementation
        // is that they are updating these in a loop?
        // For multiple grammars this will only keep the last one (or two) right?
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
                System.out.println("Expecting ANTLRv4 grammar file(s) with .g4 file extension");
            }
        }

        GrammarGraph graph = GrammarGraphBuilder.build_grammar_graph(lexer_root, parser_root, options);
        // TODO: analyze graph
        


    }
}
