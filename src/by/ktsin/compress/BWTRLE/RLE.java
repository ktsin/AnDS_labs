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

}
