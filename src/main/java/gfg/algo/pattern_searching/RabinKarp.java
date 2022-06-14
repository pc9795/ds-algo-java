package gfg.algo.pattern_searching;

public class RabinKarp {

  // t=n+m average case
  // t=nm worst case when all characters are same
  public static void search(String text, String pattern) {
    int d = 256;
    int h = (int) Math.pow(d, pattern.length() - 1);
    int textHash = 0;
    int patternHash = 0;
    for (int i = 0; i < pattern.length(); i++) {
      textHash = d * textHash + text.charAt(i);
      patternHash = d * patternHash + pattern.charAt(i);
    }
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
