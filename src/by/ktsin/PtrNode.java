package by.ktsin;

public class PtrNode {
    private int value;
    private PtrNode left;
    private PtrNode right;

    public PtrNode(int value) {
        this.value = value;
        left = null;
        right = null;
    }

    public PtrNode(int value, PtrNode left, PtrNode right) {
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

    public PtrNode getLeft() {
        return left;
    }

    public void setLeft(PtrNode left) {
        this.left = left;
    }

    public PtrNode getRight() {
        return right;
    }

    public void setRight(PtrNode right) {
        this.right = right;
    }

    public void detachNode(PtrNode node){
        if(this.right == node)
            this.right = null;
        else if(this.left == node)
            this.left = null;
    }

    public void replaceNode(PtrNode old, PtrNode newNode){
        if(left == old){
            left = newNode;
            left.left = old.left;
            left.right = old.right;
        }
        if(right == old){
            right = newNode;
            right.left = old.left;
            right.right = old.right;
        }
    }
}
