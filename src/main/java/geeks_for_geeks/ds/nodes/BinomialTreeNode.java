package geeks_for_geeks.ds.nodes;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created By: Prashant Chaubey
 * Created On: 18-09-2018 01:44
 **/
public class BinomialTreeNode {
    public int data;
    private Deque<BinomialTreeNode> children = new LinkedList<>();
    private BinomialTreeNode parent;

    public BinomialTreeNode(int data) {
        this.data = data;
    }

    public BinomialTreeNode divide() {
        if (children.size() == 0) {
            return null;
        }
        BinomialTreeNode child = children.removeFirst();
        child.parent = null;
        return child;
    }

    public BinomialTreeNode merge(BinomialTreeNode other) {
        assert degree() == other.degree() : "Only trees of same degree can be merged";
        //So that after merging no can mutate the structure of sub-tree.
        assert other.parent == null : "Only root trees can be merged";

        children.addFirst(other);
        other.parent = this;
        return this;
    }

    public int degree() {
        return children.size();
    }

    @Override
    public String toString() {
        return "BinomialTreeNode{" +
                "data=" + data +
                '}';
    }
}
