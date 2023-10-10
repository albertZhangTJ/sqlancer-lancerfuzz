//CHARSET_NODE
//MIN_DEPTH = 1
    public String <NAME/>(int depth){
<ee/>
        List<Integer> indices = new ArrayList<>();
        <add_indices/>
        if (indices.size()==0){
            return "";
        }
        if (indices.size()%2!=0){
            System.out.println("\u001B[31mSUSPECT ERROR DURING FUZZER RENDERING, SIZE OF INDICES SHOULD BE EVEN\u001B[0m");
        }
        //select a random range
        int r = random(0, indices.size()/2);
        int beginning = indices.get(r*2);
        int end = indices.get(r*2+1);
        return String.valueOf((char)random(beginning, end));
    }
