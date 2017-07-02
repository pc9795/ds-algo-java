package ds.linkedList;

public class CircularLinkedList {

	Node<Integer> last;

	public void insertIntoSorted(int data) {
		if (last == null) {
			last = new Node<Integer>(data);
			last.next = last;
		} else {
			Node<Integer> temp = last.next;
			boolean inserted = false;
			do {
				if (temp.next.data > data) {
					Node<Integer> newNode = new Node<Integer>(data);
					newNode.next = temp.next;
					temp.next = newNode;
					inserted = true;
					break;
				}
				temp = temp.next;
			} while (temp != last.next);
			if (!inserted) {
				this.addDataLast(data);
			}
		}
	}

	public void bisect() {
		if (last == null) {
			return;
		}
		Node<Integer> fast = last.next.next;
		Node<Integer> slow = last.next;
		for (; fast != last.next;) {
			fast = fast.next;
			if (fast != last.next) {
				fast = fast.next;
				slow = slow.next;
			}
		}
		Node<Integer> first = last.next;
		Node<Integer> second = slow.next;
		slow.next = first;
		last.next = second;
		CircularLinkedList obj = new CircularLinkedList();
		obj.last = slow;
		System.out.println(obj);
		obj.last = last;
		System.out.println(obj);
	}

	public void addAtPosition(int data, int pos) {
		if (pos > length()) {
			return;
		}
		Node<Integer> prev = last;
		for (int i = 1; i <= length(); i++) {
			if (i == pos) {
				Node<Integer> newNode = new Node<Integer>(data);
				newNode.next = prev.next;
				prev.next = newNode;
				break;
			}
			prev = prev.next;
		}

	}

	public void addDataBegining(int data) {
		if (last == null) {
			last = new Node<Integer>(data);
			last.next = last;
		} else {
			Node<Integer> newNode = new Node<>(data);
			newNode.next = last.next;
			last.next = newNode;
		}
	}

	public int length() {
		if (last == null) {
			return 0;
		} else {
			int count = 0;
			Node<Integer> temp = last.next;
			do {
				count++;
				temp = temp.next;
			} while (temp != last.next);
			return count;
		}
	}

	public void addDataLast(int data) {
		if (last == null) {
			last = new Node<Integer>(data);
			last.next = last;
		} else {
			Node<Integer> newNode = new Node<>(data);
			newNode.next = last.next;
			last.next = newNode;
			last = newNode;
		}
	}

	public String toString() {
		if (last == null) {
			return "";
		} else {
			String result = "";
			Node<Integer> temp = last.next;
			do {
				result += temp.data + "->";
				temp = temp.next;
			} while (temp != last.next);
			return result;
		}
	}
}
