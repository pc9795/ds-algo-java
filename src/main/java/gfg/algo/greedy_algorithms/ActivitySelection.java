package gfg.algo.greedy_algorithms;

import java.util.ArrayList;
import java.util.List;

public class ActivitySelection {

  /** We assume finish time is sorted */
  public static List<Integer> activitySelection(int[] startTime, int[] finishTime) {
    if (startTime.length == 0) {
      return List.of();
    }
    int i = 0;
    List<Integer> activities = new ArrayList<>();
    activities.add(0);
    for (int j = 1; j < finishTime.length; j++) {
      if (startTime[j] >= finishTime[i]) {
        activities.add(j);
        i = j;
      }
    }
    return activities;
  }
}
