package geeks_for_geeks.algo.dp;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-11-2018 01:43
 **/
public class CoinChange {
    public static void coinChange(int n, int[] coins) {
        int dp[][] = new int[n + 1][coins.length];
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < coins.length; j++) {
                int x = i - coins[j] < 0 ? 0 : dp[i - coins[j]][j];
                int y = j - 1 < 0 ? 0 : dp[i][j - 1];
                dp[i][j] = x + y;
            }
        }
        System.out.println("Answer is:" + dp[n][coins.length - 1]);
    }

    public static void coinChangeOptimized(int n, int[] coins) {
        int dp[] = new int[n + 1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= n; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }
        System.out.println("Answer is:" + dp[n]);
    }

    public static void main(String[] args) {
        coinChange(4, new int[]{1, 2, 3});
        coinChangeOptimized(4, new int[]{1, 2, 3});
    }
}
