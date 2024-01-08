package dsqlancer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Collection of static helper methods
public class Utils {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    // in-place merge list2 into list1, ignore duplicates
    public static <T> void in_place_merge(List<T> list1, List<T> list2){
        for (T obj: list2){
            if (!list1.contains(obj)){
                list1.add(obj);
            }
        }
    }

    // Compares whether two objects are equals
    // returns true if both are null or obj1.equals(obj2)
    public static boolean null_safe_equals(Object obj1, Object obj2){
        return (obj1==null && obj2==null) || (obj1.equals(obj2));
    }

    // Print error message and shutdown
    // Also print the the stack trace so tof help debugging
    public static void panic(String message){
        System.out.println("["+ANSI_RED+"ERROR"+ANSI_RESET+"] "+message);
        Exception e = new Exception();
        e.printStackTrace();
        System.exit(-1);
    }

    // Print warning message and move on
    public static void oops(String message){
        System.out.println("["+ANSI_YELLOW+"WARNING"+ANSI_RESET+"] "+message);
    }

    public static void log(String message){
        System.out.println("["+ANSI_BLUE+"LOG"+ANSI_RESET+"] "+message);
    }

    public static void log_stage(String message){
        System.out.println("["+ANSI_GREEN+"COMPLETED"+ANSI_RESET+"] "+message);
    }

    public static <T> List<T> copy_list(List<T> ori){
        if (ori==null){
            return null;
        }
        List<T> res = new ArrayList<>();
        for (T item: ori){
            res.add(item);
        }
        return res;
    }

    public static <T, U> HashMap<T, U> copy_map(HashMap<T, U> ori){
        if (ori==null){
            return null;
        }
        HashMap<T, U> res = new HashMap<>();
        for (HashMap.Entry<T, U> entry: ori.entrySet()){
            res.put(entry.getKey(), entry.getValue());
        }
        return res;
    }
}
