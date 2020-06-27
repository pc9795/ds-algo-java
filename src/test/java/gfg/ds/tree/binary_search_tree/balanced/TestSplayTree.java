package gfg.ds.tree.binary_search_tree.balanced;

import gfg.ds.tree.binary_tree.BinaryTree;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 23-06-2020 19:45
 **/
class TestSplayTree {
    @Test
    void testZig() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SplayTree splayTree = new SplayTree();

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

        Method zigMethod = splayTree.getClass().getDeclaredMethod("zig", BinaryTree.BinaryTreeNode.class);
        zigMethod.setAccessible(true);
        BinaryTree.BinaryTreeNode result = (BinaryTree.BinaryTreeNode) zigMethod.invoke(splayTree, binaryTree.root);

        binaryTree = new BinaryTree().insertAtRoot(result);
        assert binaryTree.root.data == 3;
        assert binaryTree.getAtPos("0") == 2;
        assert binaryTree.getAtPos("1") == 5;
        assert binaryTree.getAtPos("10") == 4;
        assert binaryTree.getAtPos("11") == 6;
    }

    @Test
    void testZag() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        SplayTree splayTree = new SplayTree();

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

        Method zagMethod = splayTree.getClass().getDeclaredMethod("zag", BinaryTree.BinaryTreeNode.class);
        zagMethod.setAccessible(true);
        BinaryTree.BinaryTreeNode result = (BinaryTree.BinaryTreeNode) zagMethod.invoke(splayTree, binaryTree.root);

        binaryTree = new BinaryTree().insertAtRoot(result);
        assert binaryTree.root.data == 5;
        assert binaryTree.getAtPos("0") == 3;
        assert binaryTree.getAtPos("1") == 6;
        assert binaryTree.getAtPos("00") == 2;
        assert binaryTree.getAtPos("01") == 4;
    }

    @Test
    void testSearch() throws NoSuchFieldException, IllegalAccessException {
        BinaryTree binaryTree = new BinaryTree().insertAtRoot(100);
        binaryTree.insertAtPos("0", 50).insertAtPos("1", 200).insertAtPos("00", 40).
                insertAtPos("000", 30).insertAtPos("0000", 20);

        SplayTree splayTree = new SplayTree();

        //Filed exists in BinaryTree
        Field rootField = splayTree.getClass().getSuperclass().getDeclaredField("root");
        rootField.setAccessible(true);
        rootField.set(splayTree, binaryTree.root);

        boolean found = splayTree.search(20);
        assert found;

        assert BinaryTree.preOrderTraversal(splayTree).equals(Arrays.asList(20, 50, 30, 40, 100, 200));
    }

    @Test
    void testInsert() {
        SplayTree tree = new SplayTree();
        tree.insert(100).insert(50).insert(200).insert(40).insert(30).insert(20).insert(25);

        assert BinaryTree.preOrderTraversal(tree).equals(Arrays.asList(25, 20, 30, 40, 50, 100, 200));
    }
}
