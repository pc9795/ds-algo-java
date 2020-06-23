import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
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

    private static class UGraph {
        double[] nodes;
        List<Integer>[] values;

        UGraph(int vertices) {
            nodes = new double[vertices];
            values = new ArrayList[vertices];
        }

        void addEdge(int src, int dest) {
            if (values[src] == null) {
                values[src] = new ArrayList<>();
            }
            if (values[dest] == null) {
                values[dest] = new ArrayList<>();
            }
            values[src].add(dest);
            values[dest].add(dest);
        }

        List<Integer> solve(double maxPerCapita) {
            int vertices = nodes.length;
            boolean visited[] = new boolean[nodes.length];
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                if (nodes[i] < maxPerCapita || visited[i]) {
                    continue;
                }
                List<Integer> traversal = new ArrayList<>();
                dfsUtil(i, visited, traversal);
                if (traversal.size() > ans.size()) {
                    ans = traversal;
                }
            }
            return ans;
        }

        void dfsUtil(int source, boolean[] visited, List<Integer> traversal) {
            ArrayDeque<Integer> stack = new ArrayDeque<>();
            stack.push(source);
            while (!stack.isEmpty()) {
                source = stack.pop();
                if (visited[source]) {
                    continue;
                }
                traversal.add(source);
                visited[source] = true;
                if (values[source] == null) {
                    continue;
                }
                for (Integer neighbour : values[source]) {
                    if (!visited[neighbour]) {
                        stack.push(neighbour);
                    }
                }
            }
        }
    }

    private static int MOD = 1000_000_007;

    private static void solve(Scanner in) {
        int t = in.nextInt();
        in.nextLine();
        for (int _t = 0; _t < t; _t++) {
            int n = in.nextInt();
            UGraph graph = new UGraph(n);
            int m = in.nextInt();
            int income[] = fillIntArr(in, n);

            double maxPerCapita = 0;
            for (int i = 0; i < n; i++) {
                graph.nodes[i] = income[i] / (double) in.nextInt();
                if (graph.nodes[i] > maxPerCapita) {
                    maxPerCapita = graph.nodes[i];
                }
            }
            for (int i = 0; i < m; i++) {
                int src = in.nextInt() - 1;
                int dest = in.nextInt() - 1;
                if (graph.nodes[src] < maxPerCapita || graph.nodes[dest] < maxPerCapita) {
                    continue;
                }
                graph.addEdge(src, dest);
            }
            List<Integer> ans = graph.solve(maxPerCapita);
            System.out.println(ans.size());
            StringBuilder sb = new StringBuilder();
            for (Integer i : ans) {
                sb.append(i + 1).append(" ");
            }
            System.out.println(sb);
        }
    }
}