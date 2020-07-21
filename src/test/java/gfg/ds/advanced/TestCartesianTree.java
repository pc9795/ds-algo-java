package gfg.ds.advanced;

import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 21-07-2020 00:44
 **/
class TestCartesianTree {

    @Test
    void testInsert() {
        CartesianTree cartesianTree = new CartesianTree();

        cartesianTree.insert(5, 10, 40, 30, 28);

        assert cartesianTree.getRoot().getData() == 40;
        assert cartesianTree.getRoot().getLeft().getData() == 10;
        assert cartesianTree.getRoot().getLeft().getLeft().getData() == 5;
        assert cartesianTree.getRoot().getRight().getData() == 30;
        assert cartesianTree.getRoot().getRight().getRight().getData() == 28;
        assert cartesianTree.getRightMost().getData() == 28;
    }
}
