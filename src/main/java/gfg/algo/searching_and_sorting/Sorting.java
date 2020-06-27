package gfg.algo.searching_and_sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created By: Prashant Chaubey
 * Created On: 23-10-2018 00:03
 **/
public class Sorting {

    /**
     * t=O(n^2)
     * s=O(1)
     * not stable
     * in place
     * Only O(n) swaps good when memory write is costly.
     * Selects a minimum at each iteration and then fills it starting from 0,1,...
     *
     * @param arr input array
     */
    public static void selectionSort(int arr[]) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    /**
     * t=O(n^2) (worst case)
     * =O(n) (best case)
     * s=O(1)
     * stable
     * in place
     * The current largest value will be bubbled to the end at each iteration.
     *
     * @param arr input array
     */
    public static void bubbleSort(int arr[]) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            //Optimization
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * t=O(n^2) (worst case) reverse sorted
     * =O(n) (best case) sorted
     * s=O(1)
     * stable
     * in place
     * online
     * powerful when elements are small or array is almost sorted.
     *
     * @param arr input array
     */
    public static void insertionSort(int arr[]) {
        if (arr == null) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int j = i;
            for (; j > 0 && arr[j - 1] > value; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = value;
        }
    }

    /**
     * T=O(n^2) (worst case) largest/smallest element is chosen as pivot, O(nlogn)
     * not stable
     *
     * @param arr
     */
    public static void quickSort(int arr[]) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty!");
            return;
        }
        quickSortUtil(0, arr.length - 1, arr);
    }

    /**
     * value at partition is at its position.
     *
     * @param low
     * @param high
     * @param arr
     */
    private static void quickSortUtil(int low, int high, int arr[]) {
        if (low > high) {
            return;
        }
        int partition = partition(low, high, arr);
        quickSortUtil(low, partition - 1, arr);
        quickSortUtil(partition + 1, high, arr);
    }

    private static int partition(int low, int high, int arr[]) {
        if (low == high) {
            return low;
        }
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                int temp = arr[++i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[++i];
        arr[i] = pivot;
        arr[high] = temp;
        return i;
    }

    /**
     * T=O(n+k) k is the range of input
     * A=O(n+k)
     * stable
     *
     * @param arr
     * @param maxValueOfInput
     */
    public static void countSort(int arr[], int maxValueOfInput) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty!");
            return;
        }
        int count[] = new int[maxValueOfInput + 1];
        for (int i = 0; i < arr.length; i++) {
            count[arr[i]]++;
        }
        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }
        int output[] = new int[arr.length];
        // to maintain stability.
        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    public static void radixSort(int arr[]) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty!");
            return;
        }
        int max = Arrays.stream(arr).reduce(Integer.MIN_VALUE, Math::max);
        int base = 10;
        for (int place = 0; max / (int) Math.pow(base, place) > 0; place++) {
            countSortByDigit(arr, base, place);
        }
    }

    public static void countSortByDigit(int arr[], int base, int place) {
        int output[] = new int[arr.length];
        int count[] = new int[base];
        for (int i = 0; i < arr.length; i++) {
//            make the digit to be extracted last digit.
            int value = (arr[i] / (int) Math.pow(base, place)) % 10;
            count[value]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

//        To maintain stability.
        for (int i = arr.length - 1; i >= 0; i--) {
            int value = (arr[i] / (int) Math.pow(base, place)) % 10;
            output[count[value] - 1] = arr[i];
            count[value]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }

    }

    /**
     * O(n^2)
     * With large gap more inversion counts will be removed;
     *
     * @param arr
     */
    public static void combSort(int arr[]) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty!");
            return;
        }
        int gap = arr.length;
        boolean swapped = true;
        for (; gap != 1 || swapped; ) {
            gap = (gap * 10) / 13;
            if (gap < 1) {
                gap = 1;
            }
            swapped = false;
            for (int i = 0; i < arr.length - gap; i++) {
                if (arr[i] > arr[i + gap]) {
                    int temp = arr[i];
                    arr[i] = arr[i + gap];
                    arr[i + gap] = temp;
                    swapped = true;
                }
            }
        }
    }

    /**
     * T=O(n+range)
     * <p>
     * We work on range therefore no problem with negative numbers.
     *
     * @param arr
     */
    public static void pigeonHoleSort(int arr[]) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty!");
            return;
        }
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int range = max - min + 1;
        int[] pigeonHoles = new int[range];
        for (int i = 0; i < arr.length; i++) {
            pigeonHoles[arr[i] - min]++;
        }
        for (int i = 0, index = 0; i < pigeonHoles.length; i++) {
            for (; pigeonHoles[i]-- > 0; ) {
                arr[index++] = i + min;
            }
        }
    }

    public static void bucketSort(double arr[]) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty!");
            return;
        }
        ArrayList<Double>[] bucket = new ArrayList[arr.length];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new ArrayList<>();
        }
        for (int i = 0; i < arr.length; i++) {
            bucket[(int) (arr.length * arr[i])].add(arr[i]);
        }
        for (int i = 0; i < bucket.length; i++) {
//            T=O(n) if items are uniformly distributed.
            Collections.sort(bucket[i]);
        }
        for (int i = 0, index = 0; i < bucket.length; i++) {
            for (int j = 0; j < bucket[i].size(); j++) {
                arr[index++] = bucket[i].get(j);
            }
        }

    }

    public static void shellSort(int arr[]) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty!");
            return;
        }
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                int j = i;
                for (; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    /**
     * T=O(n^2)
     * <p>
     * It minimizes the number of memory writes to sort(Each value is either written zero
     * times, if it's already in its correct position, or written one time to its correct
     * position)
     *
     * @param arr
     */
    public static void cycleSort(int arr[]) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty!");
            return;
        }
//        we run till n-2 because till here last element must be in it's position.
        for (int cycleStart = 0; cycleStart < arr.length - 1; cycleStart++) {
            int item = arr[cycleStart];
            int pos = cycleStart;
            for (int i = cycleStart + 1; i < arr.length; i++) {
                if (arr[i] < item) {
                    pos++;
                }
            }
            if (pos == cycleStart) {
                continue;
            }
            if (arr[pos] == item) {
                pos++;
            }
            if (pos != cycleStart) {
                int temp = arr[pos];
                arr[pos] = item;
                item = temp;
            }
            while (pos != cycleStart) {
                pos = cycleStart;
                for (int i = cycleStart + 1; i < arr.length; i++) {
                    if (arr[i] < item) {
                        pos++;
                    }
                }
                if (arr[pos] == item) {
                    pos++;
                }
                if (item != arr[pos]) {
                    int temp = arr[pos];
                    arr[pos] = item;
                    item = temp;
                }
            }

        }
    }
}
