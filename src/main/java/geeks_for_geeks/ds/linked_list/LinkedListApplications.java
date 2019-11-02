package geeks_for_geeks.ds.linked_list;

import geeks_for_geeks.ds.nodes.Node;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-10-2018 23:33
 **/
public class LinkedListApplications {

    /**
     * t=O(n)
     * todo geeks of geeks article
     *
     * @param list
     * @param chunkSize
     */
    private static void reverseInChunksIter(SinglyLinkedList list, int chunkSize) {
        assert list != null && list.getHead() != null;

        Node curr = list.getHead();
        Node end = null;
        Node start;
        for (int i = 0; i < list.size(); i += chunkSize) {
            Node prev = null;
            start = curr;
            int j = 1;
            while (curr != null) {
                //  we are doing this before instead of at the end.
                Node next = curr.next;
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
     *
     * @param list
     * @param chunkSize
     * @return
     */
    public static void reverseInChunks(SinglyLinkedList list, int chunkSize) {
        assert list != null;
        list.setHead(reverseInChunksUtil(list.getHead(), chunkSize));
    }

    private static Node reverseInChunksUtil(Node node, int chunkSize) {
        if (node == null) {
            return null;
        }
        Node prev = null;
        Node curr = node;
        int i = 1;
        while (curr != null) {
            //  we are doing this before instead of at the end.
            Node next = curr.next;
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
     *
     * @param list1
     * @param list2
     * @return
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
//            Because there are two numbers therefore carry can't be greater than 1.
            carry = sum > 9 ? 1 : 0;
        }
        if (carry == 1) {
            result.add(carry);
        }
        return result;
    }

    /**
     * t=O(n)
     *
     * @param list
     * @param k
     */
    public static void rotateCounterClockWise(SinglyLinkedList list, int k) {
        assert list != null && list.getHead() != null;
        assert k <= list.size();
        if (k == list.size()) {
            return;
        }

        Node curr = list.getHead();
        for (int i = 0; i < k - 1; i++) {
            curr = curr.next;
        }

        // Store breaking point.
        Node temp = curr.next;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = list.getHead();
        list.setHead(temp);
    }


    public static Pair<CircularLinkedList, CircularLinkedList> splitCircularLinkedList(CircularLinkedList list) {
        assert list != null && list.last != null;
        //For size 1;
        if (list.last.next == list.last) {
            return new Pair<>(list, new CircularLinkedList());
        }
        Node slow = list.last.next;
        Node fast = list.last.next;
        do {
            fast = fast.next;
            if (fast != list.last) {
                fast = fast.next;
                slow = slow.next;
            }
        } while (fast != list.last);

        Node temp = list.last.next;

        CircularLinkedList rightHalf = new CircularLinkedList();
        list.last.next = slow.next;
        rightHalf.last = list.last;
        rightHalf.size = list.size / 2;

        list.last = slow;
        slow.next = temp;
        list.size = list.size / 2 + (list.size % 2 != 0 ? 1 : 0);
        return new Pair<>(list, rightHalf);
    }
}