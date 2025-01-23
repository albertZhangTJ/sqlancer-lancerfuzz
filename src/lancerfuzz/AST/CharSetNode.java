package lancerfuzz.AST;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import lancerfuzz.Utils;
import lancerfuzz.parser.SGLParser.LexerAtomContext;

public class CharSetNode extends Node {
    // each of these the Lists represents the printable characters in the corresponding encoding set
    // The length of these lists are guaranteed to be even
    // For each pair of numbers in the list
    // the first number is the beginning of a printable section (inclusive)
    // the second number is the end of that section (not inclusive)
    // index 0 is ascii_characters, 1 is ascii_letters, 2 is unicode characters
    private List<Integer> char_set;

    private static List<Integer> base_charset;

    public CharSetNode(List<Integer> char_set, boolean is_negated){
        this.char_set = effective_charset(char_set, is_negated);
    }

    public static CharSetNode build(GrammarGraph graph, LexerAtomContext atom){
        //if not initialized from setting, initialize with UTF8
        if (base_charset==null || base_charset.size()==0){
            initialize(1);
        }
        if (atom.characterRange()!=null){
            //checking for 3 since the two single quotes are also counted
            //edit: we also want to support unicode here, might be longer than 3
            // if (atom.characterRange().STRING_LITERAL().get(0).getText().length()!=3 || atom.characterRange().STRING_LITERAL().get(1).getText().length()!=3){
            //     Utils.panic("CharSetNode::build : for character range, expecting single character for beginning and end, getting "+atom.characterRange().getText());
            // }
            List<Integer> char_set = new ArrayList<>();
            //the 
            char_set.add(process_lexer_char(atom.characterRange().STRING_LITERAL().get(0).getText(), 1).get(0));
            char_set.add(process_lexer_char(atom.characterRange().STRING_LITERAL().get(1).getText(), 1).get(0));
            boolean is_negated = false;
            if (atom.NOT()!=null){
                is_negated = true;
            }
            CharSetNode node = new CharSetNode(char_set, is_negated);
            graph.add_node(node);
            return node;
        }
        //terminal is removed as it can & should be processed by the expression branch (as a variable)
        //not by the lexerAtom (charset) branch
        if (atom.charSet()!=null){
            List<Integer> char_set = lexer_charset_interval(atom.charSet().charSetContent().getText());
            boolean is_negated = false;
            if (atom.NOT()!=null){
                is_negated = true;
            }
            CharSetNode node = new CharSetNode(char_set, is_negated);
            graph.add_node(node);
            return node;
        }
        Utils.panic("CharSetNode::build : internal error");
        return null;
    }

    // set the base charset of the entire fuzzer 0 for ascii, 1 for UTF8
    private static void initialize(int base_charset_index){
        if (base_charset_index==0){
            base_charset = AstUtils.printable_ranges(0, 0x80);
        }
        else {
            base_charset = AstUtils.printable_ranges(0, 65536);
        }
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
                Utils.panic("CharSetNode::process_lexer_char : Escape character must have at least two characters");
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
                        Utils.panic("CharSetNode::process_lexer_char : Missing right bracket for unicode value");
                    }
                    if (hex_end_offset==hex_start_offset){
                        Utils.panic("CharSetNode::process_lexer_char : Missing code point for unicode value");
                    }
                    end_offset = hex_end_offset + 1;
                }
                //uXXXX
                else {
                    hex_start_offset = offset;
                    hex_end_offset = hex_start_offset+4;
                    end_offset = hex_end_offset;
                    if (hex_end_offset>s.length()){
                        Utils.panic("CharSetNode::process_lexer_char : Unbracketed unicode escape must be in the form of \\uXXXX");
                    }
                }
                try {
                    int codepoint = Integer.valueOf(s.substring(hex_start_offset, hex_end_offset), 16);
                    //TODO
                    //ignoring the multi-character unicode codepoints for now
                    if (codepoint<0 || codepoint>0xFFFF){
                        Utils.panic("CharSetNode::process_lexer_char : invalid or unsupported unicode hex value "+codepoint);
                    }
                    ans.add(codepoint);
                    ans.add(end_offset); 
                }
                catch (Exception e){
                    Utils.panic("CharSetNode::process_lexer_char : invalid hex value\n"+e.toString());
                }
                return ans;
            }
    
            if (escaped=='p' || escaped=='P'){
                Utils.panic("CharSetNode::process_lexer_char : Unicode properties is not supported");
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
            Utils.panic("CharSetNode::process_lexer_char : unsupported escape character");
            return ans; //placeholder
        }
    
        public static List<Integer> lexer_charset_interval(String s){
            if (!(s.length()>0)){
                Utils.panic("CharSetNode::lexer_charset_interval : Charset cannot be empty");
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

    public static boolean is_in_charset(List<Integer> charset, int val){
         for (int i=0; i<charset.size()/2; i++){
            if (charset.get(i*2)<=val && charset.get(i*2+1)>val){
                return true;
            }
         }
         return false;
    }

    public static List<Integer> effective_charset(List<Integer> ranges, boolean is_negated){
        //flip the ranges list using unicode scope
        if (is_negated){
            ranges.add(0,0);
            ranges.add(65536);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i=0; i<ranges.size()/2; i++){
            int start=ranges.get(i*2);
            int end=ranges.get(i*2+1);
            if (start==end){
                continue; //the flipping operation might introduce ranges like 0-0 or 65536-65536
            }
            boolean con=false;
            int interval_start = -1;
            for (int j=start; j<end; j++){
                if (!con && is_in_charset(base_charset, j)){
                    con=true;
                    interval_start = j;
                }
                else if (con && !is_in_charset(base_charset, j)){
                    ans.add(interval_start);
                    ans.add(j);
                    con = false;
                    interval_start = -1;
                }
            }
            if (con){
                ans.add(interval_start);
                ans.add(end);
            }
        }
        return ans;
    }

    @Override
    public String render(List<String> function_list, String padding, boolean print){
        String func = "    public static Buffer node"+this.get_id()+"(Context ctx) throws Exception{\n" +
        "        List<Integer> s = new ArrayList<>();\n";
        for(Integer i : this.char_set){
            func = func + "        s.add("+i.intValue()+");\n";
        }
        func = func + "        return new Buffer(\"\"+(char)(CharSet.get_random_character_from_set(s)));\n";
        func = func + "    }\n";
        function_list.add(func);
        String handle = "node"+this.get_id()+"(ctx)";
        if (print){
            handle = padding + "buf.add(" + handle + ");\n";
        }
        return handle;
    }

}
