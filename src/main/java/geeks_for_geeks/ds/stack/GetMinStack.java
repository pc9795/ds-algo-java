package geeks_for_geeks.ds.stack;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-10-2018 23:18
 **/
public class GetMinStack {
    private ArrayDeque<Integer> stack = new ArrayDeque<>();
    private ArrayDeque<Integer> minStack = new ArrayDeque<>();

    /**
     * we have to push for equals to only for multiple entries of minimum element.
     *
     * @param x
     */
    public void push(int x) {
        this.stack.push(x);
        if (minStack.isEmpty() || minStack.peek() >= x) {
            minStack.push(x);
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        return stack.pop();
    }

    public int getMin() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return minStack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

}
