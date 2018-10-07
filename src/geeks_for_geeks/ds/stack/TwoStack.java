package geeks_for_geeks.ds.stack;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-10-2018 02:54
 **/
public class TwoStack {
    int[] values;
    int top1;
    int top2;

    public TwoStack() {
        values = new int[10];
        top1 = -1;
        top2 = values.length;
    }

    public TwoStack(int size) {
        values = new int[size];
        top1 = -1;
        top2 = values.length;
    }

    public boolean isEmpty() {
        return top1 == -1 && top2 == values.length;
    }

    public boolean isFull() {
        return top1 + 1 == top2 || top1 == values.length - 1 || top2 == 0;
    }

    public TwoStack push1(int value) {
        if (isFull()) {
            throw new RuntimeException("Stack is Full!");
        }
        values[++top1] = value;
        return this;
    }

    public TwoStack push2(int value) {
        if (isFull()) {
            throw new RuntimeException("Stack is Full!");
        }
        values[--top2] = value;
        return this;
    }

    public int pop1() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is Empty!");
        }
        return values[top1--];
    }

    public int pop2() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is Empty!");
        }
        return values[top2++];
    }
}
