package by.ktsin.hashes;

public class MultiplicativeHash implements HashProvider {
    @Override
    public long hash(byte[] value, int seed) {
        long res = 1;
        for(byte b : value){
            res = (res * b) % seed;
        }
        return res >>> 1;
    }
}
