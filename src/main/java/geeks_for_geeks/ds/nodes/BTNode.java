package geeks_for_geeks.ds.nodes;

import java.util.Objects;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 00:52
 * //NOTE: I could try to make it using generics but it will need extra code during comparison.
 **/

public class BTNode {
    public int data;
    public BTNode left;
    public BTNode right;
    public BTNode parent;
    public int height;

    public BTNode(int data) {
        this.data = data;
    }

    public BTNode(int data, BTNode left, BTNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public BTNode(int data, BTNode left, BTNode right, BTNode parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public BTNode(DNode node) {
        this.data = node.data;
    }

    @Override
    public String toString() {
        return "BTNode{" + "data=" + data + ", left=" + (left != null ? left.data : null) +
                ", right=" + (right != null ? right.data : null) + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BTNode btNode = (BTNode) o;
        return data == btNode.data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
