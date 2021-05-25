package by.ktsin;

public class KeyValuePair<T> {
    private final T key;
    private final T value;

    public KeyValuePair(T key, T value) {
        this.key = key;
        this.value = value;
    }

    public T getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }
}
