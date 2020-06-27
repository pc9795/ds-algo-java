package ctci.ch9_4_tree_and_graphs;

import gfg.ds.tree.binary_search_tree.BinarySearchTree;
import gfg.ds.tree.binary_tree.BinaryTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created By: Prashant Chaubey
 * Created On: 08-09-2019 15:33
 **/
class SolutionTest {

    @Test
    void testMinimalTree() {
        BinarySearchTree bst = Solution.minimalTree(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        assert BinarySearchTree.isBST(bst);
    }

    @Test
    void testListOfDepths() {
        BinaryTree bt = new BinaryTree().insertAtRoot(1);
        bt.root.left = new BinaryTree.BinaryTreeNode(2);
        bt.root.right = new BinaryTree.BinaryTreeNode(3);
        bt.root.left.left = new BinaryTree.BinaryTreeNode(4);
        bt.root.left.right = new BinaryTree.BinaryTreeNode(5);
        bt.root.right.left = new BinaryTree.BinaryTreeNode(6);
        bt.root.right.right = new BinaryTree.BinaryTreeNode(7);

        ArrayList<LinkedList<BinaryTree.BinaryTreeNode>> expected = new ArrayList<>();
        expected.add(new LinkedList<>());
        expected.add(new LinkedList<>());
        expected.add(new LinkedList<>());
        expected.get(0).add(new BinaryTree.BinaryTreeNode(1));
        expected.get(1).add(new BinaryTree.BinaryTreeNode(2));
        expected.get(1).add(new BinaryTree.BinaryTreeNode(3));
        expected.get(2).add(new BinaryTree.BinaryTreeNode(4));
        expected.get(2).add(new BinaryTree.BinaryTreeNode(5));
        expected.get(2).add(new BinaryTree.BinaryTreeNode(6));
        expected.get(2).add(new BinaryTree.BinaryTreeNode(7));

        List<LinkedList<BinaryTree.BinaryTreeNode>> ans = Solution.listOfDepths(bt);
        assert ans.equals(expected);
    }

    private static Stream<Arguments> testCheckBalanced() {
        BinaryTree bt = new BinaryTree().insertAtRoot(1);
        bt.root.left = new BinaryTree.BinaryTreeNode(2);
        bt.root.right = new BinaryTree.BinaryTreeNode(3);
        bt.root.left.left = new BinaryTree.BinaryTreeNode(4);
        bt.root.left.left.left = new BinaryTree.BinaryTreeNode(5);

        BinaryTree bt2 = new BinaryTree().insertAtRoot(1);
        bt2.root.left = new BinaryTree.BinaryTreeNode(2);
        bt2.root.right = new BinaryTree.BinaryTreeNode(3);
        bt2.root.left.left = new BinaryTree.BinaryTreeNode(4);
        return Stream.of(Arguments.of(bt, false), Arguments.of(bt2, true));
    }

    @ParameterizedTest
    @MethodSource("testCheckBalanced")
    void testCheckBalanced(BinaryTree bt, boolean expected) {
        boolean ans = Solution.checkBalanced(bt);
        assert ans == expected;
    }

    private static Stream<Arguments> testFirstCommonAncestor() {
        BinaryTree bt = new BinaryTree().insertAtRoot(1);
        bt.root.left = new BinaryTree.BinaryTreeNode(2);
        bt.root.left.parent = bt.root;
        bt.root.right = new BinaryTree.BinaryTreeNode(3);
        bt.root.right.parent = bt.root;
        bt.root.left.left = new BinaryTree.BinaryTreeNode(4);
        bt.root.left.left.parent = bt.root.left;
        bt.root.left.right = new BinaryTree.BinaryTreeNode(5);
        bt.root.left.right.parent = bt.root.left;

        BinaryTree bt2 = new BinaryTree().insertAtRoot(1);
        bt2.root.left = new BinaryTree.BinaryTreeNode(2);
        bt2.root.left.parent = bt2.root;
        bt2.root.right = new BinaryTree.BinaryTreeNode(4);
        bt2.root.right.parent = bt2.root;
        bt2.root.left.left = new BinaryTree.BinaryTreeNode(3);
        bt2.root.left.left.parent = bt2.root.left;
        bt2.root.right.right = new BinaryTree.BinaryTreeNode(6);
        bt2.root.right.right.parent = bt2.root.right;

        BinaryTree bt3 = new BinaryTree().insertAtRoot(1);
        bt3.root.left = new BinaryTree.BinaryTreeNode(2);
        bt3.root.left.parent = bt3.root;
        bt3.root.left.left = new BinaryTree.BinaryTreeNode(4);
        bt3.root.left.left.parent = bt3.root.left;
        bt3.root.left.right = new BinaryTree.BinaryTreeNode(3);
        bt3.root.left.right.parent = bt3.root.left;
        bt3.root.left.right.right = new BinaryTree.BinaryTreeNode(5);
        bt3.root.left.right.right.parent = bt3.root.left.right;
        bt3.root.left.right.right.right = new BinaryTree.BinaryTreeNode(6);
        bt3.root.left.right.right.right.parent = bt3.root.left.right.right;

        return Stream.of(
                Arguments.of(bt.root.left.left, bt.root.left.right, bt.root.left),
                Arguments.of(bt2.root.left.left, bt2.root.right.right, bt2.root),
                Arguments.of(bt3.root.left.left, bt3.root.left.right.right.right, bt3.root.left)
        );
    }

    @ParameterizedTest
    @MethodSource("testFirstCommonAncestor")
    void testFirstCommonAncestor(BinaryTree.BinaryTreeNode first, BinaryTree.BinaryTreeNode second, BinaryTree.BinaryTreeNode expected) {
        assert Solution.firstCommonAncestor(first, second).equals(expected);
    }

    @ParameterizedTest
    @MethodSource("testFirstCommonAncestor")
    void testFirstCommonAncestor2(BinaryTree.BinaryTreeNode first, BinaryTree.BinaryTreeNode second, BinaryTree.BinaryTreeNode expected) {
        assert Solution.firstCommonAncestor2(first, second).equals(expected);
    }

    @ParameterizedTest
    @MethodSource("testFirstCommonAncestor")
    void testFirstCommonAncestorWithoutParentLinks(BinaryTree.BinaryTreeNode first, BinaryTree.BinaryTreeNode second, BinaryTree.BinaryTreeNode expected) {
        BinaryTree.BinaryTreeNode root = first;
        while (root.parent != null) {
            root = root.parent;
        }
        assert Solution.firstCommonAncestorWithoutParentLinks(root, first, second).equals(expected);
    }

    @ParameterizedTest
    @MethodSource("testFirstCommonAncestor")
    void testFirstCommonAncesotrWithoutParentLinks2(BinaryTree.BinaryTreeNode first, BinaryTree.BinaryTreeNode second, BinaryTree.BinaryTreeNode expected) {
        BinaryTree.BinaryTreeNode root = first;
        while (root.parent != null) {
            root = root.parent;
        }
        BinaryTree.BinaryTreeNode ans = Solution.firstCommonAncestorWithoutParentLinks2(root, first, second);
        assert ans != null;
        assert ans.equals(expected);
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
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(50).insert(20).insert(60).insert(10).insert(25).insert(70);
        Solution.BSTSequences(bst).forEach(System.out::println);
    }

    @Test
    void testIsSubTree() {
        BinaryTree bt = new BinaryTree().insertAtRoot(1);
        bt.root.left = new BinaryTree.BinaryTreeNode(2);
        bt.root.right = new BinaryTree.BinaryTreeNode(3);
        bt.root.left.right = new BinaryTree.BinaryTreeNode(4);

        BinaryTree btSuper = new BinaryTree().insertAtRoot(5);
        btSuper.root.right = new BinaryTree.BinaryTreeNode(6);
        btSuper.root.right.right = new BinaryTree.BinaryTreeNode(7);
        btSuper.root.left = bt.root;

        BinaryTree bt2 = new BinaryTree().insertAtRoot(1);
        bt2.root.left = new BinaryTree.BinaryTreeNode(2);
        bt2.root.right = new BinaryTree.BinaryTreeNode(4);
        bt2.root.left.left = new BinaryTree.BinaryTreeNode(3);

        BinaryTree bt2Super = new BinaryTree().insertAtRoot(1);
        bt2Super.root.left = new BinaryTree.BinaryTreeNode(2);
        bt2Super.root.right = new BinaryTree.BinaryTreeNode(4);
        bt2Super.root.left.left = new BinaryTree.BinaryTreeNode(3);
        bt2Super.root.right.right = new BinaryTree.BinaryTreeNode(5);

        Assertions.assertTrue(Solution.isSubtree(btSuper, bt));
        Assertions.assertFalse(Solution.isSubtree(bt2Super, bt2));
    }

    private static Stream<Arguments> testPathsWithSum() {
        BinaryTree bt = new BinaryTree().insertAtRoot(10);
        bt.root.left = new BinaryTree.BinaryTreeNode(5);
        bt.root.right = new BinaryTree.BinaryTreeNode(-3);
        bt.root.left.left = new BinaryTree.BinaryTreeNode(3);
        bt.root.left.right = new BinaryTree.BinaryTreeNode(2);
        bt.root.right.right = new BinaryTree.BinaryTreeNode(11);
        bt.root.left.left.left = new BinaryTree.BinaryTreeNode(3);
        bt.root.left.left.right = new BinaryTree.BinaryTreeNode(-2);
        bt.root.left.right.right = new BinaryTree.BinaryTreeNode(1);
        return Stream.of(
                Arguments.of(bt, 8, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("testPathsWithSum")
    void testPathsWithSum(BinaryTree bt, int targetSum, int expected) {
        assert Solution.pathsWithSum(bt, targetSum) == expected;
    }

    @ParameterizedTest
    @MethodSource("testPathsWithSum")
    void testPathsWithSum2(BinaryTree bt, int targetSum, int expected) {
        assert Solution.pathsWithSum2(bt, targetSum) == expected;
    }
}
