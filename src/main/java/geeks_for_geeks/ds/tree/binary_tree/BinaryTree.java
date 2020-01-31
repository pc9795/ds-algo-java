package geeks_for_geeks.ds.tree.binary_tree;

import geeks_for_geeks.ds.linked_list.DoublyLinkedList;
import geeks_for_geeks.ds.nodes.BTNode;
import util.DoublePointer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 24-09-2018 02:06
 **/
@SuppressWarnings("unused")
public class BinaryTree {
    public BTNode root;

    public BinaryTree(int data) {
        root = new BTNode(data);
    }

    public BinaryTree(BTNode root) {
        this.root = root;
    }

    /**
     * Position string is a binary string of '0s' and '1s'. 0 means left and 1 means right. So if input is '01' it means
     * that data is inserted at `root.left.right`.
     * NOTE: You can't add the data to root using this method. It is done at the time of tree initialization.
     *
     * @param pos  string representing the position where data is going to be inserted
     * @param data data to insert
     * @return calling instance
     */
    public BinaryTree insertAtPos(String pos, int data) {
        assert !isEmpty() : "Tree is empty";
        BTNode curr = this.root;
        int len = pos.length();
        //Stop at the parent of the intended location
        for (int i = 0; i < len - 1; i++) {
            char ch = pos.charAt(i);
            if (ch == '0') {
                curr = curr.left;
            } else if (ch == '1') {
                curr = curr.right;
            } else {
                throw new RuntimeException(String.format("Unexpected character:%s found at position:%s", ch, i + 1));
            }
            if (curr == null) {
                throw new RuntimeException(String.format("Tree is evaluating null at position:%s", i + 1));
            }
        }
        //We reached to the parent of the node.
        if (pos.charAt(len - 1) == '0') {
            curr.left = new BTNode(data);
        } else if (pos.charAt(len - 1) == '1') {
            curr.right = new BTNode(data);
        } else {
            throw new RuntimeException(String.format("Unexpected character:%s found at position:%s", pos.charAt(len - 1), len));
        }
        return this;
    }

    /**
     * t=O(n)
     * it will traverse the whole tree.
     *
     * @param bt binary tree
     * @return height of the tree.
     */
    private static int height(BinaryTree bt) {
        return heightUtil(bt.root, 0);
    }

    private static int heightUtil(BTNode root, int height) {
        if (root == null) {
            return height;
        }
        return Math.max(height + 1, Math.max(heightUtil(root.left, height + 1), heightUtil(root.right, height + 1)));
    }

    /**
     * t=O(n^2); for skewed tree.
     * s=O(w); w is maximum width of tree
     * =O(n); for perfect binary tree;
     *
     * @param bt binary tree
     */
    public static List<List<Integer>> levelOrderTraversal(BinaryTree bt) {
        assert !bt.isEmpty() : "Tree is empty";

        int height = height(bt);
        List<List<Integer>> levels = new ArrayList<>();
        for (int i = 1; i <= height; i++) {
            List<Integer> currLevel = new ArrayList<>();
            printLevel(bt.root, i, currLevel);
            levels.add(currLevel);
        }
        return levels;
    }

    /**
     * t=O(n)
     *
     * @param root  root node
     * @param level level to be printed
     */
    private static void printLevel(BTNode root, int level, List<Integer> elements) {
        if (root == null) {
            return;
        }
        if (level == 1) {
            elements.add(root.data);
            return;
        }
        printLevel(root.left, level - 1, elements);
        printLevel(root.right, level - 1, elements);
    }

    /**
     * t=O(n)
     *
     * @param bt binary tree
     */
    public static List<Integer> levelOrderTraversalUsingQueue(BinaryTree bt) {
        assert !bt.isEmpty() : "Tree is empty";

        List<Integer> traversal = new ArrayList<>();
        ArrayDeque<BTNode> queue = new ArrayDeque<>();
        queue.add(bt.root);
        while (!queue.isEmpty()) {
            BTNode node = queue.peek();
            assert node != null;
            traversal.add(node.data);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        return traversal;
    }

    private static int getWidthFromLevel(BTNode root, int level) {
        if (root == null) {
            return 0;
        }
        if (level == 1) {
            return 1;
        }
        return getWidthFromLevel(root.left, level - 1) + getWidthFromLevel(root.right, level - 1);
    }

    /**
     * t=O(n)
     * s=O(h); h is the height of the tree; due to Recursion stack
     * =O(n); for skewed tree
     *
     * @param tree binary tree
     * @return pre-order traversal; root, left, right
     */
    public static List<Integer> preOrderTraversal(BinaryTree tree) {
        assert tree != null;
        List<Integer> traversal = new ArrayList<>();
        preOrderTraversalUtil(traversal, tree.root);
        return traversal;
    }

    private static void preOrderTraversalUtil(List<Integer> traversal, BTNode root) {
        if (root == null) {
            return;
        }
        traversal.add(root.data);
        preOrderTraversalUtil(traversal, root.left);
        preOrderTraversalUtil(traversal, root.right);
    }

    private static void preOrderTraversalWithNullsUtil(List<Integer> traversal, BTNode root) {
        if (root == null) {
            traversal.add(null);
            return;
        }
        traversal.add(root.data);
        preOrderTraversalUtil(traversal, root.left);
        preOrderTraversalUtil(traversal, root.right);
    }

    /**
     * t=O(n)
     * s=O(h); h is the height of the tree; due to Recursion stack
     * =O(n); for skewed tree
     *
     * @param tree binary tree
     * @return post-order traversal; left, right, root
     */
    public static List<Integer> postOrderTraversal(BinaryTree tree) {
        assert tree != null;
        List<Integer> traversal = new ArrayList<>();
        postOrderTraversalUtil(traversal, tree.root);
        return traversal;
    }

    private static void postOrderTraversalUtil(List<Integer> traversal, BTNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversalUtil(traversal, root.left);
        postOrderTraversalUtil(traversal, root.right);
        traversal.add(root.data);
    }

    /**
     * t=O(n)
     * s=O(h); h is the height of the tree; due to Recursion stack
     * =O(n); for skewed tree
     *
     * @param tree binary tree
     * @return in-order traversal; left, root, right
     */
    public static List<Integer> inOrderTraversal(BinaryTree tree) {
        assert tree != null;
        List<Integer> traversal = new ArrayList<>();
        inOrderTraversalUtil(traversal, tree.root);
        return traversal;
    }

    private static void inOrderTraversalUtil(List<Integer> traversal, BTNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversalUtil(traversal, root.left);
        traversal.add(root.data);
        inOrderTraversalUtil(traversal, root.right);
    }

    private static void inOrderTraversalWithNullsUtil(List<Integer> traversal, BTNode root) {
        if (root == null) {
            traversal.add(null);
            return;
        }
        inOrderTraversalUtil(traversal, root.left);
        traversal.add(root.data);
        inOrderTraversalUtil(traversal, root.right);
    }

    /**
     * t=O(n)
     *
     * @param tree binary tree
     */
    public static List<Integer> inOrderTraversalWoRecursion(BinaryTree tree) {
        assert tree != null : "Null instance is give";

        ArrayDeque<BTNode> stack = new ArrayDeque<>();
        BTNode root = tree.root;
        List<Integer> traversal = new ArrayList<>();
        for (; root != null; root = root.left) {
            stack.push(root);
        }
        for (; !stack.isEmpty(); ) {
            BTNode curr = stack.pop();
            traversal.add(curr.data);
            if (curr.right != null) {
                for (BTNode temp = curr.right; temp != null; temp = temp.left) {
                    stack.push(temp);
                }
            }
        }
        return traversal;
    }

    /**
     * t=O(n)
     * each node in worst case could be visited two times only.
     *
     * @param tree binary tree
     * @return moris traversal of the tree
     */
    public static List<Integer> morisTraversal(BinaryTree tree) {
        assert tree != null : "Null instance given";

        List<Integer> traversal = new ArrayList<>();
        for (BTNode curr = tree.root; curr != null; ) {
            if (curr.left == null) {
                traversal.add(curr.data);
                curr = curr.right;
                continue;
            }
            BTNode rightMostOfLeftSubTree = curr.left;
            //Right most node in the left sub-tree
            while (rightMostOfLeftSubTree.right != null && rightMostOfLeftSubTree.right != curr) {
                rightMostOfLeftSubTree = rightMostOfLeftSubTree.right;
            }
            //Making link
            if (rightMostOfLeftSubTree.right == null) {
                rightMostOfLeftSubTree.right = curr;
                curr = curr.left;
                continue;
            }
            //Removing link
            rightMostOfLeftSubTree.right = null;
            traversal.add(curr.data);
            curr = curr.right;
        }
        return traversal;
    }

    /**
     * t=O(n)
     *
     * @return list from tree.
     */
    public DoublyLinkedList toList() {
        BTNode head = toListUtil(this.root);
        DoublyLinkedList dll = new DoublyLinkedList();
        BTNode curr = head;
        // We have to do this because nodes used in Binary Tree and Doubly Linked list are different.
        do {
            dll.insertAtEnd(curr.data);
            curr = curr.right;
        } while (curr != head);

        // We are getting a circular doubly list but we are returning only doubly as circular one is not implemented yet.
        return dll;
    }

    private BTNode toListUtil(BTNode root) {
        if (root == null) {
            return null;
        }
        BTNode left = toListUtil(root.left);
        BTNode right = toListUtil(root.right);

        // Making root a doubly circular list;
        // left and right are just prev and next so creating a circular linked list of size 1.
        root.left = root.right = root;
        return concat(concat(left, root), right);
    }

    private BTNode concat(BTNode head1, BTNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        //left is prev and right is next.
        BTNode last1 = head1.left;
        BTNode last2 = head2.left;

        last1.right = head2;
        head2.left = last1;
        head1.left = last2;
        last2.right = head1;
        return head1;
    }

    /**
     * t=O(n)
     *
     * @param tree binary tree
     * @return diameter of the binary tree
     */
    public static int diameter(BinaryTree tree) {
        assert tree != null;

        DoublePointer<Integer> height = new DoublePointer<>(0);
        return diameterUtil(tree.root, height);
    }

    private static int diameterUtil(BTNode root, DoublePointer<Integer> height) {
        if (root == null) {
            return 0;
        }
        height.data += 1;
        DoublePointer<Integer> leftHeight = new DoublePointer<>(0);
        DoublePointer<Integer> rightHeight = new DoublePointer<>(0);
        int left = diameterUtil(root.left, leftHeight);
        int right = diameterUtil(root.right, rightHeight);
        // Update height from children
        height.data += Math.max(leftHeight.data, rightHeight.data);
        return Math.max(Math.max(left, right), leftHeight.data + rightHeight.data + 1);
    }

    /**
     * t=O(n+m)
     * s=O(n+m)
     *
     * @param subTree sub tree to check
     * @return whether the given tree is a subtree
     */
    public boolean isSubTree(BinaryTree subTree) {
        assert subTree != null : "Null instance given";
        //Calculate inorder traversal
        List<Integer> traversalSub = new ArrayList<>();
        inOrderTraversalWithNullsUtil(traversalSub, subTree.root);
        List<Integer> traversal = new ArrayList<>();
        inOrderTraversalWithNullsUtil(traversal, root);
        if (Collections.indexOfSubList(traversal, traversalSub) == -1) {
            return false;
        }
        //Calculate pre-order traversal
        traversalSub = new ArrayList<>();
        preOrderTraversalWithNullsUtil(traversalSub, subTree.root);
        traversal = new ArrayList<>();
        preOrderTraversalWithNullsUtil(traversal, root);
        return Collections.indexOfSubList(traversal, traversalSub) != -1;
    }

    public int size() {
        return sizeUtil(this.root);
    }

    public int sizeUtil(BTNode root) {
        if (root == null) {
            return 0;
        }
        int left = sizeUtil(root.left);
        int right = sizeUtil(root.right);

        return left + right + 1;

    }

    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinaryTree that = (BinaryTree) o;
        return equalsUtil(root, that.root);
    }

    private boolean equalsUtil(BTNode thisNode, BTNode thatNode) {
        if (thisNode == null && thatNode == null) {
            return true;
        }
        if (thisNode == null || thatNode == null) {
            return false;
        }
        if (thisNode.data != thatNode.data) {
            return false;
        }
        if (!equalsUtil(thisNode.left, thatNode.left)) {
            return false;
        }
        return equalsUtil(thisNode.right, thatNode.right);
    }

    @Override
    public int hashCode() {
        return root != null ? root.hashCode() : 0;
    }
}