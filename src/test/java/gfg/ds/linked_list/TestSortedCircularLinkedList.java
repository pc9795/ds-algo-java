package gfg.ds.linked_list;

import org.junit.jupiter.api.Test;

class TestSortedCircularLinkedList {
  @Test
  void testSortedCircularLinkedList() {
    SortedCircularLinkedList list = new SortedCircularLinkedList().insert(5, 6, 1, 4, 2, 3);
    int[] expected = {1, 2, 3, 4, 5, 6};
    SinglyLinkedList.Node curr = list.last.next;
    int i = 0;
    do {
      assert curr.data == expected[i];
      i++;
      curr = curr.next;
    } while (curr != list.last.next);
  }
}
