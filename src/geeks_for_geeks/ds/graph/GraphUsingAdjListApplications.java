package geeks_for_geeks.ds.graph;

import geeks_for_geeks.ds.graph.adj_list.Graph;
import geeks_for_geeks.ds.nodes.Edge;

import java.util.ArrayDeque;
import java.util.HashMap;

/**
 * Created By: Prashant Chaubey
 * Created On: 11-02-2019 02:46
 * Purpose: TODO:
 **/
public class GraphUsingAdjListApplications {

    public static void addEdgeInDAG(Edge[] edges, Graph graph) {

        ArrayDeque<Integer> stack = graph.topologicalSort();
        HashMap<Integer, Integer> vertexToPositionMap = new HashMap<>();

        int i = 0;
        for (Integer elem : stack) {
            vertexToPositionMap.put(elem, i++);
        }

        for (Edge edge : edges) {
            int diff = vertexToPositionMap.get(edge.src) - vertexToPositionMap.get(edge.dest);

//            index which is before in topological order is less than the other.
            if (diff < 0) {
                System.out.println("Edge to be added from " + edge.src + " to " + edge.dest);
            } else {
                System.out.println("Edge to be added from " + edge.dest + " to " + edge.src);
            }
        }
    }
}
