package geeks_for_geeks.ds.heap;

import geeks_for_geeks.ds.array.Array;
import geeks_for_geeks.ds.heap.adt.Heap;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 15:03
 **/
public class MinHeap implements Heap {
    public int[] values;
    public int size;

    public MinHeap() {
        values = new int[10];
    }

    public MinHeap(int capacity) {
        values = new int[capacity];
    }

    public MinHeap(int arr[], int size) {
        values = Arrays.copyOf(arr, size);
        this.size = size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == values.length;
    }

    public static int parent(int index) {
        return (index - 1) / 2;
    }

    public static int left(int index) {
        return (2 * index) + 1;
    }

    public static int right(int index) {
        return (2 * index) + 2;
    }

    /**
     * t=O(1)
     *
     * @return
     */
    @Override
    public int getMin() {
        return values[0];
    }

    /**
     * t=O(logn)
     *
     * @return
     */
    @Override
    public int extractMin() {
        if (isEmpty()) {
            throw new RuntimeException("Heap is empty");
        }
        if (size == 1) {
            size--;
            return getMin();
        }
        int returnedValue = values[0];
        values[0] = values[--size];
        precolateDown(0, values, size);
        return returnedValue;
    }

    private static void precolateDown(int index, int values[], int size) {
        int left = left(index);
        int right = right(index);
        int minIndex = index;
        if (left < size && values[left] < values[minIndex]) {
            minIndex = left;
        }
        if (right < size && values[right] < values[minIndex]) {
            minIndex = right;
        }

        if (minIndex != index) {
            int temp = values[minIndex];
            values[minIndex] = values[index];
            values[index] = temp;
            precolateDown(minIndex, values, size);
        }
    }

    private static void precolateUp(int index, int[] values, int size) {
        int parentIndex = parent(index);
        if (parentIndex < 0) {
            return;
        }
        if (values[parentIndex] < values[index]) {
            int temp = values[index];
            values[index] = values[parentIndex];
            values[parentIndex] = temp;
            precolateUp(parentIndex, values, size);
        }
    }


    /**
     * T=O(log n)
     * we believe that the newVal is less than previous value therefore we are moving in up direction.
     *
     * @param index
     * @param newVal
     */
    @Override
    public void decreaseKey(int index, int newVal) {
        if (values[index] >= newVal) {
            throw new RuntimeException("newVal must be less than current value");
        }
        values[index] = newVal;
        precolateUp(index, values, size);
    }

    /**
     * t=O(logn)
     *
     * @param data
     */
    @Override
    public Heap insert(int data) {
        if (isFull()) {
            throw new RuntimeException("Heap is full");
        }
        values[size++] = data;
        precolateUp(size - 1, values, size);
        return this;
    }

    /**
     * t=O(logn)
     *
     * @param i
     */
    @Override
    public void delete(int i) {
        decreaseKey(i, Integer.MIN_VALUE);
        extractMin();
    }

    /**
     * t=O(n)
     *
     * @param arr
     * @param size
     * @return
     */
    private static MinHeap heapify(int arr[], int size) {
        if (arr.length == 0) {
            throw new RuntimeException("Array is empty!");
        }
        for (int i = arr.length / 2; i >= 0; i--) {
//            If we use precloateUp then in the case of bigger parent we have to move it downward, and after this it
//           again have to be moved down so we have to move up and then move down again.
//           But if we precolateDown, we are sure that bigger element are moving down continuously.
            precolateDown(i, arr, size);
        }
        return new MinHeap(arr, size);
    }

    /**
     * t=O(nlogn)
     * not stable.
     *
     * @param arr
     */
    public static void heapSort(int arr[]) {
//        It should be implemented using max-geeks_for_geeks.heap in which min element is replaced with last element continuously.
//        todo implement using max-geeks_for_geeks.heap to remove extra space.
        MinHeap heap = heapify(arr, arr.length);

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = heap.extractMin();
        }
        arr = new Array(arr).reverse().values;

    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MinHeap{");
        for (int i = 0; i < size; i++) {
            sb.append(values[i]).append(",");
        }
        sb.append("}");
        return sb.toString();
    }

}
