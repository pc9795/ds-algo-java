import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created By: Prashant Chaubey
 * Created On: 31-03-2019 01:36
 * Purpose: Template for code chef's problems.
 **/
@SuppressWarnings({"Duplicates", "unchecked", "unused"})
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
                return value != null ? value.equals(pair.value) : pair.value == null;
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

    private static void solve(Scanner in) {
        int t = in.nextInt();
        in.nextLine();
        for (int _t = 0; _t < t; _t++) {
            int n = in.nextInt();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= n; i++) {
                if (n % 2 == 0 && i % 2 == 0) {
                    int temp = i * n;
                    for (int j = temp; j > temp - n; j--) {
                        sb.append(j);
                        if (j == temp - n + 1) {
                            sb.append(System.lineSeparator());
                        } else {
                            sb.append(" ");
                        }
                    }
                } else {
                    int temp = (i - 1) * n + 1;
                    for (int j = temp; j < temp + n; j++) {
                        sb.append(j);
                        if (j == temp + n - 1) {
                            sb.append(System.lineSeparator());
                        } else {
                            sb.append(" ");
                        }
                    }
                }
            }
            System.out.println(sb);
        }
    }
}