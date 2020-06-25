package geeks_for_geeks.ds.tree.binary_search_tree.balanced;

import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 22-06-2020 22:58
 **/
class TestAVLTree {

    @Test
    void testRightRotate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        AVLTree avlTree = new AVLTree();

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

        Method rightRotateMethod = avlTree.getClass().getDeclaredMethod("rightRotate", BTNode.class);
        rightRotateMethod.setAccessible(true);
        BTNode result = (BTNode) rightRotateMethod.invoke(avlTree, binaryTree.root);

        binaryTree = new BinaryTree().insertAtRoot(result);
        assert binaryTree.root.data == 3;
        assert binaryTree.getAtPos("0") == 2;
        assert binaryTree.getAtPos("1") == 5;
        assert binaryTree.getAtPos("10") == 4;
        assert binaryTree.getAtPos("11") == 6;
    }

    @Test
    void testLeftRotate() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        AVLTree avlTree = new AVLTree();

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

        Method leftRotateMethod = avlTree.getClass().getDeclaredMethod("leftRotate", BTNode.class);
        leftRotateMethod.setAccessible(true);
        BTNode result = (BTNode) leftRotateMethod.invoke(avlTree, binaryTree.root);

        binaryTree = new BinaryTree().insertAtRoot(result);
        assert binaryTree.root.data == 5;
        assert binaryTree.getAtPos("0") == 3;
        assert binaryTree.getAtPos("1") == 6;
        assert binaryTree.getAtPos("00") == 2;
        assert binaryTree.getAtPos("01") == 4;
    }

    @Test
    void testInsertion() {
        AVLTree tree = new AVLTree();
        tree.insert(10).insert(20).insert(30).insert(40).insert(50).insert(25);
        assert BinaryTree.preOrderTraversal(tree).equals(Arrays.asList(30, 20, 10, 25, 40, 50));
    }

    @Test
    void testDeletion() {
        AVLTree tree = new AVLTree();
        tree.insert(9).insert(5).insert(10).insert(0).insert(6).insert(11).insert(-1).insert(1).insert(2);
        assert BinaryTree.preOrderTraversal(tree).equals(Arrays.asList(9, 1, 0, -1, 5, 2, 6, 10, 11));
        tree.delete(10);
        assert BinaryTree.preOrderTraversal(tree).equals(Arrays.asList(1, 0, -1, 9, 5, 2, 6, 11));
    }
}
