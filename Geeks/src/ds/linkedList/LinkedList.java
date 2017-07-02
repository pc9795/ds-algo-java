package ds.linkedList;

public class LinkedList<T extends Comparable<T>> {

	public Node<T> head;

	public Node<T> sortedMergeR(Node<T> list1, Node<T> list2) {
		// System.out.println(list1);
		// System.out.println(list2);
		if (list1 == null) {
			return list2;
		} else if (list2 == null) {
			return list1;
		} else {
			Node<T> result = null;
			if (list1.data.compareTo(list2.data) < 0) {
				result = list1;
				result.next = sortedMergeR(list1.next, list2);
			} else {
				result = list2;
				result.next = sortedMergeR(list1, list2.next);

			}
			// System.out.println("Returned:"+result.next);
			return result;
		}

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
						result = list1;
						list1 = list1.next;
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

	public void reverse(Node<T> part1, Node<T> part2) {
		if (part2 == null) {
			head.next = null;
			head = part1;
			return;
		}
		Node<T> temp = part2.next;
		part2.next = part1;
		reverse(part2, temp);
	}

	public void reverse() {
		Node<T> first = null, second = null;
		for (Node<T> temp = head; temp != null; temp = second) {
			second = temp.next;
			temp.next = first;
			first = temp;
		}
		head = first;
	}

	public void swap2(T first, T second) {
		if (first == second) {
			System.out.println("Swapped!");
			return;
		}
		Node<T> prev1 = null, prev2 = null, curr1 = null, curr2 = null;
		for (Node<T> temp = head; temp != null; temp = temp.next) {
			if (temp.data == first) {
				curr1 = temp;
			} else if (temp.data == second) {
				curr2 = temp;
			}
			if (curr1 == null) {
				prev1 = temp;
			}
			if (curr2 == null) {
				prev2 = temp;
			}
			if (curr1 != null && curr2 != null) {
				break;
			}
		}
		if (curr1 == null || curr2 == null) {
			System.out.println("Element not found!");
			return;
		}
		if (prev1 == null) {
			head = curr2;
		} else {
			prev1.next = curr2;
		}
		if (prev2 == null) {
			head = curr1;
		} else {
			prev2.next = curr1;
		}
		Node<T> temp = curr1.next;
		curr1.next = curr2.next;
		curr2.next = temp;
		System.out.println("Swapped!");
	}

	public void swap(T first, T second) {

		Node<T> temp1 = null, temp2 = null;
		for (Node<T> temp = head; temp != null; temp = temp.next) {
			if (temp.data == first) {
				temp1 = temp;
			} else if (temp.data == second) {
				temp2 = temp;
			}
			if (temp1 != null && temp2 != null) {
				break;
			}
		}
		if (temp1 == null || temp2 == null) {
			System.out.println("Elements not found!");
		} else {
			T temp = temp1.data;
			temp1.data = temp2.data;
			temp2.data = temp;
			System.out.println("Swapped!");
		}
	}

	public int length() {
		int count = 0;
		for (Node<T> temp = head; temp != null; temp = temp.next) {
			count++;
		}
		return count;
	}

	public int lengthR(Node<T> temp) {
		if (temp == null) {
			return 0;
		}
		return 1 + lengthR(temp.next);
	}

	public void deleteGivenPosition(int pos) {
		if (this.length() < pos) {
			return;
		}
		if (pos == 0) {
			head = head.next;
		}
		Node<T> temp = head;
		Node<T> prev = head;
		for (int i = 0; i < pos; i++) {
			prev = temp;
			temp = temp.next;
		}
		prev.next = temp.next;
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

	@Override
	public String toString() {
		String list = "";
		for (Node<T> temp = head; null != temp; temp = temp.next) {
			list += String.valueOf(temp.data) + "->";
		}
		return list;
	}

	// int count=2;
	// list.test(++count);
	// list.test(count++);
	public void test(int data) {
		System.out.println(data);
	}

}
