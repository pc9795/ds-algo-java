package ds.tree;

public class TreeWithRandomNode {

	TNode2 root;

	public static void main(String[] args) {
		TreeWithRandomNode tree = new TreeWithRandomNode();
		tree.root = new TNode2(10);
		TNode2 n2 = new TNode2(6);
		TNode2 n3 = new TNode2(12);
		TNode2 n4 = new TNode2(5);
		TNode2 n5 = new TNode2(8);
		TNode2 n6 = new TNode2(11);
		TNode2 n7 = new TNode2(13);
		TNode2 n8 = new TNode2(7);
		TNode2 n9 = new TNode2(9);
		tree.root.left = n2;
		tree.root.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.left = n6;
		n3.right = n7;
		n5.left = n8;
		n5.right = n9;
		tree.root.random = n2;
		n2.random = n8;
		n3.random = n5;
		n4.random = n9;
		n5.random = tree.root;
		n6.random = n9;
		n9.random = n8;
		tree.levelorder(tree.root);
		System.out.println("-----");
		TNode2 clone = tree.copyLeftRightNode(tree.root);
		// tree.levelorder(tree.root);
		tree.copyRandomNode(tree.root, clone);
		tree.restoreTreeLeftNode(tree.root, clone);
		tree.levelorder(clone);
	}

	public void restoreTreeLeftNode(TNode2 tree, TNode2 clone) {

		if (tree == null) {
			return;
		}
		if (clone.left != null) {
			TNode2 cloneLeft = clone.left.left;
			tree.left = tree.left.left;
			clone.left = cloneLeft;
		} else {
			tree.left = null;
		}
		restoreTreeLeftNode(tree.left, clone.left);
		restoreTreeLeftNode(tree.right, clone.right);
	}

	public void copyRandomNode(TNode2 tree, TNode2 clone) {
		if (tree == null) {
			return;
		}
		if (tree.random != null) {
			clone.random = tree.random.left;
		}
		if (tree.left != null && clone.left != null) {
			copyRandomNode(tree.left.left, clone.left.left);
		}
		copyRandomNode(tree.right, clone.right);
	}

	public TNode2 copyLeftRightNode(TNode2 root) {
		if (root == null) {
			return null;
		}
		TNode2 left = root.left;
		root.left = new TNode2(root.data);
		root.left.left = left;
		if (left != null) {
			left.left = copyLeftRightNode(left);
		}
		root.left.right = copyLeftRightNode(root.right);
		return root.left;

	}

	public int height(TNode2 root) {
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

	public void printLevel(TNode2 parent, TNode2 root, int level) {
		// System.out.println("Level:" + level + "Data:" + root.data);
		if (root == null) {
			return;
		}
		if (level == 1) {
			if (parent == null) {
				System.out.print(root + "<null>" + " ");
			} else {
				System.out.print(root + "<" + parent + ">" + " ");
			}
		} else {
			printLevel(root, root.left, level - 1);
			printLevel(root, root.right, level - 1);
		}
	}

	public void levelorderParent(TNode2 root) {
		int h = height(root);
		for (int i = 1; i <= h; i++) {
			printLevel(null, root, i);
			System.out.println();
		}
	}

	public void printLevel(TNode2 root, int level) {
		// System.out.println("Level:" + level + "Data:" + root.data);
		if (root == null) {
			return;
		}
		if (level == 1) {
			System.out.print(root + " ");
		} else

		{
			printLevel(root.left, level - 1);
			printLevel(root.right, level - 1);
		}

	}

	public void levelorder(TNode2 root) {
		int h = height(root);
		for (int i = 1; i <= h; i++) {
			printLevel(root, i);
			System.out.println();
		}
	}

}
