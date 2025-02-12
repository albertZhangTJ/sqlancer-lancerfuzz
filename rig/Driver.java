public class Driver{
    public static void main(String[] args){
        Fuzzer.init();
        try{
            for (int i=0; i<50; i++){
                System.out.println(Fuzzer.fuzz(null, "rule"));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}