package by.ktsin;

public interface Tree<Node> {
    public boolean isPresent(int value);

    public Node search(int value);

    public boolean add(int value);

    public boolean remove(int value);

    public void defence();
}
