package gfg.ds.heap;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

class TestBinomialHeap {

  @Test
  void testUnion()
      throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException,
          InvocationTargetException {
    /*
     *  12
     */
    BinomialHeap.BinomialTreeNode bt1 = new BinomialHeap.BinomialTreeNode(12);
    /*
     *   7
     *  /
     * 25
     */
    BinomialHeap.BinomialTreeNode bt2 =
        new BinomialHeap.BinomialTreeNode(7).merge(new BinomialHeap.BinomialTreeNode(25));

    /*
     *     15
     *    / |
     *  28  33
     *   |
     *  41
     */
    BinomialHeap.BinomialTreeNode bt3 =
        new BinomialHeap.BinomialTreeNode(15)
            .merge(new BinomialHeap.BinomialTreeNode(33))
            .merge(
                new BinomialHeap.BinomialTreeNode(28).merge(new BinomialHeap.BinomialTreeNode(41)));

    BinomialHeap heap1 = new BinomialHeap();
    Field f = heap1.getClass().getDeclaredField("values");
    f.setAccessible(true);
    //noinspection unchecked
    List<BinomialHeap.BinomialTreeNode> values = (List<BinomialHeap.BinomialTreeNode>) f.get(heap1);
    values.addAll(Arrays.asList(bt1, bt2, bt3));

    /*
     *  18
     */
    BinomialHeap.BinomialTreeNode bt4 = new BinomialHeap.BinomialTreeNode(18);
    /*
     *   3
     *  /
     * 37
     */
    BinomialHeap.BinomialTreeNode bt5 =
        new BinomialHeap.BinomialTreeNode(3).merge(new BinomialHeap.BinomialTreeNode(37));
    /*
     *                          6
     *               /    /   /  |
     *             8    29  10  44
     *       /   / |    / |   |
     *      30  23 22  48 31  17
     *    /  |   |      |
     *   45 32  24     50
     *   |
     *  55
     */
    BinomialHeap.BinomialTreeNode bt6 =
        new BinomialHeap.BinomialTreeNode(6)
            .merge(new BinomialHeap.BinomialTreeNode(44))
            .merge(
                new BinomialHeap.BinomialTreeNode(10).merge(new BinomialHeap.BinomialTreeNode(17)));
    bt6.merge(
        new BinomialHeap.BinomialTreeNode(29)
            .merge(new BinomialHeap.BinomialTreeNode(31))
            .merge(
                new BinomialHeap.BinomialTreeNode(48)
                    .merge(new BinomialHeap.BinomialTreeNode(50))));
    bt6.merge(
        new BinomialHeap.BinomialTreeNode(8)
            .merge(new BinomialHeap.BinomialTreeNode(22))
            .merge(
                new BinomialHeap.BinomialTreeNode(23).merge(new BinomialHeap.BinomialTreeNode(24)))
            .merge(
                new BinomialHeap.BinomialTreeNode(30)
                    .merge(new BinomialHeap.BinomialTreeNode(32))
                    .merge(
                        new BinomialHeap.BinomialTreeNode(45)
                            .merge(new BinomialHeap.BinomialTreeNode(55)))));

    BinomialHeap heap2 = new BinomialHeap();
    values = (List<BinomialHeap.BinomialTreeNode>) f.get(heap2);
    values.addAll(Arrays.asList(bt4, bt5, bt6));

    Method m = heap2.getClass().getDeclaredMethod("union", BinomialHeap.class);
    m.setAccessible(true);
    m.invoke(heap1, heap2);

    /*
     *    12->                   3->                          6
     *     |               /   /  |                /    /   /  |
     *    18              15   7  37              8    29  10  44
     *                  / |   |           /   / |    / |   |
     *                28 33  25         30  23 22  48 31  17
     *                  |              /  |   |      |
     *                 41             45 32  24     50
     *                                |
     *                               55
     */
    assert heap1.size() == 3;
    values = (List<BinomialHeap.BinomialTreeNode>) f.get(heap1);
    assert values.get(0).data == 12;
    assert values.get(1).data == 3;
    assert values.get(2).data == 6;
  }
}
