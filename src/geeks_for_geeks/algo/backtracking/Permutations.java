package geeks_for_geeks.algo.backtracking;

/**
 * Created By: Prashant Chaubey
 * Created On: 28-10-2018 02:34
 **/
public class Permutations {
    public static void printPermutations(String text) {
        printPermutationsUtil("", text);
    }

    /**
     * T=O(n!)
     *
     * @param first
     * @param last
     */
    private static void printPermutationsUtil(String first, String last) {
        if (last.length() == 0) {
            System.out.println(first);
            return;
        }
        for (int i = 0; i < last.length(); i++) {
            printPermutationsUtil(first + last.charAt(i), last.substring(0, i) + last.substring(i + 1));
        }
    }

    public static void main(String[] args) {
        printPermutations("ABC");
    }
}
