package geeks_for_geeks.ds.tree.binary_search_tree;

import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import util.DoublePointer;
import javafx.util.Pair;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-10-2018 23:41
 **/
public class BinarySearchTreeApplications {

    /**
     * t=O(n)
     *
     * @param bst
     * @return
     */
    public static BinarySearchTree correctBstIfTwoNodesAreSwapped(BinarySearchTree bst) {
        assert bst != null && bst.root != null;

        DoublePointer<BTNode> first = new DoublePointer<>();
//        for the case where the two in correct nodes are adjacent to each other; in that case their is no last we will
//        use middle;
        DoublePointer<BTNode> middle = new DoublePointer<>();
        DoublePointer<BTNode> last = new DoublePointer<>();

        inorderModified(bst.root, null, first, middle, last);

//        the nodes are adjacent.
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

    /**
     * t=O(m+n)
     * s=O(h1+h2)
     * =O(n) ;skewed trees
     *
     * @param bt1
     * @param bt2
     */
    public static void mergedInorder(BinarySearchTree bt1, BinarySearchTree bt2) {
        ArrayDeque<BTNode> stack1 = new ArrayDeque<>();
        ArrayDeque<BTNode> stack2 = new ArrayDeque<>();

        if (bt1 == null) {
            BinaryTree.inOrderTraversal(bt2);
            return;
        }

        if (bt2 == null) {
            BinaryTree.inOrderTraversal(bt1);
            return;
        }

        BTNode curr1 = bt1.root;
        BTNode curr2 = bt2.root;

        while (curr1 != null || curr2 != null || !stack1.isEmpty() || !stack2.isEmpty()) {
            if (curr1 != null) {

                stack1.push(curr1);
                curr1 = curr1.left;
            } else if (curr2 != null) {

                stack2.push(curr2);
                curr2 = curr2.left;
            } else if (stack1.isEmpty()) {

                while (!stack2.isEmpty()) {
                    BTNode curr = stack2.pop();

//                    Previously all nodes which are at the left are visited, so we have to disconnect them to prevent
//                    them from traversing again.
                    curr.left = null;
                    BinaryTree.inOrderTraversal(new BinarySearchTree(curr));
                }
            } else if (stack2.isEmpty()) {

                while (!stack1.isEmpty()) {
                    BTNode curr = stack1.pop();
                    curr.left = null;
                    BinaryTree.inOrderTraversal(new BinarySearchTree(curr));
                }
            } else {
                curr1 = stack1.pop();
                curr2 = stack2.pop();

                if (curr1.data < curr2.data) {
                    System.out.print(curr1.data + "->");
                    curr1 = curr1.right;
                    stack2.push(curr2);
//                  Value of second geeks_for_geeks.stack is not used so push it again and make it null; As according to flow it can come
//                    again in this else and we don't have any check if curr2 contains some previous value.
                    curr2 = null;
                } else {
                    System.out.print(curr2.data + "->");
                    curr2 = curr2.right;
                    stack1.push(curr1);
                    curr1 = null;
                }
            }
        }
//        No need for a sysout at the end as it will be generated from inorder traversal.
    }

    public static Pair<BTNode, BTNode> findPairWithGivenSum(BinarySearchTree bst, int sum) {
        assert bst != null && bst.root != null;

        ArrayDeque<BTNode> stack = new ArrayDeque<>();
//        Reverse in order
        ArrayDeque<BTNode> stackForReversed = new ArrayDeque<>();

        BTNode curr = bst.root;
        BTNode currForReversed = bst.root;

        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }

        while (currForReversed != null) {
            stackForReversed.push(currForReversed);
            currForReversed = currForReversed.right;
        }

        while (true) {
            curr = stack.pop();
            currForReversed = stackForReversed.pop();

//            Both traversals crossed each other.
            if (curr.data >= currForReversed.data) {
                break;
            }

            int currSum = curr.data + currForReversed.data;

            if (currSum == sum) {
                return new Pair<>(curr, currForReversed);
            } else if (currSum > sum) {
                stack.push(curr);
                if (currForReversed.right != null) {
                    currForReversed = currForReversed.left;
                    while (currForReversed != null) {
                        stackForReversed.push(currForReversed);
                        currForReversed = currForReversed.right;
                    }
                }
            } else {
                stackForReversed.push(currForReversed);
                if (curr.right != null) {
                    curr = curr.right;
                    while (curr != null) {
                        stack.push(curr);
                        curr = curr.left;
                    }
                }
            }
        }

        return new Pair<>(null, null);
    }

    public static void convertBinaryTreeToBST(BinaryTree bt) {
        assert bt != null && bt.root != null;

        List<Integer> list = bt.getInOrder(false, -1);

        Collections.sort(list);

        updateWithGivenInorder(bt.root, list);
    }

    private static void updateWithGivenInorder(BTNode root, List<Integer> inOrder) {
        if (root == null) {
            return;
        }
        updateWithGivenInorder(root.left, inOrder);
        root.data = inOrder.get(0);
        inOrder.remove(0);
        updateWithGivenInorder(root.right, inOrder);
    }
}
