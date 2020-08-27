package gfg.ds.queue;

import java.util.ArrayDeque;

/** @noinspection WeakerAccess */
public class Applications {

  public static class PetrolPump {
    int petrol;
    int nextPetrolPumpDistance;

    public PetrolPump(int petrol, int nextPetrolPumpDistance) {
      this.petrol = petrol;
      this.nextPetrolPumpDistance = nextPetrolPumpDistance;
    }

    @Override
    public String toString() {
      return "PetrolPump{"
          + "petrol="
          + petrol
          + ", nextPetrolPumpDistance="
          + nextPetrolPumpDistance
          + '}';
    }
  }

  /**
   * t=O(n); enqueue is done at-most two times. consider a example
   * (1,1)->(2,2)->(3,3)->(1,0)->(1,2). so when we start from (1,1) and fail at (1,0) we can't
   * succeed from any point between them. Because if we start at (1,1) worst case we don't stock any
   * extra petrol so it is negligible. So by starting from any node it will result in a failure at
   * (1,0) so we an just check from (1,2) which make time complexity linear.
   *
   * @param arr array of petrol pumps
   * @return index of petrol pump from which the tour starts.
   */
  public static int getTour(PetrolPump[] arr) {
    if (arr.length == 0) {
      return -1;
    }
    int start = 0;
    int next = 1;
    ArrayDeque<PetrolPump> queue = new ArrayDeque<>();
    int currentPetrol = arr[start].petrol - arr[start].nextPetrolPumpDistance;
    queue.add(new PetrolPump(arr[start].petrol, arr[start].nextPetrolPumpDistance));
    for (; start != next || currentPetrol < 0; next = (next + 1) % arr.length) {
      // If petrol is less than zero move the starting point.
      for (; start != next && currentPetrol < 0; ) {
        PetrolPump curr = queue.peek();
        assert curr != null;
        currentPetrol -= curr.petrol - curr.nextPetrolPumpDistance;
        start = (start + 1) % arr.length;
        queue.poll();
        if (start == 0) {
          return -1;
        }
      }
      currentPetrol += arr[next].petrol - arr[next].nextPetrolPumpDistance;
      queue.add(new PetrolPump(arr[next].petrol, arr[next].nextPetrolPumpDistance));
    }
    return start;
  }

  /** t=O(n); enqueue at most two times. */
  @SuppressWarnings("ConstantConditions")
  public static int[] slidingWindowMaximum(int[] arr, int windowSize) {
    assert arr.length >= windowSize : "Array length can't be less than window size";
    assert windowSize >= 1 : "Window size can't be less than 1";
    // Q's front will always have the biggest element of the current window.
    ArrayDeque<Integer> queue = new ArrayDeque<>();
    for (int i = 0; i < windowSize; i++) {
      while (!queue.isEmpty() && arr[queue.peekLast()] < arr[i]) {
        queue.pollLast();
      }
      queue.add(i);
    }
    int slidingWindowMaxArr[] = new int[arr.length - windowSize + 1];
    for (int i = windowSize, j = 0; i < arr.length; i++) {
      slidingWindowMaxArr[j++] = arr[queue.peek()];
      // removing elements which are out of window. For this step we are saving the indexes. With
      // the indexes it is
      // easy
      while (!queue.isEmpty() && queue.peek() <= i - windowSize) {
        queue.poll();
      }
      while (!queue.isEmpty() && arr[queue.peekLast()] < arr[i]) {
        queue.pollLast();
      }
      queue.add(i);
    }
    slidingWindowMaxArr[slidingWindowMaxArr.length - 1] = arr[queue.peek()];
    return slidingWindowMaxArr;
  }

  /** t=O(1) It will generate binary numbers starting from 1 till n; */
  public static String[] generateBinaryNums(int n) {
    assert n >= 0;

    ArrayDeque<String> q = new ArrayDeque<>();
    q.offer("1");
    String[] ans = new String[n];
    int i = 0;
    while (n-- > 0) {
      String curr = q.poll();
      ans[i++] = curr;
      q.offer(curr + "0");
      q.offer(curr + "1");
    }
    return ans;
  }
}
