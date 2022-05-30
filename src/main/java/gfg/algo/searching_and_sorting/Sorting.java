package gfg.algo.searching_and_sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sorting {

  // t=n^2
  // not stable + in-place
  public static void selectionSort(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      int minIndex = i;
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[minIndex] > arr[j]) {
          minIndex = j;
        }
      }
      int temp = arr[i];
      arr[i] = arr[minIndex];
      arr[minIndex] = temp;
    }
  }

  // t=n^2 (best-case=n)
  // stable + in-place

  public static void bubbleSort(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 0; i < arr.length; i++) {
      boolean swapped = false;
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
          swapped = true;
        }
      }
      // Optimization
      if (!swapped) {
        break;
      }
    }
  }

  // t=n^2 (best-case=n)
  // stable + in-place + online
  public static void insertionSort(int[] arr) {
    if (arr == null) {
      return;
    }
    for (int i = 1; i < arr.length; i++) {
      int value = arr[i];
      int j = i;
      for (; j > 0 && arr[j - 1] > value; j--) {
        arr[j] = arr[j - 1];
      }
      arr[j] = value;
    }
  }

  // t=nlogn
  // not stable + in-place
  public static void quickSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    quickSortUtil(0, arr.length - 1, arr);
  }

  private static void quickSortUtil(int low, int high, int[] arr) {
    if (low > high) {
      return;
    }
    int partition = partition(low, high, arr);
    quickSortUtil(low, partition - 1, arr);
    quickSortUtil(partition + 1, high, arr);
  }

  private static int partition(int low, int high, int[] arr) {
    if (low == high) {
      return low;
    }
    int pivot = arr[high];
    int i = low - 1;
    for (int j = low; j < high; j++) {
      if (arr[j] < pivot) {
        int temp = arr[++i];
        arr[i] = arr[j];
        arr[j] = temp;
      }
    }
    int temp = arr[++i];
    arr[i] = pivot;
    arr[high] = temp;
    return i;
  }

  // t=n+k ;k is the range of input
  // s=n+k
  // stable + not in-place
  public static void countSort(int[] arr, int maxValueOfInput) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int[] count = new int[maxValueOfInput + 1];
    for (int j : arr) {
      count[j]++;
    }
    for (int i = 1; i < count.length; i++) {
      count[i] += count[i - 1];
    }
    int[] output = new int[arr.length];
    // to maintain stability.
    for (int i = arr.length - 1; i >= 0; i--) {
      output[count[arr[i]] - 1] = arr[i];
      count[arr[i]]--;
    }
    System.arraycopy(output, 0, arr, 0, arr.length);
  }

  public static void radixSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int max = Arrays.stream(arr).reduce(Integer.MIN_VALUE, Math::max);
    int base = 10;
    for (int place = 0; max / (int) Math.pow(base, place) > 0; place++) {
      countSortByDigit(arr, base, place);
    }
  }

  public static void countSortByDigit(int[] arr, int base, int place) {
    int[] output = new int[arr.length];
    int[] count = new int[base];
    for (int j : arr) {
      int value = (j / (int) Math.pow(base, place)) % 10;
      count[value]++;
    }

    for (int i = 1; i < count.length; i++) {
      count[i] += count[i - 1];
    }

    // to maintain stability.
    for (int i = arr.length - 1; i >= 0; i--) {
      int value = (arr[i] / (int) Math.pow(base, place)) % 10;
      output[count[value] - 1] = arr[i];
      count[value]--;
    }

    System.arraycopy(output, 0, arr, 0, arr.length);
  }

  // t=n^2
  // variation of bubble sort which tries to remove more inversions in a single swap
  public static void combSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int gap = arr.length;
    boolean swapped = true;
    while (gap != 1 || swapped) {
      gap = (gap * 10) / 13;
      if (gap < 1) {
        gap = 1;
      }
      swapped = false;
      for (int i = 0; i < arr.length - gap; i++) {
        if (arr[i] > arr[i + gap]) {
          int temp = arr[i];
          arr[i] = arr[i + gap];
          arr[i + gap] = temp;
          swapped = true;
        }
      }
    }
  }

  // t=n+range
  // we work on range therefore no problem with negative numbers.
  public static void pigeonHoleSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    int min = Integer.MAX_VALUE;
    int max = Integer.MIN_VALUE;
    for (int j : arr) {
      if (j < min) {
        min = j;
      }
      if (j > max) {
        max = j;
      }
    }
    int range = max - min + 1;
    int[] pigeonHoles = new int[range];
    for (int j : arr) {
      pigeonHoles[j - min]++;
    }
    for (int i = 0, index = 0; i < pigeonHoles.length; i++) {
      while (pigeonHoles[i]-- > 0) {
        arr[index++] = i + min;
      }
    }
  }

  // t=n
  // assumes that items are uniformly distributed
  public static void bucketSort(double[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    List<Double>[] bucket = new ArrayList[arr.length];
    for (int i = 0; i < bucket.length; i++) {
      bucket[i] = new ArrayList<>();
    }
    for (double v : arr) {
      bucket[(int) (arr.length * v)].add(v);
    }
    // t=n on average if items are uniformly distributed
    for (List<Double> doubles : bucket) {
      Collections.sort(doubles);
    }
    for (int i = 0, index = 0; i < bucket.length; i++) {
      for (int j = 0; j < bucket[i].size(); j++) {
        arr[index++] = bucket[i].get(j);
      }
    }
  }

  // t=n^2
  // variation of insertion sort
  public static void shellSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    for (int gap = arr.length / 2; gap > 0; gap /= 2) {
      for (int i = gap; i < arr.length; i++) {
        int temp = arr[i];
        int j = i;
        for (; j >= gap && arr[j - gap] > temp; j -= gap) {
          arr[j] = arr[j - gap];
        }
        arr[j] = temp;
      }
    }
  }

  // t=n^2
  // optimizes the number of memory writes
  // in-place + unsatble
  public static void cycleSort(int[] arr) {
    if (arr == null || arr.length == 0) {
      return;
    }
    // we run till n-2 because till here last element must be in its position.
    for (int cycleStart = 0; cycleStart < arr.length - 1; cycleStart++) {
      int item = arr[cycleStart];
      int pos = cycleStart;
      for (int i = cycleStart + 1; i < arr.length; i++) {
        if (arr[i] < item) {
          pos++;
        }
      }
      if (pos == cycleStart) {
        continue;
      }
      if (arr[pos] == item) {
        pos++;
      }
      if (pos != cycleStart) {
        int temp = arr[pos];
        arr[pos] = item;
        item = temp;
      }
      while (pos != cycleStart) {
        pos = cycleStart;
        for (int i = cycleStart + 1; i < arr.length; i++) {
          if (arr[i] < item) {
            pos++;
          }
        }
        if (arr[pos] == item) {
          pos++;
        }
        if (item != arr[pos]) {
          int temp = arr[pos];
          arr[pos] = item;
          item = temp;
        }
      }
    }
  }
}
