package geeks_for_geeks.algo.greedy_algorithms;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-10-2018 22:27
 **/
public class ActivitySelection {

    /**
     * We assume finish time is sorted
     *
     * @param startTime
     * @param finishTime
     */
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
