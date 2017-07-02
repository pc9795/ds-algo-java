package ds.stack;

import ds.linkedList.DLinkedList;
import ds.linkedList.NNode;

public class StackWithMid {

	DLinkedList list;
	NNode<Integer> mid;
	int counter;

	public static void main(String[] args) {
		StackWithMid stack = new StackWithMid();
		// System.out.println(stack.isEmpty());
		// System.out.println(stack);
		stack.push(18);
		// System.out.println("Here");
		// System.out.println(stack);
		// System.out.println(stack.isEmpty());
		stack.push(19);
		// System.out.println(stack);
		stack.push(29);
		// System.out.println(stack);
		stack.push(15);
		// System.out.println(stack);
		stack.push(16);
		System.out.println(stack);
		// stack.pop();
		// System.out.println(stack);
		// stack.pop();
		// System.out.println(stack);
		stack.deleteMid();
		System.out.println(stack);
		stack.deleteMid();
		System.out.println(stack);
	}

	public StackWithMid() {
		list = new DLinkedList();
	}

	public String toString() {
		String result = "List->";

		for (NNode<Integer> temp = list.head; temp != null; temp = temp.next) {
			result += temp.data + ":";
		}
		result += System.lineSeparator() + "MiddleElement->" + (mid == null ? "No" : mid.data) + System.lineSeparator()
				+ "Counter->" + counter;
		return result;
	}

	public void push(int data) {
		if (list.head == null) {
			list.head = new NNode<>(data);
			mid = list.head;
			counter++;
		} else {
			counter++;
			list.head.prev = new NNode<>(data);
			list.head.prev.next = list.head;
			list.head = list.head.prev;
			if (counter % 2 == 0) {
				mid = mid.prev;
			}
		}
	}

	public int pop() {
		int data;
		if (counter == 0) {
			return -1;
		}
		data = list.head.data;
		if (counter == 1) {
			list.head = null;
			mid = null;
			counter--;
			return data;
		}
		if (counter % 2 == 0) {
			mid = mid.next;
		}
		list.head = list.head.next;
		counter--;
		return data;
	}

	public boolean isEmpty() {
		return list.head == null;
	}

	public int getMid() {
		return (mid == null) ? -1 : mid.data;
	}

	public boolean deleteMid() {
		if (mid == null) {
			return false;
		}
		if (counter == 1) {
			list.head = null;
			mid = null;
			counter--;
		} else {
			if (counter % 2 == 0) {
				NNode<Integer> temp = mid;
				mid = mid.next;
				temp.prev.next = temp.next;
				temp.next.prev = temp.prev;
			} else {
				NNode<Integer> temp = mid;
				mid = mid.prev;
				temp.prev.next = temp.next;
				temp.next.prev = temp.prev;
			}
		}
		counter--;
		return true;
	}

}
