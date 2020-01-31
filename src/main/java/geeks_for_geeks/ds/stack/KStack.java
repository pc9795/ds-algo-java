package geeks_for_geeks.ds.stack;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 27-01-2019 19:17
 **/
public class KStack {
    private int[] arr, top, next;
    private int free;

    public KStack(int noOfStacks, int size) {
        arr = new int[size];
        top = new int[noOfStacks];
        Arrays.fill(top, -1);
        next = new int[size];
        free = 0;
        for (int i = 0; i < size - 1; i++) {
            next[i] = i + 1;
        }
        next[size - 1] = -1;
    }

    /**
     * t=O(1)
     *
     * @param data  data to add
     * @param stack no of stack
     */
    public void push(int stack, int data) {
        assert stack >= 0 && stack < top.length;
        assert !isFull() : "Stack is full";

        int i = free;
        free = next[i];

        next[i] = top[stack];
        top[stack] = i;

        arr[i] = data;
    }

    /**
     * t=O(1)
     *
     * @param stack no of stack
     * @return popped value
     */
    public int pop(int stack) {
        assert stack >= 0 && stack < top.length;
        assert !isEmpty(stack) : "Stack is empty";

        int i = top[stack];
        top[stack] = next[i];

        next[i] = free;
        free = i;

        return arr[i];
    }

    /**
     * @return true if no space to add data
     */
    public boolean isFull() {
        return free == -1;
    }

    /**
     * t=O(1)
     *
     * @param stack no of stack
     * @return true if stack is empty
     */
    public boolean isEmpty(int stack) {
        assert stack >= 0 && stack < top.length;
        return top[stack] == -1;
    }
}