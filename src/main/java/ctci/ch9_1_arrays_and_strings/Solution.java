package ctci.ch9_1_arrays_and_strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Solution {

  /** t=O(n) */
  public static boolean isUnique(String str) {
    if (str == null || str.isEmpty()) {
      return true;
    }
    HashSet<Character> set = new HashSet<>();
    for (char c : str.toCharArray()) {
      if (set.contains(c)) {
        return false;
      }
      set.add(c);
    }
    return true;
  }

  /** t=O(n^2) We can also sort the array and compare. */
  public static boolean isUnique2(String str) {
    if (str == null || str.isEmpty()) {
      return true;
    }
    char[] strArr = str.toCharArray();
    Arrays.sort(strArr);
    for (int i = 0; i < strArr.length; i++) {
      for (int j = 0; j < strArr.length; j++) {
        if (i == j) {
          continue;
        }
        if (strArr[i] == strArr[j]) {
          return false;
        }
      }
    }
    return true;
  }

  /** t=O(n) We can also sort the string and then compare. */
  public static boolean checkPermutation(String str1, String str2) {
    if (str1 == null || str2 == null) {
      return false;
    }

    if (str1.length() != str2.length()) {
      return false;
    }
    HashMap<Character, Integer> wordCount = new HashMap<>();
    for (char c : str1.toCharArray()) {
      if (!wordCount.containsKey(c)) {
        wordCount.put(c, 0);
      }
      wordCount.put(c, wordCount.get(c) + 1);
    }
    for (char c : str2.toCharArray()) {
      if (!wordCount.containsKey(c)) {
        wordCount.put(c, 0);
      }
      wordCount.put(c, wordCount.get(c) - 1);
      if (wordCount.get(c) < 0) {
        return false;
      }
    }
    return true;
  }

  /** t=O(n) */
  public static String urlify(String str, int trueLength) {
    if (trueLength <= 0) {
      return str;
    }
    if (str == null) {
      return "";
    }
    trueLength--;
    char[] strArr = str.toCharArray();
    for (int j = strArr.length - 1; trueLength >= 0; j--, trueLength--) {
      if (strArr[trueLength] == ' ') {
        strArr[j--] = '%';
        strArr[j--] = '0';
        strArr[j] = '2';
        continue;
      }
      strArr[j] = strArr[trueLength];
    }
    return new String(strArr);
  }

  /** t=O(n) We can use a bit set and do toggling; If 0 remains 0 then it is even else odd. */
  public static boolean palindromePermutation(String str) {
    HashMap<Character, Integer> wordCount = new HashMap<>();
    str = str.toLowerCase();
    for (char c : str.toCharArray()) {
      if (!Character.isAlphabetic(c)) {
        continue;
      }
      if (!wordCount.containsKey(c)) {
        wordCount.put(c, 0);
      }
      wordCount.put(c, wordCount.get(c) + 1);
    }
    int oddCount = 0;
    for (char c : wordCount.keySet()) {
      if (wordCount.get(c) % 2 == 1) {
        oddCount++;
      }
    }
    return oddCount <= 1;
  }

  /** t=O(m+n) */
  public static boolean oneAway(String str1, String str2) {
    HashMap<Character, Integer> wordCount = new HashMap<>();

    if (Math.abs(str1.length() - str2.length()) > 1) {
      return false;
    }
    for (char c : str1.toCharArray()) {
      if (!wordCount.containsKey(c)) {
        wordCount.put(c, 0);
      }
      wordCount.put(c, wordCount.get(c) + 1);
    }
    for (char c : str2.toCharArray()) {
      if (!wordCount.containsKey(c)) {
        wordCount.put(c, 0);
      }
      wordCount.put(c, wordCount.get(c) - 1);
    }
    boolean oneFound = false;
    boolean minusOneFound = false;
    for (char c : wordCount.keySet()) {
      if (wordCount.get(c) == 1) {
        if (oneFound) {
          return false;
        }
        oneFound = true;
      } else if (wordCount.get(c) == -1) {
        if (minusOneFound) {
          return false;
        }
        minusOneFound = true;
      } else if (wordCount.get(c) != 0) {
        return false;
      }
    }

    return true;
  }

  /** t=O(n) */
  public static String stringCompression(String str) {
    if (str == null || str.isEmpty()) {
      return "";
    }
    StringBuilder ans = new StringBuilder();
    char curr = str.charAt(0);
    int currCount = 1;
    for (int i = 1; i < str.length(); i++) {
      if (str.charAt(i) == curr) {
        currCount++;
      } else {
        ans.append(curr).append(currCount);
        curr = str.charAt(i);
        currCount = 1;
      }
    }
    ans.append(curr).append(currCount);
    return ans.length() == str.length() ? str : ans.toString();
  }

  /** t=O(m*n) */
  public static int[][] rotateMatrix(int[][] mat) {
    int n = mat.length;
    for (int layer = 0; layer < n / 2; layer++) {
      for (int i = layer; i < n - layer - 1; i++) {
        int temp = mat[layer][i];
        mat[layer][i] = mat[n - i - 1][layer];
        mat[n - i - 1][layer] = mat[n - layer - 1][n - i - 1];
        mat[n - layer - 1][n - i - 1] = mat[i][n - layer - 1];
        mat[i][n - layer - 1] = temp;
      }
    }
    return mat;
  }

  /** t=O(m*n) s=O(1) */
  public static int[][] zeroMatrix(int[][] mat) {
    if (mat == null || mat.length == 0) {
      return new int[][] {};
    }
    boolean firstRowHasZero = false;
    boolean firstColumnHasZero = false;
    for (int i = 0; i < mat.length; i++) {
      if (mat[0][i] == 0) {
        firstRowHasZero = true;
        break;
      }
    }

    for (int i = 0; i < mat[0].length; i++) {
      if (mat[i][0] == 0) {
        firstColumnHasZero = true;
        break;
      }
    }
    for (int i = 1; i < mat.length; i++) {
      for (int j = 1; j < mat[0].length; j++) {
        if (mat[i][j] == 0) {
          mat[i][0] = 0;
          mat[0][j] = 0;
        }
      }
    }
    for (int i = 1; i < mat.length; i++) {
      for (int j = 1; j < mat[0].length; j++) {
        if (mat[i][0] == 0 || mat[0][j] == 0) {
          mat[i][j] = 0;
        }
      }
    }
    if (firstRowHasZero) {
      for (int i = 0; i < mat.length; i++) {
        mat[0][i] = 0;
      }
    }

    if (firstColumnHasZero) {
      for (int i = 0; i < mat[0].length; i++) {
        mat[i][0] = 0;
      }
    }

    return mat;
  }

  /** t=O(n) */
  public static boolean stringRotation(String str1, String str2) {
    if (str1.length() != str2.length()) {
      return false;
    }
    return (str2 + str2).contains(str1);
  }
}
