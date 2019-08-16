package geeks_for_geeks.ds.nodes;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-11-2018 01:16
 **/
public class PrimNode {
    public int vertex;
    public int key;
    public int parent;

    public PrimNode(int vertex, int key) {
        this.vertex = vertex;
        this.key = key;
    }

    @Override
    public String toString() {
        return "PrimNode{" +
                "vertex=" + vertex +
                ", key=" + key +
                '}';
    }
}
