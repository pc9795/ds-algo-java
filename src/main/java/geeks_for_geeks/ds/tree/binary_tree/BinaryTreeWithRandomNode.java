package geeks_for_geeks.ds.tree.binary_tree;

import geeks_for_geeks.ds.nodes.BTNodeWithRandom;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-02-2019 17:41
 **/
public class BinaryTreeWithRandomNode {
    public BTNodeWithRandom root;

    public BinaryTreeWithRandomNode(int data) {
        this.root = new BTNodeWithRandom(data);
    }

    private BinaryTreeWithRandomNode(BTNodeWithRandom root) {
        this.root = root;
    }

    public BinaryTreeWithRandomNode copyUsingMap() {
        assert this.root != null : "Tree is emtpy";

        HashMap<BTNodeWithRandom, BTNodeWithRandom> map = new HashMap<>();
        ArrayDeque<BTNodeWithRandom> queue = new ArrayDeque<>();
        queue.add(this.root);
        // Caching all nodes using level order traversal.
        while (!queue.isEmpty()) {
            BTNodeWithRandom node = queue.poll();
            assert node != null;
            map.put(node, new BTNodeWithRandom(node.data));
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
            BTNodeWithRandom node = queue.poll();
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
        return new BinaryTreeWithRandomNode(map.get(this.root));
    }


    public BinaryTreeWithRandomNode copy() {
        BTNodeWithRandom cloneRoot = buildCloneNodes(root);
        fixRandomLinks(root, cloneRoot);
        restoreTree(root, cloneRoot);
        return new BinaryTreeWithRandomNode(cloneRoot);
    }

    private BTNodeWithRandom buildCloneNodes(BTNodeWithRandom root) {
        if (root == null) {
            return null;
        }
        BTNodeWithRandom left = root.left;
        root.left = new BTNodeWithRandom(root.data);
        root.left.left = left;
        if (left != null) {
            left.left = buildCloneNodes(left);
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

    public static List<Integer> inOrderTraversal(BinaryTreeWithRandomNode tree) {
        assert tree != null : "Null instance given";
        List<Integer> traversal = new ArrayList<>();
        inOrderTraversalUtil(tree.root, traversal);
        return traversal;
    }

    private static void inOrderTraversalUtil(BTNodeWithRandom root, List<Integer> traversal) {
        if (root == null) {
            return;
        }
        inOrderTraversalUtil(root.left, traversal);
        traversal.add(root.data);
        inOrderTraversalUtil(root.right, traversal);
    }
}
