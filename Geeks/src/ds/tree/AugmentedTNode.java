package ds.tree;

public class AugmentedTNode {

	public AugmentedTNode left;
	public AugmentedTNode right;
	public int lcount;
	public int data;

	public AugmentedTNode(int data) {
		this.data = data;

	}

	public String toString() {
		return data + "<" + lcount + ">";
	}
}
