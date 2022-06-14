package gfg.algo.pattern_searching;

import java.util.HashMap;
import java.util.Map;

public class BoyerMoore {

  private static Map<Character, Integer> badCharacterHeuristic(String pattern) {
    // we can use a fix size array also
    Map<Character, Integer> bc = new HashMap<>();
    for (int i = 0; i < pattern.length(); i++) {
      bc.put(pattern.charAt(i), i);
    }
    return bc;
  }

  // t=n*m - when all the characters of text and pattern are same
  // s=1
  public static void search(String text, String pattern) {
    Map<Character, Integer> bc = badCharacterHeuristic(pattern);
    int shift = 0;
    while (shift <= text.length() - pattern.length()) {
      int j;
      for (j = pattern.length() - 1; j >= 0; j--) {
        if (text.charAt(shift + j) != pattern.charAt(j)) {
          break;
        }
      }
      if (j < 0) {
        System.out.println("Pattern found at " + (shift));
        shift +=
            (shift + pattern.length()) < text.length()
                ? pattern.length() - bc.getOrDefault(text.charAt(shift + pattern.length()), -1)
                : 1;
      } else {
        shift += Math.max(1, j - bc.getOrDefault(text.charAt(shift + j), -1));
      }
    }
  }
}
