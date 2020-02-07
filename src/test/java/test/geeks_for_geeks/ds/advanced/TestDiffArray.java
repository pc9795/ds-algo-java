package test.geeks_for_geeks.ds.advanced;

import geeks_for_geeks.ds.advanced.DiffArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 07-02-2020 16:14
 **/
class TestDiffArray {
    @Test
    void testDiffArrayOperations() {
        int arr[] = {10, 5, 20, 40};
        DiffArray diffArray = new DiffArray(arr, arr.length);
        diffArray.update(0, 1, 10);
        Assertions.assertArrayEquals(diffArray.get(), new int[]{20, 15, 20, 40});
    }
}
