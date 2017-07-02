package ds.tree;

public class BinarySearchTree extends Tree {

	public boolean isBST(TNode<Integer> root, int min, int max) {
		if (root == null) {
			return true;
		}
		if (root.data < min || root.data > max) {
			return false;
		}
		return isBST(root.left, min, root.data - 1)
				&& isBST(root.right, root.data + 1, max);
	}

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
