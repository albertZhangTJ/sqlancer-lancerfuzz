package dsqlancer;

public class DBMSOption {
    private String name;
    private String type;
    private String defau;
    private Boolean is_connection_param;

    public DBMSOption(String name, String type, String defau, Boolean is_connection_param){
        this.name = name;
        this.type = type;
        this.defau = defau;
        this.is_connection_param = is_connection_param;
    }

    public String get_name(){
        return this.name;
    }

    public String get_type(){
        return this.type;
    }

    public String get_default(){
        return this.defau;
    }

    public Boolean is_dbms_param(){
        return this.is_connection_param;
    }
}
