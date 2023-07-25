package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.google.protobuf.TextFormat.Parser;

import org.antlr.v4.runtime.ParserRuleContext;

import dsqlancer.Options;
import dsqlancer.Utils;
import dsqlancer.ANTLR.ANTLRv4Parser;
import dsqlancer.ANTLR.ANTLRv4Parser.Action_Context;
import dsqlancer.ANTLR.ANTLRv4Parser.ArgActionBlockContext;
import dsqlancer.ANTLR.ANTLRv4Parser.FlexibleParserRuleContext;
import dsqlancer.ANTLR.ANTLRv4Parser.GrammarSpecContext;
import dsqlancer.ANTLR.ANTLRv4Parser.IdentifierContext;
import dsqlancer.ANTLR.ANTLRv4Parser.OptionContext;
import dsqlancer.ANTLR.ANTLRv4Parser.ParserRuleSpecContext;
import dsqlancer.ANTLR.ANTLRv4Parser.LexerRuleSpecContext;
import dsqlancer.ANTLR.ANTLRv4Parser.LocalsSpecContext;
import dsqlancer.ANTLR.ANTLRv4Parser.ModeSpecContext;
import dsqlancer.ANTLR.ANTLRv4Parser.PrequelConstructContext;
import dsqlancer.ANTLR.ANTLRv4Parser.RuleAltListContext;
import dsqlancer.ANTLR.ANTLRv4Parser.AltListContext;
import dsqlancer.ANTLR.ANTLRv4Parser.LexerAltListContext;
import dsqlancer.ANTLR.ANTLRv4Parser.RuleReturnsContext;
import dsqlancer.ANTLR.ANTLRv4Parser.RuleSpecContext;

// TODO: remove suppress after complete
@SuppressWarnings("unused")
public class GrammarGraphBuilder {

    //Since Java does not have a getattr, the behavior of this function is slightly different with the 
    //corresponding function in Grammarinator
    public static String find_condition(FlexibleParserRuleContext node, Options options){
        if (options.ignore_actions){
            return "1";
        }
        if (node.actionBlock()!=null){
            if (node.actionBlock().ACTION_CONTENT()!=null && node.QUESTION()!=null){
                String ans = "";
                for (TerminalNode child : node.actionBlock().ACTION_CONTENT()){
                    ans = ans + child.toString();
                }
                return ans;
            }
            return "1";
        }
        if (node.element()!=null){
            return find_condition(node.element(0), options);
        }
        if (node.lexerElement()!=null){
            return find_condition(node.lexerElement(0), options);
        }
        if (node.alternative()!=null){
            //not entirely sure how this should behave
            return find_condition(node.alternative().get(0), options); 
        }
        if (node.lexerElements()!=null){
            return find_condition(node.lexerElements(), options);
        }
        return "1";
    }

    public static HashMap<String, String> arg_action_block(FlexibleParserRuleContext node){
        HashMap<String, String> args = new HashMap<>();
        ArgActionBlockContext aabc = node.argActionBlock();

        if (aabc!=null){
            String chr_args = "";
            for (TerminalNode chr_arg: aabc.ARGUMENT_CONTENT()){
                chr_args = chr_args+chr_arg.toString();
            }
            String[] chr_args_list = chr_args.split(",");
            for (String s : chr_args_list){
                if (s.contains("=")){
                    args.put(s.split("=")[0].strip(), s.split("=")[1].strip());
                }
                else {
                    args.put(s.strip(), null);
                }
            }
        }
        return args;
    }

    // I personally feels that this implementation is not quite elegant
    // so many instanceof and type castings
    // But an alternative would require modification to the ANTLR generated code
    // And the current method is how grammarinator did this
    //indices == [alt_idx, quant_idx, chr_idx]
    public static void build_expr(GrammarGraph graph, RuleNode rule, ParserRuleContext node, 
                                    int parent_id, List<Integer> indices, Options options){
        if (node instanceof ANTLRv4Parser.ParserRuleSpecContext){
            if (!options.ignore_actions){
                rule.set_args(arg_action_block((ParserRuleSpecContext)node));
                rule.set_locals(arg_action_block(((ParserRuleSpecContext)node).localsSpec()));
                rule.set_returns(arg_action_block(((ParserRuleSpecContext)node).ruleReturns()));
            }
            build_expr(graph, rule, ((ParserRuleSpecContext)node).ruleBlock(), parent_id, indices, options);
        }

        else if (node instanceof ANTLRv4Parser.RuleAltListContext || node instanceof ANTLRv4Parser.AltListContext || node instanceof ANTLRv4Parser.LexerAltListContext){
            List<ParseTree> children_list = null;
            if (node instanceof ANTLRv4Parser.RuleAltListContext){
                children_list = ((RuleAltListContext)node).children;
            }
            else if (node instanceof ANTLRv4Parser.AltListContext){
                children_list = ((AltListContext)node).children;
            }
            else if (node instanceof ANTLRv4Parser.LexerAltListContext) {
                children_list = ((LexerAltListContext)node).children;
            }
            List<FlexibleParserRuleContext> children = new ArrayList<>();
            for (ParseTree child : children_list){
                if (child instanceof FlexibleParserRuleContext){
                    children.add((FlexibleParserRuleContext)child);
                }
            }
            if (children.size()==1){
                build_expr(graph, rule, children.get(0), parent_id, indices, options);
                return;
            }
            List<String> conditions = new ArrayList<>();
            for (FlexibleParserRuleContext child : children){
                conditions.add(find_condition(child, options));
            }
            int alt_id = graph.add_node(new AlternationNode(rule.get_name(), indices.get(0), conditions));
            int parent_alt_index = indices.get(0);
            indices.set(0, indices.get(0)+1);
            graph.add_edge(parent_id, alt_id, null);
            for (int i=0; i<children.size(); i++){
                int alter_id = graph.add_node(new AlternativeNode(rule.get_name(), parent_alt_index, i));
                graph.add_edge(alt_id, alter_id, null);
                build_expr(graph, rule, children.get(i), alter_id, indices, options);
            }
        }
    }


    public static void build_rule(GrammarGraph graph, RuleNode rule, ParserRuleContext node){
        if (rule instanceof UnlexerRuleNode){
            
        }   
    }

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
        LinkedHashMap<RuleNode, ParserRuleContext> generator_rules = new LinkedHashMap<>();
        List<String> duplicate_rules = new ArrayList<>();

        for (RuleSpecContext rule : node.rules().ruleSpec()){
            ParserRuleContext antlr_node = null;
            RuleNode rule_node = null;
            if (rule.parserRuleSpec()!=null){
                ParserRuleSpecContext rule_spec = rule.parserRuleSpec();
                rule_node = new UnparserRuleNode(rule_spec.RULE_REF().toString());
                antlr_node = rule_spec;
            }
            else if (rule.lexerRuleSpec()!=null){
                LexerRuleSpecContext rule_spec = rule.lexerRuleSpec();
                rule_node =new UnlexerRuleNode(rule_spec.TOKEN_REF().toString());
                antlr_node = rule_spec;
            }
            else {
                Utils.panic("GrammarGraphBuilder::build_rules : Something went very wrong, this line should never be executed");
            }

            if (!graph.contains_node_with_identifier(rule_node.get_identifier())){
                graph.add_node(rule_node);
                generator_rules.put(rule_node, antlr_node);
            }
            else {
                duplicate_rules.add(rule_node.get_identifier());
            }
        }


        for (ModeSpecContext mode_spec : node.modeSpec()){
            for (LexerRuleSpecContext rule_spec : mode_spec.lexerRuleSpec()){
                UnlexerRuleNode rule_node = new UnlexerRuleNode(rule_spec.TOKEN_REF().toString());
                if (!graph.contains_node_with_identifier(rule_spec.TOKEN_REF().toString())){
                    graph.add_node(rule_node);
                    generator_rules.put(rule_node, rule_spec.lexerRuleBlock());
                }
                else {
                    duplicate_rules.add(rule_node.get_identifier());
                }
            }
        }

        if (duplicate_rules.size()>0){
            Utils.panic("GrammarGraphBuilder::build_rules : Redefinition of the following rule(s) "+duplicate_rules.toString());
        }
        
        // TODO: build a single rule
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
