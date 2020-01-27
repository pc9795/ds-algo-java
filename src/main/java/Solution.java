import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 31-03-2019 01:36
 * Purpose: Template for code chef's problems.
 **/
@SuppressWarnings("Duplicates")
class Solution {

    static class Pair<K, V> {
        public K key;
        public V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }

        @Override
        public int hashCode() {
            return key.hashCode() * 13 + (value == null ? 0 : value.hashCode());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o instanceof Pair) {
                Pair pair = (Pair) o;
                if (key != null ? !key.equals(pair.key) : pair.key != null) return false;
                if (value != null ? !value.equals(pair.value) : pair.value != null) return false;
                return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        if (System.getenv("GOOGLE_USERNAME") != null) {
            in = new Scanner(new FileInputStream(
                    new File("D:\\3_Dev\\Projects\\ds_algo\\src\\main\\java\\input")));
        } else {
            in = new Scanner(System.in);
        }
        solve(in);
        in.close();
    }

    private static Pair<Integer, Integer>[] fillPairArr(Scanner in, int n) {
        Pair<Integer, Integer>[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair<>(in.nextInt(), in.nextInt());
        }
        return arr;
    }

    private static int[] fillIntArr(Scanner in, int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        return arr;
    }

    private static long[] fillLongArr(Scanner in, int n) {
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextLong();
        }
        return arr;
    }

    private static int MOD = 1000_000_007;
    private static boolean SINGLE_TEST_CASE = false;

    private static ArrayList<String> state;
    private static HashMap<Integer, Integer> dp;

    private static void solve(Scanner in) {
        int t = SINGLE_TEST_CASE ? 1 : in.nextInt();
        in.nextLine();
        for (int _t = 0; _t < t; _t++) {
            int n = in.nextInt();
            int k = in.nextInt();
            in.nextLine();
            state = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                state.add(in.nextLine());
            }
            HashSet<Integer> path = new HashSet<>();
            dp = new HashMap<>();
            int ans = solveUtil(path, 0, n, k);
            System.out.println(ans);
        }
    }

    private static int solveUtil(HashSet<Integer> path, int curr, int n, int k) {
        if (dp.containsKey(curr) && (dp.get(curr) <= path.size() || dp.get(curr) == -1)) {
            return dp.get(curr);
        }
        if (curr == n - 1) {
            return path.size();
        }
        int min = Integer.MAX_VALUE;
        path.add(curr);
        for (int i = curr - k < 0 ? 0 : curr - k; i <= curr + k && i < n; i++) {
            if (i < 0 || i == curr || state.get(curr).charAt(i) != '1' || path.contains(i)) {
                continue;
            }
            int subPathAns = solveUtil(path, i, n, k);
            if (subPathAns == -1) {
                continue;
            }
            min = Math.min(min, subPathAns);
        }
        path.remove(curr);
        min = min == Integer.MAX_VALUE ? -1 : min;
        dp.put(curr, min);
        return min;
    }
}
