package gfg.ds.linked_list;

import utils.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * @noinspection WeakerAccess
 */
public class Applications {

    /**
     * t=O(n)
     * todo geeks of geeks article
     */
    public static void reverseInChunksIter(SinglyLinkedList list, int chunkSize) {
        assert list.getHead() != null;

        SinglyLinkedList.Node curr = list.getHead();
        SinglyLinkedList.Node end = null;
        SinglyLinkedList.Node start;
        for (int i = 0; i < list.size(); i += chunkSize) {
            SinglyLinkedList.Node prev = null;
            start = curr;
            int j = 1;
            while (curr != null) {
                SinglyLinkedList.Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
                j++;
                if (j > chunkSize) {
                    break;
                }
            }
            if (end == null) {
                list.setHead(prev);
            } else {
                end.next = prev;
            }
            end = start;
        }
    }

    /**
     * t=O(n)
     */
    public static void reverseInChunks(SinglyLinkedList list, int chunkSize) {
        list.setHead(reverseInChunksUtil(list.getHead(), chunkSize));
    }

    private static SinglyLinkedList.Node reverseInChunksUtil(SinglyLinkedList.Node node, int chunkSize) {
        if (node == null) {
            return null;
        }
        SinglyLinkedList.Node prev = null;
        SinglyLinkedList.Node curr = node;
        int i = 1;
        while (curr != null) {
            SinglyLinkedList.Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            i++;
            if (i > chunkSize) {
                break;
            }
        }
        node.next = reverseInChunksUtil(curr, chunkSize);
        return prev;
    }

    /**
     * t=O(n1 + n2)
     */
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
            // Because there are two numbers therefore carry can't be greater than 1.
            carry = sum > 9 ? 1 : 0;
        }
        // A leftover carry
        if (carry == 1) {
            result.add(carry);
        }
        return result;
    }

    /**
     * t=O(n)
     */
    public static void rotateCounterClockWise(SinglyLinkedList list, int k) {
        assert list.getHead() != null;
        assert k <= list.size();

        if (k == list.size()) {
            return;
        }

        SinglyLinkedList.Node curr = list.getHead();
        for (int i = 0; i < k; i++) {
            curr = curr.next;
        }

        // Store breaking point.
        SinglyLinkedList.Node temp = curr;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = list.getHead();
        list.setHead(temp);
    }


    /**
     * In case of odd no of elements the first list will contain the extra value
     * NOTE: it is mutating the input list (NOT PURE)
     */
    public static Pair<CircularLinkedList, CircularLinkedList> split(CircularLinkedList list) {
    assert list.last != null : "Input list can't be empt";
        //For size 1;
        if (list.size() == 1) {
            return new Pair<>(list, new CircularLinkedList());
        }
        SinglyLinkedList.Node slow = list.last.next;
        SinglyLinkedList.Node fast = list.last.next;
        do {
            fast = fast.next;
            if (fast != list.last) {
                fast = fast.next;
                slow = slow.next;
            }
        } while (fast != list.last);

        SinglyLinkedList.Node temp = list.last.next;
        list.last.next = slow.next;
        CircularLinkedList rightHalf = new CircularLinkedList();
        rightHalf.last = list.last;
        rightHalf.size = list.size / 2;
        list.last = slow;
        slow.next = temp;
        list.size = list.size / 2 + (list.size % 2 != 0 ? 1 : 0);
        return new Pair<>(list, rightHalf);
    }
}