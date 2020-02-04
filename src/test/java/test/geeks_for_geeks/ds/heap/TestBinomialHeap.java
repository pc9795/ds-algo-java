package test.geeks_for_geeks.ds.heap;

import geeks_for_geeks.ds.heap.BinomialHeap;
import geeks_for_geeks.ds.nodes.BinomialTreeNode;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 01-02-2020 13:19
 **/
class TestBinomialHeap {

    @Test
    void testUnion() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        /*
         *  12
         */
        BinomialTreeNode bt1 = new BinomialTreeNode(12);
        /*
         *   7
         *  /
         * 25
         */
        BinomialTreeNode bt2 = new BinomialTreeNode(7).merge(new BinomialTreeNode(25));

        /*
         *     15
         *    / |
         *  28  33
         *   |
         *  41
         */
        BinomialTreeNode bt3 = new BinomialTreeNode(15).merge(new BinomialTreeNode(33))
                .merge(new BinomialTreeNode(28).merge(new BinomialTreeNode(41)));


        BinomialHeap heap1 = new BinomialHeap();
        Field f = heap1.getClass().getDeclaredField("values");
        f.setAccessible(true);
        //noinspection unchecked
        List<BinomialTreeNode> values = (List) f.get(heap1);
        values.addAll(Arrays.asList(bt1, bt2, bt3));

        /*
         *  18
         */
        BinomialTreeNode bt4 = new BinomialTreeNode(18);
        /*
         *   3
         *  /
         * 37
         */
        BinomialTreeNode bt5 = new BinomialTreeNode(3).merge(new BinomialTreeNode(37));
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
        BinomialTreeNode bt6 = new BinomialTreeNode(6).merge(new BinomialTreeNode(44))
                .merge(new BinomialTreeNode(10).merge(new BinomialTreeNode(17)));
        bt6.merge(new BinomialTreeNode(29).merge(new BinomialTreeNode(31)).
                merge(new BinomialTreeNode(48).merge(new BinomialTreeNode(50))));
        bt6.merge(new BinomialTreeNode(8).
                merge(new BinomialTreeNode(22)).
                merge(new BinomialTreeNode(23).merge(new BinomialTreeNode(24))).
                merge(new BinomialTreeNode(30).merge(new BinomialTreeNode(32)).merge(new BinomialTreeNode(45).merge(new BinomialTreeNode(55))))
        );

        BinomialHeap heap2 = new BinomialHeap();
        //noinspection unchecked
        values = (List) f.get(heap2);
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
        //noinspection unchecked
        values = (List) f.get(heap1);
        assert values.get(0).data == 12;
        assert values.get(1).data == 3;
        assert values.get(2).data == 6;
    }
}
