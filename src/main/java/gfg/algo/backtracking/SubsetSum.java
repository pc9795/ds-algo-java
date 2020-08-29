package gfg.algo.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SubsetSum {
  public static void subsetSum(int arr[], int sum) {
    Arrays.sort(arr);
    subsetSumUtil(arr, 0, new HashSet<>(), 0, sum);
  }

  private static void subsetSumUtil(
      int arr[], int start, Set<Integer> subset, int subsetSum, int sum) {
    // we are supposed to get input as a set therefore it is not worth to check if we get the
    // sum.
    if (subsetSum == sum) {
      System.out.println(subset);
    } else if (start < arr.length && subsetSum + arr[start] <= sum) {
      for (int i = start; i < arr.length; i++) {
        if (subsetSum + arr[i] <= sum) {
          subset.add(arr[i]);
          subsetSumUtil(arr, i + 1, subset, subsetSum + arr[i], sum);
          subset.remove(arr[i]);
        }
      }
    }
  }
}
