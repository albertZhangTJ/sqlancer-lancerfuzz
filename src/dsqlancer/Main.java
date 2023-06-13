package dsqlancer;

// import java.io.File;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.io.Writer;
// import java.nio.file.Files;
// import java.text.DateFormat;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.ServiceLoader;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;
// import java.util.concurrent.ScheduledExecutorService;
// import java.util.concurrent.TimeUnit;
// import java.util.concurrent.atomic.AtomicBoolean;
// import java.util.concurrent.atomic.AtomicLong;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.JCommander.Builder;



public class Main {
    public static void main(String[] args){

        Options options=new Options();
        Builder commandBuilder = JCommander.newBuilder().addObject(options);
        JCommander jc = commandBuilder.programName("DSQLancer").build();
        jc.parse(args);
        if (jc.getParsedCommand() == null || options.help) {
            jc.usage();
            return;
        }

        if (options.parserRules.size()==0){

        }

    }
}
