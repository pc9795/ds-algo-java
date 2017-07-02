package ds.advancedDs;

public abstract class SegmentTree {

	Integer segmentTree[];

	public abstract void update(int sStart, int sEnd, int diff, int i, int sCurrent);

	public abstract int query(int start, int end, int sStart, int sEnd, int sCurrent);

	public abstract int constructSegmentTree(int arr[], int start, int end, int treeCurrent);
}
