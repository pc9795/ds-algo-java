package ds.tree;

import java.util.Stack;

import ds.linkedList.DLinkedList;
import ds.linkedList.NNode;

public class BinarySearchTree extends Tree {
	public static NNode<Integer> head;

	public static void main(String[] args) {
		BinarySearchTree tree1 = new BinarySearchTree();
		// tree1.root = new TNode<Integer>(6);
		// tree1.root.left = new TNode<Integer>(10);
		// tree1.root.right = new TNode<Integer>(2);
		// tree1.root.left.left = new TNode<Integer>(1);
		// tree1.root.left.right = new TNode<Integer>(3);
		// tree1.root.right.left = new TNode<Integer>(7);
		// tree1.root.right.right = new TNode<Integer>(12);
		// tree1.root = new TNode<Integer>(10);
		// tree1.root.left = new TNode<Integer>(8);
		// tree1.root.right = new TNode<Integer>(20);
		// tree1.root.left.left = new TNode<Integer>(5);
		// tree1.root.left.right = new TNode<Integer>(7);
		// tree1.root.left.left.left = new TNode<Integer>(3);
		// tree1.root.right.left = new TNode<Integer>(15);
		// tree1.root.right.right = new TNode<Integer>(25);
		// tree1.levelorder(tree1.root);
		// tree1.correctBSTUtil(tree1.root);
		// System.out.println("After correction");
		// tree1.levelorder(tree1.root);
		DLinkedList dl = new DLinkedList();
		dl.addToEnd(1);
		dl.addToEnd(2);
		dl.addToEnd(3);
		dl.addToEnd(4);
		dl.addToEnd(5);
		dl.addToEnd(6);
		dl.addToEnd(7);
		System.out.println(dl);
		head = dl.head;
		System.out.println("head:" + head);
		int length = dl.length();
		NNode<Integer> root = tree1.sortedDLLtoBST(length);
		System.out.println(root);
		System.out.println(root.prev);
		System.out.println(root.next);
		System.out.println(root.prev.prev);
		System.out.println(root.prev.next);
		System.out.println(root.next.prev);
		System.out.println(root.next.next);

	}

	public NNode<Integer> sortedDLLtoBST(int length) {
		if (length <= 0) {
			return null;
		}
		NNode<Integer> left = sortedDLLtoBST(length / 2);
		NNode<Integer> root = head;
		head = head.next;
		root.prev = left;
		// BUG: I used (length/2)-1
		root.next = sortedDLLtoBST(length - (length / 2) - 1);
		return root;

	}

	// TNode<Integer> test = new TNode<Integer>(5);
	// tree1.test2(test);
	// System.out.println(test);
	public void test(TNode<Integer> test) {
		test.data = 10;
	}

	public void test2(TNode<Integer> test2) {
		test2 = new TNode<Integer>(10);
	}

	public void correctBSTUtil(TNode<Integer> root) {
		// we can also use a static variable "first";
		TNode<Integer> first = new TNode<Integer>(0);
		TNode<Integer> middle = new TNode<Integer>(0);
		TNode<Integer> last = new TNode<Integer>(0);
		TNode<Integer> prev = new TNode<Integer>(0);
		correctBST(prev, root, first, middle, last);
		System.out.println("first:" + first.left);
		System.out.println("middle:" + middle.left);
		System.out.println("last:" + last.left);
		if (last.left == null) {
			int temp = first.left.data;
			first.left.data = middle.left.data;
			middle.left.data = temp;
		} else {
			int temp = first.left.data;
			first.left.data = last.left.data;
			last.left.data = temp;
		}
	}

	public void correctBST(TNode<Integer> prev, TNode<Integer> root, TNode<Integer> first, TNode<Integer> middle,
			TNode<Integer> last) {
		if (root == null) {
			return;
		}
		correctBST(prev, root.left, first, middle, last);

		if (prev.left != null && root.data < prev.left.data) {
			if (first.left == null) {
				first.left = prev.left;
				middle.left = root;
			} else {
				last.left = root;
			}
		}
		// System.out.println("prev:" + (prev.left == null ? "null" : prev.left)
		// + " root:"
		// + (root == null ? "null" : root) + " first:" + (first.left == null ?
		// "null" : first.left) + " middle:"
		// + (middle.left == null ? "null" : middle.left) + " last:" +
		// (last.left == null ? "null" : last.left));
		prev.left = root;
		correctBST(prev, root.right, first, middle, last);
	}

	// m+n|h1+h2
	public void merge(TNode<Integer> tree1, TNode<Integer> tree2) {
		if (tree1 == null && tree2 == null) {
			return;
		}
		if (tree1 == null) {
			inOrderTraversal(tree2);
			return;
		}
		if (tree2 == null) {
			inOrderTraversal(tree1);
		}
		Stack<TNode<Integer>> stack1 = new Stack<>();
		Stack<TNode<Integer>> stack2 = new Stack<>();
		TNode<Integer> curr1 = tree1;
		TNode<Integer> curr2 = tree2;
		// System.out.println("here");
		for (; curr1 != null || curr2 != null || !stack1.isEmpty() || !stack2.isEmpty();) {
			for (; curr1 != null; curr1 = curr1.left) {
				stack1.push(curr1);
			}
			for (; curr2 != null; curr2 = curr2.left) {
				stack2.push(curr2);
			}
			// System.out.println(stack1);
			// System.out.println(stack2);
			// System.out.println("--");
			if (stack1.isEmpty()) {
				for (; !stack2.isEmpty();) {
					curr2 = stack2.pop();
					curr2.left = null;
					inOrderTraversal(curr2);
				}
				return;
			}
			if (stack2.isEmpty()) {

				for (; !stack1.isEmpty();) {
					curr1 = stack1.pop();
					curr1.left = null;
					inOrderTraversal(curr1);
				}
				return;
			}
			curr1 = stack1.pop();
			curr2 = stack2.pop();
			if (curr1.data < curr2.data) {
				System.out.println(curr1.data);
				curr1 = curr1.right;
				stack2.push(curr2);
				curr2 = null;

			} else {
				System.out.println(curr2.data);
				curr2 = curr2.right;
				stack1.push(curr1);
				curr1 = null;
			}
		}
	}

	// m+n
	public TNode<Integer> sortedArrayToBST(Integer arr[], int start, int end) {
		// when left is null this is reached.
		if (start > end) {
			return null;
		}
		if (start == end) {
			return new TNode<Integer>(arr[start]);
		}
		int mid = (start + end) / 2;
		TNode<Integer> root = new TNode<>(arr[mid]);
		root.left = sortedArrayToBST(arr, start, mid - 1);
		root.right = sortedArrayToBST(arr, mid + 1, end);
		return root;

	}

	// h
	public void kOrderStatistics(AugmentedTNode root, int order) {
		if (root == null) {
			return;
		}
		AugmentedTNode curr = root;
		for (; curr != null;) {
			if (curr.lcount + 1 == order) {
				System.out.println(order + "th Order statistics is " + curr.data);
				return;
			} else if (order <= curr.lcount) {
				curr = curr.left;
			} else {
				order = order - curr.lcount - 1;
				curr = curr.right;
			}

		}
	}

	public AugmentedTNode insertRAugmented(AugmentedTNode root, int data) {
		if (root == null) {
			return new AugmentedTNode(data);
		}
		if (root.data > data) {
			root.lcount++;
			root.left = insertRAugmented(root.left, data);
		} else {
			root.right = insertRAugmented(root.right, data);
		}
		return root;
	}

	public class AugmentedTree {
		AugmentedTNode root;

		public int height(AugmentedTNode root) {
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

		public void printLevel(AugmentedTNode root, int level) {
			// System.out.println("Level:" + level + "Data:" + root.data);
			if (root == null) {
				return;
			}
			if (level == 1) {
				System.out.print(root + " ");
			} else {
				printLevel(root.left, level - 1);
				printLevel(root.right, level - 1);
			}
		}

		public void levelorder(AugmentedTNode root) {
			int h = height(root);
			for (int i = 1; i <= h; i++) {
				printLevel(root, i);
				System.out.println();
			}
		}
	}

	public void lowestCommonAncestor(TNode<Integer> root, int n1, int n2) {
		if (root == null) {
			return;
		}
		if (n1 < root.data && n2 < root.data) {
			lowestCommonAncestor(root.left, n1, n2);
		} else if (n1 > root.data && n2 > root.data) {
			lowestCommonAncestor(root.right, n1, n2);
		} else {
			System.out.println("CommonAncestor:" + root.data);
		}
	}

	public boolean isBST(TNode<Integer> root, int min, int max) {
		if (root == null) {
			return true;
		}
		if (root.data < min || root.data > max) {
			return false;
		}
		return isBST(root.left, min, root.data - 1) && isBST(root.right, root.data + 1, max);
	}

	// using in order traversal
	public boolean isBST(TNode<Integer> root, TNode<Integer> prev) {
		boolean left = true, right = true;
		if (root == null) {
			return true;
		}
		if (root.left != null) {
			left = isBST(root.left, prev);
		}
		if (prev == null) {
			prev = root;
		} else {
			if (root.data < prev.data) {
				return false;
			}
			prev = root;
		}
		if (root.right != null) {
			right = isBST(root.right, prev);
		}
		return left && right;
	}

	public int findInorderSuccessor(TNode<Integer> root) {
		if (root == null) {
			return -1;
		}
		if (root.left != null) {
			root = root.left;
		}
		return root.data;
	}

	public int findInorderPredecessor(TNode<Integer> root) {
		if (root == null)
			return -1;
		if (root.right != null) {
			root = root.right;
		}
		return root.data;
	}

	public void findSP(int data) {
		if (root == null) {
			System.out.println("Tree is empty");
		}
		TNode<Integer> curr = root;
		int prec = -1, succ = -1;
		for (;;) {
			if (curr == null) {
				System.out.println("Value Not Found!");
				break;
			}
			if (data > curr.data) {
				prec = curr.data;
				curr = curr.right;
			} else if (data < curr.data) {
				succ = curr.data;
				curr = curr.left;
			} else {
				int value = findInorderPredecessor(curr.left);
				prec = value == -1 ? prec : value;
				value = findInorderSuccessor(curr.right);
				succ = value == -1 ? succ : value;
				break;
			}
		}
		System.out.println("prec:" + prec + ",succ:" + succ);

	}

	public int minValue(TNode<Integer> root) {
		TNode<Integer> curr = root;
		while (curr.left != null) {
			curr = curr.left;
		}
		return curr.data;

	}

	public TNode<Integer> delete(TNode<Integer> root, int data) {

		if (root == null) {
			return null;
		}
		if (data > root.data) {
			root.right = delete(root.right, data);
		} else if (data < root.data) {
			root.left = delete(root.left, data);
		} else {
			if (root.left == null) {
				return root.right;
			} else if (root.right == null) {
				return root.left;
			}
			root.data = minValue(root.right);
			root = delete(root.right, root.data);
		}
		return root;

	}

	public void delete(int data) {
		if (root == null) {
			System.out.println("Tree is empty!");
			return;
		}
		TNode<Integer> prev = null;
		TNode<Integer> curr = root;
		for (;;) {
			if (curr == null) {
				System.out.println("Value not found!");
				return;
			}
			if (curr.data > data) {
				prev = curr;
				curr = curr.left;
			} else if (curr.data < data) {
				prev = curr;
				curr = curr.right;
			} else {
				break;
			}
		}
		System.out.println("Processing...");
		if (curr.left == null && curr.right == null) {
			if (prev == null) {
				root = null;
			} else {
				if (prev.data < data) {
					prev.right = null;
				} else {
					prev.left = null;
				}
			}
		} else if (curr.left == null) {
			if (prev == null) {
				root = curr;
			} else {
				if (prev.data < data) {
					prev.right = curr.right;
				} else {
					prev.left = curr.right;
				}
			}
		} else if (curr.right == null) {
			if (prev == null) {
				root = curr;
			} else {
				if (prev.data < data) {
					prev.right = curr.left;
				} else {
					prev.left = curr.left;
				}
			}
		} else {
			TNode<Integer> minPrev = curr;
			TNode<Integer> min = curr.right;
			if (min.left == null) {
				curr.data = min.data;
				minPrev.right = min.right;
				return;
			}
			while (min.left != null) {
				minPrev = min;
				min = min.left;
			}
			curr.data = min.data;
			minPrev.left = min.right;
		}

	}

	public void search(int data) {
		if (root == null) {
			System.out.println("Not found!");
		}
		TNode<Integer> curr = root;
		for (;;) {
			if (curr == null) {
				System.out.println("Not found");
				break;
			}
			if (data == curr.data) {
				System.out.println("Found " + curr.data);
				break;
			} else if (data > curr.data) {
				curr = curr.right;
			} else {
				curr = curr.left;
			}
		}
	}

	public TNode<Integer> insertR(TNode<Integer> root, int data) {
		if (root == null) {
			return new TNode<Integer>(data);
		}
		if (root.data > data) {
			root.left = insertR(root.left, data);
		} else {
			root.right = insertR(root.right, data);
		}
		return root;
	}

	public void insert(int data) {
		if (root == null) {
			root = new TNode<>(data);
			return;
		}
		TNode<Integer> curr = root;
		for (;;) {
			if (data > curr.data) {
				if (curr.right == null) {
					curr.right = new TNode<>(data);
					break;
				}
				curr = curr.right;
			} else {
				if (curr.left == null) {
					curr.left = new TNode<>(data);
					break;
				}
				curr = curr.left;

			}
		}

	}
}
