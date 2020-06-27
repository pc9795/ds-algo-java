package gfg.ds.queue;

import gfg.ds.queue.adt.Queue;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 23:03
 * NOTE: If we use another variable 'size' then checking overflow becomes easy.
 **/
public class QueueUsingArray implements Queue {
    protected int values[];
    int front;
    int rear;

    QueueUsingArray(int size) {
        values = new int[size];
        front = -1;
        rear = 0;
    }

    /**
     * t=O(1)
     *
     * @param data data to add
     * @return calling instance
     */
    @Override
    public QueueUsingArray enqueue(int data) {
        assert !isFull() : "Queue is full";
        if (front == -1) {
            front = 0;
        }
        values[rear++] = data;
        rear = (rear) % values.length;
        return this;
    }

    /**
     * t=O(1)
     *
     * @return calling instance
     */
    @Override
    public int dequeue() {
        assert !isEmpty() : "Queue is empty";
        int temp = values[front];
        front = (front + 1) % values.length;
        if (front == rear) {
            front = -1;
            rear = 0;
        }
        return temp;
    }

    /**
     * t=O(1)
     *
     * @return value at the front
     */
    @Override
    public int front() {
        assert !isEmpty() : "Queue is empty";
        return values[front];
    }

    /**
     * t=O(1)
     *
     * @return value at the rear
     */
    @Override
    public int rear() {
        assert !isEmpty() : "Queue is empty";
        if (rear == 0) {
            return values[values.length - 1];
        }
        return values[rear - 1];
    }

    /**
     * @return true if queue is full
     */
    public boolean isFull() {
        return front == rear;
    }

    /**
     * @return true if queue is empty
     */
    public boolean isEmpty() {
        return front == -1;
    }

    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int tempFront = front;
        int tempRear = rear;
        do {
            sb.append(values[tempFront]).append(",");
            tempFront = (tempFront + 1) % values.length;
        } while (tempFront != tempRear);
        sb.append("}").append("front:").append(front).append(",rear:").append(rear);
        return sb.toString();
    }

}
