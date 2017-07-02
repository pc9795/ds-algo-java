package ds.stack;

import util.Util;

public class StackInt {

	int top;
	int arr[];

	public static void main(String[] args) {
		StackInt stack = new StackInt(6);
		int price[] = { 10, 4, 5, 90, 120, 80 };
		Integer S[] = new Integer[price.length];
		stack.stockSpanProblem(price, S);
		Util.printArray(S);
	}	

	// n|n<all elements in decreasing order>
	public void stockSpanProblem(int price[], Integer S[]) {
		push(0);
		S[0] = 1;
		for (int i = 1; i < price.length; i++) {
			for (; !isEmpty() && price[peek()] <= price[i];) {
				pop();
			}
			S[i] = isEmpty() ? i + 1 : i - peek();
			push(i);
		}
	}

	// n
	public void nextGreaterElement(int arr[]) {
		if (arr.length == 0) {
			System.out.println("Empty array!");
			return;
		} else {
			push(arr[0]);
		}
		for (int i = 1; i < arr.length; i++) {
			for (; !isEmpty();) {
				if (peek() >= arr[i]) {
					break;
				} else {
					System.out.println(pop() + "--" + arr[i]);
				}
			}
			push(arr[i]);
		}
		for (; !isEmpty();) {
			System.out.println(pop() + "--" + -1);
		}
	}

	public int calculate(char operator, int op1, int op2) {
		switch (operator) {
		case '+':
			return op1 + op2;
		case '-':
			return op1 - op2;
		case '*':
			return op1 * op2;
		case '/':
			return op1 / op2;
		case '^':
			return op1 ^ op2;
		default:
			return -1;
		}
	}

	public void evaluatePostfix(String exp) {
		if (exp.equals("") || exp == null) {
			System.out.println("Expression is empty!");
			return;
		}
		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			if (Character.isDigit(c)) {
				push(Character.getNumericValue(c));
			} else {
				int op2 = pop();
				int op1 = pop();
				if (op1 == -1 || op2 == -1) {
					System.out.println("Invalid Expression!");
					return;
				}
				push(calculate(c, op1, op2));
			}
		}
		System.out.println("Result:" + pop());
	}

	public StackInt(int size) {
		top = -1;
		arr = new int[size];
	}

	public String toString() {
		String result = ":";
		for (int i = top; i >= 0; i--) {
			result += arr[i] + ":";
		}
		return result;
	}

	public void push(int data) {
		if (top == arr.length - 1) {
			System.out.println("Stack Overflow!");
			return;
		}
		arr[++top] = data;
	}

	public int pop() {
		if (top == -1) {
			return -1;
		}
		return arr[top--];
	}

	public int peek() {
		if (top == -1) {
			return -1;
		}
		return arr[top];
	}

	public boolean isFull() {
		return top == arr.length - 1 ? true : false;
	}

	public boolean isEmpty() {
		if (top == -1) {
			return true;
		}
		return false;
	}

}
