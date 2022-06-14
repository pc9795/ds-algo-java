package gfg.algo.pattern_searching;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class AhoCorasickTest {
  @Test
  public void testSearch() {
    Map<String, List<Integer>> occurences =
        AhoCorasick.search("ahishers", List.of("he", "she", "hers", "his"));
    Assertions.assertThat(occurences)
        .isEqualTo(
            Map.of("she", List.of(3), "his", List.of(1), "he", List.of(4), "hers", List.of(4)));
  }
}
