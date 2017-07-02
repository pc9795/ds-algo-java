package ds.queue;

import ds.linkedList.Node;

public class QueueUsingList {
	Node<Integer> front;
	Node<Integer> rear;

	public String toString() {
		String result = ":";
		for (Node<Integer> temp = front; temp != null; temp = temp.next) {
			result += temp.data + "->";
		}
		return result;
	}

	public int peek() {
		if (front == null) {
			return -1;
		}
		return front.data;
	}

	public void enqueue(int data) {
		if (rear == null) {
			rear = new Node<Integer>(data);
			front = rear;
			return;
		}
		rear.next = new Node<Integer>(data);
		rear = rear.next;
	}

	public int dequeue() {
		if (front == null) {
			return -1;
		}
		int data;
		if (front == rear) {
			data = front.data;
			front = rear = null;
		} else {
			data = front.data;
			front = front.next;
		}
		return data;
	}
}
