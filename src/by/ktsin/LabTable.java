package by.ktsin;

import by.ktsin.hashes.HashProvider;

import java.io.PrintStream;
import java.util.LinkedList;

public class LabTable implements HashTable {
    private final HashProvider hash;
    private final int startSize;
    private LinkedList[] array;


    public LabTable(HashProvider hash, int startSize) {
        this.hash = hash;
        this.startSize = startSize;
        array = new LinkedList[startSize];
    }

    @Override
    public void add(String key, String value){
        int hval = (int) (hash.hash(key.getBytes(), 0)  % startSize);
        if(array[hval] != null){
            array[hval].add(new KeyValuePair<String>(key, value));
        }
        else{
            array[hval] = new LinkedList();
            array[hval].add(new KeyValuePair<String>(key, value));
        }

    }

    @Override
    public void remove(String key){
        int hval = (int) (hash.hash(key.getBytes(), 0) % startSize);
        if(array[hval] != null){
            if((long) array[hval].size() == 1){
                array[hval] = null;
                return;
            }
            else{
                Object target = null;
                for(Object i : array[hval]){
                    KeyValuePair<String> e = (KeyValuePair<String>) i;
                    if(e.getKey().equals(key)){
                        target = e;
                    }
                }
                if(target != null)
                    array[hval].remove(target);
            }
        }
        else
            return;
    }

    @Override
    public KeyValuePair<String> search(String key){
        int hval = (int) (hash.hash(key.getBytes(), 0) % startSize);
        if(array[hval] != null){
            if((long) array[hval].size() == 1){
                return (KeyValuePair<String>)(array[hval].get(0));
            }
            else{
                for(Object i : array[hval]){
                    KeyValuePair<String> e = (KeyValuePair<String>) i;
                    if(e.getKey().equals(key)){
                        return e;
                    }
                }
                return null;
            }
        }
        else
            return null;
    }

    @Override
    public void print(PrintStream stream){
        stream.printf("Hash table with size %d\n\n", startSize);
        for(LinkedList list : array){
            if(list != null){
                for(Object obj : list){
                    stream.printf("%s, ", obj);
                }
                stream.println("");

            }
        }
    }
}
