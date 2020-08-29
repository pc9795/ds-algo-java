package gfg.algo.searching_and_sorting;

import java.util.Arrays;

public class Searching {

  /** t=O(n) */
  public static int linearSearch(int arr[], int data) {
    if (arr == null || arr.length == 0) {
      return -1;
    }
    for (int arrVal : arr) {
      if (arrVal == data) {
        return data;
      }
    }
    return -1;
  }

  /** t(n)=t(n/2)+c t=O(log n) */
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
    return -1;
  }
}
