package by.ktsin;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Pointer tree");
        Scanner in = new Scanner(System.in);
        System.out.print("Enter size: ");
        int size = in.nextInt();
        PtrTree pTree = new PtrTree();
        Random random = new Random();
        for(int i = 0; i < size; i++){
            pTree.add(random.nextInt(10000));
        }
        System.out.println("Tree:");
        System.out.println(pTree);
        System.out.print("Enter value to remove: ");
        int valueToRemove = in.nextInt();
        System.out.println(pTree.remove(valueToRemove));
        System.out.println(pTree);

	// write your code here
    }
}
