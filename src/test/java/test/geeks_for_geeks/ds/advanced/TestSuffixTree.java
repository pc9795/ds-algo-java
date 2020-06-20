package test.geeks_for_geeks.ds.advanced;

import geeks_for_geeks.ds.advanced.suffix_tree.SuffixTree;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-02-2020 13:11
 **/
class TestSuffixTree {

    @Test
    void testSearch() {
        SuffixTree suffixTree = new SuffixTree("banana");
        assert suffixTree.search("nan");
        assert !suffixTree.search("nab");
    }
}
