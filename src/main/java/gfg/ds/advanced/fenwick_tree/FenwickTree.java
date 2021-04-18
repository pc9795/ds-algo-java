package gfg.ds.advanced.fenwick_tree;

/**
 * NOTE: Index are 1-based
 */
public class FenwickTree {
  public int[] values;
  public int n;

  public FenwickTree(int[] arr, int n) {
    // Oth index is a dummy index.
    this.n = n;
    this.values = new int[n + 1];
    build(arr);
  }

  /** t=n*log(n) */
  private void build(int[] arr) {
    for (int i = 0; i < n; i++) {
      update(i + 1, arr[i]);
    }
  }

  /** t=O(log n) */
  public void update(int index, int increment) {
    if (index == 0) {
      return;
    }
    while (index < n + 1) {
      values[index] += increment;
      index += index & (-index);
    }
  }

  /** t=O(log n) */
  public int rq(int leftLimit, int rightLimit) {
    assert leftLimit <= rightLimit;

    if (leftLimit == rightLimit) {
      return valueAt(leftLimit);
    }
    return query(rightLimit) - query(leftLimit - 1);
  }

  /** t=O(log n) */
  public int query(int index) {
    if (index == 0) {
      return 0;
    }
    int sum = 0;
    while (index > 0) {
      sum += values[index];
      index -= index & (-index);
    }
    return sum;
  }

  /** Can be implemented as query(index)- query(index-1) */
  public int valueAt(int index) {
    // let index is a1b` where b` consists of all zeroes
    int sum = values[index];
    // a0b`
    int parent = index - (index & (-index));
    // a1b` - 1 = a0b; b consists of all ones
    index--;
    // their is some point where all ones are removed and z is reached.
    while (index != parent) {
      sum -= values[index];
      index -= index & (-index);
    }
    return sum;
  }

  public void scale(int c) {
    for (int i = 0; i < n + 1; i++) {
      values[i] /= c;
    }
  }

  /**
   * It returns 1 based index
   *
   * @param freqSum cumulative sum for which Fenwick Tree's index is needed.
   */
  public int findIndexWithFreqSum(int freqSum) {
    // greatest bit of max index; right most node of the 1st level of the tree.
    int bitmask = (int) Math.pow(2, (int) (Math.log10(n + 1) / Math.log10(2)));
    int index = 0;
    while (bitmask != 0) {
      int tempIndex = index + bitmask;
      bitmask >>= 1;
      if (tempIndex > n + 1) {
        continue;
      }
      if (freqSum == values[tempIndex]) {
        return tempIndex;
      } else if (freqSum > values[tempIndex]) {
        index = tempIndex;
        freqSum -= values[tempIndex];
      }
    }
    if (freqSum != 0) {
      return -1;
    }
    return index;
  }
}
