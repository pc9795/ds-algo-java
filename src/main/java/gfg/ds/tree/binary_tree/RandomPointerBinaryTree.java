package gfg.ds.tree.binary_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-02-2019 17:41
 **/
public class RandomPointerBinaryTree {
    public RandomPointerBinaryTreeNode root;

    public RandomPointerBinaryTree insertAtRoot(RandomPointerBinaryTreeNode node) {
        this.root = node;
        return this;
    }

    public RandomPointerBinaryTree insertAtRoot(int data) {
        this.root = new RandomPointerBinaryTreeNode(data);
        return this;
    }

    public RandomPointerBinaryTree copyUsingMap() {
        assert this.root != null : "Tree is emtpy";

        HashMap<RandomPointerBinaryTreeNode, RandomPointerBinaryTreeNode> map = new HashMap<>();
        ArrayDeque<RandomPointerBinaryTreeNode> queue = new ArrayDeque<>();
        queue.add(this.root);
        // Caching all nodes using level order traversal.
        while (!queue.isEmpty()) {
            RandomPointerBinaryTreeNode node = queue.poll();
            assert node != null;
            map.put(node, new RandomPointerBinaryTreeNode(node.data));
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
            RandomPointerBinaryTreeNode node = queue.poll();
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
        return new RandomPointerBinaryTree().insertAtRoot(map.get(this.root));
    }


    public RandomPointerBinaryTree copy() {
        RandomPointerBinaryTreeNode cloneRoot = buildCloneNodes(root);
        fixRandomLinks(root, cloneRoot);
        restoreTree(root, cloneRoot);
        return new RandomPointerBinaryTree().insertAtRoot(cloneRoot);
    }

    private RandomPointerBinaryTreeNode buildCloneNodes(RandomPointerBinaryTreeNode root) {
        if (root == null) {
            return null;
        }
        RandomPointerBinaryTreeNode left = root.left;
        root.left = new RandomPointerBinaryTreeNode(root.data);
        root.left.left = left;
        if (left != null) {
            left.left = buildCloneNodes(left);
        }
        root.left.right = buildCloneNodes(root.right);
        return root.left;
    }

    private void fixRandomLinks(RandomPointerBinaryTreeNode originalRoot, RandomPointerBinaryTreeNode cloneRoot) {
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

    private void restoreTree(RandomPointerBinaryTreeNode originalRoot, RandomPointerBinaryTreeNode cloneRoot) {
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

    public static List<Integer> inOrderTraversal(RandomPointerBinaryTree tree) {
        assert tree != null : "Null instance given";
        List<Integer> traversal = new ArrayList<>();
        inOrderTraversalUtil(tree.root, traversal);
        return traversal;
    }

    private static void inOrderTraversalUtil(RandomPointerBinaryTreeNode root, List<Integer> traversal) {
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
    public static class RandomPointerBinaryTreeNode {
        public int data;
        public RandomPointerBinaryTreeNode left;
        public RandomPointerBinaryTreeNode right;
        public RandomPointerBinaryTreeNode random;

        public RandomPointerBinaryTreeNode(int data) {
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
