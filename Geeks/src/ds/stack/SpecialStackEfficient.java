package ds.stack;

public class SpecialStackEfficient {

	StackInt mainStack;
	StackInt minStack;

	public static void main(String[] args) {

		SpecialStackEfficient stack = new SpecialStackEfficient(5);
		stack.push(18);
		stack.push(19);
		stack.push(29);
		stack.push(15);
		stack.push(16);
		System.out.println(stack);
		for (; !stack.isEmpty();) {
			System.out.println("min:" + stack.getMin());
			System.out.println("poppedValue:" + stack.pop());
		}
	}

	SpecialStackEfficient(int size) {
		mainStack = new StackInt(size);
		minStack = new StackInt(size);
	}

	public int peek() {
		return mainStack.peek();
	}

	public boolean isEmpty() {
		return mainStack.isEmpty();
	}

	public int pop() {
		if (mainStack.peek() == minStack.peek()) {
			minStack.pop();
		}
		return mainStack.pop();
	}

	public int getMin() {
		return minStack.peek();
	}

	public void push(int x) {

		// System.out.println(minStack.isEmpty());
		mainStack.push(x);
		if (minStack.isEmpty()) {
			minStack.push(x);
			return;
		}
		if (x < minStack.peek()) {
			minStack.push(x);
		}
	}

	public String toString() {
		String result = "MainStack->" + mainStack.toString();
		result += System.lineSeparator() + "MinStack->" + minStack.toString();
		return result;

	}
}
