package gfg.ds.advanced;

/** @noinspection WeakerAccess */
public class CartesianTree {
  private CartesianTreeNode root;
  private CartesianTreeNode rightMost;

  public CartesianTreeNode getRoot() {
    return root;
  }

  public CartesianTreeNode getRightMost() {
    return rightMost;
  }

  public CartesianTree insert(int... values) {
    for (int value : values) {
      insert(value);
    }
    return this;
  }

  /** t=O(n*log n); On average */
  public CartesianTree insert(int data) {
    if (isEmpty()) {
      root = rightMost = new CartesianTreeNode(data);
      return this;
    }

    CartesianTreeNode ancestor = rightMost;
    for (; ancestor != null; ) {
      if (ancestor.data > data) {
        CartesianTreeNode ancestorRightChild = ancestor.right;

        CartesianTreeNode newNode = new CartesianTreeNode(data);
        newNode.parent = ancestor;
        ancestor.right = newNode;

        newNode.left = ancestorRightChild;
        if (ancestorRightChild != null) {
          ancestorRightChild.parent = newNode;
        }
        break;
      }
      ancestor = ancestor.parent;
    }

    if (ancestor == null) {
      CartesianTreeNode newNode = new CartesianTreeNode(data);
      newNode.left = root;
      root.parent = newNode;
      root = newNode;
      rightMost = newNode;
      return this;
    }

    if (ancestor == rightMost) {
      rightMost = rightMost.right;
    }

    return this;
  }

  public boolean isEmpty() {
    return root == null;
  }

  public static class CartesianTreeNode {
    private int data;
    private CartesianTreeNode left;
    private CartesianTreeNode right;
    private CartesianTreeNode parent;

    public CartesianTreeNode(int data) {
      this.data = data;
    }

    public int getData() {
      return data;
    }

    public CartesianTreeNode getLeft() {
      return left;
    }

    public CartesianTreeNode getRight() {
      return right;
    }

    @Override
    public String toString() {
      return "CartesianTreeNode{" + "data=" + data + '}';
    }
  }
}
