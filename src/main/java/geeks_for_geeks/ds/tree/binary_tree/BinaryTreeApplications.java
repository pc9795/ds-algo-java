package geeks_for_geeks.ds.tree.binary_tree;

import geeks_for_geeks.ds.nodes.BTNode;

import java.util.ArrayDeque;
import java.util.HashSet;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-02-2019 17:37
 * Purpose: TODO:
 **/
public class BinaryTreeApplications {

    public static BinaryTree createFromPreAndInorder(int[] preOrder, int[] inOrder) {
        assert preOrder.length == inOrder.length;

        BinaryTree bt = null;
        ArrayDeque<BTNode> stack = new ArrayDeque<>();
        HashSet<BTNode> set = new HashSet<>();

        for (int pre = 0, in = 0; pre < preOrder.length; ) {
            do {
                BTNode node = null;
                if (bt == null) {
//                    First element in preorder is the root of the tree;
                    bt = new BinaryTree(preOrder[pre]);
                    node = bt.root;
                } else {
                    node = new BTNode(preOrder[pre]);
//                    Elements in the set represent elements whose right subtree is to be created.
                    if (!set.contains(stack.peek())) {
                        stack.peek().left = node;
                    } else {
                        set.remove(stack.peek());
                        stack.pop().right = node;
                    }
                }
                stack.push(node);
            } while (preOrder[pre++] != inOrder[in] && pre < preOrder.length);

            BTNode nodeEligibleForRightSubTree = null;

            while (!stack.isEmpty() && in < inOrder.length && stack.peek().data == inOrder[in]) {
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

    /**
     * t=O(n)
     *
     * @param bt
     * @return
     */
    public static int getMaxWidth(BinaryTree bt) {
        assert bt != null;
        ArrayDeque<BTNode> q = new ArrayDeque<>();
        int max = Integer.MIN_VALUE;

        q.add(bt.root);
        while (!q.isEmpty()) {
            int nodesInLevel = q.size();
            max = Math.max(max, nodesInLevel);

//            Remove all nodes of prev level.
            while (nodesInLevel-- > 0) {
                BTNode tmp = q.poll();
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

    public static void printAncestors(BinaryTree bt, BTNode node) {
        assert bt != null;
        assert node != null;

        printAncestorsUtil(bt.root, node);
    }

    /**
     * t=O(n)
     *
     * @param root
     * @param targetNode
     * @return
     */
    private static boolean printAncestorsUtil(BTNode root, BTNode targetNode) {
        if (root == null) {
            return false;
        }
        if (root.equals(targetNode)) {
            return true;
        }

        if (printAncestorsUtil(root.left, targetNode) || printAncestorsUtil(root.right, targetNode)) {

//            I should have printed the root's string repr for more robust code, but it would yield difficulty in testing
            System.out.println(root.data);
            return true;
        }
        return false;
    }
}


