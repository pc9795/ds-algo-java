package geeks_for_geeks.ds.tree.binary_search_tree.balanced;

import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;

/**
 * Created By: Prashant Chaubey
 * Created On: 23-06-2020 19:33
 **/
public class SplayTree extends BinaryTree {
    public SplayTree(int data) {
        super(data);
    }

    /**
     * t=O(1)
     * Similar to right rotation
     */
    private BTNode zig(BTNode node) {
        assert node != null && node.left != null;

        BTNode leftRight = node.left.right;
        node.left.right = node;
        BTNode newNode = node.left;
        node.left = leftRight;

        return newNode;
    }

    /**
     * t=O(1)
     * Similar to left rotation
     */
    private BTNode zag(BTNode node) {
        assert node != null && node.right != null;

        BTNode rightLeft = node.right.left;
        node.right.left = node;
        BTNode newNode = node.right;
        node.right = rightLeft;

        return newNode;
    }

    /**
     * t=O(log n)
     */
    private BTNode splay(BTNode node, int data) {
        if (node == null || node.data == data) {
            return node;
        }
        if (data < node.data) {
            if (node.left == null) {
                return node;
            }
            if (data < node.left.data) {
                node.left.left = splay(node.left.left, data);
                node = zig(node);
            } else {
                node.left.right = splay(node.left.right, data);
                if (node.left.right != null) {
                    node.left = zag(node.left);
                }
            }
            //We are again checking as left can be changed due to previous operations.
            return node.left == null ? node : zig(node);
        } else {
            if (node.right == null) {
                return node;
            }
            if (data > node.right.data) {
                node.right.right = splay(node.right.right, data);
                node = zag(node);
            } else {
                node.right.left = splay(node.right.left, data);
                if (node.right.left != null) {
                    node.right = zig(node.right);
                }
            }
            //We are again checking as right can be changed due to previous operations.
            return node.right == null ? node : zag(node);
        }
    }

    /**
     * t=O(log n)
     */
    public SplayTree insert(int data) {
        root = splay(root, data);
        if (data < root.data) {
            BTNode newNode = new BTNode(data);
            newNode.right = root;
            //todo explanation that why this will not break the BST property
            newNode.left = root.left;
            root.left = null;
            root = newNode;
        } else if (data > root.data) {
            BTNode newNode = new BTNode(data);
            newNode.left = root;
            //todo explanation that why this will not break the BST property
            newNode.right = root.right;
            root.right = null;
            root = newNode;
        } else {
            throw new RuntimeException("Duplicate data");
        }

        return this;
    }

    public boolean search(int data) {
        root = splay(root, data);
        return root != null && root.data == data;
    }
}
