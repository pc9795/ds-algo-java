package geeks_for_geeks.algo.pattern_searching;

import geeks_for_geeks.ds.advanced.SuffixArray;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-11-2018 00:47
 **/
public class PatternSearchingProblems {
    /**
     * T=mlog(n)
     * Can only find one occurence
     *
     * @param text
     * @param pattern
     */
    public static void patternSearchUsingSuffixArray(String text, String pattern) {
        int[] arr = SuffixArray.buildSuffixArray(text);
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (pattern.length() <= text.length() - arr[mid]) {
                if (pattern.equals(text.substring(arr[mid], arr[mid] + pattern.length()))) {
                    System.out.println("Pattern found at:" + arr[mid]);
                }
            }
            if (pattern.compareTo(text.substring(arr[mid])) < 1) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
    }

    /**
     * T=O(n)
     *
     * @param text
     * @param pattern
     */
    public static void anagramSubstringSearch(String text, String pattern) {
        int[] windowCharFreq = new int[26];
        int[] patternCharFreq = new int[26];
        for (int i = 0; i < pattern.length(); i++) {
            windowCharFreq[pattern.charAt(i) - 'A']++;
            patternCharFreq[pattern.charAt(i) - 'A']++;
        }
        if (compareArray(windowCharFreq, patternCharFreq)) {
            System.out.println("Pattern found at: 0");
        }
        for (int i = pattern.length(); i < text.length(); i++) {
            windowCharFreq[text.charAt(i) - 'A']++;
            windowCharFreq[text.charAt(i - pattern.length()) - 'A']--;
            if (compareArray(windowCharFreq, patternCharFreq)) {
                System.out.println("Pattern found at:" + (i - pattern.length() + 1));
            }
        }
    }

    /**
     * T=O(1)
     *
     * @param first
     * @param second
     * @return
     */
    private static boolean compareArray(int[] first, int[] second) {
        for (int i = 0; i < first.length; i++) {
            if (first[i] != second[i]) {
                return false;
            }
        }
        return true;
    }
}
