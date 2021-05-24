package by.ktsin;

import java.util.ArrayList;

public class ArrayTree implements ITree<ArrayNode> {
    private ArrayList<ArrayNode> nodes;
    private int rootIndex;

    @Override
    public boolean isPresent(int value) {
        return false;
    }

    @Override
    public ArrayNode search(int value) {
        return null;
    }

    @Override
    public boolean add(int value) {
        return false;
    }

    @Override
    public boolean remove(int value) {
        return false;
    }

    @Override
    public void optimize() {

    }
}
