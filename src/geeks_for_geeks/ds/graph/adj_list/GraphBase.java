package geeks_for_geeks.ds.graph.adj_list;

import geeks_for_geeks.ds.nodes.GraphNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 12-01-2019 18:53
 * Purpose: TODO:
 **/
public abstract class GraphBase {
    public List<GraphNode>[] values;

    GraphBase(int vertices) {
        values = new ArrayList[vertices];
        for (int i = 0; i < values.length; i++) {
            values[i] = new ArrayList<>();
        }
    }

    public int vertices() {
        return values.length;
    }

    public abstract GraphBase addEdge(int src, int dest);

    public abstract GraphBase removeEdge(int src, int dest);

    public abstract GraphBase addEdge(int src, int dest, int weight);

    public abstract boolean isEulerian();

    public abstract boolean isStronglyConnected();

    /**
     * T=O(V+E)
     */
    public void dfs() {
        boolean visited[] = new boolean[vertices()];
        for (int i = 0; i < vertices(); i++) {
            if (!visited[i]) {
                dfsUtil(i, visited);
            }
        }
    }

    void dfsUtil(int source, boolean[] visited) {
        visited[source] = true;
        List<GraphNode> neighbours = values[source];
        for (GraphNode neighbour : neighbours) {
            if (!visited[neighbour.vertex]) {
                dfsUtil(neighbour.vertex, visited);
            }
        }
    }

    public boolean isEdge(int src, int dest) {
        assert (src >= values.length || dest >= values.length);
        for (int i = 0; i < values[src].size(); i++) {
            if (values[src].get(i).vertex == dest) {
                return true;
            }
        }
        return false;
    }

    /**
     * T=O(V+E)
     */
    public void bfs() {
        boolean visited[] = new boolean[vertices()];
        for (int i = 0; i < vertices(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                bfsUtil(i, visited);
            }
        }
    }

    private void bfsUtil(int vertex, boolean[] visited) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(vertex);
        for (; !queue.isEmpty(); ) {
            int curr = queue.poll();
            System.out.println("visited:" + curr);
            for (int i = 0; i < values[curr].size(); i++) {
                int neighbour = values[curr].get(i).vertex;
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }

    /**
     * T=O(V+E)
     *
     * @return
     */
    public Graph transpose() {
        int vertices = this.vertices();
        Graph graph = new Graph(vertices);
        for (int i = 0; i < vertices; i++) {
            List<GraphNode> neighbours = this.values[i];
            for (GraphNode neighbour : neighbours) {
                graph.addEdge(neighbour.vertex, i);
            }
        }
        return graph;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph{").append(System.lineSeparator());
        for (int i = 0; i < vertices(); i++) {
            sb.append(i).append("=>").append(values[i]);
            sb.append(System.lineSeparator());
        }
        sb.append('}');

        return sb.toString();
    }
}
