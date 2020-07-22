package gfg.ds.advanced;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created By: Prashant Chaubey
 * Created On: 22-07-2020 22:56
 **/
class TestSparseSet {

    @Test
    void testSearch() {
        SparseSet set = new SparseSet(5, 10);
        set.add(5).add(3).add(9).add(10);

        assert set.search(5);
        assert set.search(3);
        assert set.search(9);
        assert set.search(10);
    }

    @Test
    void testDelete() {
        SparseSet set = new SparseSet(5, 10);
        set.add(5).add(3).add(9).add(10);
        set.delete(9);

        assert set.search(5);
        assert set.search(3);
        assert !set.search(9);
        assert set.search(10);
    }

    @Test
    void testClear() {
        SparseSet set = new SparseSet(5, 10);
        set.add(5).add(3).add(9).add(10);
        set.clear();

        assert !set.search(5);
        assert !set.search(3);
        assert !set.search(9);
        assert !set.search(10);
    }

    @Test
    void testUnion() {
        SparseSet set = new SparseSet(5, 10);
        set.add(5).add(3).add(10);

        SparseSet set2 = new SparseSet(5, 100);
        set2.add(4).add(3).add(7).add(100);

        SparseSet union = set.union(set2);

        assert union.values().equals(new HashSet<>(Arrays.asList(5, 3, 10, 4, 7, 100)));
    }

    @Test
    void testIntersection() {
        SparseSet set = new SparseSet(5, 10);
        set.add(5).add(3).add(10);

        SparseSet set2 = new SparseSet(5, 100);
        set2.add(4).add(3).add(7).add(100);

        assert set.intersection(set2).values().equals(new HashSet<>(Collections.singletonList(3)));
    }
}
