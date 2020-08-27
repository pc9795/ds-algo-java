package gfg.ds.advanced.segment_tree;

import gfg.ds.tree.binary_tree.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Persisitency means retaining the changes
 *
 * @noinspection WeakerAccess
 */
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

  /** t=O(n) */
  private BinaryTree.BinaryTreeNode build(int leftLimit, int rightLimit) {
    if (leftLimit == rightLimit) {
      return new BinaryTree.BinaryTreeNode(this.original[leftLimit]);
    }
    int mid = (leftLimit + rightLimit) / 2;
    BinaryTree.BinaryTreeNode leftChild = build(leftLimit, mid);
    BinaryTree.BinaryTreeNode rightChild = build(mid + 1, rightLimit);
    BinaryTree.BinaryTreeNode root =
        new BinaryTree.BinaryTreeNode(leftChild.data + rightChild.data);
    root.left = leftChild;
    root.right = rightChild;
    return root;
  }

  /** t=O(log n) */
  public int query(int queryLeftLimit, int queryRightLimit) {
    return queryUtil(queryLeftLimit, queryRightLimit, 0, this.original.length - 1, this.root);
  }

  private int queryUtil(
      int queryLeftLimit,
      int queryRightLimit,
      int treeLeftLimit,
      int treeRightLimit,
      BinaryTree.BinaryTreeNode root) {
    // Inside range
    if (queryLeftLimit <= treeLeftLimit && queryRightLimit >= treeRightLimit) {
      return root.data;
    }
    // Outside range
    if (queryRightLimit < treeLeftLimit || queryLeftLimit > treeRightLimit) {
      return 0;
    }
    int mid = (treeLeftLimit + treeRightLimit) / 2;
    return queryUtil(queryLeftLimit, queryRightLimit, treeLeftLimit, mid, root.left)
        + queryUtil(queryLeftLimit, queryRightLimit, mid + 1, treeRightLimit, root.right);
  }

  /** t=O(log n) At each update only log n nodes will be affected */
  public void update(int index, int newVal) {
    int increment = newVal - this.original[index];
    this.original[index] = newVal;
    BinaryTree.BinaryTreeNode newRoot =
        updateUtil(0, this.original.length - 1, index, this.root, increment);
    this.root = newRoot;
    this.versions.add(newRoot);
  }

  private BinaryTree.BinaryTreeNode updateUtil(
      int treeLeftLimit,
      int treeRightLimit,
      int index,
      BinaryTree.BinaryTreeNode root,
      int increment) {
    if (root == null) {
      return null;
    }
    if (index < treeLeftLimit || index > treeRightLimit) {
      return null;
    }
    BinaryTree.BinaryTreeNode newRoot = new BinaryTree.BinaryTreeNode(root.data + increment);
    int mid = (treeLeftLimit + treeRightLimit) / 2;
    BinaryTree.BinaryTreeNode leftChild =
        updateUtil(treeLeftLimit, mid, index, root.left, increment);
    BinaryTree.BinaryTreeNode rightChild =
        updateUtil(mid + 1, treeRightLimit, index, root.right, increment);
    newRoot.left = leftChild == null ? root.left : leftChild;
    newRoot.right = rightChild == null ? root.right : rightChild;
    return newRoot;
  }

  public void migrate(int version) {
    assert version < versions.size();

    this.root = versions.get(version);
  }
}
