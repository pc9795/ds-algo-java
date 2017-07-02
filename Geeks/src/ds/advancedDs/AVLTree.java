package ds.advancedDs;

public class AVLTree {

	Node root;

	public class Node {
		Node left;
		Node right;
		int data;
		int height;

		public Node(int data) {
			this.data = data;
			this.height = 1;
		}
	}

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		tree.root = tree.insert(tree.root, 9);
		System.out.println("-->finished");
		System.out.println();
		tree.root = tree.insert(tree.root, 5);
		System.out.println("-->finished");
		System.out.println();
		tree.root = tree.insert(tree.root, 10);
		System.out.println("-->finished");
		System.out.println();
		tree.root = tree.insert(tree.root, 0);
		System.out.println("-->finished");
		System.out.println();
		tree.root = tree.insert(tree.root, 6);
		System.out.println("-->finished");
		System.out.println();
		tree.root = tree.insert(tree.root, 11);
		System.out.println("-->finished");
		System.out.println();
		tree.root = tree.insert(tree.root, -1);
		System.out.println("-->finished");
		System.out.println();
		tree.root = tree.insert(tree.root, 1);
		System.out.println("-->finished");
		System.out.println();
		tree.root = tree.insert(tree.root, 2);
		System.out.println("-->finished");
		System.out.println();
		tree.root = tree.delete(tree.root, 10);
		tree.levelorder(tree.root);

	}

	public Node minValue(Node node) {
		Node temp = node;
		for (; temp.left != null;) {
			temp = temp.left;
		}
		return temp;
	}

	public Node delete(Node root, int data) {
		if (root == null) {
			return root;
		} else if (data < root.data) {
			root.left = delete(root.left, data);
		} else if (data > root.data) {
			root.right = delete(root.right, data);
		} else {
			if (root.left == null || root.right == null) {
				Node temp = null;
				if (temp == root.left) {
					temp = root.right;
				} else {
					temp = root.left;
				}
				if (temp == null) {
					temp = root;
					root = null;
				} else {
					root = temp;
				}
			} else {
				Node temp = minValue(root.right);
				root.data = temp.data;
				root.right = delete(root.right, root.data);
			}

		}
		if (root == null) {
			return root;
		}
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		int balance = getBalance(root);
		// left-left
		if (balance > 1 && getBalance(root.left) >= 0) {
			return rightRotate(root);
		}
		// left-right
		if (balance > 1 && getBalance(root.left) < 0) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}
		// right-right
		if (balance < -1 && getBalance(root.right) <= 0) {
			return leftRotate(root);
		}
		// right-left
		if (balance < -1 && getBalance(root.right) > 0) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}
		return root;
	}

	public Node insert(Node root, int data) {
		System.out.println("Using " + ((root == null) ? "empty" : root.data + "<" + root.height + ">"));
		System.out.println("for data:" + data);
		if (root == null) {
			System.out.println("condition1");
			return new Node(data);
		} else if (data < root.data) {
			System.out.println("condition2");
			root.left = insert(root.left, data);
		} else if (data > root.data) {
			System.out.println("condition3");
			root.right = insert(root.right, data);
		} else {
			return root;
		}
		root.height = Math.max(height(root.left), height(root.right)) + 1;
		int balance = getBalance(root);
		System.out.println("for " + root.data + " Balance:" + balance);
		// left left
		if (balance > 1 && data < root.left.data) {
			System.out.println(">>left-left for" + root.data);
			return rightRotate(root);
		}
		// right right
		if (balance < -1 && data > root.right.data) {
			System.out.println(">>right-right for" + root.data);
			return leftRotate(root);
		}
		// left right
		if (balance > 1 && data > root.left.data) {
			System.out.println(">>left-right for" + root.data);
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}
		// right left
		if (balance < -1 && data < root.right.data) {
			System.out.println(">>right-left for" + root.data);
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}
		return root;
	}

	public int height(Node node) {
		if (node == null) {
			return 0;
		} else {
			return node.height;
		}
	}

	public void printLevel(Node root, int level) {
		if (root == null) {
			return;
		}
		if (level == 1) {
			System.out.print(root.data + " ");
		} else {
			printLevel(root.left, level - 1);
			printLevel(root.right, level - 1);
		}
	}

	public void levelorder(Node root) {
		int h = height(root);
		for (int i = 1; i <= h; i++) {
			printLevel(root, i);
			System.out.println();
		}
	}

	public Node rightRotate(Node Y) {
		Node X = Y.left;
		Node T2 = X.right;
		Y.left = T2;
		X.right = Y;
		Y.height = Math.max(height(Y.left), height(Y.right)) + 1;
		X.height = Math.max(height(X.left), height(X.right)) + 1;
		return X;
	}

	public Node leftRotate(Node X) {
		Node Y = X.right;
		Node T2 = Y.left;
		X.right = T2;
		Y.left = X;
		// I was stuck for 1:30 hours due to different ordering.
		// Y was placed before X
		X.height = Math.max(height(X.left), height(X.right)) + 1;
		Y.height = Math.max(height(Y.left), height(Y.right)) + 1;
		return Y;
	}

	public int getBalance(Node node) {
		System.out.println("Inside getBalance() for " + node.data);
		System.out.println("left:" + (node.left == null ? 0 : node.left.data));
		System.out.println("right:" + (node.right == null ? 0 : node.right.data));
		System.out.println("left height" + (node.left == null ? 0 : node.left.height));
		System.out.println("right height" + (node.right == null ? 0 : node.right.height));
		System.out.println("End of getBalance()");
		return height(node.left) - height(node.right);
	}
}
