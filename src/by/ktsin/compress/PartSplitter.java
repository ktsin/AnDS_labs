package by.ktsin.compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;

public class PartSplitter {
    public static String[] SplitToParts(String input, int partSize){
        char[] array = input.toCharArray();
        int numOfChunks = (int) Math.ceil((double) input.length() / partSize);
        String[] output = new String[numOfChunks];
        for (int i = 0; i < numOfChunks; ++i) {
            int start = i * partSize;
            int length = Math.min(input.length() - start, partSize);

            char[] temp = new char[length];
            System.arraycopy(array, start, temp, 0, length);
            output[i] = new String(temp);
        }
        return output;
    }

    private static String ConcatParts(String[] input){
        StringBuilder builder = new StringBuilder();
        for(String str : input){
            builder.append(str);
        }
        return builder.toString();
    }
}
