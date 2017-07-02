package ds.matrix;

import util.Util;

public class Matrix {

	// mn|mn
	public static void findMaxSubSquare(Integer[][] matrix) {
		Integer[][] temp = new Integer[matrix.length][matrix[0].length];
		Util.print2DArray(temp);
		Util.print2DArray(matrix);
		for (int i = 0; i < matrix.length; i++) {
			temp[i][0] = matrix[i][0];
		}
		for (int i = 0; i < matrix[0].length; i++) {
			temp[0][i] = matrix[0][i];
		}
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 1) {
					// System.out.println("i:" + i + "j:" + j + "min:"
					// + Matrix.min(matrix[i - 1][j], matrix[i][j - 1], matrix[i
					// - 1][j - 1]));
					temp[i][j] = Matrix.min(temp[i - 1][j], temp[i][j - 1], temp[i - 1][j - 1]) + 1;
				} else {
					temp[i][j] = 0;
				}
			}
		}
		int max = Integer.MIN_VALUE;
		int maxi = Integer.MIN_VALUE;
		int maxj = Integer.MIN_VALUE;
		for (int i = 0; i < temp.length; i++) {
			for (int j = 0; j < temp[0].length; j++) {
				if (temp[i][j] > max) {
					max = temp[i][j];
					maxi = i;
					maxj = j;
				}
			}
		}
		System.out.println("size:" + max + " i:" + maxi + " j:" + maxj);
		Util.print2DArray(temp);
	}

	public static int min(int a, int b, int c) {
		if (a < b) {
			if (a < c) {
				return a;
			} else {
				return c;
			}
		} else if (b < c) {
			return b;
		} else {
			return c;
		}
	}

	// n^3
	public static void findMaxSubMatrix(int[][] matrix) {

		int cols = matrix[0].length;
		int rows = matrix.length;
		int[] currentResult;
		int maxSum = Integer.MIN_VALUE;
		int left = 0;
		int right = 0;
		int top = 0;
		int bottom = 0;
		for (int leftCol = 0; leftCol < cols; leftCol++) {
			int[] temp = new int[rows];
			for (int rightCol = leftCol; rightCol < cols; rightCol++) {
				for (int i = 0; i < rows; i++) {
					temp[i] += matrix[i][rightCol];
				}
				currentResult = kadne(temp);
				if (currentResult[0] > maxSum) {
					maxSum = currentResult[0];
					left = leftCol;
					right = rightCol;
					top = currentResult[1];
					bottom = currentResult[2];
				}
			}
		}
		System.out.println("maxsum:" + maxSum + "top:" + top + "bottom:" + bottom + "left:" + left + "right:" + right);
	}

	public static int[] kadne(int[] arr) {

		int result[] = new int[] { Integer.MIN_VALUE, 0, -1 };
		int currentSum = 0;
		int localStart = 0;
		for (int i = 0; i < arr.length; i++) {
			currentSum += arr[i];
			if (currentSum < 0) {
				currentSum = 0;
				localStart = i + 1;
			} else if (currentSum > result[0]) {
				result[0] = currentSum;
				result[1] = localStart;
				result[2] = i;
			}
		}
		if (result[2] == -1) {
			result[0] = arr[0];
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] > result[0]) {
					result[0] = arr[i];
					result[1] = i;
					result[2] = i;
				}
			}
		}
		return result;
	}

	// mn|mn
	public void alternateOandX(int ex, int ey) {
		int sx = 0;
		int sy = 0;
		int r = ex, c = ey;
		Character[][] matrix = new Character[ex][ey];
		char x = 'X';
		for (; sx < ex && sy < ey;) {
			for (int i = sy; i < ey; i++) {
				matrix[sx][i] = x;
			}
			// Util.print2DArray(matrix);
			sx++;
			for (int i = sx; i < ex; i++) {
				matrix[i][ey - 1] = x;
			}
			// Util.print2DArray(matrix);
			ey--;
			if (sx < ex) {
				for (int i = sy; i < ey; i++) {
					matrix[ex - 1][i] = x;
				}
				ex--;
			}
			// Util.print2DArray(matrix);
			if (sy < ey) {
				for (int i = sx; i < ex; i++) {
					matrix[i][sy] = x;
				}
				sy++;
			}
			// Util.print2DArray(matrix);
			x = (x == 'X' ? 'O' : 'X');
		}
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	// n^2[square matrix]
	public void sumOfSubsquare(int matrix[][], int k) {
		if (k > matrix.length) {
			return;
		}
		Integer subSquare[][] = new Integer[matrix.length][];
		for (int i = 0; i < matrix.length; i++) {
			subSquare[i] = new Integer[matrix.length];
		}
		Util.print2DArray(subSquare, "subSquare");
		for (int j = 0; j < matrix.length; j++) {
			int sum = 0;
			for (int i = 0; i < k; i++) {
				sum += matrix[i][j];
			}
			subSquare[0][j] = sum;
			for (int i = 1; i < matrix.length - k + 1; i++) {
				sum += matrix[i + k - 1][j] - matrix[i - 1][j];
				subSquare[i][j] = sum;
			}
		}
		Util.print2DArray(subSquare, "subSquare");
		for (int i = 0; i < matrix.length - k + 1; i++) {
			int sum = 0;
			for (int j = 0; j < k; j++) {
				sum += subSquare[i][j];
			}
			System.out.print(sum);
			for (int j = 1; j < matrix.length - k + 1; j++) {
				sum += subSquare[i][j + k - 1] - subSquare[i][j - 1];
				System.out.print(sum);
			}
			System.out.println();
		}

	}

	// mn
	public int findNoOfIslands(char[][] matrix) {

		int islandCount = 0;
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 'X') {
					System.out.println("i:" + i + ",j:" + j);
					if ((i == 0 || matrix[i - 1][j] == 'O') && (j == 0 || matrix[i][j - 1] == 'O')) {
						islandCount++;
					}
				}
			}
		}
		System.out.println("count:" + islandCount);
		return islandCount;
	}

	// mn
	public boolean findCommonElement(int[][] matrix) {

		System.out.println(matrix.length);
		System.out.println(matrix[0].length);
		Integer columns[] = new Integer[matrix.length];
		for (int i = 0; i < matrix.length; i++) {
			columns[i] = matrix[0].length - 1;
		}
		Util.printArray(columns);
		int minRow = 0;
		System.out.println("Loop");
		while (columns[minRow] >= 0) {

			for (int i = 1; i < matrix.length; i++) {
				if (matrix[i][columns[i]] < matrix[minRow][columns[minRow]]) {
					minRow = i;
				}
			}
			System.out.println(minRow);
			int eqCount = 0;
			for (int i = 0; i < matrix.length; i++) {
				if (matrix[i][columns[i]] != matrix[minRow][columns[minRow]]) {
					columns[i]--;
					if (columns[i] < 0) {
						return false;
					}
				} else {
					eqCount++;
				}
			}
			Util.printArray(columns);
			System.out.println("eqCount:" + eqCount);

			if (eqCount == matrix.length) {
				System.out.println("Minimum Element");
				System.out.println(matrix[minRow][columns[minRow]]);
				return true;
			}
		}
		return false;
	}
}
