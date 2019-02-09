package binary_tree;

import geeks_for_geeks.ds.nodes.BTNodeWithRandom;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTreeApplications;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTreeWithRandomNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created By: Prashant Chaubey
 * Created On: 26-01-2019 19:46
 * Purpose: TODO:
 **/
public class TestBinaryTree {
    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void testToList() {
        BinaryTree bt = new BinaryTree(10);
        bt.root.left = new BTNode(12);
        bt.root.right = new BTNode(15);
        bt.root.left.left = new BTNode(25);
        bt.root.left.right = new BTNode(30);
        bt.root.right.left = new BTNode(36);
        System.out.println(bt.toList());
    }

    @Test
    void testMorisTraversal() {
        System.setOut(new PrintStream(testOut));

        BinaryTree bt = new BinaryTree(1);
        bt.root.left = new BTNode(2);
        bt.root.right = new BTNode(3);
        bt.root.left.left = new BTNode(4);
        bt.root.left.right = new BTNode(5);

        BTNode rightChildOfLeftChildOfRoot = bt.root.left.right;
        rightChildOfLeftChildOfRoot.left = new BTNode(6);
        rightChildOfLeftChildOfRoot.right = new BTNode(7);
        rightChildOfLeftChildOfRoot.right.right = new BTNode(8);

        BinaryTree.morisTraversal(bt);
        Assertions.assertEquals(testOut.toString(), "4->2->6->5->7->8->1->3->" + System.lineSeparator());

        System.setOut(originalOut);
    }

    @Test
    void testClone() {
        BinaryTreeWithRandomNode bt = new BinaryTreeWithRandomNode(1);
        bt.root.left = new BTNodeWithRandom(2);
        bt.root.right = new BTNodeWithRandom(3);
        bt.root.left.left = new BTNodeWithRandom(4);
        bt.root.left.right = new BTNodeWithRandom(5);
        bt.root.random = bt.root.left.right;
        bt.root.left.random = bt.root.right;
        System.out.println("Original:");
        BinaryTreeWithRandomNode.inOrderTraversal(bt);

        System.out.println("Cloned Using HashMap:");
        BinaryTreeWithRandomNode.inOrderTraversal(bt.cloneUsingHashMap());

        System.out.println("Clone:");
        BinaryTreeWithRandomNode.inOrderTraversal(bt.clone());
    }

    @Test
    void testCreateFromPreAndInOrder() {
        int[] preOrder = {1, 2, 4, 6, 7, 3, 4, 5};
        int[] inOrder = {4, 6, 2, 7, 1, 4, 3, 5};
        BinaryTree.levelOrderTraversal(BinaryTreeApplications.createFromPreAndInorder(preOrder, inOrder));
    }

    @Test
    void testPrintAncestors() {
        System.setOut(new PrintStream(testOut));

        BinaryTree bt = new BinaryTree(1);
        bt.root.left = new BTNode(2);
        bt.root.right = new BTNode(3);
        bt.root.left.left = new BTNode(4);
        bt.root.left.right = new BTNode(5);

        BinaryTreeApplications.printAncestors(bt, bt.root.left.left);

        Assertions.assertEquals(testOut.toString(), "2" + System.lineSeparator() + "1" + System.lineSeparator());

        System.setOut(originalOut);

    }

    @Test
    void testIsSubTree() {
        BinaryTree bt = new BinaryTree(1);
        bt.root.left = new BTNode(2);
        bt.root.right = new BTNode(3);
        bt.root.left.right = new BTNode(4);

        BinaryTree btSuper = new BinaryTree(5);
        btSuper.root.right = new BTNode(6);
        btSuper.root.right.right = new BTNode(7);
        btSuper.root.left = bt.root;

        BinaryTree bt2 = new BinaryTree(1);
        bt2.root.left = new BTNode(2);
        bt2.root.right = new BTNode(4);
        bt2.root.left.left = new BTNode(3);

        BinaryTree bt2Super = new BinaryTree(1);
        bt2Super.root.left = new BTNode(2);
        bt2Super.root.right = new BTNode(4);
        bt2Super.root.left.left = new BTNode(3);
        bt2Super.root.right.right = new BTNode(5);

        Assertions.assertTrue(bt.isSubTree(btSuper));
        Assertions.assertFalse(bt2.isSubTree(bt2Super));
    }


}
