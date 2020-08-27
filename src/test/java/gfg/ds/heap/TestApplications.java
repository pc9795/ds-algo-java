package gfg.ds.heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class TestApplications {

  @Test
  void testKLargestElements() {
    int arr[] = {1, 23, 12, 9, 30, 2, 50};
    int k = 3;
    int expected[] = {50, 30, 23};
    int ans[] = Applications.kLargestElements(arr, k);

    // We don't consider ordering.
    // Can check for any library method for this.
    for (int elem : ans) {
      boolean found = false;
      for (int expectedElem : expected) {
        if (elem == expectedElem) {
          found = true;
          break;
        }
      }
      assert found;
    }
  }

  @Test
  void testSortNearlySortedArray() {
    int arr[] = {6, 5, 3, 2, 8, 10, 9};
    Applications.sortNearlySortedArray(arr, 3);
    Assertions.assertArrayEquals(arr, new int[] {2, 3, 5, 6, 8, 9, 10});
  }

  @Test
  void testKMostFrequentWords() {
    String data =
        "Welcome to the world of Geeks "
            + "This portal has been created to provide well written well thought and well explained "
            + "solutions for selected questions If you like Geeks for Geeks and would like to contribute "
            + "here is your chance You can write article and mail your article to contribute at "
            + "geeksforgeeks org See your article appearing on the Geeks for Geeks main page and help "
            + "thousands of other Geeks";
    String[] dataSplitted = data.split(" ");
    List<String> words = Arrays.stream(dataSplitted).collect(Collectors.toList());

    Map<String, Integer> kMostFrequentWords = Applications.kMostFrequentWords(words, 5);

    Map<String, Integer> expected = new HashMap<>();
    expected.put("your", 3);
    expected.put("well", 3);
    expected.put("and", 4);
    expected.put("to", 4);
    expected.put("Geeks", 6);

    assert expected.equals(kMostFrequentWords);
  }
}
