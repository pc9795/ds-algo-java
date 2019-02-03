package geeks_for_geeks.ds.queue;

import geeks_for_geeks.ds.array.Array;

import java.util.Arrays;

public class KQueue {
    private int[] arr, front, rear, next;
    private int free;

    public KQueue(int queues, int size) {
        arr = new int[size];
        front = new int[queues];
        rear = new int[queues];
        next = new int[size];
        free = 0;
        Arrays.fill(front, -1);
        Arrays.fill(rear, -1);
        for (int i = 0; i < size - 1; i++) {
            next[i] = i + 1;
        }
    }

    public void enqueue(int stackNo, int data) {
        if (isFull()) {
            throw new RuntimeException("Queue overflow!");
        }
        int toBeInsertedIndex = free;
        free = next[free];

        if (front[stackNo] == -1) {
            front[stackNo] = toBeInsertedIndex;
        } else {
            next[rear[stackNo]] = toBeInsertedIndex;
        }
        next[toBeInsertedIndex] = -1;
        rear[stackNo] = toBeInsertedIndex;
        arr[toBeInsertedIndex] = data;
    }

    public int dequeue(int stackNo) {
        if (isEmpty(stackNo)) {
            throw new RuntimeException("Queue underflow!");
        }
        int toBeDeletedIndex = front[stackNo];

        next[toBeDeletedIndex] = free;
        free = toBeDeletedIndex;

        front[stackNo] = next[toBeDeletedIndex];

        if (front[stackNo] == -1) {
            rear[stackNo] = -1;
        }

        return arr[toBeDeletedIndex];
    }

    public int front(int stackNo) {
        if (isEmpty(stackNo)) {
            throw new RuntimeException("Queue underflow!");
        }
        return front[stackNo];
    }

    public int rear(int stackNo) {
        if (isEmpty(stackNo)) {
            throw new RuntimeException("Queue underflow!");
        }

        return rear[stackNo];
    }

    public boolean isFull() {
        return free == -1;
    }

    public boolean isEmpty(int stackNo) {
        return front[stackNo] == -1;
    }

}
