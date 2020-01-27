package geeks_for_geeks.ds.stack;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-10-2018 02:54
 **/
public class TwoStack {
    private int[] values;
    private int top1;
    private int top2;

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
        return top1 + 1 == top2;
    }

    /**
     * t=O(1)
     *
     * @param value value to add
     * @return calling instance
     */
    public TwoStack push1(int value) {
        assert isFull() : "Stack is full";
        values[++top1] = value;
        return this;
    }

    /**
     * t=O(1)
     *
     * @param value value to add
     * @return calling instance
     */
    public TwoStack push2(int value) {
        assert isFull() : "Stack is full";
        values[--top2] = value;
        return this;
    }

    /**
     * t=O(1)
     *
     * @return top of the stack
     */
    public int pop1() {
        assert !isEmpty() : "Stack is empty";
        return values[top1--];
    }

    /**
     * t=O(1)
     *
     * @return top of the stack
     */
    public int pop2() {
        assert !isEmpty() : "Stack is empty";
        return values[top2++];
    }
}