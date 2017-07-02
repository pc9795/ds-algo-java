package ds.linkedList;

import ds.tree.BinarySearchTree;

public class DLinkedList {

	public NNode<Integer> head;

	public NNode<Integer> partition(NNode<Integer> l, NNode<Integer> h) {
		// System.out.println("l:" + l.data + "h:" + h.data);
		NNode<Integer> i = l.prev;
		int x = h.data;
		for (NNode<Integer> j = l; j != h; j = j.next) {
			if (j.data <= x) {
				// j.show();
				i = i == null ? l : i.next;
				int temp = i.data;
				i.data = j.data;
				j.data = temp;
				// System.out.println("loop:" + this);
			}
		}
		i = i == null ? l : i.next;
		int temp = i.data;
		i.data = h.data;
		h.data = temp;
		// System.out.println("end:" + this);
		return i;
	}

	public void quickSort(NNode<Integer> l, NNode<Integer> h) {
		if (h != null && l != h && l != h.next) {
			NNode<Integer> temp = partition(l, h);
			// System.out.println("temp:" + temp.data);
			// System.out.println("end:" + this);
			// System.out.println(
			// "tried:" + ((l == null) ? "null" : l.data) + "," + ((temp.prev ==
			// null ? "null" : temp.prev.data)));
			// System.out.println(
			// "tried:" + ((temp.next == null) ? "null" : temp.next.data) + ","
			// + ((h == null ? "null" : h.data)));
			quickSort(l, temp.prev);
			quickSort(temp.next, h);
		}
	}

	public NNode<Integer> lastNode() {
		if (head == null)
			return null;
		NNode<Integer> temp;
		for (temp = head; temp.next != null; temp = temp.next)
			;
		return temp;
	}

	public void bstToDLL(BinarySearchTree tree) {
		if (tree.root == null) {
			return;
		}
		if (tree.root.left != null) {
			BinarySearchTree left = new BinarySearchTree();
			left.root = tree.root.left;
			bstToDLL(left);
		}
		addToEnd(tree.root.data);
		if (tree.root.right != null) {
			BinarySearchTree right = new BinarySearchTree();
			right.root = tree.root.right;
			bstToDLL(right);
		}
	}

	public void reverse() {
		if (head == null)
			return;
		for (NNode<Integer> temp = head, prev = null; temp != null; head = temp.next == null ? temp
				: head, temp.prev = temp.next, temp.next = prev, prev = temp, temp = temp.prev)
			;
	}

	public int length() {
		int count = 0;
		for (NNode<Integer> temp = head; temp != null; temp = temp.next, count++)
			;
		return count;
	}

	public void delete(int pos) {
		if (head == null) {
			return;
		}
		if (pos > length()) {
			return;
		}
		NNode<Integer> temp = head;
		for (int i = 1; i <= length(); i++, temp = temp.next) {
			if (i == pos) {
				if (temp.prev != null) {
					if (temp.next != null) {
						temp.next.prev = temp.prev;
						temp.prev.next = temp.next;
					} else {
						temp.prev.next = null;
					}
				} else if (temp.next != null) {
					head = temp.next;
				} else {
					head = null;
				}
			}
		}

	}

	public void addAfter(int value, int data) {
		if (head == null) {
			return;
		}
		NNode<Integer> temp;
		boolean found = false;
		for (temp = head; temp != null; temp = temp.next) {
			if (temp.data == value) {
				found = true;
				break;
			}
		}
		if (!found) {
			return;
		}
		NNode<Integer> newNode = new NNode<>(data);
		newNode.prev = temp;
		if (temp.next != null) {
			newNode.next = temp.next;
			temp.next.prev = newNode;
		}
		temp.next = newNode;
	}

	public void addBefore(int value, int data) {
		if (head == null) {
			return;
		}
		NNode<Integer> temp;
		boolean found = false;
		for (temp = head; temp != null; temp = temp.next) {
			if (temp.data == value) {
				found = true;
				break;
			}
		}
		if (!found) {
			return;
		}
		NNode<Integer> newNode = new NNode<>(data);
		newNode.next = temp;
		if (temp.prev != null) {
			newNode.prev = temp.prev;
			temp.prev.next = newNode;
		} else {
			head = newNode;
		}
		temp.prev = newNode;
	}

	public void addToBegining(int data) {
		if (head == null) {
			head = new NNode<>(data);
			return;
		}
		NNode<Integer> newNode = new NNode<>(data);
		head.prev = newNode;
		newNode.next = head;
		head = newNode;

	}

	@Override
	public String toString() {
		String result = "";
		for (NNode<Integer> temp = head; temp != null; temp = temp.next) {
			result += temp.data + "->";
		}
		return result;

	}

	public void addToEnd(int data) {
		if (head == null) {
			head = new NNode<>(data);
			return;
		}
		NNode<Integer> temp;
		for (temp = head; temp.next != null; temp = temp.next)
			;
		NNode<Integer> newNode = new NNode<>(data);
		temp.next = newNode;
		newNode.prev = temp;
		newNode.next = null;
	}
}
