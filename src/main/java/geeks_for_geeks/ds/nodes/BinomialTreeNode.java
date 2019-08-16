package geeks_for_geeks.ds.nodes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 18-09-2018 01:44
 **/
public class BinomialTreeNode {
    public int data;
    public List<BinomialTreeNode> children = new ArrayList<>();

    public BinomialTreeNode(int data) {
        this.data = data;
    }

    public int degree() {
        return children.size();
    }

    @Override
    public String toString() {
        return "BinomialTreeNode{" +
                "data=" + data +
                ", children=" + children +
                '}';
    }
}
