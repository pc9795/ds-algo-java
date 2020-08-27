package gfg.ds.array;

import org.junit.jupiter.api.Test;

class TestArray {
  @Test
  void testSearch() {
    Array arr = new Array(new int[] {1, 2, 3});

    assert arr.search(2);
    assert !arr.search(4);
  }

  @Test
  void testInsert() {
    Array arr = new Array();

    arr.insert(4);

    assert arr.search(4);
  }

  @Test
  void testDelete() {
    Array arr = new Array(new int[] {7, 8, 9});

    arr.delete(2);

    assert !arr.search(9);
  }

  @Test
  void testReverseOddLength() {
    assert new Array(new int[] {1, 2, 3}).reverse().equals(new Array(new int[] {3, 2, 1}));
  }

  @Test
  void testReverseEvenLength() {
    assert new Array(new int[] {1, 2, 3, 4}).reverse().equals(new Array(new int[] {4, 3, 2, 1}));
  }
}
