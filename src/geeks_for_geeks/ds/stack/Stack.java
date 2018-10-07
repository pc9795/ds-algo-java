package geeks_for_geeks.ds.stack;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 18:51
 **/
public class Stack {
    int[] values;
    int top;

    public Stack() {
        values = new int[10];
        top = -1;
    }

    public Stack(int size) {
        values = new int[size];
        top = -1;
    }

    public void push(int data) {
        if (isFull()) {
            throw new RuntimeException("Stack is full");
        }
        values[++top] = data;
    }

    public Object pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return values[top--];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == values.length - 1;
    }

    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return values[top];
    }

}






























