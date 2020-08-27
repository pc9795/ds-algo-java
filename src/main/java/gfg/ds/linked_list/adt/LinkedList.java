package gfg.ds.linked_list.adt;

public interface LinkedList {
  int size();

  LinkedList insertAtFront(int data);

  LinkedList insertAtPos(int pos, int data);

  LinkedList insertAtEnd(int data);
}
