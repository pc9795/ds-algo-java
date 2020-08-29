package gfg.algo.dp;

import java.util.Arrays;

public class SequenceProblems {

  /** t=O(n^2) */
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

  /** t=O(mn) */
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
        dp[i][j] =
            str1.charAt(i) == str2.charAt(j)
                ? 1 + dp[i - 1][j - 1]
                : Math.max(dp[i - 1][j], dp[i][j - 1]);
      }
    }
    int max = Integer.MIN_VALUE;
    for (int[] aDp : dp) {
      for (int j = 0; j < dp[0].length; j++) {
        if (aDp[j] > max) {
          max = aDp[j];
        }
      }
    }
    return max;
  }
}
