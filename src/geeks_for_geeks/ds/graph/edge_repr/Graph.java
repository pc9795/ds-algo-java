package geeks_for_geeks.ds.graph.edge_repr;

import geeks_for_geeks.ds.nodes.Edge;
import geeks_for_geeks.ds.union_find.UnionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This is undirected graph.
 * Created By: Prashant Chaubey
 * Created On: 15-10-2018 22:40
 **/
public class Graph {
    private List<Edge> edges;
    private ArrayList<Integer>[] values;
    private int vertices;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
        this.values = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            values[i] = new ArrayList<>();
        }
    }


    public Graph addEdge(int src, int dest, int weight) {
        assert dest >= 0 && dest < vertices && src >= 0 && src < vertices;
        int index = edges.size();
        this.values[src].add(index);
        this.values[dest].add(index);
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

    public boolean isConnected(int source) {
        boolean[] visited = new boolean[this.vertices];
        Arrays.fill(visited, false);
        dfsUtil(source, visited);
        for (boolean i : visited) {
            if (!i) {
                return false;
            }
        }
        return true;
    }

    public boolean isEulerianCircuitExists() {
        if (!isConnected(0)) {
            return false;
        }
        for (int i = 0; i < this.values.length; i++) {
            if (this.values[i].size() % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    public void dfsUtil(int source, boolean[] visited) {
        visited[source] = true;
        ArrayList<Integer> neighbours = this.values[source];
        for (Integer neighbourEdge : neighbours) {
            Edge edge = this.edges.get(neighbourEdge);
            int neighbour = source ^ edge.src ^ edge.dest;
            if (!visited[neighbour]) {
                dfsUtil(neighbour, visited);
            }
        }
    }

}
