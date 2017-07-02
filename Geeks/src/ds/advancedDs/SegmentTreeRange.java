package ds.advancedDs;

import util.Util;

public class SegmentTreeRange extends SegmentTree {

	public static void main(String[] args) {
		int arr[] = { 1, 3, 2, 7, 9, 11 };
		SegmentTreeRange tree = new SegmentTreeRange(arr);
		Util.printArray(tree.segmentTree);
		System.out.println(tree.query(1, 5, 0, arr.length - 1, 0));
	}

	public SegmentTreeRange(int arr[]) {
		int size = (int) (2 * (Math.pow(2, Math.ceil(Math.log(arr.length) / Math.log(2)))) - 1);
		segmentTree = new Integer[size];
		constructSegmentTree(arr, 0, arr.length - 1, 0);
	}

	@Override
	public void update(int sStart, int sEnd, int diff, int i, int sCurrent) {

	}

	@Override
	// start-sStart-sEnd-end
	public int query(int start, int end, int sStart, int sEnd, int sCurrent) {
		System.out.println("sStart:" + sStart + " sEnd:" + sEnd + " sCurrent:" + sCurrent);
		if (start <= sStart && end >= sEnd) {
			return segmentTree[sCurrent];
		}
		if (sStart > end || sEnd < start) {
			return Integer.MAX_VALUE;
		}
		return Math.min(query(start, end, sStart, (sEnd + sStart) / 2, 2 * sCurrent + 1),
				query(start, end, (sStart + sEnd) / 2 + 1, sEnd, 2 * sCurrent + 2));
	}

	@Override
	public int constructSegmentTree(int[] arr, int start, int end, int treeCurrent) {
		// System.out.println("Start:" + start + "End:" + end + "Current:" +
		// treeCurrent);
		if (start == end) {
			segmentTree[treeCurrent] = arr[start];
			return arr[start];
		} else {

			segmentTree[treeCurrent] = Math.min(
					constructSegmentTree(arr, start, (start + end) / 2, 2 * treeCurrent + 1),
					constructSegmentTree(arr, (start + end) / 2 + 1, end, 2 * treeCurrent + 2));
			return segmentTree[treeCurrent];
		}
	}

}
