package gfg.ds.advanced.suffix_tree;

import utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SuffixArray {
  public int[] values;
  public String input;

  public SuffixArray(String input) {
    this.input = input;
    this.values = new int[input.length()];
    buildSuffixArray(input);
  }

  // t=(n^2)logn - sorting with string comparison
  private void buildSuffixArray(String input) {
    List<Pair<Integer, String>> suffixes = new ArrayList<>();
    for (int i = 0; i < input.length(); i++) {
      suffixes.add(Pair.of(i, input.substring(i)));
    }
    suffixes.sort(Comparator.comparing(o -> o.value));
    for (int i = 0; i < input.length(); i++) {
      values[i] = suffixes.get(i).key;
    }
  }

  // t=n*logn*logn; if radix sort is used we can get rid of one logn
  public static int[] buildSuffixArrayEfficient(String input) {
    int n = input.length();
    SuffixNode[] suffixes = new SuffixNode[n];
    for (int i = 0; i < n; i++) {
      suffixes[i] = new SuffixNode(i, input.charAt(i) - 'a');
    }
    for (int i = 0; i < n; i++) {
      suffixes[i].nextRank = i + 1 < n ? suffixes[i + 1].rank : -1;
    }
    Arrays.sort(suffixes);

    int[] ind = new int[n];
    for (int length = 4; length < 2 * n; length <<= 1) {
      int increment = 0;
      int previousRank = suffixes[0].rank;
      suffixes[0].rank = increment;
      ind[suffixes[0].index] = 0;
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
      for (int i = 0; i < n; i++) {
        int nextSuffixStart = suffixes[i].index + (length / 2);
        suffixes[i].nextRank = nextSuffixStart < n ? suffixes[ind[nextSuffixStart]].rank : -1;
      }
      Arrays.sort(suffixes);
    }

    int[] suffixArr = new int[n];
    for (int i = 0; i < n; i++) {
      suffixArr[i] = suffixes[i].index;
    }
    return suffixArr;
  }

  // t=mlogn - m is pattern length
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

  public static class SuffixNode implements Comparable<SuffixNode> {
    public int index;
    public int rank;
    public int nextRank;

    public SuffixNode(int index, int rank) {
      this.index = index;
      this.rank = rank;
    }

    @Override
    public int compareTo(SuffixNode o) {
      return (rank == o.rank) ? nextRank - o.nextRank : rank - o.rank;
    }

    @Override
    public String toString() {
      return "SuffixNode{" + "index=" + index + ", rank=" + rank + ", nextRank=" + nextRank + '}';
    }
  }
}
