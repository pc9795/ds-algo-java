package geeks_for_geeks.linked_list;

import geeks_for_geeks.ds.linked_list.*;
import geeks_for_geeks.ds.nodes.Node;
import geeks_for_geeks.ds.tree.binary_search_tree.BinarySearchTree;
import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Stream;

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

    @Test
    void testHasLoop() {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.append(1).append(2).append(3).append(4).append(5);
        Node head = linkedList.getHead();
        head.next.next.next.next.next = head.next;
        assert SinglyLinkedList.hasLoop(linkedList);
    }

    @Test
    void testDetectAndRemoveLoop() {
        SinglyLinkedList linkedList = new SinglyLinkedList();
        linkedList.append(1).append(2).append(3).append(4).append(5);
        Node head = linkedList.getHead();
        head.next.next.next.next.next = head.next;
        assert SinglyLinkedList.hasLoop(linkedList);
        SinglyLinkedList.detectAndRemoveLoop(linkedList);
        assert !SinglyLinkedList.hasLoop(linkedList);
    }

    @Test
    void testRotateCounterClockWise() {
        SinglyLinkedList list = new SinglyLinkedList();
        list.append(10, 20, 30, 40, 50, 60);
        LinkedListApplications.rotateCounterClockWise(list, 4);
        assert list.equals(new SinglyLinkedList().append(50, 60, 10, 20, 30, 40));
    }

    static Stream<Arguments> testSplitCircularLinkedList() {
        return Stream.of(
                Arguments.of(new CircularLinkedList().append(1), new Pair<>(new CircularLinkedList().append(1),
                        new CircularLinkedList())),
                Arguments.of(new CircularLinkedList().append(1, 2), new Pair<>(new CircularLinkedList().append(1),
                        new CircularLinkedList().append(2))),
                Arguments.of(new CircularLinkedList().append(1, 2, 3), new Pair<>(new CircularLinkedList().append(1, 2),
                        new CircularLinkedList().append(3)))
        );
    }

    @ParameterizedTest
    @MethodSource
    void testSplitCircularLinkedList(CircularLinkedList list, Pair<CircularLinkedList, CircularLinkedList> expected) {
        boolean ans = LinkedListApplications.splitCircularLinkedList(list).equals(expected);
        assert ans;
    }

    @Test
    void testSortedCircularLinkedList() {
        SortedCircularLinkedList list = new SortedCircularLinkedList();
        list.insert(5, 6, 1, 4, 2, 3);
        int[] expected = {1, 2, 3, 4, 5, 6};
        Node curr = list.last.next;
        int i = 0;
        do {
            assert curr.data == expected[i];
            i++;
            curr = curr.next;
        } while (curr != list.last.next);
    }
}
