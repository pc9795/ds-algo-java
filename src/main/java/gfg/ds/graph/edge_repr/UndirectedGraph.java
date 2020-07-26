package gfg.ds.graph.edge_repr;

import gfg.ds.graph.adj_list.GraphBase;
import gfg.ds.union_find.UnionFind;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-10-2018 22:40
 **/
public class UndirectedGraph {
    public List<GraphBase.Edge> edges;
    private ArrayList<Integer>[] values;
    public int vertices;

    public UndirectedGraph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
        this.values = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            values[i] = new ArrayList<>();
        }
    }

    public UndirectedGraph addEdge(int src, int dest, int weight) {
        assert dest >= 0 && dest < vertices && src >= 0 && src < vertices;
        int index = edges.size();
        this.values[src].add(index);
        this.values[dest].add(index);
        this.edges.add(new GraphBase.Edge(src, dest, weight));
        return this;
    }

    public UndirectedGraph addEdge(int src, int dest) {
        return addEdge(src, dest, 0);
    }

    /**
     * t=O(E*Log V)
     * Assumes that there are no self loops
     */
    public boolean isCyclic() {
        UnionFind uf = new UnionFind(this.vertices);
        for (GraphBase.Edge edge : edges) {
            if (uf.find(edge.src) == uf.find(edge.dest)) {
                return true;
            }
            uf.union(edge.src, edge.dest);
        }
        return false;
    }

    /**
     * todo time complexity
     */
    private boolean isConnected() {
        boolean[] visited = new boolean[this.vertices];
        dfsUtil(0, visited);
        for (boolean i : visited) {
            if (!i) {
                return false;
            }
        }
        return true;
    }

    /**
     * todo time complexity
     */
    public boolean isEulerianCircuitExists() {
        if (!isConnected()) {
            return false;
        }
        for (ArrayList<Integer> value : this.values) {
            if (value.size() % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    private void dfsUtil(int source, boolean[] visited) {
        if (visited[source]) {
            return;
        }
        visited[source] = true;
        ArrayList<Integer> neighbours = this.values[source];
        for (Integer neighbourEdge : neighbours) {
            GraphBase.Edge edge = this.edges.get(neighbourEdge);
            int neighbour = source ^ edge.src ^ edge.dest;
            dfsUtil(neighbour, visited);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph{");
        for (GraphBase.Edge edge : edges) {
            sb.append(edge).append(",").append(System.lineSeparator());
        }
        sb.append("}");
        return sb.toString();
    }
}
