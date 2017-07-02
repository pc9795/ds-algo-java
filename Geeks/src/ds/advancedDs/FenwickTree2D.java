package ds.advancedDs;

import util.Util;

public class FenwickTree2D {

	Integer[][] fenwickTree;

	public static void main(String[] args) {
		int arr[][] = { { 1, 2, 3, 4 }, { 5, 3, 8, 1 }, { 4, 6, 7, 5 }, { 2, 4, 8, 9 } };
		new FenwickTree2D(arr);
		// int x1 = 1;
		// int y1 = 1;
		// int x2 = 3;
		// int y2 = 2;
		// System.out.println(
		// tree.getSum(x2, y2) - tree.getSum(x2, y1 - 1) - tree.getSum(x1 - 1,
		// y2) + tree.getSum(x1 - 1, y1 - 1));
	}

	public void update(int x, int y, int value) {
		System.out.println("Inside update(" + x + "," + y + "," + value + ")");
		for (; x < fenwickTree.length; x += (x & -x)) {
			for (; y < fenwickTree[0].length; y += (y & -y)) {
				System.out.println("(" + x + "," + y + ")");
				fenwickTree[x][y] += value;
			}
		}
		Util.print2DArray(fenwickTree, "UpdatedFenwickTree");
		return;
	}

	public int getSum(int x, int y) {
		System.out.println("for(" + x + "," + y + ")");
		int sum = 0;
		for (; x > 0; x -= x & -x) {
			for (; y > 0; y -= y & -y) {
				System.out.println("(" + x + "," + y + ")");
				sum += fenwickTree[x][y];
			}
		}
		System.out.println("-->finished");
		return sum;
	}

	public void constructFenwickTree(int[][] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print("(" + i + "," + j + "," + arr[i][j] + ")|");
			}
			System.out.println();
		}
		Integer aux[][] = new Integer[arr.length + 1][arr.length + 1];
		for (int j = 1; j < aux.length; j++) {
			for (int i = 1; i < aux.length; i++) {
				aux[i][j] = arr[arr.length - j][i - 1];
				System.out.print("(" + i + "," + j + ")-->(" + (arr.length - j) + "," + (i - 1) + ")|");
			}
			System.out.println();
		}
		Util.print2DArray(aux, "Auxiallary Matrix");
		for (int i = 1; i <= arr.length; i++) {
			for (int j = 1; j <= arr.length; j++) {
				int v1 = getSum(i, j);
				int v2 = getSum(i, j - 1);
				int v3 = getSum(i - 1, j - 1);
				int v4 = getSum(i - 1, j);
				System.out.println("aux[i][j]:" + aux[i][j] + ",v1:" + v1 + ",v2:" + v2 + ",v3:" + v3 + ",v4:" + v4);
				update(i, j, aux[i][j] - (v1 - v2 - v4 + v3));
			}
		}
		Util.print2DArray(fenwickTree, "FenwickTree");
	}

	public FenwickTree2D(int arr[][]) {
		fenwickTree = new Integer[arr.length + 1][arr.length + 1];
		for (int i = 0; i < arr.length + 1; i++) {
			for (int j = 0; j < arr.length + 1; j++) {
				fenwickTree[i][j] = 0;
			}
		}
		constructFenwickTree(arr);
	}
}
