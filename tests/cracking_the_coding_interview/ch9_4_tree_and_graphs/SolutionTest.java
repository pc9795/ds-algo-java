package cracking_the_coding_interview.ch9_4_tree_and_graphs;

import geeks_for_geeks.ds.array.Array;
import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_search_tree.BinarySearchTree;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Stream;

/**
 * Created By: Prashant Chaubey
 * Created On: 08-09-2019 15:33
 * Purpose: TODO:
 **/
public class SolutionTest {

    @Test
    void testMinimalTree() {
        BinarySearchTree bst = Solution.minimalTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assert BinarySearchTree.isBST(bst);
    }

    @Test
    void testListOfDepths() {
        BinaryTree bt = new BinaryTree(1);
        bt.root.left = new BTNode(2);
        bt.root.right = new BTNode(3);
        bt.root.left.left = new BTNode(4);
        bt.root.left.right = new BTNode(5);
        bt.root.right.left = new BTNode(6);
        bt.root.right.right = new BTNode(7);

        ArrayList<LinkedList<BTNode>> expected = new ArrayList<>();
        expected.add(new LinkedList<>());
        expected.add(new LinkedList<>());
        expected.add(new LinkedList<>());
        expected.get(0).add(new BTNode(1));
        expected.get(1).add(new BTNode(2));
        expected.get(1).add(new BTNode(3));
        expected.get(2).add(new BTNode(4));
        expected.get(2).add(new BTNode(5));
        expected.get(2).add(new BTNode(6));
        expected.get(2).add(new BTNode(7));

        assert Solution.listOfDepths(bt).equals(expected);
    }

    static Stream<Arguments> testCheckBalanced() {
        BinaryTree bt = new BinaryTree(1);
        bt.root.left = new BTNode(2);
        bt.root.right = new BTNode(3);
        bt.root.left.left = new BTNode(4);
        bt.root.left.left.left = new BTNode(5);

        BinaryTree bt2 = new BinaryTree(1);
        bt2.root.left = new BTNode(2);
        bt2.root.right = new BTNode(3);
        bt2.root.left.left = new BTNode(4);
        return Stream.of(Arguments.of(bt, false), Arguments.of(bt2, true));
    }

    @ParameterizedTest
    @MethodSource
    void testCheckBalanced(BinaryTree bt, boolean ans) {
        assert Solution.checkBalanced(bt) == ans;
    }

    static Stream<Arguments> testFirstCommonAncestor() {
        BinaryTree bt = new BinaryTree(1);
        bt.root.left = new BTNode(2);
        bt.root.left.parent = bt.root;
        bt.root.right = new BTNode(3);
        bt.root.right.parent = bt.root;
        bt.root.left.left = new BTNode(4);
        bt.root.left.left.parent = bt.root.left;
        bt.root.left.right = new BTNode(5);
        bt.root.left.right.parent = bt.root.left;

        BinaryTree bt2 = new BinaryTree(1);
        bt2.root.left = new BTNode(2);
        bt2.root.left.parent = bt2.root;
        bt2.root.right = new BTNode(4);
        bt2.root.right.parent = bt2.root;
        bt2.root.left.left = new BTNode(3);
        bt2.root.left.left.parent = bt2.root.left;
        bt2.root.right.right = new BTNode(6);
        bt2.root.right.right.parent = bt2.root.right;

        BinaryTree bt3 = new BinaryTree(1);
        bt3.root.left = new BTNode(2);
        bt3.root.left.parent = bt3.root;
        bt3.root.left.left = new BTNode(4);
        bt3.root.left.left.parent = bt3.root.left;
        bt3.root.left.right = new BTNode(3);
        bt3.root.left.right.parent = bt3.root.left;
        bt3.root.left.right.right = new BTNode(5);
        bt3.root.left.right.right.parent = bt3.root.left.right;
        bt3.root.left.right.right.right = new BTNode(6);
        bt3.root.left.right.right.right.parent = bt3.root.left.right.right;

        return Stream.of(
                Arguments.of(bt.root.left.left, bt.root.left.right, bt.root.left),
                Arguments.of(bt2.root.left.left, bt2.root.right.right, bt2.root),
                Arguments.of(bt3.root.left.left, bt3.root.left.right.right.right, bt3.root.left)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testFirstCommonAncestor(BTNode first, BTNode second, BTNode expected) {
        assert Solution.firstCommonAncestor(first, second).equals(expected);
    }

    @ParameterizedTest
    @MethodSource("testFirstCommonAncestor")
    void testFirstCommonAncestor2(BTNode first, BTNode second, BTNode expected) {
        assert Solution.firstCommonAncestor2(first, second).equals(expected);
    }

    @ParameterizedTest
    @MethodSource("testFirstCommonAncestor")
    void testFirstCommonAncestorWithoutParentLinks(BTNode first, BTNode second, BTNode expected) {
        BTNode root = first;
        while (root.parent != null) {
            root = root.parent;
        }
        assert Solution.firstCommonAncestorWithoutParentLinks(root, first, second).equals(expected);
    }

    @ParameterizedTest
    @MethodSource("testFirstCommonAncestor")
    void testFirstCommonAncesotrWithoutParentLinks2(BTNode first, BTNode second, BTNode expected) {
        BTNode root = first;
        while (root.parent != null) {
            root = root.parent;
        }
        assert Solution.firstCommonAncestorWithoutParentLinks2(root, first, second).equals(expected);
    }

    @Test
    void testWeaveLists() {
        LinkedList<Integer> list1 = new LinkedList<>();
        list1.add(1);
        list1.add(2);
        LinkedList<Integer> list2 = new LinkedList<>();
        list2.add(3);
        list2.add(4);
        ArrayList<LinkedList<Integer>> result = new ArrayList<>();
        Solution.weaveLists(list1, list2, result, new LinkedList<>());
        ArrayList<LinkedList<Integer>> expected = new ArrayList<>();
        expected.add(new LinkedList<>(Arrays.asList(1, 2, 3, 4)));
        expected.add(new LinkedList<>(Arrays.asList(1, 3, 2, 4)));
        expected.add(new LinkedList<>(Arrays.asList(1, 3, 4, 2)));
        expected.add(new LinkedList<>(Arrays.asList(4, 2, 1, 3)));
        expected.add(new LinkedList<>(Arrays.asList(4, 2, 3, 1)));
        expected.add(new LinkedList<>(Arrays.asList(4, 3, 1, 2)));
        assert expected.equals(result);
    }

    @Test
    void testBSTSequences() {
        BinarySearchTree bst = new BinarySearchTree(50);
        bst.insert(20).insert(60).insert(10).insert(25).insert(70);
        Solution.BSTSequences(bst).forEach(obj -> {
            System.out.println(obj);
        });
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

        Assertions.assertTrue(Solution.isSubtree(btSuper, bt));
        Assertions.assertFalse(Solution.isSubtree(bt2Super, bt2));
    }

    static Stream<Arguments> testPathsWithSum() {
        BinaryTree bt = new BinaryTree(10);
        bt.root.left = new BTNode(5);
        bt.root.right = new BTNode(-3);
        bt.root.left.left = new BTNode(3);
        bt.root.left.right = new BTNode(2);
        bt.root.right.right = new BTNode(11);
        bt.root.left.left.left = new BTNode(3);
        bt.root.left.left.right = new BTNode(-2);
        bt.root.left.right.right = new BTNode(1);
        return Stream.of(
                Arguments.of(bt, 8, 3)
        );
    }

    @ParameterizedTest
    @MethodSource
    void testPathsWithSum(BinaryTree bt, int targetSum, int expected) {
        assert Solution.pathsWithSum(bt, targetSum) == expected;
    }

    @ParameterizedTest
    @MethodSource("testPathsWithSum")
    void testPathsWithSum2(BinaryTree bt, int targetSum, int expected) {
        assert Solution.pathsWithSum2(bt, targetSum) == expected;
    }
}
