package geeks_for_geeks.ds.tree.binary_tree;

import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.nodes.BTNodeWithRandom;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-02-2019 17:41
 * Purpose: TODO:
 **/
public class BinaryTreeWithRandomNode {

    public BTNodeWithRandom root;

    public BinaryTreeWithRandomNode(int data) {
        this.root = new BTNodeWithRandom(data);
    }

    public BinaryTreeWithRandomNode cloneUsingHashMap() {
        HashMap<BTNodeWithRandom, BTNodeWithRandom> map = new HashMap<>();
        ArrayDeque<BTNodeWithRandom> queue = new ArrayDeque<>();

        queue.add(this.root);

//        Caching all nodes using level order traversal.
        while (!queue.isEmpty()) {
            BTNodeWithRandom node = queue.poll();
            map.put(node, new BTNodeWithRandom(node.data));
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }

        queue.add(this.root);

//        Fixing the links.
        while (!queue.isEmpty()) {
            BTNodeWithRandom node = queue.poll();

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
        BinaryTreeWithRandomNode clone = new BinaryTreeWithRandomNode(-1);
        clone.root = map.get(this.root);

        return clone;
    }


    @Override
    public BinaryTreeWithRandomNode clone() {
        BTNodeWithRandom cloneRoot = buildCloneNodes(this.root);
        fixRandomLinks(this.root, cloneRoot);
        restoreTree(this.root, cloneRoot);

        BinaryTreeWithRandomNode bt = new BinaryTreeWithRandomNode(-1);
        bt.root = cloneRoot;

        return bt;
    }

    private BTNodeWithRandom buildCloneNodes(BTNodeWithRandom root) {
        if (root == null) {
            return null;
        }
        BTNodeWithRandom left = root.left;
        root.left = new BTNodeWithRandom(root.data);
        root.left.left = left;
        if (left != null) {
            left.left = buildCloneNodes(root.left.left);
        }

        root.left.right = buildCloneNodes(root.right);

        return root.left;
    }

    private void fixRandomLinks(BTNodeWithRandom originalRoot, BTNodeWithRandom cloneRoot) {
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

    private void restoreTree(BTNodeWithRandom originalRoot, BTNodeWithRandom cloneRoot) {
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

    public static void inOrderTraversal(BinaryTreeWithRandomNode tree) {
        if (tree == null) {
            throw new RuntimeException("Tree is empty!");
        }
        inOrderTraversalUtil(tree.root);
        System.out.println();
    }

    private static void inOrderTraversalUtil(BTNodeWithRandom root) {
        if (root == null) {
            return;
        }
        inOrderTraversalUtil(root.left);
        System.out.println(root);
        inOrderTraversalUtil(root.right);
    }
}
