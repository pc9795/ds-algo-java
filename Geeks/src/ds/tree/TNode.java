package ds.tree;

public class TNode<T> {

	public TNode<T> left;
	public TNode<T> right;
	public T data;

	public TNode() {
	}

	public TNode(T data) {
		this.data = data;
	}

	public void show() {
		System.out.println("Data:" + data);
	}

	public String toString() {
		return "" + data;
	}
}
