package utils;

public class LeetCodeUtils {
    void print2D(int[][] mat){
        for (int[] ints : mat) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(ints[j] + ", ");
            }
            System.out.println();
        }
    }
}
