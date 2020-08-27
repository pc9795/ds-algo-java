package gfg.ds.tree.binary_search_tree.balanced;

import gfg.ds.tree.binary_search_tree.BinarySearchTree;

public class SplayTree extends BinarySearchTree {
  /** t=O(1) Similar to right rotation NOTE: It MODIFIES the links of input node. */
  private BinaryTreeNode zig(BinaryTreeNode node) {
    assert node.left != null;

    BinaryTreeNode leftRight = node.left.right;
    node.left.right = node;
    BinaryTreeNode newNode = node.left;
    node.left = leftRight;

    return newNode;
  }

  /** t=O(1) Similar to left rotation NOTE: It MODIFIES the links of input node. */
  private BinaryTreeNode zag(BinaryTreeNode node) {
    assert node.right != null;

    BinaryTreeNode rightLeft = node.right.left;
    node.right.left = node;
    BinaryTreeNode newNode = node.right;
    node.right = rightLeft;

    return newNode;
  }

  /** t=O(log n) */
  private BinaryTreeNode splay(BinaryTreeNode node, int data) {
    if (node == null || node.data == data) {
      return node;
    }
    if (data < node.data) {
      if (node.left == null) {
        return node;
      }
      if (data < node.left.data) {
        node.left.left = splay(node.left.left, data);
        node = zig(node);
      } else {
        node.left.right = splay(node.left.right, data);
        if (node.left.right != null) {
          node.left = zag(node.left);
        }
      }
      // We are again checking as left can be changed due to previous operations.
      return node.left == null ? node : zig(node);
    } else {
      if (node.right == null) {
        return node;
      }
      if (data > node.right.data) {
        node.right.right = splay(node.right.right, data);
        node = zag(node);
      } else {
        node.right.left = splay(node.right.left, data);
        if (node.right.left != null) {
          node.right = zig(node.right);
        }
      }
      // We are again checking as right can be changed due to previous operations.
      return node.right == null ? node : zag(node);
    }
  }

  /** t=O(log n) */
  @Override
  public SplayTree insert(int data) {
    if (isEmpty()) {
      root = new BinaryTreeNode(data);
      return this;
    }
    root = splay(root, data);
    if (data < root.data) {
      BinaryTreeNode newNode = new BinaryTreeNode(data);
      newNode.right = root;
      // todo explanation that why this will not break the BST property
      newNode.left = root.left;
      root.left = null;
      root = newNode;
    } else if (data > root.data) {
      BinaryTreeNode newNode = new BinaryTreeNode(data);
      newNode.left = root;
      // todo explanation that why this will not break the BST property
      newNode.right = root.right;
      root.right = null;
      root = newNode;
    } else {
      throw new RuntimeException("Duplicate data");
    }

    return this;
  }

  @Override
  public boolean search(int data) {
    root = splay(root, data);
    return root != null && root.data == data;
  }

  @Override
  public SplayTree delete(int key) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
