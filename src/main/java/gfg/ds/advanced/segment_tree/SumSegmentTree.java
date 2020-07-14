package gfg.ds.advanced.segment_tree;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-10-2018 02:27
 **/
public class SumSegmentTree extends SegmentTreeBase {
    private int lazy[];

    public SumSegmentTree(int arr[], boolean copyOriginal) {
        super(arr, copyOriginal);
    }

    public SumSegmentTree(int arr[], boolean copyOriginal, boolean lazy) {
        super(arr, copyOriginal, lazy);
        this.lazy = new int[val.length];
    }

    @Override
    int operation(int leftRoot, int rightRoot) {
        return leftRoot + rightRoot;
    }

    @Override
    int dummyNode() {
        return 0;
    }

    @Override
    void makePendingUpdates(int segmentTreeNode, int leftLimit, int rightLimit) {
        if (lazy[segmentTreeNode] == 0) {
            return;
        }
        val[segmentTreeNode] += (rightLimit - leftLimit + 1) * lazy[segmentTreeNode];
        // Not a leaf node.
        if (leftLimit != rightLimit) {
            lazy[left(segmentTreeNode)] += lazy[segmentTreeNode];
            lazy[right(segmentTreeNode)] += lazy[segmentTreeNode];
        }
        lazy[segmentTreeNode] = 0;
    }

    @Override
    void lazyUpdate(int segmentTreeNode, int leftLimit, int rightLimit, int diff) {
        val[segmentTreeNode] += (rightLimit - leftLimit + 1) * diff;
        // Not a leaf node
        if (leftLimit != rightLimit) {
            lazy[left(segmentTreeNode)] += diff;
            lazy[right(segmentTreeNode)] += diff;
        }
    }
}