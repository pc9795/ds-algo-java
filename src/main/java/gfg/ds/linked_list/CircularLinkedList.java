package gfg.ds.linked_list;

import gfg.ds.linked_list.adt.LinkedList;

public class CircularLinkedList implements LinkedList {
  public SinglyLinkedList.Node last;
  public int size;

  @Override
  public int size() {
    return size;
  }

  /** t=O(1) */
  @Override
  public CircularLinkedList insertAtFront(int data) {
    if (this.last == null) {
      return insertEmpty(data);
    }
    SinglyLinkedList.Node node = new SinglyLinkedList.Node(data);
    node.next = last.next;
    last.next = node;
    size++;
    return this;
  }

  /** t=O(n) */
  @Override
  public CircularLinkedList insertAtPos(int pos, int data) {
    assert pos >= 0 && pos <= size
        : String.format("Position entered should be between %s and %s", 0, size);

    if (this.last == null) {
      return insertEmpty(data);
    }
    if (pos == size) {
      return insertAtEnd(data);
    }
    if (pos == 0) {
      return insertAtFront(data);
    }

    SinglyLinkedList.Node curr = last.next;
    for (int i = 0; i < pos - 1; i++) {
      curr = curr.next;
    }
    SinglyLinkedList.Node node = new SinglyLinkedList.Node(data);
    node.next = curr.next;
    curr.next = node;
    size++;
    return this;
  }

  /** t=O(1) */
  CircularLinkedList insertEmpty(int data) {
    this.last = new SinglyLinkedList.Node(data);
    this.last.next = last;
    size++;
    return this;
  }

  public CircularLinkedList append(int... data) {
    for (Integer item : data) {
      this.insertAtEnd(item);
    }
    return this;
  }

  /** t=O(1) */
  @Override
  public CircularLinkedList insertAtEnd(int data) {
    if (this.last == null) {
      return insertEmpty(data);
    }
    SinglyLinkedList.Node node = new SinglyLinkedList.Node(data);
    node.next = last.next;
    last.next = node;
    last = node; // This is one line in addition to the steps of insertAtFront
    size++;
    return this;
  }

  /** t=O(n) */
  @Override
  public String toString() {
    if (this.last == null) {
      return "{}";
    }
    SinglyLinkedList.Node curr = this.last.next;
    StringBuilder sb = new StringBuilder();
    sb.append("{");
    do {
      sb.append(curr.data).append(",");
      curr = curr.next;
    } while (curr != last.next);
    sb.append("}");
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    CircularLinkedList that = (CircularLinkedList) o;
    if (size != that.size) return false;
    // Considering empty lists as equal.
    if (size == 0) {
      return true;
    }
    CircularLinkedList otherList = (CircularLinkedList) o;
    SinglyLinkedList.Node currThis = last.next;
    SinglyLinkedList.Node currThat = otherList.last.next;
    do {
      if (currThis.data != currThat.data) {
        return false;
      }
      currThat = currThat.next;
      currThis = currThis.next;
    } while (currThis != last.next);
    return true;
  }

  @Override
  public int hashCode() {
    int result = last != null ? last.hashCode() : 0;
    result = 31 * result + size;
    return result;
  }
}
