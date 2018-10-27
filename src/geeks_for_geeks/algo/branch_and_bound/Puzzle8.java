package geeks_for_geeks.algo.branch_and_bound;

import geeks_for_geeks.util.Util;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created By: Prashant Chaubey
 * Created On: 19-10-2018 22:38
 **/
public class Puzzle8 {

    private static boolean check(int[][] initial, int[][] result) {
        for (int i = 0; i < initial.length; i++) {
            for (int j = 0; j < initial[0].length; j++) {
                if (initial[i][j] != result[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int calculateCost(int[][] state, int[][] result) {
        int cost = 0;
        for (int i = 0; i < state.length; i++) {
            for (int j = 0; j < state[0].length; j++) {
                if (state[i][j] != result[i][j]) {
                    cost++;
                }
            }
        }
        return cost;
    }

    static class PuzzleNode {
        PuzzleNode parent = null;
        int[][] value;
        int cost;
        int x, y;
    }

    public static void puzzle8(int[][] initial, int[][] result, int x, int y) {
        PriorityQueue<PuzzleNode> heap = new PriorityQueue<>(new Comparator<PuzzleNode>() {
            @Override
            public int compare(PuzzleNode o1, PuzzleNode o2) {
                return o1.cost - o2.cost;
            }
        });
        if (check(initial, result)) {
            Util.prettyPrint2DMatrix(initial);
        }
        int row[] = {0, 1, -1, 0};
        int col[] = {-1, 0, 0, 1};
        PuzzleNode node = new PuzzleNode();
        node.value = Util.deepCopy(initial);
        node.cost = calculateCost(node.value, result);
        node.parent = null;
        node.x = x;
        node.y = y;
        heap.add(node);
        for (; ; ) {
            PuzzleNode curr = heap.poll();
            for (int i = 0; i < row.length; i++) {
                if (Util.isSafe(curr.value, curr.x + row[i], curr.y + col[i])) {
                    PuzzleNode child = new PuzzleNode();
                    child.value = Util.deepCopy(curr.value);
                    child.x = curr.x + row[i];
                    child.y = curr.y + col[i];
                    int temp = child.value[child.x][child.y];
                    child.value[child.x][child.y] = 0;
                    child.value[curr.x][curr.y] = temp;
                    child.parent = curr;
                    if (check(child.value, result)) {
                        PuzzleNode iter;
                        for (iter = child; iter.parent != null; iter = iter.parent) {
                            Util.prettyPrint2DMatrix(iter.value);
                            System.out.println("||");
                        }
                        Util.prettyPrint2DMatrix(iter.value);
                        return;
                    }
                    child.cost = calculateCost(child.value, result);
                    heap.add(child);
                }
            }
            if (heap.isEmpty()) {
                System.out.println("No answer");
                return;
            }
        }

    }

    public static void main(String[] args) {
        int[][] initial = new int[][]{
                {1, 2, 3},
                {5, 6, 0},
                {7, 8, 4}
        };
        int[][] result = new int[][]{
                {1, 2, 3},
                {5, 8, 6},
                {0, 7, 4}
        };
        puzzle8(initial, result, 1, 2);
    }
}
