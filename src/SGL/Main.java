package SGL;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.JCommander.Builder;


public class Main {

    

    public static void main(String[] args){

        Options options=new Options();
        Builder commandBuilder = JCommander.newBuilder().addObject(options);
        JCommander jc = commandBuilder.programName("SGL").build();
        jc.parse(args);
        
        if (jc.getParameters().isEmpty()) {
            Utils.oops("Main::main : parameters needed for running SGL, see usage below");
            jc.usage();
            return;
        }

        if (options.help){
            jc.usage();
            return;
        }
        
        if (options.grammarRules.size()==0){
            Utils.panic("Main::main : need one or more grammar files for parser rules");
        }

        if (options.config.equals("")){
            Utils.panic("Main::main : need one configuration file");
        }

        Processor p = new Processor();
        p.generate_fuzzer(options);
    }
}
