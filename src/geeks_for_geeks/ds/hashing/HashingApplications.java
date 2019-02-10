package geeks_for_geeks.ds.hashing;

import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import geeks_for_geeks.ds.nodes.BTNode;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 24-09-2018 02:05
 **/
public class HashingApplications {

    /**
     * t=O(n)
     *
     * @param tree
     */
    public static void printTreeInVerticalOrder(BinaryTree tree) {
        assert tree != null;

//        Tree map so that we will have values sorted according to axises.
        Map<Integer, List<Integer>> map = new TreeMap<>();

        printTreeInVerticalOrderUtil(tree.root, 0, map);

        for (Integer key : map.keySet()) {
            System.out.println("for axis:" + key + "=>" + map.get(key));
        }
    }

    private static void printTreeInVerticalOrderUtil(BTNode node, int axis, Map<Integer, List<Integer>> map) {
        if (node == null) {
            return;
        }

        if (!map.containsKey(axis)) {
            map.put(axis, new ArrayList<>());
        }

        map.get(axis).add(node.data);

        printTreeInVerticalOrderUtil(node.left, axis - 1, map);
        printTreeInVerticalOrderUtil(node.right, axis + 1, map);

    }

    /**
     * t=O(bigger array length)
     *
     * @param check
     * @param arr
     * @return
     */
    public static boolean isArraySubsetOfAnother(int[] check, int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int elem : arr) {
            if (!map.containsKey(elem)) {
                map.put(elem, 0);
            }
            map.put(elem, map.get(elem) + 1);
        }
        for (int elem : check) {
            if (!map.containsKey(elem)) {
                return false;
            } else if (map.get(elem) < 1) {
                return false;
            }
            map.put(elem, map.get(elem) - 1);
        }
        return true;
    }


    /**
     * Handle duplicates also.
     *
     * @param list1
     * @param list2
     * @return
     */
    public static LinkedList<Integer> union(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> result = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Integer value : list1) {
            if (!map.containsKey(value)) {
                map.put(value, 0);
            }
            map.put(value, map.get(value) + 1);
        }

        for (Integer value : list2) {
            if (!map.containsKey(value)) {
                map.put(value, 0);
            }
            map.put(value, map.get(value) + 1);
        }

        for (Integer key : map.keySet()) {
            for (int i = 0; i < map.get(key); i++) {
                result.add(key);
            }
        }

        return result;
    }

    /**
     * Handle duplicates also.
     *
     * @param list1
     * @param list2
     * @return
     */
    public static LinkedList<Integer> intersection(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> result = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer value : list1) {
            if (!map.containsKey(value)) {
                map.put(value, 0);
            }
            map.put(value, map.get(value) + 1);
        }
        for (Integer value : list2) {
            if (map.containsKey(value) && map.get(value) >= 1) {
                result.add(value);
                map.put(value, map.get(value) - 1);
            }
        }
        return result;
    }

    /**
     * t=O(n)
     *
     * @param arr
     * @param sum
     */
    public static void findPairWithGivenSum(int arr[], int sum) {
        HashSet<Integer> set = new HashSet<>();
        boolean pairFound = false;

        for (int elem : arr) {
            int diff = sum - elem;

//            we can use diff>0 if we there are no negative numbers.
            if (set.contains(diff)) {
                System.out.println("Pair found:" + elem + "," + diff);
                pairFound = true;
            }
            set.add(elem);
        }
        if (!pairFound) {
            System.out.println("No pair found!");
        }
    }


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
     * @param fromTo
     */
    public static void findItineraryFromGivenListOfTickets(Map<String, String> fromTo) {
        Map<String, String> toFrom = new HashMap<>();

        for (String key : fromTo.keySet()) {
            toFrom.put(fromTo.get(key), key);
        }

        String src = "";

        for (String key : fromTo.keySet()) {
            if (!toFrom.containsKey(key)) {
                src = key;
                break;
            }
        }

        assert !src.equals("");

        for (; fromTo.get(src) != null; src = fromTo.get(src)) {
            System.out.println("From:" + src + ",To:" + fromTo.get(src));
        }

    }

    public static void numberOfEmployeesUnderAEmployee(char[][] employeeManagerPair) {
        Map<Character, List<Character>> managerToEmployee = new HashMap<>();

        for (int i = 0; i < employeeManagerPair.length; i++) {

            if (!managerToEmployee.containsKey(employeeManagerPair[i][1])) {
                managerToEmployee.put(employeeManagerPair[i][1], new ArrayList<>());
            }

            managerToEmployee.get(employeeManagerPair[i][1]).add(employeeManagerPair[i][0]);
        }
        Map<Character, Integer> memo = new HashMap<>();

        for (int i = 0; i < employeeManagerPair.length; i++) {

            char employee = employeeManagerPair[i][0];
            System.out.println("Count of reportees to " + employee + ":" +
                    countOfReportees(managerToEmployee, employee, memo));
        }
    }

    /**
     * @param managerToEmployee
     * @param employee
     * @return
     */
    private static int countOfReportees(Map<Character, List<Character>> managerToEmployee, Character employee,
                                        Map<Character, Integer> memo) {

        if (memo.containsKey(employee)) {
            return memo.get(employee);
        }

        if (managerToEmployee.get(employee) == null) {

            memo.put(employee, 0);
            return 0;
        }
        int count = 0;
        for (Character c : managerToEmployee.get(employee)) {

            if (c != employee) {
                count += 1 + countOfReportees(managerToEmployee, c, memo);
            }
        }
        memo.put(employee, count);
        return count;
    }


}
