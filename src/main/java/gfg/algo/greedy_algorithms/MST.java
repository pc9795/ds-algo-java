package gfg.algo.greedy_algorithms;

import gfg.ds.graph.adj_list.GraphBase;
import gfg.ds.graph.edge_repr.UndirectedGraph;
import gfg.ds.union_find.UnionFind;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MST {
  // t=E*logE or E*logV (value of E can be at most V^2)
  public static UndirectedGraph kruskalMinimumSpanningTree(UndirectedGraph graph) {
    if (graph.edges.size() < graph.vertices) {
      throw new RuntimeException("Graph is not connected");
    }

    UndirectedGraph mst = new UndirectedGraph(graph.vertices);
    graph.edges.sort(Comparator.comparingInt(o -> o.weight));

    int vertices = graph.vertices;
    UnionFind uf = new UnionFind(vertices);

    for (int i = 0, j = 0; i < vertices - 1; j++) {
      GraphBase.Edge edge = graph.edges.get(j);
      if (uf.find(edge.src) != uf.find(edge.dest)) {
        uf.union(edge.src, edge.dest);
        mst.addEdge(edge.src, edge.dest, edge.weight);
        i++;
      } else {
        System.out.println("Discarding edge because it will form a cycle:" + edge);
      }
    }
    return mst;
  }

  // t=V^2 for adjacency matrix
  // t=(E+V)*logV for adjacency list. traversing * heap poll
  public static GraphBase primMinimumSpanningTree(GraphBase graph) {
    GraphBase mst = new gfg.ds.graph.adj_list.Graph(graph.vertices());
    PriorityQueue<PrimNode> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.key));
    PrimNode[] nodes = new PrimNode[graph.vertices()];
    boolean[] inHeap = new boolean[graph.vertices()];
    Arrays.fill(inHeap, true);
    for (int i = 0; i < nodes.length; i++) {
      nodes[i] = new PrimNode(i, Integer.MAX_VALUE);
      nodes[i].parent = -1;
      heap.add(nodes[i]);
    }
    nodes[0].key = 0;
    while (!heap.isEmpty()) {
      PrimNode node = heap.poll();
      if (node.parent != -1) {
        mst.addEdge(node.parent, node.vertex, node.key);
      }
      inHeap[node.vertex] = false;
      for (int i = 0; i < graph.values[node.vertex].size(); i++) {
        GraphBase.GraphNode neighbour = graph.values[node.vertex].get(i);
        if (inHeap[neighbour.vertex]) {
          PrimNode neighbourNode = nodes[neighbour.vertex];
          if (neighbourNode.key > neighbour.weight) {
            neighbourNode.parent = node.vertex;
            heap.remove(neighbourNode); // inefficient in Java
            nodes[neighbour.vertex].key = neighbour.weight;
            heap.add(nodes[neighbour.vertex]);
          }
        }
      }
    }
    return mst;
  }

  public static class PrimNode {
    public int vertex;
    public int key;
    public int parent;

    public PrimNode(int vertex, int key) {
      this.vertex = vertex;
      this.key = key;
    }

    @Override
    public String toString() {
      return "PrimNode{" + "vertex=" + vertex + ", key=" + key + '}';
    }
  }
}
