package gfg.ds.advanced.fenwick_tree;

import gfg.ds.advanced.DiffArray;

/**
 * Created By: Prashant Chaubey
 * Created On: 08-02-2020 18:13
 **/
public class Applications {
    /**
     * Alternative of Difference Array.
     */
    public static class RangeUpdatePointQuery {
        private FenwickTree bit;

        public RangeUpdatePointQuery(int arr[]) {
            DiffArray diffArray = new DiffArray(arr, arr.length);
            bit = new FenwickTree(diffArray.values, arr.length);
        }

        /**
         * t=O(log n)
         *
         * @param l         lower bound
         * @param r         upper bound
         * @param increment value to update the range with.
         */
        public void rangeUpdate(int l, int r, int increment) {
            //Incrementing indices for Fenwick tree operations which are 1 based.
            bit.update(l + 1, increment);
            bit.update(r + 1 + 1, -increment);
        }

        /**
         * t=O(log n)
         *
         * @param index index of the element
         * @return value at the index
         */
        public int get(int index) {
            //Incrementing indices for Fenwick tree operations which are 1 based.
            return bit.query(index + 1);
        }

        /**
         * t=O(n * log n)
         *
         * @return stored array
         */
        public int[] getArray() {
            //Bit stores 1 extra the original array length. 0th index is for dummy node.
            int[] arr = new int[bit.n];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = get(i);
            }
            return arr;
        }
    }

    public static class RangeUpdateRangeQuery {
        private FenwickTree primary;
        private FenwickTree secondary;

        public RangeUpdateRangeQuery(int n) {
            //This implementation requires that all values start with 0.
            primary = new FenwickTree(new int[n], n);
            secondary = new FenwickTree(new int[n], n);
        }

        public void rangeUpdate(int l, int r, int increment) {
            //Incrementing indices for Fenwick tree operations which are 1 based.
            primary.update(l + 1, increment);
            primary.update(r + 1 + 1, -increment);
            secondary.update(l + 1, (l - 1) * increment);
            secondary.update(r + 1 + 1, -r * increment);
        }

        public int rangeQuery(int l, int r) {
            return query(r) - query(l - 1);
        }

        private int query(int index) {
            //We are optimistic that all the values are same till the index therefore multiplying by index.
            //Everything starts with 0 therefore this assumption is true.
            //After we are adjusting values according to second BIT.
            return primary.query(index + 1) * index - secondary.query(index + 1);
        }

    }
}
