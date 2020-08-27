package gfg.ds.tree.binary_search_tree.balanced;

import java.util.function.Supplier;

public class Treap {
  private TreapNode root;
  private Supplier<Integer> prioritySupplier;

  public Treap(Supplier<Integer> prioritySupplier) {
    this.prioritySupplier = prioritySupplier;
  }

  public TreapNode getRoot() {
    return root;
  }

  /** t=O(log n); it is expected to be log n as it is a randomized BST we can't say it for sure. */
  public boolean search(int key) {
    for (TreapNode curr = root; curr != null; ) {
      if (curr.data == key) {
        return true;
      }
      curr = key < curr.data ? curr.left : curr.right;
    }
    return false;
  }

  public Treap insert(int... values) {
    for (int data : values) {
      insert(data);
    }
    return this;
  }

  /** t=O(log n); it is expected to be log n as it is a randomized BST we can't say it for sure. */
  public Treap insert(int data) {
    root = insertUtil(root, data);
    return this;
  }

  private TreapNode insertUtil(TreapNode root, int data) {
    if (root == null) {
      return new TreapNode(data, prioritySupplier.get());
    }

    assert root.data != data : "Duplicate data";

    if (data < root.data) {
      root.left = insertUtil(root.left, data);
      if (root.left.priority > root.priority) {
        root = rightRotate(root);
      }
    } else {
      root.right = insertUtil(root.right, data);
      if (root.right.priority > root.priority) {
        root = leftRotate(root);
      }
    }

    return root;
  }

  /** t=O(log n); it is expected to be log n as it is a randomized BST we can't say it for sure. */
  public Treap delete(int data) {
    root = deleteUtil(root, data);
    return this;
  }

  private TreapNode deleteUtil(TreapNode root, int data) {
    if (root == null) {
      throw new RuntimeException("Element not found");
    }

    if (data < root.data) {
      root.left = deleteUtil(root.left, data);
    } else if (data > root.data) {
      root.right = deleteUtil(root.right, data);
    } else {
      if (isLeaf(root)) {
        return null;
      }

      // Single child cases
      if (root.left == null) {
        return root.right;
      }
      if (root.right == null) {
        return root.left;
      }

      // Both children are present
      if (root.right.priority > root.left.priority) {
        root = leftRotate(root);
        root.left = deleteUtil(root.left, data);
      } else {
        root = rightRotate(root);
        root.right = deleteUtil(root.right, data);
      }
    }
    return root;
  }

  private boolean isLeaf(TreapNode node) {
    return node != null && node.left == null && node.right == null;
  }

  private TreapNode rightRotate(TreapNode node) {
    assert node.left != null;

    TreapNode leftRight = node.left.right;
    node.left.right = node;
    TreapNode newNode = node.left;
    node.left = leftRight;

    return newNode;
  }

  private TreapNode leftRotate(TreapNode node) {
    assert node.right != null;

    TreapNode rightLeft = node.right.left;
    node.right.left = node;
    TreapNode newNode = node.right;
    node.right = rightLeft;

    return newNode;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public static class TreapNode {

    private TreapNode left;
    private TreapNode right;
    private int data;
    private int priority;

    public TreapNode(int data, int priority) {
      this.data = data;
      this.priority = priority;
    }

    public TreapNode getLeft() {
      return left;
    }

    public TreapNode getRight() {
      return right;
    }

    public int getData() {
      return data;
    }

    public int getPriority() {
      return priority;
    }
  }
}
