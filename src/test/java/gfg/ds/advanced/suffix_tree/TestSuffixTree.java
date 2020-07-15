package gfg.ds.advanced.suffix_tree;

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
