package geeks_for_geeks.binary_search_tree;

import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_search_tree.Applications;
import geeks_for_geeks.ds.tree.binary_search_tree.BinarySearchTree;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Pair;

import java.util.Arrays;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 10-02-2019 13:18
 **/
class TestBinarySearchTree {
    @Test
    void testMergedInorder() {
        BinarySearchTree bst1 = new BinarySearchTree(3).insert(1).insert(5);
        BinarySearchTree bst2 = new BinarySearchTree(4).insert(2).insert(6);

        List<Integer> traversal = Applications.mergedInorder(bst1, bst2);
        assert traversal.equals(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    void testFindPairWithGivenSum() {
        BinarySearchTree bst = new BinarySearchTree(15).insert(10).insert(20).insert(8).insert(12).insert(16).insert(25);

        Pair<BTNode, BTNode> result = Applications.findPairWithGivenSum(bst, 33);
        assert result.equals(new Pair<>(new BTNode(8), new BTNode(25)));

        result = Applications.findPairWithGivenSum(bst, 24);
        assert result.equals(new Pair<>(new BTNode(8), new BTNode(16)));

        result = Applications.findPairWithGivenSum(bst, 100);
        assert result.key == null;
        assert result.value == null;
    }

    @Test
    void testConvertBinaryTreeToBST() {
        BinaryTree bt = new BinaryTree(10).insertAtPos("0", 2).insertAtPos("1", 7).
                insertAtPos("00", 8).insertAtPos("01", 4);
        Applications.convertBinaryTreeToBST(bt);
        assert BinarySearchTree.isBST(bt);
    }

    private BinarySearchTree bst1;
    private BinarySearchTree bst2;
    private BinarySearchTree bst3;
    private BinarySearchTree bst4;
    private BinarySearchTree bst5;

    @BeforeEach
    void setup() {
        /*
         *     1
         */
        bst1 = new BinarySearchTree(1);
        /*
         *    5
         *   /
         *  1
         */
        bst2 = new BinarySearchTree(5).insert(1);
        /*
         *      5
         *     /
         *    1
         *     \
         *      3
         */
        bst3 = new BinarySearchTree(5).insert(1).insert(3);
        /*
         *      5
         *     /  \
         *    2    6
         *   /  \
         *  1    4
         *      /
         *     3
         */
        bst4 = new BinarySearchTree(5).insert(6).insert(2).insert(1).insert(4).insert(3);
        /*
         *      18
         *     /  \
         *    10    111
         *   /  \
         *  1    14
         *      /
         *     12
         */
        bst5 = new BinarySearchTree(18).insert(111).insert(10).insert(1).insert(14).insert(12);

    }

    @Test
    void testDeleteNoChild() {
        bst1.delete(1);
        assert bst1.isEmpty();

        bst2 = new BinarySearchTree(5).insert(1);
        bst2.delete(1);
        assert bst2.equals(new BinarySearchTree(5));
    }

    @Test
    void testDeleteSingleChild() {
        bst2.delete(5);
        assert bst2.equals(new BinarySearchTree(1));

        bst3.delete(1);
        BinarySearchTree expected = new BinarySearchTree(5).insert(3);
        assert bst3.equals(expected);
    }

    @Test
    void testDeleteRoot() {
        bst4.delete(5);
        BinarySearchTree expected = new BinarySearchTree(6).insert(2).insert(4).insert(1).insert(3);
        assert bst4.equals(expected);
    }

    @Test
    void testDeleteInternalNode() {
        bst4.delete(2);
        BinarySearchTree expected = new BinarySearchTree(5).insert(6).insert(3).insert(1).insert(4);
        assert bst4.equals(expected);
    }

    @Test
    void testIsBST() {
        assert BinarySearchTree.isBST(bst1);
        assert BinarySearchTree.isBST(bst2);
        assert BinarySearchTree.isBST(bst3);
        assert BinarySearchTree.isBST(bst4);
    }

    @Test
    void testFindPreSuc() {
        Pair<BTNode, BTNode> ans = bst4.findPreSuc(1);
        assert ans.key == null;
        assert ans.value.data == 2;

        ans = bst4.findPreSuc(4);
        assert ans.equals(new Pair<>(new BTNode(3), new BTNode(5)));
    }

    @Test
    void testLCA() {
        assert bst4.lca(1, 3) == 2;
        assert bst4.lca(1, 6) == 5;
    }

    @Test
    void testKthSmallest() {
        assert BinarySearchTree.kthSmallest(bst4, 3) == 3;
        assert BinarySearchTree.kthSmallest(bst4, 5) == 5;
        assert BinarySearchTree.kthSmallest(bst4, 6) == 6;
    }

    @Test
    void testCorrectBSTIfTwoNodesAreSwappedAdjacent() {
        int temp = bst4.root.data;
        bst4.root.data = bst4.root.right.data;
        bst4.root.right.data = temp;
        assert !BinarySearchTree.isBST(bst4);
        Applications.correctBSTIfTwoNodesAreSwapped(bst4);
        assert BinarySearchTree.isBST(bst4);
    }

    @Test
    void testCorrectBSTIfTwoNodesAreSwapped() {
        int temp = bst4.root.left.data;
        bst4.root.data = bst4.root.left.right.left.data;
        bst4.root.left.right.left.data = temp;
        assert !BinarySearchTree.isBST(bst4);
        Applications.correctBSTIfTwoNodesAreSwapped(bst4);
        assert BinarySearchTree.isBST(bst4);
    }

    @Test
    void testCeil() {
        assert bst5.ceil(2) == 10;
        assert bst5.ceil(1) == 1;
        assert bst5.ceil(112) == Integer.MIN_VALUE;
    }
}
