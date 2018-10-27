package geeks_for_geeks.ds.graph;

import geeks_for_geeks.ds.nodes.Edge;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-10-2018 11:59
 **/
public class GraphApplications {

    public static class BoardEntry {
        int moves;
        int pos;

        public BoardEntry(int moves, int pos) {
            this.moves = moves;
            this.pos = pos;
        }

        @Override
        public String toString() {
            return "BoardEntry{" +
                    "moves=" + moves +
                    ", pos=" + pos +
                    '}';
        }
    }

    public static int snakeAndLadderProblem(int n, int[] move) {
        ArrayDeque<BoardEntry> queue = new ArrayDeque<>();
        queue.add(new BoardEntry(0, 0));
        boolean[] visited = new boolean[n];
        visited[0] = true;
        for (; !queue.isEmpty(); ) {
            BoardEntry entry = queue.poll();
            if (entry.pos == n - 1) {
                return entry.moves;
            }
            for (int i = entry.pos + 1; i <= entry.pos + 6 && i < n; i++) {
                if (!visited[i]) {
                    if (i == n - 1) {

                    }
                    if (move[i] != -1) {
                        queue.add(new BoardEntry(entry.moves + 1, move[i]));
                    } else {
                        queue.add(new BoardEntry(entry.moves + 1, i));
                    }
                    visited[i] = true;
                }
            }
        }
        return -1;
    }

    public static void minimizeCashFlow(Graph graph) {
        int n = graph.vertices();
        int amount[] = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                amount[i] += graph.values[j][i] - graph.values[i][j];
            }
        }
        minimizeCashFlowUtil(amount);
    }

    private static void minimizeCashFlowUtil(int[] amount) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for (int i = 0; i < amount.length; i++) {
            if (amount[i] < min) {
                min = amount[i];
                minIndex = i;
            }
        }
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < amount.length; i++) {
            if (amount[i] > max) {
                max = amount[i];
                maxIndex = i;
            }
        }
        if (min == 0 && max == 0) {
            return;
        }
        int minCash = Math.min(-amount[minIndex], amount[maxIndex]);
        System.out.println("Person(" + minIndex + ") gives Person(" + maxIndex + ") " + minCash + " rupees!");
        amount[minIndex] += minCash;
        amount[maxIndex] -= minCash;
        minimizeCashFlowUtil(amount);
    }

    public static void boggle(char matrix[][], Set<String> dict) {
        StringBuilder word = new StringBuilder();
        Set<String> hash = new HashSet<>();
        boolean visited[][] = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                boggleUtil(i, j, word.append(matrix[i][j]), hash, dict, matrix, visited);
            }
        }
    }

    private static void boggleUtil(int row, int col, StringBuilder word, Set<String> hash, Set<String> dict,
                                   char[][] matrix, boolean[][] visited) {
        visited[row][col] = true;
        if (hash.contains(word.toString())) {
            return;
        }
        if (dict.contains(word.toString())) {
            System.out.println("Found:" + word);
            hash.add(word.toString());
        }
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && j >= 0 && i < matrix.length && j < matrix[0].length && !visited[i][j]) {
                    boggleUtil(i, j, word.append(matrix[i][j]), hash, dict, matrix, visited);
                }
            }
        }
        word.deleteCharAt(word.length() - 1);
        visited[row][col] = false;
    }


    public static void findDirectionsForEdgesSoAfterAddingGraphRemainsDAG(Edge[] edges, GraphUsingAdjacencyList graph) {
        ArrayDeque<Integer> stack = graph.topologicalSort();
        for (Edge edge : edges) {
            Iterator<Integer> it = stack.iterator();
            for (; it.hasNext(); ) {
                int val = it.next();
                if (val == edge.src) {
                    System.out.println("Edge to be added from " + edge.src + " to " + edge.dest);
                    break;
                } else if (val == edge.dest) {
                    System.out.println("Edge to be added from " + edge.dest + " to " + edge.src);
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 30;
        int moves[] = new int[n];
        Arrays.fill(moves, -1);
//        ladders
        moves[2] = 21;
        moves[4] = 7;
        moves[10] = 25;
        moves[19] = 28;
//         Snakes
        moves[26] = 0;
        moves[20] = 8;
        moves[16] = 3;
        moves[18] = 6;
//        System.out.println(snakeAndLadderProblem(n, moves));
        Graph graph = new Graph(3);
        graph.addEdge(0, 1, 1000).addEdge(0, 2, 2000).addEdge(1, 2, 5000);
//        minimizeCashFlow(graph);
        Set<String> dict = new HashSet<>();
        dict.add("GEEKS");
        dict.add("FOR");
        dict.add("QUIZ");
        dict.add("GO");
        char[][] matrix = new char[][]{
                {'G', 'I', 'Z'},
                {'U', 'E', 'K'},
                {'Q', 'S', 'E'}
        };
//        boggle(matrix, dict);
        GraphUsingAdjacencyList graph2 = new GraphUsingAdjacencyList(6);
        graph2.addEdge(0, 5).addEdge(0, 1).addEdge(5, 1).addEdge(5, 2).
                addEdge(1, 2)
                .addEdge(1, 3).addEdge(1, 4).addEdge(2, 3).
                addEdge(2, 4).addEdge(3, 4);
        findDirectionsForEdgesSoAfterAddingGraphRemainsDAG(new Edge[]{
                new Edge(3, 0), new Edge(4, 5), new Edge(0, 2)
        }, graph2);
    }
}
