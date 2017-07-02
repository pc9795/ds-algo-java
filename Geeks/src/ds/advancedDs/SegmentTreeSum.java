package ds.advancedDs;

import util.Util;

public class SegmentTreeSum extends SegmentTree {

	public static void main(String[] args) {
		int arr[] = new int[] { 1, 3, 5, 7, 9, 11 };
		SegmentTreeSum tree = new SegmentTreeSum(arr);
		Util.printArray(tree.segmentTree);
		System.out.println(tree.query(1, 4, 0, arr.length - 1, 0));
		// tree.update(0, arr.length - 1, 10, 1, 0);
		// Util.printArray(tree.segmentTree);
	}

	public SegmentTreeSum(int arr[]) {
		int size = (int) (2 * (Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)))) - 1);
		segmentTree = new Integer[size];
		constructSegmentTree(arr, 0, arr.length - 1, 0);
	}

	@Override
	public void update(int sStart, int sEnd, int diff, int i, int sCurrent) {
		if (!(sStart <= i && sEnd >= i)) {
			return;
		}
		segmentTree[sCurrent] += diff;
		if (sStart != sEnd) {
			update(sStart, sStart + ((sEnd - sStart) / 2), diff, i, sCurrent * 2 + 1);
			update(sStart + ((sEnd - sStart) / 2) + 1, sEnd, diff, i, sCurrent * 2 + 2);
		}
	}

	@Override
	// start and end should enclose sStart and sEnd
	public int query(int start, int end, int sStart, int sEnd, int sCurrent) {
		System.out.println("sStart:" + sStart + "sEnd:" + sEnd + "sCurrent:" + sCurrent);
		if (start <= sStart && end >= sEnd) {
			return segmentTree[sCurrent];
		}
		if (sStart > end || sEnd < start) {
			return 0;
		}
		int leftSum = query(start, end, sStart, sStart + ((sEnd - sStart) / 2), sCurrent * 2 + 1);
		int rightSum = query(start, end, sStart + ((sEnd - sStart) / 2) + 1, sEnd, sCurrent * 2 + 2);
		// System.out.println("sStart:" + sStart + "sEnd:" + sEnd + "sCurrent:"
		// + sCurrent + "leftSum:" + leftSum + "rightSum" + rightSum);
		return leftSum + rightSum;
	}

	@Override
	public int constructSegmentTree(int arr[], int start, int end, int treeCurrent) {
		// System.out.println("Start:" + start + "End:" + end + "Current:"
		// + treeCurrent);
		if (start == end) {
			segmentTree[treeCurrent] = arr[start];
			return arr[start];
		} else {
			segmentTree[treeCurrent] = constructSegmentTree(arr, start, start + ((end - start) / 2),
					treeCurrent * 2 + 1)
					+ constructSegmentTree(arr, start + ((end - start) / 2) + 1, end, treeCurrent * 2 + 2);
			return segmentTree[treeCurrent];
		}
	}
}
