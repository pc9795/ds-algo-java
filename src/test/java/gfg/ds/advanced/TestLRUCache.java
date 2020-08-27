package gfg.ds.advanced;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class TestLRUCache {

  @Test
  void testRefer() {
    LRUCache cache = new LRUCache(4);
    cache.refer(1);
    cache.refer(2);
    cache.refer(3);
    cache.refer(1);
    cache.refer(4);
    cache.refer(5);

    assert cache.getContents().equals(Arrays.asList(3, 1, 4, 5));
  }
}
