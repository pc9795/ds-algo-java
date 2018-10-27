package geeks_for_geeks.ds.nodes;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-09-2018 18:35
 **/
//I could try to make generic using Object but it will cause problem in comparison.
public class Node {
    public Node next;
    public int data;

    public Node(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ItemNode{" +
                "data=" + data +
                '}';
    }
}
