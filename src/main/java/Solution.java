import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created By: Prashant Chaubey
 * Created On: 31-03-2019 01:36
 * Purpose: Template for code chef's problems.
 **/
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

    private static Pair<Integer, Integer>[] fillPairArr(Scanner in, int n, int start) {
        Pair<Integer, Integer>[] arr = new Pair[start + n];
        for (int i = start; i < start + n; i++) {
            arr[i] = new Pair<>(in.nextInt(), in.nextInt());
        }
        return arr;
    }

    private static int[] fillIntArr(Scanner in, int n, int start) {
        int[] arr = new int[start + n];
        for (int i = start; i < start + n; i++) {
            arr[i] = in.nextInt();
        }
        return arr;
    }

    private static long[] fillLongArr(Scanner in, int n, int start) {
        long[] arr = new long[start + n];
        for (int i = start; i < start + n; i++) {
            arr[i] = in.nextLong();
        }
        return arr;
    }

    private static int MOD = 10_000_000;
    private static boolean SINGLE_TEST_CASE = false;

    private static void solve(Scanner in) {
        int t = SINGLE_TEST_CASE ? 1 : in.nextInt();
        for (int _t = 0; _t < t; _t++) {
        }
    }
}

















