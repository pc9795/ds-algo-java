package gfg.ds.advanced;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 21-07-2020 01:00
 **/
public final class Applications {
    private Applications() {
        throw new IllegalStateException("Object should not be constructed");
    }

    /**
     * t=O(n); If data is partially sorted
     * =O(n*log n); Otherwise
     * s=O(n); Priority queue size is bounded by the input size
     */
    public static List<Integer> cartesianTreeSort(List<Integer> input) {
        if (input == null || input.size() == 0) {
            return new ArrayList<>();
        }

        CartesianTree cartesianTree = new CartesianTree();
        for (int inputVal : input) {
            cartesianTree.insert(inputVal);
        }

        PriorityQueue<CartesianTree.CartesianTreeNode> maxHeap = new PriorityQueue<>((o1, o2) -> o2.getData() - o1.getData());
        maxHeap.add(cartesianTree.getRoot());

        List<Integer> sorted = new ArrayList<>();
        while (!maxHeap.isEmpty()) {
            CartesianTree.CartesianTreeNode node = maxHeap.poll();
            assert node != null;

            sorted.add(node.getData());

            //If data is partially sorted these additions to the heap are constant time.
            if (node.getLeft() != null) {
                maxHeap.add(node.getLeft());
            }
            if (node.getRight() != null) {
                maxHeap.add(node.getRight());
            }
        }

        //Right now cartesian tree is using max-heap that's why we have to reverse the data. Can use Min-heap based
        //Cartesian tree implementation with a Min-heap Priority queue
        Collections.reverse(sorted);

        return sorted;
    }
}
