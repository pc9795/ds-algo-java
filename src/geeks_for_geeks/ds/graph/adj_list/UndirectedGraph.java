package geeks_for_geeks.ds.graph.adj_list;

import geeks_for_geeks.ds.nodes.GraphNode;

import java.util.Arrays;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 09-10-2018 01:16
 **/
public class UndirectedGraph extends GraphBase {

    public UndirectedGraph(int vertices) {
        super(vertices);
    }

    @Override
    public UndirectedGraph addEdge(int src, int dest) {
        assert !(src >= values.length || dest >= values.length);

        values[src].add(new GraphNode(dest));
        values[dest].add(new GraphNode(src));

        return this;
    }

    @Override
    public UndirectedGraph removeEdge(int src, int dest) {
        assert !(src >= values.length || dest >= values.length);

        values[src].remove(new GraphNode(dest));
        values[dest].remove(new GraphNode(src));

        return this;
    }

    @Override
    public UndirectedGraph addEdge(int src, int dest, int weight) {
        assert !(src >= values.length || dest >= values.length);

        values[src].add(new GraphNode(dest, weight));
        values[dest].add(new GraphNode(src, weight));

        return this;
    }

    public int degree(int vertex) {
        assert (vertex > 0 && vertex <= values.length);

        return this.values[vertex].size();
    }

    /**
     * T=O(V+E)
     * Because of the dfs involved in finding whether the graph is connected or not.
     *
     * @return
     */
    @Override
    public boolean isEulerian() {
        if (!this.isStronglyConnected()) {
            return false;
        }
        int vertices = this.vertices();
        int odd = 0;
        for (int i = 0; i < vertices; i++) {
            if (this.values[i].size() % 2 == 1) {
                odd++;
            }
        }
        return (odd == 0) || (odd == 2);
    }

    /**
     * Fluery's Algorithm
     * T=O(V+E)
     */
    public void printEulerPath() {
        if (!isEulerian()) {
            System.out.println("Graph is not eulerain");
            return;
        }
//        We can't directly use getBridges because one's the graph's one edge is removed we have to again recalculate the
//        bridges.
        int source = 0;
        int vertices = vertices();
        for (int i = 0; i < vertices; i++) {
            if (values[i].size() % 2 == 1) {
                source = i;
                break;
            }
        }
        printEulerPathUtil(source);
    }

    private void printEulerPathUtil(int source) {
        List<GraphNode> neighbours = values[source];
        for (int i = 0; i < neighbours.size(); i++) {
            GraphNode neighbour = neighbours.get(i);
            if (isValidEdgeForEulerianPath(source, neighbour.vertex)) {
                System.out.print(source + " " + neighbour.vertex + "=>");
                removeEdge(source, neighbour.vertex);
                printEulerPathUtil(neighbour.vertex);
            }
        }
    }


    private boolean isValidEdgeForEulerianPath(int source, int dest) {
        if (values[source].size() == 1) {
            return true;
        }
        boolean[] visited = new boolean[vertices()];
        int count1 = dfsCountUtil(source, visited);
        removeEdge(source, dest);
        Arrays.fill(visited, false);
        int count2 = dfsCountUtil(source, visited);
        addEdge(source, dest);
//        If count2 is decreased then it this edge is a bridge.
        return count1 == count2;
    }

    /**
     * T=O(V+E)
     *
     * @return
     */
    @Override
    public boolean isStronglyConnected() {
        int vertices = this.vertices();
        int i = 0;
        for (; i < vertices; i++) {
            if (this.values[i].size() > 0) {
                break;
            }
        }
//        No edges in the graph.
        if (i == vertices) {
            return true;
        }
        boolean[] visited = new boolean[vertices];
        dfsUtil(i, visited);
        for (boolean j : visited) {
            if (!j) {
                return false;
            }
        }
        return true;
    }
}