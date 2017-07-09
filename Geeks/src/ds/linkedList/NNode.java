package ds.linkedList;

public class NNode<T> {

	public T data;
	public NNode<T> next;
	public NNode<T> prev;

	public NNode(T data) {
		this.data = data;
	}

	public NNode() {

	}

	public void show() {
		System.out.println("Data:" + this.data);
	}

	@Override
	public String toString() {
		return data + "";
	}
}
