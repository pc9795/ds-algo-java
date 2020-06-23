package geeks_for_geeks.ds.queue;

import geeks_for_geeks.ds.queue.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestQueue {
    @Test
    void testSlidingWindowMaximum() {
        int[] arr = {8, 5, 10, 7, 9, 4, 5, 12, 90, 13};
        int[] expected = {10, 10, 10, 9, 12, 90, 90};
        Assertions.assertArrayEquals(expected, Applications.slidingWindowMaximum(arr, 4));
    }

    @Test
    void testGenerateBinaryNums() {
        Assertions.assertArrayEquals(new String[]{"1", "10", "11", "100", "101"}, Applications.generateBinaryNums(5));
    }

    @Test
    void testQueueUsingLinkedListOperations() {
        QueueUsingLinkedList q = new QueueUsingLinkedList();
        q.enqueue(1).enqueue(2).enqueue(3);

        assert q.front() == 1;
        assert q.rear() == 3;
        int data = q.dequeue();
        assert data == 1;

        assert q.front() == 2;
        data = q.dequeue();
        assert data == 2;

        assert q.front() == 3;
        data = q.dequeue();
        assert data == 3;

        assert q.isEmpty();
    }

    @Test
    void testDequeueUsingArrayOperations() {
        DQueueUsingArray dq = new DQueueUsingArray(10);
        dq = dq.insertFront(1);
        assert dq.front() == 1;
        dq = dq.insertFront(2);
        assert dq.front() == 2;

        int data = dq.deleteLast();
        assert data == 1;
        data = dq.deleteLast();
        assert data == 2;
    }

    @Test
    void testKQueueOperations() {
        KQueue kq = new KQueue(2, 6);
        kq.enqueue(0, 1);
        kq.enqueue(0, 2);
        kq.enqueue(1, 11);
        kq.enqueue(1, 12);

        assert kq.front(0) == 1;
        assert kq.front(1) == 11;

        assert kq.rear(0) == 2;
        assert kq.rear(1) == 12;

        int data = kq.dequeue(0);
        assert data == 1;
        data = kq.dequeue(1);
        assert data == 11;
    }

    @Test
    void tetQueueUsingStacks() {
        QueueUsingStacks q = new QueueUsingStacks(false);
        q.enqueue(1).enqueue(2).enqueue(3);

        assert q.front() == 1;
        assert q.rear() == 3;

        int data = q.dequeue();
        assert data == 1;
        data = q.dequeue();
        assert data == 2;
        data = q.dequeue();
        assert data == 3;

        assert q.isEmpty();
    }

    @Test
    void testGetTour() {
        Applications.PetrolPump[] arr = {new Applications.PetrolPump(6, 4),
                new Applications.PetrolPump(3, 6),
                new Applications.PetrolPump(7, 3)};
        assert Applications.getTour(arr) == 2;
    }
}