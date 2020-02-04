package geeks_for_geeks.ds.heap.adt;

/**
 * Created By: Prashant Chaubey
 * Created On: 10-02-2019 20:08
 **/
public interface MinHeap {

    int getMin();

    int extractMin();

    void decreaseKey(int index, int newVal);

    MinHeap insert(int data);

    void delete(int index);
}
