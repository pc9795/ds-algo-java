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
     * @param data value to add
     */
    public void push(int data) {
        this.stack.push(data);
        if (minStack.isEmpty() || minStack.peek() >= data) {
            minStack.push(data);
        }
    }

    public int pop() {
        assert !isEmpty() : "Stack is empty";
        assert stack.peek() != null;

        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        return stack.pop();
    }

    @SuppressWarnings("ConstantConditions")
    public int getMin() {
        assert !isEmpty() : "Stack is empty";
        return minStack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
}