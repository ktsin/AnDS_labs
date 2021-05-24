package by.ktsin;

public interface ITree<Node> {
    public boolean isPresent(int value);

    public Node search(int value);

    public boolean add(int value);

    public boolean remove(int value);

    public void optimize();
}
