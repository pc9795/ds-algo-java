package geeks_for_geeks.ds.advanced;

import geeks_for_geeks.ds.advanced.suffix_tree.Applications;
import geeks_for_geeks.ds.advanced.suffix_tree.SuffixArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 08-02-2020 22:19
 **/
class TestSuffixArray {
    @Test
    void testSearch() {
        SuffixArray suffixArray = new SuffixArray("banana");
        assert suffixArray.search("nan") == 2;
    }

    @Test
    void testBuildSuffixArray2() {
        Assertions.assertArrayEquals(SuffixArray.buildSuffixArray2("banana"), new int[]{5, 3, 1, 0, 4, 2});
    }

    @Test
    void testBuildLCPArray() {
        Assertions.assertArrayEquals(Applications.buildLCPArray("banana"), new int[]{1, 3, 0, 0, 2, 0});
    }
}