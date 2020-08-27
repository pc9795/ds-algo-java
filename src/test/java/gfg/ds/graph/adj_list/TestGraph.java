package gfg.ds.graph.adj_list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class TestGraph {
  @Test
  void testAddEdgeToDAG() {
    Graph graph = new Graph(6);
    graph
        .addEdge(0, 5)
        .addEdge(0, 1)
        .addEdge(5, 1)
        .addEdge(5, 2)
        .addEdge(1, 2)
        .addEdge(1, 3)
        .addEdge(1, 4)
        .addEdge(2, 3)
        .addEdge(2, 4)
        .addEdge(3, 4);

    assert Applications.addEdgeInDAG(
            new GraphBase.Edge[] {
              new GraphBase.Edge(3, 0), new GraphBase.Edge(4, 5), new GraphBase.Edge(0, 2)
            },
            graph)
        .equals(
            new HashSet<>(
                Arrays.asList(
                    new GraphBase.Edge(0, 3), new GraphBase.Edge(5, 4), new GraphBase.Edge(0, 2))));
  }

  @Test
  void testBFS() {
    Graph g = new Graph(4);
    g.addEdge(0, 1).addEdge(0, 2).addEdge(1, 2).addEdge(2, 0).addEdge(2, 3).addEdge(3, 3);
    assert g.bfs().equals(Arrays.asList(0, 1, 2, 3));
  }

  @Test
  void testDFS() {
    Graph g = new Graph(4);
    g.addEdge(0, 1).addEdge(0, 2).addEdge(1, 2).addEdge(2, 0).addEdge(2, 3).addEdge(3, 3);
    assert g.dfs().equals(Arrays.asList(0, 1, 2, 3));
  }

  @Test
  void testIsCyclic() {
    Graph graph =
        new Graph(4)
            .addEdge(0, 1)
            .addEdge(0, 2)
            .addEdge(1, 2)
            .addEdge(2, 0)
            .addEdge(2, 3)
            .addEdge(3, 3);
    assert graph.isCyclic();

    graph = new Graph(4).addEdge(0, 1).addEdge(0, 2).addEdge(2, 3);
    assert !graph.isCyclic();
  }

  @Test
  void testIsCyclicUndirected() {
    UndirectedGraph graph = new UndirectedGraph(5);
    graph.addEdge(1, 0).addEdge(0, 3).addEdge(3, 4).addEdge(1, 2).addEdge(2, 0);
    assert graph.isCyclic();
  }

  @Test
  void testLongestPath() {
    Graph graph = new Graph(6);
    graph
        .addEdge(0, 1, 5)
        .addEdge(0, 2, 3)
        .addEdge(1, 3, 6)
        .addEdge(1, 2, 2)
        .addEdge(2, 4, 4)
        .addEdge(2, 5, 2)
        .addEdge(2, 3, 7)
        .addEdge(3, 5, 1)
        .addEdge(3, 4, -1)
        .addEdge(4, 5, -2);
    Assertions.assertArrayEquals(
        graph.longestPath(1), new int[] {Integer.MIN_VALUE, 0, 2, 9, 8, 10});
  }

  @Test
  void testTopologicalSort() {
    Graph g =
        new Graph(6)
            .addEdge(5, 2)
            .addEdge(5, 0)
            .addEdge(4, 0)
            .addEdge(4, 1)
            .addEdge(2, 3)
            .addEdge(3, 1);
    assert new ArrayList<>(g.topologicalSort()).equals(Arrays.asList(5, 4, 2, 3, 1, 0));
  }

  @Test
  void testCheckBipartite() {
    Graph graph = new Graph(6);
    graph.addEdge(0, 1).addEdge(1, 2).addEdge(2, 3).addEdge(3, 4).addEdge(4, 5).addEdge(5, 0);
    assert graph.isBipartite();

    graph = new Graph(5);
    graph.addEdge(0, 1).addEdge(1, 2).addEdge(2, 3).addEdge(3, 4).addEdge(4, 0);
    assert !graph.isBipartite();
  }

  @Test
  void testGetEulerPath() {
    UndirectedGraph graph = new UndirectedGraph(5);
    graph.addEdge(1, 0).addEdge(0, 3).addEdge(3, 4).addEdge(2, 0).addEdge(1, 2);
    List<Pair<Integer, Integer>> expected =
        Arrays.asList(
            new Pair<>(0, 1),
            new Pair<>(1, 2),
            new Pair<>(2, 0),
            new Pair<>(0, 3),
            new Pair<>(3, 4));
    assert graph.getEulerPath().equals(expected);

    graph.addEdge(1, 0).addEdge(0, 3).addEdge(3, 4).addEdge(4, 0).addEdge(2, 0).addEdge(1, 2);
    expected =
        Arrays.asList(
            new Pair<>(0, 1),
            new Pair<>(1, 2),
            new Pair<>(2, 0),
            new Pair<>(0, 3),
            new Pair<>(3, 4),
            new Pair<>(4, 0));
    assert graph.getEulerPath().equals(expected);
  }

  @Test
  void testGetBridges() {
    UndirectedGraph graph = new UndirectedGraph(5);
    graph.addEdge(1, 0).addEdge(0, 3).addEdge(3, 4).addEdge(2, 0).addEdge(1, 2);
    boolean ans =
        GraphBase.getBridges(graph)
            .equals(Arrays.asList(new GraphBase.Edge(3, 4), new GraphBase.Edge(0, 3)));
    assert ans;
  }

  @Test
  void testGetArticulationPoints() {
    UndirectedGraph graph = new UndirectedGraph(5);
    graph.addEdge(1, 0).addEdge(0, 3).addEdge(3, 4).addEdge(2, 0).addEdge(1, 2);
    boolean ans = Graph.getArticulationPoints(graph).equals(new HashSet<>(Arrays.asList(0, 3)));
    assert ans;
  }
}
