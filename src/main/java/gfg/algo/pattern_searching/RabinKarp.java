package gfg.algo.pattern_searching;

public class RabinKarp {

  /** t=O(n+m) O(nm) when all characters of pattern and text are same. */
  public static void rabinKarp(String text, String pattern) {
    int d = 256;
    int h = (int) Math.pow(d, pattern.length() - 1);
    int textHash = 0;
    int patternHash = 0;
    for (int i = 0; i < pattern.length(); i++) {
      textHash = d * textHash + text.charAt(i);
      patternHash = d * patternHash + pattern.charAt(i);
    }
    //        Equal to sign is here because we will run the loop for each value after removing the
    // pattern.length that is
    //        text.length()-pattern.length()-1 and 1 time for the pattern length which is
    // subtracted.
    for (int i = 0; i <= text.length() - pattern.length(); i++) {
      if (textHash == patternHash) {
        int j = 0;
        for (; j < pattern.length(); j++) {
          if (text.charAt(i + j) != pattern.charAt(j)) {
            break;
          }
        }
        if (j == pattern.length()) {
          System.out.println("Pattern found at:" + i);
        }
      }
      if (i < (text.length() - pattern.length())) {
        textHash = d * (textHash - h * text.charAt(i)) + text.charAt(i + pattern.length());
      }
    }
  }
}
