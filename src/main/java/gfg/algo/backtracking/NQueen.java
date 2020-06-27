package gfg.algo.backtracking;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 12-11-2018 01:14
 **/
public class NQueen {
    public static void nqueen(int boardSize) {
        int chessBoard[][] = new int[boardSize][boardSize];
        if (nqueenUtil(0, chessBoard)) {
            for (int[] aChessBoard : chessBoard) {
                System.out.println(Arrays.toString(aChessBoard));
            }
        } else {
            System.out.println("No solution exists!");
        }
    }

    private static boolean isSafePosition(int chessBoard[][], int col, int row) {
        for (int i = 0; i < col; i++) {
            if (chessBoard[row][i] == 1) {
                return false;
            }
        }
        int diagonalRow = row;
        int diagonalCol = col;
        while (--diagonalCol >= 0 && --diagonalRow >= 0) {
            if (chessBoard[diagonalRow][diagonalCol] == 1) {
                return false;
            }
        }
        diagonalRow = row;
        diagonalCol = col;
        while (--diagonalCol >= 0 && ++diagonalRow < chessBoard.length) {
            if (chessBoard[diagonalRow][diagonalCol] == 1) {
                return false;
            }
        }
        return true;

    }

    private static boolean nqueenUtil(int col, int chessBoard[][]) {
        if (col == chessBoard[0].length) {
            return true;
        }
        for (int row = 0; row < chessBoard.length; row++) {
            if (isSafePosition(chessBoard, col, row)) {
                chessBoard[row][col] = 1;
                if (nqueenUtil(col + 1, chessBoard)) {
                    return true;
                } else {
                    chessBoard[row][col] = 0;
                }
            }
        }
        return false;
    }
}
