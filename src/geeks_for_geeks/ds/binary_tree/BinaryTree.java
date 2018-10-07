package geeks_for_geeks.ds.binary_tree;

import geeks_for_geeks.ds.binary_search_tree.BinarySearchTree;

/**
 * Created By: Prashant Chaubey
 * Created On: 24-09-2018 02:06
 **/
public class BinaryTree {
    public BTNode root;

    public BinaryTree() {

    }

    public BinaryTree(int data) {
        root = new BTNode(data);
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }

    public static int getHeight(BinaryTree bt) {
        return getHeightUtil(bt.root, 0);
    }

    public static int getHeightUtil(BTNode root, int height) {
        if (root == null) {
            return height;
        }
        return Math.max(height + 1, Math.max(getHeightUtil(root.left, height + 1), getHeightUtil(root.right, height + 1)));
    }

    public static void levelOrderTraversal(BinaryTree bt) {
        if (bt.root == null) {
            throw new RuntimeException("Tree is empty");
        }
        int height = getHeight(bt);
        for (int i = 1; i <= height; i++) {
            printLevel(bt.root, 1, i);
            System.out.println();
        }
    }

    public static void printLevel(BTNode root, int currHeight, int level) {
        if (root == null) {
            return;
        }
        if (currHeight == level) {
            System.out.print(root.data + "(" + root.left + "," + root.right + ")    ");
            return;
        }
        printLevel(root.left, currHeight + 1, level);
        printLevel(root.right, currHeight + 1, level);
    }
}
