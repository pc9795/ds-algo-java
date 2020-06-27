package gfg.ds.graph.adj_list;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 11-02-2019 02:46
 **/
public class Applications {
    public static Set<GraphBase.Edge> addEdgeInDAG(GraphBase.Edge[] edges, Graph graph) {
        ArrayDeque<Integer> stack = graph.topologicalSort();
        Map<Integer, Integer> vertexToPosMap = new HashMap<>();
        int i = 0;
        for (Integer elem : stack) {
            vertexToPosMap.put(elem, i++);
        }
        Set<GraphBase.Edge> edgesToBeAdded = new HashSet<>();
        for (GraphBase.Edge edge : edges) {
            int diff = vertexToPosMap.get(edge.src) - vertexToPosMap.get(edge.dest);
            // index which is before in topological order is less than the other.
            if (diff < 0) {
                edgesToBeAdded.add(new GraphBase.Edge(edge.src, edge.dest));
            } else {
                edgesToBeAdded.add(new GraphBase.Edge(edge.dest, edge.src));
            }
        }
        return edgesToBeAdded;
    }
}
