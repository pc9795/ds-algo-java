package geeks_for_geeks.ds.advanced.suffix_tree;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-02-2020 12:13
 **/
public class Applications {
    /**
     * Longest common prefix.
     * We can search a pattern in m + log n using LCP array
     * LCP array is an array of size n. A value lcp[i] indicates length of the longest common prefix of the suffixes
     * indexed by suffix[i] and suffix[i+1]
     * Below is Kasai's algorithm to construct suffix array.
     *
     * @param input input word
     * @return lcp array
     */
    public static int[] buildLCPArray(String input) {
        int n = input.length();
        int suffixArr[] = SuffixArray.buildSuffixArray2(input);
        //Inverse suffix array. To know the position of a particular suffix in suffix array.
        int suffixArrInv[] = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArrInv[suffixArr[i]] = i;
        }

        int lcp[] = new int[n];
        int k = 0;
        for (int i = 0; i < n; i++) {
            int suffixArrPos = suffixArrInv[i];
            //LCP for last position is not defined.
            if (suffixArrPos == n - 1) {
                continue;
            }
            //Next suffix stored in suffix array.
            int j = suffixArr[suffixArrPos + 1];
            while ((i + k < n) && (j + k < n) && (input.charAt(i + k) == input.charAt(j + k))) {
                k++;
            }

            lcp[suffixArrPos] = k;
            //Reducing k for next prefix
            if (k > 0) {
                k--;
            }
        }
        return lcp;
    }
}
