package geeks_for_geeks.ds.queue;

import geeks_for_geeks.ds.linked_list.Node;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 23:43
 **/
public class QueueUsingLinkedList {
    public Node front;
    public Node rear;

    public void enqueue(int data) {
        Node node = new Node(data);
        if (isEmpty()) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }

    }

    public Object dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        Object data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }
}
