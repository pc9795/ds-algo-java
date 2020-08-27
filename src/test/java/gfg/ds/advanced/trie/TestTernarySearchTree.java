package gfg.ds.advanced.trie;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TestTernarySearchTree {

  @Test
  void testInsert() {
    TernarySearchTree trie = new TernarySearchTree();
    List<String> words = Arrays.asList("cat", "cats", "up", "bug");
    for (String word : words) {
      trie.insert(word);
    }

    Set<String> keys = trie.keys();
    assert keys.equals(new HashSet<>(words));
  }

  @Test
  void testSearch() {
    TernarySearchTree trie = new TernarySearchTree();
    List<String> words = Arrays.asList("cat", "cats", "up", "bug");
    for (String word : words) {
      trie.insert(word);
    }

    for (String word : words) {
      assert trie.search(word);
    }
  }
}
