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

    public static void main(String[] args) {
        PetrolPump[] arr = {new PetrolPump(4, 6), new PetrolPump(6, 5),
                new PetrolPump(7, 3), new PetrolPump(4, 5)};
        System.out.println("position:" + (printTour(arr) + 1));
    }
}
