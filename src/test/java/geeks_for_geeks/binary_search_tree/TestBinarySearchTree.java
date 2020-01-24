package geeks_for_geeks.binary_search_tree;

import geeks_for_geeks.ds.tree.binary_search_tree.BinarySearchTree;
import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_search_tree.Applications;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import javafx.util.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created By: Prashant Chaubey
 * Created On: 10-02-2019 13:18
 * Purpose: TODO:
 **/
class TestBinarySearchTree {
    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    void testMergedInorder() {
        System.setOut(new PrintStream(testOut));
        BinarySearchTree bt1 = new BinarySearchTree(3);
        bt1.root.left = new BTNode(1);
        bt1.root.right = new BTNode(5);

        BinarySearchTree bt2 = new BinarySearchTree(4);
        bt2.root.left = new BTNode(2);
        bt2.root.right = new BTNode(6);

        Applications.mergedInorder(bt1, bt2);
        Assertions.assertEquals(testOut.toString(), "1->2->3->4->5->6->" + System.lineSeparator());

        System.setOut(originalOut);
        System.out.println(testOut.toString());
    }

    @Test
    void testFindPairWithGivenSum() {
        BinarySearchTree bst = new BinarySearchTree(15);
        bst.insert(10).insert(20).insert(8).insert(12).insert(16).insert(25);

        Pair<BTNode, BTNode> result = Applications.findPairWithGivenSum(bst, 33);

        Assertions.assertEquals(result.getKey().data, 8);
        Assertions.assertEquals(result.getValue().data, 25);

        result = Applications.findPairWithGivenSum(bst, 24);

        Assertions.assertEquals(result.getKey().data, 8);
        Assertions.assertEquals(result.getValue().data, 16);

        result = Applications.findPairWithGivenSum(bst, 100);

        Assertions.assertNull(result.getKey());
        Assertions.assertNull(result.getValue());

    }

    @Test
    void testConvertBinaryTreeToBST() {
        BinaryTree bt = new BinaryTree(10);
        bt.root.left = new BTNode(2);
        bt.root.right = new BTNode(7);
        bt.root.left.left = new BTNode(8);
        bt.root.left.right = new BTNode(4);

        Applications.convertBinaryTreeToBST(bt);

        Assertions.assertTrue(BinarySearchTree.isBST(bt));
    }
}
