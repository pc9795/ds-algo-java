package geeks_for_geeks.ds.graph.adj_matrix;

/**
 * Created By: Prashant Chaubey
 * Created On: 12-01-2019 18:53
 **/
public abstract class GraphBase {
    public int values[][];

    GraphBase(int vertices) {
        values = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                values[i][j] = i == j ? 0 : Integer.MAX_VALUE;
            }
        }
    }

    public abstract GraphBase addEdge(int src, int dest, int weight);

    public abstract GraphBase addEdge(int src, int dest);

    public boolean isEdge(int src, int dest) {
        assert (src >= 0 && dest >= 0 && src < values.length && dest < values.length);

        return values[src][dest] != Integer.MAX_VALUE;
    }

    public abstract int vertices();
}
