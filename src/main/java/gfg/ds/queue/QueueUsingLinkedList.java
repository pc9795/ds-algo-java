package gfg.ds.queue;

import gfg.ds.linked_list.SinglyLinkedList;
import gfg.ds.queue.adt.Queue;

public class QueueUsingLinkedList implements Queue {
  private SinglyLinkedList.Node front;
  private SinglyLinkedList.Node rear;

  /** t=O(1) */
  @Override
  public Queue enqueue(int data) {
    SinglyLinkedList.Node node = new SinglyLinkedList.Node(data);
    if (isEmpty()) {
      front = rear = node;
    } else {
      rear.next = node;
      rear = node;
    }
    return this;
  }

  /** It will remove the data in the front t=O(1) */
  @Override
  public int dequeue() {
    assert !isEmpty() : "Queue is empty";
    int data = front.data;
    front = front.next;
    if (front == null) {
      rear = null;
    }
    return data;
  }

  /** t=O(1) */
  @Override
  public int front() {
    assert !isEmpty() : "Queue is empty";
    return front.data;
  }

  /** t=O(1) */
  @Override
  public int rear() {
    assert !isEmpty() : "Queue is empty";
    return rear.data;
  }

  /** t=O(1) */
  public boolean isEmpty() {
    return front == null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    QueueUsingLinkedList that = (QueueUsingLinkedList) o;
    SinglyLinkedList.Node i, j;
    for (i = front, j = that.front; i != null && j != null; i = i.next, j = j.next) {
      if (i.data != j.data) {
        return false;
      }
    }
    return i == null && j == null;
  }

  @Override
  public int hashCode() {
    int result = front != null ? front.hashCode() : 0;
    result = 31 * result + (rear != null ? rear.hashCode() : 0);
    return result;
  }
}
