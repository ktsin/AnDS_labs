package by.ktsin;

import by.ktsin.hashes.FNVHash;
import by.ktsin.hashes.MurMurHash;
import by.ktsin.hashes.HashProvider;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FNVHash hash0 = new FNVHash();
        MurMurHash hash1 = new MurMurHash();
        Scanner sc = new Scanner(System.in);

        HashTable table0 = fillTable(hash0, 5, false);
        HashTable table1 = fillTable(hash1, 5, false);

        System.out.println("First table:");
        table0.print(System.out);
        System.out.println("\n\n");

        System.out.println("Second table:");
        table1.print(System.out);
        System.out.println("\n\n");

        System.out.print("Enter new key: ");
        String key = sc.nextLine();
        System.out.print("Enter new value: ");
        String value = sc.nextLine();

        table0.add(key, value);
        System.out.println("First table:");
        table0.print(System.out);
        System.out.println("\n\n");

        table1.add(key, value);
        System.out.println("Second table:");
        table1.print(System.out);
        System.out.println("\n\n");

        System.out.print("Enter key to search: ");
        key = sc.nextLine();

        System.out.println("First table:");
        System.out.println(table0.search(key));
        System.out.println("\n\n");

        System.out.println("Second table:");
        System.out.println(table0.search(key));
        System.out.println("\n\n");

        System.out.print("Enter key to delete: ");
        key = sc.nextLine();

        table0.remove(key);
        System.out.println("First table:");
        table0.print(System.out);
        System.out.println("\n\n");

        table1.remove(key);
        System.out.println("Second table:");
        table1.print(System.out);
        System.out.println("\n\n");
    }

    private static HashTable fillTable(HashProvider provider, int count, boolean isDefence) {
        HashTable table = (!isDefence)?new LabTable(provider, 997):new DefenceTable(provider, 23);
        for(int i = 0; i < count; i++){
            table.add(randString(), randString());
        }
        return table;
    }

    private static String randString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
