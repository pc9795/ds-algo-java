package geeks_for_geeks.ds.heap;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-10-2018 18:39
 **/
public class HeapAppllications {

    /**
     * T=O(k+(n-k)log k)
     *
     * @param arr
     * @param k
     * @return
     */
    public static Integer[] kLargestElements(int arr[], int k) {
        assert arr.length != 0 && k <= arr.length;

//        Min heap.
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        //       k
        for (int i = 0; i < k; i++) {
            heap.add(arr[i]);
        }
//        n-k
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > heap.peek()) {
                heap.poll();
//                log k
                heap.add(arr[i]);
            }
        }
        return heap.toArray(new Integer[k]);
    }

    /**
     * t=O(k+(n-k)*logk)
     *
     * @param arr
     * @param k
     * @return
     */
    public static int[] sortNearlySortedArray(int arr[], int k) {
        assert k >= 1 && k < arr.length;

        PriorityQueue<Integer> heap = new PriorityQueue<>();

//        k
        for (int i = 0; i <= k; i++) {
            heap.add(arr[i]);
        }

//        (n-k)*logk
        for (int i = k + 1; i < arr.length; i++) {
//            if k is 0 then it will fail here.
            arr[i - k - 1] = heap.poll();
            heap.add(arr[i]);
        }

//        k*logk
        for (int i = arr.length - 1 - k - 1 + 1; i < arr.length; i++) {
            arr[i] = heap.poll();
        }

        return arr;
    }
}
