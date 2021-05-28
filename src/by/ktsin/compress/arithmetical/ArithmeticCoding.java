package by.ktsin.compress.arithmetical;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ArithmeticCoding {
    private TreeMap<Character, Double> probabilities = new TreeMap<>();
    private TreeMap<Character, Double> right_interval = new TreeMap<>();
    private TreeMap<Character, Double> left_interval = new TreeMap<>();

    public TreeMap<Character, Double> getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(TreeMap<Character, Double> probabilities) {
        this.probabilities = probabilities;
    }

    public byte[] encode(String str, int blockSize) {
        //сначала побавляем лишнее
        int originalSize = str.length();
        int newSize = (str.length() % blockSize == 0)?(str.length() / blockSize)
                :(blockSize*(str.length() / blockSize + 1));
        String format = String.format("%%-%ds", newSize);
        str = String.format(format, str);
        format = String.format("%%-%ds", blockSize);
        // строим модель
        TreeMap<Character, Integer> count = new TreeMap<>();
        int length = str.length();
        for (char c : str.toCharArray()) {
            if (count.containsKey(c)) {
                count.put(c, count.get(c) + 1);
            } else
                count.put(c, 1);
        }
        for (Map.Entry<Character, Integer> c : count.entrySet()) {
            probabilities.put(c.getKey(), (c.getValue() / (double) length));
        }
        Object[] sorted = probabilities.entrySet().stream().sorted(new EntrySetComparer()).toArray();
        double max = 0;
        double min = 0;
        for (int i = sorted.length - 1; i >= 0; i--) {
            Map.Entry<Character, Double> t = (Map.Entry<Character, Double>) sorted[i];
            max += t.getValue();
            right_interval.put(t.getKey(), max);
            left_interval.put(t.getKey(), min);
            min += t.getValue();
        }
        // разбиваем на блоки  по 12 символов
        String finalFormat = format;
        Object[] blocks = Arrays.stream(str.split("(?<=\\G.{"+blockSize+"})")).map(e -> String.format(finalFormat, e)).toArray();
        ArrayList<Double> res = new ArrayList<>();
        for (Object block : blocks) {
            double a = encodeBlock((String) block);
            res.add(a);
        }

        res = res;
        int block = 0;
        byte[] bytes = new byte[res.size() * Double.BYTES];
        int num = 0;
        for (Double r : res) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(Double.BYTES);
            byteBuffer.putDouble(r);
            for (byte b : byteBuffer.array()) {
                bytes[num] = b;
                num++;
            }
            block++;
        }
        return bytes;
    }

    private double encodeBlock(String block) {
        double res = 0;
        double p_left = 0;
        double p_right = 0;
        double low = 0;
        double high = 0;
        for (char c : block.toCharArray()) {
            if (res == 0) {
                res = right_interval.get(c);
                p_left = left_interval.get(c);
                p_right = right_interval.get(c);
            } else {
                low = p_left + (p_right - p_left) * left_interval.get(c);
                high = p_left + (p_right - p_left) * right_interval.get(c);
                res = low;
                p_left = low;
                p_right = high;
            }
            res += (p_right - p_left)/2.0;
        }
        return res;
    }

    private class EntrySetComparer implements Comparator<Map.Entry<Character, Double>> {
        public int compare(Map.Entry<Character, Double> o1, Map.Entry<Character, Double> o2) {
            return Double.compare(o1.getValue(), o2.getValue());
        }
    }

    public String decode(byte[] input, TreeMap<Character, Double> probabilities, int length, int blockSize) {
        this.probabilities = probabilities;
        Object[] sorted = probabilities.entrySet().stream().sorted(new EntrySetComparer()).toArray();
//        double max = 0;
//        double min = 0;
//        for (int i = sorted.length - 1; i >= 0; i--) {
//            Map.Entry<Character, Double> t = (Map.Entry<Character, Double>) sorted[i];
//            max += t.getValue();
//            right_interval.put(t.getKey(), max);
//            left_interval.put(t.getKey(), min);
//            min += t.getValue();
//        }
        StringBuilder str = new StringBuilder();
        // конвертируем из байтов в даблы
        double[] doubles = byte2Double(input, false);
        for (double b : doubles) {
            str.append(decodeBlock(b, blockSize));
        }
        return str.toString();
    }

    private String decodeBlock(double input, int length) {
        StringBuilder result = new StringBuilder();
        double code = input;
        for (int i = 0; i < length; i++) {
            // если первый сивол, то просто найти интервал
            // если далее, то найти код, найти интервал
            if (i == 0) {
                Character c = decodeSymbol(code);
                result.append(c);
            } else {
                char prev = result.charAt(result.length() - 1);
                code = (code - getMin(prev))
                        / (getMax(prev) - getMin(prev));
                Character c = decodeSymbol(code);
                result.append(c);
            }
        }
        return result.toString();
    }

    private Double getMin(char c) {
        for (Map.Entry<Character, Double> e : left_interval.entrySet()){
            if(e.getKey().charValue() == c)
                return e.getValue();
        }
        return 0.0;
    }

    private Double getMax(char c) {
        for (Map.Entry<Character, Double> e : right_interval.entrySet()){
            if(e.getKey().charValue() == c)
                return e.getValue();
        }
        return 0.0;
    }

    private Character decodeSymbol(double code) {
        Object[] left = new Map.Entry[left_interval.size()];
        left = left_interval.entrySet().stream().sorted(new EntrySetComparer()).map(e -> e).toArray();
        Object[] right = new Map.Entry[right_interval.size()];
        right = right_interval.entrySet().stream().sorted(new EntrySetComparer()).toArray();;
        for (int i = 0; i < left_interval.size(); i++) {
            if (((Map.Entry<Character, Double>) left[i]).getValue() <= code
                    && ((Map.Entry<Character, Double>) right[i]).getValue() > code) {
                return ((Map.Entry<Character, Double>) left[i]).getKey();
            }
        }
        return '_';
    }

    private static double[] byte2Double(byte[] inData, boolean byteSwap) {
        double[] res = new double[inData.length / Double.BYTES];
        byte[][] chunks = chunkArray(inData, Double.BYTES);
        int i = 0;
        for (byte[] chunk : chunks) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(Double.BYTES);
            byteBuffer.put(chunk);
            byteBuffer.flip();
            res[i] = byteBuffer.getDouble();
            i++;
        }
        return res;
    }

    private static byte[][] chunkArray(byte[] array, int chunkSize) {
        int numOfChunks = (int) Math.ceil((double) array.length / chunkSize);
        byte[][] output = new byte[numOfChunks][];

        for (int i = 0; i < numOfChunks; ++i) {
            int start = i * chunkSize;
            int length = Math.min(array.length - start, chunkSize);

            byte[] temp = new byte[length];
            System.arraycopy(array, start, temp, 0, length);
            output[i] = temp;
        }

        return output;
    }

    public static void main(String[] args) {
        int blockSize = 9;
        ArithmeticCoding a = new ArithmeticCoding();
        String str = "Big fucking string for testing purposes";
        System.out.println("Source: " + args[0]);
        byte[] bytes = a.encode(args[0], blockSize);
        int l = 0;
        String res = a.decode(bytes, a.probabilities, args[0].length(), blockSize);
        System.out.println("Result: " + res);
    }
}
