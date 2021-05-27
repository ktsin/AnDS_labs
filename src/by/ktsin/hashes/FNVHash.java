package by.ktsin.hashes;

public class FNVHash implements HashProvider {
    private static final long FNV_64_INIT = 0xcbf29ce484222325L;
    private static final long FNV_64_PRIME = 1099511628211L;

    @Override
    public long hash(byte[] value, int seed) {
        long rv = FNV_64_INIT;
        final int len = value.length;
        for (byte b : value) {
            rv ^= b;
            rv *= FNV_64_PRIME;
        }
        return rv >>> 1;
    }
}
