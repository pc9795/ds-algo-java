package geeks_for_geeks.ds.heap;

import geeks_for_geeks.ds.heap.BinaryMinHeap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 10-02-2019 21:15
 **/
class TestBinaryMinHeap {

    @Test
    void testHeapSort() {
        int[] arr = {5, 1, 4, 3, 2, 6, 7, 10, 9, 8};
        BinaryMinHeap.heapSort(arr);
        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arr);
    }

    @Test
    void testHeapOperations() {
        BinaryMinHeap heap = new BinaryMinHeap(5);
        heap.insert(5).insert(4).insert(3).insert(2).insert(1);
        assert heap.isFull();

        /*
         *      1
         *     / \
         *    2   3
         *   / \
         *  4   5
         */
        assert heap.getMin() == 1;

        int ans = heap.extractMin();
        /*
         *      2
         *     / \
         *    3   4
         *   /
         *  5
         */
        assert ans == 1;
        assert heap.getMin() == 2;

        heap.decreaseKey(1, 0);
        /*
         *      0
         *     / \
         *    2   4
         *   /
         *  5
         */
        assert heap.getMin() == 0;

        heap.delete(3);
        /*
         *      0
         *     / \
         *    2   4
         */
        ans = heap.extractMin();
        assert ans == 0;
        ans = heap.extractMin();
        assert ans == 2;
        ans = heap.extractMin();
        assert ans == 4;

        assert heap.isEmpty();
    }
}
