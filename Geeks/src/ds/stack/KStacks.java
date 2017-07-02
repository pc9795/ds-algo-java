package ds.stack;

import util.Util;

public class KStacks {

	int arr[];
	int top[];
	int next[];
	int free;

	public static void main(String[] args) {
		KStacks stack = new KStacks(10, 3);
		stack.push(15, 2);
		stack.push(45, 2);
		// stack.printStack();
		stack.push(17, 1);
		stack.push(49, 1);
		stack.push(39, 1);
		// stack.printStack();
		stack.push(11, 0);
		stack.push(9, 0);
		stack.push(7, 0);
		stack.printStack();
		System.out.println("--");
		System.out.println(stack.pop(2));
		System.out.println(stack.pop(1));
		System.out.println(stack.pop(0));
		stack.printStack();

	}

	public void printStack() {
		Util.printArray(arr, "Content");
		Util.printArray(top, "Tops");
		Util.printArray(next, "Next");
		System.out.println("free:" + free);
	}

	public KStacks(int size, int stacks) {
		if (stacks > size) {
			System.out.println("Not enough memory for stacks!");
			return;
		}
		arr = new int[size];
		next = new int[size];
		top = new int[stacks];
		free = 0;
		for (int i = 0; i < top.length; i++) {
			top[i] = -1;
		}
		for (int i = 0; i < next.length - 1; i++) {
			next[i] = i + 1;
		}
		next[next.length - 1] = -1;

	}

	public boolean isEmpty(int stack) {
		return top[stack] == -1;
	}

	public boolean isFull() {
		return free == -1;
	}

	public int peek(int stack) {
		return top[stack];
	}

	public int pop(int stack) {
		if (isEmpty(stack)) {
			return -1;
		}
		int i = top[stack];
		top[stack] = next[i];
		next[i] = free;
		free = i;
		int value = arr[i];
		arr[i] = 0;
		return value;
	}

	public void push(int data, int stack) {
		if (isFull()) {
			System.out.println("Stack Overflow!");
			return;
		}
		int i = free;
		free = next[i];
		next[i] = top[stack];
		top[stack] = i;
		arr[i] = data;
	}
}
