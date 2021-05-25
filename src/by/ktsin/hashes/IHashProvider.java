package by.ktsin.hashes;

public interface IHashProvider {
    public long hash(byte[] value, int seed);
}
