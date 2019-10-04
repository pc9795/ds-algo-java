import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created By: Prashant Chaubey
 * Created On: 31-03-2019 01:36
 * Purpose: TODO:
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
        Scanner in = null;
        if (System.getenv("GOOGLE_USERNAME") != null) {
            in = new Scanner(new FileInputStream(
                    new File("D:\\3_Dev\\Projects\\ds_algo\\src\\main\\java\\input")));
        } else {
            in = new Scanner(System.in);
        }
        solve(in);
        in.close();
    }

    private static int[] fillArr(Scanner in, int[] arr, int n, int start) {
        for (int i = start; i < start + n; i++) {
            arr[i] = in.nextInt();
        }
        return arr;
    }

    private static Pair[] fillArr(Scanner in, Pair[] pairs, int n) {
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair<Integer, Integer>(in.nextInt(), in.nextInt());
        }
        return pairs;
    }


    static int MOD = 10_000_000;

    private static void solve(Scanner in) {
        int t = in.nextInt();
        for (int _t = 0; _t < t; _t++) {
        }
    }

}

