package gfg.ds.advanced;

/**
 * Created By: Prashant Chaubey
 * Created On: 06-10-2019 13:39
 * Range updates in O(1)
 **/
public class DiffArray {
    public int[] values;
    public int n;

    public DiffArray(int[] arr, int n) {
        // We update r + 1 therefore extra space.
        this.n = n;
        values = new int[n];
        values[0] = arr[0];
        for (int i = 1; i < n; i++) {
            values[i] = arr[i] - arr[i - 1];
        }
    }

    /**
     * t=O(1)
     */
    public void update(int lowerIndex, int upperIndex, int increment) {
        assert lowerIndex >= 0 && lowerIndex < n && upperIndex >= 0 && upperIndex < n && lowerIndex < upperIndex : "Invalid value of lower index and upper index";

        values[lowerIndex] += increment;
        if (upperIndex == n - 1) {
            return;
        }
        values[upperIndex + 1] -= increment;
    }

    /**
     * t=O(n)
     */
    public int[] get() {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
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
