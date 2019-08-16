package geeks_for_geeks.ds.stack;

import geeks_for_geeks.ds.stack.adt.Stack;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 18:51
 **/
public class StackUsingArray implements Stack {
    private int[] values;
    private int top;

    public StackUsingArray() {
        values = new int[10];
        top = -1;
    }

    public StackUsingArray(int size) {
        values = new int[size];
        top = -1;
    }

    /**
     * t=O(1)
     *
     * @param data
     */
    @Override
    public void push(int data) {
        if (isFull()) {
            throw new RuntimeException("StackUsingArray is full");
        }
        values[++top] = data;
    }

    /**
     * t=O(1)
     *
     * @return
     */
    @Override
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("StackUsingArray is empty");
        }
        return values[top--];
    }

    /**
     * t=O(1)
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == values.length - 1;
    }

    /**
     * t=O(1)F
     *
     * @return
     */
    @Override
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("StackUsingArray is empty");
        }
        return values[top];
    }

}






























