package gfg.ds.queue;

import java.util.Arrays;

/**
 * This method requires some extra space. Space may not be an issue because queue items are
 * typically large, for example, queues of employees, students, etc where every item is of hundreds
 * of bytes. For usch large queues, the extra space used is comparatively very less as we use three
 * integer arrays as extra space.
 *
 * @noinspection WeakerAccess
 */
public class KQueue {
  private int[] arr, front, rear, next;
  private int free;

  public KQueue(int queues, int size) {
    assert queues <= size;
    arr = new int[size];
    front = new int[queues];
    rear = new int[queues];
    next = new int[size];
    free = 0;
    Arrays.fill(front, -1);
    Arrays.fill(rear, -1);
    for (int i = 0; i < size - 1; i++) {
      next[i] = i + 1;
    }
  }

  /** t=O(1) */
  public void enqueue(int queueNo, int data) {
    assert !isFull() : "Queue is full";
    assert queueNo >= 0 && queueNo < front.length : "Invalid queue no";

    int toBeInsertedIndex = free;
    free = next[free];

    if (front[queueNo] == -1) {
      front[queueNo] = toBeInsertedIndex;
    } else {
      next[rear[queueNo]] = toBeInsertedIndex;
    }
    next[toBeInsertedIndex] = -1;
    rear[queueNo] = toBeInsertedIndex;
    arr[toBeInsertedIndex] = data;
  }

  /** t=O(1) */
  public int dequeue(int queueNo) {
    assert !isEmpty(queueNo) : "Queue is empty";
    assert queueNo >= 0 && queueNo < front.length : "Invalid queue no";

    int toBeDeletedIndex = front[queueNo];

    next[toBeDeletedIndex] = free;
    free = toBeDeletedIndex;

    front[queueNo] = next[toBeDeletedIndex];

    if (front[queueNo] == -1) {
      rear[queueNo] = -1;
    }

    return arr[toBeDeletedIndex];
  }

  /** t=O(1) */
  public int front(int queueNo) {
    assert !isEmpty(queueNo) : "Queue is empty";
    assert queueNo >= 0 && queueNo < front.length : "Invalid queue no";

    return arr[front[queueNo]];
  }

  /** t=O(1) */
  public int rear(int queueNo) {
    assert !isEmpty(queueNo) : "Queue is empty";
    assert queueNo >= 0 && queueNo < front.length : "Invalid queue no";

    return arr[rear[queueNo]];
  }

  /** t=O(1) */
  public boolean isFull() {
    return free == -1;
  }

  /** t=O(1) */
  public boolean isEmpty(int queueNo) {
    assert queueNo >= 0 && queueNo < front.length : "Invalid queue no";

    return front[queueNo] == -1;
  }
}
