package geeks_for_geeks.searching_and_sorting;

import geeks_for_geeks.algo.searching_and_sorting.Sorting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 04-02-2020 00:10
 **/
class TestSorting {

    @Test
    void testSelectionSort() {
        int arr[] = new int[]{5, 4, 3, 2, 1};
        Sorting.selectionSort(arr);
        Assertions.assertArrayEquals(arr, new int[]{1, 2, 3, 4, 5});
    }

    @Test
    void testBubbleSort() {
        int arr[] = new int[]{5, 4, 3, 2, 1};
        Sorting.bubbleSort(arr);
        Assertions.assertArrayEquals(arr, new int[]{1, 2, 3, 4, 5});
    }

    @Test
    void testInsertionSort() {
        int arr[] = new int[]{5, 4, 3, 2, 1};
        Sorting.insertionSort(arr);
        Assertions.assertArrayEquals(arr, new int[]{1, 2, 3, 4, 5});
    }
}
