package by.ktsin;

import java.io.PrintStream;

public interface HashTable {
    void add(String key, String value);

    void remove(String key);

    KeyValuePair<String> search(String key);

    void print(PrintStream stream);
}
