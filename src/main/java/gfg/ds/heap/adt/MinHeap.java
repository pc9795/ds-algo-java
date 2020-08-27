package gfg.ds.heap.adt;

public interface MinHeap {

  int getMin();

  int extractMin();

  void decreaseKey(int index, int newVal);

  MinHeap insert(int data);

  void delete(int index);
}
