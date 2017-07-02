package ds.stack;

public class Stack {

	int top;
	char arr[];

	public static void main(String[] args) {
		Stack stack = new Stack(5);
		stack.push('a');
		stack.push('d');
		stack.push('e');
		stack.push('b');
		stack.push('c');
		System.out.println(stack);
		stack.sort();
		System.out.println(stack);
	}

	public void sortedInsert(char value) {
		// System.out.println("sorted Insert:" + value);
		// System.out.println(this);
		if (isEmpty()) {
			// System.out.println("here");
			push(value);
		} else {
			if (peek() > value) {
				// System.out.println("there");
				char temp = pop();
				sortedInsert(value);
				push(temp);
			} else {
				push(value);
			}
		}
	}

	public void sort() {

		char value = pop();
		// System.out.println(value);
		if (!isEmpty()) {
			sort();
		}
		sortedInsert(value);
	}

	public void insertAtBotton(char x) {
		if (isEmpty()) {
			push(x);
		} else {
			char value = pop();
			insertAtBotton(x);
			push(value);
		}
	}

	public void reverse() {
		char value = pop();
		if (!isEmpty()) {
			reverse();
		}
		insertAtBotton(value);
	}

	public void checkBalancedParenthesis(String exp) {
		if (exp.equals("") || exp == null) {
			System.out.println("String is empty!");
			return;
		}
		for (int i = 0; i < exp.length(); i++) {
			// System.out.println(this);
			char c = exp.charAt(i);
			if (c == '(' || c == '[' || c == '{') {
				push(c);
			} else if (c == ')') {
				if (pop() != '(') {
					System.out.println("Not Balanced!");
					return;
				}
			} else if (c == '}') {
				if (pop() != '{') {
					System.out.println("Not Balanced!");
					return;
				}
			} else if (c == ']') {
				if (pop() != '[') {
					System.out.println("Not Balanced!");
					return;
				}
			}
		}
		if (isEmpty()) {
			System.out.println("Balanced!");
		} else {
			System.out.println("Not Balanced!");
		}
	}

	public void reverseString(String str) {
		if (str.equals("") || str == null) {
			System.out.println("Empty String!");
			return;
		}
		for (int i = 0; i < str.length(); i++) {
			push(str.charAt(i));
		}
		String result = "";
		for (char temp = pop(); temp != '0'; temp = pop()) {
			result += temp;
		}
		System.out.println("Reversed String:" + result);
	}

	public Stack(int size) {
		top = -1;
		arr = new char[size];
	}

	public String toString() {
		String result = "";
		for (int i = top; i >= 0; i--) {
			result += arr[i] + ":";
		}
		return result;
	}

	public void push(char data) {
		if (top == arr.length - 1) {
			System.out.println("Stack Overflow!");
			System.exit(1);
			return;
		}
		arr[++top] = data;
	}

	public char pop() {
		if (top == -1) {
			return '0';
		}
		return arr[top--];
	}

	public char peek() {
		if (top == -1) {
			return '0';
		}
		return arr[top];
	}

	public boolean isEmpty() {
		if (top == -1) {
			return true;
		}
		return false;
	}

	public int precedence(char op) {
		switch (op) {
		case '+':
		case '-':
			return 1;
		case '/':
		case '*':
			return 2;
		case '^':
			return 3;
		default:
			return -1;
		}
	}

	// a+b*(c^d-e)^(f+g*h)-i
	// abcd^e-fgh*+^*+i-
	public void infixToPostfix(String exp) {
		String result = "";
		for (int i = 0; i < exp.length(); i++) {
			char c = exp.charAt(i);
			// System.out.println("element:" + c);
			// System.out.println("stack:" + this);
			// System.out.println("result:" + result);
			if (Character.isLetterOrDigit(c)) {
				result = result + c;
			} else if (c == '(') {
				push(c);
			} else if (c == ')') {
				for (;;) {
					char temp = peek();
					if (temp == '(') {
						pop();
						break;
					} else if (temp == '0') {
						System.out.println("Invalid Expression");
						System.out.println("Result:" + result);
						return;
					} else {
						pop();
						result = result + temp;
					}
				}
			} else {
				for (;;) {
					char temp = peek();
					if (precedence(c) > precedence(temp) || temp == '0') {
						push(c);
						break;
					} else {
						pop();
						result = result + temp;
					}
				}
			}
		}
		char temp;
		for (temp = pop(); temp != '0'; temp = pop()) {
			result += temp;
		}
		System.out.println("Result:" + result);
		// System.out.println(this);
	}

}
