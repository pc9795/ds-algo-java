package geeks_for_geeks.algo.dp;

/**
 * Created By: Prashant Chaubey
 * Created On: 10-11-2018 15:13
 **/
public class MinCostPath {
    public static int minCostPath(int[][] costMatrix) {
//        TODO: validation
        int dp[][] = new int[costMatrix.length][costMatrix[0].length];
        dp[0][0] = costMatrix[0][0];
        for (int i = 1; i < costMatrix.length; i++) {
            dp[i][0] = costMatrix[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < costMatrix[0].length; i++) {
            dp[0][i] = costMatrix[0][i] + dp[0][i - 1];
        }
        for (int i = 1; i < costMatrix.length; i++) {
            for (int j = 1; j < costMatrix[0].length; j++) {
                dp[i][j] = costMatrix[i][j] + Math.min(dp[i - 1][j], Math.min(dp[j][i - 1], dp[i - 1][j - 1]));
            }
        }
        return dp[costMatrix.length - 1][costMatrix[0].length - 1];
    }

    public static void main(String[] args) {
        int costMatrix[][] = {{1, 2, 3},
                {4, 8, 2},
                {1, 5, 3}};
        System.out.println(minCostPath(costMatrix));
    }
}
