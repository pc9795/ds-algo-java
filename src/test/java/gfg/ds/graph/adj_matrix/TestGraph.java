package gfg.ds.graph.adj_matrix;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class TestGraph {

  @Test
  void testSnakeAndLadderProblem() {
    int n = 30;
    int moves[] = new int[n];
    Arrays.fill(moves, -1);
    // ladders
    moves[2] = 21;
    moves[4] = 7;
    moves[10] = 25;
    moves[19] = 28;
    // Snakes
    moves[26] = 0;
    moves[20] = 8;
    moves[16] = 3;
    moves[18] = 6;
    Assertions.assertEquals(Applications.snakeAndLadderProblem(n, moves), 3);
  }

  @Test
  void testMinimizeCashFlow() {
    Graph graph = new Graph(3);
    graph.addEdge(0, 1, 1000).addEdge(0, 2, 2000).addEdge(1, 2, 5000);
    assert Applications.minimizeCashFlow(graph)
        .equals(
            Arrays.asList(new Pair<>(1, new Pair<>(2, 4000)), new Pair<>(0, new Pair<>(2, 3000))));
  }

  @Test
  void testBoggle() {
    Set<String> set = new HashSet<>();
    set.add("GEEKS");
    set.add("FOR");
    set.add("QUIZ");
    set.add("GO");
    char[][] matrix =
        new char[][] {
          {'G', 'I', 'Z'},
          {'U', 'E', 'K'},
          {'Q', 'S', 'E'}
        };
    assert Applications.boggle(matrix, set).equals(new HashSet<>(Arrays.asList("QUIZ", "GEEKS")));
  }

  @Test
  void testGraphOperations() {
    Graph graph = new Graph(3);
    assert !graph.isEdge(0, 1);
    graph.addEdge(0, 1);
    assert graph.isEdge(0, 1);
    assert !graph.isEdge(1, 0);

    assert !graph.isEdge(1, 2);
    graph.addEdge(1, 2, 5);
    assert graph.isEdge(1, 2);
    assert !graph.isEdge(2, 1);
  }

  @Test
  void testUndirectedGraphOperations() {
    UndirectedGraph graph = new UndirectedGraph(3);
    assert !graph.isEdge(0, 1);
    graph.addEdge(0, 1);
    assert graph.isEdge(0, 1);
    assert graph.isEdge(1, 0);

    assert !graph.isEdge(1, 2);
    graph.addEdge(1, 2, 5);
    assert graph.isEdge(1, 2);
    assert graph.isEdge(2, 1);
  }
}
