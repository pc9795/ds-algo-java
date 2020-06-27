package gfg.algo.pattern_searching;

/**
 * Created By: Prashant Chaubey
 * Created On: 17-10-2018 23:16
 **/
public class KMP {

    public static void kmpSubstringSearch(String text, String pattern) {
        int lps[] = buildPrefixArray(pattern);
        for (int i = 0, j = 0; i < text.length(); ) {
            if (text.charAt(i) != pattern.charAt(j)) {
                if (j == 0) {
                    i++;
                } else {
                    j = lps[j - 1];
                }
            } else {
                i++;
                j++;
            }
            if (j == pattern.length()) {
                System.out.println("Match found at pos:" + (i - pattern.length()));
                j = lps[j - 1];
            }
        }

    }

    private static int[] buildPrefixArray(String text) {
        if (text == null || text.length() == 0) {
            System.out.println("Pattern is empty");
            return new int[0];
        }
        int lps[] = new int[text.length()];
        lps[0] = 0;
        for (int i = 1, j = 0; i < text.length(); ) {
            if (text.charAt(j) == text.charAt(i)) {
                j++;
                lps[i] = j;
                i++;
            } else if (j == 0) {
                lps[i] = 0;
                i++;
            } else {
                j = lps[j - 1];
            }
        }
        return lps;
    }
}
