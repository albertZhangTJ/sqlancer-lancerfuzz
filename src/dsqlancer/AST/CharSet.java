package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;

import dsqlancer.Utils;

public class CharSet {
    // each of these three Lists represents the printable characters in the corresponding encoding set
    // The length of these lists are guaranteed to be even
    // For each pair of numbers in the list
    // the first number is the beginning of a printable section (inclusive)
    // the second number is the end of that section (not inclusive)
    private static List<Integer> ascii_letters;
    private static List<Integer> ascii_characters;
    private static List<Integer> unicode_characters;

    private static boolean is_initialized = false;

    private static void initialize(){
        ascii_letters = new ArrayList<>();
        ascii_letters.add((int)'A');
        ascii_letters.add(((int)'Z')+1);
        ascii_letters.add((int)'a');
        ascii_letters.add(((int)'z')+1);

        ascii_characters = AstUtils.printable_ranges(0, 0x80);

        unicode_characters = AstUtils.printable_ranges(0,65536);

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
        return Utils.copy_list(ascii_letters);
    }

    public static List<Integer> get_ascii_characters(){
        if (!is_initialized){
            initialize();
        }
        return Utils.copy_list(ascii_characters);
    }

    public static List<Integer> get_unicode_characters(){
        if (!is_initialized){
            initialize();
        }
        return Utils.copy_list(unicode_characters);
    }

    public static List<Integer> get_encoding_characters(int encoding){
        if (encoding<0 || encoding>2){
            Utils.panic("CharSet::get_encoding_characters : invalid encoding "+encoding+". Valid options are 0 for any ASCII characters, 1 for any ASCII letters, 2 for any Unicode characters");
        }
        if (encoding==0){
            return get_ascii_characters();
        }
        else if (encoding==1){
            return get_ascii_letters();
        }
        return get_unicode_characters();
    }

    public static int get_random_character_from_encoding(int encoding){
        if (encoding<0 || encoding>2){
            Utils.panic("CharSet::get_random_character_from_encoding : invalid encoding "+encoding+". Valid options are 0 for any ASCII characters, 1 for any ASCII letters, 2 for any Unicode characters");
        }
        return get_random_character_from_set(get_encoding_characters(encoding));
    }
}
