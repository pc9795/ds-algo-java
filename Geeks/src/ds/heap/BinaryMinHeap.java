package ds.heap;

public class BinaryMinHeap extends BinaryHeap {

	public String toString() {
		String result = ":";
		for (int i = 0; i < size; i++) {
			result += arr[i] + ":";
		}
		return result;
	}

	public BinaryMinHeap(int size) {
		super(size);
	}

	@Override
	public int getMin() {
		return size == 0 ? -1 : arr[0];
	}

	@Override
	public void insert(int data) {
		if (size == arr.length) {
			System.out.println("Heap Overflow!");
			return;
		}
		arr[size++] = data;
		for (int i = size - 1; i != 0 && arr[parent(i)] > arr[i]; i = parent(i)) {
			int temp = arr[parent(i)];
			arr[parent(i)] = arr[i];
			arr[i] = temp;
		}
	}

	public void heapify(int index) {
		int smallest = index;
		int l = left(index);
		int r = right(index);
		if (l < size && arr[l] < arr[index]) {
			smallest = l;
		}
		if (r < size && arr[r] < arr[smallest]) {
			smallest = r;
		}
		if (smallest != index) {
			int temp = arr[index];
			arr[index] = arr[smallest];
			arr[smallest] = temp;
			heapify(smallest);
		}
	}

	@Override
	public int extractMin() {
		if (size <= 0) {
			return -1;
		} else if (size == 1) {
			return arr[--size];
		}
		int value = arr[0];
		arr[0] = arr[--size];
		System.out.println("After extraction");
		System.out.println(this);
		heapify(0);
		System.out.println("After Heapify");
		return value;

	}

	@Override
	public void decreaseKey(int key, int value) {
		if (key < 0 || key >= size) {
			return;
		}
		arr[key] -= value;
		for (int i = key; i != 0 && arr[parent(i)] > arr[i]; i = parent(i)) {
			int temp = arr[parent(i)];
			arr[parent(i)] = arr[i];
			arr[i] = temp;
		}
	}

	@Override
	public void delete(int value) {
		decreaseKey(value, Integer.MIN_VALUE);
		System.out.println("Decreasing Step");
		System.out.println(this);
		extractMin();
	}

}
