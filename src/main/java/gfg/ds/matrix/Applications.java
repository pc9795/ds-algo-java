package gfg.ds.matrix;

import java.util.HashMap;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 19:29
 **/
public class Applications {

    public static boolean searchInColumnAndRowWiseSortedMatrix(int mat[][], int key) {
        int rows = mat.length;
        int columns = mat[0].length;
        int i = 0;
        int j = columns - 1;

        for (; i < rows && j >= 0; ) {
            if (mat[i][j] == key) {
                return true;
            }
            if (key < mat[i][j]) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    public static void printInSpiralForm(int mat[][]) {
        int rows = mat.length;
        int columns = mat[0].length;
        for (int startRow = 0, startColumn = 0; startRow < rows && startColumn < columns; ) {
            for (int i = startColumn; i < columns; i++) {
                System.out.print(mat[startRow][i] + " ");
            }
            startRow++;
            for (int i = startRow; i < rows; i++) {
                System.out.print(mat[i][columns - 1] + " ");
            }
            columns--;
            if (startRow < rows) {
                for (int i = columns - 1; i >= startColumn; i--) {
                    System.out.print(mat[rows - 1][i] + " ");
                }
                rows--;
            }

            if (startColumn < columns) {
                for (int i = rows - 1; i >= startRow; i--) {
                    System.out.print(mat[i][startColumn] + " ");
                }
                startColumn++;
            }
        }

    }

    public static void makeColumnAndRowOneHavingAtLeastOneOne(int mat[][]) {
        boolean firstRowHasOne = false;
        boolean firstColumnHasOne = false;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (i == 0 && mat[i][j] == 1) {
                    firstRowHasOne = true;
                }
                if (j == 0 && mat[i][j] == 1) {
                    firstColumnHasOne = true;
                }
                if (mat[i][j] == 1) {
                    mat[0][j] = 1;
                    mat[i][0] = 1;
                }
            }
        }

        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                if (mat[0][j] == 1 || mat[i][0] == 1) {
                    mat[i][j] = 1;
                }
            }
        }
        if (firstColumnHasOne) {
            for (int i = 0; i < mat.length; i++) {
                mat[i][0] = 1;
            }
        }
        if (firstRowHasOne) {
            for (int i = 0; i < mat[0].length; i++) {
                mat[0][i] = 1;
            }
        }
    }

    public static void printUniqueRows(boolean mat[][]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < mat.length; i++) {
            double rowTotal = 0;
            for (int j = 0; j < mat[0].length; j++) {
                rowTotal += Math.pow(2, mat[0].length - 1 - j) * (mat[i][j] ? 1 : 0);
            }
            if (!map.containsKey((int) rowTotal)) {
                map.put((int) rowTotal, i);
                System.out.println("Unique row:" + i);
            }
        }
    }

    public static void maxSizeSquareSubMarixWitAllOnes(int mat[][]) {
        int aux[][] = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            aux[i][0] = mat[i][0];
        }
        System.arraycopy(mat[0], 0, aux[0], 0, mat[0].length);
        for (int i = 1; i < mat.length; i++) {
            for (int j = 1; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    aux[i][j] = Math.min(Math.min(aux[i - 1][j - 1], aux[i - 1][j]), aux[i][j - 1]) + 1;
                } else {
                    aux[i][j] = 0;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (aux[i][j] > max) {
                    max = aux[i][j];
                }
            }
        }
        System.out.println("Max:" + max);
    }
}
