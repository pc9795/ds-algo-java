package gfg.ds.tree.binary_search_tree;

import gfg.ds.tree.binary_tree.BinaryTree;
import utils.DoublePointer;
import utils.Pair;

import java.util.*;

/** @noinspection WeakerAccess */
public class Applications {

  /** t=O(n) */
  public static void correctBSTIfTwoNodesAreSwapped(BinarySearchTree bst) {
    assert !bst.isEmpty();

    DoublePointer<BinaryTree.BinaryTreeNode> first = new DoublePointer<>();
    // for the case where the two in correct nodes are adjacent to each other; in that case their is
    // no last we will
    // use middle;
    DoublePointer<BinaryTree.BinaryTreeNode> middle = new DoublePointer<>();
    DoublePointer<BinaryTree.BinaryTreeNode> last = new DoublePointer<>();
    // Take care of in-order
    DoublePointer<BinaryTree.BinaryTreeNode> prev = new DoublePointer<>();
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

  private static void correctBSTIfTwoNodesAreSwappedUtil(
      BinaryTree.BinaryTreeNode root,
      DoublePointer<BinaryTree.BinaryTreeNode> prev,
      DoublePointer<BinaryTree.BinaryTreeNode> first,
      DoublePointer<BinaryTree.BinaryTreeNode> middle,
      DoublePointer<BinaryTree.BinaryTreeNode> last) {
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

  /** t=O(m+n) s=O(h1+h2) =O(n) ;skewed trees */
  public static List<Integer> mergedInorder(BinarySearchTree first, BinarySearchTree second) {
    ArrayDeque<BinaryTree.BinaryTreeNode> firstStack = new ArrayDeque<>();
    ArrayDeque<BinaryTree.BinaryTreeNode> secondStack = new ArrayDeque<>();
    if (first == null || first.isEmpty()) {
      return BinaryTree.inOrderTraversal(second);
    }
    if (second == null || second.isEmpty()) {
      return BinaryTree.inOrderTraversal(first);
    }
    List<Integer> traversal = new ArrayList<>();
    for (BinaryTree.BinaryTreeNode curr = first.root; curr != null; curr = curr.left) {
      firstStack.push(curr);
    }
    for (BinaryTree.BinaryTreeNode curr = second.root; curr != null; curr = curr.left) {
      secondStack.push(curr);
    }
    while (!firstStack.isEmpty() && !secondStack.isEmpty()) {
      BinaryTree.BinaryTreeNode firstNode = firstStack.peek();
      BinaryTree.BinaryTreeNode secondNode = secondStack.peek();
      assert firstNode != null && secondNode != null;

      if (firstNode.data < secondNode.data) {
        traversal.add(firstNode.data);
        firstStack.pop();
        for (BinaryTree.BinaryTreeNode curr = firstNode.right; curr != null; curr = curr.left) {
          firstStack.push(curr);
        }
      } else {
        traversal.add(secondNode.data);
        secondStack.pop();
        for (BinaryTree.BinaryTreeNode curr = secondNode.right; curr != null; curr = curr.left) {
          secondStack.push(curr);
        }
      }
    }
    ArrayDeque<BinaryTree.BinaryTreeNode> leftOver =
        firstStack.isEmpty() ? secondStack : firstStack;
    while (!leftOver.isEmpty()) {
      BinaryTree.BinaryTreeNode curr = leftOver.pop();
      traversal.add(curr.data);
      for (BinaryTree.BinaryTreeNode temp = curr.right; temp != null; temp = temp.left) {
        secondStack.push(temp);
      }
    }
    return traversal;
  }

  /** t=O(n) s=O(h) */
  public static Pair<BinaryTree.BinaryTreeNode, BinaryTree.BinaryTreeNode> findPairWithGivenSum(
      BinarySearchTree bst, int sum) {
    assert !bst.isEmpty();

    ArrayDeque<BinaryTree.BinaryTreeNode> stack = new ArrayDeque<>();
    ArrayDeque<BinaryTree.BinaryTreeNode> stackReversed = new ArrayDeque<>();
    BinaryTree.BinaryTreeNode curr = bst.root;
    BinaryTree.BinaryTreeNode currReversed = bst.root;
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
        for (BinaryTree.BinaryTreeNode temp = currReversed.left; temp != null; temp = temp.right) {
          stackReversed.push(temp);
        }
        continue;
      }
      // Less than case.
      stackReversed.push(currReversed);
      for (BinaryTree.BinaryTreeNode temp = curr.right; temp != null; temp = temp.left) {
        stack.push(temp);
      }
    }
    return new Pair<>(null, null);
  }

  /** t=O(n*log n) s=O(n) It will respect the structure of the original tree */
  public static void convertBinaryTreeToBST(BinaryTree bt) {
    assert !bt.isEmpty();

    List<Integer> list = BinaryTree.inOrderTraversal(bt);
    // n*log n element
    Collections.sort(list);
    convertBinaryTreeToBSTUtil(bt.root, list, new DoublePointer<>(0));
  }

  private static void convertBinaryTreeToBSTUtil(
      BinaryTree.BinaryTreeNode root, List<Integer> inOrder, DoublePointer<Integer> index) {
    if (root == null) {
      return;
    }
    convertBinaryTreeToBSTUtil(root.left, inOrder, index);
    root.data = inOrder.get(index.data++);
    convertBinaryTreeToBSTUtil(root.right, inOrder, index);
  }
}
