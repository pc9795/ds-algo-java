package geeks_for_geeks.ds.stack.adt;

/**
 * Created By: Prashant Chaubey
 * Created On: 27-01-2019 15:09
 **/
public interface Stack {

    void push(int data);

    int pop();

    boolean isEmpty();

    int peek();
}