package geeks_for_geeks.ds.nodes;

import java.util.Objects;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-10-2018 17:48
 **/
public class DNode {
    public DNode prev;
    public DNode next;
    public int data;

    public DNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DNode{" +
                "prev=" + prev +
                ", next=" + next +
                ", data=" + data +
                "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DNode dNode = (DNode) o;
        return data == dNode.data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }
}
