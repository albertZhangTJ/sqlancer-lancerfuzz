package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;

import org.antlr.v4.runtime.tree.TerminalNode;

import dsqlancer.Options;
import dsqlancer.Utils;
import dsqlancer.ANTLR.ANTLRv4Parser.Action_Context;
import dsqlancer.ANTLR.ANTLRv4Parser.GrammarSpecContext;
import dsqlancer.ANTLR.ANTLRv4Parser.IdentifierContext;
import dsqlancer.ANTLR.ANTLRv4Parser.OptionContext;
import dsqlancer.ANTLR.ANTLRv4Parser.PrequelConstructContext;

// TODO: remove suppress after complete
@SuppressWarnings("unused")
public class GrammarGraphBuilder {
    public static void build_prerequisite(GrammarGraph graph, GrammarSpecContext node, Options options){
        if (node==null){
            Utils.panic("GrammarGraphBuilder::build_prerequisite : root node cannot be null");
        }
        if (graph.get_name()==null){
            String graph_name = "";
            if (node.grammarDecl().identifier().TOKEN_REF()!=null){
                graph_name = node.grammarDecl().identifier().TOKEN_REF().toString();
            }
            else {
                graph_name = node.grammarDecl().identifier().RULE_REF().toString();
            }
            if (graph_name.indexOf("Lexer")>0){
                graph_name = graph_name.substring(0, graph_name.indexOf("Lexer"));
            }
            else if (graph_name.indexOf("Parser")>0){
                graph_name = graph_name.substring(0, graph_name.indexOf("Parser"));
            }
            graph_name = graph_name + "Generator";
        }
        if (node.prequelConstruct().size()>0){
            List<PrequelConstructContext> prequel_construct_list = node.prequelConstruct();
            for (PrequelConstructContext prequel_construct: prequel_construct_list){
                if (prequel_construct.optionsSpec()!=null){
                    for (OptionContext option: prequel_construct.optionsSpec().option()){
                        String identifier = "";
                        if (option.identifier().RULE_REF()!=null){
                            identifier = option.identifier().RULE_REF().toString();
                        }
                        identifier = option.identifier().TOKEN_REF().toString();
                        graph.add_option(identifier, option.optionValue().getText());
                    }
                }

                if (prequel_construct.tokensSpec()!=null && prequel_construct.tokensSpec().idList()!=null){
                    for (IdentifierContext identifier : prequel_construct.tokensSpec().idList().identifier()){
                        if (identifier.TOKEN_REF()==null){
                            Utils.panic("GrammarGraphBuilder::build_prerequisite : Token names must not be null and must start with uppercase letter");
                        }
                        graph.add_node(new ImagRuleNode(identifier.TOKEN_REF().toString()));
                    }
                }

                if (prequel_construct.action_()!=null && !options.ignore_actions){
                    Action_Context action = prequel_construct.action_();
                    IdentifierContext action_identifier = action.identifier();
                    String action_type = "";
                    if (action_identifier.RULE_REF()!=null){
                        action_type = action_identifier.RULE_REF().toString();
                    }
                    else {
                        action_type = action_identifier.TOKEN_REF().toString();
                    }
                    if (action_type.equals("members")){
                        for (TerminalNode child: action.actionBlock().ACTION_CONTENT()){
                            graph.append_members_code(child.toString());
                        }
                    }
                    if (action_type.equals("header")){
                        for (TerminalNode child: action.actionBlock().ACTION_CONTENT()){
                            graph.append_header_code(child.toString());
                        }
                    }
                }
            }
        }

    }

    public static void build_rules(GrammarGraph graph, GrammarSpecContext node, Options options){
        List<RuleNode> generator_rules = new ArrayList<>();
    }
    
    
    public static GrammarGraph build_grammar_graph(GrammarSpecContext lexer_root, GrammarSpecContext parser_root, Options options){
        GrammarGraph graph = new GrammarGraph();
        int lambda_id = graph.add_node(new LambdaNode());
        graph.add_node(new UnlexerRuleNode("EOF"));
        if (lexer_root!=null){
            build_prerequisite(graph, lexer_root, options);
        }
        if (parser_root!=null){
            build_prerequisite(graph, parser_root, options);
        }

        // Ignored the options for the graph, not entirely sure why grammar graph need info on options
        // Will come back to implement if later found needed
        // TODO

        return graph;
    }
}
