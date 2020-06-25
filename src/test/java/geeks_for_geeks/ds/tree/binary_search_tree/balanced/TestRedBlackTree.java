package geeks_for_geeks.ds.tree.binary_search_tree.balanced;

import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_search_tree.BinarySearchTree;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created By: Prashant Chaubey
 * Created On: 24-06-2020 20:30
 **/
class TestRedBlackTree {
    @Test
    void testRightRotate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RedBlackTree rbTree = new RedBlackTree();

         /*
         ACTUAL
              5
            /  \
           3    6
         /  \
        2   4

        EXPECTED
              3
            /  \
           2    5
               / \
              4   6

         */
        BinaryTree binaryTree = new BinaryTree().insertAtRoot(5);
        binaryTree.
                insertAtPos("1", 6).
                insertAtPos("0", 3).
                insertAtPos("01", 4).
                insertAtPos("00", 2);

        binaryTree.root.left.parent = binaryTree.root;
        binaryTree.root.left.right.parent = binaryTree.root.left;

        Method rightRotateMethod = rbTree.getClass().getDeclaredMethod("rightRotate", BTNode.class);
        rightRotateMethod.setAccessible(true);
        rightRotateMethod.invoke(rbTree, binaryTree.root);

        binaryTree = new BinaryTree().insertAtRoot(rbTree.root);
        assert binaryTree.root.data == 3;
        assert binaryTree.getAtPos("0") == 2;
        assert binaryTree.getAtPos("1") == 5;
        assert binaryTree.getAtPos("10") == 4;
        assert binaryTree.getAtPos("11") == 6;

        assert binaryTree.root.parent == null;
        assert binaryTree.root.right.parent.data == 3;
        assert binaryTree.root.right.left.data == 4;
    }

    @Test
    void testLeftRotate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RedBlackTree rbTree = new RedBlackTree();

         /*
         ACTUAL
              3
            /  \
           2    5
               / \
              4   6

        EXPECTED
              5
            /  \
           3    6
         /  \
        2   4

         */

        BinaryTree binaryTree = new BinaryTree().insertAtRoot(3);
        binaryTree.
                insertAtPos("0", 2).
                insertAtPos("1", 5).
                insertAtPos("10", 4).
                insertAtPos("11", 6);

        binaryTree.root.right.parent = binaryTree.root;
        binaryTree.root.right.left.parent = binaryTree.root.right;

        Method leftRotateMethod = rbTree.getClass().getDeclaredMethod("leftRotate", BTNode.class);
        leftRotateMethod.setAccessible(true);
        leftRotateMethod.invoke(rbTree, binaryTree.root);

        binaryTree = new BinaryTree().insertAtRoot(rbTree.root);
        assert binaryTree.root.data == 5;
        assert binaryTree.getAtPos("0") == 3;
        assert binaryTree.getAtPos("1") == 6;
        assert binaryTree.getAtPos("00") == 2;
        assert binaryTree.getAtPos("01") == 4;

        assert binaryTree.root.parent == null;
        assert binaryTree.root.left.parent.data == 5;
        assert binaryTree.root.left.right.parent.data == 3;
    }

    @Test
    void testInsert() {
        RedBlackTree rbTree = new RedBlackTree();
        rbTree.insert(7).insert(6).insert(5).insert(4).insert(3).insert(2).insert(1);

        /*
         * https://www.cs.usfca.edu/~galles/visualization/RedBlack.html - for visualization
         *                     (6)
         *                     / \
         *                    4  (7)
         *                   / \
         *                 (2) (5)
         *                 / \
         *                1   3
         * Node with parenthesis are black
         */
        assert BinaryTree.inOrderTraversal(rbTree).equals(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        assert BinaryTree.levelOrderTraversal(rbTree).
                stream().
                flatMap(Collection::stream).
                collect(Collectors.toList()).
                equals(Arrays.asList(6, 4, 7, 2, 5, 1, 3));
    }

    @Test
    void testDelete() {
        RedBlackTree rbTree = new RedBlackTree();
        rbTree.insert(7, 3, 18, 10, 22, 8, 11, 26, 2, 6, 13);

        /*
         * https://www.cs.usfca.edu/~galles/visualization/RedBlack.html - for visualization
         *                          (10)
         *                         /    \
         *                        7      18
         *                      /   \   /   \
         *                    (3)  (8)(11)  (22)
         *                   /   \       \     \
         *                  2     5       13    26
         * Node with parenthesis are black
         */
        assert BinaryTree.inOrderTraversal(rbTree).equals(Arrays.asList(2, 3, 6, 7, 8, 10, 11, 13, 18, 22, 26));
        assert BinaryTree.levelOrderTraversal(rbTree).
                stream().
                flatMap(Collection::stream).
                collect(Collectors.toList()).
                equals(Arrays.asList(10, 7, 18, 3, 8, 11, 22, 2, 6, 13, 26));

        rbTree.delete(18).delete(11).delete(3).delete(10).delete(22);
        /*
         *                      (18)
         *                      /  \
         *                     6   (13)
         *                    / \     \
         *                  (2) (7)   26
         */
        assert BinaryTree.inOrderTraversal(rbTree).equals(Arrays.asList(2, 6, 7, 8, 13, 26));
        assert BinaryTree.levelOrderTraversal(rbTree).
                stream().
                flatMap(Collection::stream).
                collect(Collectors.toList()).
                equals(Arrays.asList(8, 6, 13, 2, 7, 26));
    }
}
