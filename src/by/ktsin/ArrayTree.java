package by.ktsin;

import java.util.ArrayList;

public class ArrayTree implements Tree<ArrayNode> {
    private ArrayList<ArrayNode> nodes;
    private int rootIndex;

    @Override
    public boolean isPresent(int value) {
        return nodes.stream().anyMatch(e -> e.getValue() == value);
    }

    @Override
    public ArrayNode search(int value) {
        return searchLookup(rootIndex, value);
    }

    private ArrayNode searchLookup(int index, int value) {
//        if(index == -1)
//            return null;
//        if(nodes.get(index).getValue() == value)
//            return nodes.get(index);
//        else{
//            ArrayNode r = searchLoockup(nodes.get(index).getLeft(), value);
//            if(r != null)
//                return  r;
//            else
//                return searchLoockup(nodes.get(index).getRight(), value);
//        }
        if (nodes.get(index).getValue() == value)
            return nodes.get(index);
        else {
            if (nodes.get(index).getValue() > value)
                if (nodes.get(index).getLeft() != -1)
                    return searchLookup(nodes.get(index).getLeft(), value);
                else
                    return null;
            else if (nodes.get(index).getRight() != -1)
                return searchLookup(nodes.get(index).getRight(), value);
            else
                return null;
        }
    }

    @Override
    public boolean add(int value) {
        if (isPresent(value))
            return false;
        else {
            if (nodes == null || nodes.size() == 0) {
                nodes = new ArrayList<>();
                nodes.add(new ArrayNode(value));
                rootIndex = 0;
            } else {
                ArrayNode node = getNearestNode(value, rootIndex);
                if (node.getValue() > value) {
                    int index = nodes.size();
                    node.setLeft(index);
                    nodes.add(new ArrayNode(value));
                } else {
                    int index = nodes.size();
                    node.setRight(index);
                    nodes.add(new ArrayNode(value));
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(int value) {
        deleteNode(rootIndex, value);
        return true;
    }

    private void deleteNode(int node, int value) {
        if (!isPresent(value))
            return;
        else {
            ArrayNode parent = null;
            // start with the root node
            ArrayNode curr = nodes.get(rootIndex);
            // search key in the BST and set its parent pointer
            while (curr != null && curr.getValue() != value) {
                // update the parent to the current node
                parent = curr;
                // if the given key is less than the current node, go to the left subtree;
                // otherwise, go to the right subtree
                if (value < curr.getValue()) {
                    curr = nodes.get(curr.getLeft());
                } else {
                    curr = nodes.get(curr.getRight());
                }
            }
            // Case 1: node to be deleted has no children, i.e., it is a leaf node
            assert curr != null;
            if (curr.getLeft() == -1 && curr.getRight() == -1) {
                // if the node to be deleted is not a root node, then set its
                // parent left/right child to null
                if (curr != nodes.get(rootIndex)) {
                    assert parent != null;
                    if (nodes.get(parent.getLeft()) == curr) {
                        parent.setLeft(-1);
                    } else {
                        parent.setRight(-1);
                    }
                }
                // if the tree has only a root node, set it to null
                else {
                    rootIndex = -1;
                    nodes = null;
                }
            }
            // Case 2: node to be deleted has two children
            else if (curr.getLeft() != -1 && curr.getRight() != -1) {
                // find its inorder successor node
                ArrayNode successor = getMinimumKey(nodes.get(curr.getRight()));

                // store successor value
                int val = successor.getValue();

                // recursively delete the successor. Note that the successor
                // will have at most one child (right child)
                deleteNode(rootIndex, successor.getValue());

                // copy value of the successor to the current node
                curr.setValue(val);
            }
            // Case 3: node to be deleted has only one child
            else {
                // choose a child node
                ArrayNode child = (curr.getLeft() != -1) ? nodes.get(curr.getLeft()) : nodes.get(curr.getRight());
                // if the node to be deleted is not a root node, set its parent
                // to its child
                if (curr != nodes.get(rootIndex)) {
                    assert parent != null;
                    if (curr == nodes.get(parent.getLeft())) {
                        parent.setLeft(nodes.indexOf(child));
                    } else {
                        parent.setRight(nodes.indexOf(child));
                    }
                }
                // if the node to be deleted is a root node, then set the root to the child
                else {
                    rootIndex = nodes.indexOf(child);
                }
            }
        }
    }

    private ArrayNode getMinimumKey(ArrayNode curr) {
        while (curr.getLeft() != -1) {
            curr = nodes.get(curr.getLeft());
        }
        return curr;
    }

    @Override
    public void defence() {

    }

    private ArrayNode getNearestNode(int value, int root) {
        if (nodes.get(root).getValue() > value)
            if (nodes.get(root).getLeft() != -1 && nodes.get(root).getValue() != value)
                return getNearestNode(value, nodes.get(root).getLeft());
            else
                return nodes.get(root);
        else if (nodes.get(root).getRight() != -1 && nodes.get(nodes.get(root).getRight()).getValue() != value)
            return getNearestNode(value, nodes.get(root).getRight());
        else
            return nodes.get(root);
    }
}
