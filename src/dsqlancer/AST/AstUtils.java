package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;

import dsqlancer.Utils;

public class AstUtils {

    // Implementation acquired from https://stackoverflow.com/q/220547
    // with modifications
    public static boolean is_printable(char c){
        Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
        return (!Character.isISOControl(c)) &&
                    block != null &&
                    block != Character.UnicodeBlock.SPECIALS;
    }

    // return the ranges of printable characters (represented as Integers)
    // between a lower bound and an upper bound (upper bound not inclusive)
    // The length of the result List is guaranteed to be even
    // The values stored at even indices represents the beginning of a range (inclusive)
    // The value stored after that represents the end of that range (not inclusive)
    public static List<Integer> printable_ranges(int lower_bound, int upper_bound){
        if (lower_bound<0 || upper_bound<0 || upper_bound<lower_bound){
            Utils.panic("AstUtils::printable_ranges : invalid range "+lower_bound+", "+upper_bound);
        }
        List<Integer> ranges = new ArrayList<Integer>();
        int start = -1;
        for (int i=lower_bound; i<upper_bound; i++){
            if (start==-1 && is_printable((char)i)){
                start = i;
            }
            else if (start!=-1 && !is_printable((char)i)){
                ranges.add(start);
                ranges.add(i);
                start = -1;
            }
        }

        if (start!=-1){
            ranges.add(start);
            ranges.add(upper_bound);
        }

        return ranges;
    }
}
