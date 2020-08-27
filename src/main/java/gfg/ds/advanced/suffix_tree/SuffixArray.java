package gfg.ds.advanced.suffix_tree;

import java.util.Arrays;

/**
 * todo check how can we get rid of two SuffixNode implementation
 *
 * @noinspection WeakerAccess
 */
public class SuffixArray {
  public int values[];
  public String input;

  public SuffixArray(String input) {
    this.input = input;
    values = new int[input.length()];
    buildSuffixArray(input);
  }

  /** T=(n^2)*(log n) ; sort*string comparison during sort(O(n)) */
  private void buildSuffixArray(String input) {
    SuffixNode[] arr = new SuffixNode[input.length()];
    for (int i = 0; i < input.length(); i++) {
      arr[i] = new SuffixNode(i, input.substring(i));
    }
    Arrays.sort(arr);
    for (int i = 0; i < input.length(); i++) {
      values[i] = arr[i].index;
    }
  }

  /** t=O(n*log n*log n); if n*log n sorting algo is used =O(n*log n); if radix sort is used. */
  public static int[] buildSuffixArray2(String input) {
    int n = input.length();
    SuffixNode2[] suffixes = new SuffixNode2[n];
    // Creating suffixes and assigning ranks
    for (int i = 0; i < n; i++) {
      // We have taken ! as it is first printable character and comes before 0-9, a-z, and A-Z.
      suffixes[i] = new SuffixNode2(i, input.charAt(i) - '!');
    }
    // We have already calculated rank of all possible suffixes so we can use that information for
    // next rank.
    for (int i = 0; i < n; i++) {
      suffixes[i].nextRank = i + 1 < n ? suffixes[i + 1].rank : -1;
    }

    Arrays.sort(suffixes);

    int ind[] = new int[n]; // Description below
    for (int length = 4; length < 2 * n; length <<= 1) {
      int increment = 0;
      int previousRank = suffixes[0].rank;
      suffixes[0].rank = increment;
      ind[suffixes[0].index] = 0;
      // Calculating rank
      for (int i = 1; i < n; i++) {
        if (suffixes[i].rank == previousRank && suffixes[i].nextRank == suffixes[i - 1].nextRank) {
          previousRank = suffixes[i].rank;
          suffixes[i].rank = increment;
        } else {
          previousRank = suffixes[i].rank;
          suffixes[i].rank = ++increment;
        }
        ind[suffixes[i].index] = i;
      }
      // Calculating next rank
      for (int i = 0; i < n; i++) {
        int nextSuffixStart = suffixes[i].index + (length / 2);
        // Previously we used first calculation of ranks to calculate next ranks but we knew that
        // the next suffix
        // is just below the current one. That's why we just used i+1(second for loop). Now it is
        // not sure so
        // actually we have to store position of a suffix inside the sorted array so that we can use
        // its rank
        suffixes[i].nextRank = nextSuffixStart < n ? suffixes[ind[nextSuffixStart]].rank : -1;
      }
      Arrays.sort(suffixes);
    }

    int suffixArr[] = new int[n];
    for (int i = 0; i < n; i++) {
      suffixArr[i] = suffixes[i].index;
    }
    return suffixArr;
  }

  /** t=O(m*log n); m is pattern length */
  public int search(String pattern) {
    int l = 0;
    int r = values.length - 1;
    while (l <= r) {
      int mid = (l + r) >> 1;
      int res = input.substring(mid, mid + pattern.length()).compareTo(pattern);
      if (res == 0) {
        return mid;
      }
      if (res < 0) {
        r = mid - 1;
      } else {
        l = mid + 1;
      }
    }
    return -1;
  }

  /** Created By: Prashant Chaubey Created On: 09-02-2020 11:29 */
  public static class SuffixNode2 implements Comparable<SuffixNode2> {
    public int index;
    public int rank;
    public int nextRank;

    public SuffixNode2(int index, int rank) {
      this.index = index;
      this.rank = rank;
    }

    @Override
    public int compareTo(SuffixNode2 o) {
      return (rank == o.rank) ? nextRank - o.nextRank : rank - o.rank;
    }

    @Override
    public String toString() {
      return "SuffixNode2{" + "index=" + index + ", rank=" + rank + ", nextRank=" + nextRank + '}';
    }
  }

  public static class SuffixNode implements Comparable<SuffixNode> {
    public int index;
    private String suffix;

    public SuffixNode(int index, String suffix) {
      this.index = index;
      this.suffix = suffix;
    }

    @Override
    public int compareTo(SuffixNode o) {
      return this.suffix.compareTo(o.suffix);
    }
  }
}
