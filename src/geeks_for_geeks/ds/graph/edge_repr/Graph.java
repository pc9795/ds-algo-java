package geeks_for_geeks.ds.graph.edge_repr;

import geeks_for_geeks.ds.nodes.Edge;
import geeks_for_geeks.ds.union_find.UnionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * This is undirected graph.
 * Created By: Prashant Chaubey
 * Created On: 15-10-2018 22:40
 **/
public class Graph {
    public List<Edge> edges = new ArrayList<>();
    public int vertices;

    public Graph(int vertices) {
        this.vertices = vertices;
    }


    public Graph addEdge(int src, int dest, int weight) {
        assert dest >= 0 && dest < vertices && src >= 0 && src < vertices;

        this.edges.add(new Edge(src, dest, weight));
        return this;
    }

    public boolean isCyclic() {
        UnionFind uf = new UnionFind(this.vertices);

        for (Edge edge : edges) {
            int parent1 = uf.find(edge.src);
            int parent2 = uf.find(edge.dest);

            if (parent1 == parent2) {
                return true;
            }

            uf.union(edge.src, edge.dest);
        }

        return false;
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
