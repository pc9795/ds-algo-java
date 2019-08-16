package geeks_for_geeks.ds.heap;

import geeks_for_geeks.ds.heap.adt.Heap;
import geeks_for_geeks.ds.nodes.BinomialTreeNode;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 17-09-2018 18:08
 **/
public class BinomialHeap implements Heap {
    List<BinomialTreeNode> values = new ArrayList<>();

    public BinomialHeap() {
    }

    public BinomialHeap(int data) {
        BinomialTreeNode btree = new BinomialTreeNode(data);
        this.values.add(btree);
    }

    private void union(BinomialHeap other) {
        if (other.values.isEmpty()) {
            return;
        }
        if (this.values.isEmpty()) {
//            Add all children of other geeks_for_geeks.heap to this.
            this.values.addAll(other.values);
            return;
        }

        BinomialHeap merged = new BinomialHeap();
        int i, j;

//        Simple merging of heaps in non-decreasing order.
        for (i = 0, j = 0; i < this.values.size() && j < other.values.size(); ) {

            if (this.values.get(i).degree() <= other.values.get(j).degree()) {
                merged.values.add(this.values.get(i));
                i++;
            } else {
                merged.values.add(other.values.get(j));
                j++;
            }
        }

//        Adding leftovers.
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

        int size = this.values.size();

//      Combining degrees.
//      We only check for nextNext because their can only be three nodes of same degrees, one of current geeks_for_geeks.heap,
//      other for merging geeks_for_geeks.heap and one from merger of previous nodes. (Every geeks_for_geeks.heap have single tree for some degree)

        for (int k = 0; k < size; ) {

            int next = k + 1;
            int nextNext = k + 2;

            if (next < this.values.size()) {

                if (this.values.get(k).degree() != this.values.get(next).degree()) {
//                    No need for combining; move ahead.
                    k++;
                } else {
                    if (nextNext < this.values.size() && this.values.get(k).degree() ==
                            this.values.get(nextNext).degree()) {
//                      next next and next are having same degrees so they will be merged
//                      in next iteration so move ahead.
                        k++;
                    } else {
                        if (this.values.get(k).data < this.values.get(next).data) {
//                        For get array list is better and for remove linked list is better
//                        It can be improved by using custom linked list.
                            this.values.get(k).children.add(this.values.get(next));
                            this.values.remove(next);
                            size--;
                        } else {
                            this.values.get(next).children.add(this.values.get(k));
                            this.values.remove(k);
                            size--;
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

    @Override
    public BinomialHeap insert(int key) {
        BinomialHeap other = new BinomialHeap(key);
        this.union(other);
        return this;
    }

    /**
     * t=O(logn) ;a binomaial tree can be seen as bit repr of a number so that repr is logn.
     * It can be optimized to O(1) by maintaining a pointer
     * to minimum ke root.
     */
    @Override
    public int getMin() {
        int min = Integer.MAX_VALUE;

        for (BinomialTreeNode node : this.values) {
            min = Math.min(min, node.data);
        }

        return min;
    }

    /**
     * t=O(logn)
     *
     * @return
     */
    public int extractMin() {
        int minIndex = 0;
//       logn
        for (int i = 0; i < this.values.size(); i++) {
            if (this.values.get(minIndex).data > this.values.get(i).data) {
                minIndex = i;
            }
        }

        BinomialTreeNode minBinomialTree = this.values.get(minIndex);

//        logn
        this.values.remove(minIndex);

        BinomialHeap heapFromChildOfMin = new BinomialHeap();

        for (BinomialTreeNode child : minBinomialTree.children) {

            BinomialHeap childHeap = new BinomialHeap();
            childHeap.values.add(child);

            heapFromChildOfMin.union(childHeap);
        }

        this.union(heapFromChildOfMin);

        return minBinomialTree.data;
    }

    @Override
    public void delete(int key) {
        throw new NotImplementedException();
    }

    @Override
    public void decreaseKey(int key, int newValue) {
        throw new NotImplementedException();
    }

    @Override
    public String toString() {
        return "BinomialHeap{" +
                "values=" + values +
                '}';
    }

}