package geeks_for_geeks.ds.hashing;

import geeks_for_geeks.ds.binary_tree.BTNode;
import geeks_for_geeks.ds.binary_tree.BinaryTree;

import javax.sound.sampled.Line;
import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 24-09-2018 02:05
 **/
public class HashingApplications {

    public static void printTreeInVerticalOrder(BinaryTree tree) {
        if (tree.root == null) {
            throw new RuntimeException("empty tree!");
        }
        Map<Integer, List<Integer>> ht = new TreeMap<>();
        printTreeInVerticalOrderUtil(tree.root, 0, ht);
        for (Integer key : ht.keySet()) {
            System.out.println("for axis:" + key + "=>" + ht.get(key));
        }
    }

    private static void printTreeInVerticalOrderUtil(BTNode node, int axis, Map<Integer, List<Integer>> ht) {
        if (node == null) {
            return;
        }
        if (!ht.containsKey(axis)) {
            ht.put(axis, new ArrayList<>());
        }
        ht.get(axis).add(node.data);
        printTreeInVerticalOrderUtil(node.left, axis - 1, ht);
        printTreeInVerticalOrderUtil(node.right, axis + 1, ht);

    }

    public static boolean isArraySubsetOfAnother(int[] check, int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (!map.containsKey(arr[i])) {
                map.put(arr[i], 0);
            }
            map.put(arr[i], map.get(arr[i]) + 1);
        }
        for (int i = 0; i < check.length; i++) {
            if (!map.containsKey(check[i])) {
                return false;
            } else if (map.get(check[i]) < 1) {
                return false;
            }
            map.put(check[i], map.get(check[i]) - 1);
        }
        return true;
    }

    public static LinkedList<Integer> unionOrIntersection(LinkedList<Integer> list1, LinkedList<Integer> list2, boolean union) {
        LinkedList<Integer> result = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        if (union) {
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
        } else {
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
        }
        return result;
    }

    public static void findPairWithGivenSum(int arr[], int sum) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (set.contains(sum - arr[i])) {
                System.out.println("Pair found:" + arr[i] + "," + (sum - arr[i]));
                return;
            }
            set.add(arr[i]);
        }
        System.out.println("No pair found!");
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

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(1);
        tree.root.left = new BTNode(2);
        tree.root.right = new BTNode(3);
        tree.root.left.left = new BTNode(4);
        tree.root.left.right = new BTNode(5);
        tree.root.right.left = new BTNode(6);
        tree.root.right.right = new BTNode(7);
        tree.root.right.right.left = new BTNode(8);
        tree.root.right.right.right = new BTNode(9);
//        BinaryTree.levelOrderTraversal(tree);
//        printTreeInVerticalOrder(tree);
//        System.out.println(isArraySubsetOfAnother(new int[]{1, 4, 2}, new int[]{1, 4, 2, 3}));
        LinkedList<Integer> list1 = new LinkedList<>();
        LinkedList<Integer> list2 = new LinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(2);
        list1.add(3);
        list2.add(4);
        list2.add(5);
        list2.add(2);
        list2.add(6);
        list2.add(2);
//        System.out.println(unionOrIntersection(list1, list2, false));
//        findPairWithGivenSum(new int[]{1, 2, 3, 4, 5}, 4);
        System.out.println(isDuplicateElementWithinKDistance(new int[]{1, 2, 3, 4, 1, 2, 3, 4}, 3));
        System.out.println(isDuplicateElementWithinKDistance(new int[]{1, 2, 3, 1, 4, 5}, 3));
    }

}
