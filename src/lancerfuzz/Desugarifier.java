package lancerfuzz;
// import java.util.Scanner;
// import java.util.List;
// import java.util.ArrayList;
// import java.io.FileOutputStream;
// import java.util.regex.*;
// This file is a makeshift transpiler to convert the new version of LancerSpec to the old, ANTLR compatible version
// it does so by simple regex matching and rewriting, without involving more complicates parsers 
// therefore it is not the most robust implementation
// but given that it is a makeshift implementation and 
// the possibility of seeing those patterns used for other purposes in the grammar file is rather low
// I believe this would not cause troubles in practice
public class Desugarifier {
    // public static List<String> desugar(List<String> grammar_file_names){
    //     List<String> files = new ArrayList<>();
    //     for (String file_name : grammar_file_names){
    //         Scanner sc = new Scanner(new File(file_name));
    //         files.add(sc.useDelimiter("\\Z").next());
    //         sc.close();
    //     }
    //     return write_back(process(files), grammar_file_names);
    // }

    // public static String process_expected_errors(String file){
    //     Pattern pe = Pattern.compile("<(\"[^\"\\\\]*(?:\\\\.[^\"\\\\]*)*\")>");
        
    // } 

    // public static List<String> process(List<String> grammar_files){
    //     List<String> processed_files = new ArrayList<>();
    //     for (String file : grammar_files){

    //     }
    //     return processed_files;
    // }

    // public static List<String> write_back(List<String> grammar_files, List<String> file_names){
    //     List<String> processed_file_names = new ArrayList<>();
    //     if (grammar_files.size()!=file_names.size()){
    //         Utils.panic("Desugarifier::write_back : file content count does not match file name count");
    //     }
    //     for (int i=0; i<grammar_files.size(); i++){
    //         String original = file_names.get(i);
    //         String new_name = original.substring(0, original.length()-3)+"_diet.g4";
    //         FileOutputStream os = new FileOutputStream(new_name);
    //         os.write(grammar_files.get(i).getBytes());
    //         os.close();
    //         processed_file_names.add(new_name);
    //     }
    //     return processed_file_names;
    // }
}
