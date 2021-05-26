package by.ktsin.compress;

import by.ktsin.compress.BWTRLE.BWT;
import by.ktsin.compress.BWTRLE.RLE;

public class BWTRLECompresser implements Compresser{
    @Override
    public String compress(String input) {
        String res = BWT.encode(input + "$");
        res = RLE.encode(res);
        return res;
    }

    @Override
    public String decompress(String input) {
        String res = RLE.decode(input);
        res = BWT.decode(res);
        return res;
    }

    public static void main(String[] args){
        BWTRLECompresser c = new BWTRLECompresser();
        System.out.print("Input: ");
        System.out.print(args[0] + "\n");
        System.out.print("Compressed: ");
        String t = c.compress(args[0]);
        System.out.print(t + "\n");
        System.out.print("Decompress: ");
        t = c.decompress(t);
        System.out.print(t + "\n");

    }
}
