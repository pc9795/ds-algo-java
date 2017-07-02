package ds.queue;

import java.util.LinkedList;

public class Queue {

	int front;
	int rear;
	int arr[];

	public class PetrolPump {
		int next;
		int capacity;

		public PetrolPump(int capacity, int next) {
			this.next = next;
			this.capacity = capacity;
		}
	}

	public static void main(String[] args) {
		Queue queue = new Queue(0);
		queue.generateBinary(5);
	}

	public void generateBinary(int n) {
		java.util.Queue<String> queue = new LinkedList<String>();
		String value = "1";
		queue.add(value);
		for (; n-- > 0;) {
			value = queue.poll();
			System.out.println(value);
			queue.add(value + "0");
			queue.add(value + "1");
		}
	}

	// 2n->n
	public int circularTour(PetrolPump arr[]) {
		int start = 0;
		int end = 1;
		int currPetrol = arr[start].capacity - arr[start].next;
		for (; end != start || currPetrol < 0;) {
			for (; currPetrol < 0 && start != end;) {
				currPetrol -= arr[start].capacity - arr[start].next;
				start = (start + 1) % arr.length;
				if (start == 0) {
					return -1;
				}
			}
			currPetrol += arr[end].capacity - arr[end].next;
			end = (end + 1) % arr.length;
		}
		return start;
	}

	public String toString() {
		String result = "";
		for (int i = 0; i < arr.length; i++) {
			result += arr[i] + ":";
		}
		return result;
	}

	public boolean isEmpty() {
		return front == -1 ? true : false;
	}

	public boolean isFull() {
		return front == 0 && rear == arr.length - 1 || front == rear + 1 ? true
				: false;
	}

	public int rear() {
		return isEmpty() ? -1 : arr[rear];
	}

	public int front() {
		return isEmpty() ? -1 : arr[front];
	}

	public int dequeue() {
		if (isEmpty()) {
			return -1;
		}
		int value = arr[front];
		if (front == rear) {
			front = -1;
			rear = -1;
		} else if (front == arr.length - 1) {
			front = 0;
		} else {
			front = front + 1;
		}
		return value;
	}

	public void enqueue(int data) {
		if (isFull()) {
			System.out.println("Queue Overflow!");
			return;
		}
		if (rear == -1) {
			front = 0;
			rear = 0;
		} else if (rear == arr.length - 1) {
			rear = 0;
		} else {
			rear = rear + 1;
		}
		arr[rear] = data;
	}

	public Queue(int size) {
		front = -1;
		rear = -1;
		arr = new int[size];
	}
}
