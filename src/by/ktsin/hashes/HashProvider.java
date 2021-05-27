package by.ktsin.hashes;

public interface HashProvider {
    public long hash(byte[] value, int seed);
}
