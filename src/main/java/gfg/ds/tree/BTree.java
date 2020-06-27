package gfg.ds.tree;

import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 26-06-2020 00:09
 **/
public class BTree {
    public BNode root;
    private int degree;

    public BTree(int degree) {
        this.degree = degree;
    }

    public boolean search(int data) {
        return false;
    }

    public List<Integer> traversal() {
        return null;
    }

    public BTree insert(int data) {
        if (isEmpty()) {
            root = new BNode(degree, true);
            root.insert(data);
            return this;
        }
        BNode curr = root;
        while (true) {
            if (curr.isFull()) {
                curr = split(curr);
            }
            if (curr.leaf) {
                curr.insert(data);
                break;
            }
            int i;
            for (i = 0; i < curr.pointer; i++) {
                if (curr.values[i] > data) {
                    break;
                }
            }
            curr = curr.children[i];
        }
        return this;
    }

    private BNode split(BNode node) {
        if (!node.isFull()) {
            throw new RuntimeException("Can't split an non-full node");
        }
        /* <degree-1><1><degree-1> The first k-1 will stay with the node; The middle value will go to the parent;
         * The last half will go the sibling/split-half
         */
        BNode sibling = new BNode(degree, node.leaf);
        for (int i = degree + 1; i <= 2 * degree - 1; i++) {
            sibling.values[++sibling.pointer] = node.values[i];
        }

        int mid = node.values[degree];
        //No parent - root
        if (node.parent == null) {
            BNode parent = new BNode(degree, false); //The parent nodes will never be leaf nodes.
            parent.values[++parent.pointer] = mid;
            parent.children[parent.pointer] = node;
            parent.children[parent.pointer + 1] = sibling;
            node.parent = parent;
            sibling.parent = parent;
            return parent;
        }

        BNode parent = node.parent;
        //Finding which child is this node of parent
        int i;
        for (i = 0; i < parent.pointer; i++) {
            if (node.values[0] > parent.values[i]) {
                break;
            }
        }
        //Shifting data
        for (int j = parent.pointer; j > i; j--) {
            parent.values[j + 1] = parent.values[j];
        }
        //Shifting children. Children will always be 1 greater than the no of current values -> (pointer + 1)
        for (int j = parent.pointer + 1; j > i; j--) {
            parent.children[j + 1] = parent.children[j];
        }
        parent.values[i + 1] = mid;
        sibling.parent = parent;
        parent.children[i + 1] = sibling;
        return parent;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public BTree delete(int data) {
        return this;
    }

    public static class BNode {
        public int degree;
        public int[] values;
        public BNode[] children;
        public boolean leaf;
        public int pointer; //Pointer to track the data in current node;
        public BNode parent;

        public BNode(int degree, boolean leaf) {
            this.degree = degree;
            this.values = new int[2 * degree - 1];
            this.children = new BNode[2 * degree];
            this.leaf = leaf;
            this.pointer = -1;
        }

        public boolean isFull() {
            return pointer == 2 * degree - 1;
        }

        public void insert(int data) {
            if (!leaf) {
                throw new RuntimeException("Can't add to a non-leaf node");
            }
            if (isFull()) {
                throw new RuntimeException("Can't add data to filled node");
            }
            values[++pointer] = data;
        }
    }
}
