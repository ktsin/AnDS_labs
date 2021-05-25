package by.ktsin;

import by.ktsin.hashes.IHashProvider;

import java.util.LinkedList;

public class LabTable {
    private final IHashProvider hash;
    private final int startSize;
    private LinkedList[] array;


    public LabTable(IHashProvider hash, int startSize) {
        this.hash = hash;
        this.startSize = startSize;
        array = new LinkedList[startSize];
    }

    public void add(String key, String value){

    }

    public void remove(String key){
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
}
