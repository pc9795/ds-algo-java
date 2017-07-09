package ds.advancedDs;

public class SplayTree {

	Node root;

	public class Node {
		Node left;
		Node right;
		int data;

		public Node(int data) {
			this.data = data;
		}
	}

	public Node rightRotate(Node y) {
		Node x = y.left;
		y.left = x.right;
		x.right = y;
		return x;
	}

	public Node leftRotate(Node x) {
		Node y = x.right;
		x.right = y.left;
		y.left = x;
		return y;
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
			String prevS = (prev == null) ? "null" : prev.data + "";
			System.out.print(root.data + "<" + prevS + "> ");
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
		System.out.println("____");
	}

	public static void main(String[] args) {
		SplayTree tree = new SplayTree();
		Node n1 = tree.new Node(100);
		Node n2 = tree.new Node(50);
		Node n3 = tree.new Node(200);
		Node n4 = tree.new Node(40);
		Node n5 = tree.new Node(30);
		Node n6 = tree.new Node(20);

		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n4.left = n5;
		n5.left = n6;

		n1 = tree.insert(25, n1);
		tree.levelorder(n1);

		// tree.levelorder(n1);
		// n1 = tree.splay(40, n1);
		// tree.levelorder(n1);
	}

	public Node insert(int data, Node root) {
		if (root == null) {
			return new Node(data);
		}
		root = splay(data, root);
		if (root.data == data) {
			return root;
		}
		Node newRoot = new Node(data);
		if (data < root.data) {
			newRoot.right = root;
			newRoot.left = root.left;
			root.left = null;
		} else {
			newRoot.left = root;
			newRoot.right = root.right;
			root.right = null;
		}
		return newRoot;
	}

	public Node splay(int data, Node root) {
		// System.out.println("data:" + root.data);
		if (root == null || root.data == data) {
			return root;
		}
		if (data < root.data) {
			if (root.left == null) {
				return root;
			}
			if (root.left.data == data) {
				return rightRotate(root);
			}
			if (data < root.left.data) {
				root.left.left = splay(data, root.left.left);
				Node temp = rightRotate(root);
				root = rightRotate(temp);
			} else {
				root.left = splay(data, root.left.right);
				Node temp = leftRotate(root);
				root = rightRotate(temp);
			}
		} else {
			if (root.right == null) {
				return root;
			}
			if (root.right.data == data) {
				return leftRotate(root);
			}
			if (data < root.right.data) {
				root.right = splay(data, root.right.left);
				Node temp = rightRotate(root);
				root = leftRotate(temp);
			} else {
				root.left = splay(data, root.right.right);
				Node temp = leftRotate(root);
				root = leftRotate(temp);
			}
		}
		return root;
	}
}
