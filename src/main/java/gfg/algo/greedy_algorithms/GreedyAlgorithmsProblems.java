package gfg.algo.greedy_algorithms;

import utils.Pair;

import java.util.*;

public class GreedyAlgorithmsProblems {
  public static class Job {
    public char id;
    public int deadline, profit;

    public Job(char id, int deadline, int profit) {
      this.id = id;
      this.deadline = deadline;
      this.profit = profit;
    }
  }

  // t=nlogn
  // s=n
  public static List<Job> jobSequencing(List<Job> jobs) {
    jobs.sort(Comparator.comparingInt(o -> o.deadline));
    PriorityQueue<Job> maxHeap = new PriorityQueue<>((o1, o2) -> o2.profit - o1.profit);
    List<Job> result = new ArrayList<>();
    for (int i = jobs.size() - 1; i >= 0; i--) {
      Job job = jobs.get(i);
      int slotsAvailable = i == 0 ? job.deadline : job.deadline - jobs.get(i - 1).deadline;
      maxHeap.add(job);
      while (slotsAvailable > 0 && !maxHeap.isEmpty()) {
        result.add(maxHeap.poll());
        slotsAvailable--;
      }
    }
    result.sort(Comparator.comparingInt(o -> o.deadline));
    return result;
  }

  // t=n
  public static int minimumNumberOfCoins(int amount) {
    TreeSet<Integer> denominations = new TreeSet<>(Set.of(1, 2, 5, 10, 20, 50, 100, 500, 1000));
    int count = 0;
    while (amount > 0) {
      amount -= denominations.floor(amount);
      count++;
    }
    return count;
  }

  // t=nlogn
  // s=n
  public static int minimumNumberOfPlatforms(List<Pair<Integer, Integer>> trainIntervals) {
    trainIntervals.sort(Comparator.comparingInt(o -> o.key));
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o));
    for (Pair<Integer, Integer> interval : trainIntervals) {
      if (!minHeap.isEmpty() && minHeap.peek() < interval.key) {
        minHeap.poll();
      }
      minHeap.add(interval.value);
    }
    return minHeap.size();
  }

  // t=nlogn
  // s=1
  public static int minimumNumberOfPlatforms2(List<Integer> arrivals, List<Integer> departures) {
    int platforms = 0;
    int max = 0;
    arrivals.sort(Comparator.comparingInt(o -> o));
    departures.sort(Comparator.comparingInt(o -> o));

    int len = arrivals.size();
    int i = 0, j = 0;
    while (i < len) {
      if (arrivals.get(i) < departures.get(j)) {
        i++;
        platforms++;
      } else {
        j++;
        platforms--;
      }

      max = Math.max(max, platforms);
    }
    return max;
  }
}
