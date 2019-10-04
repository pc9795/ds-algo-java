package geeks_for_geeks.algo.backtracking;

import util.Utils;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 28-10-2018 14:31
 **/
public class KnightTour {

    public static void knightTour(int boardSize) {
        int mat[][] = new int[boardSize][boardSize];
        for (int i = 0; i < mat.length; i++) {
            Arrays.fill(mat[i], -1);
        }
        mat[0][0] = 0;
        if (knightTourUtil(0, 0, 8, mat, 1)) {
            for (int i = 0; i < mat.length; i++) {
                System.out.println(Arrays.toString(mat[i]));
            }
        } else {
            System.out.println("No solution exists!");
        }
    }

    private static boolean knightTourUtil(int x, int y, int boardSize, int[][] board, int nextMove) {
//        System.out.println("Move:" + nextMove);
        int moves = 8;
        int[] row = {2, 1, -1, -2, -2, -1, 1, 2};
        int[] col = {1, 2, 2, 1, -1, -2, -2, -1};
        if (nextMove == boardSize * boardSize) {
            return true;
        } else {
            for (int i = 0; i < moves; i++) {
                int nextX = x + row[i];
                int nextY = y + col[i];
                if (Utils.isSafe(board, nextX, nextY) && board[nextX][nextY] == -1) {
                    board[nextX][nextY] = nextMove;
                    if (knightTourUtil(nextX, nextY, boardSize, board, nextMove + 1)) {
                        return true;
                    } else {
                        board[nextX][nextY] = -1;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        knightTour(8);
    }
}
