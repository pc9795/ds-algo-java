package geeks_for_geeks.ds.queue;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 07-10-2018 23:22
 **/
public class QueueUsingStacks {
    ArrayDeque<Integer> insertionStack=new ArrayDeque<>();
    ArrayDeque<Integer> queryStack=new ArrayDeque<>();

    public QueueUsingStacks enqueue(int data) {
        insertionStack.push(data);
        return this;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty1");
        }
        if (queryStack.isEmpty()) {
            deloadInsertionStack();
        }
        return queryStack.pop();
    }

    public int front() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty1");
        }
        if (queryStack.isEmpty()) {
            deloadInsertionStack();
        }
        return queryStack.peek();
    }

    public int rear() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty1");
        }
        return insertionStack.peek();
    }

    private void deloadInsertionStack() {
        for (; !insertionStack.isEmpty(); ) {
            queryStack.push(insertionStack.pop());
        }
    }

    public boolean isEmpty() {
        return insertionStack.isEmpty() && queryStack.isEmpty();
    }

    public static void main(String[] args) {
        QueueUsingStacks queue = new QueueUsingStacks();
        queue.enqueue(1).enqueue(2).enqueue(3).enqueue(4);
        for (; !queue.isEmpty(); ) {
            System.out.println(queue.dequeue());
        }
    }
}
