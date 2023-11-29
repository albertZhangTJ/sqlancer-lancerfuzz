package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;

import dsqlancer.Utils;

public class CharSet {
    // each of these the Lists represents the printable characters in the corresponding encoding set
    // The length of these lists are guaranteed to be even
    // For each pair of numbers in the list
    // the first number is the beginning of a printable section (inclusive)
    // the second number is the end of that section (not inclusive)
    // index 0 is ascii_characters, 1 is ascii_letters, 2 is unicode characters
    private static List<List<Integer>> char_sets;

    private static boolean is_initialized = false;

    private static void initialize(){
        List<Integer> ascii_letters = new ArrayList<>();
        ascii_letters.add((int)'A');
        ascii_letters.add(((int)'Z')+1);
        ascii_letters.add((int)'a');
        ascii_letters.add(((int)'z')+1);

        List<Integer> ascii_characters = AstUtils.printable_ranges(0, 0x80);

        List<Integer> unicode_characters = AstUtils.printable_ranges(0, 65536);

        char_sets = new ArrayList<>();
        char_sets.add(ascii_characters);
        char_sets.add(ascii_letters);
        char_sets.add(unicode_characters);
        is_initialized = true;
    }

    public static int get_random_character_from_set(List<Integer> set){
        if (set.size()==0){
            Utils.panic("CharSet::get_random_character_from_set : Cannot handle empty set");
        }
        if (set.size()%2!=0){
            Utils.panic("CharSet::get_random_character_from_set : size of the set must be even, actual size: "+set.size());
        }

        //randomly select a range
        int ranges = set.size()/2;
        int range = (int)(Math.random()*ranges);
        int start = set.get(range*2);
        int end = set.get(range*2+1);

        //return a random number from that range
        return (int)(Math.random()*(end-start)+start);
    }

    public static List<Integer> get_ascii_letters(){
        if (!is_initialized){
            initialize();
        }
        return Utils.copy_list(char_sets.get(1));
    }

    public static List<Integer> get_ascii_characters(){
        if (!is_initialized){
            initialize();
        }
        return Utils.copy_list(char_sets.get(0));
    }

    public static List<Integer> get_unicode_characters(){
        if (!is_initialized){
            initialize();
        }
        return Utils.copy_list(char_sets.get(2));
    }

    public static List<Integer> get_encoding_characters(int encoding){
        if (encoding<0 || encoding>=char_sets.size()){
            Utils.panic("CharSet::get_encoding_characters : invalid encoding "+encoding);
        }
        if (!is_initialized){
            initialize();
        }
        return Utils.copy_list(char_sets.get(encoding));
    }

    public static int get_random_character_from_encoding(int encoding){
        if (encoding<0 || encoding>=char_sets.size()){
            Utils.panic("CharSet::get_random_character_from_encoding : invalid encoding "+encoding);
        }
        return get_random_character_from_set(get_encoding_characters(encoding));
    }

    public static boolean is_in_charset(List<Integer> charset, int val){
         for (int i=0; i<charset.size()/2; i++){
            if (charset.get(i*2)<=val && charset.get(i*2+1)>val){
                return true;
            }
         }
         return false;
    }

    public static List<Integer> effective_charset(List<Integer> ranges, List<Integer> base_charset, boolean exclusion){
        //flip the ranges list using unicode scope
        if (exclusion){
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

    public static int register_custom_charset(List<Integer> ranges, boolean exclusion, int base_charset_index){
        if (!is_initialized){
            initialize();
        }
        char_sets.add(effective_charset(ranges, get_encoding_characters(base_charset_index), exclusion));
        return char_sets.size()-1;
    }
}
