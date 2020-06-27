package gfg.ds.graph.adj_matrix;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-10-2018 01:16
 **/
public class UndirectedGraph extends GraphBase {
    public UndirectedGraph(int vertices) {
        super(vertices);
    }

    @Override
    public UndirectedGraph addEdge(int src, int dest, int weight) {
        assert (src >= 0 && dest >= 0 && src < values.length && dest < values.length);

        values[src][dest] = weight;
        values[dest][src] = weight;
        return this;
    }

    @Override
    public UndirectedGraph addEdge(int src, int dest) {
        return addEdge(src, dest, 0);
    }

    @Override
    public int vertices() {
        return values.length;
    }
}

