package gfg.algo.pattern_searching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatternSearchingProblems {
  // t=n
  public static List<Integer> anagramSubstringSearch(String text, String pattern) {
    int[] windowCharFreq = new int[26];
    int[] patternCharFreq = new int[26];
    List<Integer> occurences = new ArrayList<>();
    for (int i = 0; i < pattern.length(); i++) {
      windowCharFreq[pattern.charAt(i) - 'A']++;
      patternCharFreq[pattern.charAt(i) - 'A']++;
    }
    if (Arrays.equals(windowCharFreq, patternCharFreq)) {
      occurences.add(0);
    }
    for (int i = pattern.length(); i < text.length(); i++) {
      windowCharFreq[text.charAt(i) - 'A']++;
      windowCharFreq[text.charAt(i - pattern.length()) - 'A']--;
      if (Arrays.equals(windowCharFreq, patternCharFreq)) {
        occurences.add(i - pattern.length() + 1);
      }
    }
    return occurences;
  }
}
