package geeks_for_geeks.algo.searching_and_sorting;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-10-2018 08:24
 **/
public class Searching {

    /**
     * T=O(n)
     *
     * @param arr
     * @param data
     * @return
     */
    public static int linearSearch(int arr[], int data) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty!");
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == data) {
                return data;
            }
        }
        System.out.println("Element not found!");
        return -1;
    }

    /**
     * T(n)=T(n/2)+C
     * T=O(log n)
     *
     * @param arr
     * @param data
     * @return
     */
    public static int binarySearch(int arr[], int data) {
        Arrays.sort(arr);
        int low = 0;
        int high = arr.length - 1;
        int middle;
        for (; low < high; ) {
            middle = (low + high) / 2;
            if (arr[middle] == data) {
                return data;
            } else if (arr[middle] < data) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        System.out.println("Element not found!");
        return -1;
    }
}
