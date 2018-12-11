package geeks_for_geeks.ds.queue;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 23:03
 **/
public class Queue {
    /**
     * If we use another variable size then checking overflow becomes easy.
     */
    public int values[];
    public int front;
    public int rear;

    public Queue() {
        values = new int[10];
        front = -1;
        rear = 0;
    }

    public Queue(int size) {
        values = new int[size];
        front = -1;
        rear = 0;
    }

    public Queue enqueue(int data) {
        if (isFull()) {
            throw new RuntimeException("Queue is full");
        }
        if (front == -1) {
            front = 0;
        }
        values[rear++] = data;
        rear = (rear) % values.length;
        return this;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        int temp = values[front];
        front = (front + 1) % values.length;
        if (front == rear) {
            front = -1;
            rear = 0;
        }
        return temp;
    }

    public int front() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        return values[front];
    }

    public int rear() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
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
            throw new RuntimeException("Queue is empty");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int tempFront = front;
        int tempRear = rear;
        do {
            sb.append(values[tempFront]).append(",");
            tempFront = (tempFront + 1) % values.length;
        } while (tempFront != tempRear);
        sb.append("}").append("front:" + front).append(",rear:" + rear);
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue queue = new Queue(5);
        queue.enqueue(1).enqueue(2).enqueue(3).enqueue(4).enqueue(5);
        System.out.println(queue);
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.dequeue());
        }
        queue.enqueue(6).enqueue(7).enqueue(8);
        System.out.println(queue);
    }

}
