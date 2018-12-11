package geeks_for_geeks.algo.greedy_algorithms;

import geeks_for_geeks.ds.graph.GraphUsingAdjacencyList;
import geeks_for_geeks.ds.graph.UndirectedGraphUsingAdjacencyList;
import geeks_for_geeks.ds.nodes.GraphNode;
import geeks_for_geeks.ds.nodes.PrimNode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created By: Prashant Chaubey
 * Created On: 14-11-2018 23:32
 **/
public class DijkstraShortestPath {
    /**
     * T=O(V^2) for adjacency matrix
     * =O(ElogV) for adjacency list
     * (E+V) * logV = traversing * remove
     *
     * @param graph
     * @return
     */
    public static int[] dijkstraShortestPath(GraphUsingAdjacencyList graph) {
        assert graph != null;
        int dist[] = new int[graph.vertices()];
        PriorityQueue<PrimNode> heap = new PriorityQueue<>(new Comparator<PrimNode>() {
            @Override
            public int compare(PrimNode o1, PrimNode o2) {
                return o1.key - o2.key;
            }
        });
        PrimNode[] nodes = new PrimNode[graph.vertices()];
        boolean[] inHeap = new boolean[graph.vertices()];
        Arrays.fill(inHeap, true);
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new PrimNode(i, Integer.MAX_VALUE);
            nodes[i].parent = -1;
            heap.add(nodes[i]);
        }
//        TODO: check whether this triggers movement in heap automatically.
        nodes[0].key = 0;
        while (!heap.isEmpty()) {
            PrimNode node = heap.poll();
            inHeap[node.vertex] = false;
            dist[node.vertex] = node.key;
            for (int i = 0; i < graph.values[node.vertex].size(); i++) {
                GraphNode neighbour = graph.values[node.vertex].get(i);
                if (inHeap[neighbour.vertex]) {
                    PrimNode neighbourNode = nodes[neighbour.vertex];
                    if (neighbourNode.key > node.key + neighbour.weight) {
                        heap.remove(neighbourNode);
                        nodes[neighbour.vertex].key = node.key + neighbour.weight;
                        heap.add(nodes[neighbour.vertex]);
                    }
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        GraphUsingAdjacencyList graph = new UndirectedGraphUsingAdjacencyList(9);
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);
        System.out.println(Arrays.toString(dijkstraShortestPath(graph)));
    }
}
