package geeks_for_geeks.ds.queue;

import geeks_for_geeks.ds.queue.adt.Queue;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 23:03
 **/
public class QueueUsingArray implements Queue {
    /**
     * If we use another variable 'size' then checking overflow becomes easy.
     */
    protected int values[];
    protected int front;
    protected int rear;

    public QueueUsingArray() {
        values = new int[10];
        front = -1;
        rear = 0;
    }

    public QueueUsingArray(int size) {
        values = new int[size];
        front = -1;
        rear = 0;
    }

    @Override
    public QueueUsingArray enqueue(int data) {
        if (isFull()) {
            throw new RuntimeException("QueueUsingArray is full");
        }
        if (front == -1) {
            front = 0;
        }
        values[rear++] = data;
        rear = (rear) % values.length;
        return this;
    }

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("QueueUsingArray is empty");
        }
        int temp = values[front];
        front = (front + 1) % values.length;
        if (front == rear) {
            front = -1;
            rear = 0;
        }
        return temp;
    }

    @Override
    public int front() {
        if (isEmpty()) {
            throw new RuntimeException("QueueUsingArray is empty");
        }
        return values[front];
    }

    @Override
    public int rear() {
        if (isEmpty()) {
            throw new RuntimeException("QueueUsingArray is empty");
        }
        if (rear == 0) {
            return values[values.length - 1];
        }
        return values[rear - 1];
    }

    public boolean isFull() {
        return front == rear;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public String toString() {
        if (isEmpty()) {
            throw new RuntimeException("QueueUsingArray is empty");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int tempFront = front;
        int tempRear = rear;
        do {
            sb.append(values[tempFront]).append(",");
            tempFront = (tempFront + 1) % values.length;
        } while (tempFront != tempRear);
        sb.append("}").append("front:").append(front).append(",rear:").append( rear);
        return sb.toString();
    }

}
