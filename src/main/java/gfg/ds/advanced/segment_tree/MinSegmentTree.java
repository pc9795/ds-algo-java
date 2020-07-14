package gfg.ds.advanced.segment_tree;

/**
 * Created By: Prashant Chaubey
 * Created On: 07-02-2020 18:51
 **/
public class MinSegmentTree extends SegmentTreeBase {
    private int lazy[];

    public MinSegmentTree(int[] arr, boolean copyOriginal) {
        super(arr, copyOriginal);
    }

    public MinSegmentTree(int[] arr, boolean copyOriginal, boolean lazy) {
        super(arr, copyOriginal, lazy);
        this.lazy = new int[val.length];
    }

    @Override
    int operation(int leftRoot, int rightRoot) {
        return Math.min(leftRoot, rightRoot);
    }

    @Override
    int dummyNode() {
        return Integer.MAX_VALUE;
    }

    @Override
    void makePendingUpdates(int segmentTreeNode, int leftLimit, int rightLimit) {
        if (lazy[segmentTreeNode] == 0) {
            return;
        }
        val[segmentTreeNode] += lazy[segmentTreeNode];
        // Not a leaf node.
        if (leftLimit != rightLimit) {
            lazy[left(segmentTreeNode)] += lazy[segmentTreeNode];
            lazy[right(segmentTreeNode)] += lazy[segmentTreeNode];
        }
        lazy[segmentTreeNode] = 0;
    }

    @Override
    void lazyUpdate(int segmentTreeNode, int leftLimit, int rightLimit, int diff) {
        val[segmentTreeNode] += diff;
        // Not a leaf node
        if (leftLimit != rightLimit) {
            lazy[left(segmentTreeNode)] += diff;
            lazy[right(segmentTreeNode)] += diff;
        }
    }
}
