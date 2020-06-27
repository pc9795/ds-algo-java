package gfg.ds.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 03-02-2020 23:45
 **/
class TestApplications {

    @Test
    void testKLargestElements() {
        int arr[] = {1, 23, 12, 9, 30, 2, 50};
        int k = 3;
        int expected[] = {50, 30, 23};
        int ans[] = Applications.kLargestElements(arr, k);

        // We don't consider ordering.
        // Can check for any library method for this.
        for (int elem : ans) {
            boolean found = false;
            for (int expectedElem : expected) {
                if (elem == expectedElem) {
                    found = true;
                    break;
                }
            }
            assert found;
        }
    }

    @Test
    void testSortNearlySortedArray() {
        int arr[] = {6, 5, 3, 2, 8, 10, 9};
        Applications.sortNearlySortedArray(arr, 3);
        Assertions.assertArrayEquals(arr, new int[]{2, 3, 5, 6, 8, 9, 10});
    }
}
