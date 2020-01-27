package geeks_for_geeks.linked_list;

import geeks_for_geeks.ds.linked_list.DoublyLinkedList;
import geeks_for_geeks.ds.nodes.BTNode;
import geeks_for_geeks.ds.tree.binary_search_tree.BinarySearchTree;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 18-10-2019 19:27
 * Purpose: Test
 **/
class TestDoublyLinkedList {
    @Test
    void testReverse() {
        DoublyLinkedList list = new DoublyLinkedList().append(1, 2, 3, 4, 5).reverse();
        DoublyLinkedList expected = new DoublyLinkedList().append(5, 4, 3, 2, 1);
        assert list.equals(expected);
    }

    @Test
    void testQuickSortInDLL() {
        DoublyLinkedList dll = new DoublyLinkedList().append(25, 12, 30, 10, 16);
        DoublyLinkedList.quickSort(dll);
        DoublyLinkedList expected = new DoublyLinkedList().append(10, 12, 16, 25, 30);
        assert dll.equals(expected);
    }

    @Test
    void testToBST() {
        DoublyLinkedList list = new DoublyLinkedList().append(1, 2, 3, 4, 5, 6);
        BinarySearchTree bst = DoublyLinkedList.convert(list);
        BinarySearchTree expected = new BinarySearchTree(4);
        expected.insertAtPos("0", 2).insertAtPos("00", 1).insertAtPos("01", 3)
                .insertAtPos("1", 6).insertAtPos("10", 5);
        assert expected.equals(bst);
    }
}
