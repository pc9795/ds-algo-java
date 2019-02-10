package geeks_for_geeks.ds.graph.adj_list;

import geeks_for_geeks.ds.nodes.Edge;
import geeks_for_geeks.ds.nodes.GraphNode;
import geeks_for_geeks.util.DoublePointer;

import java.util.*;

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

    public abstract boolean isStronglyConnected();

    public abstract boolean isEulerian();

    public abstract void printEulerPath();

    /**
     * t=O(V+E)
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

    /**
     * T=O(V+E)
     *
     * @param source
     * @param visited
     * @return
     */
    int dfsCountUtil(int source, boolean[] visited) {
        visited[source] = true;
        int count = 1;
        List<GraphNode> neighbours = this.values[source];
        for (GraphNode neighbour : neighbours) {
            if (!visited[neighbour.vertex]) {
                count += dfsCountUtil(neighbour.vertex, visited);
            }
        }
        return count;
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
     * t=O(V+E)
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

    /**
     * T=O(V+E)
     * Same as DFS.
     * Will not work in the case of multiple edges.
     *
     * @param graph
     */
    public static List<Edge> getBridges(GraphBase graph) {
        assert graph != null;
        int vertices = graph.vertices();
        int[] parent = new int[vertices];
        Arrays.fill(parent, -1);
//        The ancestor with lowest discovery time which is found by the vertex or it's descendants during dfs of the
//        subtree rooted from here.
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];
        int[] discoveryTime = new int[vertices];
        DoublePointer<Integer> currentTime = new DoublePointer<>();
        currentTime.data = 0;
        List<Edge> bridges = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                bridges.addAll(getBridgesUtil(parent, low, visited, discoveryTime, currentTime, i, graph));
            }
        }
        return bridges;
    }

    private static List<Edge> getBridgesUtil(int[] parent, int[] low, boolean[] visited, int[] discoveryTime,
                                             DoublePointer<Integer> currentTime, int source, GraphBase graph) {
        visited[source] = true;
        low[source] = discoveryTime[source] = currentTime.data++;
        List<Edge> bridges = new ArrayList<>();
        List<GraphNode> neighbours = graph.values[source];
        for (GraphNode neighbour : neighbours) {
            if (!visited[neighbour.vertex]) {
                parent[neighbour.vertex] = source;
                if (!visited[neighbour.vertex]) {
                    bridges.addAll(getBridgesUtil(parent, low, visited, discoveryTime, currentTime, neighbour.vertex,
                            graph));
//                    Updating root with it's descendants.
                    low[source] = Math.min(low[source], low[neighbour.vertex]);
                    if (low[neighbour.vertex] > discoveryTime[source]) {
                        bridges.add(new Edge(source, neighbour.vertex));
                    }
                }
            } else if (parent[source] != neighbour.vertex) {
                low[source] = Math.min(low[source], discoveryTime[neighbour.vertex]);
            }
        }
        return bridges;
    }

    /**
     * O(N+M)
     * DFS
     *
     * @param graph
     * @return
     */
    public static Set<Integer> getArticulationPoints(GraphBase graph) {
        assert graph != null;
        int vertices = graph.vertices();
        int[] parent = new int[vertices];
        Arrays.fill(parent, -1);
//        The ancestor with lowest discovery time which is found by the vertex or it's descendants during dfs of the
//        subtree rooted from here.
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];
        boolean[] ap = new boolean[vertices];
        int[] discoveryTime = new int[vertices];
        DoublePointer<Integer> currentTime = new DoublePointer<>();
        currentTime.data = 0;
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                getArticulationPointsUtil(parent, low, visited, discoveryTime, currentTime, ap, i, graph);
            }
        }
        Set<Integer> articulationPoints = new HashSet<>();
        for (int i = 0; i < ap.length; i++) {
            if (ap[i]) {
                articulationPoints.add(i);
            }
        }
        return articulationPoints;
    }

    private static void getArticulationPointsUtil(
            int[] parent, int[] low, boolean[] visited, int[] discoveryTime,
            DoublePointer<Integer> currentTime, boolean[] ap, int source, GraphBase graph) {
        visited[source] = true;
        low[source] = discoveryTime[source] = currentTime.data++;
        int children = 0;
        List<GraphNode> neighbours = graph.values[source];
        for (GraphNode neighbour : neighbours) {
            if (!visited[neighbour.vertex]) {
                children++;
                parent[neighbour.vertex] = source;
                getArticulationPointsUtil(parent, low, visited, discoveryTime, currentTime, ap, neighbour.vertex,
                        graph);
//                    Updating root with it's descendants.
                low[source] = Math.min(low[source], low[neighbour.vertex]);
//                    Only child which have no edge to neighbour subtree are counted.
                if (parent[source] == -1 && children > 1)
                    ap[source] = true;
//                  Any child vertex which has no back edge above source.
                if (parent[source] != -1 && low[neighbour.vertex] >= discoveryTime[source])
                    ap[source] = true;
            } else if (parent[source] != neighbour.vertex) {
                low[source] = Math.min(low[source], discoveryTime[neighbour.vertex]);
            }
        }
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
