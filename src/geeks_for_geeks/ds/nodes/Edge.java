package geeks_for_geeks.ds.nodes;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-10-2018 22:26
 **/
public class Edge {
    public int src;
    public int weight;
    public int dest;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public Edge(int src, int dest) {
        this.src = src;
        this.dest = dest;
        this.weight = 0;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "src=" + src +
                ", weight=" + weight +
                ", dest=" + dest +
                '}';
    }
}
