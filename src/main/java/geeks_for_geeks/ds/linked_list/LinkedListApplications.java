package geeks_for_geeks.ds.linked_list;

import geeks_for_geeks.ds.nodes.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-10-2018 23:33
 **/
public class LinkedListApplications {

    public static SinglyLinkedList reverseInChunks(SinglyLinkedList list, int chunkSize) {
        SinglyLinkedList reversed = new SinglyLinkedList();
        reversed.head = reverseInChunksUtil(list.head, chunkSize);
        return reversed;
    }

    private static Node reverseInChunksUtil(Node head, int chunkSize) {
        if (head == null) {
            return null;
        }
        Node prev = null;
        Node curr = head;
        int i = 1;
        while (curr != null) {
//            we are doing this before instead of at the end.
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            i++;
            if (i > chunkSize) {
                break;
            }
        }
        head.next = reverseInChunksUtil(curr, chunkSize);
        return prev;
    }

    public static List addTwoNumbersRepresentedByLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        int carry = 0;
        int i;
        int n1 = list1.size();
        int n2 = list2.size();
        int max = Math.max(n1, n2);
        for (i = 0; i < max; i++) {
            int a = i < n1 ? list1.get(i) : 0;
            int b = i < n2 ? list2.get(i) : 0;
            int sum = a + b + carry;
            result.add(sum > 9 ? sum % 10 : sum);
//            Because there are two numbers therefore carry can't be greater than 1.
            carry = sum > 9 ? 1 : 0;
        }
        if (carry == 1) {
            result.add(carry);
        }
        return result;
    }

}