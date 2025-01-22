public class Driver{
    public static void main(String[] args){
        Fuzzer.init();
        try{
            System.out.println(Fuzzer.fuzz(null, "test"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}