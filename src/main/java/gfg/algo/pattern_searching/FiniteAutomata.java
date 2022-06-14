package gfg.algo.pattern_searching;

public class FiniteAutomata {
  private static final int NUM_OF_CHARS = 5;

  // t=m^2
  private static int getNextState(String pattern, int currentState, char currentCharacter) {
    if (currentState < pattern.length() && pattern.charAt(currentState) == currentCharacter) {
      return currentState + 1;
    }
    for (int i = currentState; i > 0; i--) {
      if (pattern.charAt(i - 1) == currentCharacter) {
        int j;
        for (j = 0; j < i - 1; j++) {
          if (pattern.charAt(j) != pattern.charAt(currentState + 1 - i + j)) {
            break;
          }
        }
        if (j == i - 1) {
          return i;
        }
      }
    }
    return 0;
  }

  // t=m^3
  // s=m
  public static int[][] computeFiniteAutomata(String pattern) {
    int[][] finiteAutomata = new int[pattern.length() + 1][NUM_OF_CHARS];
    for (int i = 0; i < pattern.length() + 1; i++) {
      for (int j = 0; j < NUM_OF_CHARS; j++) {
        finiteAutomata[i][j] = getNextState(pattern, i, (char) ('A' + j));
      }
    }
    return finiteAutomata;
  }

  // t=m
  // s=m
  public static int[][] computeFiniteAutomataEfficient(String pattern) {
    int[][] finiteAutomata = new int[pattern.length() + 1][NUM_OF_CHARS];
    int lps = 0;
    finiteAutomata[0][pattern.charAt(0) - 'A'] = 1;
    for (int i = 1; i < pattern.length(); i++) {
      System.arraycopy(finiteAutomata[lps], 0, finiteAutomata[i], 0, NUM_OF_CHARS);
      finiteAutomata[i][pattern.charAt(i) - 'A'] = i + 1;
      lps = finiteAutomata[lps][pattern.charAt(i) - 'A'];
    }
    System.arraycopy(finiteAutomata[lps], 0, finiteAutomata[pattern.length()], 0, NUM_OF_CHARS);
    return finiteAutomata;
  }

  // t = m^3 + n
  public static void search(String text, String pattern) {
    int[][] finiteAutomata = computeFiniteAutomata(pattern);
    for (int i = 0, state = 0; i < text.length(); i++) {
      state = finiteAutomata[state][text.charAt(i) - 'A'];
      if (state == pattern.length()) {
        System.out.println("Pattern found at:" + (i - pattern.length() + 1));
      }
    }
  }
}
