package gfg.ds.array;

import utils.Pair;
import utils.Utils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created By: Prashant Chaubey
 * Created On: 04-10-2018 02:09
 **/
public class Applications {
    /**
     * t=O(n)
     */
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
     * t=O(n)
     * We can also use hashing or sorting. But hashing requires extra space and sorting increase the time complexity.
     */
    public static int getMajorityElement(int[] arr) {
        assert arr != null && arr.length != 0;

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

    /**
     * t=O(log n)
     */
    public static Optional<Pair<Integer, Integer>> findPairWithSum(int[] arr, int sum) {
        assert arr != null;

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
     * t=O(n)
     * `arr` should have only one value that occur odd number of times for this logic to work.
     */
    public static int getOddTimesOccuringValue(int[] arr) {
        assert arr != null && arr.length != 0;

        int xorSum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            xorSum = xorSum ^ arr[i];
        }

        return xorSum;
    }

    /**
     * t=O(n)
     * Also known as Kadane's Algorithm
     * It will behave unexpectedly if there is no sub array with positive sum.
     */
    public static Pair<Integer, Integer> getMaxSumContiguousSubarray(int arr[]) {
        assert arr != null && arr.length != 0;

        int subArrStart = 0;
        int subArrEnd = 0;
        int positiveSumStart = 0;
        int maxSoFar = arr[0];
        int maxTillHere = arr[0];
        for (int i = 1; i < arr.length; i++) {
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

        return Pair.of(subArrStart, subArrEnd);
    }

    /**
     * t=O(n)
     * We can also use xor sum to solve this problem
     */
    public static int findMissingNumber(int[] arr, int limit) {
        assert arr != null;
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

    /**
     * t=O(logn)
     */
    public static boolean searchInRotatedSortedArr(int[] arr, int key) {
        assert arr != null && arr.length != 0;
        // Think of how this algo will search 0 in the array: {2, 0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}. It is not possible
        // currently.
        assert new HashSet<>(Arrays.stream(arr).boxed().collect(Collectors.toList())).size() == arr.length : "All elements should be distinct";

        int rotationPoint = findRotationPointInRotatedAndSortedArray(arr);
        if (rotationPoint == -1) {
            //No rotation point so array is just sorted
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
            if (arrWithNullsPtrExhausted || (fillerArrPtr < fillerArr.length && fillerArr[fillerArrPtr] < arrWithNulls[arrWithNullsPtr])) {
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

    /**
     * t=O(log n)
     * Get median of two sorted arrays of equal length after merging
     */
    public static double getMedian(int sortedArr1[], int sortedArr2[]) {
        assert sortedArr1 != null && sortedArr2 != null && sortedArr1.length != 0 && sortedArr2.length != 0;
        assert sortedArr1.length == sortedArr2.length;
        assert Utils.isNonDecreasing(sortedArr1) && Utils.isNonDecreasing(sortedArr2);

        if (sortedArr1.length == 1) {
            return (sortedArr1[0] + sortedArr2[0]) / 2.0;
        }

        return getMedianUtil(sortedArr1, 0, sortedArr1.length - 1, sortedArr2, 0, sortedArr2.length - 1);
    }

    /**
     * `end1` and `end2` are inclusive
     */
    private static double getMedianUtil(int sortedArr1[], int start1, int end1, int sortedArr2[], int start2, int end2) {
        int size1 = end1 - start1 + 1;
        int size2 = end2 - start2 + 1;
        assert size1 == size2;

        if (size1 == 2) {
            return (Math.max(sortedArr1[start1], sortedArr2[start2]) + Math.min(sortedArr1[end1], sortedArr2[end2])) / 2.0;
        }

        double median1 = Utils.getMedian(sortedArr1, start1, end1);
        double median2 = Utils.getMedian(sortedArr2, start2, end2);
        if (median1 == median2) {
            return median1;
        }
        if (median1 < median2) {
            if (size1 % 2 == 0) {
                return getMedianUtil(sortedArr1, start1 + size1 / 2, end1, sortedArr2, start2, start2 + (size2 / 2) - 1);
            } else {
                return getMedianUtil(sortedArr1, start1 + size1 / 2, end1, sortedArr2, start2, start2 + (size2 / 2));
            }
        }
        if (size1 % 2 == 0) {
            return getMedianUtil(sortedArr1, start1, start1 + (size1 / 2) - 1, sortedArr2, start2 + size2 / 2, end2);
        } else {
            return getMedianUtil(sortedArr1, start1, start1 + (size1 / 2), sortedArr2, start2 + size2 / 2, end2);
        }
    }


    /**
     * t=O(n)
     */
    public static int[] rotateByJuggling(int[] arr, int rotateBy) {
        assert arr != null;
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

    /**
     * t=O(n)
     */
    public static int[] rotate(int[] arr, int rotateBy) {
        assert arr != null;
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
}
