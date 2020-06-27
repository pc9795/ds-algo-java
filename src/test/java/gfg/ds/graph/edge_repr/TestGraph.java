package gfg.ds.graph.edge_repr;

import org.junit.jupiter.api.Test;

/**
 * Created By: Prashant Chaubey
 * Created On: 06-02-2020 22:57
 **/
class TestGraph {
    @Test
    void testIsCyclic() {
        UndirectedGraph graph = new UndirectedGraph(3);
        graph.addEdge(0, 1).addEdge(1, 2).addEdge(0, 2);
        assert graph.isCyclic();
    }

    @Test
    void testIsEulerianPathExists() {
        UndirectedGraph graph = new UndirectedGraph(5);
        graph.addEdge(1, 0).addEdge(0, 3).addEdge(3, 4).addEdge(2, 0).addEdge(1, 2);
        assert !graph.isEulerianCircuitExists();

        graph = new UndirectedGraph(5);
        graph.addEdge(1, 0).addEdge(0, 3).addEdge(3, 4).addEdge(4, 0).addEdge(2, 0).addEdge(1, 2);
        assert graph.isEulerianCircuitExists();
    }
}
