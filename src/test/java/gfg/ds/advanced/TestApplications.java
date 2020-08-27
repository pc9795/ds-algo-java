package gfg.ds.advanced;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class TestApplications {

  @Test
  void testCartesianTreeSort() {
    List<Integer> actual = Applications.cartesianTreeSort(Arrays.asList(5, 10, 40, 30, 28));
    List<Integer> expected = Arrays.asList(5, 10, 28, 30, 40);

    assert actual.equals(expected);
  }
}
