package gfg.ds.array;

import utils.Pair;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 04-10-2018 02:09
 **/
public class Applications {
    /**
     * t=O(n)
     */
    public static List<Integer> getLeaders(int arr[]) {
        if (arr.length == 0) {
            System.out.println("Leaders:{}");
            return new ArrayList<>();
        }

        List<Integer> leaders = new ArrayList<>();
        leaders.add(arr[arr.length - 1]);

        int lastLeader = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > lastLeader) {
                leaders.add(arr[i]);
                lastLeader = arr[i];
            }
        }

        Collections.reverse(leaders);

        return leaders;
    }

    /**
     * t=O(log n)
     */
    public static Optional<Pair<Integer, Integer>> findPairWithSum(int[] arr, int sum) {
        if (arr == null || arr.length == 0) {
            return Optional.empty();
        }

        Arrays.sort(arr);

        for (int left = 0, right = arr.length - 1; left < right; ) {
            int currSum = arr[left] + arr[right];

            if (currSum == sum) {
                return Optional.of(new Pair<>(arr[left], arr[right]));
            }
            if (currSum > sum) {
                right--;
            } else {
                left++;
            }
        }

        return Optional.empty();
    }

    public static int findValueWhichOccurredOddNumberOfTimes(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }
        int accum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            accum = accum ^ arr[i];
        }
        return accum;
    }

    /**
     * Also known as Kadane's Algorithm
     */
    public static void maxSumContiguousSubarray(int arr[]) {
        if (arr.length == 0) {
            System.out.println("Input is empty");
            return;
        }
        int start = 0;
        int end = 0;
        int s = 0;
        int maxSoFar = arr[0];
        int maxTillHere = arr[0];
        for (int i = 1; i < arr.length; i++) {
            maxTillHere += arr[i];
            if (maxTillHere < 0) {
                maxTillHere = 0;
                s = i + 1;
            } else if (maxTillHere > maxSoFar) {
                maxSoFar = maxTillHere;
                start = s;
                end = i;
            }
        }

        System.out.println("start:" + start + ", end:" + end);

    }
}
