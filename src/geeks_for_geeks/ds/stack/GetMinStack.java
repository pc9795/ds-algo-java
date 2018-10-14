package geeks_for_geeks.ds.stack;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-10-2018 23:18
 **/
public class GetMinStack {
    ArrayDeque<Integer> stack = new ArrayDeque<>();
    ArrayDeque<Integer> minStack = new ArrayDeque<>();

    /**
     * we have to push for equals to only for multiple entries of minimum element.
     *
     * @param x
     */
    public void push(int x) {
        this.stack.push(x);
        if (minStack.isEmpty() || minStack.peek() > x) {
            minStack.push(x);
        }
    }

    public int pop() {
        if (stack.peek() == minStack.peek()) {
            minStack.pop();
        }
        return stack.pop();
    }

    public int getMin() {
        return minStack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        GetMinStack minStack = new GetMinStack();
        minStack.push(10);
        minStack.push(20);
        minStack.push(30);
        System.out.println(minStack.getMin());
        minStack.push(5);
        System.out.println(minStack.getMin());
    }
}
