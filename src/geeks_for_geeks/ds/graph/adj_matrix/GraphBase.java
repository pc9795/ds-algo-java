package geeks_for_geeks.ds.graph.adj_matrix;

/**
 * Created By: Prashant Chaubey
 * Created On: 12-01-2019 18:53
 * Purpose: TODO:
 **/
public abstract class GraphBase {
    public int values[][];

    public GraphBase(int vertices) {
        values = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (i == j) {
                    values[i][j] = 0;
                } else {
                    values[i][j] = Integer.MAX_VALUE;
                }
            }
        }
    }

    public abstract GraphBase addEdge(int src, int dest, int weight);

    public abstract GraphBase addEdge(int src, int dest);

    public abstract boolean isEdge(int src, int dest);

    public abstract int vertices();
}
