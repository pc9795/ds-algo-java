package geeks_for_geeks.ds.linked_list;

public interface LinkedList {
    public int size();

    public LinkedList insertAtFront(int data);

    public LinkedList insertAtPosition(int pos, int data);

    public LinkedList insertAtEnd(int data);
}
