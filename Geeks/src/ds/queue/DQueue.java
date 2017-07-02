package ds.queue;

public class DQueue {

	int front;
	int rear;
	int arr[];

	public DQueue(int size) {
		front = -1;
		rear = -1;
		arr = new int[size];
	}

	public String toString() {
		if (isEmpty()) {
			return ":";
		}
		String result = "";
		for (int i = front; i <= rear; i++) {
			result += arr[i] + ":";
		}
		return result;
	}

	public static void main(String[] args) {
		int arr[] = { 12, 1, 78, 90, 57, 89, 56 };
		DQueue queue = new DQueue(arr.length);
		queue.slidingWindowMaximum(arr, 3);
	}

	//2n->n
	// if getFront() is used then some shorter element also come after the
	// biggest front element which will cause problem once it is removed after
	// window separation
	public void slidingWindowMaximum(int arr[], int windowSize) {
		int i;
		for (i = 0; i < windowSize; i++) {
			for (; !isEmpty() && arr[getRear()] < arr[i];) {
				deleteLast();
			}
			insertLast(i);
		}
		// System.out.println(this);
		// System.out.println("---");
		for (; i < arr.length; i++) {
			System.out.println(arr[getFront()]);
			// System.out.println("font:" + getFront());
			// System.out.println("diff:" + (i - windowSize));
			for (; !isEmpty() && getFront() <= i - windowSize;) {
				deleteFront();
			}
			// System.out.println(this);

			for (; !isEmpty() && arr[getRear()] < arr[i];) {
				deleteLast();
			}
			// System.out.println(this);
			insertLast(i);
		}
		System.out.println(arr[getFront()]);
	}

	public void insertLast(int data) {
		if (isFull()) {
			System.out.println("StackOverflow!");
			return;
		}
		if (front == -1) {
			front = 0;
			rear = 0;
		} else if (rear == arr.length - 1) {
			rear = 0;
		} else {
			rear = rear + 1;
		}
		arr[rear] = data;
	}

	public void insertFront(int data) {
		if (isFull()) {
			System.out.println("StackOverflow!");
			return;
		}
		if (front == -1) {
			front = 0;
			rear = 0;
		} else if (front == 0) {
			front = arr.length - 1;
		} else {
			front = front - 1;
		}
		arr[front] = data;
	}

	public int deleteFront() {
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

	public int deleteLast() {
		if (isEmpty()) {
			return -1;
		}
		int value = arr[rear];
		if (front == rear) {
			front = -1;
			rear = -1;
		} else if (rear == 0) {
			rear = arr.length - 1;
		} else {
			rear = rear - 1;
		}
		return value;
	}

	public int getFront() {
		return isEmpty() ? -1 : arr[front];
	}

	public int getRear() {
		return isEmpty() ? -1 : arr[rear];
	}

	public boolean isEmpty() {
		return front == -1 ? true : false;
	}

	public boolean isFull() {
		return front == 0 && rear == arr.length - 1 || front == rear + 1 ? true
				: false;
	}
}
