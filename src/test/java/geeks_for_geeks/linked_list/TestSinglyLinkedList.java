package geeks_for_geeks.linked_list;

import geeks_for_geeks.ds.linked_list.Applications;
import geeks_for_geeks.ds.linked_list.SinglyLinkedList;
import geeks_for_geeks.ds.nodes.Node;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created By: Prashant Chaubey
 * Created On: 26-01-2019 16:04
 * Purpose: Test
 **/
class TestSinglyLinkedList {

    @Test
    void testReverseInChunk() {
        SinglyLinkedList list = new SinglyLinkedList().append(1, 2, 3, 4, 5, 6, 7, 8);
        SinglyLinkedList expected = new SinglyLinkedList().append(3, 2, 1, 6, 5, 4, 8, 7);
        Applications.reverseInChunks(list, 3);
        assert list.equals(expected);
    }

    @Test
    void testReverseInChunkIter() {
        SinglyLinkedList list = new SinglyLinkedList().append(1, 2, 3, 4, 5, 6, 7, 8);
        SinglyLinkedList expected = new SinglyLinkedList().append(3, 2, 1, 6, 5, 4, 8, 7);
        Applications.reverseInChunksIter(list, 3);
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
        assert Applications.addTwoNumbersRepresentedByLists(list1, list2).equals(expected);
    }

    @Test
    void testHasLoop() {
        SinglyLinkedList linkedList = new SinglyLinkedList().append(1, 2, 3, 4, 5);
        Node head = linkedList.getHead();
        //Creating a loop
        head.next.next.next.next.next = head.next;
        assert SinglyLinkedList.hasLoop(linkedList);
    }

    @Test
    void testDetectAndRemoveLoop() {
        SinglyLinkedList linkedList = new SinglyLinkedList().append(1, 2, 3, 4, 5);
        Node head = linkedList.getHead();
        //Creating a loop
        head.next.next.next.next.next = head.next;
        assert SinglyLinkedList.hasLoop(linkedList);
        SinglyLinkedList.detectAndRemoveLoop(linkedList);
        assert !SinglyLinkedList.hasLoop(linkedList);
    }

    @Test
    void testRotateCounterClockWise() {
        SinglyLinkedList list = new SinglyLinkedList().append(10, 20, 30, 40, 50, 60);
        Applications.rotateCounterClockWise(list, 4);
        assert list.equals(new SinglyLinkedList().append(50, 60, 10, 20, 30, 40));
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> testSwap() {
        return Stream.of(
                Arguments.of(new SinglyLinkedList().append(1, 2, 3, 4, 5), 2, 4, new SinglyLinkedList().append(1, 4, 3, 2, 5)),
                Arguments.of(new SinglyLinkedList().append(1, 2, 3, 4, 5), 2, 3, new SinglyLinkedList().append(1, 3, 2, 4, 5)),
                Arguments.of(new SinglyLinkedList().append(1, 2, 3, 4, 5), 1, 2, new SinglyLinkedList().append(2, 1, 3, 4, 5)),
                Arguments.of(new SinglyLinkedList().append(1, 2, 3, 4, 5), 1, 3, new SinglyLinkedList().append(3, 2, 1, 4, 5)),
                Arguments.of(new SinglyLinkedList().append(1, 2, 3, 4, 5), 2, 5, new SinglyLinkedList().append(1, 5, 3, 4, 2)),
                Arguments.of(new SinglyLinkedList().append(1, 2, 3, 4, 5), 1, 5, new SinglyLinkedList().append(5, 2, 3, 4, 1))
        );
    }

    @ParameterizedTest
    @MethodSource
    void testSwap(SinglyLinkedList input, int data1, int data2, SinglyLinkedList expected) {
        input.swap(data1, data2);
        assert input.equals(expected);
    }

    @Test
    void testReverseRecursive() {
        SinglyLinkedList input = new SinglyLinkedList().append(1, 2, 3, 4, 5);
        SinglyLinkedList expected = new SinglyLinkedList().append(5, 4, 3, 2, 1);
        boolean ans = input.reverseRecursive().equals(expected);
        assert ans;
    }

    @SuppressWarnings("unused")
    static Stream<Arguments> testSortedMerge() {
        return Stream.of(
                Arguments.of(new SinglyLinkedList().append(1, 2, 3), null, new SinglyLinkedList().append(1, 2, 3)),
                Arguments.of(null, new SinglyLinkedList().append(1, 2, 3), new SinglyLinkedList().append(1, 2, 3)),
                Arguments.of(new SinglyLinkedList().append(1, 2, 3), new SinglyLinkedList(), new SinglyLinkedList().append(1, 2, 3)),
                Arguments.of(new SinglyLinkedList(), new SinglyLinkedList().append(1, 2, 3), new SinglyLinkedList().append(1, 2, 3)),
                Arguments.of(new SinglyLinkedList().append(1, 3, 5), new SinglyLinkedList().append(2, 4), new SinglyLinkedList().append(1, 2, 3, 4, 5)),
                Arguments.of(new SinglyLinkedList().append(2, 4), new SinglyLinkedList().append(1, 3, 5), new SinglyLinkedList().append(1, 2, 3, 4, 5))
        );
    }

    @ParameterizedTest
    @MethodSource
    void testSortedMerge(SinglyLinkedList list1, SinglyLinkedList list2, SinglyLinkedList expected) {
        boolean ans = SinglyLinkedList.sortedMerge(list1, list2).equals(expected);
        assert ans;
    }

    @Test
    void testDeleteAtPosition() {
        SinglyLinkedList list = new SinglyLinkedList().append(1, 2, 3, 4, 5, 6);
        //head
        list.deleteAtPosition(0);
        assert list.equals(new SinglyLinkedList().append(2, 3, 4, 5, 6));
        //last
        list.deleteAtPosition(list.size() - 1);
        assert list.equals(new SinglyLinkedList().append(2, 3, 4, 5));

        list.deleteAtPosition(1);
        assert list.equals(new SinglyLinkedList().append(2, 4, 5));
    }

    @Test
    void testMergeSort() {
        SinglyLinkedList list = new SinglyLinkedList().append(5, 4, 3, 2, 1);
        SinglyLinkedList.mergeSort(list);
        assert list.equals(new SinglyLinkedList().append(1, 2, 3, 4, 5));
    }

    @Test
    void testInsertAtPosition() {
        SinglyLinkedList list = new SinglyLinkedList().append(1, 2, 3, 4, 5);
        //head
        list = list.insertAtPos(0, 0);
        assert list.equals(new SinglyLinkedList().append(0, 1, 2, 3, 4, 5));
        //last
        list = list.insertAtPos(list.size(), 6);
        assert list.equals(new SinglyLinkedList().append(0, 1, 2, 3, 4, 5, 6));

        list = list.insertAtPos(2, 7);
        assert list.equals(new SinglyLinkedList().append(0, 1, 7, 2, 3, 4, 5, 6));


    }
}
