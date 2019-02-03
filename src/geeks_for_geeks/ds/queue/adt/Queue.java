package geeks_for_geeks.ds.queue.adt;

public interface Queue {

    public Queue enqueue(int data);

    public int dequeue();

    public int front();

    public int rear();
}
