package gfg.algo.pattern_searching;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-11-2018 00:47
 **/
public class PatternSearchingProblems {
    /**
     * t=O(n)
     */
    public static void anagramSubstringSearch(String text, String pattern) {
        int[] windowCharFreq = new int[26];
        int[] patternCharFreq = new int[26];
        for (int i = 0; i < pattern.length(); i++) {
            windowCharFreq[pattern.charAt(i) - 'A']++;
            patternCharFreq[pattern.charAt(i) - 'A']++;
        }
        if (Arrays.equals(windowCharFreq, patternCharFreq)) {
            System.out.println("Pattern found at: 0");
        }
        for (int i = pattern.length(); i < text.length(); i++) {
            windowCharFreq[text.charAt(i) - 'A']++;
            windowCharFreq[text.charAt(i - pattern.length()) - 'A']--;
            if (Arrays.equals(windowCharFreq, patternCharFreq)) {
                System.out.println("Pattern found at:" + (i - pattern.length() + 1));
            }
        }
    }
}
