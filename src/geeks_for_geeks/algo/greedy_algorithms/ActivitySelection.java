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

    public static void main(String[] args) {
        int s[] = {1, 3, 0, 5, 8, 5};
        int f[] = {2, 4, 6, 7, 9, 9};
        activitySelection(s, f);
    }
}
