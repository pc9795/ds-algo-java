package ds.advancedDs;

import java.util.Arrays;
import java.util.Comparator;

import util.Util;

public class SuffixArray {

	public class Suffix {
		int i;
		String str;

		@Override
		public String toString() {
			return "Suffix [i=" + i + ", str=" + str + "]";
		}

	}

	public static void main(String[] args) {
		SuffixArray obj = new SuffixArray();
		String str = "prashant";
		String pattern = "p";
		Integer[] suffixArr = obj.buildSufixArray(str);
		System.out.println(obj.findPattern(str, pattern, suffixArr));
	}

	// m*n->m*log(n)
	public boolean findPattern(String text, String pattern, Integer[] suffixArr) {

		int l = 0;
		int r = suffixArr.length - 1;
		for (; l <= r;) {
			int mid = l + ((r - l) / 2);
			// System.out.println("l:" + l + "r:" + r + "mid:" + mid);
			// System.out.println(suffixArr[mid]);
			// System.out.println(text.substring(suffixArr[mid], suffixArr[mid]
			// + pattern.length()));
			int dec = pattern.compareTo(text.substring(suffixArr[mid],
					suffixArr[mid] + pattern.length()));
			// System.out.println("dec:" + dec);
			if (dec == 0) {
				return true;
			} else if (dec < 0) {
				r = mid - 1;
			} else {
				l = mid + 1;
			}

		}
		return false;
	}

	// n^2*log(n)
	public Integer[] buildSufixArray(String str) {

		Suffix[] arr = new Suffix[str.length()];
		for (int i = 0; i < str.length(); i++) {
			arr[i] = new Suffix();
			arr[i].i = i;
			arr[i].str = new String(str.substring(i));
		}

		Arrays.sort(arr, new Comparator<Suffix>() {

			@Override
			public int compare(Suffix arg0, Suffix arg1) {
				return arg0.str.compareTo(arg1.str);
			}
		});

		Util.printArrayH(arr);
		Integer suffixArr[] = new Integer[arr.length];
		for (int i = 0; i < suffixArr.length; i++) {
			suffixArr[i] = arr[i].i;
		}
		return suffixArr;

	}
}
