package geeks_for_geeks.linked_list;

import geeks_for_geeks.ds.linked_list.DoublyLinkedList;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 18-10-2019 19:27
 * Purpose: TODO:
 **/
public class TestDoublyLinkedList {
    @Test
    void testReverse() {
        DoublyLinkedList list = new DoublyLinkedList();
        list.append(1, 2, 3, 4, 5);
        list.reverse();
        DoublyLinkedList expected = new DoublyLinkedList();
        expected.append(5, 4, 3, 2, 1);
        assert list.equals(expected);
    }
}
