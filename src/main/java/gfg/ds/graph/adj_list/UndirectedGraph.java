package gfg.ds.graph.adj_list;

import utils.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UndirectedGraph extends GraphBase {

  public UndirectedGraph(int vertices) {
    super(vertices);
  }

  @Override
  public UndirectedGraph addEdge(int src, int dest) {
    return addEdge(src, dest, 0);
  }

  @Override
  public void removeEdge(int src, int dest) {
    assert !(src >= values.length || dest >= values.length);

    values[src].remove(new GraphNode(dest));
    values[dest].remove(new GraphNode(src));
  }

  @Override
  public UndirectedGraph addEdge(int src, int dest, int weight) {
    assert !(src >= values.length || dest >= values.length);

    values[src].add(new GraphNode(dest, weight));
    values[dest].add(new GraphNode(src, weight));
    return this;
  }

  /** t=O(V+E) */
  @Override
  public boolean isEulerian() {
    // Assuming that there is no vertex with 0 edges. A graph with no edges is also eulerian. If
    // there are such edges
    // then the below line will fail as it doesn't follow such discrimination. USE CAREFULLY WITH
    // USE-CASE.
    assert isStronglyConnected() : "Graph is not connected";

    int vertices = this.vertices();
    int odd = 0;
    for (int i = 0; i < vertices; i++) {
      if (this.values[i].size() % 2 == 1) {
        odd++;
      }
    }
    return (odd == 0) || (odd == 2);
  }

  /** todo time complexity Fluery's Algorithm */
  public List<Pair<Integer, Integer>> getEulerPath() {
    assert isEulerian() : "Graph is not Eulerian";
    // We can't directly use getBridges because one's the graph's one edge is removed we have to
    // again recalculate the
    // bridges.
    int source = 0;
    int vertices = vertices();
    // If there are 0 odd vertices then we can start from any where if 2 then we have to start from
    // any of them.
    for (int i = 0; i < vertices; i++) {
      if (values[i].size() % 2 == 1) {
        source = i;
        break;
      }
    }
    List<Pair<Integer, Integer>> eulerPath = new ArrayList<>();
    getEulerPathUtil(source, eulerPath);
    return eulerPath;
  }

  private void getEulerPathUtil(int source, List<Pair<Integer, Integer>> eulerPath) {
    // Copying to a new list so that doesn't throw ConcurrentModificationException. Can improve
    // this.
    for (int i = 0; i < values[source].size(); i++) {
      GraphNode neighbour = values[source].get(i);
      if (!isValidForEulerPath(source, neighbour.vertex)) {
        continue;
      }
      eulerPath.add(new Pair<>(source, neighbour.vertex));
      removeEdge(source, neighbour.vertex);
      getEulerPathUtil(neighbour.vertex, eulerPath);
    }
  }

  private boolean isValidForEulerPath(int source, int dest) {
    // Only one edge
    if (values[source].size() == 1) {
      return true;
    }
    boolean[] visited = new boolean[vertices()];
    int countWithEdge = countUtil(source, visited);
    removeEdge(source, dest);
    Arrays.fill(visited, false);
    int countWithoutEdge = countUtil(source, visited);
    addEdge(source, dest);
    // If count is decreased then this edge is a bridge.
    return countWithEdge == countWithoutEdge;
  }

  /** t=O(V+E) */
  @Override
  public boolean isStronglyConnected() {
    int vertices = this.vertices();
    boolean[] visited = new boolean[vertices];
    // We can start from any vertex in undirected graph.
    dfsUtil(0, visited);
    for (boolean j : visited) {
      if (!j) {
        return false;
      }
    }
    return true;
  }

  /** t=O(V+E) There should not be parallel edges */
  public boolean isCyclic() {
    boolean visited[] = new boolean[vertices()];
    for (int i = 0; i < vertices(); i++) {
      if (visited[i]) {
        continue;
      }
      if (isCyclicUtil(i, visited, -1)) {
        return true;
      }
    }
    return false;
  }

  private boolean isCyclicUtil(int vertex, boolean[] visited, int parent) {
    visited[vertex] = true;
    for (int i = 0; i < values[vertex].size(); i++) {
      int neighbour = values[vertex].get(i).vertex;
      if (!visited[neighbour]) {
        if (isCyclicUtil(neighbour, visited, vertex)) {
          return true;
        }
      } else if (neighbour != parent) {
        return true;
      }
    }
    return false;
  }
}
