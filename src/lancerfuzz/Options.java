package lancerfuzz;


import java.util.List;
import java.util.ArrayList;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=", commandDescription = "Options applicable to all DBMS")
public class Options {

    public static final Options DEFAULT_OPTIONS = new Options();

    @Parameter(names = { "--help", "-h" }, description = "Display help message", help = true)
    public boolean help;


    @Parameter(names = {"--encoding", "-e"}, description = "Encoding of generated characters, 0 for any ASCII characters, 1 for any ASCII letters, 2 for any Unicode characters")
    public int encoding = 0;

    @Parameter(names = {"--grammar", "-g"}, description= "ANTLR grammar files", variableArity = true)
    public List<String> grammarRules = new ArrayList<>();

    // @Deprecated
    // @Parameter(names = {"--parser_rule", "-p"}, description="Path to parser grammar file", variableArity=true)
    // public List<String> parserRules = new ArrayList<>();

    // @Deprecated
    // @Parameter(names = {"--lexer_rule", "-l"}, description="Path to lexer grammar files", variableArity=true)
    // public List<String> lexerRules = new ArrayList<>();

    @Parameter(names = {"--output", "-o"}, description="Path to output folder", arity=1)
    public String outputLocation = "./";
    
    @Parameter(names = {"--config", "-c"}, description="Path to JSON file containing the configuration info", arity=1)
    public String config = "";;

    @Parameter(names = {"--cwd"}, description="Library directory, default to be current working directory", arity=1)
    public String cwd = System.getProperty("user.dir");

    // @Parameter(names = {"--default_rule", "-d"}, description = "the root grammar rule", arity=1)
    // public String defaultRule = null;
}
