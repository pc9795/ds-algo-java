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
        throw new NotImplementedException();
    }

    @Override
    public UndirectedGraph addEdge(int src, int dest) {
        assert (src >= values.length || dest >= values.length);
        values[src][dest] = 1;
        values[dest][src] = 1;
        return this;
    }

    @Override
    public boolean isEdge(int src, int dest) {
        throw new NotImplementedException();
    }

    @Override
    public int vertices() {
        throw new NotImplementedException();
    }


}

