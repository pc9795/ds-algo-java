package gfg.algo.greedy_algorithms;

public class ActivitySelection {

  /** We assume finish time is sorted */
  public static void activitySelection(int[] startTime, int[] finishTime) {
    int i = 0;
    System.out.println("Activity no:" + i);
    for (int j = 1; j < finishTime.length; j++) {
      if (startTime[j] >= finishTime[i]) {
        System.out.println("Activity no:" + j);
        i = j;
      }
    }
  }
}
