package gfg.ds.queue.adt;

public interface Queue {

    Queue enqueue(int data);

    int dequeue();

    int front();

    int rear();
}
