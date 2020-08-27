package gfg.ds.tree;

import gfg.ds.graph.adj_list.UndirectedGraph;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

class TestApplications {
  @Test
  void testCreateCentroidTree() {
    UndirectedGraph nryTree = new UndirectedGraph(16);
    nryTree.addEdge(0, 3);
    nryTree.addEdge(1, 3);
    nryTree.addEdge(2, 3);
    nryTree.addEdge(3, 4);
    nryTree.addEdge(4, 5);
    nryTree.addEdge(5, 6);
    nryTree.addEdge(5, 9);
    nryTree.addEdge(6, 7);
    nryTree.addEdge(6, 8);
    nryTree.addEdge(9, 10);
    nryTree.addEdge(10, 11);
    nryTree.addEdge(10, 12);
    nryTree.addEdge(11, 13);
    nryTree.addEdge(12, 14);
    nryTree.addEdge(12, 15);

    int centroidTreeVertex = Applications.centroidDecomposition(nryTree);

    assert centroidTreeVertex == 5;

    assert nryTree.getNeighbours(5).stream()
        .map(node -> node.vertex)
        .collect(Collectors.toSet())
        .equals(new HashSet<>(Arrays.asList(3, 6, 10)));
    assert nryTree.getNeighbours(3).stream()
        .map(node -> node.vertex)
        .collect(Collectors.toSet())
        .equals(new HashSet<>(Arrays.asList(0, 1, 2, 4, 5)));
    assert nryTree.getNeighbours(6).stream()
        .map(node -> node.vertex)
        .collect(Collectors.toSet())
        .equals(new HashSet<>(Arrays.asList(7, 8, 5)));
    assert nryTree.getNeighbours(10).stream()
        .map(node -> node.vertex)
        .collect(Collectors.toSet())
        .equals(new HashSet<>(Arrays.asList(9, 11, 12, 5)));
    assert nryTree.getNeighbours(11).stream()
        .map(node -> node.vertex)
        .collect(Collectors.toSet())
        .equals(new HashSet<>(Arrays.asList(13, 10)));
    assert nryTree.getNeighbours(12).stream()
        .map(node -> node.vertex)
        .collect(Collectors.toSet())
        .equals(new HashSet<>(Arrays.asList(14, 15, 10)));
  }
}
