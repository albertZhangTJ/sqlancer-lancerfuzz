package dsqlancer;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class ConfigProcessor {
    public static JSONObject read_json_file(String file_path){
        try {
            String file_content = new String(Files.readAllBytes(Paths.get(file_path)));
            return new JSONObject(file_content);
        }
        catch (Exception e){
            Utils.panic("ConfigProcessor::read_json_file : IO Error"+e.toString());
        }
        return null;
    }

    public static List<Stage> get_stages(JSONObject config){
        JSONArray stages = config.getJSONArray("stages");
        List<Stage> ans = new ArrayList<>();
        if (stages==null){
            Utils.panic("ConfigProcessor::get_stages : cannot find stage configuration in JSON file");
        }
        for (int i=0; i<stages.length(); i++){
            JSONObject stage = stages.getJSONObject(i);
            JSONArray rules = stage.getJSONArray("rules");
            if (rules==null){
                Utils.panic("ConfigProcessor::get_stages : cannot find rules in stage "+i);
            }
            List<String> stmts = new ArrayList<>();
            for (int j=0; j<stages.length(); j++){
                stmts.add(rules.getString(j));
            }
            ans.add(new Stage(stage.getString("name"), stmts, stage.getInt("min"), stage.getInt("max")));
        }
        return ans;
    }

    public static List<DBMSOption> get_options(JSONObject config){
        JSONArray options = config.getJSONArray("options");
        List<DBMSOption> ans = new ArrayList<>();
        if (options==null){
            Utils.oops("ConfigProcessor::get_options : cannot find options configuration in JSON file, this is ok but not expected");
            return ans;
        }
        for (int i=0; i<options.length(); i++){
            JSONObject option = options.getJSONObject(i);
            ans.add(new DBMSOption(option.getString("name"), option.getString("type"), option.getString("default"), option.getBoolean("connection_parameter")));
        }
        return ans;
    }

}
