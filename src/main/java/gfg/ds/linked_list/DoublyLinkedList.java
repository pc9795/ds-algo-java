package gfg.ds.linked_list;

import gfg.ds.linked_list.adt.LinkedList;
import gfg.ds.tree.binary_search_tree.BinarySearchTree;
import gfg.ds.tree.binary_tree.BinaryTree;
import utils.DoublePointer;

import java.util.Objects;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-10-2018 17:48
 **/
public class DoublyLinkedList implements LinkedList {
    private DNode head;
    public int size;

    @Override
    public int size() {
        return size;
    }


    /**
     * t=O(1)
     *
     * @param data data to be added
     * @return calling instance
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
     * @param pos  position to which to insert data
     * @param data data to add
     * @return calling instance
     */
    @Override
    public DoublyLinkedList insertAtPos(int pos, int data) {
        assert pos >= 0 && pos <= size : String.format("Position should be between %s and %s", 0, size);
        if (pos == 0) {
            insertAtFront(data);
            return this;
        }
        if (pos == size) {
            insertAtEnd(data);
            return this;
        }
        DNode temp = this.head;
        // No need of prev as we know have prev pointer.
        for (int i = 0; i < pos; i++) {
            temp = temp.next;
        }
        DNode node = new DNode(data);
        node.prev = temp.prev;
        temp.prev = node;
        node.next = temp;
        node.prev.next = node;
        size++;
        return this;
    }

    /**
     * t=O(1)
     *
     * @param data data to add
     * @return calling instance
     */
    @Override
    public DoublyLinkedList insertAtEnd(int data) {
        if (this.head == null) {
            this.head = new DNode(data);
            size++;
            return this;
        }
        DNode temp;
        for (temp = this.head; temp.next != null; ) {
            temp = temp.next;
        }
        temp.next = new DNode(data);
        temp.next.prev = temp;
        size++;
        return this;
    }

    /**
     * t=O(n)
     *
     * @param data data to delete
     * @return calling instance
     */
    public DoublyLinkedList delete(int data) {
        DNode curr = this.head;
        for (; curr != null && curr.data != data; ) {
            curr = curr.next;
        }
        assert curr != null : "Node with given data not found";
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
     * NOTE: modified the list(NOT PURE)
     *
     * @return calling instance
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

    /**
     * t = O(n * log n) in best and average case
     * = O(n^2) in worst case (list is already sorted)
     *
     * @param dll list to sort
     */
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

    /**
     * We are swapping data not links.
     *
     * @param first first element in the list
     * @param last  last element in the list
     * @return the pivot element
     */
    private static DNode partition(DNode first, DNode last) {
        DNode i = null;
        DNode curr = first;
        while (curr != last) {
            if (curr.data >= last.data) {
                curr = curr.next;
                continue;
            }
            i = (i == null) ? first : i.next;
            swapData(curr, i);
            curr = curr.next;
        }
        i = (i == null) ? first : i.next;
        swapData(last, i);
        return i;
    }

    private static void swapData(DNode first, DNode second) {
        int temp = first.data;
        first.data = second.data;
        second.data = temp;
    }

    /**
     * If list is not sorted then it will give unexpected results.
     *
     * @param dll input list
     * @return binary search tree obtained from the list
     */
    public static BinarySearchTree toBST(DoublyLinkedList dll) {
        assert dll != null && dll.head != null;

        int n = dll.size();
        return BinarySearchTree.fromBinaryTree(new BinaryTree().insertAtRoot(convertUtil(new DoublePointer<>(dll.head), n)));
    }

    /**
     * t=O(n)
     * create BST from leaves.
     *
     * @param headPtr double pointer containing the head of the list for a recursion
     * @param n       size of the list
     * @return root of the binary search tree node
     */
    private static BinaryTree.BinaryTreeNode convertUtil(DoublePointer<DNode> headPtr, int n) {
        if (n == 0) {
            return null;
        }
        BinaryTree.BinaryTreeNode left = convertUtil(headPtr, n / 2);
        BinaryTree.BinaryTreeNode root = new BinaryTree.BinaryTreeNode(headPtr.data);
        // headPtr is progressing as tree is constructing.
        headPtr.data = headPtr.data.next;
        BinaryTree.BinaryTreeNode right = convertUtil(headPtr, n - n / 2 - 1);

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

    /**
     * Created By: Prashant Chaubey
     * Created On: 13-10-2018 17:48
     **/
    public static class DNode {
        public DNode prev;
        public DNode next;
        public int data;

        public DNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "DNode{" +
                    "prev=" + prev +
                    ", next=" + next +
                    ", data=" + data +
                    "} ";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DNode dNode = (DNode) o;
            return data == dNode.data;
        }

        @Override
        public int hashCode() {
            return Objects.hash(data);
        }
    }
}