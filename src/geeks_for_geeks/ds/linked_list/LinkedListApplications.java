package geeks_for_geeks.ds.linked_list;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-10-2018 23:33
 **/
public class LinkedListApplications {
    public static List addTwoNumbersRepresentedByLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        int carry = 0;
        int i;
        for (i = 0; i < list1.size() && i < list2.size(); i++) {
            int a = list1.get(i);
            int b = list2.get(i);
            int sum = a + b;
            result.add((sum > 9 ? sum % 10 : sum) + carry);
            carry = sum > 9 ? 1 : 0;
        }
        if (list1.size() == list2.size()) {
            if (carry == 1) {
                result.add(carry);
            }
        } else if (i == list1.size()) {
            list2.set(i, list2.get(i) + carry);
            for (; i < list2.size(); i++) {
                result.add(list2.get(i));
            }
        } else if (i == list2.size()) {
            list1.set(i, list1.get(i) + carry);
            for (; i < list1.size(); i++) {
                result.add(list1.get(i));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        list1.add(2);
        list1.add(1);
        list1.add(2);
        list1.add(9);
        list2.add(4);
        list2.add(9);
        list2.add(1);
        System.out.println(addTwoNumbersRepresentedByLists(list1, list2));
    }
}
