package gfg.ds.advanced.suffix_tree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestSuffixArray {
  @Test
  void testSearch() {
    SuffixArray suffixArray = new SuffixArray("banana");
    assert suffixArray.search("nan") == 2;
  }

  @Test
  void testBuildSuffixArray2() {
    Assertions.assertArrayEquals(
        SuffixArray.buildSuffixArrayEfficient("banana"), new int[] {5, 3, 1, 0, 4, 2});
  }

  @Test
  void testBuildLCPArray() {
    Assertions.assertArrayEquals(
        Applications.buildLCPArray("banana"), new int[] {1, 3, 0, 0, 2, 0});
  }
}
