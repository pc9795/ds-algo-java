package geeks_for_geeks.ds.heap;

import geeks_for_geeks.ds.nodes.BinomialTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 17-09-2018 18:08
 **/
public class BinomialHeap {
    List<BinomialTreeNode> values = new ArrayList<>();

    public BinomialHeap() {

    }

    public BinomialHeap(int data) {
        BinomialTreeNode btree = new BinomialTreeNode(data);
        this.values.add(btree);
    }

    public void union(BinomialHeap other) {
        System.out.println("parent:" + this);
        System.out.println("child:" + other);
        if (other.values.isEmpty()) {
            throw new RuntimeException("Other heap is empty");
        }
        if (this.values.isEmpty()) {
            this.values.addAll(other.values);
            return;
        }
        BinomialHeap merged = new BinomialHeap();
//        Merging stage according to degrees.
        int i, j;
        for (i = 0, j = 0; i < other.values.size() && j < other.values.size(); ) {
            System.out.println("Inside merging");
            if (this.values.get(i).degree() <= other.values.get(j).degree()) {
                merged.values.add(this.values.get(i));
                i++;
            } else {
                merged.values.add(other.values.get(j));
                j++;
            }
        }
        if (i != this.values.size()) {
            for (; i < this.values.size(); i++) {
                merged.values.add(this.values.get(i));
            }
        }

        if (j != other.values.size()) {
            for (; j < other.values.size(); j++) {
                merged.values.add(other.values.get(j));
            }
        }
        this.values = merged.values;
//      Combining degrees.
//      We only check for nextNext because their can only be three nodes of same degrees, one of current heap, other for merging heap
//      and one from merger of previous nodes.
        for (int k = 0; k < this.values.size(); ) {
            int next = k + 1;
            int nextNext = k + 2;
            if (next < this.values.size()) {
                if (this.values.get(k).degree() != this.values.get(next).degree()) {
                    k++;
                } else {
                    if (nextNext < this.values.size() && this.values.get(k).degree() == this.values.get(nextNext).degree()) {
                        k++;
                    } else {
                        if (this.values.get(k).data < this.values.get(next).data) {
                            this.values.get(k).children.add(this.values.get(next));
                            this.values.remove(next);
                        } else {
                            this.values.get(next).children.add(this.values.get(k));
                            this.values.remove(k);
                        }
                    }
                }
            } else {
//              This is for the case when the value of k is size-1 if no k++ then it wil stick into an infinite loop
                k++;
            }
        }
        System.out.println("parent after merging:" + this);
    }

    public BinomialHeap insert(int key) {
        BinomialHeap other = new BinomialHeap(key);
        this.union(other);
        System.out.println("---insert done---");
        return this;
    }

    /**
     * This implementation requires O(log n) time. It can be optimized to O(1) by maintaining a pointer
     * to minimum ke root.
     */
    public int getMin() {
        return -1;
    }

    public int extractMin() {
        return -1;
    }

    public void delete(int key) {
        decreaseKey(key, Integer.MIN_VALUE);
        extractMin();
    }

    public void decreaseKey(int key, int newValue) {

    }

    @Override
    public String toString() {
        return "BinomialHeap{" +
                "values=" + values +
                '}';
    }

    public static void main(String[] args) {
        BinomialHeap heap = new BinomialHeap();
        heap.insert(41).insert(28).union(new BinomialHeap().insert(15).insert(33));
    }

}
