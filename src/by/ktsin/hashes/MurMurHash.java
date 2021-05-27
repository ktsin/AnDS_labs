package by.ktsin.hashes;

public class MurMurHash implements HashProvider {
    private static final int UNSIGNED_MASK = 0xff;
    private static final long UINT_MASK = 0xFFFFFFFFL;
    private static final long LONG_MASK = 0xFFFFFFFFFFFFFFFFL;
    private static final long m = 0xc6a4a793L;
    private static final int r = 16;

    @Override
    public long hash(byte[] data, int seed) {
        int length = data.length;
        long h = seed ^ (length * m);

        // Mix 4 bytes at a time into the hash
        int length4 = length >> 2;

        for (int i = 0; i < length4; i++) {
            final int i4 = i << 2;

            long k = (data[i4] & UNSIGNED_MASK);
            k |= (data[i4 + 1] & UNSIGNED_MASK) << 8;
            k |= (data[i4 + 2] & UNSIGNED_MASK) << 16;
            k |= (long) (data[i4 + 3] & UNSIGNED_MASK) << 24;

            h = ((h + k) & UINT_MASK);
            h = ((h * m) & UINT_MASK);
            h ^= ((h >> 16) & UINT_MASK);
        }

        // remaining bytes
        int offset = length4 << 2;
        switch (length & 3) {
            case 3:
                h += ((data[offset + 2] << 16) & UINT_MASK);

            case 2:
                h += ((data[offset + 1] << 8) & UINT_MASK);

            case 1:
                h += ((data[offset]) & UINT_MASK);
                h = ((h * m) & UINT_MASK);
                h ^= ((h >> r) & UINT_MASK);
        }

        // final operations
        h = ((h * m) & UINT_MASK);
        h ^= ((h >> 10) & UINT_MASK);
        h = ((h * m) & UINT_MASK);
        h ^= ((h >> 17) & UINT_MASK);

        // return the hash
        return h >>> 1;
    }
}
