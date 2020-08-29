package gfg.algo.dp;

public class DPProblems {
  /** t=O(n^3) s=O(n^2) matrix starts from 1; dimensions are arr[i-1]*arr[i] for ith matrix. */
  public static int matrixChainOrder(int arr[]) {
    // 0th row and column will not be used.
    int dp[][] = new int[arr.length][arr.length];
    dp[0][0] = -1;
    for (int i = 1; i < arr.length; i++) {
      dp[0][i] = -1;
      dp[i][0] = -1;
      dp[i][i] = 0;
    }
    for (int len = 2; len < arr.length; len++) {
      for (int i = 1; i < arr.length - len + 1; i++) {
        int j = i + len - 1;
        dp[i][j] = Integer.MAX_VALUE;
        for (int k = i; k <= j - 1; k++) {
          int temp = dp[i][k] + dp[k + 1][j] + arr[i - 1] * arr[k] * arr[j];
          if (temp < dp[i][j]) {
            dp[i][j] = temp;
          }
        }
      }
    }
    return dp[1][arr.length - 1];
  }
}
