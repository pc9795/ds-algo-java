package geeks_for_geeks.ds.tree.binary_search_tree.balanced;

import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_search_tree.BinarySearchTree;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;

/**
 * Created By: Prashant Chaubey
 * Created On: 22-06-2020 22:38
 **/
public class RedBlackTree extends BinarySearchTree {

    @Override
    public RedBlackTree insert(int data) {
        BTNode newNode = new BTNode(data);
        newNode.color = BTNode.Color.RED;

        if (isEmpty()) {
            root = newNode;
            fixInsertion(newNode);
            return this;
        }

        BTNode curr = root;
        BTNode prev = null;
        for (; curr != null; ) {
            assert curr.data != data : "Duplicate data";

            prev = curr;
            curr = data < curr.data ? curr.left : curr.right;
        }

        // Prev is empty only if tree is empty but we are checking that before hand.
        assert prev != null;

        newNode.parent = prev;
        if (data < prev.data) {
            prev.left = newNode;
        } else {
            prev.right = newNode;
        }

        fixInsertion(newNode);
        return this;
    }

    /**
     * t=O(1)
     * NOTE: It MODIFIES the links of input node.
     */
    private void rightRotate(BTNode node) {
        assert node != null && node.left != null;

        BTNode newNode = node.left;
        newNode.parent = node.parent;
        if (node.parent == null) {
            root = newNode;
        } else {
            if (node == node.parent.left) {
                node.parent.left = newNode;
            } else {
                node.parent.right = newNode;
            }
        }
        BTNode leftRight = node.left.right;
        node.left.right = node;
        node.parent = node.left;
        node.left = leftRight;
        if (leftRight != null) {
            leftRight.parent = node;
        }

    }

    /**
     * t=O(1)
     * NOTE: It MODIFIES the links of input node.
     */
    private void leftRotate(BTNode node) {
        assert node != null && node.right != null;

        BTNode newNode = node.right;
        newNode.parent = node.parent;
        if (node.parent == null) {
            root = newNode;
        } else {
            if (node == node.parent.left) {
                node.parent.left = newNode;
            } else {
                node.parent.right = newNode;
            }
        }
        BTNode rightLeft = node.right.left;
        node.right.left = node;
        node.parent = node.right;
        node.right = rightLeft;
        if (rightLeft != null) {
            rightLeft.parent = node;
        }
    }

    private void fixInsertion(BTNode node) {
        //This method is only for nodes that are newly inserted and they are RED by default.
        assert node.color == BTNode.Color.RED : "Expected color of the node to be RED";

        while (node != root && node.parent.color == BTNode.Color.RED) {
            //We only have this condition violated for the child of root. But root is black so it is not possible as
            // we are also checking for parent's color to be RED.
            assert node.parent.parent != null;

            BTNode parent = node.parent;
            BTNode grandParent = node.parent.parent;
            BTNode uncle = parent == grandParent.left ? grandParent.right : grandParent.left;

            //Null node is treated as a black node.
            if (uncle != null && uncle.color == BTNode.Color.RED) {
                uncle.color = BTNode.Color.BLACK;
                parent.color = BTNode.Color.BLACK;
                grandParent.color = BTNode.Color.RED;
                node = grandParent;
            } else {
                //Parent is left child
                if (parent == grandParent.left) {
                    if (node == parent.right) {
                        leftRotate(parent);
                        //The parent is now at the position where the actual Right right case will be applied.
                        node = parent;
                        parent = node.parent;
                        grandParent = parent.parent;
                    }
                    grandParent.color = BTNode.Color.RED;
                    parent.color = BTNode.Color.BLACK;
                    rightRotate(grandParent);
                } else {
                    //Parent is right child
                    if (node == parent.left) {
                        rightRotate(parent);
                        //The parent is now at the position where the actual Left left case will be applied.
                        node = parent;
                        parent = node.parent;
                        grandParent = parent.parent;
                    }
                    parent.color = BTNode.Color.BLACK;
                    grandParent.color = BTNode.Color.RED;
                    leftRotate(grandParent);
                }
            }

        }
        //Root is always black
        root.color = BTNode.Color.BLACK;
    }

    @Override
    public RedBlackTree delete(int key) {
        assert !isEmpty() : "Empty tree";

        BTNode curr = root;
        for (; curr != null; ) {
            if (curr.data == key) {
                break;
            }
            curr = key < curr.data ? curr.left : curr.right;
        }

        assert curr != null : "Element not found";

        if (curr.left == null) {
            fixDeletion(curr, curr.right);
            if (curr.parent == null) {
                root = curr.right;
            } else {
                if (curr.parent.right == curr) {
                    curr.parent.right = curr.right;
                } else {
                    curr.parent.left = curr.right;
                }
            }
            if (curr.right != null) {
                curr.right.parent = curr.parent;
            }
            return this;
        } else if (curr.right == null) {
            fixDeletion(curr, curr.left);
            if (curr.parent == null) {
                root = curr.left;
            } else {
                if (curr.parent.right == curr) {
                    curr.parent.right = curr.left;
                } else {
                    curr.parent.left = curr.left;
                }
            }
            if (curr.left != null) {
                curr.left.parent = curr.parent;
            }
            return this;
        }
        // Node with two children.
        // Finding in-order predecessor for the node. We can also use in-order successor
        BTNode inOrderSucc = BinaryTree.inOrderPred(curr);
        delete(inOrderSucc.data);
        inOrderSucc.left = curr.left;
        inOrderSucc.right = curr.right;
        if (curr.left != null) {
            curr.left.parent = inOrderSucc;
        }
        if (curr.right != null) {
            curr.right.parent = inOrderSucc;
        }
        if (curr.parent == null) {
            root = inOrderSucc;
        } else {
            if (curr == curr.parent.right) {
                curr.parent.right = inOrderSucc;
                inOrderSucc.parent = curr.parent;
            } else {
                curr.parent.left = inOrderSucc;
                inOrderSucc.parent = curr.parent;
            }
        }
        inOrderSucc.color = curr.color;
        return this;
    }

    private void fixDeletion(BTNode toBeDeleted, BTNode replacement) {
        assert toBeDeleted != null;

        if (toBeDeleted.color == BTNode.Color.RED) {
            // Child can't be red as we can't have two consecutive reds
            // If child is null or black we don't care as black height is not affected by deletion of RED node.
            return;
        }

        if (replacement != null && replacement.color == BTNode.Color.RED) {
            // A black node is deleted and the child with RED replaced it.
            replacement.color = BTNode.Color.BLACK;
            return;
        }

        while (toBeDeleted != root) {
            BTNode sibling = (toBeDeleted.parent.left == toBeDeleted) ? toBeDeleted.parent.right : toBeDeleted.parent.left;
            BTNode parent = toBeDeleted.parent;

            if (sibling.color == BTNode.Color.RED) {
                // Rotation will move old sibling up; Recolor the old sibling and parent. The new sibling is always black.
                // This converts the tree to black sibling case.
                if (sibling == parent.left) {
                    rightRotate(parent);
                } else {
                    leftRotate(parent);
                }
                parent.color = BTNode.Color.RED;
                sibling.color = BTNode.Color.BLACK;
            } else {
                //Both children of sibling are black
                if ((sibling.left == null || sibling.left.color == BTNode.Color.BLACK) &&
                        (sibling.right == null || sibling.right.color == BTNode.Color.BLACK)) {
                    sibling.color = BTNode.Color.RED;
                    //Now the parent of the `toBeDeleted` is double BLACK and if it is RED we don't want to progress
                    //further as RED and double BLACK will make BLACK(Get rid of double BLACK).
                    if (parent.color == BTNode.Color.RED) {
                        parent.color = BTNode.Color.BLACK;
                        break;
                    }
                    toBeDeleted = parent;
                } else {
                    //If sibling is right child
                    if (sibling == parent.right) {
                        //Remember Null is treated as black
                        if (sibling.right == null || sibling.right.color == BTNode.Color.BLACK) {
                            //Right left case. Sibling is at right and red child is in left
                            sibling.left.color = BTNode.Color.BLACK;
                            sibling.color = BTNode.Color.RED;
                            rightRotate(sibling);
                            //After rotation the sibling is changed. The child of sibling takes its position and this
                            //turns into a Right right case.
                            sibling = parent.right;
                        }
                        //Right right case; Sibling is at right and either both children are red or red child is at right
                        sibling.color = parent.color;
                        parent.color = BTNode.Color.BLACK;
                        sibling.right.color = BTNode.Color.BLACK;
                        leftRotate(parent);
                        break;
                    } else {
                        //Remember Null is treated as black
                        if (sibling.left == null || sibling.left.color == BTNode.Color.BLACK) {
                            sibling.right.color = BTNode.Color.BLACK;
                            sibling.color = BTNode.Color.RED;
                            leftRotate(sibling);
                            //After rotation the sibling is changed. The child of sibling takes its position and this
                            //turns into a left left case.
                            sibling = parent.left;
                        }
                        sibling.color = parent.color;
                        parent.color = BTNode.Color.BLACK;
                        sibling.left.color = BTNode.Color.BLACK;
                        rightRotate(parent);
                        break;
                    }
                }
            }
        }
        root.color = BTNode.Color.BLACK;
    }
}
