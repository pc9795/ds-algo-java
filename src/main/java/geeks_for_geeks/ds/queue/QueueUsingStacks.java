package geeks_for_geeks.ds.queue;

import geeks_for_geeks.ds.queue.adt.Queue;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 07-10-2018 23:22
 **/
public class QueueUsingStacks implements Queue {
    private final ArrayDeque<Integer> insertionStack, queryStack;
    // Costly dequeue is better than costly enqueue as we have to move elements only one time.
    private final boolean costlyEnqueue;

    public QueueUsingStacks(boolean costlyEnqueue) {
        insertionStack = new ArrayDeque<>();
        queryStack = new ArrayDeque<>();
        this.costlyEnqueue = costlyEnqueue;
    }

    /**
     * t=O(1)
     *
     * @param data data to add
     * @return calling instance
     */
    @Override
    public Queue enqueue(int data) {
        if (costlyEnqueue) {
            costlyEnqueue(data);
        } else {
            efficientEnqueue(data);
        }
        return this;
    }

    /**
     * t=O(1)
     *
     * @return front of the queue
     */
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

    /**
     * t=O(1)
     *
     * @return front of the queue
     */
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

    /**
     * t=O(1)
     *
     * @return rear of the queue
     */
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


    /**
     * t=O(1)
     *
     * @return true if queue is empty
     */
    public boolean isEmpty() {
        return insertionStack.isEmpty() && queryStack.isEmpty();
    }
}
