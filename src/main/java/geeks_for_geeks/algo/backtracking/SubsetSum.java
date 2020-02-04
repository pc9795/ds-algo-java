package geeks_for_geeks.algo.backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-11-2018 01:28
 **/
public class SubsetSum {
    public static void subsetSum(int arr[], int sum) {
        Arrays.sort(arr);
        subsetSumUtil(arr, 0, new HashSet<>(), 0, sum);
    }

    private static void subsetSumUtil(int arr[], int start, Set<Integer> subset, int subsetSum, int sum) {
//        we are supposed to get input as a set therefore it is not worth to check if we get the sum.
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


