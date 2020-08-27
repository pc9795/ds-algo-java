package gfg.ds.tree.binary_search_tree.balanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

class TestTreap {

  private Treap treap;

  @BeforeEach
  void setupTest() {
    List<Integer> priorites = Arrays.asList(73, 48, 92, 21, 50, 55, 44);
    ListIterator<Integer> prioritiesIterator = priorites.listIterator();
    treap = new Treap(prioritiesIterator::next);

    treap.insert(50, 30, 20, 40, 70, 60, 80);
  }

  @Test
  void testInsert() {
    /*
     *          20(92)
     *             \
     *            50(73)
     *            /    \
     *         30(48)  60(55)
     *            \       \
     *           40(21)  70(50)
     *                      \
     *                     80(44)
     */
    assert treap.getRoot().getData() == 20;
    assert treap.getRoot().getPriority() == 92;
    assert treap.getRoot().getRight().getData() == 50;
    assert treap.getRoot().getRight().getPriority() == 73;
    assert treap.getRoot().getRight().getLeft().getData() == 30;
    assert treap.getRoot().getRight().getLeft().getPriority() == 48;
    assert treap.getRoot().getRight().getLeft().getRight().getData() == 40;
    assert treap.getRoot().getRight().getLeft().getRight().getPriority() == 21;
    assert treap.getRoot().getRight().getRight().getData() == 60;
    assert treap.getRoot().getRight().getRight().getPriority() == 55;
    assert treap.getRoot().getRight().getRight().getRight().getData() == 70;
    assert treap.getRoot().getRight().getRight().getRight().getPriority() == 50;
    assert treap.getRoot().getRight().getRight().getRight().getRight().getData() == 80;
    assert treap.getRoot().getRight().getRight().getRight().getRight().getPriority() == 44;
  }

  @Test
  void testDelete1() {
    treap.delete(20);

    /*
     *            50(73)
     *            /    \
     *         30(48)  60(55)
     *            \       \
     *           40(21)  70(50)
     *                      \
     *                     80(44)
     */
    assert treap.getRoot().getData() == 50;
    assert treap.getRoot().getPriority() == 73;
    assert treap.getRoot().getLeft().getData() == 30;
    assert treap.getRoot().getLeft().getPriority() == 48;
    assert treap.getRoot().getLeft().getRight().getData() == 40;
    assert treap.getRoot().getLeft().getRight().getPriority() == 21;
    assert treap.getRoot().getRight().getData() == 60;
    assert treap.getRoot().getRight().getPriority() == 55;
    assert treap.getRoot().getRight().getRight().getData() == 70;
    assert treap.getRoot().getRight().getRight().getPriority() == 50;
    assert treap.getRoot().getRight().getRight().getRight().getData() == 80;
    assert treap.getRoot().getRight().getRight().getRight().getPriority() == 44;
  }

  @Test
  void testDelete2() {
    treap.delete(20);
    treap.delete(30);

    /*
     *            50(73)
     *            /    \
     *         40(21)  60(55)
     *                    \
     *                   70(50)
     *                      \
     *                     80(44)
     */
    assert treap.getRoot().getData() == 50;
    assert treap.getRoot().getPriority() == 73;
    assert treap.getRoot().getLeft().getData() == 40;
    assert treap.getRoot().getLeft().getPriority() == 21;
    assert treap.getRoot().getRight().getData() == 60;
    assert treap.getRoot().getRight().getPriority() == 55;
    assert treap.getRoot().getRight().getRight().getData() == 70;
    assert treap.getRoot().getRight().getRight().getPriority() == 50;
    assert treap.getRoot().getRight().getRight().getRight().getData() == 80;
    assert treap.getRoot().getRight().getRight().getRight().getPriority() == 44;
  }

  @Test
  void testDelete3() {
    treap.delete(20);
    treap.delete(30);
    treap.delete(50);

    /*
     *            60(55)
     *            /    \
     *         40(21)  70(50)
     *                    \
     *                   80(44)
     */
    assert treap.getRoot().getData() == 60;
    assert treap.getRoot().getPriority() == 55;
    assert treap.getRoot().getLeft().getData() == 40;
    assert treap.getRoot().getLeft().getPriority() == 21;
    assert treap.getRoot().getRight().getData() == 70;
    assert treap.getRoot().getRight().getPriority() == 50;
    assert treap.getRoot().getRight().getRight().getData() == 80;
    assert treap.getRoot().getRight().getRight().getPriority() == 44;
  }

  @Test
  void testSearch() {
    assert treap.search(60);
    assert !treap.search(100);
  }
}
