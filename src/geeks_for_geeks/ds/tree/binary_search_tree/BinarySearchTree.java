package geeks_for_geeks.ds.tree.binary_search_tree;

import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.util.DoublePointer;

import java.util.ArrayDeque;

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
        BTNode curr = root;

        for (; curr != null; ) {

            if (curr.data == key) {
                return true;
            }

            if (key < curr.data) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        return false;
    }

    public boolean isEmpty() {
        return root == null;
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

            if (curr.data == data) {
                throw new RuntimeException("Duplicate data");
            }

            prev = curr;
            if (data < curr.data) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        if (data < prev.data) {
            prev.left = new BTNode(data);
        } else {
            prev.right = new BTNode(data);
        }

        return this;
    }

    public static BTNode inOrderSucc(BTNode node) {
        assert node != null && node.right != null;

        BTNode inOrderSucc = node.right;
        for (; inOrderSucc.left != null; inOrderSucc = inOrderSucc.left) ;
        return inOrderSucc;

    }

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

        if (isEmpty()) {
            throw new RuntimeException("Empty tree");
        }

        BTNode curr = root;
        BTNode prev = null;

        for (; curr != null; ) {
            if (curr.data == key) {
                break;
            }

            prev = curr;
            if (key < curr.data) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        if (curr == null) {
            throw new RuntimeException("Element not found");
        }

        if (curr.left == null) {
//    Will cover both leaf and single child node.
            if (prev == null) {
//                if node is root.
                root = curr.right;
            } else {
                if (prev.right == curr) {
                    prev.right = curr.right;
                } else {
                    prev.left = curr.right;
                }
            }

        } else if (curr.right == null) {
//Cover the single child node.
            if (prev == null) {
//                if node is root.
                root = curr.left;
            } else {
                if (prev.right == curr) {
                    prev.right = curr.left;
                } else {
                    prev.left = curr.left;
                }
            }

        } else {
//            Node with two children.
//            Finding in-order successor for the node.
            BTNode inOrderSucc = BinarySearchTree.inOrderSucc(curr);

            delete(inOrderSucc.data);

//            swapping inside data will be expensive for bigger object.
//            We can use recursive delete code which will use the links.
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
        if (isEmpty()) {
            throw new RuntimeException("Empty Tree");
        }

        BTNode temp = root;
        for (; temp.left != null; temp = temp.left) ;
        return temp.data;
    }

    /**
     * t=O(n) ; for skewed tree.
     *
     * @param key
     */
    public void findPreSuc(int key) {
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

        System.out.println("curr:" + curr);
        System.out.println("predecessor:" + pred);
        System.out.println("successor:" + succ);

    }

    /**
     * t=O(n) ; for skewed tree.
     *
     * @param n1
     * @param n2
     * @return
     */
    public int lca(int n1, int n2) {
        if (!search(n1) || !search(n2)) {
            return -1;
        }

        if (n1 > n2) {
            int temp = n2;
            n2 = n1;
            n1 = temp;
        }

        BTNode curr = root;
        for (; curr != null; ) {
            if (curr.data >= n1 && curr.data <= n2) {
                return curr.data;
            }
            if (curr.data < n1) {
                curr = curr.right;
            } else {
                curr = curr.left;
            }
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

