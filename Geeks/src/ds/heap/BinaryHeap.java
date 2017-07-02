package ds.heap;

public abstract class BinaryHeap {

	int arr[];
	int size;

	public BinaryHeap(int size) {
		arr = new int[size];
		this.size = 0;
	}

	public int left(int i) {
		return 2 * i + 1;
	}

	public int right(int i) {
		return 2 * i + 2;
	}

	public int parent(int i) {
		return (i - 1) / 2;
	}

	public abstract int getMin();

	public abstract void insert(int data);

	public abstract int extractMin();

	public abstract void decreaseKey(int key, int value);

	public abstract void delete(int value);
}
