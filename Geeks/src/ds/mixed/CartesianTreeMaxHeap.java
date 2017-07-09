package ds.mixed;

import java.util.Arrays;

public class CartesianTreeMaxHeap {

	Node root;

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
	}

	public class Node {
		Node left;
		Node right;
		int data;

		public Node(int data) {
			this.data = data;
		}
	}

	public static void main(String[] args) {
		CartesianTreeMaxHeap tree = new CartesianTreeMaxHeap();
		tree.root = tree.buildCartesianTree(new int[] { 5, 10, 40, 30, 28 });
		tree.levelorder(tree.root);
	}

	public Node buildCartesiaTreeFromArray(int root, int[] parent, int[] leftChild, int[] rightChild, int arr[]) {
		if (root == -1) {
			return null;
		}
		Node temp = new Node(arr[root]);
		temp.left = buildCartesiaTreeFromArray(leftChild[root], parent, leftChild, rightChild, arr);
		temp.right = buildCartesiaTreeFromArray(rightChild[root], parent, leftChild, rightChild, arr);
		return temp;
	}

	// n|n
	public Node buildCartesianTree(int arr[]) {
		int[] parent = new int[arr.length];
		int[] leftChild = new int[arr.length];
		int[] rightChild = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			parent[i] = -1;
			leftChild[i] = -1;
			rightChild[i] = -1;
		}
		int root = 0;
		int last = -1;
		for (int i = 1; i < arr.length; i++) {
			rightChild[i] = -1;
			last = i - 1;
			for (; arr[last] <= arr[i] && last != root; last = parent[last])
				;
			if (arr[last] <= arr[i]) {
				parent[root] = i;
				leftChild[i] = root;
				root = i;
			} else if (rightChild[last] == -1) {
				rightChild[last] = i;
				parent[i] = last;
				leftChild[i] = -1;
			} else {
				parent[rightChild[last]] = i;
				leftChild[i] = rightChild[last];
				rightChild[last] = i;
				parent[i] = last;
			}
		}
		parent[root] = -1;
		System.out.println(Arrays.toString(arr));
		System.out.println(Arrays.toString(parent));
		System.out.println(Arrays.toString(leftChild));
		System.out.println(Arrays.toString(rightChild));
		return buildCartesiaTreeFromArray(root, parent, leftChild, rightChild, arr);
	}
}
