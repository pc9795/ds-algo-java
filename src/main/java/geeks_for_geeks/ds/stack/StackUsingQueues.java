package geeks_for_geeks.ds.stack;

import geeks_for_geeks.ds.stack.adt.Stack;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 27-01-2019 17:54
 **/
public class StackUsingQueues implements Stack {
    private ArrayDeque<Integer> q1;
    private ArrayDeque<Integer> q2;
    private final boolean costlyPush;

    public StackUsingQueues(boolean costlyPush) {
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
        this.costlyPush = costlyPush;
    }

    @Override
    public void push(int data) {
        if (costlyPush) {
            pushCostly(data);
        } else {
            pushEfficient(data);
        }
    }

    private void pushCostly(int data) {
        q2.add(data);
        while (!q1.isEmpty()) {
            q2.add(q1.poll());
        }
        // swapping references.
        ArrayDeque<Integer> temp = q2;
        q2 = q1;
        q1 = temp;
    }

    private void pushEfficient(int data) {
        q1.add(data);
    }

    @Override
    public int pop() {
        if (costlyPush) {
            return popEfficient();
        } else {
            return popCostly();
        }
    }

    private int popCostly() {
        while (q1.size() != 1) {
            q2.add(q1.poll());
        }
        int val = q1.pop();
        // swapping references.
        ArrayDeque<Integer> temp = q2;
        q2 = q1;
        q1 = temp;
        return val;
    }

    private int popEfficient() {
        assert !q1.isEmpty() : "Stack is empty";
        return q1.poll();
    }

    @Override
    public boolean isEmpty() {
        return q1.isEmpty();
    }

    @Override
    public int peek() {
        int val = -1;
        if (costlyPush) {
            val = popEfficient();
            pushCostly(val);
        } else {
            val = popCostly();
            pushEfficient(val);
        }
        return val;
    }
}