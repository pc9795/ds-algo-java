package geeks_for_geeks.ds.stack;

import geeks_for_geeks.ds.nodes.DNode;
import geeks_for_geeks.exceptions.DSException;

/**
 * Created By: Prashant Chaubey
 * Created On: 27-01-2019 18:43
 * Purpose: TODO:
 **/
public class GetMiddleStack implements Stack {
    private DNode top;
    private DNode mid;
    private int size;

    @Override
    public void push(int data) {
        DNode node = new DNode(data);
//        new node is always added to the head of the stack.
        node.prev = null;
        node.next = top;
        if (node.next != null) {
            node.next.prev = node;
        }
        top = node;
        size++;
        if (size == 1) {
            mid = top;
        } else if (size % 2 == 1) {
//          If now total elements are odd then move up.
            mid = mid.prev;
        }

    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new DSException("Stack underflow");
        }
        int val = top.data;
        top = top.next;
        if (top != null) {
            top.prev = null;
        }
        size--;
        if (size == 0) {
            mid = top = null;
        } else if (size % 2 == 0) {
//            If now total elements are even then move down.
            mid = mid.next;
        }

        return val;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new DSException("Stack underflow");
        }
        return top.data;
    }

    public int peekMiddle() {
        if (isEmpty()) {
            throw new DSException("Stack underflow");
        }
        return mid.data;
    }

    public int popMiddle() {
        if (isEmpty()) {
            throw new DSException("Stack underflow");
        }
        int val = mid.data;
        DNode temp = null;
        if (size % 2 == 1) {
            temp = mid.next;
        } else {
            temp = mid.prev;
        }
        if (mid.prev != null) {
            mid.prev.next = mid.next;
        }
        if (mid.next != null) {
            mid.next.prev = mid.prev;
        }
        size--;
        if (size == 0) {
            mid = top = null;
        } else {
            mid = temp;
        }

        return val;
    }

}
