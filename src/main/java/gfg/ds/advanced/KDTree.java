package gfg.ds.advanced;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 23-06-2020 19:35
 **/
public class KDTree {
    public KDNode root;
    public int degree;

    public KDTree(int degree) {
        this.degree = degree;
    }

    /**
     * t=O(log n)
     */
    public boolean search(KDNode node) {
        assert node.degree == degree;

        int depth = 0;
        for (KDNode curr = root; curr != null; depth++) {
            if (curr.equals(node)) {
                return true;
            }
            curr = node.get(depth % degree) < curr.get(depth % degree) ? curr.left : curr.right;
        }
        return false;
    }

    /**
     * t=O(log n)
     */
    public KDTree insert(KDNode node) {
        assert node.degree == degree;

        if (isEmpty()) {
            root = node.copy();
            return this;
        }
        KDNode curr = root;
        KDNode prev = null;
        int depth = 0;
        for (; curr != null; depth++) {
            prev = curr;
            curr = node.get(depth % degree) < curr.get(depth % degree) ? curr.left : curr.right;
        }
        // Prev is empty only if tree is empty but we are checking that before hand.
        assert prev != null;
        if (node.get((depth - 1) % degree) < prev.get((depth - 1) % degree)) {
            prev.left = node.copy();
        } else {
            prev.right = node.copy();
        }
        return this;
    }

    public boolean isEmpty() {
        return root == null;
    }

    /**
     * todo time complexity
     */
    public KDNode getMin(int dimension) {
        return getMinUtil(root, 0, dimension);
    }

    private KDNode getMinUtil(KDNode root, int depth, int dimension) {
        if (root == null) {
            return null;
        }
        if (dimension == depth % degree) {
            return min(root, getMinUtil(root.left, depth + 1, dimension), dimension);
        }
        return min(root,
                min(getMinUtil(root.left, depth + 1, dimension),
                        getMinUtil(root.right, depth + 1, dimension), dimension),
                dimension);
    }

    private KDNode min(KDNode first, KDNode second, int dimension) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }
        return first.get(dimension) < second.get(dimension) ? first : second;
    }

    /**
     * todo time complexity
     */
    public KDTree delete(KDNode node) {
        assert node.degree == degree;
        assert !isEmpty() : "Empty tree";

        KDNode curr = root;
        KDNode prev = null;
        int depth = 0;
        for (; curr != null; depth++) {
            if (curr.equals(node)) {
                break;
            }
            prev = curr;
            curr = node.get(depth % degree) < curr.get(depth % degree) ? curr.left : curr.right;
        }
        if (curr == null) {
            throw new RuntimeException("Element not found");
        }

        if (curr.right == null && curr.left == null) {
            if (prev == null) {
                root = null;
            } else {
                if (prev.left == curr) {
                    prev.left = null;
                } else {
                    prev.right = null;
                }
            }
        } else {
            KDNode min = getMinUtil(curr.right != null ? curr.right : curr.left, 0, depth % degree);
            delete(min);
            min.left = curr.left;
            min.right = curr.right;
            if (prev == null) {
                root = min;
            } else {
                /* The case where we have both right and left subtrees or only right subtree we search for the minimum
                 * and replace it with the to-be-deleted node.
                 * The case where we have only left subtree we make it a right subtree. It could be better visualized
                 * when trying to draw this on paper and see what is happening.
                 */
                prev.right = min;
            }
        }
        return this;
    }

    /**
     * Created By: Prashant Chaubey
     * Created On: 25-06-2020 23:16
     **/
    public static class KDNode {
        public int degree;
        private int data[];
        public KDNode left, right;

        public KDNode(int degree) {
            this.degree = degree;
            this.data = new int[degree];
        }

        public int get(int dimension) {
            assert dimension < degree;

            return data[dimension];
        }

        public static KDNode of(int degree, int... values) {
            assert values.length == degree;

            KDNode node = new KDNode(degree);
            System.arraycopy(values, 0, node.data, 0, degree);
            return node;
        }

        public KDNode copy() {
            KDNode copy = new KDNode(degree);
            System.arraycopy(data, 0, copy.data, 0, degree);
            return copy;
        }

        @Override
        public String toString() {
            return "KDNode{" +
                    "degree=" + degree +
                    ", data=" + Arrays.toString(data) +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            KDNode node = (KDNode) o;

            if (degree != node.degree) return false;
            return Arrays.equals(data, node.data);
        }

        @Override
        public int hashCode() {
            int result = degree;
            result = 31 * result + Arrays.hashCode(data);
            return result;
        }
    }
}
