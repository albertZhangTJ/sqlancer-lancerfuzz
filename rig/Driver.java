public class Driver{
    public static void main(String[] args){
        try{
            Fuzzer.init(new SQLConnection(null));
            while (true){
                String next = Fuzzer.fuzz_next();
                if (next==null){
                    break;
                }
                System.out.println(next);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}