package gfg.ds.advanced.fenwick_tree;

import gfg.ds.advanced.DiffArray;

public class RangeUpdatePointQuery {
  private final FenwickTree bit;

  public RangeUpdatePointQuery(int[] arr) {
    DiffArray diffArray = new DiffArray(arr, arr.length);
    bit = new FenwickTree(diffArray.values, arr.length);
  }

  /** t=O(log n) */
  public void rangeUpdate(int lowerBound, int upperBound, int increment) {
    // Incrementing indices for Fenwick tree operations which are 1 based.
    bit.update(lowerBound + 1, increment);
    bit.update(upperBound + 1 + 1, -increment);
  }

  /** t=O(log n) */
  public int get(int index) {
    // Incrementing indices for Fenwick tree operations which are 1 based.
    return bit.query(index + 1);
  }

  /** t=O(n * log n) */
  public int[] getArray() {
    // Bit stores 1 extra the original array length. 0th index is for dummy node.
    int[] arr = new int[bit.n];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = get(i);
    }
    return arr;
  }
}
