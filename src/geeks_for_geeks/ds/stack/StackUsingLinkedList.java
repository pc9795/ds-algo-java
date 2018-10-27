package geeks_for_geeks.ds.stack;


import geeks_for_geeks.ds.nodes.Node;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 19:01
 **/
public class StackUsingLinkedList {
    Node top;

    public void push(int data) {
        Node node = new Node(data);
        node.next = top;
        top = node;
    }

    public Object pop() {
        if (isEmpty()) {
            return new RuntimeException("Stack is empty");
        }
        Object data = top.data;
        top = top.next;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return top.data;
    }

}
