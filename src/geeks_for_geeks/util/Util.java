package geeks_for_geeks.util;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 19-10-2018 22:48
 **/
public class Util {
    public static void prettyPrint2DMatrix(int[][] mat) {
        for (int i = 0; i < mat.length; i++) {
            System.out.print("{");
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + ",");
            }
            System.out.print("},");
            System.out.println();
        }
    }

    public static int[][] deepCopy(int[][] mat) {
        if (mat == null) {
            return null;
        }
        int[][] copy = new int[mat.length][mat[0].length];
        for (int i = 0; i < mat.length; i++) {
            copy[i] = Arrays.copyOf(mat[i], mat[i].length);
        }
        return copy;
    }

    public static boolean isSafe(int[][] mat, int i, int j) {
        return i >= 0 && i < mat.length && j >= 0 && j < mat[0].length;
    }


}
