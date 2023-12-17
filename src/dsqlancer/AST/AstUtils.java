package dsqlancer.AST;

import java.util.List;
import java.util.ArrayList;

import dsqlancer.Utils;

public class AstUtils {

    public static final String EERR_DECL = "E_ERR(";
    public static final int EERR_MIN_LENGTH = 10; // E_ERR("");
    public static final String WGHT_DECL = "BRANCH_W(";
    public static final int WGHT_MIN_LENGTH = 11;  // BRANCH_W();
    public static final String RPLM_DECL = "RP_LIMIT(";
    public static final int RPLM_MIN_LENGTH = 11;  // RP_LIMIT();
    public static final String RPID_DECL = "RP_ID(";
    public static final int RPID_MIN_LENGTH = 10;  // RP_LIMIT();


    // Implementation acquired from https://stackoverflow.com/q/220547
    // with modifications
    public static boolean is_printable(char c){
        Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
        return c==0 || //Allows for generating null bytes
                "\r\t\n".contains(""+c) || 
                (
                    (!Character.isISOControl(c)) &&
                    block != null &&
                    block != Character.UnicodeBlock.SPECIALS
                );
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

    // This is not actually parsing but simply a string matching
    // The result list of strings start with the stripped src with all the declaration removed
    // then starting from index 1 the content of declared expected errors
    public static List<String> get_decl_from_action(String src, boolean is_eerr, boolean is_wght, boolean is_rplm, boolean is_rpid){
        int MIN_LENGTH = is_eerr ? EERR_MIN_LENGTH :  is_wght ? WGHT_MIN_LENGTH :  is_rplm ? RPLM_MIN_LENGTH : RPID_MIN_LENGTH;
        String DECL = is_eerr ? EERR_DECL : is_wght ? WGHT_DECL : is_rplm ? RPLM_DECL : RPID_DECL;
        List<String> ans = new ArrayList<>();
        int start_index = 0;
        // An empty expected error declaration E_ERR(""); is 10 chars long
        // Anything shorter than that won't be valid
        if (src==null || src.length()<MIN_LENGTH){
            ans.add(src);
            return ans;
        }
        String stripped_src = "";
        while (start_index<src.length()){
            int idx = src.indexOf(DECL, start_index);
            if (idx==-1){
                stripped_src = stripped_src + src.substring(start_index);
                break;
            }
            stripped_src = stripped_src + src.substring(start_index, idx);
            int stk = 0; // the left bracket at DECL will set this to 0
            String content = "";
            for (int i=idx; i<src.length(); i++){
                if (src.charAt(i)=='('){
                    stk++;
                }
                else if (src.charAt(i)==')'){
                    stk--;
                    if (stk==0){
                        content = src.substring(idx+DECL.length(), i);
                        start_index = src.indexOf(";", i)+1;
                        // if the ending ; is not found, start_index will be set to -1
                        if (start_index==0){
                            Utils.panic("AstUtils::get_decl_from_action : Declaration is not ended with ;\nsrc:\n"+src);
                        }
                        content = content.strip();
                        if (is_eerr || is_rpid){
                            if (content.charAt(0)!='\"' || content.charAt(content.length()-1)!='\"'){
                                Utils.panic("AstUtils::get_decl_from_action : Declaration content not wrapped with \"\"\nsrc:\n"+src);
                            }
                            content=content.substring(1, content.length()-1);
                        }
                        ans.add(content);
                        break;
                    }
                }
                else if (i==src.length()-1){
                    Utils.panic("AstUtils::get_decl_from_action : No matching right bracket found for declaration\nsrc:\n"+src);
                }
            }

        }
        if (is_wght && ans.size()>1){
            Utils.panic("AstUtils::get_decl_from_action : Each action block can only have one weight declaration\nsrc:\n"+src);
        }
        if (is_rplm && ans.size()>1){
            Utils.panic("AstUtils::get_decl_from_action : Each action block can only have one RP_LIMIT declaration\nsrc:\n"+src);
        }
        if (is_rpid && ans.size()>1){
            Utils.panic("AstUtils::get_decl_from_action : Each action block can only have one RP_ID declaration\nsrc:\n"+src);
        }
        ans.add(0, stripped_src);
        return ans;
    }

    public static List<String> get_expected_errors(String src){
        return get_decl_from_action(src, true, false, false, false);
    }

    public static List<String> get_weight_decl(String src){
        return get_decl_from_action(src, false, true, false, false);
    }

    public static List<String> get_rep_limit_decl(String src){
        return get_decl_from_action(src, false, false, true, false);
    }

    public static List<String> get_rep_id_decl(String src){
        return get_decl_from_action(src, false, false, false, true);
    }
}
