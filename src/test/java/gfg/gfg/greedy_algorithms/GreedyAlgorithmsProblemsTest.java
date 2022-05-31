package gfg.gfg.greedy_algorithms;

import gfg.algo.greedy_algorithms.GreedyAlgorithmsProblems;
import gfg.algo.greedy_algorithms.GreedyAlgorithmsProblems.Job;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GreedyAlgorithmsProblemsTest {
  @Test
  public void jobSequencingTest() {
    List<Job> jobs =
        new ArrayList<>(
            List.of(
                new Job('a', 2, 100),
                new Job('b', 1, 19),
                new Job('c', 2, 27),
                new Job('d', 1, 25),
                new Job('e', 3, 15)));
    List<Job> result = GreedyAlgorithmsProblems.jobSequencing(jobs);
    Assertions.assertThat(result.stream().map(job -> job.id).collect(Collectors.toList()))
        .isEqualTo(List.of('a', 'c', 'e'));
  }

  @Test
  public void minimumNumberOfCoinsTest() {
    Assertions.assertThat(GreedyAlgorithmsProblems.minimumNumberOfCoins(70)).isEqualTo(2);
    Assertions.assertThat(GreedyAlgorithmsProblems.minimumNumberOfCoins(121)).isEqualTo(3);
  }

  @Test
  public void minimumNumberOfPlatformsTest() {
    List<Pair<Integer, Integer>> trainIntervals =
        new ArrayList<>(
            List.of(
                Pair.of(540, 550),
                Pair.of(580, 720),
                Pair.of(590, 680),
                Pair.of(660, 690),
                Pair.of(900, 1440),
                Pair.of(1080, 1600)));
    Assertions.assertThat(GreedyAlgorithmsProblems.minimumNumberOfPlatforms(trainIntervals))
        .isEqualTo(3);

    trainIntervals = new ArrayList<>(List.of(Pair.of(540, 550), Pair.of(580, 720)));
    Assertions.assertThat(GreedyAlgorithmsProblems.minimumNumberOfPlatforms(trainIntervals))
        .isEqualTo(1);
  }

  @Test
  public void minimumNumberOfPlatforms2Test() {
    List<Integer> arrivals = new ArrayList<>(List.of(540, 580, 590, 660, 900, 1080));
    List<Integer> departures = new ArrayList<>(List.of(550, 720, 680, 690, 1440, 1600));
    Assertions.assertThat(GreedyAlgorithmsProblems.minimumNumberOfPlatforms2(arrivals, departures))
        .isEqualTo(3);

    arrivals = new ArrayList<>(List.of(540, 580));
    departures = new ArrayList<>(List.of(550, 720));
    Assertions.assertThat(GreedyAlgorithmsProblems.minimumNumberOfPlatforms2(arrivals, departures))
        .isEqualTo(1);
  }
}
