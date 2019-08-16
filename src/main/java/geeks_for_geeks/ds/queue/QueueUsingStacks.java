package geeks_for_geeks.ds.queue;

import geeks_for_geeks.ds.queue.adt.Queue;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 07-10-2018 23:22
 **/
public class QueueUsingStacks implements Queue {
    private ArrayDeque<Integer> insertionStack, queryStack;

//    Costly dequeue is better than costly enqueue as we have to move elements only one time.
    private final boolean costlyEnqueue;

    public QueueUsingStacks(boolean costlyEnqueue) {
        insertionStack = new ArrayDeque<>();
        queryStack = new ArrayDeque<>();
        this.costlyEnqueue = costlyEnqueue;
    }

    @Override
    public Queue enqueue(int data) {
        if (costlyEnqueue) {
            costlyEnqueue(data);
        } else {
            efficientEnqueue(data);
        }
        return this;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("QueueUsingArray is empty1");
        }
        if (costlyEnqueue) {
            return efficientDeqeue();
        } else {
            return costlyDequeue();
        }
    }

    private QueueUsingStacks efficientEnqueue(int data) {
        insertionStack.push(data);
        return this;
    }

    private QueueUsingStacks costlyEnqueue(int data) {
        while (!queryStack.isEmpty()) {
            insertionStack.push(queryStack.pop());
        }
        insertionStack.push(data);
        while (!insertionStack.isEmpty()) {
            queryStack.push(insertionStack.pop());
        }
        return this;
    }

    private int costlyDequeue() {
        if (queryStack.isEmpty()){
            while (!insertionStack.isEmpty()) {
                queryStack.push(insertionStack.pop());
            }
        }
        return queryStack.pop();
    }

    private int efficientDeqeue() {
        return queryStack.pop();
    }

    @Override
    public int front() {
        if (isEmpty()) {
            throw new RuntimeException("QueueUsingArray is empty1");
        }
        if(costlyEnqueue){
            return queryStack.peek();
        }else{
            if (queryStack.isEmpty()){
                while (!insertionStack.isEmpty()) {
                    queryStack.push(insertionStack.pop());
                }
            }
            return queryStack.peek();
        }
    }

    @Override
    public int rear() {
        if (isEmpty()) {
            throw new RuntimeException("QueueUsingArray is empty1");
        }
        if(costlyEnqueue){
            while(!queryStack.isEmpty()){
                insertionStack.push(queryStack.pop());
            }
            int temp=insertionStack.peek();
            while(!insertionStack.isEmpty()){
                queryStack.push(insertionStack.pop());
            }
            return temp;
        }else{
            if(insertionStack.isEmpty()) {
                while (!queryStack.isEmpty()) {
                    insertionStack.push(queryStack.pop());
                }
            }
            return insertionStack.peek();
        }
    }


    public boolean isEmpty() {
        return insertionStack.isEmpty() && queryStack.isEmpty();
    }
}
