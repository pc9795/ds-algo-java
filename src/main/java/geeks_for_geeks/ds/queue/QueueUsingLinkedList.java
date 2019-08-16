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

    @Override
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        int data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    @Override
    public int front(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        return front.data;
    }

    @Override
    public int rear(){
        if(isEmpty()){
            throw new RuntimeException("Queue is empty");
        }
        return rear.data;
    }

    public boolean isEmpty() {
        return front == null;
    }
}
