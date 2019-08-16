package geeks_for_geeks.ds.advanced;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-11-2018 23:27
 **/
public class SuffixArray {

    /**
     * T=(n^2)*logn ; sort*string comparison during sort(O(n))
     *
     * @param input
     * @return
     */
    public static int[] buildSuffixArray(String input) {
        SuffixNode[] arr = new SuffixNode[input.length()];
        for (int i = 0; i < input.length(); i++) {
            arr[i] = new SuffixNode(i, input.substring(i));
        }
        Arrays.sort(arr);
        int suffixArr[] = new int[input.length()];
        for (int i = 0; i < input.length(); i++) {
            suffixArr[i] = arr[i].index;
        }
        return suffixArr;
    }
}

class SuffixNode implements Comparable<SuffixNode> {
    int index;
    String suffix;

    public SuffixNode(int index, String suffix) {
        this.index = index;
        this.suffix = suffix;
    }

    @Override
    public int compareTo(SuffixNode o) {
        return this.suffix.compareTo(o.suffix);
    }
}
