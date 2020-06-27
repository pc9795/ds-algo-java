package gfg.ds.hashing;

import gfg.ds.tree.binary_tree.BinaryTree;
import javafx.util.Pair;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 24-09-2018 02:05
 **/
public class Applications {

    /**
     * t=O(n)
     *
     * @param tree input tree
     */
    public static Map<Integer, List<Integer>> getVerticalOrder(BinaryTree tree) {
        assert tree != null;
        // Tree map so that we will have values sorted according to axises.
        Map<Integer, List<Integer>> map = new TreeMap<>();
        getVerticalOrderUtil(tree.root, 0, map);
        return map;
    }

    private static void getVerticalOrderUtil(BinaryTree.BinaryTreeNode node, int axis, Map<Integer, List<Integer>> map) {
        if (node == null) {
            return;
        }
        if (!map.containsKey(axis)) {
            map.put(axis, new ArrayList<>());
        }
        map.get(axis).add(node.data);
        getVerticalOrderUtil(node.left, axis - 1, map);
        getVerticalOrderUtil(node.right, axis + 1, map);
    }

    /**
     * t=O(bigger array length)
     *
     * @param subArrToCheck array to check whether it is sub array of original or not
     * @param original      input array
     * @return true if the probed array is sub array of given array.
     */
    public static boolean isArraySubsetOfAnother(int[] original, int[] subArrToCheck) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int elem : original) {
            map.put(elem, map.getOrDefault(elem, 0) + 1);
        }
        for (int elem : subArrToCheck) {
            if (!map.containsKey(elem) || map.get(elem) < 1) {
                return false;
            }
            map.put(elem, map.get(elem) - 1);
        }
        return true;
    }


    /**
     * Handle duplicates also.
     *
     * @param first  first list
     * @param second second list
     * @return union of the list
     */
    public static List<Integer> union(List<Integer> first, List<Integer> second) {
        List<Integer> result = new LinkedList<>();
        HashMap<Integer, Integer> firstMap = new HashMap<>();
        HashMap<Integer, Integer> secondMap = new HashMap<>();
        for (Integer value : first) firstMap.put(value, firstMap.getOrDefault(value, 0) + 1);
        for (Integer value : second) secondMap.put(value, secondMap.getOrDefault(value, 0) + 1);

        Set<Integer> keys = new HashSet<>(firstMap.keySet());
        keys.addAll(secondMap.keySet());
        for (Integer key : keys) {
            int times = Math.max(firstMap.getOrDefault(key, 0), secondMap.getOrDefault(key, 0));
            for (int i = 0; i < times; i++) {
                result.add(key);
            }
        }
        return result;
    }

    /**
     * Handle duplicates also.
     *
     * @param first  first list
     * @param second second list
     * @return intersection of the list
     */
    public static List<Integer> intersection(List<Integer> first, List<Integer> second) {
        List<Integer> result = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer value : first) map.put(value, map.getOrDefault(value, 0) + 1);
        for (Integer value : second) {
            if (map.containsKey(value) && map.get(value) > 0) {
                result.add(value);
                map.put(value, map.get(value) - 1);
            }
        }
        return result;
    }

    /**
     * t=O(n)
     *
     * @param arr input array
     * @param sum target sum
     * @return pair of numbers with sum equal to target sum
     */
    public static Pair<Integer, Integer> findPairWithGivenSum(int arr[], int sum) {
        HashSet<Integer> set = new HashSet<>();
        for (int elem : arr) {
            int diff = sum - elem;
            // We can use diff > 0 if we there are no negative numbers.
            if (set.contains(diff)) {
                return new Pair<>(diff, elem);
            }
            set.add(elem);
        }
        return null;
    }


    /**
     * t=O(n)
     *
     * @param arr input array
     * @param k   tolerable distance for duplicates
     * @return true if there is duplicate within k.
     */
    public static boolean isDuplicateElementWithinKDistance(int arr[], int k) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(arr[i])) {
                return true;
            }
            set.add(arr[i]);
            if (i >= k) {
                set.remove(arr[i - k]);
            }
        }
        return false;
    }

    /**
     * t=O(n)
     *
     * @param fromTo itinerary map
     * @return source of itinerary map
     */
    public static List findItineraryFromGivenListOfTickets(Map<String, String> fromTo) {
        Set<String> tos = new HashSet<>(fromTo.values());
        String src = "";
        for (String key : fromTo.keySet()) {
            if (!tos.contains(key)) {
                src = key;
                break;
            }
        }
        assert !src.equals("");

        List<String> itinerary = new ArrayList<>();
        while (src != null) {
            itinerary.add(src);
            src = fromTo.get(src);
        }
        return itinerary;
    }

    public static int numberOfEmployeesUnder(char[][] employeeManagerPair, char employee) {
        Map<Character, List<Character>> managerToEmployee = new HashMap<>();
        for (char[] anEmployeeManagerPair : employeeManagerPair) {
            if (!managerToEmployee.containsKey(anEmployeeManagerPair[1])) {
                managerToEmployee.put(anEmployeeManagerPair[1], new ArrayList<>());
            }
            managerToEmployee.get(anEmployeeManagerPair[1]).add(anEmployeeManagerPair[0]);
        }
        return numberOfEmployeesUnderUtil(managerToEmployee, employee);
    }

    /**
     * Can memo this function if use multiple times.
     *
     * @param managerToEmployee map with manager and list of employee
     * @param employee          employee for which to calculate reportees.
     * @return count of reportees for a given employee.
     */
    private static int numberOfEmployeesUnderUtil(Map<Character, List<Character>> managerToEmployee, Character employee) {
        if (managerToEmployee.get(employee) == null) {
            return 0;
        }
        int count = 0;
        for (Character c : managerToEmployee.get(employee)) {
            if (c == employee) {
                continue;
            }
            count += 1 + numberOfEmployeesUnderUtil(managerToEmployee, c);
        }
        return count;
    }
}
