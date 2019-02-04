package binary_tree;

import geeks_for_geeks.ds.tree.binary_tree.BinaryTree;
import geeks_for_geeks.ds.nodes.BTNode;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 26-01-2019 19:46
 * Purpose: TODO:
 **/
public class TestBinaryTree {

    @Test
    void testToList() {
        BinaryTree bt = new BinaryTree(10);
        bt.root.left = new BTNode(12);
        bt.root.right = new BTNode(15);
        bt.root.left.left = new BTNode(25);
        bt.root.left.right = new BTNode(30);
        bt.root.right.left = new BTNode(36);
        System.out.println(bt.toList());
    }
}
