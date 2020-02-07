package geeks_for_geeks.ds.advanced;

import geeks_for_geeks.ds.nodes.BTNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 17-02-2019 01:58
 **/
public class PersistentSegmentTree {
    private BTNode root;
    public List<BTNode> versions;
    private int[] original;

    public PersistentSegmentTree(int arr[]) {
        original = Arrays.copyOf(arr, arr.length);
        this.root = build(0, arr.length - 1);
        versions = new ArrayList<>();
        versions.add(this.root);
    }

    private BTNode build(int left, int right) {
        if (left == right) {
            return new BTNode(this.original[left]);
        }
        int mid = (left + right) / 2;
        BTNode leftChild = build(left, mid);
        BTNode rightChild = build(mid + 1, right);
        BTNode root = new BTNode(leftChild.data + rightChild.data);
        root.left = leftChild;
        root.right = rightChild;
        return root;
    }


    public int query(int ql, int qr) {
        return queryUtil(ql, qr, 0, this.original.length - 1, this.root);
    }

    private int queryUtil(int ql, int qr, int sl, int sr, BTNode root) {
        if (ql <= sl && qr >= sr) {
            return root.data;
        }
        if (qr < sl || ql > sr) {
            return 0;
        }
        int mid = (sl + sr) / 2;
        return queryUtil(ql, qr, sl, mid, root.left) +
                queryUtil(ql, qr, mid + 1, sr, root.right);
    }

    public void update(int index, int newVal) {
        int diff = newVal - this.original[index];
        this.original[index] = newVal;
        BTNode newRoot = updateUtil(0, this.original.length - 1, index, this.root, diff);
        this.root = newRoot;
        this.versions.add(newRoot);
    }

    private BTNode updateUtil(int sl, int sr, int index, BTNode root, int diff) {
        if (root == null) {
            return null;
        }
        if (index < sl || index > sr) {
            return null;
        }
        BTNode newRoot = new BTNode(root.data + diff);
        int mid = (sl + sr) / 2;
        BTNode leftChild = updateUtil(sl, mid, index, root.left, diff);
        BTNode rightChild = updateUtil(mid + 1, sr, index, root.right, diff);
        newRoot.left = leftChild == null ? root.left : leftChild;
        newRoot.right = rightChild == null ? root.right : rightChild;

        return newRoot;
    }

    public void migrate(int version) {
        assert version < versions.size();
        this.root = versions.get(version);
    }
}
