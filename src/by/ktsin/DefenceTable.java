package by.ktsin;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import by.ktsin.hashes.HashProvider;

public class DefenceTable implements HashTable{
    private final HashProvider provider;
    private int size;
    private KeyValuePair<String>[] table;


    public DefenceTable(HashProvider provider, int startSize){
        this.provider = provider;
        this.size = startSize;
        this.table = new KeyValuePair[startSize];
    }

    @Override
    public void add(String key, String value) {
        if(search(key) != null)
            return;
        int num = (int)provider.hash(key.getBytes(), size);
        if(table[num] == null)
            table[num] = new KeyValuePair<>(key, value);
        else{
            boolean isFound = false;
            while(num < size){
                if(table[num] == null){
                    table[num] = new KeyValuePair<>(key, value);
                    isFound = true;
                }
                num++;
            }
            if(!isFound){
                resize();
                add(key, value);
            }
        }


    }

    @Override
    public void remove(String key) {
        int num = (int)provider.hash(key.getBytes(), size);
        if(table[num] != null){
            boolean isFind = false;
            if(table[num].getKey().equals(key)){
                table[num] = null;
            }
            else{
                while(num < size){
                    if(table[num] != null){
                        if(table[num].getKey().equals(key)){
                            table[num] = null;
                            return;
                        }
                    }
                    num++;
                }
            }
        }
    }

    @Override
    public KeyValuePair<String> search(String key) {
        int num = (int)provider.hash(key.getBytes(), size);
        if(table[num] != null){
            boolean isFind = false;
            if(table[num].getKey().equals(key)){
                return table[num];
            }
            else{
                while(num < size){
                    if(table[num] != null){
                        if(table[num].getKey().equals(key)){
                            return table[num];
                        }
                    }
                    num++;
                }
            }
        }
        return null;
    }

    @Override
    public void print(PrintStream stream) {
        for (int i = 0; i < table.length; i++) {
            stream.printf("%3d. %s\n", i, table[i]);
        }
    }

    private void resize(){
        size = nextPrime(size);
        List<KeyValuePair<String>> tmp = new ArrayList<>();
        for(KeyValuePair<String> pair : table){
            if(pair != null)
                tmp.add(pair);
        }
        table = new KeyValuePair[size];
        for(KeyValuePair<String> pair : tmp){
            add(pair.getKey(), pair.getValue());
        }

    }

    private int nextPrime(int num){
        num++;
        for (int i = 2; i < num; i++) {
            if(num%i == 0) {
                num++;
                i=2;
            } else {
                continue;
            }
        }
        return num;
    }
}
