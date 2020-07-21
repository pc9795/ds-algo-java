package gfg.ds.advanced;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 26-06-2020 00:09
 * While reading the code just remember the constraint that except root all other nodes will have a minimum of `degree-1`
 * nodes and a maximum of `2*degree-1` nodes.
 **/
public class BTree {
    public BNode root;
    private int degree;

    public BTree(int degree) {
        this.degree = degree;
    }

    /**
     * todo time complexity; I think it is O(h*degree) where h is height of the tree.
     */
    public boolean search(int data) {
        BNode curr = root;
        while (curr != null) {
            int i;
            for (i = 0; i < curr.count; i++) {
                if (data == curr.values[i]) {
                    return true;
                } else if (data < curr.values[i]) {
                    break;
                }
            }
            curr = curr.children[i];
        }
        return false;
    }

    /**
     * t=O(n); n=no of nodes in the tree.
     */
    public List<Integer> traversal() {
        List<Integer> traversal = new ArrayList<>();
        traversalUtil(root, traversal);
        return traversal;
    }

    private void traversalUtil(BNode root, List<Integer> traversal) {
        if (root == null) {
            return;
        }
        int i;
        for (i = 0; i < root.count; i++) {
            traversalUtil(root.children[i], traversal);
            traversal.add(root.values[i]);
        }
        traversalUtil(root.children[i], traversal);
    }

    public BTree insert(int... data) {
        for (Integer val : data) {
            insert(val);
        }
        return this;
    }

    /**
     * todo time complexity
     */
    public BTree insert(int data) {
        if (isEmpty()) {
            root = new BNode(degree, true);
            root.values[root.count++] = data;
            return this;
        }
        BNode curr = root;
        while (true) {
            if (curr.isFull()) {
                curr = split(curr);
            }
            if (curr.leaf) {
                int i;
                for (i = 0; i < curr.count; i++) {
                    if (curr.values[i] > data) {
                        break;
                    }
                }
                System.arraycopy(curr.values, i, curr.values, i + 1, curr.count - i);
                curr.values[i] = data;
                curr.count++;
                break;
            }
            int i;
            for (i = 0; i < curr.count; i++) {
                if (curr.values[i] > data) {
                    break;
                }
            }
            curr = curr.children[i];
        }
        return this;
    }

    /**
     * Returns the parent pointer. BTree grows from down to up.
     * This method will split the node into two and increase the count of values in parent by 1.
     */
    private BNode split(BNode node) {
        if (!node.isFull()) {
            throw new RuntimeException("Can't split an non-full node");
        }
        int mid = node.values[degree - 1];
        /* <degree-1><1><degree-1> The first k-1 will stay with the node; The middle value will go to the parent;
         * The last half will go the sibling/split-half
         */
        BNode sibling = new BNode(degree, node.leaf);
        System.arraycopy(node.values, degree, sibling.values, 0, degree - 1);
        //We actually don't need this line but it will help in debugging as the values which don't belong are replaced by
        //zero. This behavior is expected in test cases so if remove this also fix there.
        Arrays.fill(node.values, degree - 1, 2 * degree - 1, 0);
        sibling.count = degree - 1;
        node.count = degree - 1; //Updating the pointer of node to reflect new number of values.
        if (!node.leaf) {
            //Copying children to sibling
            System.arraycopy(node.children, degree, sibling.children, 0, degree);
            //We actually don't need this line but it will help in debugging as the values which don't belong are replaced by
            //zero. This behavior is expected in test cases so if remove this also fix there.
            Arrays.fill(node.children, degree, 2 * degree, null);
        }

        //No parent - root
        if (node.parent == null) {
            BNode parent = new BNode(degree, false); //The parent nodes will never be leaf nodes.
            parent.values[0] = mid;
            parent.children[0] = node;
            parent.children[1] = sibling;
            parent.count = 1;
            node.parent = parent;
            sibling.parent = parent;
            root = parent;
            return parent;
        }

        BNode parent = node.parent;
        //Finding which child is this node of parent
        int i;
        for (i = 0; i < parent.count; i++) {
            if (node.values[0] < parent.values[i]) {
                break;
            }
        }
        //Shifting data
        System.arraycopy(parent.values, i, parent.values, i + 1, parent.count - i);
        //Shifting children. Children will always be 1 greater than the no of current values -> (pointer + 1)
        System.arraycopy(parent.children, i + 1, parent.children, i + 1 + 1, parent.count - i);
        parent.values[i] = mid;
        parent.count++;
        parent.children[i + 1] = sibling;
        sibling.parent = parent;
        return parent;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * todo time complexity
     */
    public BTree delete(int data) {
        if (isEmpty()) {
            throw new RuntimeException("Tree is empty");
        }
        if (!deleteUtil(root, data)) {
            throw new RuntimeException("Data not found");
        }
        if (root.count == 0) {
            if (root.leaf) {
                root = null;
            } else {
                root = root.children[0];
            }
        }
        return this;
    }

    private boolean deleteUtil(BNode root, int data) {
        if (root == null) {
            return false;
        }
        int i;
        for (i = 0; i < root.count; i++) {
            if (root.values[i] >= data) {
                break;
            }
        }
        BNode child = root.children[i];
        // Data not found in the current node.
        // i == root.count; the data is greater than all the values present in this node.
        // root.values[i]! = data; the data is less than the value present at i. We can found it in the children located
        // at i.
        if (i == root.count || root.values[i] != data) {
            int prevCount = root.count;
            if (prevCount < degree) {
                fill(root, i);

                //After fill either borrowing should have happened that doesn't affect the count or merging should
                //have happened which will reduce the count by 1.
                assert root.count == prevCount || root.count == prevCount - 1;
            }
            BNode prevChild = root.children[i - 1];
            //In the case where the child is the last child and merging as happened we have to use the previous child
            //as the last child should be merged in it.
            return deleteUtil(i == prevCount && root.count == prevCount - 1 ? child : prevChild, data);
        }
        if (root.leaf) {
            System.arraycopy(root.values, i + 1, root.values, i, root.count - i - 1);
            //We actually don't need this line but it will help in debugging as the values which don't belong are replaced by
            //zero. This behavior is expected in test cases so if remove this also fix there.
            root.values[root.count--] = 0;
            return true;
        }
        BNode nextChild = root.children[i + 1];
        //The node is an internal node.
        if (child.count >= degree) {
            int pred = predecessor(root, i);
            root.values[i] = pred;
            return deleteUtil(child, pred);
        } else if (nextChild.count >= degree) {
            int succ = successor(root, i);
            root.values[i] = succ;
            return deleteUtil(nextChild, succ);
        } else {
            //If both of the child doesn't have at-least "degree" values then they should have "degree-1" as it is a
            //constraint for a B-tree node of minimum no of nodes.
            assert child.count == degree - 1;
            assert nextChild.count == degree - 1;

            //After merge the count of the values and children are decreased by 1 because of merging of two children.
            merge(root, i);
            return deleteUtil(child, data);
        }
    }

    private int predecessor(BNode root, int index) {
        BNode child = root.children[index];
        while (!child.leaf) {
            //Last child
            child = child.children[child.count];
        }
        //Last value
        return child.values[child.count - 1];
    }

    private int successor(BNode root, int index) {
        BNode child = root.children[index + 1];
        while (!child.leaf) {
            //First child
            child = child.children[0];
        }
        //First value
        return child.values[0];
    }


    /**
     * Merge the child present at i+1 into the child present at i. It will decrease the count of parent values by 1.
     */
    private void merge(BNode root, int i) {
        //Get the value from parent
        BNode child = root.children[i];
        child.values[child.count++] = root.values[i];

        //Get the value from sibling
        BNode nextChild = root.children[i + 1];
        System.arraycopy(child.values, child.count, nextChild.values, 0, nextChild.count);
        child.count += nextChild.count;

        //Shifting values in root node. A hole is created as nextChild is merged into child.
        System.arraycopy(root.values, i + 1, root.values, i, root.count - i - 1);
        if (!root.leaf) {
            System.arraycopy(root.children, i + 1, root.children, i, root.count - i); //Children are 1 plus the values.
        }
        //We actually don't need this line but it will help in debugging as the values which don't belong are replaced by
        //zero. This behavior is expected in test cases so if remove this also fix there.
        root.values[root.count - 1] = 0;
        root.children[root.count--] = null;
    }

    /**
     * This method will try to fill the child at i with a value from either predecessor or successor. If not possible it
     * will merge with either of them.
     */
    private void fill(BNode root, int i) {
        //We try to fill a child only if it has minimum no of nodes
        assert root.children[i].count == degree - 1;

        if (i != 0 && root.children[i - 1].count >= degree) {
            borrowFromPrev(root, i);
            return;
        }
        if (i != root.count && root.children[i + 1].count >= degree) {
            borrowFromNext(root, i);
            return;
        }
        if (i != root.count) {
            //Checks for the minimum no of nodes constraint
            assert root.children[i].count == degree - 1;
            assert root.children[i + 1].count == degree - 1;

            merge(root, i);
        } else {
            //Checks for the minimum no of nodes constraint
            assert root.children[i - 1].count == degree - 1;
            assert root.children[i].count == degree - 1;

            merge(root, i - 1);
        }

    }

    private void borrowFromPrev(BNode root, int i) {
        //When we talk about keys we go for range [0, root.count-1].
        //When we talk about children we go for range [0, root.count]. Children are 1 greater than the values present in
        //a node.
        assert i >= 0 && i <= root.count;

        BNode sibling = root.children[i - 1];
        BNode child = root.children[i];

        int lastValueOfSibling = sibling.values[sibling.count - 1];
        BNode lastChildOfSibling = sibling.children[sibling.count];
        //We actually don't need this line but it will help in debugging as the values which don't belong are replaced by
        //zero. This behavior is expected in test cases so if remove this also fix there.
        sibling.values[sibling.count - 1] = 0;
        sibling.children[sibling.count] = null;

        //Shift values in child
        System.arraycopy(child.values, 0, child.values, 1, child.count - 1);
        System.arraycopy(child.children, 0, child.children, 1, child.count);

        child.values[0] = root.values[i];
        root.values[i] = lastValueOfSibling;
        child.children[0] = lastChildOfSibling;

        child.count++;
        sibling.count--;
    }

    private void borrowFromNext(BNode root, int i) {
        //When we talk about keys we go for range [0, root.count-1].
        //When we talk about children we go for range [0, root.count]. Children are 1 greater than the values present in
        //a node.
        assert i >= 0 && i <= root.count;

        BNode sibling = root.children[i + 1];
        BNode child = root.children[i];

        //Shifting data in sibling
        int firstValueOfSibling = sibling.values[0];
        BNode firstChildOfSibling = sibling.children[0];
        System.arraycopy(sibling.values, 1, sibling.values, 0, child.count - 1 - 1);
        if (!sibling.leaf) {
            System.arraycopy(sibling.children, 1, sibling.children, 0, child.count - 1);
        }
        //We actually don't need this line but it will help in debugging as the values which don't belong are replaced by
        //zero. This behavior is expected in test cases so if remove this also fix there.
        sibling.values[sibling.count - 1] = 0;
        sibling.children[sibling.count] = null;

        child.values[child.count] = root.values[i];
        root.values[i] = firstValueOfSibling;
        child.children[child.count + 1] = firstChildOfSibling;

        child.count++;
        sibling.count--;
    }

    public static class BNode {
        public int degree;
        public int[] values;
        public BNode[] children;
        public boolean leaf;
        public int count; //Pointer to track the data in current node;
        public BNode parent;

        public BNode(int degree, boolean leaf) {
            this.degree = degree;
            this.values = new int[2 * degree - 1];
            this.children = new BNode[2 * degree];
            this.leaf = leaf;
        }

        public boolean isFull() {
            return count == 2 * degree - 1;
        }
    }
}
