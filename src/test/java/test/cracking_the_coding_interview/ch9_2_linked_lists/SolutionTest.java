package test.cracking_the_coding_interview.ch9_2_linked_lists;

import cracking_the_coding_interview.ch9_2_linked_lists.Solution;
import geeks_for_geeks.ds.linked_list.SinglyLinkedList;
import geeks_for_geeks.ds.nodes.Node;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

/**
 * Created By: Prashant Chaubey
 * Created On: 04-09-2019 22:16
 * Purpose: TODO:
 **/
class SolutionTest {
    private static Stream<Arguments> testRemoveDups() {
        return Stream.of(
                Arguments.of(new SinglyLinkedList(), new SinglyLinkedList()),
                Arguments.of(new SinglyLinkedList().append(1), new SinglyLinkedList().append(1)),
                Arguments.of(new SinglyLinkedList().append(1).append(2).append(2),
                        new SinglyLinkedList().append(1).append(2)),
                Arguments.of(new SinglyLinkedList().append(1).append(1).append(1),
                        new SinglyLinkedList().append(1))
        );
    }

    @ParameterizedTest
    @MethodSource("testRemoveDups")
    void testRemoveDups(SinglyLinkedList input, SinglyLinkedList expected) {
        Solution.removeDups(input);
        assert input.equals(expected);
    }

    @ParameterizedTest
    @MethodSource("testRemoveDups")
    void testRemoveDups2(SinglyLinkedList input, SinglyLinkedList expected) {
        Solution.removeDups2(input);
        assert input.equals(expected);
    }

    private static Stream<Arguments> testKthFromLast() {
        return Stream.of(
                Arguments.of(new SinglyLinkedList().append(1).append(2).append(3), -1, -1),
                Arguments.of(new SinglyLinkedList().append(1).append(2).append(3), 4, -1),
                Arguments.of(new SinglyLinkedList().append(1).append(2).append(3).append(4), 2, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("testKthFromLast")
    void testKthFromLast(SinglyLinkedList input, int k, int expected) {
        assert Solution.kthFromLast(input, k) == expected;
    }

    private static Stream<Arguments> testDeleteMiddleNode() {
        SinglyLinkedList list1 = new SinglyLinkedList().append(1);
        SinglyLinkedList list2 = new SinglyLinkedList().append(1).append(2).append(3);
        SinglyLinkedList list3 = new SinglyLinkedList();
        return Stream.of(
                Arguments.of(list3, null, new SinglyLinkedList()),
                Arguments.of(list1, list1.getHead(), new SinglyLinkedList().append(1)),
                Arguments.of(list2, list2.getHead().next,
                        new SinglyLinkedList().append(1).append(3))
        );
    }

    @ParameterizedTest
    @MethodSource("testDeleteMiddleNode")
    void testDeleteMiddleNode(SinglyLinkedList input, Node ref, SinglyLinkedList expected) {
        Solution.deleteMiddleNode(input, ref);
        assert input.equals(expected);
    }

    private static Stream<Arguments> testPartition() {
        return Stream.of(
                Arguments.of(new SinglyLinkedList().append(3).append(5).append(8).append(5).append(10).
                        append(2).append(1), 5, new SinglyLinkedList().append(1).append(2).append(3).append(5).append(8)
                        .append(5).append(10))
        );
    }

    @ParameterizedTest
    @MethodSource("testPartition")
    void testPartition(SinglyLinkedList input, int partitionElement, SinglyLinkedList expected) {
        Solution.partition(input, partitionElement);
        assert input.equals(expected);
    }

    private static Stream<Arguments> testSumLists() {
        return Stream.of(
                Arguments.of(
                        new SinglyLinkedList().append(7).append(1).append(6),
                        new SinglyLinkedList().append(5).append(9).append(2),
                        new SinglyLinkedList().append(2).append(1).append(9)
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testSumLists")
    void testSumLists(SinglyLinkedList list1, SinglyLinkedList list2, SinglyLinkedList ans) {
        assert Solution.sumLists(list1, list2).equals(ans);
    }

    private static Stream<Arguments> testSumListsInForwardOrder() {
        return Stream.of(
                Arguments.of(
                        new SinglyLinkedList().append(9).append(2).append(3).append(4),
                        new SinglyLinkedList().append(9).append(6).append(7),
                        new SinglyLinkedList().append(1).append(0).append(2).append(0).append(1)
                )
        );
    }


    @ParameterizedTest
    @MethodSource("testSumListsInForwardOrder")
    void testSumListsInForwardOrder(SinglyLinkedList list1, SinglyLinkedList list2, SinglyLinkedList result) {
        assert Solution.sumListsInForwardOrder(list1, list2).equals(result);
    }

    private static Stream<Arguments> testIsPalindrome() {
        return Stream.of(
                Arguments.of(new SinglyLinkedList().append(1), true),
                Arguments.of(new SinglyLinkedList().append(1).append(2).append(3), false),
                Arguments.of(new SinglyLinkedList().append(1).append(2).append(1), true),
                Arguments.of(new SinglyLinkedList().append(1).append(2), false),
                Arguments.of(new SinglyLinkedList().append(1).append(1), true)
        );
    }

    @ParameterizedTest
    @MethodSource("testIsPalindrome")
    void testIsPalindrome(SinglyLinkedList list, boolean expected) {
        assert Solution.isPalindrome(list) == expected;
    }

    private static Stream<Arguments> testIsIntersecting() {
        SinglyLinkedList list1 = new SinglyLinkedList().append(1).append(2).append(3).append(4);
        SinglyLinkedList list2 = new SinglyLinkedList().append(9).append(8);
        list2.getHead().next.next = list1.getHead().next.next;
        list2.setSize(list2.getSize() + 2);
        SinglyLinkedList list3 = new SinglyLinkedList().append(9).append(8).append(7);
        return Stream.of(
                Arguments.of(list1, list2, true),
                Arguments.of(list1, list3, false)
        );
    }

    @ParameterizedTest
    @MethodSource("testIsIntersecting")
    void testIsIntersecting(SinglyLinkedList list1, SinglyLinkedList list2, boolean expected) {
        assert Solution.isIntersecting(list1, list2) == expected;
    }

    @Test
    void testGetLoopPoint() {
        SinglyLinkedList list = new SinglyLinkedList().append(1).append(2).append(3).append(4).append(5);
        Node i = list.getHead();
        while (i.next != null) {
            i = i.next;
        }

        i.next = list.getHead().next.next.next;
        assert Solution.getLoopPoint(list) == list.getHead().next.next.next;

        i.next = null;
        assert Solution.getLoopPoint(list) == null;
    }
}
