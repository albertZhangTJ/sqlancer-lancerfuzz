package lancerfuzz;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import lancerfuzz.AST.GrammarGraph;

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

    public static List<Stage> get_stages(JSONObject config, String default_rule){
        JSONArray stages = config.getJSONArray("stages");
        List<Stage> ans = new ArrayList<>();
        if (stages==null){
            Utils.oops("ConfigProcessor::get_stages : No stage configuration found, will generate single default rule");
            if (default_rule == null){
                Utils.panic("ConfigProcessor::get_stages : default rule is also not set, exiting");
            }
        }
        for (int i=0; i<stages.length(); i++){
            JSONObject stage = stages.getJSONObject(i);
            JSONArray rules = stage.getJSONArray("rules");
            JSONArray weights = null;
            try {
                weights = stage.getJSONArray("weights");
            }
            catch (JSONException je){
            
            }
            if (rules==null){
                Utils.panic("ConfigProcessor::get_stages : cannot find rules in stage "+i);
            }
            boolean has_valid_weights = true;
            if (weights==null){
                has_valid_weights = false;
                Utils.oops("ConfigProcessor::get_stages : No weights declaration found for stage "+stage.getString("name")+", using uniform probability");
            }

            else if (weights.length()!=rules.length()){
                has_valid_weights = false;
                Utils.oops("ConfigProcessor::get_stages : length of weights declaration found for stage "+stage.getString("name")+" does not match that of rules declaration, using uniform probability");
            }
            List<String> stmts = new ArrayList<>();
            List<Double> wl = new ArrayList<>();
            for (int j=0; j<rules.length(); j++){
                stmts.add(rules.getString(j));
                if (has_valid_weights){
                    wl.add(weights.getDouble(j));
                }
                else {
                    wl.add(1.0);
                }
            }
            ans.add(new Stage(stage.getString("name"), stmts, wl, stage.getInt("min"), stage.getInt("max")));
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
            ans.add(new DBMSOption(option.getString("name"), option.getString("default")));
        }
        return ans;
    }

    // Make sure every rule referred in the stages exists in the AST
    // Will panic when a rule is not found
    public static void sanity_check(GrammarGraph graph, List<Stage> stages){
        for (Stage stage : stages){
            for (String rule_name : stage.get_rules()){
                if (!graph.contains_node_with_identifier(rule_name)){
                    Utils.panic("ConfigProcessor::sanity_check : rule "+rule_name+" not found in the AST");
                }
            }
            if (stage.get_min()<0 || stage.get_max()<0){
                Utils.panic("ConfigProcessor::sanity_check : Minimum and maximum number of statements must e non-negative for stage "+stage.get_name());
            }
            if (stage.get_min()>stage.get_max()){
                Utils.panic("ConfigProcessor::sanity_check : Minimum shall not be bigger than maximum for stage "+stage.get_name());
            }
            if (stage.get_min()>65535 || stage.get_max()>65535){
                Utils.panic("ConfigProcessor::sanity_check : For performance consideration, min or max shall not exceed 65535 for stage "+stage.get_name());
            }
        }
    }
}
