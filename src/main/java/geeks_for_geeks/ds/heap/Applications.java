package geeks_for_geeks.ds.heap;

import java.util.PriorityQueue;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-10-2018 18:39
 **/
public class Applications {

    /**
     * T=O(k+(n-k)*log k)
     * We can also create a max heap and call extract max k times. O(k*log n)
     *
     * @param arr input array
     * @param k   no of largest elements needed
     * @return k largest elements
     */
    @SuppressWarnings("ConstantConditions")
    public static int[] kLargestElements(int arr[], int k) {
        assert arr.length != 0 && k <= arr.length;

        //Min heap.
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // O(k)
        for (int i = 0; i < k; i++) {
            heap.add(arr[i]);
        }
        // O(n-k)
        for (int i = k; i < arr.length; i++) {
            if (arr[i] > heap.peek()) {
                heap.poll();
                // O(log k)
                heap.add(arr[i]);
            }
        }
        Integer[] temp = new Integer[k];
        temp = heap.toArray(temp);
        int[] kLargest = new int[k];
        for (int i = 0; i < k; i++) {
            kLargest[i] = temp[i];
        }
        return kLargest;
    }

    /**
     * t=O(k+(n-k)*log k)
     * We can also use a BST but it need extra pointers for left, right and also for maintaining min/max for O(1) getMin()
     *
     * @param arr nearly sorted input array
     * @param k   maximum distance of an element from its actual position from a sorted array
     */
    @SuppressWarnings("ConstantConditions")
    public static void sortNearlySortedArray(int arr[], int k) {
        assert k >= 1 && k < arr.length;

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // O(k)
        for (int i = 0; i <= k; i++) {
            heap.add(arr[i]);
        }
        // O((n-k)*log k)
        for (int i = k + 1; i < arr.length; i++) {
            // if k is 0 then it will fail here.
            arr[i - k - 1] = heap.poll();
            heap.add(arr[i]);
        }

        // O(k*logk)
        for (int i = arr.length - 1 - k - 1 + 1; i < arr.length; i++) {
            arr[i] = heap.poll();
        }
    }
}
