package ds.advancedDs;

import java.util.Arrays;

public class BTree {

	public BTreeNode root;
	public int degree;

	public BTree(int degree) {

		this.degree = degree;
	}

	public String toString() {
		return root.toString();
	}

	public class BTreeNode {
		BTreeNode[] childs;
		int[] keys;
		boolean leaf;
		int currKeys;
		int degree;

		@Override
		public String toString() {
			return "BTreeNode [childs=" + Arrays.toString(childs) + ", keys=" + Arrays.toString(keys) + ", leaf=" + leaf
					+ ", currKeys=" + currKeys + ", degree=" + degree + "]";
		}

		public BTreeNode(boolean leaf, int degree) {
			this.degree = degree;
			this.leaf = leaf;
			this.keys = new int[2 * degree - 1];
			this.currKeys = 0;
			this.childs = new BTreeNode[2 * degree];
		}

		public void traverse() {
			int i;
			for (i = 0; i < currKeys; i++) {
				if (!leaf) {
					childs[i].traverse();
				}
				System.out.println(keys[i]);
			}
			if (!leaf) {
				childs[i].traverse();
			}
		}

		public void split(int index, BTreeNode oldRoot) {
			BTreeNode oldRootPart = new BTreeNode(oldRoot.leaf, degree);
			oldRootPart.currKeys = degree - 1;
			for (int i = 0; i < degree - 1; i++) {
				oldRootPart.keys[i] = oldRoot.keys[degree + i];
				oldRoot.keys[degree + i] = 0;
			}
			if (!oldRoot.leaf) {
				for (int i = 0; i < degree; i++) {
					oldRootPart.childs[i] = oldRoot.childs[i + degree];
					oldRoot.childs[i + degree] = null;
				}
			}
			oldRoot.currKeys = degree - 1;
			for (int j = currKeys; j >= index + 1; j--) {
				childs[j + 1] = childs[j];
			}
			childs[index + 1] = oldRootPart;
			for (int j = currKeys - 1; j >= index; j--) {
				keys[j + 1] = keys[j];
			}
			keys[index] = oldRoot.keys[degree - 1];
			oldRoot.keys[degree - 1] = 0;
			currKeys++;
			// System.out.println("newRoot:" + this);
		}

		public void insertNotFull(int data) {
			int i = currKeys - 1;
			if (leaf) {
				for (; i >= 0 && keys[i] > data; i--) {
					keys[i + 1] = keys[i];
				}
				keys[i + 1] = data;
				currKeys++;
			} else {
				for (; i >= 0 && keys[i] > data; i--)
					;
				if (childs[i + 1].currKeys == 2 * degree - 1) {
					split(i + 1, childs[i + 1]);
					if (keys[i + 1] < data) {
						i++;
					}
				}
				childs[i + 1].insertNotFull(data);
			}
		}

		public BTreeNode search(int data) {
			int i = 0;
			for (; i < currKeys && data > keys[i]; i++)
				;
			if (keys[i] == data) {
				return this;
			}
			if (leaf) {
				return null;
			}
			return childs[i].search(data);
		}

		public int findKey(int data) {
			int i = 0;
			for (; i < currKeys && data > keys[i]; i++)
				;
			return i;
		}

		public void removeFromLeaf(int index) {
			for (int i = index + 1; i < currKeys; i++) {
				keys[i - 1] = keys[i];
			}
			keys[currKeys - 1] = 0;
			--currKeys;
			return;
		}

		public void removeFromNonLeaf(int index) {
			int key = keys[index];
			if (childs[index].currKeys >= degree) {
				int pred = 0;
				pred = getPred(index);
				keys[index] = pred;
				childs[index].remove(pred);

			} else if (childs[index + 1].currKeys >= degree) {
				int succ = 0;
				succ = getSucc(index);
				keys[index] = succ;
				childs[index + 1].remove(succ);
			} else {
				merge(index);
				childs[index].remove(key);
			}

		}

		public void merge(int index) {

			BTreeNode child = childs[index];
			BTreeNode sibling = childs[index + 1];
			child.keys[degree - 1] = keys[index];
			for (int i = 0; i < sibling.currKeys; i++) {
				child.keys[degree + i] = sibling.keys[i];
			}
			if (!child.leaf) {
				for (int i = 0; i <= sibling.currKeys; i++) {
					child.childs[degree + i] = sibling.childs[i];
				}
			}
			for (int i = index + 1; i < currKeys; i++) {
				keys[i - 1] = keys[i];
			}
			keys[currKeys - 1] = 0;
			for (int i = index + 2; i <= currKeys; i++) {
				childs[i - 1] = childs[i];
			}
			childs[currKeys] = null;
			child.currKeys += sibling.currKeys + 1;
			currKeys--;
			return;
		}

		public int getPred(int index) {
			BTreeNode node = childs[index];
			for (; !node.leaf; node = node.childs[node.currKeys])
				;
			return node.keys[node.currKeys - 1];
		}

		public int getSucc(int index) {
			BTreeNode node = childs[index + 1];
			for (; !node.leaf; node = node.childs[0])
				;
			return node.keys[0];
		}

		public void remove(int data) {
			int i = findKey(data);
			if (i < currKeys && keys[i] == data) {
				if (leaf) {
					removeFromLeaf(i);
				} else {
					removeFromNonLeaf(i);
				}
			} else {
				if (leaf) {
					System.out.println("The key:" + data + " is not present in this tree");
					return;
				}
				boolean flag = (i == currKeys) ? true : false;
				if (childs[i].currKeys < degree) {
					fill(i);
				}
				if (flag && i > currKeys) {
					// in case last child is merged
					childs[i - 1].remove(data);
				} else {
					childs[i].remove(data);
				}

			}
		}

		public void borrowFromPrev(int index) {

			BTreeNode child = childs[index];
			BTreeNode sibling = childs[index - 1];
			for (int i = child.currKeys - 1; i >= 0; i--) {
				child.keys[i + 1] = child.keys[i];
			}
			if (!child.leaf) {
				for (int i = child.currKeys; i >= 0; i--) {
					child.childs[i + 1] = child.childs[i];
				}
				child.childs[0] = sibling.childs[sibling.currKeys];
				sibling.childs[sibling.currKeys] = null;
			}
			child.keys[0] = keys[index - 1];
			keys[index - 1] = sibling.keys[sibling.currKeys - 1];
			child.currKeys++;
			sibling.currKeys--;
			return;

		}

		public void borrowFromNext(int index) {

			BTreeNode child = childs[index];
			BTreeNode sibling = childs[index + 1];
			child.keys[child.currKeys] = keys[index];
			if (!child.leaf) {
				child.childs[(child.currKeys) + 1] = sibling.childs[0];
			}
			keys[index] = sibling.keys[0];
			for (int i = 1; i < sibling.currKeys; i++) {
				sibling.keys[i - 1] = sibling.keys[i];
			}
			sibling.keys[sibling.currKeys - 1] = 0;
			for (int i = 1; i <= sibling.currKeys; i++) {
				sibling.childs[i - 1] = sibling.childs[i];
			}
			sibling.childs[sibling.currKeys] = null;
			sibling.currKeys--;
			child.currKeys++;
			return;
		}

		public void fill(int index) {

			if (index != 0 && childs[index - 1].currKeys >= degree) {
				borrowFromPrev(index);
			} else if (index != currKeys && childs[index + 1].currKeys >= degree) {
				borrowFromNext(index);
			} else {
				if (index != currKeys) {
					merge(index);
				} else {
					// merge merges with next child so if it is last child we
					// have to merge it with previous one
					merge(index - 1);
				}
			}
			return;
		}
	}

	public static void main(String[] args) {
		BTree bTree = new BTree(3);
		bTree.insert(1);
		bTree.insert(3);
		bTree.insert(7);
		bTree.insert(10);
		bTree.insert(11);
		bTree.insert(13);
		// bTree.insert(14);
		// bTree.insert(15);
		// bTree.insert(18);
		// bTree.insert(16);
		// bTree.insert(19);
		// bTree.insert(24);
		// bTree.insert(25);
		// bTree.insert(26);
		// bTree.insert(21);
		// bTree.insert(4);
		// bTree.insert(5);
		// bTree.insert(20);
		// bTree.insert(22);
		// bTree.insert(2);
		// bTree.insert(17);
		// bTree.insert(12);
		// bTree.insert(6);
		// bTree.root.traverse();
		// System.out.println(bTree.root.search(4));
	}

	public void remove(int data) {
		if (root == null) {
			System.out.println("Tree is Empty");
		}
		root.remove(data);
		if (root.currKeys == 0) {
			if (root.leaf) {
				root = null;
			} else {
				root = root.childs[0];
			}
		}
		return;
	}

	public void insert(int data) {
		if (root == null) {
			root = new BTreeNode(true, degree);
			root.keys[0] = data;
			root.currKeys = 1;
		} else {
			if (root.currKeys == 2 * degree - 1) {
				BTreeNode newRoot = new BTreeNode(false, degree);
				newRoot.childs[0] = root;
				newRoot.split(0, root);
				int i = 0;
				if (data > newRoot.keys[0]) {
					i++;
				}
				newRoot.childs[i].insertNotFull(data);
				root = newRoot;

			} else {
				root.insertNotFull(data);
			}
		}
	}
}
