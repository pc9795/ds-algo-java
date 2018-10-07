package geeks_for_geeks.ds.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-10-2018 18:39
 **/
public class HeapOperations {

    //    T=O(k+(n-k)log k)
    public static Integer[] kLargestElements(int arr[], int k) {
        if (arr.length == 0) {
            throw new RuntimeException("Array is empty!");
        }
        if (k > arr.length) {
            throw new RuntimeException("Invalid input: k can't be greater than array's length");
        }
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

    public static void main(String[] args) {
        System.out.println(Arrays.toString(kLargestElements(new int[]{1, 23, 12, 9, 30, 2, 50}, 3)));
    }
}
