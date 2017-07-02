package ds.stack;

import ds.queue.Queue;

public class StackUsingQueue {

	Queue queue1;
	Queue queue2;

	public static void main(String[] args) {
		StackUsingQueue stack = new StackUsingQueue(5);
		System.out.println(stack.isEmpty());
		stack.push(18);
		System.out.println(stack.isEmpty());
		stack.push(19);
		stack.push(29);
		stack.push(15);
		stack.push(16);
		System.out.println(stack);
		for (; !stack.isEmpty();) {
			System.out.println("---");
			System.out.println(stack.pop());
			System.out.println(stack);
		}

	}

	public StackUsingQueue(int size) {
		queue1 = new Queue(size);
		queue2 = new Queue(size);
	}

	public boolean isEmpty() {
		return queue1.isEmpty() && queue2.isEmpty();
	}

	public int peek() {
		Queue src;
		if (queue1.isEmpty()) {
			src = queue2;
		} else {
			src = queue1;
		}
		return src.rear();
	}

	public void push(int data) {
		Queue src;
		if (queue1.isEmpty()) {
			src = queue2;
		} else {
			src = queue1;
		}
		if (src.isFull()) {
			System.out.println("StackOverflow!");
			return;
		}
		src.enqueue(data);
	}

	public int pop() {
		if (queue1.isEmpty() && queue2.isEmpty()) {
			return -1;
		}
		Queue src, dest;
		if (queue1.isEmpty()) {
			src = queue2;
			dest = queue1;
		} else {
			src = queue1;
			dest = queue2;
		}
		System.out.println("Src:" + src);
		System.out.println("Dest:" + dest);
		for (; !src.isEmpty();) {
			// System.out.println(src);
			int value = src.dequeue();
			if (src.isEmpty()) {
				return value;
			}
			dest.enqueue(value);
		}
		return -1;
	}

	public String toString() {
		return "Queue1->" + queue1.toString() + System.lineSeparator() + "Queue2->" + queue2.toString();
	}
}
