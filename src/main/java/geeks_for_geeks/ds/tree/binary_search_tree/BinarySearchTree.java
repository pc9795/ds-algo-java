package geeks_for_geeks.ds.tree.binary_search_tree;

import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import geeks_for_geeks.ds.nodes.BTNode;
import util.DoublePointer;
import util.Pair;

import java.util.ArrayDeque;
import java.util.Map;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 00:50
 **/
public class BinarySearchTree extends BinaryTree {

    public BinarySearchTree(int data) {
        super(data);
    }

    public BinarySearchTree(BTNode root) {
        super(root);
    }

    /**
     * t=O(n) ;if tree is skewed.
     *
     * @param key
     * @return
     */
    public boolean search(int key) {
        for (BTNode curr = root; curr != null; ) {
            if (curr.data == key) {
                return true;
            }
            curr = key < curr.data ? curr.left : curr.right;
        }

        return false;
    }


    /**
     * t=O(n); if tree is skewed.
     *
     * @param data
     * @return
     */
    public BinarySearchTree insert(int data) {
        if (isEmpty()) {
            root = new BTNode(data);
            return this;
        }
        BTNode curr = root;
        BTNode prev = null;
        for (; curr != null; ) {
            assert curr.data != data : "Duplicate data";
            prev = curr;
            curr = data < curr.data ? curr.left : curr.right;
        }
        // Prev is empty only if tree is empty but we are checking that before hand.
        if (data < prev.data) {
            prev.left = new BTNode(data);
        } else {
            prev.right = new BTNode(data);
        }

        return this;
    }

    /**
     * In order successor if subtree is present of a node.
     *
     * @param node
     * @return
     */
    public static BTNode inOrderSucc(BTNode node) {
        assert node != null && node.right != null;

        BTNode inOrderSucc = node.right;
        for (; inOrderSucc.left != null; inOrderSucc = inOrderSucc.left) ;
        return inOrderSucc;

    }

    /**
     * In order predecessor if subtree is present of a node.
     *
     * @param node
     * @return
     */
    public static BTNode inOrderPred(BTNode node) {
        assert node != null && node.left != null;

        BTNode inOrderPred = node.left;
        for (; inOrderPred.right != null; inOrderPred = inOrderPred.right) ;
        return inOrderPred;
    }

    /**
     * t=O(n) ; if tree is skewed.
     *
     * @param key
     */
    public void delete(int key) {
        assert isEmpty() : "Empty tree";

        BTNode curr = root;
        BTNode prev = null;
        for (; curr != null; ) {
            if (curr.data == key) {
                break;
            }
            prev = curr;
            curr = key < curr.data ? curr.left : curr.right;
        }

        assert curr == null : "Element not found";

        if (curr.left == null || curr.right == null) {
            // Will cover both leaf and single child node.
            if (prev == null) {
                // if node is root.
                root = curr.left == null ? curr.right : curr.left;
                return;
            }
            if (prev.right == curr) {
                prev.right = curr.right;
            } else {
                prev.left = curr.right;
            }
        } else {
            // Node with two children.
            // Finding in-order successor for the node.
            BTNode inOrderSucc = BinarySearchTree.inOrderSucc(curr);
            delete(inOrderSucc.data);
            // Swapping inside data will be expensive for bigger object.
            // We can use recursive delete code which will use the links.
            int temp = curr.data;
            curr.data = inOrderSucc.data;
            inOrderSucc.data = temp;
        }
    }

    /**
     * t=O(n) for skewed trees
     *
     * @return
     */
    public int getMin() {
        assert isEmpty() : "Empty tree";

        BTNode temp = root;
        for (; temp.left != null; temp = temp.left) ;
        return temp.data;
    }

    /**
     * t=O(n) ; for skewed tree.
     *
     * @param key
     */
    public Pair<BTNode, BTNode> findPreSuc(int key) {
        BTNode pred = null, succ = null, curr = this.root;

        for (; curr != null; ) {
            if (curr.data == key) {
                if (curr.left != null) {
                    pred = inOrderPred(curr);
                }
                if (curr.right != null) {
                    succ = inOrderSucc(curr);
                }
                break;
            }
            if (key < curr.data) {
                succ = curr;
                curr = curr.left;
            } else {
                pred = curr;
                curr = curr.right;
            }
        }
        return new Pair<>(pred, succ);
    }

    /**
     * t=O(n) ; for skewed tree.
     *
     * @param n1
     * @param n2
     * @return
     */
    public int lca(int n1, int n2) {
        // If elements are not found
        if (!search(n1) || !search(n2)) {
            return -1;
        }

        // n2 will hold max value.
        n2 = Math.max(n1, n2);
        n1 = Math.min(n1, n2);

        BTNode curr = root;
        for (; curr != null; ) {
            if (curr.data >= n1 && curr.data <= n2) {
                return curr.data;
            }
            curr = curr.data < n1 ? curr.right : curr.left;
        }
        return -1;
    }

    public int ceil(int number) {
        BTNode curr = this.root;
        int ceil = -1;

        while (curr != null) {
            if (curr.data == number) {
                return curr.data;
            }
            if (number < curr.data) {
                ceil = curr.data;
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return ceil;
    }


    public static boolean isBST(BinaryTree bt) {
        assert bt != null;
        return isBSTUtil(bt.root, new DoublePointer<>());
    }

    private static boolean isBSTUtil(BTNode root, DoublePointer<BTNode> prev) {
        if (root == null) {
            return true;
        }
        if (!isBSTUtil(root.left, prev)) {
            return false;
        }
        if (prev.data != null && prev.data.data > root.data) {
            return false;
        }
        prev.data = root;

        return isBSTUtil(root.right, prev);
    }

    /**
     * t=O(n)
     *
     * @param tree
     */
    public static int kthSmallest(BinarySearchTree tree, int k) {
        ArrayDeque<BTNode> stack = new ArrayDeque<>();
        BTNode root = tree.root;

        for (; root != null; root = root.left) {
            stack.push(root);
        }

        for (; !stack.isEmpty(); ) {

            k--;
            BTNode curr = stack.pop();

            if (k == 0) {
                return curr.data;
            }

            if (curr.right != null) {
                for (BTNode temp = curr.right; temp != null; temp = temp.left) {
                    stack.push(temp);
                }
            }
        }
        return -1;
    }
}

