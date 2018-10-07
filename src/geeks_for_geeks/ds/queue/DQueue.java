package geeks_for_geeks.ds.queue;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-10-2018 05:20
 **/
public class DQueue extends Queue {

    public DQueue() {
        super();
    }

    public DQueue(int size) {
        super(size);
    }

    public DQueue insertFront(int value) {
        if (isFull()) {
            throw new RuntimeException("Queue is Full!");
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
            throw new RuntimeException("Queue is Empty!");
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

    public static void main(String[] args) {
        DQueue queue = new DQueue(5);
        queue.insertFront(1).insertFront(2).insertFront(3).insertFront(4)
                .insertFront(5);
        System.out.println(queue);
        for (int i = 0; i < 5; i++) {
            System.out.println(queue.dequeue());
        }
    }
}
