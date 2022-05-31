package gfg.algo.greedy_algorithms;

import gfg.ds.graph.adj_list.GraphBase;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DijkstraShortestPath {
  // t=V^2 for adjacency matrix
  // t=(E+V)*logV for adjacency list. traversing * heap poll
  public static int[] dijkstraShortestPath(GraphBase graph) {
    int[] dist = new int[graph.vertices()];
    PriorityQueue<MST.PrimNode> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.key));
    MST.PrimNode[] nodes = new MST.PrimNode[graph.vertices()];
    boolean[] inHeap = new boolean[graph.vertices()];
    Arrays.fill(inHeap, true);
    for (int i = 0; i < nodes.length; i++) {
      nodes[i] = new MST.PrimNode(i, i == 0 ? 0 : Integer.MAX_VALUE);
      nodes[i].parent = -1;
      heap.add(nodes[i]);
    }
    while (!heap.isEmpty()) {
      MST.PrimNode node = heap.poll();
      inHeap[node.vertex] = false;
      dist[node.vertex] = node.key;
      for (int i = 0; i < graph.neighboursSize(node.vertex); i++) {
        GraphBase.GraphNode neighbour = graph.getNeighbour(node.vertex, i);
        if (!inHeap[neighbour.vertex]) {
          continue;
        }
        MST.PrimNode neighbourNode = nodes[neighbour.vertex];
        if (neighbourNode.key <= node.key + neighbour.weight) {
          continue;
        }
        heap.remove(neighbourNode); // inefficient in java
        nodes[neighbour.vertex].key = node.key + neighbour.weight;
        heap.add(nodes[neighbour.vertex]);
      }
    }
    return dist;
  }
}
