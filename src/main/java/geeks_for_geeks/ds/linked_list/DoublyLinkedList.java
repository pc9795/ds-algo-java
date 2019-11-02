package geeks_for_geeks.ds.linked_list;

import geeks_for_geeks.ds.linked_list.adt.LinkedList;
import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.nodes.DNode;
import geeks_for_geeks.ds.tree.binary_search_tree.BinarySearchTree;
import util.DoublePointer;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-10-2018 17:48
 **/
public class DoublyLinkedList implements LinkedList {
    public DNode head;
    public int size;

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
    public DoublyLinkedList insertAtFront(int data) {
        DNode node = new DNode(data);
        node.next = this.head;
        if (this.head != null) {
            this.head.prev = node;
        }
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
    public DoublyLinkedList insertAtPosition(int pos, int data) {
        assert pos >= 0 && pos <= size;
        if (pos == 0) {
            insertAtFront(data);
            return this;
        }
        if (pos == size) {
            insertAtEnd(data);
            return this;
        }
        DNode temp = this.head;
        // No need of prev-1 as we know have prev pointer.
        for (int i = 0; i < pos; i++, temp = temp.next) ;
        DNode node = new DNode(data);
        node.prev = temp.prev;
        temp.prev = node;
        node.next = temp;
        node.prev.next = node;
        size++;
        return this;
    }

    @Override
    public DoublyLinkedList insertAtEnd(int data) {
        if (this.head == null) {
            this.head = new DNode(data);
            size++;
            return this;
        }
        DNode temp;
        for (temp = this.head; temp.next != null; temp = temp.next) ;
        temp.next = new DNode(data);
        temp.next.prev = temp;
        size++;
        return this;
    }

    /**
     * t=O(n)
     *
     * @param data
     * @return
     */
    public DoublyLinkedList delete(int data) {
        DNode curr = this.head;
        for (; curr != null && curr.data != data; curr = curr.next) {
        }
        assert curr != null;
        if (curr.next != null) {
            curr.next.prev = curr.prev;
        }
        if (curr.prev != null) {
            curr.prev.next = curr.next;
        } else {
            this.head = curr.next;
        }
        size--;
        return this;
    }

    public DoublyLinkedList append(int... data) {
        for (Integer item : data) {
            insertAtEnd(item);
        }
        return this;
    }

    /**
     * t=O(n)
     *
     * @return
     */
    public DoublyLinkedList reverse() {
        if (head == null || size == 1) {
            return this;
        }
        DNode prev = null;
        DNode curr = head;
        for (; curr != null; ) {
            prev = curr.prev;
            curr.prev = curr.next;
            curr.next = prev;
            curr = curr.prev;
        }
        this.head = prev.prev;
        return this;
    }

    public static void quickSort(DoublyLinkedList dll) {
        if (dll == null || dll.size <= 1) {
            return;
        }
        DNode last = dll.head;
        while (last.next != null) {
            last = last.next;
        }
        quickSortUtil(dll.head, last);
    }

    private static void quickSortUtil(DNode first, DNode last) {
        if (first == null || last == null || first == last) {
            return;
        }
        DNode pivot = partition(first, last);
        quickSortUtil(first, pivot.prev);
        quickSortUtil(pivot.next, last);
    }

    private static DNode partition(DNode first, DNode last) {
        // We are swapping data not links.
        DNode i = null;
        DNode curr = first;
        while (curr != last) {
            if (curr.data >= last.data) {
                curr = curr.next;
                continue;
            }
            i = (i == null) ? first : i.next;
            int temp = curr.data;
            curr.data = i.data;
            i.data = temp;
            curr = curr.next;
        }
        i = (i == null) ? first : i.next;
        int temp = last.data;
        last.data = i.data;
        i.data = temp;
        return i;
    }

    /**
     * If list is not sorted then it will give unexpected results.
     *
     * @param dll
     * @return
     */
    public static BinarySearchTree toBST(DoublyLinkedList dll) {
        assert dll != null && dll.head != null;

        int n = dll.size();
        return new BinarySearchTree(toBSTUtil(new DoublePointer<>(dll.head), n));
    }

    /**
     * t=O(n)
     * create BST from leaves.
     *
     * @param headPtr
     * @param n
     * @return
     */
    private static BTNode toBSTUtil(DoublePointer<DNode> headPtr, int n) {
        if (n == 0) {
            return null;
        }
        BTNode left = toBSTUtil(headPtr, n / 2);
        BTNode root = new BTNode(headPtr.data);
        // headPtr is progressing as tree is constructing.
        headPtr.data = headPtr.data.next;
        BTNode right = toBSTUtil(headPtr, n - n / 2 - 1);

        root.left = left;
        root.right = right;
        return root;
    }

    @Override
    public String toString() {
        if (this.head == null) {
            return "{}";
        }
        DNode curr = this.head;
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        while (curr != null) {
            sb.append(curr.data).append(",");
            curr = curr.next;
        }
        sb.append("}");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoublyLinkedList that = (DoublyLinkedList) o;

        if (size != that.size) return false;
        if (size == 0) {
            return true;
        }
        DNode thisCurr = head;
        DNode thatCurr = that.head;
        while (thisCurr != null) {
            if (thisCurr.data != thatCurr.data) {
                return false;
            }
            thisCurr = thisCurr.next;
            thatCurr = thatCurr.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = head != null ? head.hashCode() : 0;
        result = 31 * result + size;
        return result;
    }
}
