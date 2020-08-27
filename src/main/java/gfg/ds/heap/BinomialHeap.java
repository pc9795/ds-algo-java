package gfg.ds.heap;

import gfg.ds.heap.adt.MinHeap;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * This is not referenced from anywhere I have implemented this on own. SO USE WISELY.
 *
 * @noinspection WeakerAccess
 */
public class BinomialHeap implements MinHeap {
  private List<BinomialTreeNode> values = new ArrayList<>();

  public BinomialHeap() {}

  private BinomialHeap(int data) {
    BinomialTreeNode btree = new BinomialTreeNode(data);
    this.values.add(btree);
  }

  private BinomialTreeNode getChild(int child) {
    assert child >= 0 && child < values.size();

    return values.get(child);
  }

  public int size() {
    return this.values.size();
  }

  /** t=O(log(n) + log(m)); sum of length of children */
  private void union(BinomialHeap other) {
    if (other.isEmpty()) {
      return;
    }
    if (isEmpty()) {
      // add all children of other heap to this.
      this.values.addAll(other.values);
      return;
    }
    BinomialHeap merged = new BinomialHeap();
    int i, j;
    // simple merging of heaps in non-decreasing order.
    for (i = 0, j = 0; i < size() && j < other.size(); ) {
      if (getChild(i).degree() <= other.getChild(j).degree()) {
        merged.values.add(getChild(i));
        i++;
      } else {
        merged.values.add(other.getChild(j));
        j++;
      }
    }

    // adding leftovers.
    BinomialHeap leftOver = i != size() ? this : other;
    i = i != size() ? i : j;
    for (; i < leftOver.size(); i++) {
      merged.values.add(leftOver.getChild(i));
    }

    // the original heap will contain all the merged trees
    this.values = merged.values;
    int size = size();

    // combining degrees.
    // We only check for nextNext because their can only be three nodes of same degrees, one from
    // current heap, other
    // from going to be merged heap and one from the merger.(Every heap have single tree for some
    // degree)
    for (int k = 0; k < size; ) {
      int next = k + 1;
      int nextNext = k + 2;
      if (k == size - 1 || (getChild(k).degree() != getChild(next).degree())) {
        k++;
        continue;
      }
      if (nextNext < size && getChild(k).degree() == getChild(nextNext).degree()) {
        // nextNext and next are having same degrees so they will be merged
        // in next iteration so move ahead.
        k++;
        continue;
      }
      // merging
      if (getChild(k).data < getChild(next).data) {
        getChild(k).merge(getChild(next));
        values.remove(next);
      } else {
        getChild(next).merge(getChild(k));
        values.remove(k);
      }
      size--;
    }
  }

  /** t=O(log n) */
  @Override
  public BinomialHeap insert(int key) {
    BinomialHeap other = new BinomialHeap(key);
    this.union(other);
    return this;
  }

  @Override
  public void delete(int index) {
    throw new UnsupportedOperationException();
  }

  /**
   * t=O(log n) a binomial tree can be seen as bit representation of a number which is log n. It can
   * be optimized to O(1) by maintaining a pointer to minimum ke root.
   */
  @Override
  public int getMin() {
    assert isEmpty() : "Heap is empty";

    int min = Integer.MAX_VALUE;
    for (BinomialTreeNode node : values) {
      min = Math.min(min, node.data);
    }
    return min;
  }

  public boolean isEmpty() {
    return this.values.isEmpty();
  }

  /** t=O(log n) */
  public int extractMin() {
    // log n
    int minIndex = 0;
    for (int i = 0; i < size(); i++) {
      if (getChild(minIndex).data > getChild(i).data) {
        minIndex = i;
      }
    }

    BinomialTreeNode minBinomialTree = getChild(minIndex);
    // log n
    this.values.remove(minIndex);

    BinomialHeap heapFromChildOfMin = new BinomialHeap();
    BinomialTreeNode child;
    while ((child = minBinomialTree.divide()) != null) {
      BinomialHeap childHeap = new BinomialHeap();
      childHeap.values.add(child);
      heapFromChildOfMin.union(childHeap);
    }
    this.union(heapFromChildOfMin);
    return minBinomialTree.data;
  }

  @Override
  public void decreaseKey(int index, int newVal) {
    throw new UnsupportedOperationException();
  }

  @Override
  public String toString() {
    return "BinomialHeap{" + "values=" + values + '}';
  }

  public static class BinomialTreeNode {
    public int data;
    private Deque<BinomialTreeNode> children = new LinkedList<>();
    private BinomialTreeNode parent;

    public BinomialTreeNode(int data) {
      this.data = data;
    }

    public BinomialTreeNode divide() {
      if (children.size() == 0) {
        return null;
      }
      BinomialTreeNode child = children.removeFirst();
      child.parent = null;
      return child;
    }

    public BinomialTreeNode merge(BinomialTreeNode other) {
      assert degree() == other.degree() : "Only trees of same degree can be merged";
      // So that after merging no can mutate the structure of sub-tree.
      assert other.parent == null : "Only root trees can be merged";

      children.addFirst(other);
      other.parent = this;
      return this;
    }

    public int degree() {
      return children.size();
    }

    @Override
    public String toString() {
      return "BinomialTreeNode{" + "data=" + data + '}';
    }
  }
}
