package geeks_for_geeks.ds.advanced;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-10-2018 02:27
 **/
public class SegmentTree {
    int val[];
    int original[];

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
        constructSegmentTree(0, 0, arr.length - 1);
    }

    /**
     * T=O(log n)
     *
     * @param ql
     * @param qr
     * @return
     */
    public int query(int ql, int qr) {
        return queryUtil(ql, qr, 0, this.original.length - 1, 0);
    }

    public int queryUtil(int ql, int qr, int sl, int sr, int currIndex) {
        if (ql <= sl && qr >= sr) {
            return this.val[currIndex];
        }
        if (qr < sl || ql > sr) {
            return 0;
        }
        int mid = (sl + sr) / 2;
        return queryUtil(ql, qr, sl, mid, left(currIndex)) + queryUtil(ql, qr, mid + 1, sr, right(currIndex));
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


    private void updateUtil(int left, int right, int index, int currIndex, int diff) {
        if (index < left || index > right) {
            return;
        }
        this.val[currIndex] += diff;
        if (left != right) {
            int mid = (left + right) / 2;
            updateUtil(left, mid, index, left(currIndex), diff);
            updateUtil(mid + 1, right, index, right(currIndex), diff);
        }

    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    /**
     * T=O(n)
     *
     * original nodes were 2n-1 => O(n)
     *
     * @param currIndex
     * @param left
     * @param right
     * @return
     */
    private int constructSegmentTree(int currIndex, int left, int right) {
        if (left == right) {
            return this.val[currIndex] = this.original[left];
        }
        int mid = (left + right) / 2;
        return this.val[currIndex] = constructSegmentTree(left(currIndex), left, mid) +
                constructSegmentTree(right(currIndex), mid + 1, right);
    }

    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 7, 9, 11};
        SegmentTree st = new SegmentTree(arr);
        System.out.println(Arrays.toString(st.val));
        System.out.println("query:" + st.query(1, 3));
        st.update(1, 10);
        System.out.println(Arrays.toString(st.val));
        System.out.println("query after update:" + st.query(1, 3));
    }
}
