package ds.linkedList;

public class Node<T> {

	public T data;
	public Node<T> next;

	public Node(T data) {
		this.data = data;
	}

	public Node() {
	}

	@Override
	public String toString() {
		String print = "";
		for (Node<T> temp = this; temp != null; temp = temp.next) {
			print += temp.data + "->";
		}
		return print;
	}

}
