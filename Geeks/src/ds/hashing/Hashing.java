package ds.hashing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ds.linkedList.Node;
import ds.tree.TNode;

public class Hashing {
	public Map<Object, List<Object>> map = new HashMap<>();

	public Hashing() {
		this.map = new HashMap<>();
	}

	public void employeeUnder(String[] employee, String[] manager) {
		Map<String, List<String>> set = new HashMap<>();
		for (int i = 0; i < manager.length; i++) {
			if (!set.containsKey(manager[i])) {
				set.put(manager[i], new ArrayList<String>());
			}
			set.get(manager[i]).add(employee[i]);
		}
		Set<String> keys = set.keySet();
		for (String key : keys) {
			System.out.println("Manager:" + key + " Employee(s):"
					+ set.get(key));
		}
	}

	public void itinerary(String[] src, String[] dest) {

		Map<String, String> set = new HashMap<>();
		Map<String, String> revSet = new HashMap<>();
		for (int i = 0; i < src.length; i++) {
			set.put(src[i], dest[i]);
			revSet.put(dest[i], src[i]);
		}
		System.out.println(set);
		System.out.println(revSet);
		String start = "";
		Set<String> keys = set.keySet();
		for (String key : keys) {
			if (!revSet.containsKey(key)) {
				start = key;
				break;
			}
		}
		for (; set.containsKey(start);) {
			System.out.print("|" + start + "->" + set.get(start) + "|");
			start = set.get(start);

		}
	}

	public boolean findDubplicateWithinK(int arr[], int k) {
		map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])) {
				return true;
			}
			map.put(arr[i], null);
			if (i >= k) {
				map.remove(arr[i - k]);
			}
		}
		return false;
	}

	public void checkPair(int arr[], int value) {
		System.out.println("Pairs are :");
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(value - arr[i])) {
				System.out.println(arr[i] + "," + (value - arr[i]));
			} else {
				map.put(arr[i], null);
			}
		}
	}

	// can be optimized for duplicates
	public void unionAndIntersection(Node<Integer> list1, Node<Integer> list2) {

		// union
		Node<Integer> union = null;
		Node<Integer> unionCurr = null;
		Node<Integer> inter = null;
		Node<Integer> interCurr = null;
		for (Node<Integer> temp = list1; temp != null; temp = temp.next) {
			map.put(temp.data, null);
			if (union == null) {
				union = temp;
				unionCurr = temp;
			} else {
				unionCurr.next = temp;
				unionCurr = unionCurr.next;
			}

		}
		for (Node<Integer> temp = list2; temp != null; temp = temp.next) {
			if (map.containsKey(temp.data)) {
				if (inter == null) {
					inter = temp;
					interCurr = temp;
				} else {
					interCurr.next = temp;
					interCurr = unionCurr.next;
				}
			}
		}
		for (Node<Integer> temp = list2; temp != null; temp = temp.next) {
			if (!map.containsKey(temp.data)) {
				if (union == null) {
					union = temp;
					unionCurr = temp;
				} else {
					unionCurr.next = temp;
					unionCurr = unionCurr.next;
				}
			}
		}
		System.out.println("Union: " + union);
		System.out.println("Intersection: " + inter);
	}

	// problem with duplicates
	public boolean findSubset(int[] main, int[] second) {

		for (int i = 0; i < main.length; i++) {
			map.put(main[i], null);
		}
		for (int i = 0; i < second.length; i++) {
			if (!map.containsKey(second[i])) {
				return false;
			}
		}
		return true;
	}

	// O(n) if map insert and search is O(1)
	public void printVerticalBTree(TNode<Integer> root, int level) {
		if (root == null) {
			return;
		}
		if (!map.containsKey(level)) {
			List<Object> list = new ArrayList<>();
			map.put(level, list);
		}
		map.get(level).add(root);
		printVerticalBTree(root.left, level - 1);
		printVerticalBTree(root.right, level + 1);

	}
}
