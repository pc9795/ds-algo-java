package gfg.ds.advanced.fenwick_tree;

/** @noinspection WeakerAccess */
public class RangeUpdateRangeQuery {
  private FenwickTree primary;
  private FenwickTree secondary;

  public RangeUpdateRangeQuery(int n) {
    // This implementation requires that all values start with 0.
    primary = new FenwickTree(new int[n], n);
    secondary = new FenwickTree(new int[n], n);
  }

  public void rangeUpdate(int l, int r, int increment) {
    // Incrementing indices for Fenwick tree operations which are 1 based.
    primary.update(l + 1, increment);
    primary.update(r + 1 + 1, -increment);
    secondary.update(l + 1, (l - 1) * increment);
    secondary.update(r + 1 + 1, -r * increment);
  }

  public int rangeQuery(int l, int r) {
    return query(r) - query(l - 1);
  }

  private int query(int index) {
    // We are optimistic that all the values are same till the index therefore multiplying by index.
    // Everything starts with 0 therefore this assumption is true.
    // After we are adjusting values according to second BIT.
    return primary.query(index + 1) * index - secondary.query(index + 1);
  }
}
