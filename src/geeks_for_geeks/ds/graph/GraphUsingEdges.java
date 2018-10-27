package geeks_for_geeks.ds.graph;

import geeks_for_geeks.ds.nodes.Edge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-10-2018 22:40
 **/
public class GraphUsingEdges {
    public List<Edge> edges = new ArrayList<>();
    public int maxVertex = 0;

    public int vertices() {
        return maxVertex + 1;
    }

    public GraphUsingEdges addEdge(int src, int dest, int weight) {
        this.edges.add(new Edge(src, dest, weight));
        maxVertex = maxVertex > dest ? maxVertex : dest;
        maxVertex = maxVertex > src ? maxVertex : src;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GraphUsingEdges{");
        for (int i = 0; i < this.edges.size(); i++) {
            sb.append(this.edges.get(i) + "," + System.lineSeparator());
        }
        sb.append("}");
        return sb.toString();
    }
}
