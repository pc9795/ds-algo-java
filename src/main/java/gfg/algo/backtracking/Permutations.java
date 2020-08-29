package gfg.algo.backtracking;

public class Permutations {
  public static void printPermutations(String text) {
    printPermutationsUtil("", text);
  }

  /** t=O(n!) */
  private static void printPermutationsUtil(String first, String last) {
    if (last.length() == 0) {
      System.out.println(first);
      return;
    }
    for (int i = 0; i < last.length(); i++) {
      printPermutationsUtil(first + last.charAt(i), last.substring(0, i) + last.substring(i + 1));
    }
  }
}
