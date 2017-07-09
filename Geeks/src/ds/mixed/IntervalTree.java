package ds.mixed;

public class IntervalTree {

	Node root;

	public class Interval {
		int low;
		int high;

		public Interval(int low, int high) {
			this.low = low;
			this.high = high;
		}

		public String toString() {
			return "{" + low + "," + high + "}";
		}
	}

	public class Node {
		Node left;
		Node right;
		int max;
		Interval interval;

		public Node(Interval interval) {
			this.interval = interval;
			this.max = interval.high;
		}

		@Override
		public String toString() {
			return "Node [max=" + max + ", interval=" + interval + "]";
		}

	}

	public static void main(String[] args) {
		IntervalTree tree = new IntervalTree();
		tree.root = tree.insert(tree.root, tree.new Interval(15, 20));
		tree.root = tree.insert(tree.root, tree.new Interval(10, 30));
		tree.root = tree.insert(tree.root, tree.new Interval(17, 19));
		tree.root = tree.insert(tree.root, tree.new Interval(5, 20));
		tree.root = tree.insert(tree.root, tree.new Interval(12, 15));
		tree.root = tree.insert(tree.root, tree.new Interval(30, 40));
		tree.levelorder(tree.root);
		System.out.println(tree.intervalSearch(tree.root, tree.new Interval(6, 7)));
	}

	public Interval intervalSearch(Node root, Interval i) {
		if (root == null) {
			return null;
		}
		if (doOverlap(root.interval, i)) {
			return root.interval;
		}
		if (root.left != null && root.left.max >= i.low) {
			return intervalSearch(root.left, i);
		} else {
			return intervalSearch(root.right, i);
		}
	}

	public boolean doOverlap(Interval i1, Interval i2) {
		if (i1.low <= i2.high && i2.low <= i1.high) {
			return true;
		}
		return false;
	}

	public Node insert(Node root, Interval i) {
		if (root == null) {
			return new Node(i);
		}
		if (i.low < root.interval.low) {
			root.left = insert(root.left, i);
		} else {
			root.right = insert(root.right, i);
		}
		if (root.max < i.high) {
			root.max = i.high;
		}
		return root;
	}

	public int height(Node root) {
		if (root == null) {
			return 0;
		}
		int lheight = height(root.left);
		int rheight = height(root.right);
		if (lheight > rheight) {
			return lheight + 1;
		} else {
			return rheight + 1;
		}
	}

	public void printLevel(Node prev, Node root, int level) {
		// System.out.println("Level:" + level + "Data:" + root.data);
		if (root == null) {
			return;
		}
		if (level == 1) {
			String prevS = (prev == null) ? "null" : prev.toString();
			System.out.print(root + "<" + prevS + "> ");
		} else {
			printLevel(root, root.left, level - 1);
			printLevel(root, root.right, level - 1);
		}
	}

	public void levelorder(Node root) {
		int h = height(root);
		for (int i = 1; i <= h; i++) {
			printLevel(null, root, i);
			System.out.println();
		}
	}
}
