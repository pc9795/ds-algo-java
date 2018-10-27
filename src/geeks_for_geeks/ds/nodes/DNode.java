package geeks_for_geeks.ds.nodes;

/**
 * Created By: Prashant Chaubey
 * Created On: 13-10-2018 17:48
 **/
public class DNode {
    public DNode prev;
    public DNode next;
    public int data;

    public DNode(int data) {
        this.data=data;
    }

    @Override
    public String toString() {
        return "DNode{" +
                "prev=" + prev +
                ", next=" + next +
                ", data=" + data +
                "} ";
    }
}
