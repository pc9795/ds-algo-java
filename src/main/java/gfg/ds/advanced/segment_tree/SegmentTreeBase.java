package gfg.ds.advanced.segment_tree;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 07-02-2020 17:54
 **/
public abstract class SegmentTreeBase {
    int val[];
    private int original[];
    private boolean lazy;

    /**
     * n leaves and n-1 internal nodes = (2*n)-1
     * The total no of nodes will be (2*2^(ceil(log n)))-1 ; we convert n to nearest power of 2 so that a full binary
     * tree can be constructed.
     */
    SegmentTreeBase(int arr[], boolean copyOriginal) {
        int size = (int) (2 * Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2))) - 1);
        val = new int[size];
        if (copyOriginal) {
            original = Arrays.copyOf(arr, arr.length);
        } else {
            // Save reference
            original = arr;
        }
        build(0, 0, arr.length - 1);
    }

    SegmentTreeBase(int arr[], boolean copyOriginal, boolean lazy) {
        this(arr, copyOriginal);
        this.lazy = lazy;
    }

    /**
     * t=O(n)
     * original nodes were (2*n)-1 => O(n)
     */
    private int build(int currIndex, int leftLimit, int rightLimit) {
        if (leftLimit == rightLimit) {
            return this.val[currIndex] = this.original[leftLimit];
        }
        int mid = (leftLimit + rightLimit) / 2;
        return this.val[currIndex] = operation(build(left(currIndex), leftLimit, mid), build(right(currIndex), mid + 1, rightLimit));
    }

    int left(int i) {
        return 2 * i + 1;
    }

    int right(int i) {
        return 2 * i + 2;
    }

    abstract int operation(int leftRoot, int rightRoot);

    /**
     * t=O(log n)
     */
    public int query(int queryLeftLimit, int queryRightLimit) {
        return queryUtil(queryLeftLimit, queryRightLimit, 0, this.original.length - 1, 0);
    }

    private int queryUtil(int queryLeftLimit, int queryRightLimit, int treeLeftLimit, int treeRightLimit, int currIndex) {
        if (lazy) {
            makePendingUpdates(currIndex, treeLeftLimit, treeRightLimit);
        }
        //Within range
        if (queryLeftLimit <= treeLeftLimit && queryRightLimit >= treeRightLimit) {
            return this.val[currIndex];
        }
        //Outside range
        if (queryRightLimit < treeLeftLimit || queryLeftLimit > treeRightLimit) {
            return dummyNode();
        }
        //Overlapping range
        int mid = (treeLeftLimit + treeRightLimit) / 2;
        return operation(queryUtil(queryLeftLimit, queryRightLimit, treeLeftLimit, mid, left(currIndex)), queryUtil(queryLeftLimit, queryRightLimit, mid + 1, treeRightLimit, right(currIndex)));
    }

    abstract int dummyNode();

    abstract void makePendingUpdates(int segmentTreeNode, int leftLimit, int rightLimit);

    /**
     * t=O(log n)
     */
    public void update(int index, int newVal) {
        assert index >= 0 && index < original.length - 1;

        int increment = newVal - this.original[index];
        this.original[index] = newVal;
        updateUtil(0, this.original.length - 1, index, 0, increment);
    }

    private void updateUtil(int treeLeftLimit, int treeRightLimit, int index, int currIndex, int increment) {
        //Outside range
        if (index < treeLeftLimit || index > treeRightLimit) {
            return;
        }
        if (treeLeftLimit == treeRightLimit) {
            this.val[currIndex] += increment;
            return;
        }

        int mid = (treeLeftLimit + treeRightLimit) / 2;
        updateUtil(treeLeftLimit, mid, index, left(currIndex), increment);
        updateUtil(mid + 1, treeRightLimit, index, right(currIndex), increment);
        val[currIndex] = operation(val[left(currIndex)], val[right(currIndex)]);
    }

    public void updateRange(int leftLimit, int rightLimit, int increment) {
        assert leftLimit >= 0 && rightLimit >= 0 && leftLimit < original.length && rightLimit < original.length && leftLimit < rightLimit;
        assert lazy : "Update range is only available for lazy trees";

        updateRangeUtil(0, this.original.length - 1, leftLimit, rightLimit, 0, increment);
    }

    private void updateRangeUtil(int treeLeftLimit, int treeRightLimit, int queryLeftLimit, int queryRightLimit, int currIndex, int increment) {
        makePendingUpdates(currIndex, treeLeftLimit, treeRightLimit);
        //Outside the range
        if (queryRightLimit < treeLeftLimit || queryLeftLimit > treeRightLimit) {
            return;
        }
        // Inside the range.
        if (treeLeftLimit >= queryLeftLimit && treeRightLimit <= queryRightLimit) {
            lazyUpdate(currIndex, treeLeftLimit, treeRightLimit, increment);
            return;
        }
        int mid = treeLeftLimit + treeRightLimit >> 1;
        updateRangeUtil(treeLeftLimit, mid, queryLeftLimit, queryRightLimit, left(currIndex), increment);
        updateRangeUtil(mid + 1, treeRightLimit, queryLeftLimit, queryRightLimit, right(currIndex), increment);
        val[currIndex] = operation(val[left(currIndex)], val[right(currIndex)]);
    }

    abstract void lazyUpdate(int segmentTreeNode, int leftLimit, int rightLimit, int diff);
}
