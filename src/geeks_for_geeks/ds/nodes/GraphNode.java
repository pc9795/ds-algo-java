package geeks_for_geeks.ds.nodes;

/**
 * Created By: Prashant Chaubey
 * Created On: 31-10-2018 01:18
 **/
public class GraphNode {
    public int vertex;
    public int weight;

    public GraphNode(int vertex) {
        this.vertex = vertex;
        this.weight = 0;
    }

    public GraphNode(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "GraphNode{" +
                "vertex=" + vertex +
                ", weight=" + weight +
                '}';
    }
}
