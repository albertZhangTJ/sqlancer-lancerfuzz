package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;

import dsqlancer.Utils;

public class CharSet {
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
}
