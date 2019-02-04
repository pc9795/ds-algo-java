package geeks_for_geeks.ds.tree.binary_tree;

import geeks_for_geeks.ds.linked_list.DoublyLinkedList;
import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.util.DoublePointer;

import java.security.PublicKey;
import java.util.ArrayDeque;
import java.util.Hashtable;

/**
 * Created By: Prashant Chaubey
 * Created On: 24-09-2018 02:06
 **/
public class BinaryTree {
    public BTNode root;

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

    public DoublyLinkedList toList() {
        BTNode head = toListUtil(this.root);
        DoublyLinkedList dll = new DoublyLinkedList();
        BTNode curr = head;
//        We have to do this because nodes used in Binary Tree and Doubly Linked list are different.
        do {
            dll.insertAtEnd(curr.data);
            curr = curr.right;
        } while (curr != head);

//        We are getting a circular doubly list but we are returning only doubly as circular one is not implemented yet.+
        return dll;
    }

    private BTNode toListUtil(BTNode root) {
        if (root == null) {
            return null;
        }
        BTNode left = toListUtil(root.left);
        BTNode right = toListUtil(root.right);

//        Making root a doubly circular list;
        root.left = root.right = root;
        return concatenate(concatenate(left, root), right);
    }

    private static BTNode concatenate(BTNode circularList1Head, BTNode circularList2Head) {
        if (circularList1Head == null) {
            return circularList2Head;
        }
        if (circularList2Head == null) {
            return circularList1Head;
        }
        BTNode circularList1Last = circularList1Head.left;
        BTNode circularList2Last = circularList2Head.left;

        circularList1Last.right = circularList2Head;
        circularList2Head.left = circularList1Last;
        circularList1Head.left = circularList2Last;
        circularList2Last.right = circularList1Head;
        return circularList1Head;
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
}
