package geeks_for_geeks.linked_list;

import geeks_for_geeks.ds.linked_list.*;
import geeks_for_geeks.ds.nodes.BTNode;
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
        SinglyLinkedList list = new SinglyLinkedList().append(1, 2, 3, 4, 5, 6, 7, 8);
        SinglyLinkedList expected = new SinglyLinkedList().append(3, 2, 1, 6, 5, 4, 8, 7);
        LinkedListApplications.reverseInChunks(list, 3);
        assert list.equals(expected);
    }

    @Test
    void testAddTwoNumbersRepresentedByLists() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(9);
        list1.add(9);
        list1.add(9);
        List<Integer> list2 = new ArrayList<>();
        list2.add(1);
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(0);
        expected.add(0);
        expected.add(1);
        assert LinkedListApplications.addTwoNumbersRepresentedByLists(list1, list2).equals(expected);
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
        BinarySearchTree bst = DoublyLinkedList.toBST(list);
        BinarySearchTree expected = new BinarySearchTree(4);
        expected.root.left = new BTNode(2);
        expected.root.left.left = new BTNode(1);
        expected.root.left.right = new BTNode(3);
        expected.root.right = new BTNode(6);
        expected.root.right.left = new BTNode(5);
        assert expected.equals(bst);

    }

    @Test
    void testHasLoop() {
        SinglyLinkedList linkedList = new SinglyLinkedList().append(1, 2, 3, 4, 5);
        Node head = linkedList.getHead();
        head.next.next.next.next.next = head.next;
        assert SinglyLinkedList.hasLoop(linkedList);
    }

    @Test
    void testDetectAndRemoveLoop() {
        SinglyLinkedList linkedList = new SinglyLinkedList().append(1, 2, 3, 4, 5);
        Node head = linkedList.getHead();
        head.next.next.next.next.next = head.next;
        assert SinglyLinkedList.hasLoop(linkedList);
        SinglyLinkedList.detectAndRemoveLoop(linkedList);
        assert !SinglyLinkedList.hasLoop(linkedList);
    }

    @Test
    void testRotateCounterClockWise() {
        SinglyLinkedList list = new SinglyLinkedList().append(10, 20, 30, 40, 50, 60);
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
        SortedCircularLinkedList list = new SortedCircularLinkedList().insert(5, 6, 1, 4, 2, 3);
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
