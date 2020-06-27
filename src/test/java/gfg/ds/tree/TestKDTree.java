package gfg.ds.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 25-06-2020 23:20
 **/
class TestKDTree {

    private KDTree kdTree1;
    private KDTree kdTree2;

    @BeforeEach
    void setup() {
        kdTree1 = new KDTree(2);
        kdTree1.insert(KDTree.KDNode.of(2, 3, 6));
        kdTree1.insert(KDTree.KDNode.of(2, 17, 15));
        kdTree1.insert(KDTree.KDNode.of(2, 13, 15));
        kdTree1.insert(KDTree.KDNode.of(2, 6, 12));
        kdTree1.insert(KDTree.KDNode.of(2, 9, 1));
        kdTree1.insert(KDTree.KDNode.of(2, 2, 7));
        kdTree1.insert(KDTree.KDNode.of(2, 10, 19));

        kdTree2 = new KDTree(2);
        kdTree2.insert(KDTree.KDNode.of(2, 30, 40));
        kdTree2.insert(KDTree.KDNode.of(2, 5, 25));
        kdTree2.insert(KDTree.KDNode.of(2, 70, 70));
        kdTree2.insert(KDTree.KDNode.of(2, 10, 12));
        kdTree2.insert(KDTree.KDNode.of(2, 50, 30));
        kdTree2.insert(KDTree.KDNode.of(2, 35, 45));

    }

    @Test
    void testInsert() {
        assert kdTree1.root.equals(KDTree.KDNode.of(2, 3, 6));
        assert kdTree1.root.left.equals(KDTree.KDNode.of(2, 2, 7));
        assert kdTree1.root.right.equals(KDTree.KDNode.of(2, 17, 15));
        assert kdTree1.root.right.left.equals(KDTree.KDNode.of(2, 6, 12));
        assert kdTree1.root.right.right.equals(KDTree.KDNode.of(2, 13, 15));
        assert kdTree1.root.right.left.right.equals(KDTree.KDNode.of(2, 9, 1));
        assert kdTree1.root.right.right.left.equals(KDTree.KDNode.of(2, 10, 19));
    }

    @Test
    void testSearch() {
        assert kdTree1.search(KDTree.KDNode.of(2, 10, 19));
        assert !kdTree1.search(KDTree.KDNode.of(2, 12, 19));
    }

    @Test
    void testGetMin() {
        assert kdTree2.getMin(0).get(0) == 5;
        assert kdTree2.getMin(1).get(1) == 12;
    }

    @Test
    void testGetDelete1() {
        boolean ans = kdTree2.delete(KDTree.KDNode.of(2, 30, 40)).root.equals(KDTree.KDNode.of(2, 35, 45));
        assert ans;
    }

    @Test
    void testGetDelete2() {
        boolean ans = kdTree2.delete(KDTree.KDNode.of(2, 70, 70)).root.right.right.equals(KDTree.KDNode.of(2, 35, 45));
        assert ans;
    }
}
