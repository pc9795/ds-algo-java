package geeks_for_geeks.linked_list;

import geeks_for_geeks.ds.linked_list.DoublyLinkedList;
import geeks_for_geeks.ds.linked_list.LinkedListApplications;
import geeks_for_geeks.ds.linked_list.SinglyLinkedList;
import geeks_for_geeks.ds.tree.binary_search_tree.BinarySearchTree;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 26-01-2019 16:04
 * Purpose: TODO:
 **/
class TestLinkedList {

    @Test
    void testReverseInChunk() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.insertAtEnd(1).insertAtEnd(2).insertAtEnd(3).insertAtEnd(4).insertAtEnd(5).
                insertAtEnd(6).insertAtEnd(7).insertAtEnd(8);

        System.out.println(LinkedListApplications.reverseInChunks(list, 8));
    }

    @Test
    void testAddTwoNumbersRepresentedByLists() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(9);
        list1.add(9);
        list1.add(9);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        System.out.println(LinkedListApplications.addTwoNumbersRepresentedByLists(list1, list2));
    }

    @Test
    void testQuickSortInDLL() {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.insertAtEnd(25).insertAtEnd(12).insertAtEnd(30).insertAtEnd(10).insertAtEnd(16);
        DoublyLinkedList.quickSort(dll);
        System.out.println(dll);
    }

    @Test
    void testToBST() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.insertAtEnd(1).insertAtEnd(2).insertAtEnd(3).insertAtEnd(4).insertAtEnd(5).insertAtEnd(6);
        System.out.println("List:" + list);

        BinarySearchTree bst = DoublyLinkedList.toBST(list);

        System.out.println("Inorder of created BST:");
        BinaryTree.inOrderTraversal(bst);
    }
}
