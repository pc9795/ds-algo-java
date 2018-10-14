package geeks_for_geeks.ds.graph;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-10-2018 01:16
 **/
class UndirectedGraph extends Graph {
    public UndirectedGraph(int vertices) {
        super(vertices);
    }

    @Override
    public UndirectedGraph addEdge(int src, int dest) {
        if (src >= values.length || dest >= values.length) {
            throw new RuntimeException("Vertex is out of bound");
        }
        values[src][dest] = 1;
        values[dest][src] = 1;
        return this;
    }
}

