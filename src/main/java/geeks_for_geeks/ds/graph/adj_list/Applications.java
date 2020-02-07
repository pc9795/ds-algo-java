package geeks_for_geeks.ds.graph.adj_list;

import geeks_for_geeks.ds.nodes.Edge;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 11-02-2019 02:46
 **/
public class Applications {
    public static Set<Edge> addEdgeInDAG(Edge[] edges, Graph graph) {
        ArrayDeque<Integer> stack = graph.topologicalSort();
        Map<Integer, Integer> vertexToPosMap = new HashMap<>();
        int i = 0;
        for (Integer elem : stack) {
            vertexToPosMap.put(elem, i++);
        }
        Set<Edge> edgesToBeAdded = new HashSet<>();
        for (Edge edge : edges) {
            int diff = vertexToPosMap.get(edge.src) - vertexToPosMap.get(edge.dest);
            // index which is before in topological order is less than the other.
            if (diff < 0) {
                edgesToBeAdded.add(new Edge(edge.src, edge.dest));
            } else {
                edgesToBeAdded.add(new Edge(edge.dest, edge.src));
            }
        }
        return edgesToBeAdded;
    }
}
