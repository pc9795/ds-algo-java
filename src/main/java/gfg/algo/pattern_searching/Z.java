package gfg.algo.pattern_searching;

import java.util.ArrayList;
import java.util.List;

public class Z {
  public static List<Integer> search(String text, String pattern) {
    String combined = pattern + '$' + text;
    int[] z = buildZArray(combined);
    List<Integer> occurrences = new ArrayList<>();
    int m = pattern.length();
    for (int i = 0; i < combined.length(); i++) {
      if (z[i] == m) {
        occurrences.add(i - m - 1);
      }
    }
    return occurrences;
  }

  // t=n
  // https://www.youtube.com/watch?v=CpZh4eF8QBw&ab_channel=TusharRoy-CodingMadeSimple
  private static int[] buildZArray(String input) {
    // [l,r] matches with prefix of input
    int l = 0, r = 0;
    int n = input.length();
    int[] z = new int[n];
    for (int i = 1; i < n; i++) {
      // nothing matches, recalculate
      if (i > r) {
        l = r = i;
        while (r < n && input.charAt(r - l) == input.charAt(r)) {
          r++;
        }
        z[i] = r - l;
        r--;
      } else {
        int k = i - l;
        if (z[k] < r - i + 1) {
          z[i] = z[k];
        } else {
          l = i;
          while (r < n && input.charAt(r - l) == input.charAt(r)) {
            r++;
          }
          z[i] = r - l;
          r--;
        }
      }
    }
    return z;
  }
}
