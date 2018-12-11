package geeks_for_geeks.algo.dp;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-11-2018 01:27
 **/
public class DPProblems {
    /**
     * T=O(n^3)
     * S=O(n^2)
     * <p>
     * matrix starts from 1; dimensions are arr[i-1]*arr[i] for ith matrix.
     *
     * @param arr
     */
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

    public static void main(String[] args) {
        System.out.println(matrixChainOrder(new int[]{1, 2, 3, 4}));
    }
}
