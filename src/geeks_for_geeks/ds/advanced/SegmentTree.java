package geeks_for_geeks.ds.advanced;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-10-2018 02:27
 **/
public class SegmentTree {
    int val[];
    int original[];
    int lazy[];

    /**
     * n leaves and n-1 internal nodes = 2*n-1
     * The total no of nodes will be 2* 2^(ceil(log n))-1 ; we convert n to nearest power of 2 so that a full binary
     * tree can be constructed.
     *
     * @param arr
     */
    SegmentTree(int arr[]) {
        int size = (int) (2 * Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2))) - 1);
        val = new int[size];
        original = Arrays.copyOf(arr, arr.length);
        lazy = new int[size];
        build(0, 0, arr.length - 1);
    }

    /**
     * T=O(log n)
     *
     * @param ql
     * @param qr
     * @return
     */
    public int query(int ql, int qr, boolean lazy) {
        return queryUtil(ql, qr, 0, this.original.length - 1, 0, lazy);
    }

    private int queryUtil(int ql, int qr, int sl, int sr, int currIndex, boolean lazy) {
        if (lazy) {
            makePendingUpdates(currIndex, sl, sr);
        }
        if (ql <= sl && qr >= sr) {
            return this.val[currIndex];
        }
        if (qr < sl || ql > sr) {
            return 0;
        }
        int mid = (sl + sr) / 2;
        return queryUtil(ql, qr, sl, mid, left(currIndex), lazy) +
                queryUtil(ql, qr, mid + 1, sr, right(currIndex), lazy);
    }

    /**
     * T=O(log n)
     *
     * @param index
     * @param newVal
     */
    public void update(int index, int newVal) {
        int diff = newVal - this.original[index];
        this.original[index] = newVal;
        updateUtil(0, this.original.length - 1, index, 0, diff);
    }

    public void updateRange(int left, int right, int diff) {
        updateRangeUtil(0, this.original.length - 1, left, right, 0, diff);
    }

    private void makePendingUpdates(int segmentTreeNode, int left, int right) {
        if (lazy[segmentTreeNode] != 0) {
            val[segmentTreeNode] += (right - left + 1) * lazy[segmentTreeNode];
//            Not a leaf node.
            if (left != right) {
                lazy[left(segmentTreeNode)] += lazy[segmentTreeNode];
                lazy[right(segmentTreeNode)] += lazy[segmentTreeNode];
            }
            lazy[segmentTreeNode] = 0;
        }
    }

    private void updateRangeUtil(int sl, int sr, int ql, int qr, int ci, int diff) {
        makePendingUpdates(ci, sl, sr);
        if (qr < sl || ql > sr) {
            return;
        }
//        inside the range.
        if (sl >= ql && sr <= qr) {
            val[ci] += (sr - sl + 1) * diff;
//            Not a leaf node
            if (sl != sr) {
                lazy[left(ci)] += diff;
                lazy[right(ci)] += diff;
            }
            return;
        }
        int mid = sl + sr >> 1;
        updateRangeUtil(sl, mid, ql, qr, left(ci), diff);
        updateRangeUtil(mid + 1, sr, ql, qr, right(ci), diff);
        val[ci] = val[left(ci)] + val[right(ci)];
    }

    private void updateUtil(int sl, int sr, int index, int currIndex, int diff) {
        if (index < sl || index > sr) {
            return;
        }
        this.val[currIndex] += diff;
        if (sl != sr) {
            int mid = (sl + sr) / 2;
            updateUtil(sl, mid, index, left(currIndex), diff);
            updateUtil(mid + 1, sr, index, right(currIndex), diff);
        }
    }


    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    /**
     * T=O(n)
     * <p>
     * original nodes were 2n-1 => O(n)
     *
     * @param currIndex
     * @param left
     * @param right
     * @return
     */
    private int build(int currIndex, int left, int right) {
        if (left == right) {
            return this.val[currIndex] = this.original[left];
        }
        int mid = (left + right) / 2;
        return this.val[currIndex] = build(left(currIndex), left, mid) +
                build(right(currIndex), mid + 1, right);
    }

    public static void test1() {
        int arr[] = {1, 3, 5, 7, 9, 11};
        SegmentTree st = new SegmentTree(arr);
        System.out.println(st.query(1, 3, true));
        st.updateRange(1, 3, 10);
        System.out.println(st.query(1, 3, true));
    }

    public static void main(String[] args) {
        test1();
    }
}
