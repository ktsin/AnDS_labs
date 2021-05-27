package by.ktsin;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PtrTree implements Tree<PtrNode> {
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
        deleteNode(root, value);
        return true;
    }

    public void deleteNode(PtrNode node, int value){
        if (!isPresent(value))
            return;
        else {
            PtrNode parent = null;
            // start with the root node
            PtrNode curr = root;
            // search key in the BST and set its parent pointer
            while (curr != null && curr.getValue() != value)
            {
                // update the parent to the current node
                parent = curr;
                // if the given key is less than the current node, go to the left subtree;
                // otherwise, go to the right subtree
                if (value < curr.getValue()) {
                    curr = curr.getLeft();
                }
                else {
                    curr = curr.getRight();
                }
            }
            // Case 1: node to be deleted has no children, i.e., it is a leaf node
            assert curr != null;
            if (curr.getLeft() == null && curr.getRight() == null)
            {
                // if the node to be deleted is not a root node, then set its
                // parent left/right child to null
                if (curr != root)
                {
                    assert parent != null;
                    if (parent.getLeft() == curr) {
                        parent.setLeft(null);
                    }
                    else {
                        parent.setRight(null);
                    }
                }
                // if the tree has only a root node, set it to null
                else {
                    root = null;
                }
            }
            // Case 2: node to be deleted has two children
            else if (curr.getLeft() != null && curr.getRight() != null)
            {
                // find its inorder successor node
                PtrNode successor = getMinimumKey(curr.getRight());

                // store successor value
                int val = successor.getValue();

                // recursively delete the successor. Note that the successor
                // will have at most one child (right child)
                deleteNode(root, successor.getValue());

                // copy value of the successor to the current node
                curr.setValue(val);
            }
            // Case 3: node to be deleted has only one child
            else {
                // choose a child node
                PtrNode child = (curr.getLeft() != null)? curr.getLeft(): curr.getRight();
                // if the node to be deleted is not a root node, set its parent
                // to its child
                if (curr != root)
                {
                    assert parent != null;
                    if (curr == parent.getLeft()) {
                        parent.setLeft(child);
                    }
                    else {
                        parent.setRight(child);
                    }
                }
                // if the node to be deleted is a root node, then set the root to the child
                else {
                    root = child;
                }
            }
        }
        return;
    }

    private PtrNode getMinimumKey(PtrNode curr)
    {
        while (curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        return curr;
    }

    @Override
    public void defence() {
        ArrayList<Integer> leaves = new ArrayList<>();
        defenceLookup(leaves, root);
        for(int i : leaves){
            remove(i);
        }
    }

    private void defenceLookup(ArrayList<Integer> values, PtrNode node){
        if(node.getLeft() == null && node.getRight() == null){
            values.add(node.getValue());
            return;
        }
        defenceLookup(values, node.getLeft());
        defenceLookup(values, node.getRight());
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
            else if (node.getRight() != null)
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
