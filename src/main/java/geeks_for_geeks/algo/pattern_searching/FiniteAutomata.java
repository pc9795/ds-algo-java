package geeks_for_geeks.algo.pattern_searching;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-11-2018 00:22
 **/
public class FiniteAutomata {
    private static int NUM_OF_CHARS = 5;

    private static int getNextState(String pattern, int currentState, char currentCharacter) {
        if (currentState < pattern.length() && pattern.charAt(currentState) == currentCharacter) {
            return currentState + 1;
        }
//      we will check till state 1 because character for each state is present at state-1 position in actual pattern
//        so for 0 we will get -1
        for (int i = currentState; i > 0; i--) {
//            we are doing -1 because actual value in pattern is at state-1
            if (pattern.charAt(i - 1) == currentCharacter) {
//                now we have to search in the actual pattern we will check till i-2 because i-1 we already checked
//                in earlier step.
                int j;
                for (j = 0; j < i - 1; j++) {
//                    actual pattern is currentState + the character after. then we will subtract the matched state
//                    and check lps.
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

    /**
     * T=O(M^3)*NUM_OF_CHARS
     *
     * @param pattern
     * @return
     */
    public static int[][] computeFiniteAutomata(String pattern) {
        int[][] finiteAutomata = new int[pattern.length() + 1][NUM_OF_CHARS];
//        O(M)
        for (int i = 0; i < pattern.length() + 1; i++) {
//            O(NUM_OF_CHARACTERS)
            for (int j = 0; j < NUM_OF_CHARS; j++) {
//                O(M*M)
                finiteAutomata[i][j] = getNextState(pattern, i, (char) ('A' + j));
            }
        }
        return finiteAutomata;
    }


    /**
     * T=O(M)*NUM_OF_CHARS
     *
     * @param pattern
     * @return
     */
    public static int[][] computeFiniteAutomataEfficient(String pattern) {
        int[][] finiteAutomata = new int[pattern.length() + 1][NUM_OF_CHARS];
        int lps = 0;
        finiteAutomata[0][pattern.charAt(0) - 'A'] = 1;
        for (int i = 1; i < pattern.length(); i++) {
            for (int j = 0; j < NUM_OF_CHARS; j++) {
                finiteAutomata[i][j] = finiteAutomata[lps][j];
            }
            finiteAutomata[i][pattern.charAt(i) - 'A'] = i + 1;
            lps = finiteAutomata[lps][pattern.charAt(i) - 'A'];
        }
        for (int j = 0; j < NUM_OF_CHARS; j++) {
            finiteAutomata[pattern.length()][j] = finiteAutomata[lps][j];
        }
        return finiteAutomata;
    }

    /**
     * T=O(N)
     *
     * @param text
     * @param pattern
     */
    public static void search(String text, String pattern) {
//        TODO: validation code
        int[][] finiteAutomata = computeFiniteAutomata(pattern);
        for (int i = 0, state = 0; i < text.length(); i++) {
            state = finiteAutomata[state][text.charAt(i) - 'A'];
            if (state == pattern.length()) {
                System.out.println("Pattern found at:" + (i - pattern.length() + 1));
            }
        }
    }

    public static void main(String[] args) {
        String text = "AABAACAADAABAAABAA";
        String pattern = "AABA";
//        search(text, pattern);
        int[][] fa = computeFiniteAutomataEfficient("ACACADA");
        for (int i = 0; i < fa.length; i++) {
            System.out.println(Arrays.toString(fa[i]));
        }
    }
}
