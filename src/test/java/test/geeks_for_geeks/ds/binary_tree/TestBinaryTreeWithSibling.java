package test.geeks_for_geeks.ds.binary_tree;

import geeks_for_geeks.ds.nodes.BTNodeWithSibling;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTreeWithSibling;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 31-01-2020 14:40
 **/
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
        BinaryTreeWithSibling bt = new BinaryTreeWithSibling(1);
        bt.root.left = new BTNodeWithSibling(2);
        bt.root.left.left = new BTNodeWithSibling(4);
        bt.root.left.right = new BTNodeWithSibling(5);
        bt.root.right = new BTNodeWithSibling(3);
        bt.root.right.right = new BTNodeWithSibling(6);

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
        BinaryTreeWithSibling bt = new BinaryTreeWithSibling(1);
        bt.root.left = new BTNodeWithSibling(2);
        bt.root.left.left = new BTNodeWithSibling(4);
        bt.root.left.right = new BTNodeWithSibling(5);
        bt.root.right = new BTNodeWithSibling(3);
        bt.root.right.right = new BTNodeWithSibling(6);
        assert !BinaryTreeWithSibling.isCompleteBST(bt);

        bt.root.right.left = new BTNodeWithSibling(7);
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
        BinaryTreeWithSibling bt = new BinaryTreeWithSibling(1);
        bt.root.left = new BTNodeWithSibling(2);
        bt.root.left.left = new BTNodeWithSibling(4);
        bt.root.left.right = new BTNodeWithSibling(5);
        bt.root.right = new BTNodeWithSibling(3);
        bt.root.right.right = new BTNodeWithSibling(6);
        bt.root.right.left = new BTNodeWithSibling(7);

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
