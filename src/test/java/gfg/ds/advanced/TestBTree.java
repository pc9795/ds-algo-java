package gfg.ds.advanced;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created By: Prashant Chaubey
 * Created On: 28-06-2020 11:03
 **/
class TestBTree {

    private BTree bTree1;
    private BTree bTree2;

    @BeforeEach
    void setUp() {
        bTree1 = new BTree(3);
        bTree1.insert(10, 20, 30, 40, 50, 60, 70, 80, 90, 51, 52, 53, 54);
        bTree2 = new BTree(3);
        bTree2.insert(1, 3, 7, 10, 11, 13, 14, 15, 18, 16, 19, 24, 25, 26, 21, 4, 5, 20, 22, 2, 17, 12, 6);
    }

    @Test
    void testInsert() {
        assert Arrays.equals(bTree1.root.values, new int[]{30, 51, 60, 0, 0});
        assert Arrays.equals(bTree1.root.children[0].values, new int[]{10, 20, 0, 0, 0});
        assert Arrays.equals(bTree1.root.children[1].values, new int[]{40, 50, 0, 0, 0});
        assert Arrays.equals(bTree1.root.children[2].values, new int[]{52, 53, 54, 0, 0});
        assert Arrays.equals(bTree1.root.children[3].values, new int[]{70, 80, 90, 0, 0});
    }

    @Test
    void testTraversal() {
        assert bTree1.traversal().equals(Arrays.asList(10, 20, 30, 40, 50, 51, 52, 53, 54, 60, 70, 80, 90));
    }

    @Test
    void testSearch() {
        assert bTree1.search(80);
        assert !bTree1.search(81);
    }

    @Test
    void testDelete() {
        // https://www.cs.usfca.edu/~galles/visualization/BTree.html
        assert Arrays.equals(bTree2.root.values, new int[]{16, 0, 0, 0, 0});
        assert Arrays.equals(bTree2.root.children[0].values, new int[]{3, 7, 13, 0, 0});
        assert Arrays.equals(bTree2.root.children[1].values, new int[]{20, 24, 0, 0, 0});
        assert Arrays.equals(bTree2.root.children[0].children[0].values, new int[]{1, 2, 0, 0, 0});
        assert Arrays.equals(bTree2.root.children[0].children[1].values, new int[]{4, 5, 6, 0, 0});
        assert Arrays.equals(bTree2.root.children[0].children[2].values, new int[]{10, 11, 12, 0, 0});
        assert Arrays.equals(bTree2.root.children[0].children[3].values, new int[]{14, 15, 0, 0, 0});
        assert Arrays.equals(bTree2.root.children[1].children[0].values, new int[]{17, 18, 19, 0, 0});
        assert Arrays.equals(bTree2.root.children[1].children[1].values, new int[]{21, 22, 0, 0, 0});
        assert Arrays.equals(bTree2.root.children[1].children[2].values, new int[]{25, 26, 0, 0, 0});

        bTree2.delete(6).delete(13).delete(7).delete(4).delete(2).delete(16);
    }
}
