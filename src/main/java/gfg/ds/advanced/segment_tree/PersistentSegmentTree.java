package gfg.ds.advanced.segment_tree;

import gfg.ds.tree.binary_tree.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 17-02-2019 01:58
 * Persisitency means retaining the changes
 **/
public class PersistentSegmentTree {
    private BinaryTree.BinaryTreeNode root;
    public List<BinaryTree.BinaryTreeNode> versions;
    private int[] original;

    public PersistentSegmentTree(int arr[]) {
        original = Arrays.copyOf(arr, arr.length);
        this.root = build(0, arr.length - 1);
        versions = new ArrayList<>();
        versions.add(this.root);
    }

    /**
     * t=O(n)
     *
     * @param left  left limit of the bound
     * @param right right limit of the bound
     * @return node representing data for bound
     */
    private BinaryTree.BinaryTreeNode build(int left, int right) {
        if (left == right) {
            return new BinaryTree.BinaryTreeNode(this.original[left]);
        }
        int mid = (left + right) / 2;
        BinaryTree.BinaryTreeNode leftChild = build(left, mid);
        BinaryTree.BinaryTreeNode rightChild = build(mid + 1, right);
        BinaryTree.BinaryTreeNode root = new BinaryTree.BinaryTreeNode(leftChild.data + rightChild.data);
        root.left = leftChild;
        root.right = rightChild;
        return root;
    }


    /**
     * t=O(log n)
     *
     * @param ql left limit of query
     * @param qr right limit of query
     * @return result of the query
     */
    public int query(int ql, int qr) {
        return queryUtil(ql, qr, 0, this.original.length - 1, this.root);
    }

    private int queryUtil(int ql, int qr, int sl, int sr, BinaryTree.BinaryTreeNode root) {
        //Inside range
        if (ql <= sl && qr >= sr) {
            return root.data;
        }
        //Outside range
        if (qr < sl || ql > sr) {
            return 0;
        }
        int mid = (sl + sr) / 2;
        return queryUtil(ql, qr, sl, mid, root.left) + queryUtil(ql, qr, mid + 1, sr, root.right);
    }

    /**
     * t=O(log n)
     * At each update only log n nodes will be affected
     *
     * @param index  index of the array to update
     * @param newVal new value at the index
     */
    public void update(int index, int newVal) {
        int increment = newVal - this.original[index];
        this.original[index] = newVal;
        BinaryTree.BinaryTreeNode newRoot = updateUtil(0, this.original.length - 1, index, this.root, increment);
        this.root = newRoot;
        this.versions.add(newRoot);
    }

    private BinaryTree.BinaryTreeNode updateUtil(int sl, int sr, int index, BinaryTree.BinaryTreeNode root, int increment) {
        if (root == null) {
            return null;
        }
        if (index < sl || index > sr) {
            return null;
        }
        BinaryTree.BinaryTreeNode newRoot = new BinaryTree.BinaryTreeNode(root.data + increment);
        int mid = (sl + sr) / 2;
        BinaryTree.BinaryTreeNode leftChild = updateUtil(sl, mid, index, root.left, increment);
        BinaryTree.BinaryTreeNode rightChild = updateUtil(mid + 1, sr, index, root.right, increment);
        newRoot.left = leftChild == null ? root.left : leftChild;
        newRoot.right = rightChild == null ? root.right : rightChild;
        return newRoot;
    }

    public void migrate(int version) {
        assert version < versions.size();

        this.root = versions.get(version);
    }
}
