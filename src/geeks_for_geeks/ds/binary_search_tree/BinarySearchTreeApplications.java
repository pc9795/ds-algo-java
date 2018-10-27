package geeks_for_geeks.ds.binary_search_tree;

import geeks_for_geeks.ds.binary_tree.BinaryTree;
import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.util.DoublePointer;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-10-2018 23:41
 **/
public class BinarySearchTreeApplications {

    public static BinarySearchTree correctBstIfTwoNodesAreSwapped(BinarySearchTree bst) {
        if (bst == null || bst.root == null) {
            System.out.println("Tree is empty");
            return bst;
        }
        DoublePointer<BTNode> first = new DoublePointer<>();
//        for the case where the two in correct nodes are adjacent to each other; in that case their is no last we will
//        use middle;
        DoublePointer<BTNode> middle = new DoublePointer<>();
        DoublePointer<BTNode> last = new DoublePointer<>();
        inorderModified(bst.root, null, first, middle, last);
        if (last.data == null) {
            int temp = first.data.data;
            first.data.data = middle.data.data;
            middle.data.data = temp;
        } else {
            int temp = first.data.data;
            first.data.data = last.data.data;
            last.data.data = temp;
        }
        return bst;
    }

    private static void inorderModified(BTNode root, BTNode prev, DoublePointer<BTNode> first, DoublePointer<BTNode> middle,
                                        DoublePointer<BTNode> last) {
        if (root == null) {
            return;
        }
        inorderModified(root.left, root, first, middle, last);
        if (prev != null && prev.data > root.data) {
            if (first.data == null) {
                first.data = prev;
                middle.data = root;
            } else {
                last.data = root;
            }
        }
        inorderModified(root.right, root, first, middle, last);
    }


    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.root = new BTNode(6);
        bst.root.left = new BTNode(10);
        bst.root.right = new BTNode(2);
        bst.root.left.left = new BTNode(1);
        bst.root.left.right = new BTNode(3);
        bst.root.right.left = new BTNode(7);
        bst.root.right.right = new BTNode(12);
        BinaryTree.inOrderTraversal(bst);
        correctBstIfTwoNodesAreSwapped(bst);
        System.out.print("After correction:");
        BinaryTree.inOrderTraversal(bst);
    }
}
