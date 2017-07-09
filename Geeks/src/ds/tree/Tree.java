package ds.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Tree {

	public TNode<Integer> root;
	static int preIndex;

	public static void main(String[] args) {

		Tree tree = new Tree();
		String in = "425163";
		String pre = "124536";
		preIndex = 0;
		tree.root = tree.buildTreeFronPreAndIn(in, pre, 0, in.length() - 1);
		tree.levelorder(tree.root);
	}

	public int serarch(String in, int start, int end, int data) {
		for (int i = start; i <= end; i++) {
			if (Integer.parseInt(in.charAt(i) + "") == data) {
				return i;
			}
		}
		return -1;
	}

	public TNode<Integer> buildTreeFronPreAndIn(String in, String pre, int start, int end) {

		if (start > end) {
			return null;
		}
		TNode<Integer> tnode = new TNode<Integer>(Integer.parseInt(pre.charAt(preIndex++) + ""));
		if (start == end) {
			return tnode;
		}
		int index = serarch(in, start, end, tnode.data);
		tnode.left = buildTreeFronPreAndIn(in, pre, start, index - 1);
		tnode.right = buildTreeFronPreAndIn(in, pre, index + 1, end);
		return tnode;
	}

	// We have to reiterate left subtree to check if it contain thread or not.
	public void morisTraversal() {
		if (root == null) {
			return;
		}
		TNode<Integer> current = root;
		while (current != null) {
			if (current.left == null) {
				System.out.println(current.data);
				current = current.right;
			} else {
				TNode<Integer> pre = current.left;
				while (pre.right != null && pre.right != current) {
					pre = pre.right;
				}
				if (pre.right == null) {
					pre.right = current;
					current = current.left;
				} else {
					pre.right = null;
					System.out.println(current.data);
					current = current.right;
				}
			}
		}
	}

	public void inOrderTraversalWithStack() {
		if (root == null) {
			return;
		}
		TNode<Integer> temp = root;
		Stack<TNode<Integer>> stack = new Stack<>();
		for (;;) {
			for (; temp != null; temp = temp.left) {
				stack.push(temp);
			}
			if (stack.isEmpty()) {
				break;
			}
			temp = stack.pop();
			System.out.println(temp.data);
			temp = temp.right;
		}
	}

	// n^2->extra recursion due to height, if calculated here then O(n)
	public int diameter(TNode<Integer> root) {
		if (root == null) {
			return 0;
		}
		int heightL = height(root.left);
		int heightR = height(root.right);
		int diameterL = diameter(root.left);
		int diameterR = diameter(root.right);
		return Math.max(heightL + heightR + 1, Math.max(diameterL, diameterR));
	}

	public void levelOrderTraversal() {
		if (root == null) {
			return;
		}
		Queue<TNode<Integer>> queue = new LinkedList<>();
		queue.add(root);
		for (; !queue.isEmpty();) {
			TNode<Integer> temp = queue.poll();
			System.out.println(temp.data);
			if (temp.left != null) {
				queue.add(temp.left);
			}
			if (temp.right != null) {
				queue.add(temp.right);
			}
		}
	}

	public void inOrderTraversal(TNode<Integer> node) {
		if (node == null) {
			return;
		}
		if (node.left != null) {
			inOrderTraversal(node.left);
		}
		System.out.println(node.data + "->");
		if (node.right != null) {
			inOrderTraversal(node.right);
		}

	}

	public int height(TNode<Integer> root) {
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

	public void printLevel(TNode<Integer> prev, TNode<Integer> root, int level) {
		// System.out.println("Level:" + level + "Data:" + root.data);
		if (root == null) {
			return;
		}
		if (level == 1) {
			String prevS = (prev == null) ? "null" : prev.data + "";
			System.out.print(root.data + "<"+prevS+"> ");
		} else {
			printLevel(root, root.left, level - 1);
			printLevel(root, root.right, level - 1);
		}
	}

	public void levelorder(TNode<Integer> root) {
		int h = height(root);
		for (int i = 1; i <= h; i++) {
			printLevel(null, root, i);
			System.out.println();
		}
	}

	public void preorder(TNode<Integer> root) {
		System.out.println(root.data);
		if (root.left != null) {
			preorder(root.left);
		}
		if (root.right != null) {
			preorder(root.right);
		}
	}

	public void postorder(TNode<Integer> root) {
		if (root.left != null) {
			postorder(root.left);
		}
		if (root.right != null) {
			postorder(root.right);
		}
		System.out.println(root.data);
	}
}
