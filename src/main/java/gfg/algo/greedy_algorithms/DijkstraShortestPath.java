package gfg.algo.greedy_algorithms;

import gfg.ds.graph.adj_list.GraphBase;

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
        PriorityQueue<MST.PrimNode> heap = new PriorityQueue<>(new Comparator<MST.PrimNode>() {
            @Override
            public int compare(MST.PrimNode o1, MST.PrimNode o2) {
                return o1.key - o2.key;
            }
        });
        MST.PrimNode[] nodes = new MST.PrimNode[graph.vertices()];
        boolean[] inHeap = new boolean[graph.vertices()];
        Arrays.fill(inHeap, true);
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new MST.PrimNode(i, Integer.MAX_VALUE);
            nodes[i].parent = -1;
            heap.add(nodes[i]);
        }
        // because first node is at the top that's why we are getting it as first (`heap.poll()`). This changing of key
        // will have no effect.
        // key represent the cost
        nodes[0].key = 0;
        while (!heap.isEmpty()) {
            MST.PrimNode node = heap.poll();
            assert node != null;
            inHeap[node.vertex] = false;
            dist[node.vertex] = node.key;
            for (int i = 0; i < graph.values[node.vertex].size(); i++) {
                // todo make getting of neighbors clean
                GraphBase.GraphNode neighbour = graph.values[node.vertex].get(i);
                // already processed
                if (!inHeap[neighbour.vertex]) {
                    continue;
                }
                MST.PrimNode neighbourNode = nodes[neighbour.vertex];
                if (neighbourNode.key <= node.key + neighbour.weight) {
                    continue;
                }
                heap.remove(neighbourNode);
                nodes[neighbour.vertex].key = node.key + neighbour.weight;
                heap.add(nodes[neighbour.vertex]);
            }
        }
        return dist;
    }
}
