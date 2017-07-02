package ds.tree;

public class TNode2 {

	public TNode2 left;
	public TNode2 right;
	public TNode2 random;
	public int data;

	public TNode2(int data) {
		this.data = data;
	}

	public String toString() {
		return data + ((random == null) ? "<null>" : "<" + random.data + ">");
	}
}
