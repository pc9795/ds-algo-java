package geeks_for_geeks.algo.pattern_searching;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-11-2018 00:47
 **/
public class PatternSearchingProblems {
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
