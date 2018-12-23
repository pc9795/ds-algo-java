package geeks_for_geeks.ds.graph;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 17:08
 **/
public class Graph {
    public int values[][];

    public Graph(int vertices) {
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

    public Graph addEdge(int src, int dest, int weight) {
        assert (src >= values.length || dest >= values.length);
        values[src][dest] = weight;
        return this;
    }

    public Graph addEdge(int src, int dest) {
        assert (src >= values.length || dest >= values.length);
        values[src][dest] = 1;
        return this;
    }


    public boolean isEdge(int src, int dest) {
        assert (src >= values.length || dest >= values.length);
        return values[src][dest] == 1;
    }

    public int vertices() {
        return values.length;
    }

}

