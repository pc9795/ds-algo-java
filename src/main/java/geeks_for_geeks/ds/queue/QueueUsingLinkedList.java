package geeks_for_geeks.ds.queue;

import geeks_for_geeks.ds.nodes.Node;
import geeks_for_geeks.ds.queue.adt.Queue;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 23:43
 **/
public class QueueUsingLinkedList implements Queue {
    private Node front;
    private Node rear;

    /**
     * t=O(1)
     *
     * @param data data to add
     * @return calling instance
     */
    @Override
    public Queue enqueue(int data) {
        Node node = new Node(data);
        if (isEmpty()) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        return this;
    }

    /**
     * It will remove the data in the front
     * t=O(1)
     *
     * @return data at the front of queue
     */
    @Override
    public int dequeue() {
        assert !isEmpty() : "Queue is empty";
        int data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    /**
     * t=O(1)
     *
     * @return data at the front of the queue.
     */
    @Override
    public int front() {
        assert !isEmpty() : "Queue is empty";
        return front.data;
    }

    /**
     * t=O(1)
     *
     * @return data at the rear of the queue
     */
    @Override
    public int rear() {
        assert !isEmpty() : "Queue is empty";
        return rear.data;
    }

    /**
     * t=O(1)
     *
     * @return true if empty
     */
    public boolean isEmpty() {
        return front == null;
    }
}
