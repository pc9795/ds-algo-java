package gfg.ds.tree.binary_tree;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 31-01-2020 13:31
 **/
public class BinaryTreeWithSibling {
    public BinaryTreeNode root;

    public BinaryTreeWithSibling insertAtRoot(int data) {
        this.root = new BinaryTreeNode(data);
        return this;
    }

    /**
     * t=O(n)
     */
    public void updateRightSiblings() {
        ArrayDeque<BinaryTreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int levelSize = q.size();
            BinaryTreeNode curr;
            int i = 0;
            do {
                curr = q.poll();
                assert curr != null;
                if (curr.left != null) {
                    q.offer(curr.left);
                }
                if (curr.right != null) {
                    q.offer(curr.right);
                }
                //For last node we don't have to update the right sibling link
                if (i == levelSize - 1) {
                    continue;
                }
                curr.rightSibling = q.peek();
            } while (++i < levelSize);
        }
    }

    public void updateRightSiblings2() {
        assert isCompleteBST(this) : "This method can only work for a complete BST";

        updateRightSiblings2Util(root);
    }

    private void updateRightSiblings2Util(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            root.left.rightSibling = root.right;
        }
        //Right sibling will be set before hand because of pre order traversal
        if (root.right != null && root.rightSibling != null) {
            root.right.rightSibling = root.rightSibling.left;
        }
        updateRightSiblings2Util(root.left);
        updateRightSiblings2Util(root.right);
    }

    public static boolean isCompleteBST(BinaryTreeWithSibling bt) {
        assert bt != null : "Null instance given";
        return isCompleteBSTUtil(bt.root);
    }

    private static boolean isCompleteBSTUtil(BinaryTreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        return isCompleteBSTUtil(root.left) && isCompleteBSTUtil(root.right);
    }

    /**
     * Created By: Prashant Chaubey
     * Created On: 31-01-2020 13:29
     **/
    public static class BinaryTreeNode {
        public int data;
        public BinaryTreeNode left;
        public BinaryTreeNode right;
        public BinaryTreeNode rightSibling;

        public BinaryTreeNode(int data) {
            this.data = data;
        }
    }
}
