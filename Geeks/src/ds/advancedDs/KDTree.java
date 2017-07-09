package ds.advancedDs;

import java.util.Arrays;

public class KDTree {

	public static final int dimension = 2;
	Node root;

	public class Node {
		int point[];
		Node left;
		Node right;

		public Node(int arr[]) {
			point = new int[dimension];
			for (int i = 0; i < dimension; i++) {
				point[i] = arr[i];
			}
		}

		public String toString() {
			return Arrays.toString(point);
		}
	}

	public static void main(String[] args) {
		KDTree tree = new KDTree();
		// tree.root = tree.insert(new int[] { 3, 6 }, tree.root, 0);
		// tree.root = tree.insert(new int[] { 17, 15 }, tree.root, 0);
		// tree.root = tree.insert(new int[] { 13, 15 }, tree.root, 0);
		// tree.root = tree.insert(new int[] { 6, 12 }, tree.root, 0);
		// tree.root = tree.insert(new int[] { 9, 1 }, tree.root, 0);
		// tree.root = tree.insert(new int[] { 2, 7 }, tree.root, 0);
		// tree.root = tree.insert(new int[] { 10, 19 }, tree.root, 0);
		// tree.levelorder(tree.root);
		// System.out.println(tree.search(new int[] { 10, 20 }, tree.root, 0));
		// System.out.println(tree.findMin(tree.root, 0, 0));
		// System.out.println(tree.findMin(tree.root, 1, 0));
		tree.root = tree.insert(new int[] { 30, 40 }, tree.root, 0);
		tree.root = tree.insert(new int[] { 5, 25 }, tree.root, 0);
		tree.root = tree.insert(new int[] { 70, 70 }, tree.root, 0);
		tree.root = tree.insert(new int[] { 10, 12 }, tree.root, 0);
		tree.root = tree.insert(new int[] { 50, 30 }, tree.root, 0);
		tree.root = tree.insert(new int[] { 35, 45 }, tree.root, 0);
		tree.levelorder(tree.root);
		tree.root = tree.delete(tree.root, new int[] { 30, 40 }, 0);
		tree.levelorder(tree.root);
	}

	public Node delete(Node root, int point[], int depth) {
		if (root == null) {
			return null;
		}
		int cd = depth % dimension;
		if (arePointsSame(point, root.point)) {
			if (root.right != null) {
				Node min = findMin(root.right, cd, 0);
				copyNode(root, min);
				root.right = delete(root.right, min.point, depth + 1);
			} else if (root.left != null) {
				Node min = findMin(root.left, cd, 0);
				copyNode(root, min);
				root.right = delete(root.left, min.point, depth + 1);
			} else {
				return null;
			}
			return root;
		}
		if (point[cd] < root.point[cd]) {
			root.left = delete(root.left, point, depth + 1);
		}
		root.right = delete(root.right, point, depth + 1);
		return root;
	}

	public Node findMinNode(Node x, Node y, Node z, int dim) {
		Node res = x;
		if (y != null && y.point[dim] < res.point[dim]) {
			res = y;
		}
		if (z != null && z.point[dim] < res.point[dim]) {
			res = z;
		}
		return res;
	}

	public Node findMin(Node root, int dim, int depth) {
		if (root == null) {
			return null;
		}
		int axis = depth % dimension;
		if (axis == dim) {
			if (root.left == null) {
				return root;
			}
			return findMin(root.left, dim, depth + 1);
		}
		return findMinNode(root, findMin(root.left, dim, depth + 1), findMin(root.right, dim, depth + 1), dim);
	}

	public Node search(int[] point, Node root, int depth) {
		if (root == null) {
			return null;
		}
		if (arePointsSame(root.point, point)) {
			return root;
		}
		int axis = depth % dimension;
		if (point[axis] < root.point[axis]) {
			return search(point, root.left, depth + 1);
		}
		return search(point, root.right, depth + 1);
	}

	public Node insert(int[] point, Node root, int depth) {
		if (root == null) {
			return new Node(point);
		}
		int axis = depth % dimension;
		if (point[axis] < root.point[axis]) {
			root.left = insert(point, root.left, depth + 1);
		} else {
			root.right = insert(point, root.right, depth + 1);
		}
		return root;
	}

	public boolean arePointsSame(int point1[], int point2[]) {
		for (int i = 0; i < point1.length; i++) {
			if (point1[i] != point2[i]) {
				return false;
			}
		}
		return true;
	}

	public void copyNode(Node x, Node y) {
		for (int i = 0; i < dimension; i++) {
			x.point[i] = y.point[i];
		}
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
			String prevS = (prev == null) ? "null" : Arrays.toString(prev.point) + "";
			System.out.print(Arrays.toString(root.point) + "<" + prevS + "> ");
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
		System.out.println("______");
	}

}
