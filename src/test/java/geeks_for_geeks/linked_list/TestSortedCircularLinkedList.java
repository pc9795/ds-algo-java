package geeks_for_geeks.linked_list;

import geeks_for_geeks.ds.linked_list.SortedCircularLinkedList;
import geeks_for_geeks.ds.nodes.Node;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 23-01-2020 06:58
 * Purpose: Test
 **/
class TestSortedCircularLinkedList {
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
