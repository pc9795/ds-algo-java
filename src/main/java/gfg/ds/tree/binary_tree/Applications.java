package gfg.ds.tree.binary_tree;

import utils.Pointer;

import java.util.*;

/** @noinspection WeakerAccess */
public class Applications {
  /**
   * t=O(n^2); because of linear search, we can optimize this by keeping a map but it will cost us
   * extra space. s=O(n); recursion stack
   */
  public static BinaryTree createFromPreAndInorder2(int[] preOrder, int[] inOrder) {
    assert preOrder.length == inOrder.length;
    return new BinaryTree()
        .insertAtRoot(
            createFromPreAndInorder2Util(
                preOrder, inOrder, 0, inOrder.length - 1, new Pointer<>(0)));
  }

  private static BinaryTree.BinaryTreeNode createFromPreAndInorder2Util(
      int[] preOrder, int[] inOrder, int inStart, int inEnd, Pointer<Integer> preIndex) {
    if (inStart > inEnd) {
      return null;
    }
    BinaryTree.BinaryTreeNode root = new BinaryTree.BinaryTreeNode(preOrder[preIndex.data]);
    preIndex.data++;
    if (inStart == inEnd) {
      return root;
    }
    int i = inStart;
    for (; i <= inEnd; i++) {
      if (root.data == inOrder[i]) {
        break;
      }
    }
    root.left = createFromPreAndInorder2Util(preOrder, inOrder, inStart, i - 1, preIndex);
    root.right = createFromPreAndInorder2Util(preOrder, inOrder, i + 1, inEnd, preIndex);
    return root;
  }

  /**
   * t=O(n) First node in the pre-order traversal is always the root node and the first node in the
   * inorder traversal is the leftmost node in the tree.
   */
  public static BinaryTree createFromPreAndInorder(int[] preOrder, int[] inOrder) {
    assert preOrder.length == inOrder.length;

    BinaryTree bt = null;
    ArrayDeque<BinaryTree.BinaryTreeNode> stack = new ArrayDeque<>();
    HashSet<BinaryTree.BinaryTreeNode> set = new HashSet<>();
    for (int pre = 0, in = 0; pre < preOrder.length; ) {
      do {
        BinaryTree.BinaryTreeNode node;
        if (bt == null) {
          // First element in pre-order is the root of the tree;
          bt = new BinaryTree().insertAtRoot(preOrder[pre]);
          node = bt.root;
        } else {
          node = new BinaryTree.BinaryTreeNode(preOrder[pre]);
          // Elements in the set represent elements whose right subtree is to be created.
          if (set.contains(stack.peek())) {
            set.remove(stack.peek());
            stack.pop().right = node;
          } else {
            BinaryTree.BinaryTreeNode top = stack.peek();
            assert top != null;
            top.left = node;
          }
        }
        stack.push(node);
      } while (preOrder[pre++] != inOrder[in] && pre < preOrder.length);

      BinaryTree.BinaryTreeNode nodeEligibleForRightSubTree = null;
      while (!stack.isEmpty()
          && in < inOrder.length
          && Objects.requireNonNull(stack.peek()).data == inOrder[in]) {
        nodeEligibleForRightSubTree = stack.pop();
        in++;
      }
      if (nodeEligibleForRightSubTree != null) {
        stack.push(nodeEligibleForRightSubTree);
        set.add(nodeEligibleForRightSubTree);
      }
    }
    return bt;
  }

  /** t=O(n) */
  public static int getMaxWidth(BinaryTree bt) {
    ArrayDeque<BinaryTree.BinaryTreeNode> q = new ArrayDeque<>();
    int max = Integer.MIN_VALUE;
    q.add(bt.root);
    while (!q.isEmpty()) {
      int nodesInLevel = q.size();
      max = Math.max(max, nodesInLevel);
      // Remove all nodes of prev level.
      while (nodesInLevel-- > 0) {
        BinaryTree.BinaryTreeNode tmp = q.poll();
        assert tmp != null;
        if (tmp.left != null) {
          q.add(tmp.left);
        }
        if (tmp.right != null) {
          q.add(tmp.right);
        }
      }
    }
    return max;
  }

  public static List<Integer> getAncestors(BinaryTree bt, BinaryTree.BinaryTreeNode node) {
    List<Integer> ancestors = new ArrayList<>();
    getAncestors(bt.root, node, ancestors);
    return ancestors;
  }

  /** t=O(n) */
  private static boolean getAncestors(
      BinaryTree.BinaryTreeNode curr,
      BinaryTree.BinaryTreeNode targetNode,
      List<Integer> ancestors) {
    if (curr == null) {
      return false;
    }
    if (curr.equals(targetNode)) {
      return true;
    }
    if (!getAncestors(curr.left, targetNode, ancestors)
        && !getAncestors(curr.right, targetNode, ancestors)) {
      return false;
    }
    ancestors.add(curr.data);
    return true;
  }
}
