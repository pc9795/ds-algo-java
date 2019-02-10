package graph;

import geeks_for_geeks.ds.graph.GraphUsingAdjListApplications;
import geeks_for_geeks.ds.graph.adj_list.Graph;
import geeks_for_geeks.ds.nodes.Edge;
import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 11-02-2019 02:48
 * Purpose: TODO:
 **/
public class TestGraphUsingAdjList {
    @Test
    void testAddEdgeToDAG() {
        Graph graph = new Graph(6);
        graph.addEdge(0, 5).addEdge(0, 1).addEdge(5, 1).addEdge(5, 2).
                addEdge(1, 2).addEdge(1, 3).addEdge(1, 4).addEdge(2, 3).
                addEdge(2, 4).addEdge(3, 4);

        GraphUsingAdjListApplications.addEdgeInDAG(new Edge[]{
                new Edge(3, 0), new Edge(4, 5), new Edge(0, 2)
        }, graph);
    }
}
