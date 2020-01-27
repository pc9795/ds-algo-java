package geeks_for_geeks.ds.tree.binary_tree;

import geeks_for_geeks.ds.linked_list.DoublyLinkedList;
import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_search_tree.BinarySearchTree;
import util.DoublePointer;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created By: Prashant Chaubey
 * Created On: 24-09-2018 02:06
 **/
public class BinaryTree {
    public BTNode root;

    public BinaryTree(int data) {
        root = new BTNode(data);
    }

    public BinaryTree(BTNode root) {
        this.root = root;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "root=" + root +
                '}';
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

    public static int getHeight(BinaryTree bt) {
        return getHeightUtil(bt.root, 0);
    }

    public static int getHeightUtil(BTNode root, int height) {
        if (root == null) {
            return height;
        }
        return Math.max(height + 1, Math.max(getHeightUtil(root.left, height + 1), getHeightUtil(root.right,
                height + 1)));
    }

    /**
     * t=O(n^2) ; for skewed tree.
     *
     * @param bt
     */
    public static void levelOrderTraversal(BinaryTree bt) {
        if (bt.root == null) {
            throw new RuntimeException("Tree is empty");
        }
        int height = getHeight(bt);
        for (int i = 1; i <= height; i++) {
            //O(n)
            printLevel(bt.root, i);
            System.out.println();
        }
    }

    private static void printLevel(BTNode root, int level) {
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

    /**
     * t=O(n)
     *
     * @param bt
     */
    public static void levelOrderTraversalUsingQueue(BinaryTree bt) {
        assert bt.root != null;
        ArrayDeque<BTNode> queue = new ArrayDeque<>();
        queue.add(bt.root);
        while (!queue.isEmpty()) {
            BTNode node = queue.peek();
            System.out.print(node.data + " ");
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        System.out.println();
    }


    private static int getWidthFromLevel(BTNode root, int level) {
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
        System.out.print(root.data + "->");
        inOrderTraversalUtil(root.right);
    }

    //T=O(n)
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

    //T=O(n) each node in worst case could be visited two times only.
    public static void morisTraversal(BinaryTree tree) {

        if (tree == null || tree.root == null) {
            System.out.println("Tree is empty!");
            return;
        }

        for (BTNode curr = tree.root; curr != null; ) {

            if (curr.left == null) {
                System.out.print(curr.data + "->");
                curr = curr.right;
            } else {

                BTNode pre = curr.left;

                while (pre.right != null && pre.right != curr) {
                    pre = pre.right;
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

        System.out.println();
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

    public static int diameter(BinaryTree tree) {
        assert tree != null && tree.root != null;
        DoublePointer<Integer> diam = new DoublePointer<>();
        diam.data = Integer.MIN_VALUE;
        diameterUtil(tree.root, diam);
        return diam.data;
    }

    private static int diameterUtil(BTNode root, DoublePointer<Integer> diam) {
        if (root == null) {
            return 0;
        }
        int left = diameterUtil(root.left, diam);
        int right = diameterUtil(root.right, diam);
        diam.data = Math.min(diam.data, left + right + 1);
        return Math.max(left, right) + 1;
    }

    public List<Integer> getInOrder(boolean showNulls, int nullValue) {
        List<Integer> traversal = new LinkedList<>();

        getInOrder(this.root, traversal, showNulls, nullValue);

        return traversal;
    }

    private static void getInOrder(BTNode root, List<Integer> inOrder, final boolean showNull, final int nullValue) {
        if (root == null) {
//            To prevent cases in which the tree is subtree but have some extra nodes attached to it.
            if (showNull) {
                inOrder.add(nullValue);
            }
            return;
        }
        getInOrder(root.left, inOrder, showNull, nullValue);
        inOrder.add(root.data);
        getInOrder(root.right, inOrder, showNull, nullValue);
    }

    public ArrayList<Integer> getPreOrder(boolean showNull, int nullValue) {
        ArrayList<Integer> traversal = new ArrayList<>();

        getPreOrder(this.root, traversal, showNull, nullValue);

        return traversal;
    }

    private static void getPreOrder(BTNode root, ArrayList<Integer> preOrder, final boolean showNull, final int nullValue) {
        if (root == null) {
//            To prevent cases in which the tree is subtree but have some extra nodes attached to it.
            if (showNull) {
                preOrder.add(nullValue);
            }
            return;
        }
        preOrder.add(root.data);
        getPreOrder(root.left, preOrder, showNull, nullValue);
        getPreOrder(root.right, preOrder, showNull, nullValue);

    }

    //T=O(n+m), S=O(n+m)
    public boolean isSubTree(BinaryTree bt) {
        assert bt != null;

        String inOrder = this.getInOrder(true, -1).stream().
                map(Object::toString).collect(Collectors.joining(","));

        String inOrderSuper = bt.getInOrder(true, -1).stream().
                map(Object::toString).collect(Collectors.joining(","));

        //We can only use pre-order traversal as soon as we are storing null values as special characters. Because it
        //be unique.
        //T=O(n), S=O(n)
        String preOrder = this.getPreOrder(true, -1).stream()
                .map(Object::toString).collect(Collectors.joining(","));

        //T=O(m), S=O(m)
        String preOrderSuper = bt.getPreOrder(true, -1).
                stream().map(Object::toString).collect(Collectors.joining(","));

        return inOrderSuper.contains(inOrder) && preOrderSuper.contains(preOrder);
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