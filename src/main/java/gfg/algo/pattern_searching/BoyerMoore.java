package gfg.algo.pattern_searching;

/**
 * Created By: Prashant Chaubey
 * Created On: 10-11-2018 21:22
 **/
public class BoyerMoore {

    private static int[] badCharacterHeuristic(String pattern) {
        int NUM_OF_CHARS = 4;
        int bc[] = new int[NUM_OF_CHARS];
        for (int i = 0; i < bc.length; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < pattern.length(); i++) {
            bc[pattern.charAt(i) - 'A'] = i;
        }
        return bc;
    }

    public static void search(String text, String pattern) {
        int[] bc = badCharacterHeuristic(pattern);
        int shift = 0;
        while (shift <= text.length() - pattern.length()) {
            int j;
            for (j = pattern.length() - 1; j >= 0; j--) {
                if (text.charAt(shift + j) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j < 0) {
                System.out.println("Pattern found at " + (shift));
//                pattern.length() is the original shift then we will move according to bc.
//                if character not found in bc then shift will be moved by pattern.length()+1; moved past to that
//                character.
//                if condition is for the case when shift==text.length()-pattern.length() then we will get index out
//                of bounds. Adding plus 1 isi think not mandatory because it will exist from loop anyways.
                shift += (shift + pattern.length()) < text.length() ? pattern.length() - bc[text.charAt(shift + pattern.length()) - 'A'] : 1;
            } else {
//                if the mismatched character is in the right side of the matched pattern we can get negative no.
//                ex ACD..... and ACBD here D is on the right side of the mismatched position.
                shift += Math.max(1, j - bc[text.charAt(shift + j) - 'A']);
            }
        }
    }
}
