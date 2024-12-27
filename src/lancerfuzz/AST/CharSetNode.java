package lancerfuzz.AST;

import java.util.List;
import java.util.ArrayList;

import lancerfuzz.Utils;

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
        this.char_set = effective_charset(char_set, is_negated)
    }

    // set the base charset of the entire fuzzer 0 for ascii, 1 for UTF8
    private static void initialize(int base_charset_index){
        if (base_charset_index==0){
            List<Integer> ascii_characters = AstUtils.printable_ranges(0, 0x80);
        }
        else {
            List<Integer> unicode_characters = AstUtils.printable_ranges(0, 65536);
        }
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
        String func = "    public static Buffer node"+this.get_id()+"(ctx){\n" +
        "        List<Integer> s = new ArrayList<>();\n";
        for(Integer i : this.char_set){
            func = func + "        s.add("+i.intValue()+");\n";
        }
        func = func + "        return new Buffer(""+(char)(CharSet.get_random_character_from_set(s)));\n";
        func = func + "    }";
        function_list.add(func);
        return padding + "node"+this.get_id()+"(ctx)";
    }

}
