package ds.stack;

public class SpecialStack {

	StackInt mainStack;
	StackInt minStack;

	public static void main(String[] args) {

		SpecialStack stack = new SpecialStack(5);
		stack.push(18);
		stack.push(19);
		stack.push(29);
		stack.push(15);
		stack.push(16);
		System.out.println(stack);
		for (; !stack.isEmpty();) {
			System.out.println(stack.getMin());
			System.out.println(stack.pop());
		}
	}

	SpecialStack(int size) {
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
		minStack.pop();
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
		int y = minStack.peek();
		if (x < y) {
			minStack.push(x);
		} else {
			minStack.push(y);
		}
	}

	public String toString() {
		String result = "MainStack->" + mainStack.toString();
		result += System.lineSeparator() + "MinStack->" + minStack.toString();
		return result;

	}
}
