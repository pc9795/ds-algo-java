package geeks_for_geeks.ds.linked_list;

import geeks_for_geeks.ds.linked_list.adt.LinkedList;
import geeks_for_geeks.ds.nodes.Node;

import java.util.Objects;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 18:35
 **/
public class SinglyLinkedList implements LinkedList {
    private Node head;
    private int size;

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }


    @Override
    public int size() {
        return size;
    }

    /**
     * t=O(1)
     *
     * @param data
     * @return
     */
    @Override
    public SinglyLinkedList insertAtFront(int data) {
        Node node = new Node(data);
        node.next = this.head;
        this.head = node;
        size++;
        return this;
    }

    /**
     * t=O(n)
     *
     * @param pos
     * @param data
     * @return
     */
    @Override
    public SinglyLinkedList insertAtPosition(int pos, int data) {
        assert pos >= 0 && pos <= size;
        if (pos == 0) {
            insertAtFront(data);
            return this;
        }

        if (pos == size) {
            insertAtEnd(data);
            return this;
        }

        Node temp = this.head;
        for (int i = 0; i < pos - 1; i++) {
            temp = temp.next;
        }
        Node node = new Node(data);
        node.next = temp.next;
        temp.next = node;
        size++;
        return this;
    }

    public SinglyLinkedList append(int data) {
        return insertAtEnd(data);
    }

    public SinglyLinkedList append(Integer... data) {
        for (int i = 0; i < data.length; i++) {
            insertAtEnd(data[i]);
        }
        return this;
    }

    /**
     * t=O(n)
     *
     * @param data
     * @return
     */
    @Override
    public SinglyLinkedList insertAtEnd(int data) {
        if (this.head == null) {
            this.head = new Node(data);
            size++;
            return this;
        }
        Node temp;
        for (temp = this.head; temp.next != null; ) {
            temp = temp.next;
        }
        temp.next = new Node(data);
        size++;
        return this;
    }

    /**
     * t=O(n)
     *
     * @param data
     */
    public void delete(int data) {
        Node temp = head;
        Node prev = null;
        for (; temp != null; prev = temp, temp = temp.next) {
            if (temp.data == data) {
                break;
            }
        }
        assert temp != null;
        size--;
        if (prev == null) {
            // Head node is going to be deleted
            head = head.next;
        } else {
            prev.next = temp.next;
            temp.next = null;
        }
    }

    /**
     * t=O(n)
     *
     * @param pos
     */
    public void deleteAtPosition(int pos) {
        // If size was not available we have to use null check with the loop.
        assert pos >= 0 && pos < size;

        int i = 0;
        Node curr = head;
        Node prev = null;
        for (; i != pos; i++) {
            prev = curr;
            curr = curr.next;
        }
        size--;
        if (prev == null) {
            // Head is going to be deleted
            head = head.next;
            return;
        }
        if (curr.next == null) {
            // Last node is going to be deleted
            prev.next = null;
            return;
        }
        prev.next = curr.next;
    }

    /**
     * t=O(n)
     *
     * @param data
     * @return
     */
    public boolean search(int data) {
        Node curr = this.head;
        for (; curr != null; curr = curr.next) {
            if (curr.data == data) {
                return true;
            }
        }
        return false;
    }

    /**
     * t=O(n)
     * Check for the cases when they are just next to each other.
     *
     * @param data1
     * @param data2
     */
    public void swap(int data1, int data2) {
        Node curr = this.head;
        Node prev = null;
        Node currX = null;
        Node prevX = null;
        Node currY = null;
        Node prevY = null;
        if (data1 == data2) {
            System.out.println("Data are same!");
            return;
        }
        for (; curr != null; curr = curr.next) {
            if (curr.data == data1) {
                prevX = prev;
                currX = curr;
            } else if (curr.data == data2) {
                prevY = prev;
                currY = curr;
            }
            prev = curr;
        }
        if (currX == null || currY == null) {
            System.out.println("Data not found!");
            return;
        }
        if (prevX != null) {
            prevX.next = currY;
        } else {
            this.head = currY;
        }
        if (prevY != null) {
            prevY.next = currX;
        } else {
            this.head = currX;
        }
        Node temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;

    }

    /**
     * t=O(n)
     *
     * @return
     */
    public SinglyLinkedList reverse() {
        Node prev = null;
        Node curr = this.head;
        for (; curr != null; ) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        this.head = prev;
        return this;
    }

    /**
     * t=O(n1 + n2)
     *
     * @param list1
     * @param list2
     * @return
     */
    public static SinglyLinkedList sortedMerge(SinglyLinkedList list1, SinglyLinkedList list2) {
        SinglyLinkedList list = new SinglyLinkedList();
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        list.head = sortedMergeUtil(list1.head, list2.head);
        return list;
    }

    public static Node sortedMergeUtil(Node curr1, Node curr2) {
        if (curr1 == null) {
            return curr2;
        }
        if (curr2 == null) {
            return curr1;
        }
        Node merged = null;
        Node curr = null;
        for (; curr1 != null && curr2 != null; ) {
            if (curr1.data < curr2.data) {
                if (merged == null) {
                    merged = new Node(curr1.data);
                    curr = merged;
                } else {
                    curr.next = new Node(curr1.data);
                    curr = curr.next;
                }
                curr1 = curr1.next;
            } else {
                if (merged == null) {
                    merged = new Node(curr2.data);
                    curr = merged;
                } else {
                    curr.next = new Node(curr2.data);
                    curr = curr.next;
                }
                curr2 = curr2.next;
            }
        }
        if (curr2 != null) {
            for (; curr2 != null; curr2 = curr2.next) {
                curr.next = new Node(curr2.data);
                curr = curr.next;
            }
        }
        if (curr1 != null) {
            for (; curr1 != null; curr1 = curr1.next) {
                curr.next = new Node(curr1.data);
                curr = curr.next;
            }
        }
        return merged;
    }

    /**
     * t=O(n * log(n))
     * s=O(n)
     *
     * @param list
     */
    public static void mergeSort(SinglyLinkedList list) {
        if (list == null || list.head == null) {
            return;
        }
        list.head = mergeSortUtil(list.head);
    }

    public static Node mergeSortUtil(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node tortoise = node;
        Node hare = node.next;
        for (; hare != null; ) {
            hare = hare.next;
            if (hare != null) {
                tortoise = tortoise.next;
                hare = hare.next;
            }
        }
        Node first = node;
        Node second = tortoise.next;
        tortoise.next = null;
        first = mergeSortUtil(first);
        second = mergeSortUtil(second);
        return sortedMergeUtil(first, second);
    }

    @Override
    public String toString() {
        if (this.head == null) {
            return "{}";
        }
        Node curr = this.head;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        while (curr != null) {
            sb.append(curr.data).append(",");
            curr = curr.next;
        }
        sb.append("}");
        return sb.toString();
    }

    public static boolean hasLoop(SinglyLinkedList list) {
        assert list != null && list.getHead() != null;
        return hasLoopUtil(list.getHead()) != null;
    }

    private static Node hasLoopUtil(Node node) {
        assert node != null;
        Node slow = node;
        Node fast = slow;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
            if (fast == slow) {
                return slow;
            }
        }
        return null;
    }

    // We can use hashing too.
    public static void detectAndRemoveLoop(SinglyLinkedList list) {
        assert list != null && list.getHead() != null;
        Node loop = hasLoopUtil(list.getHead());
        removeLoop(list.getHead(), loop);
    }

    private static void removeLoop(Node start, Node loop) {
        assert start != null && loop != null;
        Node ptr1 = start;
        Node ptr2 = loop;

        while (ptr1.next != ptr2.next) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        // Break the loop;
        ptr2.next = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SinglyLinkedList that = (SinglyLinkedList) o;
        if (that.size != this.size) {
            return false;
        }
        boolean equal = true;
        Node currThis = this.head;
        Node currThat = that.head;
        for (int i = 0; i < size; i++) {
            if (currThat.data != currThis.data) {
                equal = false;
                break;
            }
        }
        return equal;
    }

    @Override
    public int hashCode() {
        // todo not a good implementation
        return Objects.hash(head);
    }
}
