package by.ktsin.compress.BWTRLE;

public final class RLE {

    public static String encode(String chain) {

        if (chain == null || chain.isEmpty())
            return "";
        if(chain.length()==1)return encodeUnit(chain);

        StringBuilder prefix= new StringBuilder();
        int i=0;
        do {
            prefix.append(chain.charAt(i));
            i++;
        } while (i<chain.length()&& chain.charAt(i-1)==chain.charAt(i));
        if(i<chain.length()&&chain.charAt(i-1)!=chain.charAt(i)){
            return encodeUnit(prefix.toString())+encode(chain.substring(prefix.length()));
        }

        return encodeUnit(chain);
    }

    private static String encodeUnit(String chain) {
        return "" + chain.length() + chain.charAt(0);
    }

    public static String decode(String encodedString) {
        String decodedString = null;
        //aaabbbcccccdd
        //3a3b5c2d

        int n = encodedString.length();
        StringBuilder sb= new StringBuilder();
        for (int i = 0; i < n; i++) {
            if(i+1 <n && i%2 ==0)
                sb.append(repeat(Integer.parseInt(String.valueOf(encodedString.charAt(i))),encodedString.charAt(i+1)));
        }

        return sb.toString();

    }

    private static String repeat(int length, char charAt) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < length; j++) {
            sb.append(charAt);
        }
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(args[0]);
        String encoded = encode(args[0]);
        System.out.println(encoded);
        System.out.println(decode(encoded));

    }

}
