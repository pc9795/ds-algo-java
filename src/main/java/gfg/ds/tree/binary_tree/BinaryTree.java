package gfg.ds.tree.binary_tree;

import gfg.ds.linked_list.DoublyLinkedList;
import utils.DoublePointer;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 24-09-2018 02:06
 **/
public class BinaryTree {
    public BinaryTreeNode root;

    public BinaryTree insertAtRoot(int data) {
        this.root = new BinaryTreeNode(data);
        return this;
    }

    public BinaryTree insertAtRoot(BinaryTreeNode node) {
        this.root = node;
        return this;
    }

    /**
     * Position string is a binary string of '0s' and '1s'. 0 means left and 1 means right. So if input is '01' it means
     * that data is inserted at `root.left.right`.
     * NOTE: You can't add the data to root using this method. It is done at the time of tree initialization.
     */
    public BinaryTree insertAtPos(String pos, int data) {
        assert !isEmpty() : "Tree is empty";
        BinaryTreeNode curr = this.root;
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

            assert curr != null : String.format("Tree is evaluating null at position:%s", i + 1);
        }
        //We reached to the parent of the node.
        if (pos.charAt(len - 1) == '0') {
            curr.left = new BinaryTreeNode(data);
        } else if (pos.charAt(len - 1) == '1') {
            curr.right = new BinaryTreeNode(data);
        } else {
            throw new RuntimeException(String.format("Unexpected character:%s found at position:%s", pos.charAt(len - 1), len));
        }
        return this;
    }

    /**
     * Position string is a binary string of '0s' and '1s'. 0 means left and 1 means right. So if input is '01' it means
     * we need data of `root.left.right`.
     * NOTE: You can't get the data to root using this method.
     */
    public int getAtPos(String pos) {
        assert !isEmpty() : "Tree is empty";
        BinaryTreeNode curr = this.root;

        for (int i = 0; i < pos.length(); i++) {
            char ch = pos.charAt(i);

            if (ch == '0') {
                curr = curr.left;
            } else if (ch == '1') {
                curr = curr.right;
            } else {
                throw new RuntimeException(String.format("Unexpected character:%s found at position:%s", ch, i + 1));
            }

            assert curr!=null:String.format("Tree is evaluating null at position:%s", i + 1);
        }
        return curr.data;
    }


    /**
     * t=O(n)
     * it will traverse the whole tree.
     */
    private static int height(BinaryTree bt) {
        return heightUtil(bt.root, 0);
    }

    private static int heightUtil(BinaryTreeNode root, int height) {
        if (root == null) {
            return height;
        }
        return Math.max(height + 1, Math.max(heightUtil(root.left, height + 1), heightUtil(root.right, height + 1)));
    }

    /**
     * t=O(n^2); for skewed tree.
     * s=O(w); w is maximum width of tree
     * =O(n); for perfect binary tree;
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
     */
    private static void printLevel(BinaryTreeNode root, int level, List<Integer> elements) {
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
     */
    public static List<Integer> levelOrderTraversalUsingQueue(BinaryTree bt) {
        assert !bt.isEmpty() : "Tree is empty";

        List<Integer> traversal = new ArrayList<>();
        ArrayDeque<BinaryTreeNode> queue = new ArrayDeque<>();
        queue.add(bt.root);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.peek();
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

    private static int getWidthFromLevel(BinaryTreeNode root, int level) {
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
     */
    public static List<Integer> preOrderTraversal(BinaryTree tree) {
        assert tree != null;
        List<Integer> traversal = new ArrayList<>();
        preOrderTraversalUtil(traversal, tree.root, false);
        return traversal;
    }

    private static void preOrderTraversalUtil(List<Integer> traversal, BinaryTreeNode root, boolean includeNull) {
        if (root == null) {
            if (includeNull) {
                traversal.add(null);
            }
            return;
        }
        traversal.add(root.data);
        preOrderTraversalUtil(traversal, root.left, includeNull);
        preOrderTraversalUtil(traversal, root.right, includeNull);
    }

    /**
     * t=O(n)
     * s=O(h); h is the height of the tree; due to Recursion stack
     * =O(n); for skewed tree
     */
    public static List<Integer> postOrderTraversal(BinaryTree tree) {
        assert tree != null;
        List<Integer> traversal = new ArrayList<>();
        postOrderTraversalUtil(traversal, tree.root);
        return traversal;
    }

    private static void postOrderTraversalUtil(List<Integer> traversal, BinaryTreeNode root) {
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
     */
    public static List<Integer> inOrderTraversal(BinaryTree tree) {
        assert tree != null;
        List<Integer> traversal = new ArrayList<>();
        inOrderTraversalUtil(traversal, tree.root, false);
        return traversal;
    }

    private static void inOrderTraversalUtil(List<Integer> traversal, BinaryTreeNode root, boolean includeNull) {
        if (root == null) {
            if (includeNull) {
                traversal.add(null);
            }
            return;
        }
        inOrderTraversalUtil(traversal, root.left, includeNull);
        traversal.add(root.data);
        inOrderTraversalUtil(traversal, root.right, includeNull);
    }

    /**
     * t=O(n)
     */
    public static List<Integer> inOrderTraversalWoRecursion(BinaryTree tree) {
        assert tree != null : "Null instance is give";

        ArrayDeque<BinaryTreeNode> stack = new ArrayDeque<>();
        BinaryTreeNode root = tree.root;
        List<Integer> traversal = new ArrayList<>();
        for (; root != null; root = root.left) {
            stack.push(root);
        }
        for (; !stack.isEmpty(); ) {
            BinaryTreeNode curr = stack.pop();
            traversal.add(curr.data);
            for (BinaryTreeNode temp = curr.right; temp != null; temp = temp.left) {
                stack.push(temp);
            }
        }
        return traversal;
    }

    /**
     * t=O(n)
     * each node in worst case could be visited two times only.
     */
    public static List<Integer> morisTraversal(BinaryTree tree) {
        assert tree != null : "Null instance given";

        List<Integer> traversal = new ArrayList<>();
        for (BinaryTreeNode curr = tree.root; curr != null; ) {
            if (curr.left == null) {
                traversal.add(curr.data);
                curr = curr.right;
                continue;
            }
            BinaryTreeNode rightMostOfLeftSubTree = curr.left;
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
     */
    public DoublyLinkedList toList() {
        BinaryTreeNode head = toListUtil(this.root);
        DoublyLinkedList dll = new DoublyLinkedList();
        BinaryTreeNode curr = head;
        // We have to do this because nodes used in Binary Tree and Doubly Linked list are different.
        do {
            dll.insertAtEnd(curr.data);
            curr = curr.right;
        } while (curr != head);

        // We are getting a circular doubly list but we are returning only doubly as circular one is not implemented yet.
        return dll;
    }

    private BinaryTreeNode toListUtil(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        BinaryTreeNode left = toListUtil(root.left);
        BinaryTreeNode right = toListUtil(root.right);

        // Making root a doubly circular list;
        // left and right are just prev and next so creating a circular linked list of size 1.
        root.left = root.right = root;
        return concat(concat(left, root), right);
    }

    private BinaryTreeNode concat(BinaryTreeNode head1, BinaryTreeNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        //left is prev and right is next.
        BinaryTreeNode last1 = head1.left;
        BinaryTreeNode last2 = head2.left;

        last1.right = head2;
        head2.left = last1;
        head1.left = last2;
        last2.right = head1;
        return head1;
    }

    /**
     * t=O(n)
     */
    public static int diameter(BinaryTree tree) {
        assert tree != null;

        DoublePointer<Integer> height = new DoublePointer<>(0);
        return diameterUtil(tree.root, height);
    }

    private static int diameterUtil(BinaryTreeNode root, DoublePointer<Integer> height) {
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
     */
    public boolean isSubTree(BinaryTree subTree) {
        assert subTree != null : "Null instance given";
        //Calculate inorder traversal
        List<Integer> traversalSub = new ArrayList<>();
        inOrderTraversalUtil(traversalSub, subTree.root, true);
        List<Integer> traversal = new ArrayList<>();
        inOrderTraversalUtil(traversal, root, true);
        if (Collections.indexOfSubList(traversal, traversalSub) == -1) {
            return false;
        }
        //Calculate pre-order traversal
        traversalSub = new ArrayList<>();
        preOrderTraversalUtil(traversalSub, subTree.root, true);
        traversal = new ArrayList<>();
        preOrderTraversalUtil(traversal, root, true);
        return Collections.indexOfSubList(traversal, traversalSub) != -1;
    }

    public int size() {
        return sizeUtil(this.root);
    }

    public int sizeUtil(BinaryTreeNode root) {
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

    /**
     * In order successor if subtree is present of a node.
     */
    public static BinaryTreeNode inOrderSucc(BinaryTreeNode node) {
        assert node != null && node.right != null;

        BinaryTreeNode inOrderSucc = node.right;
        for (; inOrderSucc.left != null; ) {
            inOrderSucc = inOrderSucc.left;
        }
        return inOrderSucc;
    }

    /**
     * In order predecessor if subtree is present of a node.
     */
    public static BinaryTreeNode inOrderPred(BinaryTreeNode node) {
        assert node != null && node.left != null;

        BinaryTreeNode inOrderPred = node.left;
        for (; inOrderPred.right != null; ) {
            inOrderPred = inOrderPred.right;
        }
        return inOrderPred;
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

    private boolean equalsUtil(BinaryTreeNode thisNode, BinaryTreeNode thatNode) {
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

    /**
     * Created By: Prashant Chaubey
     * Created On: 15-09-2018 00:52
     * //NOTE: I could try to make it using generics but it will need extra code during comparison.
     **/

    public static class BinaryTreeNode {
        public enum Color {RED, BLACK}

        public int data;
        public BinaryTreeNode left;
        public BinaryTreeNode right;
        public BinaryTreeNode parent;
        public int height; //Used by AVL tree
        public Color color; //Used by Red black tree

        public BinaryTreeNode(int data) {
            this.data = data;
        }

        public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right, BinaryTreeNode parent) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public BinaryTreeNode(DoublyLinkedList.DNode node) {
            this.data = node.data;
        }

        @Override
        public String toString() {
            return "BTNode{" + "data=" + data + ", left=" + (left != null ? left.data : null) +
                    ", right=" + (right != null ? right.data : null) + '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            BinaryTreeNode binaryTreeNode = (BinaryTreeNode) o;
            return data == binaryTreeNode.data;
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }
}