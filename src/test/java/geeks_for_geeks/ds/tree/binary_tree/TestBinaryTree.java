package geeks_for_geeks.ds.tree.binary_tree;

import geeks_for_geeks.ds.linked_list.DoublyLinkedList;
import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.nodes.BTNodeWithRandom;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 26-01-2019 19:46
 * Purpose: Test
 **/
class TestBinaryTree {

    @Test
    void testDiameter() {
        BinaryTree bt = new BinaryTree().insertAtRoot(1).insertAtPos("0", 2).insertAtPos("1", 3).
                insertAtPos("00", 4).insertAtPos("01", 5);
        int ans = BinaryTree.diameter(bt);
        assert ans == 4;
    }

    @Test
    void testToList() {
        BinaryTree bt = new BinaryTree().insertAtRoot(10).insertAtPos("0", 12).insertAtPos("1", 15).
                insertAtPos("00", 25).insertAtPos("01", 30).insertAtPos("10", 36);
        assert bt.toList().equals(new DoublyLinkedList().append(25, 12, 30, 10, 36, 15));
    }

    @Test
    void testMorisTraversal() {
        BinaryTree bt = new BinaryTree().insertAtRoot(1).insertAtPos("0", 2).insertAtPos("1", 3)
                .insertAtPos("00", 4).insertAtPos("01", 5).insertAtPos("010", 6).
                        insertAtPos("011", 7).insertAtPos("0111", 8);
        List<Integer> expected = Arrays.asList(4, 2, 6, 5, 7, 8, 1, 3);

        boolean ans = BinaryTree.morisTraversal(bt).equals(expected);
        assert ans;
    }

    @Test
    void testClone() {
        BinaryTreeWithRandomNode bt = new BinaryTreeWithRandomNode().insertAtRoot(1);
        bt.root.left = new BTNodeWithRandom(2);
        bt.root.right = new BTNodeWithRandom(3);
        bt.root.left.left = new BTNodeWithRandom(4);
        bt.root.left.right = new BTNodeWithRandom(5);
        bt.root.random = bt.root.left.right;
        bt.root.left.random = bt.root.right;
        List<Integer> original = BinaryTreeWithRandomNode.inOrderTraversal(bt);

        List<Integer> ans = BinaryTreeWithRandomNode.inOrderTraversal(bt.copyUsingMap());
        assert ans.equals(original);

        ans = BinaryTreeWithRandomNode.inOrderTraversal(bt.copy());
        assert ans.equals(original);
    }

    @Test
    void testCreateFromPreAndInOrder() {
        /*
         *       1
         *    /    \
         *   2      3
         *  / \    /
         * 4   5  6
         */
        int[] inOrder = {4, 2, 5, 1, 6, 3};
        int[] preOrder = {1, 2, 4, 5, 3, 6};
        BinaryTree bt = Applications.createFromPreAndInorder(preOrder, inOrder);
        List<List<Integer>> traversal = BinaryTree.levelOrderTraversal(bt);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Collections.singletonList(1));
        expected.add(Arrays.asList(2, 3));
        expected.add(Arrays.asList(4, 5, 6));
        assert expected.equals(traversal);
        assert BinaryTree.preOrderTraversal(bt).equals(Arrays.asList(1, 2, 4, 5, 3, 6));
        assert BinaryTree.inOrderTraversal(bt).equals(Arrays.asList(4, 2, 5, 1, 6, 3));

    }

    @Test
    void testCreateFromPreAndInOrder2() {
        /*
         *       1
         *    /    \
         *   2      3
         *  / \    /
         * 4   5  6
         */
        int[] inOrder = {4, 2, 5, 1, 6, 3};
        int[] preOrder = {1, 2, 4, 5, 3, 6};
        BinaryTree bt = Applications.createFromPreAndInorder2(preOrder, inOrder);
        List<List<Integer>> traversal = BinaryTree.levelOrderTraversal(bt);
        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Collections.singletonList(1));
        expected.add(Arrays.asList(2, 3));
        expected.add(Arrays.asList(4, 5, 6));
        assert expected.equals(traversal);
        assert BinaryTree.preOrderTraversal(bt).equals(Arrays.asList(1, 2, 4, 5, 3, 6));
        assert BinaryTree.inOrderTraversal(bt).equals(Arrays.asList(4, 2, 5, 1, 6, 3));

    }

    @Test
    void testPrintAncestors() {
        BinaryTree bt = new BinaryTree().insertAtRoot(1).insertAtPos("0", 2).insertAtPos("1", 3)
                .insertAtPos("00", 4).insertAtPos("01", 5);
        List<Integer> ans = Applications.getAncestors(bt, new BTNode(4));
        assert ans.equals(Arrays.asList(2, 1));
    }

    @Test
    void testIsSubTree() {
        /*
         *    1
         *   / \
         *  2   3
         *   \
         *    4
         */
        BinaryTree subTree = new BinaryTree().insertAtRoot(1).insertAtPos("0", 2).insertAtPos("1", 3).
                insertAtPos("01", 4);
        /*
         *      5
         *     / \
         *    1   6
         *  /  \   \
         * 2    3   7
         *  \
         *   4
         */
        BinaryTree tree = new BinaryTree().insertAtRoot(5).insertAtPos("1", 6).insertAtPos("11", 7);
        tree.root.right = new BTNode(6);
        tree.root.right.right = new BTNode(7);
        tree.root.left = subTree.root;

        assert tree.isSubTree(subTree);

        /*
         *     1
         *    / \
         *   2   4
         *  /
         * 3
         */
        subTree = new BinaryTree().insertAtRoot(1).insertAtPos("0", 2).insertAtPos("1", 4).
                insertAtPos("00", 3);
        /*
         *       1
         *     /  \
         *    2    4
         *   /      \
         *  3        5
         */
        tree = new BinaryTree().insertAtRoot(1).insertAtPos("0", 2).insertAtPos("1", 4).
                insertAtPos("00", 3).insertAtPos("11", 5);
        assert !tree.isSubTree(subTree);
    }

    @Test
    void testGetMaxWidth() {
        /*
              1
            /  \
           2    3
         /  \    \
        4   5     8
                 /  \
                6   7
         */
        BinaryTree bt = new BinaryTree().insertAtRoot(1).insertAtPos("0", 2).insertAtPos("00", 4).
                insertAtPos("01", 5).insertAtPos("1", 3).insertAtPos("11", 8).
                insertAtPos("110", 6).insertAtPos("111", 7);
        assert Applications.getMaxWidth(bt) == 3;
    }

    @Test
    void testGetAtPos() {
          /*
              1
            /  \
           2    3
         /  \    \
        4   5     8
                 /  \
                6   7
         */
        BinaryTree bt = new BinaryTree().insertAtRoot(1).insertAtPos("0", 2).insertAtPos("00", 4).
                insertAtPos("01", 5).insertAtPos("1", 3).insertAtPos("11", 8).
                insertAtPos("110", 6).insertAtPos("111", 7);
        assert bt.getAtPos("0") == 2;
        assert bt.getAtPos("1") == 3;
        assert bt.getAtPos("00") == 4;
        assert bt.getAtPos("01") == 5;
        assert bt.getAtPos("11") == 8;
        assert bt.getAtPos("110") == 6;
        assert bt.getAtPos("111") == 7;
    }
}
