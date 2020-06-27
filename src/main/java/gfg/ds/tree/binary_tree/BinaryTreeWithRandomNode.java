package gfg.ds.tree.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-02-2019 17:41
 **/
public class BinaryTreeWithRandomNode {
    public BinaryTreeNode root;

    public BinaryTreeWithRandomNode insertAtRoot(BinaryTreeNode node) {
        this.root = node;
        return this;
    }

    public BinaryTreeWithRandomNode insertAtRoot(int data) {
        this.root = new BinaryTreeNode(data);
        return this;
    }

    public BinaryTreeWithRandomNode copyUsingMap() {
        assert this.root != null : "Tree is emtpy";

        HashMap<BinaryTreeNode, BinaryTreeNode> map = new HashMap<>();
        ArrayDeque<BinaryTreeNode> queue = new ArrayDeque<>();
        queue.add(this.root);
        // Caching all nodes using level order traversal.
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            assert node != null;
            map.put(node, new BinaryTreeNode(node.data));
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        //Queue will be empty at this point
        queue.add(this.root);

        //Fixing the links.
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            assert node != null;
            if (node.left != null) {
                map.get(node).left = map.get(node.left);
                queue.add(node.left);
            }
            if (node.right != null) {
                map.get(node).right = map.get(node.right);
                queue.add(node.right);
            }
            if (node.random != null) {
                map.get(node).random = map.get(node.random);
            }
        }
        return new BinaryTreeWithRandomNode().insertAtRoot(map.get(this.root));
    }


    public BinaryTreeWithRandomNode copy() {
        BinaryTreeNode cloneRoot = buildCloneNodes(root);
        fixRandomLinks(root, cloneRoot);
        restoreTree(root, cloneRoot);
        return new BinaryTreeWithRandomNode().insertAtRoot(cloneRoot);
    }

    private BinaryTreeNode buildCloneNodes(BinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        BinaryTreeNode left = root.left;
        root.left = new BinaryTreeNode(root.data);
        root.left.left = left;
        if (left != null) {
            left.left = buildCloneNodes(left);
        }
        root.left.right = buildCloneNodes(root.right);
        return root.left;
    }

    private void fixRandomLinks(BinaryTreeNode originalRoot, BinaryTreeNode cloneRoot) {
        if (originalRoot == null) {
            return;
        }
        if (originalRoot.random != null) {
            cloneRoot.random = originalRoot.random.left;
        }
        if (originalRoot.left.left != null) {
            fixRandomLinks(originalRoot.left.left, cloneRoot.left.left);
        }
        if (originalRoot.right != null) {
            fixRandomLinks(originalRoot.right, cloneRoot.right);
        }
    }

    private void restoreTree(BinaryTreeNode originalRoot, BinaryTreeNode cloneRoot) {
        if (originalRoot == null) {
            return;
        }
        originalRoot.left = cloneRoot.left;
        if (originalRoot.left != null) {
            cloneRoot.left = originalRoot.left.left;
        }
        restoreTree(originalRoot.left, cloneRoot.left);
        restoreTree(originalRoot.right, cloneRoot.right);
    }

    public static List<Integer> inOrderTraversal(BinaryTreeWithRandomNode tree) {
        assert tree != null : "Null instance given";
        List<Integer> traversal = new ArrayList<>();
        inOrderTraversalUtil(tree.root, traversal);
        return traversal;
    }

    private static void inOrderTraversalUtil(BinaryTreeNode root, List<Integer> traversal) {
        if (root == null) {
            return;
        }
        inOrderTraversalUtil(root.left, traversal);
        traversal.add(root.data);
        inOrderTraversalUtil(root.right, traversal);
    }

    /**
     * Created By: Prashant Chaubey
     * Created On: 09-02-2019 17:43
     **/
    public static class BinaryTreeNode {
        public int data;
        public BinaryTreeNode left;
        public BinaryTreeNode right;
        public BinaryTreeNode random;

        public BinaryTreeNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "BTNodeWithRandom{" +
                    "data=" + data + "[" + (this.random == null ? "Null" : this.random.data) + "]" +
                    '}';
        }
    }
}
