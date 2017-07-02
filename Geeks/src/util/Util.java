package util;

public class Util {

	public static int gcd(int a, int b) {
		if (b % a == 0) {
			return a;
		} else {
			return gcd(b % a, a);
		}
	}

	public static void printArray(int[] arr, String name) {
		System.out.println("Array" + "<" + name + ">" + ":");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "|");
		}
		System.out.println();
	}

	public static void printArray(int[] arr) {
		System.out.println("Array:");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "|");
		}
		System.out.println();
	}

	public static void printArray(Object[] arr) {
		System.out.println("Array:");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + "|");
		}
		System.out.println();
	}

	public static void printArray(Object[] arr, int start, int end) {
		System.out.println("Array:");
		for (int i = start; i <= end; i++) {
			System.out.print(arr[i] + "|");
		}
		System.out.println();
	}

	public static void printArrayH(Object[] arr) {
		System.out.println("Array:");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i] + " " + i);
		}
	}

	public static void print2DArray(Object[][] arr) {
		System.out.println("2DArray:");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "|");
			}
			System.out.println();
		}
	}

	public static void print2DArray(Object[][] arr, String name) {
		System.out.println("2DArray" + "<" + name + ">" + ":");
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + "|");
			}
			System.out.println();
		}
	}
}
