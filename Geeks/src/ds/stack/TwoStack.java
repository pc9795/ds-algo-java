package ds.stack;

public class TwoStack {

	int top1;
	int top2;
	int arr[];

	public String toString() {
		String result = "";
		for (int i = 0; i < arr.length; i++) {
			result += arr[i] + ":";
		}
		return result;
	}

	public void push2(int data) {
		if (top2 == top1 + 1 || top2 == 0) {
			System.out.println("StackOverflow!");
		} else {
			arr[--top2] = data;
		}
	}

	public int pop2() {
		return (top2 == arr.length) ? -1 : arr[top2++];
	}

	public int peek2() {
		return (top2 == arr.length) ? -1 : arr[top2];
	}

	public boolean isEmpty2() {
		return (top2 == arr.length) ? true : false;
	}

	public void push1(int data) {
		if (top1 == top2 - 1 || top1 == arr.length) {
			System.out.println("StackOverflow!");
		} else {
			arr[++top1] = data;
		}

	}

	public int pop1() {
		return (top1 == -1) ? -1 : arr[top1--];
	}

	public int peek1() {
		return (top1 == -1) ? -1 : arr[top1];
	}

	public boolean isEmpty1() {
		return (top1 == -1) ? false : true;
	}

	public TwoStack(int size) {
		arr = new int[size];
		top1 = -1;
		top2 = arr.length;
	}
}
