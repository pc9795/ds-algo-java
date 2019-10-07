package cracking_the_coding_interview.ch9_8_recursion_and_dynamic_programming;


import javafx.util.Pair;
import util.Utils;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 06-10-2019 19:13
 * Purpose: TODO:
 **/
public class Solution {

    public static int tripleStep(int n) {
        int arr[] = new int[n + 1];
        arr[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= 3; j++) {
                arr[i] += i - j >= 0 ? arr[i - j] : 0;
            }
        }
        return arr[n];
    }

    public static List<Pair<Integer, Integer>> robotInGrid(boolean[][] mat) {
        return robotInGridUtil(mat, 0, 0, new HashSet<>());
    }

    private static List<Pair<Integer, Integer>> robotInGridUtil(boolean[][] mat, int x, int y,
                                                                HashSet<Pair<Integer, Integer>> set) {
        if (!Utils.isSafe(x, y, mat.length, mat[0].length)) {
            return null;
        }
        if (!mat[x][y]) {
            return null;
        }
        if (set.contains(new Pair<>(x, y))) {
            return null;
        }
        if (x == mat.length - 1 && y == mat[0].length - 1) {
            List<Pair<Integer, Integer>> list = new ArrayList<>();
            list.add(new Pair<>(x, y));
            return list;
        }
        List<Pair<Integer, Integer>> ans = robotInGridUtil(mat, x + 1, y, set);
        if (ans != null) {
            ans.add(new Pair<>(x, y));
            return ans;
        }
        set.add(new Pair<>(x + 1, y));
        ans = robotInGridUtil(mat, x, y + 1, set);
        if (ans != null) {
            ans.add(new Pair<>(x, y));
            return ans;
        }
        set.add(new Pair<>(x, y + 1));
        return null;
    }

    public static int magicIndex(int[] arr) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            set.add(arr[i]);
        }
        if (set.size() != arr.length) {
            throw new RuntimeException("Values are not distinct");
        }
        return magicIndexUtil(arr, 0, arr.length - 1);
    }

    private static int magicIndexUtil(int[] arr, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) >> 1;
        if (arr[mid] == mid) {
            return mid;
        }
        if (arr[mid] < mid) {
            return magicIndexUtil(arr, mid + 1, right);
        }
        return magicIndexUtil(arr, left, mid - 1);
    }

    public static int magicIndex2(int[] arr) {
        return magicIndex2Util(arr, 0, arr.length - 1);
    }

    private static int magicIndex2Util(int[] arr, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) >> 1;
        if (mid == arr[mid]) {
            return mid;
        }
        int leftAns = magicIndex2Util(arr, left, Math.min(mid - 1, arr[mid]));
        if (leftAns > 0) {
            return leftAns;
        }
        return magicIndex2Util(arr, Math.max(mid + 1, arr[mid]), right);
    }


}
