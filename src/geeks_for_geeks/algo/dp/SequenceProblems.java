package geeks_for_geeks.algo.dp;

import geeks_for_geeks.util.Util;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 27-10-2018 14:59
 **/
public class SequenceProblems {

    /**
     * T=O(n^2)
     *
     * @param arr
     * @return
     */
    public static int longestIncreasingSubsequence(int arr[]) {
        if (arr == null || arr.length == 0) {
            System.out.println("Array is empty!");
            return -1;
        }
        int[] lis = new int[arr.length];
        lis[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            lis[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && lis[j] + 1 > lis[i]) {
                    lis[i] = lis[j] + 1;
                }
            }
        }
        return Arrays.stream(lis).reduce(Integer.MIN_VALUE, Math::max);
    }

    /**
     * T=O(mn)
     *
     * @param str1
     * @param str2
     * @return
     */
    public static int longestCommonSubsequence(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return 0;
        }
        int dp[][] = new int[str1.length()][str2.length()];
        dp[0][0] = str1.charAt(0) == str2.charAt(0) ? 1 : 0;
        for (int i = 1; i < str1.length(); i++) {
            dp[i][0] = str1.charAt(i) == str2.charAt(0) ? 1 : dp[i - 1][0];
        }

        for (int i = 1; i < str2.length(); i++) {
            dp[0][i] = str1.charAt(0) == str2.charAt(i) ? 1 : dp[0][i - 1];
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = str1.charAt(i) == str2.charAt(j) ? 1 + dp[i - 1][j - 1] : Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int arr[] = {10, 22, 9, 33, 21, 50, 41, 60};
//        System.out.println(longestIncreasingSubsequence(arr));
        System.out.println(longestCommonSubsequence("AGGTAB", "GXTXAYB"));
    }
}
