package geeks_for_geeks.advanced;

import geeks_for_geeks.ds.advanced.FenwickTree;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-10-2019 19:27
 * Purpose: TODO:
 **/
class TestFenwickTree {

    @Test
    void testFenwickTree() {
        int arr[] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        FenwickTree bit = new FenwickTree(arr);
        System.out.println(Arrays.toString(bit.values));
        System.out.println(bit.rq(0, 5));
        for (int i = 0; i < arr.length; i++) {
            System.out.print(bit.valueAt(i + 1) + " ");
        }
        System.out.println(bit.findIndexWithFreqSum(12));
    }
}
