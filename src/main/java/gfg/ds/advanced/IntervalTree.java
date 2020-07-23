package gfg.ds.advanced;

import utils.Pair;

import java.util.Optional;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-07-2020 01:41
 **/
public class IntervalTree {
    private IntervalTreeNode root;

    public IntervalTreeNode getRoot() {
        return root;
    }

    /**
     * t=O(log n)
     */
    public IntervalTree insert(Pair<Integer, Integer> interval) {
        assert !isInvalid(interval) : String.format("Interval not valid:%s", interval);

        IntervalTreeNode newNode = new IntervalTreeNode(interval, interval.value);

        if (isEmpty()) {
            root = newNode;
            return this;
        }

        IntervalTreeNode prev = null;
        IntervalTreeNode curr = root;
        for (; curr != null; ) {
            assert !curr.interval.equals(interval) : String.format("Duplicate data: %s", interval);

            curr.maxInTree = Math.max(curr.maxInTree, newNode.maxInTree);
            prev = curr;
            curr = interval.key < curr.interval.key ? curr.left : curr.right;
        }

        assert prev != null;

        if (interval.key < prev.interval.key) {
            prev.left = newNode;
        } else {
            prev.right = newNode;
        }
        return this;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private boolean isInvalid(Pair<Integer, Integer> interval) {
        return interval == null || interval.key > interval.value;
    }

    /**
     * t=O(log n)
     */
    public Optional<Pair<Integer, Integer>> overlapSearch(Pair<Integer, Integer> interval) {
        if (isEmpty()) {
            return Optional.empty();
        }

        IntervalTreeNode curr = root;
        for (; curr != null; ) {
            if (isOverlapping(curr.interval, interval)) {
                return Optional.of(curr.interval);
            }
            if (curr.left != null && curr.maxInTree >= interval.key) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }
        return Optional.empty();
    }

    private boolean isOverlapping(Pair<Integer, Integer> interval1, Pair<Integer, Integer> interval2) {
        boolean notOverlapping = interval1.value < interval2.key || interval1.key > interval2.value;

        return !notOverlapping;
    }

    public static class IntervalTreeNode {
        private Pair<Integer, Integer> interval;
        private int maxInTree;
        private IntervalTreeNode left;
        private IntervalTreeNode right;

        public IntervalTreeNode(Pair<Integer, Integer> interval, int maxInTree) {
            this.interval = interval;
            this.maxInTree = maxInTree;
        }

        public Pair<Integer, Integer> getInterval() {
            return interval;
        }

        public int getMaxInTree() {
            return maxInTree;
        }

        public IntervalTreeNode getLeft() {
            return left;
        }

        public IntervalTreeNode getRight() {
            return right;
        }
    }
}
