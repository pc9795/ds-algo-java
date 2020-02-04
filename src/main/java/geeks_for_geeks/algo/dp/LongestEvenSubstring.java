package geeks_for_geeks.algo.dp;

/**
 * Created By: Prashant Chaubey
 * Created On: 29-10-2018 01:12
 **/
public class LongestEvenSubstring {
    /**
     * T=O(n^2)
     * S=O(n^2)
     *
     * @param str
     * @return
     */
    public static int longestEvenSubstringSumOfFirstAndSecondHalfSame(String str) {
        if (str == null || str.length() == 0) {
            System.out.println("Array is empty!");
            return -1;
        }
        int dp[][] = new int[str.length()][str.length()];
        for (int i = 0; i < str.length(); i++) {
//            considering numeric string.
            dp[i][i] = str.charAt(i) - '0';
        }
        int max = Integer.MIN_VALUE;
        for (int len = 2; len <= str.length(); len++) {
            for (int i = 0; i < str.length() - len + 1; i++) {
                int j = i + len - 1;
//                0,3 => 0,1 and 2,3
                dp[i][j] = dp[i][j - len / 2] + dp[j - len / 2 + 1][j];
                if (len % 2 == 0 && (dp[i][j - len / 2] == dp[j - len / 2 + 1][j]) && len > max) {
                    max = len;
                }
            }
        }
        return max;
    }

    /**
     * T=O(n^2)
     * S=O(n)
     *
     * @param str
     * @return
     */
    public static int longestEvenSubstringSumOfFirstAndSecondHalfSame2(String str) {
        int sum[] = new int[str.length() + 1];
        sum[0] = 0;

          /* Store cumulative sum of digits
          from first to last digit */
        for (int i = 1; i <= str.length(); i++)
            /* convert chars to int */
            sum[i] = (sum[i - 1] + str.charAt(i - 1)
                    - '0');
        int ans = 0; // initialize result
        /* consider all even length
        substrings one by one */
        for (int len = 2; len <= str.length(); len += 2) {
            for (int i = 0; i <= str.length() - len; i++) {
                int j = i + len - 1;
                  /* Sum of first and second half
                is same than update ans */
                // we are starting from one.
                if (sum[i + len / 2] - sum[i] == sum[i + len]
                        - sum[i + len / 2])
                    ans = Math.max(ans, len);
            }
        }
        return ans;
    }

    /**
     * T=O(n^2)
     * S=O(1)
     *
     * @param str
     * @return
     */
    public static int longestEvenSubstringSumOfFirstAndSecondHalfSame3(String str) {
        int ans = 0;
        // Consider all possible midpoints one by one
        for (int i = 0; i <= str.length() - 2; i++) {
        /* For current midpoint 'i', keep expanding substring on
           both sides, if sum of both sides becomes equal update
           ans */
            int l = i, r = i + 1;
            /* initialize left and right sum */
            int lsum = 0, rsum = 0;
            /* move on both sides till indexes go out of bounds */
            while (r < str.length() && l >= 0) {
                lsum += str.charAt(l) - '0';
                rsum += str.charAt(r) - '0';
                if (lsum == rsum)
                    ans = Math.max(ans, r - l + 1);
                l--;
                r++;
            }
        }
        return ans;
    }
}
