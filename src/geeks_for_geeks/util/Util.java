package geeks_for_geeks.util;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created By: Prashant Chaubey
 * Created On: 19-10-2018 22:48
 **/
public class Util {

    public static int[][] findSubArraySums(int arr[]) {
        int n = arr.length;
        int sum[][] = new int[n][n];
        for (int i = 0; i < n; i++) {
            sum[i][i] = arr[i];
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                sum[i][j] = sum[i][j - len / 2] + sum[j - len / 2 + 1][j];
            }
        }
        return sum;
    }

    /**
     * T=O(N*2^(N))
     *
     * @param arr
     * @param n
     * @return
     */
    public static Map<Integer, Integer> findSubsetSums(int arr[], int n) {
        Map<Integer, Integer> subsetSums = new HashMap<>();
        for (int i = 0; i < 1 << n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((1 << j & i) > 0) {
                    sum += arr[j];
                }
                if (!subsetSums.containsKey(sum)) {
                    subsetSums.put(sum, 0);
                }
            }
            subsetSums.put(sum, subsetSums.get(sum) + 1);
        }
        return subsetSums;
    }

    /**
     * T=O(log n)
     *
     * @param base
     * @param exp
     * @param MOD
     * @return
     */
    public static long fastExp(int base, int exp, final int MOD) {
        long res = 1;
        while (exp > 0) {
            if (exp % 2 == 1) {
                res = (res * base) % MOD;
            }
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res % MOD;
    }

    /**
     * T=O(1)
     *
     * @param c
     * @return
     */
    public static int encode(int c) {
        if (Character.isLowerCase(c)) {
            return c - 'a' + 26;
        }
        return c - 'A';
    }

    /**
     * T=O(N*logN)
     * <p>
     * T=N/2+N/3+...+1 (multiples of i upto N are N/i_
     * =N(1/2+1/3+...+1/N)
     * =N(1+1/2+...+1/N) ;adding 1 will have no effect. It will become a harmonic sum which is bounded by logN.
     * =N(logN)
     *
     * @param n
     */
    public static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];

        for (int i = 2; i <= n; i++)
            isPrime[i] = true;
        /**
         * If you've crossed out the multiples of all the numbers less than or equal to sqrt(N), all multiples of numbers
         * greater than sqrt(N) are already crossed out. This is because any number which is less than or equal to N and
         * is multiple of a number greater than sqrt(N), will have a factor that is less than or equal to sqrt(N) and
         * therefore will already be crossed out.
         */
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i)
                    isPrime[j] = false;
            }
        }
        return isPrime;
    }


    /**
     * T=O(N*lonN)
     *
     * @param n
     * @return
     */
    public static int[] findDivisorSum(int n) {
        int divisorSum[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= n; j += i) {
                divisorSum[j] += i;
            }
        }
        return divisorSum;
    }

    /**
     * T=O(log N)
     * <p>
     * It will place low(=mid) at the position of just greatest value from the searched value, and in the next iteration
     * high will go to mid-1 and the position of low is returned.
     * If searched value is greater than the last element will return the array length.
     * If searched value is smaller than the first element then return 0.
     * Return the first position where the arr[i] -lt value is false (-ge)
     *
     * @param arr
     * @param low
     * @param high
     * @param value
     * @return
     */
    public static int lowerBound(int arr[], int low, int high, int value) {
        if (low > high) {
            return low;
        }
        int mid = (low + high) >> 1;
        if (arr[mid] == value) {
            return mid;
        }
        if (arr[mid] < value) {
            return lowerBound(arr, mid + 1, high, value);
        }
        return lowerBound(arr, low, mid - 1, value);
    }


    public static double eucledianDistance(Pair<Double, Double> a, Pair<Double, Double> b) {
        return Math.sqrt(((b.getKey() - a.getKey()) * (b.getKey() - a.getKey())) + ((b.getValue() - a.getValue()) *
                (b.getValue() - a.getValue())));
    }

    /**
     * T=O(N^2)
     *
     * @param mat
     */
    public static void prettyPrint2DMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.print("{");
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + ",");
            }
            System.out.print("},");
            System.out.println();
        }
    }

    public static int[][] deepCopy(int[][] mat) {
        if (mat == null) {
            return null;
        }
        int[][] copy = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            copy[i] = Arrays.copyOf(mat[i], mat[i].length);
        }
        return copy;
    }

    public static boolean isSafe(int[][] mat, int i, int j) {
        return i >= 0 && i < mat.length && j >= 0 && j < mat[0].length;
    }

    /**
     * T=O(1)
     *
     * @param x
     * @param y
     * @param rows
     * @param cols
     * @return
     */
    public static boolean isSafe(int x, int y, int rows, int cols) {
        return !(x < 0 || y < 0 || x >= rows || y >= cols);
    }


}
