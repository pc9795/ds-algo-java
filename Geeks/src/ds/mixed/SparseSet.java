package ds.mixed;

import java.util.Arrays;

public class SparseSet {

	int sparse[];
	int dense[];
	int n;
	int maxVal;
	int capacity;

	public SparseSet(int maxVal, int capacity) {
		sparse = new int[maxVal + 1];
		dense = new int[capacity];
		for (int i = 0; i < sparse.length; i++) {
			sparse[i] = -1;
		}
		for (int i = 0; i < dense.length; i++) {
			dense[i] = -1;
		}
		this.capacity = capacity;
		this.maxVal = maxVal;
	}

	public void show() {
		System.out.println("sparse:" + Arrays.toString(sparse));
		System.out.println("dense:" + Arrays.toString(dense));
		System.out.println("n:" + n);
	}

	public static void main(String[] args) {
		SparseSet set = new SparseSet(10, 4);
		set.insert(3);
		set.insert(5);
		set.insert(7);
		set.insert(4);
		set.delete(3);
		set.insert(10);
		set.show();
	}

	public void insert(int data) {
		if (n == capacity || data > maxVal) {
			System.out.println("Cannot be inserted!");
			return;
		}
		dense[n] = data;
		sparse[data] = n;
		n++;
	}

	public void delete(int data) {
		if (!search(data)) {
			System.out.println("Data to be delted is not present!");
			return;
		}
		int prev = dense[n - 1];
		sparse[prev] = sparse[data];
		dense[sparse[data]] = prev;
		dense[n - 1] = 0;
		sparse[data] = -1;
		n--;
	}

	public void deleteAll() {
		n = 0;
	}

	public boolean search(int data) {
		if (data > maxVal) {
			System.out.println("NotPresent:1");
			return false;
		}
		if (sparse[data] < n && dense[sparse[data]] == data) {
			System.out.println("Present!");
			return true;
		}
		System.out.println("NotPresent:2");
		return false;
	}
}
