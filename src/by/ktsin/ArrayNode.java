package by.ktsin;

public class ArrayNode {
    private int value;
    private int left;
    private int right;


    public ArrayNode(int value) {
        this.value = value;
        this.left = -1;
        this.right = -1;
    }

    public ArrayNode(int value, int left, int right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return String.format("%5d", value);
    }
}
