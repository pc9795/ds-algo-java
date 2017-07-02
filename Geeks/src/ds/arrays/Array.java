package ds.arrays;

import java.util.Arrays;

import util.Util;

public class Array {

	public static void main(String[] args) {
		// Integer arr[] = new Integer[] { 1, 2, 3 };
		// Integer arr2[] = new Integer[] { null, 4, 6, 5, null, 6 };
		// Problem
		// Mismatch in algo because of different sizes[odd/even]
		// Util.printArray(Arrays.copyOfRange(arr, 0, arr.length / 2 + 1));
		// Util.printArray(Arrays.copyOfRange(arr, arr.length / 2, arr.length));
		// Util.printArray(Arrays.copyOfRange(arr2, 0, arr2.length / 2));
		// Util.printArray(Arrays.copyOfRange(arr2, arr2.length / 2,
		// arr2.length));
		Integer[] arr = new Integer[] { 1, 20, 6, 4, 5 };
		System.out.println(Array.mergeSort(arr, new Integer[arr.length], 0, arr.length - 1));
		;
	}

	public static int mergeSort(Integer arr[], Integer temp[], int left, int right) {
		System.out.println("left:" + left + " right:" + right);
		int mid, invCount = 0;
		if (left < right) {
			mid = (left + right) / 2;
			invCount += mergeSort(arr, temp, left, mid);
			invCount += mergeSort(arr, temp, mid + 1, right);
			invCount += merge(arr, temp, left, mid + 1, right);

		}
		Util.printArray(arr, left, right);
		return invCount;
	}

	// nlog(n)
	public static int merge(Integer arr[], Integer temp[], int left, int mid, int right) {
		System.out.println("Inside Merge");
		System.out.println("left:" + left + " mid:" + mid + " right:" + right);
		int i = left;
		int j = mid;
		int k = left;
		int invCount = 0;
		for (; i <= mid - 1 && j <= right;) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				// mid-1 is end, i is start therefore mid-1-i, +1 to convert
				// index into value, index in array is -1;
				invCount += mid - 1 - i + 1;
				temp[k++] = arr[j++];
			}
		}
		for (; i <= mid - 1; i++) {
			temp[k++] = arr[i];
		}
		for (; j <= right; temp[k++] = arr[j++])
			;
		System.out.println("Temp Array:");
		Util.printArray(temp, left, right);
		System.out.println("---");
		for (int l = left; l <= right; arr[l] = temp[l], l++)
			;
		return invCount;
	}

	public static void findMaxSum(Integer arr[]) {
		int excl = 0;
		int incl = arr[0];
		int excl_new;
		for (int i = 1; i < arr.length; i++) {
			excl_new = (incl > excl) ? incl : excl;
			incl = excl + arr[i];
			excl = excl_new;
		}
		System.out.println((incl > excl) ? incl : excl);
	}

	// n
	public static void blockSwapRotate(Integer arr[], int start, int end, int d) {
		System.out.println("d:" + d + " start:" + start + " n:" + (end - start) + " n-d:" + (end - start - d));
		Util.printArray(arr);
		if (arr.length == 0 || arr.length == d) {
			return;
		}
		int n = end - start;
		if (d == n - d) {
			System.out.println("Equals");
			blockSwap(arr, start, start + d, n - d);
			System.out.println("AfterSwap");
			Util.printArray(arr);
			System.out.println("---");
			return;
		}
		if (d < n - d) {
			System.out.println("Less");
			blockSwap(arr, start, start + n - d, d);
			System.out.println("AfterSwap");
			Util.printArray(arr);
			System.out.println("---");
			blockSwapRotate(arr, start, n - d, d);
		} else {
			System.out.println("Greater");
			blockSwap(arr, start, start + d, n - d);
			System.out.println("AfterSwap");
			Util.printArray(arr);
			System.out.println("---");
			blockSwapRotate(arr, start + n - d, end, d - (n - d));
		}
	}

	public static void blockSwap(Integer[] arr, int s, int d, int n) {
		System.out.println("In swap s:" + s + " d:" + d + " n:" + n);
		for (int i = 0; i < n; i++) {
			int temp = arr[s + i];
			arr[s + i] = arr[d + i];
			arr[d + i] = temp;
		}
	}

	// n|1
	public static void rotate(Integer arr[], int key) {
		int temp;
		int j;
		int k;
		for (int i = 0; i < Util.gcd(arr.length, key); i++) {
			temp = arr[i];
			j = i;
			for (;;) {
				k = j + key;
				if (k >= arr.length) {
					k -= arr.length;
				}
				if (k == i) {
					break;
				}
				arr[j] = arr[k];
				j = k;
			}
			arr[j] = temp;
		}
	}

	public static int median(Integer arr[]) {
		if (arr.length % 2 != 0) {
			return arr[arr.length / 2];
		} else {
			return (arr[arr.length / 2] + arr[(arr.length / 2) - 1]) / 2;
		}
	}

	// log(n)
	public static int medianTwoSortedArrays(Integer[] arr, Integer[] arr2) {
		Util.printArray(arr);
		Util.printArray(arr2);
		if (arr.length != arr2.length) {
			System.out.println("Length is not same");
			return -1;
		}
		if (arr.length < 1) {
			return -1;
		} else if (arr.length == 1) {
			return (arr[0] + arr2[0]) / 2;
		} else if (arr.length == 2) {
			return (Math.max(arr[0], arr2[0]) + Math.min(arr[1], arr2[1])) / 2;
		} else {
			int median1 = Array.median(arr);
			int median2 = Array.median(arr2);
			if (median1 == median2) {
				return median1;
			} else if (median1 < median2) {
				if (arr.length % 2 == 0) {
					return medianTwoSortedArrays(Arrays.copyOfRange(arr, arr.length / 2, arr.length),
							Arrays.copyOfRange(arr2, 0, arr.length / 2));
				} else {
					return medianTwoSortedArrays(Arrays.copyOfRange(arr, arr.length / 2, arr.length),
							Arrays.copyOfRange(arr2, 0, arr.length / 2 + 1));
				}
			} else {
				if (arr.length % 2 == 0) {
					return medianTwoSortedArrays(Arrays.copyOfRange(arr, 0, arr.length / 2),
							Arrays.copyOfRange(arr2, arr2.length / 2, arr2.length));
				} else {
					return medianTwoSortedArrays(Arrays.copyOfRange(arr, 0, arr.length / 2 + 1),
							Arrays.copyOfRange(arr2, arr2.length / 2, arr2.length));
				}
			}
		}
	}

	// m+n
	public static void mergeArrays(Integer[] arr, Integer[] arr2) {

		// Util.printArray(arr);
		// Util.printArray(arr2);
		for (int i = arr2.length - 1, j = i; i > -1; i--) {
			if (arr2[i] != null) {
				arr2[j] = arr2[i];
				j--;
			}
		}
		// Util.printArray(arr2);
		for (int i = arr.length, j = 0, k = 0; k < arr2.length;) {
			System.out.println("Before k:" + k + " i:" + i + " j:" + j);
			if (j == arr.length || i < arr2.length && arr2[i] < arr[j]) {
				arr2[k] = arr2[i];
				k++;
				i++;
			} else {
				arr2[k] = arr[j];
				k++;
				j++;
			}
			System.out.println("After k:" + k + " i:" + i + " j:" + j);
			Util.printArray(arr2);
		}
		// Util.printArray(arr2);
	}

	// log(n)
	public static void searchRotatedSorted(Integer arr[], int low, int high, int key) {

		if (low > high) {
			System.out.println("Not found");
			return;
		}

		int mid = (low + high) / 2;
		System.out.println("low:" + low + " high:" + high + " mid:" + mid);
		if (arr[mid] == key) {
			System.out.println("Key is found at " + mid);
			return;
		}
		if (arr[low] <= arr[mid]) {
			if (key >= arr[low] && key <= arr[mid]) {
				searchRotatedSorted(arr, low, mid - 1, key);
			} else {
				searchRotatedSorted(arr, mid + 1, high, key);
			}
		} else {
			if (key >= arr[mid] && key <= arr[high]) {
				searchRotatedSorted(arr, mid + 1, high, key);
			} else {
				searchRotatedSorted(arr, low, mid - 1, key);
			}
		}
	}

	// n
	public static void findMissingNumber(Integer arr[], int n) {

		int x1 = arr[0];
		int x2 = 1;
		for (int i = 1; i < arr.length; i++) {
			x1 = x1 ^ arr[i];
		}
		for (int i = 2; i <= n; i++) {
			x2 = x2 ^ i;
		}
		System.out.println(x1 ^ x2);
	}

	// n
	public static void oddOcurrence(Integer arr[]) {

		int res = arr[0];
		for (int i = 1; i < arr.length; i++) {
			res = res ^ arr[i];
		}
		System.out.println(res);
	}

	// n
	public static void findMajorityElement(Integer arr[]) {

		int index = 0;
		int count = 1;
		// System.out.println(arr[index] + " count: " + count);
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] == arr[index]) {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				index = i;
				count = 1;
			}
			// System.out.println(arr[index] + " count: " + count);
		}
		// System.out.println(arr[index] + " count: " + count);
		int counter = 0;
		for (int i = 0; i < arr.length; i++) {

			if (arr[index] == arr[i]) {
				counter++;
			}
		}
		if (counter > arr.length / 2) {
			System.out.println("Found");
			System.out.println(arr[index] + " count: " + count);
		} else {
			System.out.println("Not Found");
		}
	}

	// n
	public static boolean hasArrayTwoCandidates(Integer arr[], int sum) {

		Arrays.sort(arr);
		Util.printArray(arr);
		int i = 0;
		int j = arr.length - 1;
		for (; i < j;) {
			int tempSum = arr[i] + arr[j];
			if (tempSum == sum) {
				return true;
			} else if (tempSum < sum) {
				i++;
			} else {
				j--;
			}
		}
		return false;
	}

	// n
	public static void leaders(int arr[]) {
		int max = arr[arr.length - 1];
		System.out.println(max);
		for (int i = arr.length - 2; i > -1; i--) {
			if (arr[i] > max) {
				max = arr[i];
				System.out.println(max);
			}
		}
	}
}
