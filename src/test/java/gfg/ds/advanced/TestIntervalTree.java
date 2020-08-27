package gfg.ds.advanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class TestIntervalTree {
  private IntervalTree intervalTree;

  @BeforeEach
  void setup() {
    intervalTree = new IntervalTree();
    List<Pair<Integer, Integer>> pairList =
        Arrays.asList(
            new Pair<>(15, 20),
            new Pair<>(10, 30),
            new Pair<>(17, 19),
            new Pair<>(5, 20),
            new Pair<>(12, 15),
            new Pair<>(30, 40));

    for (Pair<Integer, Integer> pair : pairList) {
      intervalTree.insert(pair);
    }
  }

  @Test
  void testInsert() {
    assert intervalTree.getRoot().getInterval().equals(new Pair<>(15, 20));
    assert intervalTree.getRoot().getMaxInTree() == 40;

    assert intervalTree.getRoot().getLeft().getInterval().equals(new Pair<>(10, 30));
    assert intervalTree.getRoot().getLeft().getMaxInTree() == 30;

    assert intervalTree.getRoot().getLeft().getLeft().getInterval().equals(new Pair<>(5, 20));
    assert intervalTree.getRoot().getLeft().getLeft().getMaxInTree() == 20;

    assert intervalTree.getRoot().getLeft().getRight().getInterval().equals(new Pair<>(12, 15));
    assert intervalTree.getRoot().getLeft().getRight().getMaxInTree() == 15;

    assert intervalTree.getRoot().getRight().getInterval().equals(new Pair<>(17, 19));
    assert intervalTree.getRoot().getRight().getMaxInTree() == 40;

    assert intervalTree.getRoot().getRight().getRight().getInterval().equals(new Pair<>(30, 40));
    assert intervalTree.getRoot().getRight().getRight().getMaxInTree() == 40;
  }

  @Test
  void testOverlapSearch() {
    Optional<Pair<Integer, Integer>> maybeOverlappingInterval =
        intervalTree.overlapSearch(new Pair<>(6, 7));
    assert maybeOverlappingInterval.isPresent();
    assert maybeOverlappingInterval.get().equals(new Pair<>(5, 20));
  }
}
