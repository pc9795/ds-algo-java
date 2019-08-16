import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created By: Prashant Chaubey
 * Created On: 31-03-2019 01:36
 * Purpose: TODO:
 **/
class Solution {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = null;
        if (System.getenv("GOOGLE_USERNAME") != null) {
            in = new Scanner(new FileInputStream(new File("D:/3_Dev/Projects/codechef_practice/src/input")));
        } else {
            in = new Scanner(System.in);
        }
        solve(in);
        in.close();
    }

    private static void solve(Scanner in) {
        int t = in.nextInt();
        for (int _t = 0; _t < t; _t++) {
            int n = in.nextInt();
            int[] caves = new int[n];
            int[] zombies = new int[n];
            int[] radiatedCaves = new int[n];
            Arrays.fill(radiatedCaves, 0);
            for (int i = 0; i < n; i++) {
                caves[i] = in.nextInt();
            }
            for (int i = 0; i < n; i++) {
                zombies[i] = in.nextInt();
            }
            SegmentTree st = new SegmentTree(radiatedCaves);
            for (int i = 0; i < n; i++) {
                st.updateRange(i - caves[i] < 0 ? 0 : i - caves[i], i + caves[i] >= n ? n - 1 : i + caves[i], 1);
            }

            for (int i = 0; i < n; i++) {
                radiatedCaves[i] = st.query(i, i, true);
            }

            Arrays.sort(radiatedCaves);
            Arrays.sort(zombies);
            boolean ans = true;
            for (int i = 0; i < n; i++) {
                if (radiatedCaves[i] != zombies[i]) {
                    ans = false;
                    break;
                }
            }
            System.out.println(ans ? "YES" : "NO");
        }
    }
}

class SegmentTree {
    private int val[];
    private int original[];
    private int lazy[];

    public SegmentTree(int arr[]) {
        int size = (int) (2 * Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2))) - 1);
        val = new int[size];
        original = arr;
        lazy = new int[size];
        build(0, 0, arr.length - 1);
    }

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
        return operation(queryUtil(ql, qr, sl, mid, left(currIndex), lazy),
                queryUtil(ql, qr, mid + 1, sr, right(currIndex), lazy));
    }

    public void updateRange(int left, int right, int diff) {
        updateRangeUtil(0, this.original.length - 1, left, right, 0, diff);
    }

    private void updateRangeUtil(int sl, int sr, int ql, int qr, int ci, int diff) {
        makePendingUpdates(ci, sl, sr);
        if (qr < sl || ql > sr) {
            return;
        }
        if (sl >= ql && sr <= qr) {
            this.val[ci] += (sr - sl + 1) * diff;
            if (sl != sr) {
                lazy[left(ci)] += diff;
                lazy[right(ci)] += diff;
            }
            return;
        }
        int mid = sl + sr >> 1;
        updateRangeUtil(sl, mid, ql, qr, left(ci), diff);
        updateRangeUtil(mid + 1, sr, ql, qr, right(ci), diff);
        val[ci] = operation(val[left(ci)], val[right(ci)]);
    }

    private int left(int i) {
        return 2 * i + 1;
    }

    private int right(int i) {
        return 2 * i + 2;
    }

    private int operation(int first, int second) {
        return first + second;
    }

    private void makePendingUpdates(int segmentTreeNode, int left, int right) {
        if (lazy[segmentTreeNode] != 0) {
            val[segmentTreeNode] += (right - left + 1) * lazy[segmentTreeNode];
            if (left != right) {
                lazy[left(segmentTreeNode)] += lazy[segmentTreeNode];
                lazy[right(segmentTreeNode)] += lazy[segmentTreeNode];
            }
            lazy[segmentTreeNode] = 0;
        }
    }

    private int build(int currIndex, int left, int right) {
        if (left == right) {
            return this.val[currIndex] = this.original[left];
        }
        int mid = (left + right) / 2;
        return this.val[currIndex] = operation(build(left(currIndex), left, mid),
                build(right(currIndex), mid + 1, right));
    }
}
