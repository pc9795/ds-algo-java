package gfg.ds.advanced.suffix_tree;

public class Applications {
  public static int[] buildLCPArray(String input) {
    int n = input.length();
    int[] suffixArr = SuffixArray.buildSuffixArrayEfficient(input);
    int[] suffixArrInv = new int[n];
    for (int i = 0; i < n; i++) {
      suffixArrInv[suffixArr[i]] = i;
    }

    int[] lcp = new int[n];
    int k = 0;
    for (int i = 0; i < n; i++) {
      int suffixArrPos = suffixArrInv[i];
      if (suffixArrPos == n - 1) continue;
      int j = suffixArr[suffixArrPos + 1];
      while ((i + k < n) && (j + k < n) && (input.charAt(i + k) == input.charAt(j + k))) {
        k++;
      }

      lcp[suffixArrPos] = k;
      // Reducing k for next prefix
      if (k > 0) {
        k--;
      }
    }
    return lcp;
  }
}
