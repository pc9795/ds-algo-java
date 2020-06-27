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
     *
     * @param arr          input array
     * @param copyOriginal if true then make a copy of original array else maintain a reference of it.
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
     *
     * @param currIndex index of the segment tree
     * @param left      left bound of the range
     * @param right     right bound of the range
     * @return value of the tree at the given index
     */
    private int build(int currIndex, int left, int right) {
        if (left == right) {
            return this.val[currIndex] = this.original[left];
        }
        int mid = (left + right) / 2;
        return this.val[currIndex] = operation(build(left(currIndex), left, mid), build(right(currIndex), mid + 1, right));
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
     *
     * @param ql left bound for query range
     * @param qr right bound for query range
     * @return result of the query for a particular range
     */
    public int query(int ql, int qr) {
        return queryUtil(ql, qr, 0, this.original.length - 1, 0);
    }

    private int queryUtil(int ql, int qr, int sl, int sr, int currIndex) {
        if (lazy) {
            makePendingUpdates(currIndex, sl, sr);
        }
        //Within range
        if (ql <= sl && qr >= sr) {
            return this.val[currIndex];
        }
        //Outside range
        if (qr < sl || ql > sr) {
            return dummyNode();
        }
        //Overlapping range
        int mid = (sl + sr) / 2;
        return operation(queryUtil(ql, qr, sl, mid, left(currIndex)), queryUtil(ql, qr, mid + 1, sr, right(currIndex)));
    }

    abstract int dummyNode();

    abstract void makePendingUpdates(int segmentTreeNode, int left, int right);

    /**
     * t=O(log n)
     *
     * @param index  index to update
     * @param newVal value to update.
     */
    public void update(int index, int newVal) {
        assert index >= 0 && index < original.length - 1;

        int increment = newVal - this.original[index];
        this.original[index] = newVal;
        updateUtil(0, this.original.length - 1, index, 0, increment);
    }

    private void updateUtil(int sl, int sr, int index, int ci, int increment) {
        //Outside range
        if (index < sl || index > sr) {
            return;
        }
        if (sl == sr) {
            this.val[ci] += increment;
            return;
        }

        int mid = (sl + sr) / 2;
        updateUtil(sl, mid, index, left(ci), increment);
        updateUtil(mid + 1, sr, index, right(ci), increment);
        val[ci] = operation(val[left(ci)], val[right(ci)]);
    }

    public void updateRange(int left, int right, int increment) {
        assert left >= 0 && right >= 0 && left < original.length && right < original.length && left < right;
        assert lazy : "Update range is only available for lazy trees";

        updateRangeUtil(0, this.original.length - 1, left, right, 0, increment);
    }

    private void updateRangeUtil(int sl, int sr, int ql, int qr, int ci, int increment) {
        makePendingUpdates(ci, sl, sr);
        //Outside the range
        if (qr < sl || ql > sr) {
            return;
        }
        // Inside the range.
        if (sl >= ql && sr <= qr) {
            lazyUpdate(ci, sl, sr, increment);
            return;
        }
        int mid = sl + sr >> 1;
        updateRangeUtil(sl, mid, ql, qr, left(ci), increment);
        updateRangeUtil(mid + 1, sr, ql, qr, right(ci), increment);
        val[ci] = operation(val[left(ci)], val[right(ci)]);
    }

    abstract void lazyUpdate(int segmentTreeNode, int left, int right, int diff);
}
