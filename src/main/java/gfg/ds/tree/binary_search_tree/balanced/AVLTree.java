package gfg.ds.tree.binary_search_tree.balanced;

import gfg.ds.tree.binary_search_tree.BinarySearchTree;
import gfg.ds.tree.binary_tree.BinaryTree;

/**
 * Created By: Prashant Chaubey
 * Created On: 22-06-2020 22:38
 **/
public class AVLTree extends BinarySearchTree {

    /**
     * t=O(1)
     * NOTE: It MODIFIES the links of input node.
     */
    private BinaryTreeNode rightRotate(BinaryTreeNode node) {
        assert node != null && node.left != null;

        BinaryTreeNode leftRight = node.left.right;
        node.left.right = node;
        BinaryTreeNode newNode = node.left;
        node.left = leftRight;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        newNode.height = 1 + Math.max(height(newNode.left), height(newNode.right));

        return newNode;
    }

    /**
     * t=O(1)
     * NOTE: It MODIFIES the links of input node.
     */
    private BinaryTreeNode leftRotate(BinaryTreeNode node) {
        assert node != null && node.right != null;

        BinaryTreeNode rightLeft = node.right.left;
        node.right.left = node;
        BinaryTreeNode newNode = node.right;
        node.right = rightLeft;

        node.height = 1 + Math.max(height(node.left), height(node.right));
        newNode.height = 1 + Math.max(height(newNode.left), height(newNode.right));

        return newNode;
    }

    /**
     * t=O(1)
     * NOTE: It MODIFIES the links of input node.
     */
    private BinaryTreeNode leftLeft(BinaryTreeNode node) {
        return rightRotate(node);
    }

    /**
     * t=O(1)
     * NOTE: It MODIFIES the links of input node.
     */
    private BinaryTreeNode leftRight(BinaryTreeNode node) {
        assert node != null && node.left != null;

        node.left = leftRotate(node.left);
        return rightRotate(node);
    }

    /**
     * t=O(1)
     * NOTE: It MODIFIES the links of input node.
     */
    private BinaryTreeNode rightLeft(BinaryTreeNode node) {
        assert node != null && node.right != null;

        node.right = rightRotate(node.right);
        return leftRotate(node);
    }

    /**
     * t=O(1)
     * NOTE: It MODIFIES the links of input node.
     */
    private BinaryTreeNode rightRight(BinaryTreeNode node) {
        return leftRotate(node);
    }

    /**
     * t=O(1)
     */
    private int balanceFactor(BinaryTreeNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    /**
     * t=O(1)
     */
    private int height(BinaryTreeNode node) {
        return node == null ? 0 : node.height;
    }

    @Override
    public AVLTree insert(int data) {
        root = insertUtil(root, data);
        return this;
    }

    /**
     * t=O(log n)
     * In the recursive BST insert, after insertion, we get pointers to all ancestors one by one in a bottom-up manner.
     * So we don’t need parent pointer to travel up. The recursive code itself travels up and visits all the ancestors
     * of the newly inserted node.
     */
    private BinaryTreeNode insertUtil(BinaryTreeNode node, int data) {
        if (node == null) {
            node = new BinaryTreeNode(data);
            node.height = 1;
            return node;
        }
        if (data > node.data) {
            node.right = insertUtil(node.right, data);
        } else if (data < node.data) {
            node.left = insertUtil(node.left, data);
        } else {
            throw new RuntimeException("Duplicate Data");
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balanceFactor = balanceFactor(node);
        if (balanceFactor > 1) {
            //Left subtree is larger
            if (data < node.left.data) {
                return leftLeft(node);
            } else {
                return leftRight(node);
            }
        } else if (balanceFactor < -1) {
            //Right subtree is larger
            if (data > node.right.data) {
                return rightRight(node);
            } else {
                return rightLeft(node);
            }
        }
        return node;
    }

    @Override
    public AVLTree delete(int data) {
        this.root = deleteUtil(this.root, data);
        return this;
    }

    /**
     * t=O(log n)
     * In the recursive BST delete, after deletion, we get pointers to all ancestors one by one in bottom up manner. So
     * we don’t need parent pointer to travel up. The recursive code itself travels up and visits all the ancestors of the
     * deleted node.
     */
    private BinaryTreeNode deleteUtil(BinaryTreeNode node, int data) {
        if (node == null) {
            throw new RuntimeException(String.format("Node with data:%s is not found", data));
        }
        if (data < node.data) {
            node.left = deleteUtil(node.left, data);
        } else if (data > node.data) {
            node.right = deleteUtil(node.right, data);
        } else {
            //Single or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            //Two children
            BinaryTreeNode inOrderSucc = BinaryTree.inOrderSucc(node);
            node.right = deleteUtil(node.right, inOrderSucc.data);
            //Swapping links instead of data
            inOrderSucc.left = node.left;
            inOrderSucc.right = node.right;

            return inOrderSucc;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balanceFactor = balanceFactor(node);
        if (balanceFactor > 1) {
            //We are not comparing this with the data as in the case of insertion as the case is different. Try to draw
            //a picture you will understand.
            //Left subtree is larger
            if (balanceFactor(root.left) >= 0) {
                return leftLeft(node);
            } else {
                return leftRight(node);
            }
        } else if (balanceFactor < -1) {
            //Right subtree is larger
            if (balanceFactor(root.right) <= 0) {
                return rightRight(node);
            } else {
                return rightLeft(node);
            }
        }
        return node;
    }
}
