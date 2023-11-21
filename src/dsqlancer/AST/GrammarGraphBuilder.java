package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.google.protobuf.TextFormat.Parser;
import com.kenai.jffi.Array;

import org.antlr.v4.runtime.ParserRuleContext;

import dsqlancer.Options;
import dsqlancer.Utils;
import dsqlancer.ANTLR.ANTLRv4Parser;
import dsqlancer.ANTLR.ANTLRv4Parser.*;

// TODO: remove suppress after complete
@SuppressWarnings("unused")
public class GrammarGraphBuilder {

    

    //Since Java does not have a getattr, the behavior of this function is slightly different with the 
    //corresponding function in Grammarinator
    public static String find_condition(FlexibleParserRuleContext node, Options options){
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

    public static List<Integer> character_range_interval(SetElementContext node){
        String start = node.characterRange().STRING_LITERAL(0).toString();
        start = start.substring(1, start.length()-1);
        String end = node.characterRange().STRING_LITERAL(1).toString();
        end = end.substring(1, start.length()-1);
        List<Integer> start_val = process_lexer_char(start, 0);
        List<Integer> end_val = process_lexer_char(end, 0);
        if (start_val.get(1)<start.length() || end_val.get(1)<end.length()){
            Utils.panic("GrammarGraphBuilder::character_range_interval : only single character are allowed in character intervals");
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(start_val.get(0));
        ans.add(end_val.get(0)+1);
        return ans;
    }

    public static List<Integer> character_range_interval(LexerAtomContext node){
        String start = node.characterRange().STRING_LITERAL(0).toString();
        start = start.substring(1, start.length()-1);
        String end = node.characterRange().STRING_LITERAL(1).toString();
        end = end.substring(1, start.length()-1);
        List<Integer> start_val = process_lexer_char(start, 0);
        List<Integer> end_val = process_lexer_char(end, 0);
        if (start_val.get(1)<start.length() || end_val.get(1)<end.length()){
            Utils.panic("GrammarGraphBuilder::character_range_interval : only single character are allowed in character intervals");
        }
        List<Integer> ans = new ArrayList<>();
        ans.add(start_val.get(0));
        ans.add(end_val.get(0)+1);
        return ans;
    }

    public static List<Integer> process_lexer_char(String s, int offset){
        List<Integer> ans = new ArrayList<>();
        //not an escape character
        if (s.charAt(offset)!='\\'){
            ans.add((int)(s.charAt(offset)));
            ans.add(offset+1);
            return ans;
        }

        if (offset+2 > s.length()){
            Utils.panic("GrammarGraphBuilder::process_lexer_char : Escape character must have at least two characters");
        }

        char escaped = s.charAt(offset+1);
        offset += 2;

        //unicode
        if (escaped=='u'){
            int hex_start_offset = -1;
            int hex_end_offset = -1;
            int end_offset = -1;
            //u{....}
            if (s.charAt(offset)=='{'){
                hex_start_offset = offset+1;
                hex_end_offset = s.substring(hex_start_offset).indexOf('}')+hex_start_offset;
                if (hex_end_offset==-1){
                    Utils.panic("GrammarGraphBuilder::process_lexer_char : Missing right bracket for unicode value");
                }
                if (hex_end_offset==hex_start_offset){
                    Utils.panic("GrammarGraphBuilder::process_lexer_char : Missing code point for unicode value");
                }
                end_offset = hex_end_offset + 1;
            }
            //uXXXX
            else {
                hex_start_offset = offset;
                hex_end_offset = hex_start_offset+4;
                end_offset = hex_end_offset;
                if (hex_end_offset>s.length()){
                    Utils.panic("GrammarGraphBuilder::process_lexer_char : Unbracketed unicode escape must be in the form of \\uXXXX");
                }
            }
            try {
                int codepoint = Integer.valueOf(s.substring(hex_start_offset, hex_end_offset), 16);
                //TODO
                //ignoring the multi-character unicode codepoints for now
                if (codepoint<0 || codepoint>0xFFFF){
                    Utils.panic("GrammarGraphBuilder::process_lexer_char : invalid or unsupported unicode hex value "+codepoint);
                }
                ans.add(codepoint);
                ans.add(end_offset); 
            }
            catch (Exception e){
                Utils.panic("GrammarGraphBuilder::process_lexer_char : invalid hex value\n"+e.toString());
            }
            return ans;
        }

        if (escaped=='p' || escaped=='P'){
            Utils.panic("GrammarGraphBuilder::process_lexer_char : Unicode properties is not supported");
        }

        HashMap<Character, Character> escapes = new HashMap<>();
        escapes.put('n', '\n');
        escapes.put('r', '\r');
        escapes.put('b', '\b');
        escapes.put('t', '\t');
        escapes.put('f', '\f');
        escapes.put('\\', '\\');
        escapes.put('-', '-');
        escapes.put(']', ']');
        escapes.put('\'', '\'');

        if (escapes.containsKey(Character.valueOf(escaped))){
            ans.add((int)(escapes.get(escaped).charValue()));
            ans.add(offset);
            return ans;
        }
        Utils.panic("GrammarGraphBuilder::process_lexer_char : unsupported escape character");
        return ans; //placeholder
    }

    public static List<Integer> lexer_charset_interval(String s){
        if (!(s.length()>0)){
            Utils.panic("GrammarGraphBuilder::lexer_charset_interval : Charset cannot be empty");
        }
        List<Integer> ranges = new ArrayList<>();
        int offset = 0;
        while (offset<s.length()){
            
            boolean in_range = (s.charAt(offset)=='-' && offset!=0 && offset!=s.length()-1); // x-y format, covering all intermediate values
            if (in_range){
                offset++;
            }
            List<Integer> vals = process_lexer_char(s, offset);
            offset=vals.get(1);
            if (in_range){
                ranges.set(ranges.size()-1, vals.get(0)+1);
            }
            else {
                ranges.add(vals.get(0));
                ranges.add(vals.get(0)+1);
            }
        }
        return ranges;
    }



    public static List<Integer> chars_from_set(GrammarGraph graph, SetElementContext node){
        if (node.characterRange()!=null){
            return character_range_interval(node);
        }
        if (node.LEXER_CHAR_SET()!=null){
            return lexer_charset_interval(node.LEXER_CHAR_SET().toString().substring(1, node.LEXER_CHAR_SET().toString().length()-1));
        }
        if (node.STRING_LITERAL()!=null){
            String characters = node.STRING_LITERAL().toString();
            characters = characters.substring(1, characters.length()-1);
            List<Integer> char_vals = process_lexer_char(characters, 0);
            if (char_vals.get(1)<characters.length()){
                Utils.panic("GrammarGraphBuilder::chars_from_set : Zero or multi-character literals are not allowed in lexer sets");
            }
            char_vals.set(1, char_vals.get(0)+1); //simply reusing the list, the meaning of the list is NOT preserved
            return char_vals;
        }
        if (node.TOKEN_REF()!=null){
            String src = node.TOKEN_REF().toString();
            Node src_node = graph.get_vertices().get(graph.get_node_id_with_identifier(src));
            //TODO
            //Not entirely sure whether it should be UnlexerRuleNode or RuleNode here
            //but I think UnparserRuleNode (or RuleNode in general) should have start_ranges
            if (src_node instanceof UnlexerRuleNode && ((UnlexerRuleNode)src_node).get_start_ranges()!=null){
                return ((UnlexerRuleNode)src_node).get_start_ranges();
            }
            Utils.panic("GrammarGraphBuilder::chars_from_set : Source node "+src+" has no start_ranges");
        }
        return new ArrayList<>();
    }

    public static HashMap<String, String> arg_action_block(FlexibleParserRuleContext node){

        HashMap<String, String> args = new HashMap<>();
        if (node==null){
            return args;
        }
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

    public static String unescape_string(String s){
        String ans = "";
        int offset = 0;
        while (offset<s.length()){
            List<Integer> vals=process_lexer_char(s, offset);
            ans = ans + (char)(vals.get(0).intValue());
            offset = vals.get(1);
        }
        return ans;
    }

    // I personally feels that this implementation is not quite elegant
    // so many instanceof and type castings
    // But an alternative would require modification to the ANTLR generated code
    // And the current method is how grammarinator did this
    //indices == [alt_idx, quant_idx, chr_idx]
    public static void build_expr(GrammarGraph graph, RuleNode rule, FlexibleParserRuleContext node, 
                                    int parent_id, List<Integer> indices, Options options){
        if (node instanceof ANTLRv4Parser.ParserRuleSpecContext){
            rule.set_args(arg_action_block((ParserRuleSpecContext)node));
            rule.set_locals(arg_action_block(((ParserRuleSpecContext)node).localsSpec()));
            rule.set_returns(arg_action_block(((ParserRuleSpecContext)node).ruleReturns()));
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
        else if (node instanceof ANTLRv4Parser.LabeledAltContext){
            if (((LabeledAltContext)node).identifier()==null){
                build_expr(graph, rule, ((LabeledAltContext)node).alternative().get(0), parent_id, indices, options);
                return;
            }
            String label = null;
            if (((LabeledAltContext)node).identifier().TOKEN_REF()!=null){
                label = ((LabeledAltContext)node).identifier().TOKEN_REF().toString();
            }
            else if (((LabeledAltContext)node).identifier().RULE_REF()!=null){
                label = ((LabeledAltContext)node).identifier().RULE_REF().toString();
            }
            RuleNode rule_node = new UnparserRuleNode(rule.get_name(), label);
            graph.add_edge(parent_id, graph.add_node(rule_node), null);
            build_rule(graph, rule_node,  ((LabeledAltContext)node).alternative().get(0), options);
        }
        else if (node instanceof ANTLRv4Parser.AlternativeContext || node instanceof ANTLRv4Parser.LexerAltContext){
            if (node instanceof ANTLRv4Parser.AlternativeContext){
                for (ElementContext child : ((ANTLRv4Parser.AlternativeContext)node).element()){
                    build_expr(graph, rule, child, parent_id, indices, options);
                }
            }
            else {
                for (LexerElementContext child : ((ANTLRv4Parser.LexerAltContext)node).lexerElements().lexerElement()){
                    build_expr(graph, rule, child, parent_id, indices, options);
                }
            }
            if (graph.get_vertices().get(parent_id).get_outward_edges().size()==0){
                graph.add_edge(parent_id, graph.get_lambda_id(), null);
            }
        }
        
        else if (node instanceof ANTLRv4Parser.ElementContext || node instanceof ANTLRv4Parser.LexerElementContext){
            if (node.actionBlock()!=null){
                //System.out.println("Action found");
                //System.out.println("options.ignore_actions: "+options.ignore_actions);
                if ((node.QUESTION()!=null && node.QUESTION().size()>0 && node.QUESTION().get(0)!=null)){
                    return;
                }
                String action_content = "";
                for (TerminalNode child : node.actionBlock().ACTION_CONTENT()){
                    action_content = action_content + child.toString();
                }
                graph.add_edge(parent_id, graph.add_node(new ActionNode(action_content)), null);
                return;
            }
            EbnfSuffixContext suffix = null;
            if (node.ebnfSuffix()!=null){
                suffix = node.ebnfSuffix();
            }
            else if (node.ebnf()!=null && node.ebnf().blockSuffix()!=null){
                suffix = node.ebnf().blockSuffix().ebnfSuffix();
            }
            if (suffix==null){
                build_expr(graph, rule, (FlexibleParserRuleContext)(node.children.get(0)), parent_id, indices, options);
                return;
            }
            String suf = suffix.children.get(0).toString();
            int min=-1;
            int max=-1;
            if (suf.equals("?")){
                int quant_id = graph.add_node(new QuantifierNode(rule.get_id(), indices.get(1), 0, 1));
                indices.set(1, indices.get(1)+1);
                graph.add_edge(parent_id, quant_id, null);
                build_expr(graph, rule, (FlexibleParserRuleContext)(node.children.get(0)), quant_id, indices, options);
            }
            else if (suf.equals("*")){
                int quant_id = graph.add_node(new QuantifierNode(rule.get_id(), indices.get(1), 0, 8)); 
                indices.set(1, indices.get(1)+1);
                graph.add_edge(parent_id, quant_id, null);
                build_expr(graph, rule, (FlexibleParserRuleContext)(node.children.get(0)), quant_id, indices, options); 
            }
            else if (suf.equals("+")){
                int quant_id = graph.add_node(new QuantifierNode(rule.get_id(), indices.get(1), 1, 8));
                indices.set(1, indices.get(1)+1);
                graph.add_edge(parent_id, quant_id, null);
                build_expr(graph, rule, (FlexibleParserRuleContext)(node.children.get(0)), quant_id, indices, options);
            }
        }
        else if (node instanceof ANTLRv4Parser.LabeledElementContext){
            build_expr(graph, rule, node.atom()==null ? node.atom() : node.block() , parent_id, indices, options);
            IdentifierContext ident = ((LabeledElementContext)node).identifier();
            String name = ident.RULE_REF()==null ? ident.RULE_REF().toString() : ident.TOKEN_REF().toString();
            boolean is_list = ((LabeledElementContext)node).PLUS_ASSIGN()!=null;
            graph.add_edge(parent_id, graph.add_node(new VariableNode(name, is_list)), null);
            rule.add_label(name, String.valueOf(is_list));
        }
        else if (node instanceof ANTLRv4Parser.RulerefContext){
            int ref_id = graph.get_node_id_with_identifier(((RulerefContext)node).RULE_REF().toString());
            if (ref_id!=-1){
                graph.add_edge(parent_id, ref_id, arg_action_block(node)); 
            }
        }
        else if (node instanceof ANTLRv4Parser.LexerAtomContext || node instanceof ANTLRv4Parser.AtomContext){
            if (node.DOT().get(0)!=null){
                graph.add_edge(parent_id, graph.add_node(new CharsetNode(rule.get_id(), indices.get(2), graph.get_encoding())), null);
                indices.set(2, indices.get(2)+1);
            }
            else if (node.notSet()!=null){
                List<Integer> not_ranges = null;
                if (node.notSet().setElement()!=null){
                    not_ranges = chars_from_set(graph, node.notSet().setElement());
                }
                else {
                    not_ranges = new ArrayList<>();
                    for (SetElementContext set_element : node.notSet().blockSet().setElement()){
                        for (Integer i : chars_from_set(graph, set_element)){
                            not_ranges.add(i);
                        }
                    }
                }
                int charset_id = CharSet.register_custom_charset(not_ranges, true, graph.get_encoding());
                graph.add_edge(parent_id, graph.add_node(new CharsetNode(rule.get_id(), indices.get(2), charset_id)), null);
                indices.set(2, indices.get(2)+1);
            }
            else if (node instanceof ANTLRv4Parser.LexerAtomContext && ((LexerAtomContext)node).characterRange()!=null){
                List<Integer> vals = character_range_interval((LexerAtomContext)node);
                if (rule instanceof UnlexerRuleNode){
                    ((UnlexerRuleNode)rule).append_start_ranges(vals);
                }
                int charset_id = CharSet.register_custom_charset(vals, false, graph.get_encoding());
                graph.add_edge(parent_id, graph.add_node(new CharsetNode(rule.get_id(), indices.get(2), charset_id)), null);
                indices.set(2, indices.get(2)+1);
            }
            else if (node instanceof ANTLRv4Parser.LexerAtomContext && ((LexerAtomContext)node).LEXER_CHAR_SET()!=null){
                String s = ((LexerAtomContext)node).LEXER_CHAR_SET().toString();
                s = s.substring(1, s.length()-1);
                List<Integer> vals = lexer_charset_interval(s);
                if (rule instanceof UnlexerRuleNode){
                    ((UnlexerRuleNode)rule).append_start_ranges(vals);
                }
                //TODO didn't sort as in grammarinator
                int charset_id = CharSet.register_custom_charset(vals, false, graph.get_encoding());
                graph.add_edge(parent_id, graph.add_node(new CharsetNode(rule.get_id(), indices.get(2), charset_id)), null);
                indices.set(2, indices.get(2)+1);
            }
            for (ParseTree child : node.children){
                if (child instanceof FlexibleParserRuleContext){
                    build_expr(graph, rule, ((FlexibleParserRuleContext)child), parent_id, indices, options);
                }
            }
        }
        else if (node instanceof ANTLRv4Parser.TerminalContext){
            TerminalContext t_node = (TerminalContext)node;
            if (t_node.TOKEN_REF()!=null){
                int ref_id = graph.get_node_id_with_identifier(t_node.TOKEN_REF().toString());
                if (ref_id==-1){
                    Utils.panic("GrammarGraphBuilder::build_expr : cannot find referenced node "+t_node.TOKEN_REF().toString());
                }
                else {
                    graph.add_edge(parent_id, ref_id, null);
                }
            }
            else if (t_node.STRING_LITERAL()!=null){
                String raw = t_node.STRING_LITERAL().toString();
                raw = raw.substring(1, raw.length()-1);
                String src = unescape_string(raw);
                if (rule instanceof UnlexerRuleNode){
                    List<Integer> strt_r = new ArrayList<>();
                    strt_r.add((int)(src.charAt(0)));
                    strt_r.add((int)(src.charAt(0))+1);
                    ((UnlexerRuleNode)rule).append_start_ranges(strt_r); 
                }
                graph.add_edge(parent_id, graph.add_node(new LiteralNode(src)), null);
            }
        }
        else if (node.getChildCount()>0){
            for (ParseTree child : node.children){
                if (child instanceof FlexibleParserRuleContext){
                    build_expr(graph, rule, ((FlexibleParserRuleContext)child), parent_id, indices, options);
                }
            }
        }

    }


    public static void build_rule(GrammarGraph graph, RuleNode rule, FlexibleParserRuleContext node, Options options){
        if (rule instanceof UnlexerRuleNode){
            ((UnlexerRuleNode)rule).set_start_ranges(new ArrayList<>());
        }
        List<Integer> indices = new ArrayList<>();
        indices.add(0); //alt_idx
        indices.add(0); //quant_idx
        indices.add(0); //chr_idx
        build_expr(graph, rule, node, rule.get_id(), indices, options);
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

                if (prequel_construct.action_()!=null){
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
        LinkedHashMap<RuleNode, FlexibleParserRuleContext> generator_rules = new LinkedHashMap<>();
        List<String> duplicate_rules = new ArrayList<>();

        for (RuleSpecContext rule : node.rules().ruleSpec()){
            FlexibleParserRuleContext antlr_node = null;
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

        for (RuleNode rule_node : generator_rules.keySet()){
            build_rule(graph, rule_node, generator_rules.get(rule_node), options);
        }
        if (options.defaultRule!=null){
            graph.set_default_rule(options.defaultRule);
        }
        else if (node.grammarDecl().grammarType().PARSER()!=null || !(node.grammarDecl().grammarType().LEXER()!=null || node.grammarDecl().grammarType().LEXER()!=null)){
            graph.set_default_rule(generator_rules.entrySet().iterator().next().getKey().get_name());
        }
        // TODO: build a single rule
    }
    
    
    public static GrammarGraph build_grammar_graph(GrammarSpecContext lexer_root, GrammarSpecContext parser_root, Options options){
        GrammarGraph graph = new GrammarGraph();
        graph.set_lambda_id(graph.add_node(new LambdaNode()));
        graph.add_node(new UnlexerRuleNode("EOF"));
        if (lexer_root!=null){
            build_prerequisite(graph, lexer_root, options);
        }
        if (parser_root!=null){
            build_prerequisite(graph, parser_root, options);
        }
        if (lexer_root!=null){
            build_rules(graph, lexer_root, options);
        }
        if (parser_root!=null){
            build_rules(graph, parser_root, options);
        }
        graph.process_expected_errors();
        graph.process_weights();
        // Ignored the options for the graph, not entirely sure why grammar graph need info on options
        // Will come back to implement if later found needed
        // TODO

        return graph;
    }
}
