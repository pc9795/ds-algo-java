package ds.queue;

import util.Util;

public class KQueue {

	public int arr[];
	public int next[];
	public int front[];
	public int rear[];
	public int free;

	public static void main(String[] args) {
		KQueue queue = new KQueue(10, 3);
		queue.enqueue(15, 2);
		queue.enqueue(45, 2);
		queue.printQueue();
		System.out.println(queue.dequeue(2));
		System.out.println(queue.dequeue(2));
		queue.printQueue();
	}

	public void enqueue(int data, int queue) {
		if (isFull()) {
			System.out.println("Stack Overflow!");
			return;
		}

		int i = free;
		free = next[i];
		arr[i] = data;
		if (front[queue] == -1) {
			front[queue] = i;
		}
		if (rear[queue] != -1) {
			next[rear[queue]] = i;
		}
		next[i] = -1;
		rear[queue] = i;

	}

	public int dequeue(int queue) {
		if (isEmpty(queue)) {
			return -1;
		}
		int i = front[queue];
		front[queue] = next[i];
		if (front[queue] == -1) {
			rear[queue] = -1;
		}
		next[i] = free;
		free = i;
		int value = arr[i];
		arr[i] = 0;
		return value;
	}

	public KQueue(int size, int queues) {
		free = 0;
		arr = new int[size];
		next = new int[size];
		front = new int[queues];
		rear = new int[queues];
		for (int i = 0; i < queues; i++) {
			front[i] = -1;
			rear[i] = -1;
		}
		for (int i = 0; i < size - 1; i++) {
			next[i] = i + 1;
		}
		next[size - 1] = -1;
	}

	public void printQueue() {
		Util.printArray(arr, "Content");
		Util.printArray(next, "Next");
		Util.printArray(front, "front");
		Util.printArray(rear, "Rear");
		System.out.println("Free:" + free);
	}

	public int getFront(int queue) {
		return front[queue];
	}

	public int getRear(int queue) {
		return rear[queue];
	}

	public boolean isEmpty(int queue) {
		return front[queue] == -1;
	}

	public boolean isFull() {
		return free == -1;
	}
}
