package geeks_for_geeks.ds.stack;

import geeks_for_geeks.ds.stack.adt.Stack;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 18:51
 **/
public class StackUsingArray implements Stack {
    private int[] values;
    private int top;

    public StackUsingArray(int size) {
        values = new int[size];
        top = -1;
    }

    /**
     * t=O(1)
     *
     * @param data data to add
     */
    @Override
    public void push(int data) {
        assert !isFull() : "Stack is full";

        values[++top] = data;
    }

    /**
     * t=O(1)
     *
     * @return top of the stack
     */
    @Override
    public int pop() {
        assert !isEmpty() : "Stack is empty";
        return values[top--];
    }

    /**
     * t=O(1)
     *
     * @return true if stack is empty
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    private boolean isFull() {
        return top == values.length - 1;
    }

    /**
     * t=O(1)
     *
     * @return top of the stack
     */
    @Override
    public int peek() {
        assert !isEmpty() : "Stack is empty";
        return values[top];
    }
}