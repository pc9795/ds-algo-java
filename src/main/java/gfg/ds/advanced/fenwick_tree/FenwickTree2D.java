package gfg.ds.advanced.fenwick_tree;

/**
 * Created By: Prashant Chaubey
 * Created On: 08-02-2020 09:26
 **/
public class FenwickTree2D {
    public int[][] values;

    public FenwickTree2D(int[][] mat) {
        values = new int[mat.length + 1][mat.length + 1];
        build(mat);
    }

    private void build(int[][] mat) {
        int n = mat.length;
        int aux[][] = new int[n + 1][n + 1];
        //90 degree clock-wise rotation We are considering left bottom as origin of matrix.
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= n; i++) {
                aux[i][j] = mat[n - j][i - 1];
            }
        }
        for (int j = 1; j <= n; j++) {
            for (int i = 1; i <= n; i++) {
                update(i, j, aux[i][j]);
            }
        }
    }

    /**
     * t=O(log n)*(log m)
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @return sum from (0, 0) to (x, y)
     */
    public int getSum(int x, int y) {
        int sum = 0;
        for (int i = x; i > 0; i -= i & -i) {
            for (int j = y; j > 0; j -= j & -j) {
                sum += values[i][j];
            }
        }
        return sum;
    }

    /**
     * t=O(log n)*(log m)
     *
     * @param x         x-coordinate
     * @param y         y-coordinate
     * @param increment value to add.
     */
    public void update(int x, int y, int increment) {
        for (int i = x; i < values.length; i += i & -i) {
            for (int j = y; j < values.length; j += j & -j) {
                values[i][j] += increment;
            }
        }
    }
}
