package geeks_for_geeks.ds.queue;

import geeks_for_geeks.ds.queue.adt.Queue;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-10-2018 05:20
 **/
public class DQueue extends QueueUsingArray implements Queue {

    public DQueue() {
        super();
    }

    public DQueue(int size) {
        super(size);
    }

    public DQueue insertFront(int value) {
        if (isFull()) {
            throw new RuntimeException("QueueUsingArray is Full!");
        }
        if (front == -1) {
            front = values.length - 1;
        } else {
            front--;
        }
        values[front] = value;
        return this;
    }

    public int deleteLast() {
        if (isEmpty()) {
            throw new RuntimeException("QueueUsingArray is Empty!");
        }
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
