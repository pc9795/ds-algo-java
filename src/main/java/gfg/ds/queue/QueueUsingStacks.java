package gfg.ds.queue;

import gfg.ds.queue.adt.Queue;

import java.util.ArrayDeque;

/** @noinspection WeakerAccess */
public class QueueUsingStacks implements Queue {
  private final ArrayDeque<Integer> insertionStack, queryStack;
  // Costly dequeue is better than costly enqueue as we have to move elements only one time.
  private final boolean costlyEnqueue;

  public QueueUsingStacks(boolean costlyEnqueue) {
    insertionStack = new ArrayDeque<>();
    queryStack = new ArrayDeque<>();
    this.costlyEnqueue = costlyEnqueue;
  }

  /** t=O(1) */
  @Override
  public Queue enqueue(int data) {
    if (costlyEnqueue) {
      costlyEnqueue(data);
    } else {
      efficientEnqueue(data);
    }
    return this;
  }

  /** t=O(1) */
  public int dequeue() {
    assert !isEmpty() : "Queue is empty";
    if (costlyEnqueue) {
      return efficientDeqeue();
    } else {
      return costlyDequeue();
    }
  }

  private void efficientEnqueue(int data) {
    insertionStack.push(data);
  }

  private void costlyEnqueue(int data) {
    while (!queryStack.isEmpty()) {
      insertionStack.push(queryStack.pop());
    }
    insertionStack.push(data);
    while (!insertionStack.isEmpty()) {
      queryStack.push(insertionStack.pop());
    }
  }

  private int costlyDequeue() {
    if (queryStack.isEmpty()) {
      while (!insertionStack.isEmpty()) {
        queryStack.push(insertionStack.pop());
      }
    }
    return queryStack.pop();
  }

  private int efficientDeqeue() {
    return queryStack.pop();
  }

  /** t=O(1) */
  @SuppressWarnings("ConstantConditions")
  @Override
  public int front() {
    assert !isEmpty() : "Queue is empty";

    if (costlyEnqueue) {
      return queryStack.peek();
    } else {
      if (queryStack.isEmpty()) {
        while (!insertionStack.isEmpty()) {
          queryStack.push(insertionStack.pop());
        }
      }
      return queryStack.peek();
    }
  }

  /** t=O(1) */
  @SuppressWarnings("ConstantConditions")
  @Override
  public int rear() {
    assert !isEmpty() : "Queue is empty";
    if (costlyEnqueue) {
      while (!queryStack.isEmpty()) {
        insertionStack.push(queryStack.pop());
      }
      int temp = insertionStack.peek();
      while (!insertionStack.isEmpty()) {
        queryStack.push(insertionStack.pop());
      }
      return temp;
    } else {
      if (insertionStack.isEmpty()) {
        while (!queryStack.isEmpty()) {
          insertionStack.push(queryStack.pop());
        }
      }
      return insertionStack.peek();
    }
  }

  /** t=O(1) */
  public boolean isEmpty() {
    return insertionStack.isEmpty() && queryStack.isEmpty();
  }
}
