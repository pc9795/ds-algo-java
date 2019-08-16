package geeks_for_geeks.ds.heap.adt;

/**
 * Created By: Prashant Chaubey
 * Created On: 10-02-2019 20:08
 * Purpose: TODO:
 **/
public interface Heap {

    int getMin();

    int extractMin();

    void decreaseKey(int index, int newVal);

    Heap insert(int data);

    void delete(int index);
}
