package ds.queue;

import ds.stack.StackInt;

public class QueueUsingStack {

	StackInt stack1;
	StackInt stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackInt(size);
		stack2 = new StackInt(size);
	}

	public static void main(String[] args) {
		QueueUsingStack obj = new QueueUsingStack(5);
		obj.dequeue2R();
		obj.enqueue2(1);
		obj.enqueue2(2);
		obj.enqueue2(3);
		obj.enqueue2(4);
		obj.enqueue2(5);
		obj.dequeue2R();
		obj.dequeue2R();
		obj.dequeue2R();
		obj.dequeue2R();
		obj.dequeue2R();
		obj.dequeue2R();
		System.out.println(obj.stack1);

	}

	public void enqueue1(int data) {
		// System.out.println("for data:" + data);
		if (stack1.isFull()) {
			System.out.println("QueueOverflow!");
			return;
		}
		if (stack1.isEmpty()) {
			stack1.push(data);
			return;
		}
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
		stack1.push(data);
		while (!stack2.isEmpty()) {
			stack1.push(stack2.pop());
		}
	}

	public void dequeue1() {
		// System.out.println("in dequeue");
		if (stack1.isEmpty()) {
			System.out.println("QueueUnderflow!");
			return;
		}
		System.out.println(stack1.pop());
	}

	public void enqueue2(int data) {

		if (stack1.isFull()) {
			System.out.println("QueueOverflow!");
		} else {
			stack1.push(data);
		}

	}

	public void dequeue2() {

		if (stack1.isEmpty() && stack2.isEmpty()) {
			System.out.println("QueueUnderflow!");
		} else if (!stack2.isEmpty()) {
			System.out.println(stack2.pop());
		} else {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
			System.out.println(stack2.pop());
		}
	}

	public void dequeue2R() {

		if (stack1.isEmpty()) {
			System.out.println("QueueUnderflow!");
			return;
		}
		int value = stack1.pop();
		if (stack1.isEmpty()) {
			System.out.println(value);
			return;
		}
		dequeue2R();
		stack1.push(value);

	}

}
