package geeks_for_geeks.ds.graph;

import geeks_for_geeks.ds.graph.adj_matrix.Graph;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Set;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-10-2018 11:59
 **/
public class GraphApplications {

    public static class BoardEntry {
        int moves;
        int pos;

        BoardEntry(int moves, int pos) {
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

    /**
     * t=O(n) ;number of cells
     * Each cell is removed and added one time only.
     *
     * @param n
     * @param move
     * @return
     */
    public static int snakeAndLadderProblem(int n, int[] move) {
        ArrayDeque<BoardEntry> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        queue.add(new BoardEntry(0, 0));
        visited[0] = true;

        for (; !queue.isEmpty(); ) {
            BoardEntry entry = queue.poll();
            if (entry.pos == n - 1) {
                return entry.moves;
            }
            for (int i = entry.pos + 1; i <= entry.pos + 6 && i < n; i++) {

                if (!visited[i]) {
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

    /**
     * t=O(v^2)
     * Greedy
     *
     * @param graph
     */
    public static void minimizeCashFlow(Graph graph) {

        int n = graph.vertices();
        int amount[] = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph.values[j][i] = graph.values[j][i] == Integer.MAX_VALUE ? 0 : graph.values[j][i];
                graph.values[i][j] = graph.values[i][j] == Integer.MAX_VALUE ? 0 : graph.values[i][j];

                amount[i] += graph.values[j][i] - graph.values[i][j];
            }
        }

        minimizeCashFlowUtil(amount);
    }

    private static void minimizeCashFlowUtil(int[] amount) {
        int minIndex = 0;
        int maxIndex = 0;

        for (int i = 0; i < amount.length; i++) {
            if (amount[i] < amount[minIndex]) {
                minIndex = i;
            }

            if (amount[i] > amount[maxIndex]) {
                maxIndex = i;
            }
        }

        if (amount[minIndex] == 0 && amount[maxIndex] == 0) {
            return;
        }

        int minCash = Math.min(-amount[minIndex], amount[maxIndex]);
        System.out.println("Person(" + minIndex + ") gives Person(" + maxIndex + ") " + minCash + " rupees!");

        amount[minIndex] += minCash;
        amount[maxIndex] -= minCash;

        minimizeCashFlowUtil(amount);
    }

    /**
     * Backtracking
     *
     * @param matrix
     * @param dict
     */
    public static void boggle(char matrix[][], Set<String> dict) {

        StringBuilder word = new StringBuilder();
//        To prevent from printing multiple words.
        Set<String> memo = new HashSet<>();
        boolean visited[][] = new boolean[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

//                This appended value will be deleted in the helper function so every time this word will start with
//                a single character
                boggleUtil(i, j, word.append(matrix[i][j]), memo, dict, matrix, visited);
            }
        }
    }

    private static void boggleUtil(int row, int col, StringBuilder word, Set<String> memo, Set<String> dict,
                                   char[][] matrix, boolean[][] visited) {

        visited[row][col] = true;

        if (memo.contains(word.toString())) {
            return;
        }

        if (dict.contains(word.toString())) {
            System.out.println("Found:" + word);
            memo.add(word.toString());
        }
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {

//                Traverse all neighbours.
                if (i >= 0 && j >= 0 && i < matrix.length && j < matrix[0].length && !visited[i][j]) {

                    boggleUtil(i, j, word.append(matrix[i][j]), memo, dict, matrix, visited);
                }
            }
        }

//        back tracking step.
        word.deleteCharAt(word.length() - 1);
        visited[row][col] = false;
    }

}
