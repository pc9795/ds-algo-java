package gfg.ds.advanced.suffix_tree;

import org.junit.jupiter.api.Test;

class TestSuffixTree {

  @Test
  void testSearch() {
    SuffixTree suffixTree = new SuffixTree("banana");
    assert suffixTree.search("nan");
    assert !suffixTree.search("nab");
  }
}
