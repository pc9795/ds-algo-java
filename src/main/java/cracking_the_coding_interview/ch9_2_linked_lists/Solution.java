package cracking_the_coding_interview.ch9_2_linked_lists;

import geeks_for_geeks.ds.linked_list.SinglyLinkedList;
import geeks_for_geeks.ds.nodes.Node;

import java.util.HashSet;

/**
 * Created By: Prashant Chaubey
 * Created On: 04-09-2019 22:02
 **/
public class Solution {

    /**
     * t=O(N)
     * s=O(1)
     *
     * @param list input list
     */
    public static void removeDups(SinglyLinkedList list) {
        if (list == null) {
            return;
        }

        HashSet<Integer> set = new HashSet<>();
        Node prev = null;
        Node curr = list.getHead();
        while (curr != null) {
            if (set.contains(curr.data)) {
                if (prev == null) {
                    throw new RuntimeException("prev can't be null");
                }
                prev.next = curr.next;
                list.setSize(list.getSize() - 1);
            } else {
                set.add(curr.data);
                prev = curr;
            }
            curr = curr.next;
        }
    }

    /**
     * t=O(N^2)
     * s=O(1)
     *
     * @param list input list
     */
    public static void removeDups2(SinglyLinkedList list) {
        if (list == null) {
            return;
        }

        Node curr = list.getHead();
        while (curr != null) {
            Node runner = curr;
            while (runner.next != null) {
                if (runner.next.data == curr.data) {
                    runner.next = runner.next.next;
                    list.setSize(list.getSize() - 1);
                } else {
                    runner = runner.next;
                }
            }
            curr = curr.next;
        }
    }

    public static int kthFromLast(SinglyLinkedList list, int k) {
        int kFromFirst = list.getSize() - k + 1;
        if (kFromFirst > list.getSize() || kFromFirst < 1) {
            return -1;
        }
        Node curr = list.getHead();
        for (int i = 1; i < kFromFirst; i++) {
            curr = curr.next;
        }
        return curr.data;
    }

    public static void deleteMiddleNode(SinglyLinkedList list, Node ref) {
        if (ref == null || ref.next == null) {
            return;
        }
        ref.data = ref.next.data;
        ref.next = ref.next.next;
        list.setSize(list.getSize() - 1);
    }

    public static void partition(SinglyLinkedList list, int partitionElement) {
        SinglyLinkedList listWithElementsLessThanPartitionElem = new SinglyLinkedList();
        Node prev = null;
        Node curr = list.getHead();
        while (curr != null) {
            if (curr.data < partitionElement) {
                listWithElementsLessThanPartitionElem.append(curr.data);
                if (prev == null) {
                    list.setHead(list.getHead().next);
                } else {
                    prev.next = curr.next;
                }
                list.setSize(list.getSize() - 1);
            } else {
                prev = curr;
            }
            curr = curr.next;
        }
        curr = listWithElementsLessThanPartitionElem.getHead();
        while (curr != null) {
            list.insertAtFront(curr.data);
            curr = curr.next;
        }
    }

    public static SinglyLinkedList sumLists(SinglyLinkedList list1, SinglyLinkedList list2) {
        Node i = list1.getHead();
        Node j = list2.getHead();
        SinglyLinkedList ans = new SinglyLinkedList();
        int carry = 0;
        while (i != null && j != null) {
            ans.append((i.data + j.data + carry) % 10);
            carry = (i.data + j.data + carry) / 10;
            i = i.next;
            j = j.next;
        }
        while (i != null) {
            ans.append((i.data + carry) % 10);
            carry = (i.data + carry) / 10;
            i = i.next;
        }
        while (j != null) {
            ans.append((j.data + carry) % 10);
            carry = (j.data + carry) / 10;
            j = j.next;
        }
        return ans;
    }

    public static SinglyLinkedList sumListsInForwardOrder(SinglyLinkedList list1, SinglyLinkedList list2) {
        if (list1.size() > list2.getSize()) {
            list2 = padListWithZeroes(list2, list1.size() - list2.size());
        } else if (list1.size() < list2.getSize()) {
            list1 = padListWithZeroes(list1, list2.size() - list1.size());
        }
        PartialSum sum = sumListsInForwardOrderHelper(list1.getHead(), list2.getHead());
        if (sum.carry == 1) {
            sum.sumList.insertAtFront(1);
        }
        return sum.sumList;
    }

    static class PartialSum {
        SinglyLinkedList sumList;
        int carry;

        PartialSum() {
            sumList = new SinglyLinkedList();
        }
    }

    //Assumes they are of equal length.
    static private PartialSum sumListsInForwardOrderHelper(Node list1, Node list2) {
        if (list1 == null) {
            return new PartialSum();
        }
        PartialSum sum = sumListsInForwardOrderHelper(list1.next, list2.next);
        sum.sumList.insertAtFront((list1.data + list2.data + sum.carry) % 10);
        sum.carry = (list1.data + list2.data + sum.carry) / 10;
        return sum;
    }

    static private SinglyLinkedList padListWithZeroes(SinglyLinkedList list, int count) {
        while (count-- > 0) {
            list.insertAtFront(0);
        }
        return list;
    }

    public static boolean isPalindrome(SinglyLinkedList list) {
        if (list.size() == 1) {
            return true;
        }

        int counter = list.size() / 2 + (list.size() % 2 == 0 ? 0 : 1);
        Node afterMidPoint = list.getHead();
        while (counter-- > 0) {
            afterMidPoint = afterMidPoint.next;
        }
        Node beforeMidPoint = list.getHead();
        for (int i = 0; i < list.size() / 2; i++) {
            if (beforeMidPoint.data != afterMidPoint.data) {
                return false;
            }
            beforeMidPoint = beforeMidPoint.next;
            afterMidPoint = afterMidPoint.next;
        }
        return true;
    }

    public static boolean isIntersecting(SinglyLinkedList list1, SinglyLinkedList list2) {
        if (list1 == null || list2 == null) {
            return false;
        }
        if (list1.size() > list2.size()) {
            list1 = moveForwardHead(list1, list1.size() - list2.size());
        } else if (list1.size() < list2.size()) {
            list2 = moveForwardHead(list2, list2.size() - list1.size());
        }
        Node i = list1.getHead();
        Node j = list2.getHead();
        //Assuming now same size.
        while (i != null && j != null) {
            if (i == j) {
                return true;
            }
            i = i.next;
            j = j.next;
        }
        return false;
    }

    static private SinglyLinkedList moveForwardHead(SinglyLinkedList list, int num) {
        while (num-- > 0) {
            list.setHead(list.getHead().next);
        }
        return list;
    }

    public static Node getLoopPoint(SinglyLinkedList list) {
        Node slow = list.getHead();
        Node fast = list.getHead();
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }

        slow = list.getHead();
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}
