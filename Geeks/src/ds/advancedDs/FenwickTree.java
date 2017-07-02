package ds.advancedDs;

import util.Util;

public class FenwickTree {

	Integer fenwickTree[];

	public FenwickTree(int arr[]) {
		fenwickTree = new Integer[arr.length + 1];
		for (int i = 0; i < fenwickTree.length; i++) {
			fenwickTree[i] = 0;
		}
		constructFenwickTree(arr);
	}

	public static void main(String[] args) {
		int arr[] = { 2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9 };
		FenwickTree tree = new FenwickTree(arr);
		Util.printArray(tree.fenwickTree);
		System.out.println(tree.getSum(5));
	}

	public int getParentUpdate(int i) {
		return i + (i & -i);
	}

	public int getParentSum(int i) {
		return i - (i & -i);
	}

	public int getSum(int index) {
		index = index + 1;
		int sum = 0;
		for (; index > 0;) {
			sum += fenwickTree[index];
			index = getParentSum(index);
		}
		return sum;
	}

	public void updateSum(int value, int index, int len) {
		// System.out.println("for index:" + index);
		for (; index <= len;) {
			// System.out.println("index:" + index);
			fenwickTree[index] += value;
			index = getParentUpdate(index);
		}
		// System.out.println("-->finished");
	}

	public void constructFenwickTree(int arr[]) {
		for (int i = 0; i < arr.length; i++) {
			updateSum(arr[i], i + 1, arr.length);
		}
	}
}
