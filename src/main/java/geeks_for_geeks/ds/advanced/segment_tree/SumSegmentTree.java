package geeks_for_geeks.ds.advanced.segment_tree;

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
    void makePendingUpdates(int segmentTreeNode, int left, int right) {
        if (lazy[segmentTreeNode] == 0) {
            return;
        }
        val[segmentTreeNode] += (right - left + 1) * lazy[segmentTreeNode];
        // Not a leaf node.
        if (left != right) {
            lazy[left(segmentTreeNode)] += lazy[segmentTreeNode];
            lazy[right(segmentTreeNode)] += lazy[segmentTreeNode];
        }
        lazy[segmentTreeNode] = 0;
    }

    @Override
    void lazyUpdate(int segmentTreeNode, int left, int right, int diff) {
        val[segmentTreeNode] += (right - left + 1) * diff;
        // Not a leaf node
        if (left != right) {
            lazy[left(segmentTreeNode)] += diff;
            lazy[right(segmentTreeNode)] += diff;
        }
    }
}