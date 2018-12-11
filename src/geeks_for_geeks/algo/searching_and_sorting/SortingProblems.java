package geeks_for_geeks.algo.searching_and_sorting;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-11-2018 23:25
 **/
public class SortingProblems {
    public static void minimumLengthUnsortedSubarray(int arr[]) {
//            TODO: verification.
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


    public static void main(String[] args) {
        int arr[] = new int[]{10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60};
        int arr2[] = new int[]{0, 1, 15, 25, 6, 7, 30, 40, 50};
        minimumLengthUnsortedSubarray(arr2);
    }
}
