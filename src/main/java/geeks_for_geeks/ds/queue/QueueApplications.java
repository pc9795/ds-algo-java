package geeks_for_geeks.ds.queue;

import java.util.ArrayDeque;

/**
 * Created By: Prashant Chaubey
 * Created On: 08-10-2018 22:21
 **/
public class QueueApplications {

    static class PetrolPump {
        int petrol;
        int nextPetrolPumpDistance;

        public PetrolPump(int petrol, int nextPetrolPumpDistance) {
            this.petrol = petrol;
            this.nextPetrolPumpDistance = nextPetrolPumpDistance;
        }

        @Override
        public String toString() {
            return "PetrolPump{" +
                    "petrol=" + petrol +
                    ", nextPetrolPumpDistance=" + nextPetrolPumpDistance +
                    '}';
        }
    }

    /**
     * t=O(n); enqueue is done at-most two times.
     *
     * @param arr
     * @return
     */
    public static int printTour(PetrolPump[] arr) {
        if (arr.length == 0) {
            throw new RuntimeException("Input is emtpy!");
        }
        int start = 0;
        int end = 1;
        ArrayDeque<PetrolPump> queue = new ArrayDeque<>();
        int currentPetrol = arr[start].petrol - arr[start].nextPetrolPumpDistance;
        queue.add(new PetrolPump(arr[start].petrol, arr[start].nextPetrolPumpDistance));
        for (; start != end || currentPetrol < 0; end = (end + 1) % arr.length) {
//            If petrol is less than zero move the starting point.
            for (; start != end && currentPetrol < 0; ) {
                currentPetrol -= queue.peek().petrol - queue.peek().nextPetrolPumpDistance;
                start = (start + 1) % arr.length;
                queue.poll();
                if (start == 0) {
                    return -1;
                }
            }
            currentPetrol += arr[end].petrol - arr[end].nextPetrolPumpDistance;
            queue.add(new PetrolPump(arr[end].petrol, arr[end].nextPetrolPumpDistance));

        }
        return start;
    }

    /**
     * t=O(n); enqueue at most two times.
     *
     * @param arr
     * @param windowSize
     */
    public static void slidingWindowMaximum(int[] arr, int windowSize) {
        assert arr != null;
        assert arr.length >= windowSize;
        //Q's front will always have the biggest element of the current window.
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < windowSize; i++) {
            while (!queue.isEmpty() && arr[queue.peekLast()] < arr[i]) {
                queue.pollLast();
            }
            queue.add(i);
        }
        for (int i = windowSize; i < arr.length; i++) {
            System.out.print(arr[queue.peek()]+" ");
//            removing elements which are out of window. For this step we are saving the indexes. With the indexes it is
//            easy
            while (!queue.isEmpty() && queue.peek() <= i - windowSize) {
                queue.poll();
            }
            while (!queue.isEmpty() && arr[queue.peekLast()] < arr[i]) {
                queue.pollLast();
            }
            queue.add(i);
        }
        System.out.print(arr[queue.peek()]);
    }
}
