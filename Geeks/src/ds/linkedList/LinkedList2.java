package ds.linkedList;

public class LinkedList2<T extends Comparable<T>> {

	Node<T> head;

	public void rotate(int n) {

		int length = this.length();
		if (n == length) {
			return;
		}
		if (n > length) {
			n = n % length;
		}
		int i = 1;
		Node<T> prev = null;
		for (Node<T> temp = head; temp != null; temp = temp.next, i++) {
			if (i == length - n + 1) {
				if (prev != null) {
					prev.next = null;
				}
				// System.out.println(head);
				// System.out.println(temp);
				Node<T> temp2 = null;
				for (temp2 = temp; temp2.next != null; temp2 = temp2.next)
					;
				temp2.next = head;
				head = temp;
				break;
			}
			prev = temp;
		}
	}

	public int length() {
		int count = 0;
		for (Node<T> temp = head; temp != null; temp = temp.next) {
			count++;
		}
		return count;
	}

	// m+n
	public static Node<Integer> addNumbersRepresentedByList(Node<Integer> list1, Node<Integer> list2) {
		if (list1 == null && list2 == null) {
			return null;
		}
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		Node<Integer> main = null;
		int carry = 0;
		int sum = 0;
		int value = 0;
		Node<Integer> result = null;
		for (; list1 != null && list2 != null; list1 = list1.next, list2 = list2.next) {
			sum = list1.data + list2.data;
			value = (sum + carry) % 10;
			carry = (sum + carry) / 10;
			if (result == null) {
				result = new Node<Integer>(value);
				main = result;
			} else {
				result.next = new Node<Integer>(value);
				result = result.next;
			}
		}
		if (list1 == null) {
			for (; list2 != null; list2 = list2.next) {
				sum = list2.data;
				value = (sum + carry) % 10;
				carry = (sum + carry) / 10;
				result.next = new Node<Integer>(value);
				result = result.next;
			}
		} else if (list2 == null) {
			for (; list1 != null; list1 = list1.next) {
				sum = list1.data;
				value = (sum + carry) % 10;
				carry = (sum + carry) / 10;
				result.next = new Node<Integer>(value);
				result = result.next;
			}
		}
		if (carry != 0) {
			result.next = new Node<Integer>(carry);
			result = result.next;
		}
		System.out.println(main);
		return main;
	}

	public boolean detectLoop() {
		Node<T> fast = head;
		Node<T> slow = head;
		for (; fast != null;) {
			// System.out.println("fast:"+fast.data);
			// System.out.println("slow:"+slow.data);
			fast = fast.next;
			slow = slow.next;
			if (fast != null) {
				fast = fast.next;
			}
			if (fast.equals(slow)) {
				return true;
			}
		}
		return false;
	}

	public void detectAndRemoveLoop() {
		Node<T> fast = head;
		Node<T> slow = head;
		for (; fast != null;) {
			// System.out.println("fast:" + fast.data);
			// System.out.println("slow:"+slow.data);
			fast = fast.next;
			slow = slow.next;
			if (fast != null) {
				fast = fast.next;
			}
			if (fast.equals(slow)) {
				removeLoop2(slow, head);
				break;
			}
		}
	}

	public void detectAndRemoveLoop2() {
		Node<T> fast = head;
		Node<T> slow = head;
		boolean cycle = false;
		for (; fast != null;) {
			// System.out.println("fast:" + fast.data);
			// System.out.println("slow:"+slow.data);
			fast = fast.next;
			slow = slow.next;
			if (fast != null) {
				fast = fast.next;
			}
			if (fast.equals(slow)) {
				cycle = true;
				break;
			}
		}
		if (cycle) {
			slow = head;
			for (; slow != fast; slow = slow.next, fast = fast.next)
				;
			for (; slow.next != fast; slow = slow.next)
				;
			slow.next = null;
		}
	}

	// move from head one, cycle from the found node complete
	public void removeLoop(Node<T> slow, Node<T> head) {
		Node<T> first = head;
		Node<T> second = null;
		for (;;) {
			second = slow;
			for (; second.next != slow && second.next != first; second = second.next)
				;
			if (second.next == first) {
				break;
			}
			first = first.next;
		}
		second.next = null;
	}

	public void removeLoop2(Node<T> slow, Node<T> head) {
		Node<T> first = null, second = null;
		int k = 1;
		for (Node<T> temp = slow; temp.next != slow; temp = temp.next) {
			k++;
		}
		first = head;
		second = head;
		for (int i = 1; i <= k; i++) {
			second = second.next;
		}
		for (; second != first;) {
			first = first.next;
			second = second.next;
		}
		for (; second.next != first; second = second.next)
			;
		second.next = null;
	}

	public Node<T> reverseInGroupsR(Node<T> head, int group) {
		if (head == null) {
			return null;
		}
		Node<T> first = null;
		Node<T> second = null;
		int i = 0;
		for (Node<T> temp = head; temp != null && i < group; i++) {
			second = temp.next;
			temp.next = first;
			first = temp;
			temp = second;
		}
		if (second != null) {
			head.next = reverseInGroupsR(second, group);
		}
		return first;
	}

	public void reverseInGroups(int group) {
		if (head == null) {
			return;
		}
		int i = 1;
		Node<T> first = null;
		Node<T> second = null;
		Node<T> front = null;
		for (Node<T> temp = head; temp != null; i++) {
			second = temp.next;
			temp.next = first;
			first = temp;
			temp = second;
			// System.out.println("front:" + front + "first:" + first + "
			// second:" + second + " temp:" + temp);

			if (i % group == 0) {
				if (front == null) {
					front = first;
				} else {
					Node<T> temp2 = front;
					for (; temp2.next != null; temp2 = temp2.next) {
					}
					temp2.next = first;
				}
				second = null;
				first = null;
				// System.out.println("front:" + front);

			}
		}
		if (front == null) {
			front = first;
		} else {
			Node<T> temp2 = front;
			for (; temp2.next != null; temp2 = temp2.next) {
			}
			temp2.next = first;
		}
		head = front;
	}

	public Node<T> mergeSort(Node<T> head) {
		if (head == null || head.next == null) {
			return head;
		}
		// System.out.println("head:" + head);
		Node<T> part1 = new Node<T>();
		Node<T> part2 = new Node<T>();
		breakIntoTwo(head, part1, part2);
		part1 = part1.next;
		part2 = part2.next;
		// System.out.println(part1);
		// System.out.println(part2);
		part1 = mergeSort(part1);
		part2 = mergeSort(part2);
		// System.out.println("going to sort:" + part1 + ",and" + part2);
		Node<T> temp = sortedMerge(part1, part2);
		// System.out.println("After sorting:");
		// System.out.println("sorted:" + temp);
		// System.out.println("part1:" + part1);
		// System.out.println("part2:" + part2);
		return temp;
	}

	public void breakIntoTwo(Node<T> head, Node<T> part1, Node<T> part2) {
		if (head == null) {
			return;
		}
		if (head.next == null) {
			part1 = head;
			return;
		}
		Node<T> fast = head.next;
		Node<T> slow = head;
		for (; fast != null; fast = fast.next) {
			// System.out.println("fast:"+fast);
			// System.out.println("slow:"+slow);
			fast = fast.next;
			if (fast != null) {
				slow = slow.next;
			} else {
				break;
			}
		}
		part1.next = head;
		part2.next = slow.next;
		slow.next = null;
	}

	@Override
	public String toString() {
		if (null == head) {
			return "";
		}
		String list = "";
		Node<T> temp;
		for (temp = head; null != temp; temp = temp.next) {
			list += String.valueOf(temp.data) + "->";
		}
		return list;
	}

	public Node<T> sortedMerge(Node<T> list1, Node<T> list2) {
		if (list1 == null && list2 == null) {
			return null;
		} else if (list1 == null) {
			return list2;
		} else if (list2 == null) {
			return list1;
		} else {
			Node<T> result = null, value = null;
			for (; list1 != null && list2 != null;) {
				// System.out.println("result:" + result);
				// System.out.println("list1:" + list1);
				// System.out.println("list2:" + list2);
				// System.out.println("value:" + value);
				if (list1.data.compareTo(list2.data) < 0) {
					if (result == null) {
						value = list1;
						result = list1;
						list1 = list1.next;
						result.next = null;
					} else {
						result.next = list1;
						result = result.next;
						list1 = list1.next;
						result.next = null;
					}
				} else {
					if (result == null) {
						value = list2;
						result = list2;
						list2 = list2.next;
						result.next = null;
					} else {
						result.next = list2;
						result = result.next;
						list2 = list2.next;
						result.next = null;
					}
				}
			}
			if (list1 == null) {
				result.next = list2;
			} else {
				result.next = list1;
			}
			return value;
		}
	}

	public void addData(T data) {
		if (null == head) {
			head = new Node<T>(data);
		} else {
			Node<T> temp;
			for (temp = head; null != temp.next; temp = temp.next) {
			}
			temp.next = new Node<T>(data);
		}
	}

}
