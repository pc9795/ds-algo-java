package geeks_for_geeks.ds.stack;

/**
 * Created By: Prashant Chaubey
 * Created On: 27-01-2019 19:17
 * Purpose: TODO:
 **/
public class KStack {
    private int[] arr, top, next;
    private int free;

    public KStack(int noOfStacks, int size) {
        arr = new int[size];
        top = new int[noOfStacks];
        next = new int[size];
        free = 0;
        for (int i = 0; i < size - 1; i++) {
            next[i] = i + 1;
        }
        next[size - 1] = -1;
    }

    public void push(int data, int stack) {
        assert stack >= 0 && stack < top.length;
        if (isFull()) {
            throw new RuntimeException("Stack Overflow");
        }
        int i = free;
        free = next[i];

        next[i] = top[stack];
        top[stack] = i;

        arr[i] = data;
    }

    public int pop(int stack) {
        assert stack >= 0 && stack < top.length;
        if (isEmpty(stack)) {
            throw new RuntimeException("Stack Underflow");
        }
        int i = top[stack];
        top[stack] = next[i];

        next[i] = free;
        free = i;

        return arr[i];
    }

    public boolean isFull() {
        return free == -1;
    }

    public boolean isEmpty(int stack) {
        assert stack >= 0 && stack < top.length;
        return top[stack] == -1;
    }


}
