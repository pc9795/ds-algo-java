package geeks_for_geeks.algo.greedy_algorithms;

import geeks_for_geeks.ds.graph.adj_list.Graph;
import geeks_for_geeks.ds.graph.adj_list.GraphBase;
import geeks_for_geeks.ds.graph.adj_list.UndirectedGraph;
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
    public static int[] dijkstraShortestPath(GraphBase graph) {
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
//       because first node is at the top that's why we are getting it as first. This changing of key will have no
//        effect.
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
}
