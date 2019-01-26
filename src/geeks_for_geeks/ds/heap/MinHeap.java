package geeks_for_geeks.ds.heap;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 15:03
 **/
public class MinHeap {
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

    //    T=O(1)
    public int getMin() {
        return values[0];
    }

    //    T=O(log n)
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

    public static void precolateDown(int index, int values[], int size) {
        int left = left(index);
        int right = right(index);
        int min = index;
        if (left < size && values[left] < values[min]) {
            min = left;
        }
        if (right < size && values[right] < values[min]) {
            min = right;
        }

        if (min != index) {
            int temp = values[min];
            values[min] = values[index];
            values[index] = temp;
            precolateDown(min, values, size);
        }
    }

    public static void precolateUp(int index, int[] values, int size) {
        int parent = parent(index);
        if (parent < 0) {
            return;
        }
        if (values[parent] < values[index]) {
            int temp = values[index];
            values[index] = values[parent];
            values[parent] = temp;
            precolateUp(parent, values, size);
        }
    }


    /**
     * T=O(log n)
     * we believe that the newVal is less than previous value therefore we are moving in up direction.
     *
     * @param i
     * @param newVal
     */
    public void decreaseKey(int i, int newVal) {
        if (values[i] >= newVal) {
            throw new RuntimeException("newVal must be less than current value");
        }
        values[i] = newVal;
        precolateUp(i, values, size);
    }

    //    T=O(log n)
    public void insert(int data) {
        if (isFull()) {
            throw new RuntimeException("Heap is full");
        }
        values[size++] = data;
        precolateUp(size - 1, values, size);
    }

    //    T=O(log n)
    public void delete(int i) {
        decreaseKey(i, Integer.MIN_VALUE);
        extractMin();
    }

    //    T=O(n)
    public static MinHeap heapify(int arr[], int size) {
        if (arr.length == 0) {
            throw new RuntimeException("Array is empty!");
        }
        for (int i = arr.length / 2; i >= 0; i--) {
            precolateDown(i, arr, size);
        }
        return new MinHeap(arr, size);
    }

    public static void heapSort(int arr[]) {
        MinHeap heap = heapify(arr, arr.length);
        for (; heap.size != 0; ) {
            System.out.print(heap.extractMin() + "=>");
        }
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

    public static void main(String[] args) {
        int arr[] = {12, 11, 13, 5, 6, 7};
        heapSort(arr);
    }
}
