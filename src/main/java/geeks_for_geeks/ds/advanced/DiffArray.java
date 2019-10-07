package geeks_for_geeks.ds.advanced;

/**
 * Created By: Prashant Chaubey
 * Created On: 06-10-2019 13:39
 * Purpose: Difference array to update range.
 **/
public class DiffArray {
    private int[] values;

    public DiffArray(int[] arr, int n) {
        // We update r+1 therefore extra space.
        values = new int[n + 1];
        values[0] = arr[0];
        values[n] = 0;
        for (int i = 1; i < n; i++) {
            values[i] = arr[i] - arr[i - 1];
        }
    }

    /**
     * O(1)
     *
     * @param l
     * @param r
     * @param increment
     */
    public void update(int l, int r, int increment) {
        values[l] += increment;
        values[r + 1] -= increment;
    }

    /**
     * O(n)
     *
     * @return
     */
    public int[] get() {
        int[] arr = new int[values.length - 1];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                // Note that A[0] or D[0] decide values of rest of the elements.
                arr[i] = values[i];
            } else {
                arr[i] = values[i] + arr[i - 1];
            }
        }
        return arr;
    }
}
