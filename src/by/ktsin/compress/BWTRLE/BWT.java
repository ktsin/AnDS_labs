package by.ktsin.compress.BWTRLE;

/**
 * Burrows Wheeler Transform
 */
public final class BWT {
    public static String encode(String rawString) {
        String[] strs = new String[rawString.length()];
        for (int i = 0; i < strs.length; ++i) {
            strs[i] = rawString.substring(i) + rawString.substring(0, i);
        }

        java.util.Arrays.sort(strs);

        StringBuilder builder = new StringBuilder();
        for (String str : strs) {
            builder.append(str.charAt(str.length() - 1));
        }
        return builder.toString();
    }

    private static final class BWTComparator implements java.util.Comparator<Integer> {
        private final String string;

        BWTComparator(String string) {
            this.string = string;
        }

        @Override
        public int compare(Integer i, Integer j) {
            return string.charAt(i) - string.charAt(j);
        }

        public boolean equals(Integer i, Integer j) {
            return string.charAt(i) == string.charAt(j);
        }
    }

    public static String decode(String encodedString) {
        Integer[] indices = new Integer[encodedString.length()];
        for (int i = 0; i < indices.length; ++i) {
            indices[i] = i;
        }
        java.util.Arrays.sort(indices, new BWTComparator(encodedString));

        int startIndex = 0;
        for (; encodedString.charAt(startIndex) != '$'; ++startIndex);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < indices.length - 1; ++i) {
            startIndex = indices[startIndex];
            char c = encodedString.charAt(startIndex);
            builder.append(c);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        String encodedString = BWT.encode(args[0] + '$');
        System.out.println(encodedString);
        String decodedString = BWT.decode(encodedString);
        System.out.println(decodedString);
    }
}
