package geeks_for_geeks.ds.nodes;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 00:52
 **/
public class BTNode {
    //    For comparison can't keep it as Object.
    public int data;
    public BTNode left;
    public BTNode right;

    public BTNode(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BTNode{" +
                "data=" + data +
                '}';
    }
}
