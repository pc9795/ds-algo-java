package geeks_for_geeks.ds.tree.binary_search_tree;

import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import util.DoublePointer;
import util.Pair;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-10-2018 23:41
 **/
public class Applications {

    /**
     * t=O(n)
     *
     * @param bst binary search tree
     */
    public static void correctBSTIfTwoNodesAreSwapped(BinarySearchTree bst) {
        assert bst != null && !bst.isEmpty();

        DoublePointer<BTNode> first = new DoublePointer<>();
        // for the case where the two in correct nodes are adjacent to each other; in that case their is no last we will
        // use middle;
        DoublePointer<BTNode> middle = new DoublePointer<>();
        DoublePointer<BTNode> last = new DoublePointer<>();
        //Take care of in-order
        DoublePointer<BTNode> prev = new DoublePointer<>();
        correctBSTIfTwoNodesAreSwappedUtil(bst.root, prev, first, middle, last);

        // the nodes are adjacent.
        if (last.data == null) {
            int temp = first.data.data;
            first.data.data = middle.data.data;
            middle.data.data = temp;
        } else {
            int temp = first.data.data;
            first.data.data = last.data.data;
            last.data.data = temp;
        }
    }

    private static void correctBSTIfTwoNodesAreSwappedUtil(BTNode root,
                                                           DoublePointer<BTNode> prev,
                                                           DoublePointer<BTNode> first,
                                                           DoublePointer<BTNode> middle,
                                                           DoublePointer<BTNode> last) {
        if (root == null) {
            return;
        }
        correctBSTIfTwoNodesAreSwappedUtil(root.left, prev, first, middle, last);
        if (prev.data != null && prev.data.data > root.data) {
            if (first.data == null) {
                first.data = prev.data;
                middle.data = root;
            } else {
                last.data = root;
            }
        }
        prev.data = root;
        correctBSTIfTwoNodesAreSwappedUtil(root.right, prev, first, middle, last);
    }

    /**
     * t=O(m+n)
     * s=O(h1+h2)
     * =O(n) ;skewed trees
     *
     * @param first  first BST
     * @param second second BST
     */
    public static List<Integer> mergedInorder(BinarySearchTree first, BinarySearchTree second) {
        ArrayDeque<BTNode> firstStack = new ArrayDeque<>();
        ArrayDeque<BTNode> secondStack = new ArrayDeque<>();
        if (first == null || first.isEmpty()) {
            return BinaryTree.inOrderTraversal(second);
        }
        if (second == null || second.isEmpty()) {
            return BinaryTree.inOrderTraversal(first);
        }
        List<Integer> traversal = new ArrayList<>();
        for (BTNode curr = first.root; curr != null; curr = curr.left) {
            firstStack.push(curr);
        }
        for (BTNode curr = second.root; curr != null; curr = curr.left) {
            secondStack.push(curr);
        }
        while (!firstStack.isEmpty() && !secondStack.isEmpty()) {
            BTNode firstNode = firstStack.peek();
            BTNode secondNode = secondStack.peek();
            assert firstNode != null && secondNode != null;

            if (firstNode.data < secondNode.data) {
                traversal.add(firstNode.data);
                firstStack.pop();
                for (BTNode curr = firstNode.right; curr != null; curr = curr.left) {
                    firstStack.push(curr);
                }
            } else {
                traversal.add(secondNode.data);
                secondStack.pop();
                for (BTNode curr = secondNode.right; curr != null; curr = curr.left) {
                    secondStack.push(curr);
                }
            }
        }
        ArrayDeque<BTNode> leftOver = firstStack.isEmpty() ? secondStack : firstStack;
        while (!leftOver.isEmpty()) {
            BTNode curr = leftOver.pop();
            traversal.add(curr.data);
            for (BTNode temp = curr.right; temp != null; temp = temp.left) {
                secondStack.push(temp);
            }
        }
        return traversal;
    }

    /**
     * t=O(n)
     * s=O(h)
     *
     * @param bst binary search tree
     * @param sum target sum
     * @return a pair of numbers whose addition is equal to the given sum
     */
    public static Pair<BTNode, BTNode> findPairWithGivenSum(BinarySearchTree bst, int sum) {
        assert bst != null && !bst.isEmpty();

        ArrayDeque<BTNode> stack = new ArrayDeque<>();
        ArrayDeque<BTNode> stackReversed = new ArrayDeque<>();
        BTNode curr = bst.root;
        BTNode currReversed = bst.root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        while (currReversed != null) {
            stackReversed.push(currReversed);
            currReversed = currReversed.right;
        }
        while (true) {
            curr = stack.pop();
            currReversed = stackReversed.pop();
            // Both traversals crossed each other.
            if (curr.data >= currReversed.data) {
                break;
            }
            int currSum = curr.data + currReversed.data;
            if (currSum == sum) {
                return new Pair<>(curr, currReversed);
            }
            if (currSum > sum) {
                stack.push(curr);
                for (BTNode temp = currReversed.left; temp != null; temp = temp.right) {
                    stackReversed.push(temp);
                }
                continue;
            }
            //Less than case.
            stackReversed.push(currReversed);
            for (BTNode temp = curr.right; temp != null; temp = temp.left) {
                stack.push(temp);
            }
        }
        return new Pair<>(null, null);
    }

    /**
     * t=O(n*log n)
     * s=O(n)
     * It will respect the structure of the original tree
     *
     * @param bt input binary tree
     */
    public static void convertBinaryTreeToBST(BinaryTree bt) {
        assert bt != null && !bt.isEmpty();

        List<Integer> list = BinaryTree.inOrderTraversal(bt);
        //n*log n element
        Collections.sort(list);
        convertBinaryTreeToBSTUtil(bt.root, list, new DoublePointer<>(0));
    }

    private static void convertBinaryTreeToBSTUtil(BTNode root, List<Integer> inOrder, DoublePointer<Integer> index) {
        if (root == null) {
            return;
        }
        convertBinaryTreeToBSTUtil(root.left, inOrder, index);
        root.data = inOrder.get(index.data++);
        convertBinaryTreeToBSTUtil(root.right, inOrder, index);
    }
}