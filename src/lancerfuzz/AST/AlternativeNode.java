package lancerfuzz.AST;

public class AlternativeNode extends Node{
    private String rule_id;
    private int alternative_index;
    private int index;
    private boolean is_var; //If the current node is referring to a STATIC_VAR declaration or a VAR declaration
    private boolean is_static; //refers to a STATIC_VAR declaration if set to true, VAR declaration otherwise
    private boolean is_member;
    private String var_id;

    private String type;

    public AlternativeNode(String rule_id, int alternative_index, int index){
        this.rule_id = rule_id;
        this.alternative_index = alternative_index;
        this.index = index;
        this.is_var = false;
        this.is_static = false;
        this.var_id = "";
    }

    public String get_rule_id(){
        return this.rule_id;
    }

    public int get_alternative_index(){
        return this.alternative_index;
    }

    public int get_index(){
        return this.index;
    }

    public void set_var_ref(boolean is_static, boolean is_member, String var_id){
        this.is_var = true;
        this.is_static = is_static;
        this.is_member = is_member;
        this.var_id = var_id;
    }

    public boolean get_is_var(){
        return this.is_var;
    }

    public boolean get_is_static(){
        return this.is_static;
    }

    public boolean get_is_member(){
        return this.is_member;
    }

    public String get_var_id(){
        return this.var_id;
    }

    public void set_type(String type){
        this.type = type;
    }

    public String get_type(){
        return this.type;
    }

    // @Override
    // public int get_min_depth(){
    //     return super.get_min_depth()-1;
    // }
}
