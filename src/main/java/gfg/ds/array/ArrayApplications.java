package gfg.ds.array;

import utils.Pair;
import utils.Utils;

import java.util.*;
import java.util.stream.Collectors;

/** @noinspection WeakerAccess */
public class ArrayApplications {
  /** t=O(n) */
  public static List<Integer> getLeaders(int arr[]) {
    if (arr.length == 0) {
      return new ArrayList<>();
    }

    List<Integer> leaders = new ArrayList<>();
    leaders.add(arr[arr.length - 1]);

    int lastLeader = arr[arr.length - 1];
    for (int i = arr.length - 2; i >= 0; i--) {
      if (arr[i] > lastLeader) {
        leaders.add(arr[i]);
        lastLeader = arr[i];
      }
    }

    Collections.reverse(leaders);

    return leaders;
  }

  /**
   * t=O(n) We can also use hashing or sorting. But hashing requires extra space and sorting
   * increase the time complexity.
   */
  public static int getMajorityElement(int[] arr) {
    assert arr.length != 0;

    int candidateIndex = getMooreVotingCandidate(arr);

    return isMajority(arr, arr[candidateIndex]) ? arr[candidateIndex] : -1;
  }

  private static int getMooreVotingCandidate(int[] arr) {
    int candidateIndex = 0;
    int count = 1;
    for (int i = 1; i < arr.length; i++) {
      count += arr[i] == arr[candidateIndex] ? 1 : -1;
      if (count == 0) {
        candidateIndex = i;
        count = 1;
      }
    }
    return candidateIndex;
  }

  private static boolean isMajority(int[] arr, int value) {
    int count = 0;
    for (int arrVal : arr) {
      if (arrVal == value) {
        count++;
      }
    }
    return count > arr.length / 2;
  }

  /** t=O(log n) */
  public static Optional<Pair<Integer, Integer>> findPairWithSum(int[] arr, int sum) {
    if (arr.length == 0) {
      return Optional.empty();
    }

    Arrays.sort(arr);

    for (int left = 0, right = arr.length - 1; left < right; ) {
      int currSum = arr[left] + arr[right];

      if (currSum == sum) {
        return Optional.of(new Pair<>(arr[left], arr[right]));
      }
      if (currSum > sum) {
        right--;
      } else {
        left++;
      }
    }

    return Optional.empty();
  }

  /**
   * t=O(n) `arr` should have only one value that occur odd number of times for this logic to work.
   */
  public static int getOddTimesOccurringValue(int[] arr) {
    assert arr.length != 0;

    int xorSum = arr[0];
    for (int i = 1; i < arr.length; i++) {
      xorSum = xorSum ^ arr[i];
    }

    return xorSum;
  }

  /**
   * t=O(n) Also known as Kadane's Algorithm It will behave unexpectedly if there is no sub array
   * with positive sum.
   */
  public static MaxSumSubarray getMaxSumSubarray(int arr[]) {
    assert arr.length != 0;

    int subArrStart = 0;
    int subArrEnd = 0;
    int positiveSumStart = 0;
    int maxSoFar = 0;
    int maxTillHere = 0;
    for (int i = 0; i < arr.length; i++) {
      maxTillHere += arr[i];
      if (maxTillHere < 0) {
        maxTillHere = 0;
        positiveSumStart = i + 1;
      } else if (maxTillHere > maxSoFar) {
        maxSoFar = maxTillHere;
        subArrStart = positiveSumStart;
        subArrEnd = i;
      }
    }

    return new MaxSumSubarray(subArrStart, subArrEnd, maxSoFar);
  }

  public static class MaxSumSubarray {
    private int startIndex;
    private int endIndex;
    private int sum;

    public MaxSumSubarray(int startIndex, int endIndex, int sum) {
      this.startIndex = startIndex;
      this.endIndex = endIndex;
      this.sum = sum;
    }

    public int getStartIndex() {
      return startIndex;
    }

    public int getEndIndex() {
      return endIndex;
    }

    public int getSum() {
      return sum;
    }
  }

  /** t=O(n) We can also use xor sum to solve this problem */
  public static int findMissingNumber(int[] arr, int limit) {
    assert arr.length == limit - 1;
    for (int val : arr) {
      assert val <= limit;
    }

    int sum = limit * (limit + 1) / 2;
    for (int val : arr) {
      sum -= val;
    }

    return sum;
  }

  /** t=O(log n) */
  public static boolean searchInRotatedSortedArr(int[] arr, int key) {
    assert arr.length != 0;
    // Think of how this algo will search 0 in the array: {2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}. It
    // is not possible
    // currently.
    assert new HashSet<>(Arrays.stream(arr).boxed().collect(Collectors.toList())).size()
            == arr.length
        : "All elements should be distinct";

    int rotationPoint = findRotationPointInRotatedAndSortedArray(arr);
    if (rotationPoint == -1) {
      // No rotation point so array is just sorted
      assert Utils.isNonDecreasing(arr);

      return Arrays.binarySearch(arr, 0, arr.length, key) >= 0;
    }

    if (key == arr[rotationPoint]) {
      return true;
    }
    if (key >= arr[0]) {
      return Arrays.binarySearch(arr, 0, rotationPoint - 1, key) >= 0;
    }
    return Arrays.binarySearch(arr, rotationPoint + 1, arr.length, key) >= 0;
  }

  private static int findRotationPointInRotatedAndSortedArray(int[] arr) {
    int start = 0;
    int end = arr.length - 1;

    while (start < end) {
      int mid = (start + end + 1) / 2;
      if (mid != end && arr[mid + 1] < arr[mid]) {
        return mid + 1;
      }
      if (mid != start && arr[mid - 1] > arr[mid]) {
        return mid;
      }

      if (arr[mid] >= arr[start]) {
        start = mid + 1;
      } else {

        end = mid - 1;
      }
    }
    return -1;
  }

  public static Integer[] merge(Integer[] arrWithNulls, Integer[] fillerArr) {
    assert arrWithNulls != null && fillerArr != null;

    int nullsCount = getNullsCount(arrWithNulls);
    assert nullsCount == fillerArr.length;

    if (nullsCount == arrWithNulls.length) {
      System.arraycopy(fillerArr, 0, arrWithNulls, 0, fillerArr.length);
      return arrWithNulls;
    }

    shiftNonNullsToBack(arrWithNulls);
    int fillerArrPtr = 0;
    int arrWithNullsPtr = fillerArr.length;

    int i = 0;
    while (i < arrWithNulls.length) {
      boolean arrWithNullsPtrExhausted = arrWithNullsPtr == arrWithNulls.length;
      if (arrWithNullsPtrExhausted
          || (fillerArrPtr < fillerArr.length
              && fillerArr[fillerArrPtr] < arrWithNulls[arrWithNullsPtr])) {
        arrWithNulls[i++] = fillerArr[fillerArrPtr++];
      } else {
        arrWithNulls[i++] = arrWithNulls[arrWithNullsPtr++];
      }
    }

    return arrWithNulls;
  }

  private static int getNullsCount(Integer[] arrWithNulls) {
    int nulls = 0;
    for (Integer val : arrWithNulls) {
      if (val == null) {
        nulls++;
      }
    }
    return nulls;
  }

  private static void shiftNonNullsToBack(Integer[] arrWitNulls) {
    int j = arrWitNulls.length - 1;
    for (int i = arrWitNulls.length - 1; i >= 0; i--) {
      if (arrWitNulls[i] != null) {
        arrWitNulls[j--] = arrWitNulls[i];
      }
    }
  }

  /** t=O(log n) Get median of two sorted arrays of equal length after merging */
  public static double getMedian(int sortedArr1[], int sortedArr2[]) {
    assert sortedArr1 != null
        && sortedArr2 != null
        && sortedArr1.length != 0
        && sortedArr2.length != 0;
    assert sortedArr1.length == sortedArr2.length;
    assert Utils.isNonDecreasing(sortedArr1) && Utils.isNonDecreasing(sortedArr2);

    if (sortedArr1.length == 1) {
      return (sortedArr1[0] + sortedArr2[0]) / 2.0;
    }

    return getMedianUtil(
        sortedArr1, 0, sortedArr1.length - 1, sortedArr2, 0, sortedArr2.length - 1);
  }

  /** `end1` and `end2` are inclusive */
  private static double getMedianUtil(
      int sortedArr1[], int start1, int end1, int sortedArr2[], int start2, int end2) {
    int size1 = end1 - start1 + 1;
    int size2 = end2 - start2 + 1;
    assert size1 == size2;

    if (size1 == 2) {
      return (Math.max(sortedArr1[start1], sortedArr2[start2])
              + Math.min(sortedArr1[end1], sortedArr2[end2]))
          / 2.0;
    }

    double median1 = Utils.getMedian(sortedArr1, start1, end1);
    double median2 = Utils.getMedian(sortedArr2, start2, end2);
    if (median1 == median2) {
      return median1;
    }
    if (median1 < median2) {
      if (size1 % 2 == 0) {
        return getMedianUtil(
            sortedArr1, start1 + size1 / 2, end1, sortedArr2, start2, start2 + (size2 / 2) - 1);
      } else {
        return getMedianUtil(
            sortedArr1, start1 + size1 / 2, end1, sortedArr2, start2, start2 + (size2 / 2));
      }
    }
    if (size1 % 2 == 0) {
      return getMedianUtil(
          sortedArr1, start1, start1 + (size1 / 2) - 1, sortedArr2, start2 + size2 / 2, end2);
    } else {
      return getMedianUtil(
          sortedArr1, start1, start1 + (size1 / 2), sortedArr2, start2 + size2 / 2, end2);
    }
  }

  /** t=O(n) */
  public static int[] rotateByJuggling(int[] arr, int rotateBy) {
    assert arr.length >= rotateBy;

    if (arr.length == rotateBy) {
      return arr;
    }

    int gcd = Utils.gcd(arr.length, rotateBy);
    for (int start = 0; start < gcd; start++) {
      int valueAtStart = arr[start];
      int curr = start;
      while (true) {
        int next = nextPositionInCyclicArray(curr, rotateBy, arr);
        if (next == start) {
          break;
        }
        arr[curr] = arr[next];
        curr = next;
      }
      arr[curr] = valueAtStart;
    }
    return arr;
  }

  private static int nextPositionInCyclicArray(int start, int distance, int[] arr) {
    int next = start + distance;
    return next >= arr.length ? next - arr.length : next;
  }

  /** t=O(n) */
  public static int[] rotate(int[] arr, int rotateBy) {
    assert arr.length >= rotateBy;

    if (arr.length == rotateBy) {
      return arr;
    }
    reverse(arr, 0, rotateBy);
    reverse(arr, rotateBy, arr.length - rotateBy);
    reverse(arr, 0, arr.length);
    return arr;
  }

  private static void reverse(int arr[], int start, int size) {
    assert start + size <= arr.length;

    for (int i = start; i < start + (size / 2); i++) {
      int temp = arr[i];
      arr[i] = arr[2 * start + size - 1 - i];
      arr[2 * start + size - 1 - i] = temp;
    }
  }

  /** t=O(n) */
  public static int[] rotateByBlockSwap(int arr[], int rotateBy) {
    assert arr.length >= rotateBy;

    rotateByBlockSwapUtil(arr, 0, rotateBy, arr.length);

    return arr;
  }

  private static void rotateByBlockSwapUtil(int arr[], int start, int rotateBy, int size) {
    if (size == 0 || rotateBy == size) {
      return;
    }

    if (size - rotateBy == rotateBy) {
      swap(arr, start, start + size - rotateBy, rotateBy);
      return;
    }

    if (rotateBy < size - rotateBy) {
      swap(arr, start, start + size - rotateBy, rotateBy);
      rotateByBlockSwapUtil(arr, start, rotateBy, size - rotateBy);
    } else {
      swap(arr, start, rotateBy, size - rotateBy);
      rotateByBlockSwapUtil(
          arr, start + (size - rotateBy), rotateBy - (size - rotateBy), size - (size - rotateBy));
    }
  }

  private static void swap(int[] arr, int srcIndex, int destIndex, int count) {
    for (int i = 0; i < count; i++) {
      int temp = arr[destIndex + i];
      arr[destIndex + i] = arr[srcIndex + i];
      arr[srcIndex + i] = temp;
    }
  }

  /** t=O(n) */
  public static int getMaxSumWithNoTwoElementsAdjacent(int arr[]) {
    int adjMax = 0;
    int adjNextMax = 0;

    for (int i = arr.length - 1; i >= 0; i--) {
      int currMax = Math.max(adjMax, arr[i] + adjNextMax);
      adjNextMax = adjMax;
      adjMax = currMax;
    }

    return adjMax;
  }

  public static int[] sortByFrequency(int arr[]) {
    Map<Integer, Pair<Integer, Integer>> valToIndexAndCountPair = new HashMap<>();
    for (int i = 0; i < arr.length; i++) {
      Pair<Integer, Integer> indexAndCountPair =
          valToIndexAndCountPair.getOrDefault(arr[i], new Pair<>(i, 0));
      indexAndCountPair.value++; // incrementing the count
      valToIndexAndCountPair.put(arr[i], indexAndCountPair);
    }

    List<Integer> uniqueValuesList = new ArrayList<>(valToIndexAndCountPair.keySet());
    uniqueValuesList.sort(
        (o1, o2) -> {
          Pair<Integer, Integer> indexAndCountPair1 = valToIndexAndCountPair.get(o1);
          Pair<Integer, Integer> indexAndCountPair2 = valToIndexAndCountPair.get(o2);

          return indexAndCountPair1.value.equals(indexAndCountPair2.value)
              ? indexAndCountPair1.key - indexAndCountPair2.key
              : indexAndCountPair2.value - indexAndCountPair1.value;
        });

    for (int i = 0, j = 0; i < uniqueValuesList.size(); i++) {
      int val = uniqueValuesList.get(i);
      Pair<Integer, Integer> indexAndCountPair = valToIndexAndCountPair.get(val);
      for (int k = 0; k < indexAndCountPair.value; k++) {
        arr[j++] = val;
      }
    }

    return arr;
  }

  /** t=O(n*log n) s=O(n) */
  public static int getInversionCount(int[] arr) {
    return getInversionCountUtil(arr, 0, arr.length - 1);
  }

  /** `end` is inclusive */
  private static int getInversionCountUtil(int[] arr, int start, int end) {
    if (start == end) {
      return 0;
    }

    int mid = start + (end - start + 1) / 2;
    int lefInversions = getInversionCountUtil(arr, start, mid - 1);
    int rightInversions = getInversionCountUtil(arr, mid, end);

    return lefInversions + rightInversions + getInversionCountAtBoundary(arr, start, mid, end);
  }

  private static int getInversionCountAtBoundary(int[] arr, int start, int mid, int end) {
    int temp[] = new int[end - start + 1];
    int inversions = 0;

    int i = start;
    int j = mid;
    int k = 0;
    while (i <= mid - 1 && j <= end) {
      if (arr[i] > arr[j]) {
        temp[k++] = arr[j++];
        inversions += mid - i;
      } else {
        temp[k++] = arr[i++];
      }
    }
    while (i <= mid - 1) {
      temp[k++] = arr[i++];
    }

    while (j <= end) {
      temp[k++] = arr[j++];
    }

    System.arraycopy(temp, 0, arr, start, temp.length);
    return inversions;
  }
}
