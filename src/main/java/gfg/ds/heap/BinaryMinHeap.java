package gfg.ds.heap;

import gfg.ds.array.Array;
import gfg.ds.heap.adt.MinHeap;

import java.util.Arrays;

/** @noinspection WeakerAccess */
public class BinaryMinHeap implements MinHeap {
  public int[] values;
  public int size;

  public BinaryMinHeap(int size) {
    values = new int[size];
  }

  private BinaryMinHeap(int arr[], int size) {
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
   * @return the root of the tree
   */
  @Override
  public int getMin() {
    assert !isEmpty() : "Heap is empty";

    return values[0];
  }

  /**
   * t=O(log n)
   *
   * @return remove and return the root of the tree
   */
  @Override
  public int extractMin() {
    assert !isEmpty() : "Heap is empty";

    if (size == 1) {
      size--;
      return values[0];
    }
    int returnedValue = values[0];
    values[0] = values[--size];
    precolateDown(0);
    return returnedValue;
  }

  /** t=O(log n); heap is complete binary tree */
  private void precolateDown(int index) {
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
      precolateDown(minIndex);
    }
  }

  /** t=O(log n) */
  private void precolateUp(int index) {
    int parentIndex = parent(index);
    if (parentIndex < 0) {
      return;
    }
    if (values[index] < values[parentIndex]) {
      int temp = values[index];
      values[index] = values[parentIndex];
      values[parentIndex] = temp;
      precolateUp(parentIndex);
    }
  }

  /**
   * t=O(log n) we believe that the newVal is less than previous value therefore we are moving in up
   * direction.
   */
  @Override
  public void decreaseKey(int index, int newVal) {
    assert index >= 0 && index < size;
    assert values[index] > newVal : "New value should be less than original value";

    values[index] = newVal;
    precolateUp(index);
  }

  /** t=O(log n) */
  @Override
  public BinaryMinHeap insert(int data) {
    assert !isFull() : "Heap is full";

    values[size++] = data;
    precolateUp(size - 1);
    return this;
  }

  /** t=O(log n) */
  @Override
  public void delete(int i) {
    decreaseKey(i, Integer.MIN_VALUE);
    extractMin();
  }

  /** t=O(n) */
  private static BinaryMinHeap heapify(int arr[], int size) {
    assert size >= 0 && size <= arr.length;

    if (size == 0) {
      return new BinaryMinHeap(0);
    }
    BinaryMinHeap heap = new BinaryMinHeap(arr, size);
    for (int i = arr.length / 2; i >= 0; i--) {
      // If we use `precloateUp` then in the case of bigger parent we have to move it further
      // downward as it can
      // go till the last level(we don't know). However,if we use `precolateDown`, we are sure that
      // bigger element
      // are moving down continuously.
      heap.precolateDown(i);
    }
    return heap;
  }

  /** t=O(n*log n) not stable. in place */
  public static void heapSort(int arr[]) {
    BinaryMinHeap heap = heapify(arr, arr.length);
    for (int i = arr.length - 1; i >= 0; i--) {
      arr[i] = heap.extractMin();
    }
    // It should be implemented using max-heap.
    new Array(arr).reverse();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("BinaryMinHeap{");
    for (int i = 0; i < size; i++) {
      sb.append(values[i]).append(",");
    }
    sb.append("}");
    return sb.toString();
  }
}
