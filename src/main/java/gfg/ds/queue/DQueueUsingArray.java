package gfg.ds.queue;

import gfg.ds.queue.adt.Queue;

/** @noinspection WeakerAccess */
public class DQueueUsingArray extends QueueUsingArray implements Queue {

  public DQueueUsingArray(int size) {
    super(size);
  }

  /** t=O(1) */
  public DQueueUsingArray insertFront(int value) {
    assert !isFull() : "Queue is full";
    if (front == -1) {
      front = values.length - 1;
    } else {
      front--;
    }
    values[front] = value;
    return this;
  }

  /** t=O(1) */
  public int deleteLast() {
    assert !isEmpty() : "Queue is empty";
    rear--;
    if (rear == -1) {
      rear = values.length - 1;
    }
    int value = values[rear];
    if (front == rear) {
      front = -1;
      rear = 0;
    }
    return value;
  }
}
