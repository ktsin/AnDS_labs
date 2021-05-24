package by.ktsin;

import org.jetbrains.annotations.NotNull;

public class PtrTree implements ITree<PtrNode> {
    private PtrNode root = null;

    @Override
    public boolean isPresent(int value) {
        return search(value) != null;
    }

    @Override
    public PtrNode search(int value) {
        return search(value, root);
    }

    @Override
    public boolean add(int value) {
        if (root == null) {
            root = new PtrNode(value);
            return true;
        }
        if (isPresent(value)) {
            return false;
        } else {
            PtrNode node = getNearestNode(value, root);
            if (node.getValue() > value)
                node.setLeft(new PtrNode(value));
            else
                node.setRight(new PtrNode(value));
            return true;
        }
    }

    @Override
    public boolean remove(int value) {
        if(!isPresent(value))
            return true;
        else{
            //if node exist, it will return itself, else -- nearest
            PtrNode node = getNearestNode(value, root);
            PtrNode target = (node.getValue() > value)?node.getLeft():node.getRight();
            //if it is a leaf, only drop leaf
            if(target.getLeft() == null && target.getRight() == null){
                node.detachNode(target);
                return true;
            }
            //if it is generic node, we have to
            // get most left node in right or most right in left
            if(target.getLeft() != null){
                //get most right
                PtrNode replacer = target.getLeft();
                while(replacer.getRight() != null)
                    replacer = replacer.getRight();
                getNearestNode(replacer.getValue(), root).detachNode(replacer);
                node.replaceNode(target, replacer);
            }

        }
        return false;
    }

    @Override
    public void optimize() {

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        printLookup(builder, root, -4);
        return builder.toString();
    }

    private void printLookup(StringBuilder str, PtrNode node, int spaces) {
        if (node == null)
            return;
        spaces += 4;
        printLookup(str, node.getRight(), spaces);
        for (int i = 0; i < spaces; i++)
            str.append(' ');
        str.append(node.getValue());
        str.append('\n');
        printLookup(str, node.getLeft(), spaces);
    }

    private PtrNode search(int value, PtrNode node) {
        if (node.getValue() == value)
            return node;
        else {
            if (node.getValue() > value)
                if (node.getLeft() != null)
                    return search(value, node.getLeft());
                else
                    return null;
            else
                if (node.getRight() != null)
                    return search(value, node.getRight());
                else
                    return null;
        }
    }

    private @NotNull
    PtrNode getNearestNode(int value, PtrNode node) {
        if (node.getValue() > value)
            if (node.getLeft() != null && node.getLeft().getValue() != value)
                return getNearestNode(value, node.getLeft());
            else
                return node;
        else if (node.getRight() != null && node.getRight().getValue() != value)
            return getNearestNode(value, node.getRight());
        else
            return node;
    }
}
