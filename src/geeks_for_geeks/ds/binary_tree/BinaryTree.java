package geeks_for_geeks.ds.binary_tree;

import geeks_for_geeks.ds.nodes.BTNode;

import java.util.ArrayDeque;

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
            printLevel(bt.root, i);
            System.out.println();
        }
    }

    public static void printLevel(BTNode root, int level) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            System.out.print(root.data + "(" + root.left + "," + root.right + ")    ");
            return;
        }
        printLevel(root.left, level - 1);
        printLevel(root.right, level - 1);
    }

    public static int getWidthFromLevel(BTNode root, int level) {
        if (root == null) {
            return 0;
        }
        if (level == 1) {
            return 1;
        }
        return getWidthFromLevel(root.left, level - 1) +
                getWidthFromLevel(root.right, level - 1);
    }

    public static void preOrderTraversal(BinaryTree tree) {
        if (tree == null) {
            throw new RuntimeException("Tree is empty!");
        }
        preOrderTraversalUtil(tree.root);
        System.out.println();
    }

    private static void preOrderTraversalUtil(BTNode root) {
        if (root == null) {
            return;
        }
        System.out.print("data: " + root.data + "->");
        preOrderTraversalUtil(root.left);
        preOrderTraversalUtil(root.right);
    }

    public static void postOrderTraversal(BinaryTree tree) {
        if (tree == null) {
            throw new RuntimeException("Tree is empty!");
        }
        postOrderTraversalUtil(tree.root);
        System.out.println();
    }

    private static void postOrderTraversalUtil(BTNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversalUtil(root.left);
        postOrderTraversalUtil(root.right);
        System.out.print("data:" + root.data + "->");
    }

    public static void inOrderTraversal(BinaryTree tree) {
        if (tree == null) {
            throw new RuntimeException("Tree is empty!");
        }
        inOrderTraversalUtil(tree.root);
        System.out.println();
    }

    private static void inOrderTraversalUtil(BTNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversalUtil(root.left);
        System.out.print("data: " + root.data + "->");
        inOrderTraversalUtil(root.right);
    }

    public static void inOrderTraversalWoRecursion(BinaryTree tree) {
        ArrayDeque<BTNode> stack = new ArrayDeque<>();
        BTNode root = tree.root;
        for (; root != null; root = root.left) {
            stack.push(root);
        }
        for (; !stack.isEmpty(); ) {
            BTNode curr = stack.pop();
            System.out.print("data:" + curr.data + "->");
            if (curr.right != null) {
                for (BTNode temp = curr.right; temp != null; temp = temp.left) {
                    stack.push(temp);
                }
            }
        }
        System.out.println();
    }

    /**
     * T=O(n) each node in worst case could be visited two times only.
     *
     * @param tree
     */
    public static void morisTraversal(BinaryTree tree) {
        if (tree == null || tree.root == null) {
            System.out.println("Tree is empty!");
            return;
        }
        BTNode curr = tree.root;
        for (; curr != null; ) {
            if (curr.left == null) {
                System.out.print(curr.data + "->");
                curr = curr.right;
            } else {
                BTNode pre = curr.left;
                for (; pre.right != null && pre.right != curr; pre = pre.right) {
                }
                if (pre.right == null) {
                    pre.right = curr;
                    curr = curr.left;
                } else {
                    pre.right = null;
                    System.out.print(curr.data + "->");
                    curr = curr.right;
                }
            }
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.root = new BTNode(1);
        tree.root.left = new BTNode(2);
        tree.root.right = new BTNode(3);
        tree.root.left.left = new BTNode(4);
        tree.root.left.right = new BTNode(5);
        inOrderTraversal(tree);
        morisTraversal(tree);
    }
}
