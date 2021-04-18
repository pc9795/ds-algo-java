package gfg.ds.advanced;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestDiffArray {
  @Test
  void testDiffArrayOperations() {
    int[] arr = {10, 5, 20, 40};
    DiffArray diffArray = new DiffArray(arr, arr.length);
    diffArray.update(0, 1, 10);
    Assertions.assertArrayEquals(diffArray.get(), new int[] {20, 15, 20, 40});

    diffArray.update(0, 3, 5);
    Assertions.assertArrayEquals(diffArray.get(), new int[] {25, 20, 25, 45});
  }
}
