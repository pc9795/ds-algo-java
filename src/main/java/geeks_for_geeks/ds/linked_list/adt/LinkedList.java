package geeks_for_geeks.ds.linked_list.adt;

public interface LinkedList {
    int size();

    LinkedList insertAtFront(int data);

    LinkedList insertAtPosition(int pos, int data);

    LinkedList insertAtEnd(int data);
}
