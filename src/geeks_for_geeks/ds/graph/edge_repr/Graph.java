package geeks_for_geeks.ds.graph.edge_repr;

import geeks_for_geeks.ds.nodes.Edge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-10-2018 22:40
 **/
public class Graph {
    public List<Edge> edges = new ArrayList<>();
    private int maxVertex = 0;

    public int vertices() {
        return maxVertex + 1;
    }

    public Graph addEdge(int src, int dest, int weight) {
        this.edges.add(new Edge(src, dest, weight));

        maxVertex = maxVertex > dest ? maxVertex : dest;
        maxVertex = maxVertex > src ? maxVertex : src;

        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph{");
        for (Edge edge : edges) {
            sb.append(edge).append(",").append(System.lineSeparator());
        }
        sb.append("}");
        return sb.toString();
    }
}
