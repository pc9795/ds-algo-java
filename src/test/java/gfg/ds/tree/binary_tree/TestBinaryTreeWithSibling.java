package gfg.ds.tree.binary_tree;

import org.junit.jupiter.api.Test;

class TestBinaryTreeWithSibling {
  @Test
  void testUpdateSibling() {
    /*
           1
         / \
        2   3
       / \   \
      4   5   6
    */
    BinaryTreeWithSibling bt = new BinaryTreeWithSibling().insertAtRoot(1);
    bt.root.left = new BinaryTreeWithSibling.BinaryTreeNode(2);
    bt.root.left.left = new BinaryTreeWithSibling.BinaryTreeNode(4);
    bt.root.left.right = new BinaryTreeWithSibling.BinaryTreeNode(5);
    bt.root.right = new BinaryTreeWithSibling.BinaryTreeNode(3);
    bt.root.right.right = new BinaryTreeWithSibling.BinaryTreeNode(6);

    bt.updateRightSiblings();
    assert bt.root.rightSibling == null;
    assert bt.root.left.rightSibling == bt.root.right;
    assert bt.root.right.rightSibling == null;
    assert bt.root.left.left.rightSibling == bt.root.left.right;
    assert bt.root.left.right.rightSibling == bt.root.right.right;
    assert bt.root.right.right.rightSibling == null;
  }

  @Test
  void testIsCompleteBST() {
    /*
           1
         / \
        2   3
       / \   \
      4   5   6
    */
    BinaryTreeWithSibling bt = new BinaryTreeWithSibling().insertAtRoot(1);
    bt.root.left = new BinaryTreeWithSibling.BinaryTreeNode(2);
    bt.root.left.left = new BinaryTreeWithSibling.BinaryTreeNode(4);
    bt.root.left.right = new BinaryTreeWithSibling.BinaryTreeNode(5);
    bt.root.right = new BinaryTreeWithSibling.BinaryTreeNode(3);
    bt.root.right.right = new BinaryTreeWithSibling.BinaryTreeNode(6);
    assert !BinaryTreeWithSibling.isCompleteBST(bt);

    bt.root.right.left = new BinaryTreeWithSibling.BinaryTreeNode(7);
    assert BinaryTreeWithSibling.isCompleteBST(bt);
  }

  @Test
  void testUpdateSibling2() {
    /*
            1
         /   \
        2     3
       / \   / \
      4   5 6   7
    */
    BinaryTreeWithSibling bt = new BinaryTreeWithSibling().insertAtRoot(1);
    bt.root.left = new BinaryTreeWithSibling.BinaryTreeNode(2);
    bt.root.left.left = new BinaryTreeWithSibling.BinaryTreeNode(4);
    bt.root.left.right = new BinaryTreeWithSibling.BinaryTreeNode(5);
    bt.root.right = new BinaryTreeWithSibling.BinaryTreeNode(3);
    bt.root.right.right = new BinaryTreeWithSibling.BinaryTreeNode(6);
    bt.root.right.left = new BinaryTreeWithSibling.BinaryTreeNode(7);

    bt.updateRightSiblings2();
    assert bt.root.rightSibling == null;
    assert bt.root.left.rightSibling == bt.root.right;
    assert bt.root.right.rightSibling == null;
    assert bt.root.left.left.rightSibling == bt.root.left.right;
    assert bt.root.left.right.rightSibling == bt.root.right.left;
    assert bt.root.right.left.rightSibling == bt.root.right.right;
    assert bt.root.right.right.rightSibling == null;
  }
}
