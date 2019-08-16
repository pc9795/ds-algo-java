package geeks_for_geeks.ds.array;

import java.util.ArrayList;

/**
 * Created By: Prashant Chaubey
 * Created On: 04-10-2018 02:09
 **/
public class ArrayApplications {
    public static void findLeaders(int arr[]) {
        if (arr.length == 0) {
            System.out.println("Leaders:{}");
            return;
        }
        ArrayList<Integer> leaders = new ArrayList<>();
        leaders.add(arr[arr.length - 1]);
        int highFromEnd = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > highFromEnd) {
                leaders.add(arr[i]);
                highFromEnd = arr[i];
            }
        }
        System.out.println("Leaders:" + leaders);
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
     *
     * @param arr
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

    public static void main(String[] args) {
//        findLeaders(new int[]{16, 17, 4, 3, 5, 2});
//        System.out.println(findValueWhichOccurredOddNumberOfTimes(new int[]{1, 2, 3, 2, 3, 1, 3}));
        maxSumContiguousSubarray(new int[]{-2, -3, 4, -1, -2, 1, 5, -3});
    }
}
