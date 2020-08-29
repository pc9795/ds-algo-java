package gfg.algo.searching_and_sorting;

public class SortingProblems {
  public static void minimumLengthUnsortedSubarray(int arr[]) {
    int start = 0;
    int end = arr.length - 1;
    int i = 0;
    for (; i < arr.length - 1; i++) {
      if (arr[i + 1] < arr[i]) {
        start = i;
        break;
      }
    }
    if (i == arr.length - 1) {
      System.out.println("Array is sorted!");
      return;
    }
    for (i = arr.length - 1; i > 0; i--) {
      if (arr[i - 1] > arr[i]) {
        end = i;
        break;
      }
    }
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    for (i = start; i <= end; i++) {
      if (arr[i] > max) {
        max = arr[i];
      }
      if (arr[i] < min) {
        min = arr[i];
      }
    }
    for (i = 0; i < arr.length; i++) {
      if (arr[i] > min) {
        start = i;
        break;
      }
    }
    for (i = arr.length - 1; i >= 0; i--) {
      if (arr[i] < max) {
        end = i;
        break;
      }
    }
    System.out.println("Start:" + start + ", End:" + end);
  }
}
