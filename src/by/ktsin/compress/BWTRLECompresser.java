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
        return null;
    }
}
