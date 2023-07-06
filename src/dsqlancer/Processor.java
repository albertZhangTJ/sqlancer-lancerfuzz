package dsqlancer;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.antlr.mojo.antlr4;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

import dsqlancer.ANTLR.ANTLRv4Lexer;
import dsqlancer.ANTLR.ANTLRv4Parser;
import dsqlancer.ANTLR.ANTLRv4ParserListener;
import dsqlancer.ANTLR.ANTLRv4Parser.DelegateGrammarContext;
import dsqlancer.ANTLR.ANTLRv4Parser.GrammarSpecContext;
import dsqlancer.ANTLR.ANTLRv4Parser.IdentifierContext;
import dsqlancer.ANTLR.ANTLRv4Parser.PrequelConstructContext;
import dsqlancer.ANTLR.ANTLRv4Parser.RuleSpecContext;

public class Processor {
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

    // UTIL
    // might consider refactoring into a util class later
    // in-place merge list2 into list1, ignore duplicates
    public static void in_place_merge(List<T> list1, List<T> list2){
        for (T obj: list2){
            if (!list1.contains(obj)){
                list1.add(obj);
            }
        }
    }

    public GrammarSpecContext parse_grammar(List<String> grammar_files, Options options){
        GrammarSpecContext root = null;
        for (String grammar_file: grammar_files){
            FileInputStream fis = new FileInputStream(grammar_file);
            ANTLRv4Lexer antlr_lexer = new ANTLRv4Lexer(fis);
            TokenStream t_stream = new TokenStream(antlr_lexer);
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

            in_place_merge(grammar_files, collect_imports(current_root, options.cwd));
        }
        return root;
    }

    public void generate_fuzzer(Options options){
        //TODO
    }
}
