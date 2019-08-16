package geeks_for_geeks.ds.graph.adj_matrix;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-10-2018 01:16
 **/
public class UndirectedGraph extends GraphBase {

    public UndirectedGraph(int vertices) {
        super(vertices);
    }

    @Override
    public GraphBase addEdge(int src, int dest, int weight) {
        assert (src >= 0 && dest >= 0 && src < values.length && dest < values.length);

        values[src][dest] = weight;
        values[dest][src] = weight;
        return this;
    }

    @Override
    public UndirectedGraph addEdge(int src, int dest) {
        assert (src >= 0 && dest >= 0 && src < values.length && dest < values.length);

        values[src][dest] = 1;
        values[dest][src] = 1;
        return this;
    }

    @Override
    public int vertices() {
        return values.length;
    }


}

