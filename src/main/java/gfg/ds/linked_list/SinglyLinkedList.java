package gfg.ds.linked_list;

import gfg.ds.linked_list.adt.LinkedList;

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
     * @param data data to add
     * @return modified list
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
     * @param pos  position to add to
     * @param data data to add
     * @return modified list
     */
    @Override
    public SinglyLinkedList insertAtPos(int pos, int data) {
        assert pos >= 0 && pos <= size : String.format("Position should be between %s and %s", 0, size);
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

    /**
     * Append values in bulk
     *
     * @param data data to add
     * @return modified list
     */
    public SinglyLinkedList append(Integer... data) {
        for (Integer aData : data) {
            insertAtEnd(aData);
        }
        return this;
    }

    /**
     * t=O(n)
     *
     * @param data data to add
     * @return modified list
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
     * @param data data to be deleted
     */
    public void delete(int data) {
        Node temp = head;
        Node prev = null;
        for (; temp != null; prev = temp, temp = temp.next) {
            if (temp.data == data) {
                break;
            }
        }
        assert temp != null : String.format("Value: %s not found in the list", data);
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
     * @param pos position for which data is to be deleted
     */
    public void deleteAtPosition(int pos) {
        // If size was not available we have to use null check with the loop.
        assert pos >= 0 && pos < size : String.format("Position should be between %s and %s", 0, size);

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
     * @param data item to search
     * @return true if item is found in the list
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
     * NOTE: It assumes that all data values are distinct as how it will know which values we are referring to. Even if
     * a list with duplicate values is used this implementation will always operate on the first occurrence.
     *
     * @param data1 first input
     * @param data2 second input
     */
    public void swap(int data1, int data2) {
        Node curr = this.head;
        Node prev = null;
        Node currX = null;
        Node prevX = null;
        Node currY = null;
        Node prevY = null;
        if (data1 == data2) {
            System.out.println("Values are same!");
            return;
        }
        //NOTE: We can optimize it by early breaking if both of them are found.
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
        //NOTE: We can have more descriptive message for which of them is not found
        if (currX == null || currY == null) {
            System.out.println("One of the value not found!");
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
     * @return calling instance
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
     * t=O(n)
     * NOTE: mutates the calling list
     *
     * @return reversed list.
     */
    public SinglyLinkedList reverseRecursive() {
        this.head = reverseRecursiveUtil(this.head, null);
        return this;
    }

    private Node reverseRecursiveUtil(Node node, Node prev) {
        if (node.next == null) {
            node.next = prev;
            return node;
        }
        Node next = node.next;
        node.next = prev;
        return reverseRecursiveUtil(next, node);
    }


    /**
     * t=O(n1 + n2)
     * Unexpected behavior if both are not sorted.
     *
     * @param list1 first sorted list
     * @param list2 second sorted list
     * @return merged list
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
        list.setSize(list1.getSize() + list2.getSize());
        return list;
    }

    private static Node sortedMergeUtil(Node curr1, Node curr2) {
        if (curr1 == null) {
            return curr2;
        }
        if (curr2 == null) {
            return curr1;
        }
        Node merged = null;
        Node curr = null;
        for (; curr1 != null && curr2 != null; ) {
            int data;
            if (curr1.data < curr2.data) {
                data = curr1.data;
                curr1 = curr1.next;
            } else {
                data = curr2.data;
                curr2 = curr2.next;
            }
            if (merged == null) {
                //Case for the first node in the list
                merged = new Node(data);
                curr = merged;
            } else {
                curr.next = new Node(data);
                curr = curr.next;
            }
        }
        //This can't happen that both are not null. One of them must be exhausted in previous loop.
        Node leftOver;
        if (curr2 != null) {
            leftOver = curr2;
        } else {
            leftOver = curr1;
        }
        for (; leftOver != null; leftOver = leftOver.next) {
            curr.next = new Node(leftOver.data);
            curr = curr.next;
        }
        return merged;
    }

    /**
     * t=O(n * log(n))
     * s=O(n)
     *
     * @param list unsorted list
     */
    public static void mergeSort(SinglyLinkedList list) {
        if (list == null || list.head == null) {
            return;
        }
        list.head = mergeSortUtil(list.head);
    }

    private static Node mergeSortUtil(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        //Tortoise hair algorithm to partition the linked list into two halves
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

    /**
     * NOTE: We can also use hashing
     *
     * @param list input list
     */
    public static void detectAndRemoveLoop(SinglyLinkedList list) {
        assert list != null && list.getHead() != null : "List is empty";
        Node loop = hasLoopUtil(list.getHead());
        removeLoop(list.getHead(), loop);
    }

    /**
     * NOTE: proof of this is in the notes.
     *
     * @param start start of the linked list
     * @param loop  the point detected by floyd's cycle algorithm
     */
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
        return Objects.hash(head, size);
    }

    /**
     * Created By: Prashant Chaubey
     * Created On: 14-09-2018 18:35
     * NOTE: I could try to make it using generics but it will need extra code during comparison.
     **/

    public static class Node {
        public Node next;
        public int data;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }
}
