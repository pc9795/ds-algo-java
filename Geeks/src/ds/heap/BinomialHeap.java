package ds.heap;

import java.util.ArrayList;
import java.util.List;

public class BinomialHeap {

	List<Node> head;

	public BinomialHeap() {
		head = new ArrayList<>();
	}

	public class Node {
		int data;
		int degree;
		Node child;
		Node sibling;
		Node parent;

		public Node(int data) {
			this.data = data;
		}

		public String toString() {
			return "" + this.data;
		}
	}

	public List<Node> removeMin(Node bTree) {
		bTree = bTree.child;
		Node curr = bTree;
		List<Node> newList = new ArrayList<>();
		while (bTree != null) {
			curr = bTree;
			bTree = bTree.sibling;
			curr.sibling = null;
			curr.parent = null;
			newList.add(curr);
		}
		int size = newList.size();
		for (int i = 0; i < size / 2; i++) {
			Node temp = newList.get(i);
			newList.set(i, newList.get(size - 1 - i));
			newList.set(size - i - 1, temp);
		}
		return newList;
	}

	public List<Node> extractMin(List<Node> head) {
		Node temp = getMin(this.head);
		List<Node> newList = new ArrayList<>();
		for (int i = 0; i < head.size(); i++) {
			if (head.get(i) == temp) {
				continue;
			}
			newList.add(head.get(i));
		}
		List<Node> minRemovedList = removeMin(temp);
		minRemovedList = union(newList, minRemovedList);
		return adjust(minRemovedList);
	}

	public List<Node> adjust(List<Node> head) {
		int i = 0;
		int size = head.size();
		for (; i < size - 1; size = head.size()) {
			// System.out.println("Inside loop");
			if (head.get(i).degree != head.get(i + 1).degree) {
				i++;
			} else {
				if (i + 2 < size
						&& head.get(i + 1).degree == head.get(i + 2).degree) {
					i++;
				} else {
					if (head.get(i).data <= head.get(i + 1).data) {
						head.get(i).degree++;
						head.get(i + 1).sibling = head.get(i).child;
						head.get(i).child = head.get(i + 1);
						head.get(i + 1).parent = head.get(i);
						head.remove(i + 1);

					} else {
						head.get(i + 1).degree++;
						head.get(i).sibling = head.get(i + 1).child;
						head.get(i + 1).child = head.get(i);
						head.get(i).parent = head.get(i + 1);
						head.remove(i);

					}
				}
			}
		}
		return head;
	}

	public List<Node> union(List<Node> head, List<Node> second) {
		List<Node> result = new ArrayList<>();
		int i = 0, j = 0;
		for (; i < head.size() && j < second.size();) {
			result.add(head.get(i).degree < second.get(j).degree ? head
					.get(i++) : second.get(j++));
		}
		if (i != head.size()) {
			for (; i != head.size(); result.add(head.get(i)), i++)
				;
		} else {
			for (; j != second.size(); result.add(second.get(j)), j++)
				;
		}
		return result;
	}

	public List<Node> insert(List<Node> head, int key) {
		Node data = new Node(key);
		List<Node> list = new ArrayList<>();
		list.add(data);
		// System.out.println("head:" + head);
		// System.out.println("inserting:" + list);
		list = union(head, list);
		// System.out.println("After union:" + list);
		return adjust(list);
	}

	public void printHeap() {
		for (int i = 0; i < head.size(); i++) {
			System.out.println("Element " + i);
			levelorder(head.get(i));
		}
	}

	public void printHeap(List<Node> head) {
		for (int i = 0; i < head.size(); i++) {
			System.out.println("Element " + i);
			levelorder(head.get(i));
		}
	}

	public void printLevel(Node root, int level) {
		if (root == null) {
			return;
		}
		if (root.sibling != null) {
			printLevel(root.sibling, level);
		}
		if (level == 1) {
			System.out.print(root.data + " ");

		} else {
			printLevel(root.child, level - 1);
		}
	}

	public void levelorder(Node root) {
		int h = root.degree + 1;
		for (int i = 1; i <= h; i++) {
			printLevel(root, i);
			System.out.println();
		}
	}

	public Node getMin(List<Node> head) {
		Node result = null;
		for (int i = 0; i < head.size(); i++) {
			if (result == null) {
				result = head.get(i);
			} else {
				if (head.get(i).data < result.data) {
					result = head.get(i);
				}
			}
		}
		return result;
	}

}
