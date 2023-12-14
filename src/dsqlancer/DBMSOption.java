package dsqlancer;

public class DBMSOption {
    private String name;
    private String defau;

    public DBMSOption(String name, String defau){
        this.name = name;
        this.defau = defau;
    }

    public String get_name(){
        return this.name;
    }

    public String get_default(){
        return this.defau;
    }
}
