package utils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static utils.BitUtils.lsb;

/**
 * Created By: Prashant Chaubey
 * Created On: 19-10-2018 22:48
 **/
public class Utils {

    public static Pair<Integer, Integer> findXPS(int num) {
        int lsb = lsb(num);
        int xpsSmall = 1 << lsb;
        int xpsBig = xpsSmall ^ num;
        if (xpsBig < 1 || xpsSmall < 1 || xpsBig > num || xpsSmall > num) {
            xpsBig = -1;
            xpsSmall = -1;
        }
        return new Pair<>(xpsSmall, xpsBig);
    }

    public static double areaOfPolygon(double x[], double y[]) {
        assert x.length == y.length;
        double area = 0.0;

        // shoelace formula
        int j = x.length - 1;
        for (int i = 0; i < x.length; i++) {
            area += (x[j] + x[i]) * (y[j] - y[i]);
            j = i;
        }
        return Math.abs(area / 2.0);
    }

    /**
     * Returns value of Binomial Coefficient C(n, k)
     * todo: There is one more optimized version have to check it.
     */
    static long binomialCoeff(int n, int k) {
        long dp[][] = new long[n + 1][k + 1];
        int i, j;

        // Calculate  value of Binomial Coefficient in bottom up manner
        for (i = 0; i <= n; i++) {
            for (j = 0; j <= Math.min(i, k); j++) {
                // Base Cases
                if (j == 0 || j == i)
                    dp[i][j] = 1;

                    // Calculate value using previously stored values
                else
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        return dp[n][k];
    }

    static long combinations(long n, long r) {
        r = Math.min(r, n - r);
        long numerator = 1;
        long denominator = 1;
        for (int i = 0; i < r; i++) {
            numerator *= (n--);
            denominator *= (i + 1);
        }
        return numerator / denominator;
    }

    /**
     * t=O(logn)
     * f(n) be the (n + 1)th fibonacci number. Here we are considering 1 as first fibonacci number
     */
    static long fibonacci(long n) {
        if (n == -1) {
            return 0;
        }
        if (n == 0 || n == 1) {
            return 1;
        }
        long k = n / 2;
        if (n % 2 == 0) { // n=2*k
            return (fibonacci(k) * fibonacci(k) + fibonacci(k - 1) * fibonacci(k - 1)) % 10;
        } else { // n=2*k+1
            return (fibonacci(k) * fibonacci(k + 1) + fibonacci(k - 1) * fibonacci(k)) % 10;
        }
    }

    private static double eculedianDistSq(double x1, double y1, double x2, double y2) {
        return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
    }

    static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * t=O(2^n)
     */
    private static boolean isInSubsetSum(int[] arr, int n, int value) {
        for (int i = 0; i < 1 << n; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if ((i & 1 << j) != 0) {
                    sum += arr[j];
                }
            }
            if (sum == value) {
                return true;
            }
        }
        return false;
    }

    private static boolean nativePatternMatching(int[] hay, int[] needle) {
        for (int i = 0; i <= hay.length - needle.length; i++) {
            int j;
            for (j = 0; j < needle.length; j++) {
                if (hay[i + j] != needle[j]) {
                    break;
                }
            }
            if (j == needle.length) {
                return true;
            }
        }
        return false;
    }

    private static String getDayNameFromDayNo(int no) {
        if (no < 0 || no > 6) {
            throw new RuntimeException("Invalid input");
        }
        String[] day = {"monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
        return day[no];
    }

    private static boolean isLeapYear(int year) {
        return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
    }

    public static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }

    private static int findSumOfDigits(int num) {
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }


    private static boolean all(boolean[] arr) {
        for (boolean elem : arr) {
            if (!elem) {
                return false;
            }
        }
        return true;
    }

    public static int getPrecedence(Character ch) {
        switch (ch) {
            case '(':
                return 0;
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
            default:
                throw new RuntimeException("Invalid Operator:" + ch);
        }
    }

    /**
     * In infix expression the compiler have to rescan because of precedence rules.
     */
    public static String infixToPostFix(String infixExp) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        StringBuilder postfix = new StringBuilder();
        for (int i = 0; i < infixExp.length(); i++) {
            char curr = infixExp.charAt(i);
            if (Character.isLetterOrDigit(curr)) {
                postfix.append(curr);
            } else if (curr == '(') {
                stack.push(curr);
            } else if (curr == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                assert !stack.isEmpty() : "Invalid expression, unbalanced parenthesis  found";
                stack.pop();
            } else {
                // no need to check for '(' because it's precedence will be always low and any character would sit on it.
                for (; !stack.isEmpty() && getPrecedence(curr) < getPrecedence(stack.peek()); ) {
                    postfix.append(stack.pop());
                }
                stack.push(curr);
            }
        }
        for (; !stack.isEmpty(); ) {
            assert !stack.peek().equals("(") : "Invalid expression, unbalanced parenthesis found";
            postfix.append(stack.pop());
        }
        return postfix.toString();
    }

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
     * t=O(N*2^(N))
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
     * t=O(log n)
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
     * t=O(1)
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
     * <p>
     * CAN DO - only use odd numbers in the array, which would allow us to reduce our space usage by half.
     */
    public static boolean[] sieve(int n) {
        boolean[] isPrime = new boolean[n + 1];

        for (int i = 2; i <= n; i++)
            isPrime[i] = true;
        /*
         * If you've crossed out the multiples of all the numbers less than or equal to sqrt(N), all multiples of numbers
         * greater than sqrt(N) are already crossed out. This is because any number which is less than or equal to N and
         * is multiple of a number greater than sqrt(N), will have a factor that is less than or equal to sqrt(N) and
         * therefore will already be crossed out.
         */
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                //We can start with (i*i) because if we have a k * i, where k < i, this value would have already been
                // crossed off in a prior iteration.
                for (int j = i * i; j <= n; j += i)
                    isPrime[j] = false;
            }
        }
        return isPrime;
    }


    /**
     * t=O(N*lonN)
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
     * t=O(N^2)
     */
    public static void prettyPrint2DMatrix(int[][] mat) {
        for (int[] row : mat) {
            System.out.print("{");
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(row[j] + ",");
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
     * t=O(1)
     */
    public static boolean isNotSafe(int x, int y, int rows, int cols) {
        return x < 0 || y < 0 || x >= rows || y >= cols;
    }
}
