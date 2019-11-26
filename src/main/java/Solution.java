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

    private static int MOD = 10_000_000;
    private static boolean SINGLE_TEST_CASE = false;


    private static void solve(Scanner in) {
        int t = SINGLE_TEST_CASE ? 1 : in.nextInt();
        for (int _t = 0; _t < t; _t++) {
            int n = in.nextInt();
            double[] a = new double[n];
            double[] b = new double[n];
            for (int i = 0; i < n; i++) {
                a[i] = in.nextInt();
                b[i] = in.nextInt();
            }
            Arrays.sort(a);
            Arrays.sort(b);
            double temp = b[1];
            b[1] = b[n - 1];
            b[n - 1] = temp;
            double x[] = new double[n + 2];
            double y[] = new double[n + 2];
            System.arraycopy(a, 0, x, 1, n);
            System.arraycopy(b, 0, y, 1, n);
            x[0] = x[1];
            x[x.length - 1] = x[x.length - 2];
            System.out.println((int) (2 * areaOfPolygon(x, y)));
        }

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

}

















