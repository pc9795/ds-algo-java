package geeks_for_geeks.queue;

import geeks_for_geeks.ds.queue.Applications;
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
}
