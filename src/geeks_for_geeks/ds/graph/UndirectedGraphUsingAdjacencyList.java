package geeks_for_geeks.ds.graph;

import geeks_for_geeks.ds.nodes.GraphNode;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-10-2018 01:16
 **/
public class UndirectedGraphUsingAdjacencyList extends GraphUsingAdjacencyList {
    public UndirectedGraphUsingAdjacencyList(int vertices) {
        super(vertices);
    }

    @Override
    public UndirectedGraphUsingAdjacencyList addEdge(int src, int dest) {
        if (src >= values.length || dest >= values.length) {
            throw new RuntimeException("Vertex is out of bound");
        }
        values[src].add(new GraphNode(dest));
        values[dest].add(new GraphNode(src));
        return this;
    }

    @Override
    public UndirectedGraphUsingAdjacencyList addEdge(int src, int dest, int weight) {
        if (src >= values.length || dest >= values.length) {
            throw new RuntimeException("Vertex is out of bound");
        }
        values[src].add(new GraphNode(dest, weight));
        values[dest].add(new GraphNode(src, weight));
        return this;
    }
}
