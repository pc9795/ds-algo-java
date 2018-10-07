package geeks_for_geeks.ds.binary_search_tree;

import geeks_for_geeks.ds.binary_tree.BTNode;
import geeks_for_geeks.ds.binary_tree.BinaryTree;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 00:50
 **/
public class BinarySearchTree extends BinaryTree {

    //   T=O(h)
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

    public static BTNode inOrderSuccessor(BTNode node) {
        if (node == null) {
            throw new RuntimeException("Wrong input(null)");
        }
        if (node.right == null) {
            throw new RuntimeException("No right child");
        }
        BTNode inOrderSucc = node.right;
        for (; inOrderSucc.left != null; inOrderSucc = inOrderSucc.left) ;
        return inOrderSucc;

    }

    public static BTNode inOrderPredecessor(BTNode node) {
        if (node == null) {
            throw new RuntimeException("Wrong input(null)");
        }
        if (node.left == null) {
            throw new RuntimeException("No left child");
        }
        BTNode inOrderPred = node.left;
        for (; inOrderPred.right != null; inOrderPred = inOrderPred.right) ;
        return inOrderPred;
    }


    //  T=O(h)
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
        if (prev == null) {
            this.root = null;
        }

        if (curr.left == null) {
            if (curr.right == null) {
                System.out.println("Leaf node");
            } else {
                System.out.println("Internal node with single child");
            }
            if (prev.right == curr) {
//                curr.right can be null so leaf case covered.
                prev.right = curr.right;
            } else {
                prev.left = curr.right;
            }
        } else if (curr.right == null) {
            System.out.println("Internal node with single child");
            if (prev.right == curr) {
//                curr left can be null so leaf case covered.
                prev.right = curr.left;
            } else {
                prev.left = curr.left;
            }
        } else {
            System.out.println("Internal node with two children");
            BTNode inOrderSucc = BinarySearchTree.inOrderSuccessor(curr);
            System.out.println("InOrderSuccessor:" + inOrderSucc);
            delete(inOrderSucc.data);
//            swapping inside data will be expensive for bigger object.
            int temp = curr.data;
            curr.data = inOrderSucc.data;
            inOrderSucc.data = temp;
        }

    }

    //    T=O(n) for skewed trees
    public int getMin() {
        if (isEmpty()) {
            throw new RuntimeException("Empty Tree");
        }
        BTNode temp = root;
        for (; temp.left != null; temp = temp.left) ;
        return temp.data;
    }

    public void findPreSuc(int key) {
        BTNode pred = null, succ = null, curr = this.root;
        for (; curr != null; ) {
            if (curr.data == key) {
                if (curr.left != null) {
                    pred = inOrderPredecessor(curr);
                }
                if (curr.right != null) {
                    succ = inOrderSuccessor(curr);
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

    //    T=O(height)
    public int lowestCommonAncestor(int n1, int n2) {
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


    /*
     *           50
     *         /    \
     *       30     70
     *      /  \   /  \
     *    20   40 60  80
     *        /      /
     *       38     72
     *
     *          20
     *         /  \
     *       8     22
     *     /  \
     *   4     12
     *        /  \
     *      10   14
     *
     *
     *
     *
     * */
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
//        bst.insert(50).insert(30).insert(20).insert(40).insert(70).insert(60).insert(80).insert(72).insert(38);
        bst.insert(20).insert(8).insert(22).insert(4).insert(12).insert(10).insert(14);
        BinarySearchTree.levelOrderTraversal(bst);
        System.out.println(bst.lowestCommonAncestor(10, 14));
        System.out.println(bst.lowestCommonAncestor(14, 8));
        System.out.println(bst.lowestCommonAncestor(10, 22));
    }
}

