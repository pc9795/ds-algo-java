package gfg.ds.graph.adj_matrix;

import utils.Pair;
import utils.Utils;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-10-2018 11:59
 **/
public class Applications {
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
     * t=O(n); number of cells
     * Each cell is removed and added one time only.
     * Using BFS for unweighted graph to get shortest path.
     *
     * @param n    no of cells
     * @param move snack and ladder board. move[i] will be either -1 or the destination cell(either snake or ladder)
     * @return minimum no of moves
     */
    public static int snakeAndLadderProblem(int n, int[] move) {
        ArrayDeque<BoardEntry> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        q.add(new BoardEntry(0, 0));
        visited[0] = true;

        for (; !q.isEmpty(); ) {
            BoardEntry entry = q.poll();
            assert entry != null;
            if (entry.pos == n - 1) {
                return entry.moves;
            }
            for (int i = entry.pos + 1; i <= entry.pos + 6 && i < n; i++) {
                if (visited[i]) {
                    continue;
                }
                if (move[i] != -1) {
                    q.add(new BoardEntry(entry.moves + 1, move[i]));
                } else {
                    q.add(new BoardEntry(entry.moves + 1, i));
                }
                visited[i] = true;
            }
        }
        return -1;
    }

    /**
     * t=O(V^2)
     * Greedy
     *
     * @param graph input graph
     */
    public static List<Pair<Integer, Pair<Integer, Integer>>> minimizeCashFlow(Graph graph) {
        int n = graph.vertices();
        int amount[] = new int[n];
        //Net amount for each person
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph.values[j][i] = graph.values[j][i] == Integer.MAX_VALUE ? 0 : graph.values[j][i];
                graph.values[i][j] = graph.values[i][j] == Integer.MAX_VALUE ? 0 : graph.values[i][j];
                amount[i] += graph.values[j][i] - graph.values[i][j];
            }
        }
        List<Pair<Integer, Pair<Integer, Integer>>> transactions = new ArrayList<>();
        minimizeCashFlowUtil(amount, transactions);
        return transactions;
    }

    private static void minimizeCashFlowUtil(int[] amount, List<Pair<Integer, Pair<Integer, Integer>>> transactions) {
        int minIndex = 0;
        int maxIndex = 0;
        //Find minimum and maximum values. Source and sink
        for (int i = 0; i < amount.length; i++) {
            if (amount[i] < amount[minIndex]) {
                minIndex = i;
            }
            if (amount[i] > amount[maxIndex]) {
                maxIndex = i;
            }
        }
        //Both amounts are 0 so everything is settled.
        if (amount[minIndex] == 0 && amount[maxIndex] == 0) {
            return;
        }
        int minCash = Math.min(-amount[minIndex], amount[maxIndex]);
        transactions.add(new Pair<>(minIndex, new Pair<>(maxIndex, minCash)));
        amount[minIndex] += minCash;
        amount[maxIndex] -= minCash;
        minimizeCashFlowUtil(amount, transactions);
    }

    /**
     * Backtracking
     *
     * @param matrix matrix containing words
     * @param dict   dictionary of words
     */
    public static Set<String> boggle(char matrix[][], Set<String> dict) {
        StringBuilder word = new StringBuilder();
        Set<String> foundWords = new HashSet<>();
        boolean visited[][] = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                // This appended value will be deleted in the helper function so every time this word will start with
                // a single character
                boggleUtil(i, j, word.append(matrix[i][j]), foundWords, dict, matrix, visited);
            }
        }
        return foundWords;
    }

    private static void boggleUtil(int row, int col, StringBuilder word, Set<String> foundWords, Set<String> dict,
                                   char[][] matrix, boolean[][] visited) {
        visited[row][col] = true;
        //O(word length)
        if (foundWords.contains(word.toString())) {
            return;
        }
        if (dict.contains(word.toString())) {
            foundWords.add(word.toString());
        }
        // Traverse all neighbours.
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (!Utils.isSafe(i, j, matrix.length, matrix[0].length)) {
                    continue;
                }
                if (visited[i][j]) {
                    continue;
                }
                boggleUtil(i, j, word.append(matrix[i][j]), foundWords, dict, matrix, visited);
            }
        }
        // back tracking step.
        word.deleteCharAt(word.length() - 1);
        visited[row][col] = false;
    }
}
