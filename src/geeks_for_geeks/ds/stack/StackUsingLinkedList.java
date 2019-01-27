package geeks_for_geeks.ds.stack;


import geeks_for_geeks.ds.nodes.Node;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 19:01
 **/
public class StackUsingLinkedList implements Stack {
    private Node top;

    @Override
    public void push(int data) {
        Node node = new Node(data);
        node.next = top;
        top = node;
    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("StackUsingArray is empty");
        }
        int data = top.data;
        top = top.next;
        return data;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }


    @Override
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("StackUsingArray is empty");
        }
        return top.data;
    }

}
