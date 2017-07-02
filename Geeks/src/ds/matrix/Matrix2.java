package ds.matrix;

import util.Util;

public class Matrix2 {

	public HeapNode[] arr;
	public int size;

	public Matrix2(HeapNode[] arr, int size) {
		this.arr = arr;
		this.size = size;
	}

	public Matrix2() {

	}

	public class HeapNode {
		int element;
		int row;
		int nextElement;

		public HeapNode(int element, int row, int nextElement) {
			this.element = element;
			this.row = row;
			this.nextElement = nextElement;
		}

		public String toString() {
			return String.valueOf(element);
		}

	}

	public HeapNode getMin() {
		return arr[0];
	}

	public int parent(int i) {
		return (i - 1) / 2;
	}

	public int left(int i) {
		return 2 * i + 1;
	}

	public int right(int i) {
		return 2 * i + 2;
	}

	public static void main(String[] args) {
		int mat[][] = { { 10, 20, 30, 40 }, { 15, 25, 35, 45 }, { 27, 29, 37, 48 }, { 32, 33, 39, 50 } };
		sortRowAndColumnWiseSortedMatrix(mat);
	}

	// n^2log(n)
	public static void sortRowAndColumnWiseSortedMatrix(int[][] matrix) {

		HeapNode[] arr = new HeapNode[matrix.length];
		Matrix2 heap = new Matrix2(arr, matrix.length);
		for (int i = 0; i < matrix.length; i++) {
			arr[i] = heap.new HeapNode(matrix[i][0], i, 1);
		}
		Util.printArray(heap.arr);
		for (int i = 0; i < matrix.length * matrix.length; i++) {
			HeapNode node = heap.getMin();
			System.out.println(node.element);
			if (node.nextElement < matrix.length) {
				node.element = matrix[node.row][node.nextElement];
				node.nextElement++;
			} else {
				node.element = Integer.MAX_VALUE;
			}
			heap.replaceMin(node);
		}

	}

	public void replaceMin(HeapNode node) {
		arr[0] = node;
		heapify(0);
	}

	public void heapify(int index) {

		int l = left(index);
		int r = right(index);
		int smallest = index;
		if (l < size && arr[l].element < arr[smallest].element) {
			smallest = l;
		}
		if (r < size && arr[r].element < arr[smallest].element) {
			smallest = r;
		}
		if (smallest != index) {
			HeapNode temp = arr[smallest];
			arr[smallest] = arr[index];
			arr[index] = temp;
			heapify(smallest);
		}
	}
}
