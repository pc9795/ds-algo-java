package ds.mixed;

public class TernarySearchTree {

	Node root;

	public class Node {
		Node left;
		Node equal;
		Node right;
		char data;
		boolean isLeaf;

		public Node(char data) {
			this.data = data;
		}

		@Override
		public String toString() {
			return "Node [left=" + left + ", equal=" + equal + ", right=" + right + ", data=" + data + ", isLeaf="
					+ isLeaf + "]";
		}

	}

	public static void main(String[] args) {
		TernarySearchTree tree = new TernarySearchTree();
		tree.root = tree.insert("cat".toCharArray(), 0, tree.root);
		tree.root = tree.insert("cats".toCharArray(), 0, tree.root);
		tree.root = tree.insert("up".toCharArray(), 0, tree.root);
		tree.root = tree.insert("bugs".toCharArray(), 0, tree.root);
		tree.root = tree.insert("cpu".toCharArray(), 0, tree.root);
		tree.levelorder(tree.root);
		tree.traverse(tree.root, new char[50], 0);
		System.out.println(tree.search("catz".toCharArray(), tree.root, 0));
	}

	public Node insert(char[] data, int index, Node root) {
		if (root == null) {
			root = new Node(data[index]);
		}
		if (root.data > data[index]) {
			root.left = insert(data, index, root.left);
		} else if (root.data < data[index]) {
			root.right = insert(data, index, root.right);
		} else {
			if (index + 1 < data.length) {
				root.equal = insert(data, index + 1, root.equal);
			} else {
				root.isLeaf = true;
			}
		}

		return root;
	}

	public void printCharArray(int depth, char[] arr) {
		for (int i = 0; i <= depth; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}

	public void traverse(Node root, char[] buffer, int depth) {
		if (root != null) {
			traverse(root.left, buffer, depth);
			buffer[depth] = root.data;
			if (root.isLeaf) {
				printCharArray(depth, buffer);
			}
			traverse(root.equal, buffer, depth + 1);
			traverse(root.right, buffer, depth);
		}
	}

	public boolean search(char[] data, Node root, int index) {
		if (root == null) {
			return false;
		}
		if (data[index] < root.data) {
			return search(data, root.left, index);
		}
		if (data[index] > root.data) {
			return search(data, root.right, index);
		}
		if (index + 1 == data.length) {
			return root.isLeaf;
		} else {
			return search(data, root.equal, index + 1);
		}
	}

	public int height(Node root) {
		if (root == null) {
			return 0;
		}
		int lheight = height(root.left);
		int rheight = height(root.right);
		int eheight = height(root.equal);
		if (lheight > rheight) {
			if (lheight > eheight) {
				return lheight + 1;
			} else {
				return eheight + 1;
			}
		} else {
			if (rheight > eheight) {
				return rheight + 1;
			} else {
				return eheight + 1;
			}
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
			printLevel(root, root.equal, level - 1);
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
