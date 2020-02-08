package test.geeks_for_geeks.ds.advanced;

import geeks_for_geeks.ds.advanced.fenwick_tree.Applications;
import geeks_for_geeks.ds.advanced.fenwick_tree.FenwickTree;
import geeks_for_geeks.ds.advanced.fenwick_tree.FenwickTree2D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-10-2019 19:27
 **/
class TestFenwickTree {

    @Test
    void testFenwickTreeOperations() {
        int arr[] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        FenwickTree bit = new FenwickTree(arr, arr.length);
        // 2+1+1+3
        assert bit.query(4) == 7;
        // 3+2+3+4
        assert bit.rq(4, 7) == 12;

        assert bit.valueAt(2) == 1;
        bit.update(2, 5);
        assert bit.valueAt(2) == 6;
        assert bit.query(4) == 12;
    }

    @Test
    void testScale() {
        int arr[] = {2, 4, 6, 8, 10};
        FenwickTree bit = new FenwickTree(arr, arr.length);

        assert bit.rq(2, 4) == 18;
        bit.scale(2);
        assert bit.valueAt(2) == 2;
        assert bit.rq(2, 4) == 9;
    }

    @Test
    void testFindIndexWithFreqSum() {
        int arr[] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        FenwickTree bit = new FenwickTree(arr, arr.length);
        assert bit.findIndexWithFreqSum(12) == 6;
    }

    @Test
    void testFenwickTree2DOperations() {
        int[][] mat = {{3, 6, 9},
                {2, 5, 8},
                {1, 4, 7}};
        FenwickTree2D bit = new FenwickTree2D(mat);
        // After rotation
        // {{1, 2, 3},
        //  {4, 5, 6},
        //  {7, 8, 9}};
        assert bit.getSum(1, 1) == 1;
        assert bit.getSum(1, 2) == 3;
        assert bit.getSum(1, 3) == 6;
        assert bit.getSum(2, 1) == 5;
        assert bit.getSum(2, 2) == 12;
        assert bit.getSum(2, 3) == 21;
        assert bit.getSum(3, 1) == 12;
        assert bit.getSum(3, 2) == 27;
        assert bit.getSum(3, 3) == 45;
    }

    @Test
    void testRangeUpdatePointQuery() {
        int arr[] = {10, 5, 20, 40};
        Applications.RangeUpdatePointQuery query = new Applications.RangeUpdatePointQuery(arr);
        query.rangeUpdate(0, 3, 10);
        Assertions.assertArrayEquals(query.getArray(), new int[]{20, 15, 30, 50});
    }

    @Test
    void testRangeUpdateRangeQuery() {
        // 0 0 0 0 0
        Applications.RangeUpdateRangeQuery query = new Applications.RangeUpdateRangeQuery(5);
        // 5 5 5 5 5
        query.rangeUpdate(0, 4, 5);
        // 5 5 15 15 15
        query.rangeUpdate(2, 4, 10);
        assert query.rangeQuery(1, 4) == 50;
    }
}
