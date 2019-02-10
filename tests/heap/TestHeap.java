package heap;

import geeks_for_geeks.ds.heap.MinHeap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 10-02-2019 21:15
 * Purpose: TODO:
 **/
class TestHeap {

    @Test
    void testHeapSort() {
        int[] arr = {5, 1, 4, 3, 2, 6, 7, 10, 9, 8};
        MinHeap.heapSort(arr);

        Assertions.assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arr);
    }
}
