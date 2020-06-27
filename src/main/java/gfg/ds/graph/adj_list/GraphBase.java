package gfg.ds.graph.adj_list;

import utils.DoublePointer;
import utils.Pair;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 12-01-2019 18:53
 **/
public abstract class GraphBase {
    public List<GraphNode>[] values;

    GraphBase(int vertices) {
        //noinspection unchecked
        values = new ArrayList[vertices];
        for (int i = 0; i < values.length; i++) {
            values[i] = new ArrayList<>();
        }
    }

    public int vertices() {
        return values.length;
    }

    public abstract GraphBase addEdge(int src, int dest);

    public abstract void removeEdge(int src, int dest);

    public abstract GraphBase addEdge(int src, int dest, int weight);

    public abstract boolean isStronglyConnected();

    public abstract boolean isEulerian();

    public abstract List<Pair<Integer, Integer>> getEulerPath();

    /**
     * t=O(V+E)
     */
    public List<Integer> dfs() {
        boolean visited[] = new boolean[vertices()];
        List<Integer> traversal = new ArrayList<>();
        for (int i = 0; i < vertices(); i++) {
            dfsUtil(i, visited, traversal);
        }
        return traversal;
    }

    private void dfsUtil(int source, boolean[] visited, List<Integer> traversal) {
        if (visited[source]) {
            return;
        }
        visited[source] = true;
        traversal.add(source);

        List<GraphNode> neighbours = values[source];
        for (GraphNode neighbour : neighbours) {
            dfsUtil(neighbour.vertex, visited, traversal);
        }
    }

    void dfsUtil(int source, boolean[] visited) {
        if (visited[source]) {
            return;
        }
        visited[source] = true;
        List<GraphNode> neighbours = values[source];

        for (GraphNode neighbour : neighbours) {
            dfsUtil(neighbour.vertex, visited);
        }
    }

    /**
     * t=O(V+E)
     *
     * @param source  source vertex
     * @param visited boolean array to track visited vertices
     * @return count of the vertices reachable by source vertex using DFS
     */
    int dfsCountUtil(int source, boolean[] visited) {
        if (visited[source]) {
            return 0;
        }
        visited[source] = true;
        int count = 1;
        List<GraphNode> neighbours = this.values[source];
        for (GraphNode neighbour : neighbours) {
            count += dfsCountUtil(neighbour.vertex, visited);
        }
        return count;
    }

    /**
     * t=O(V+E)
     *
     * @return bfs traversal
     */
    public List<Integer> bfs() {
        int vertices = vertices();
        boolean visited[] = new boolean[vertices];
        List<Integer> traversal = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            traversal.addAll(bfsUtil(i, visited));
        }
        return traversal;
    }

    @SuppressWarnings("ConstantConditions")
    private List<Integer> bfsUtil(int vertex, boolean[] visited) {
        List<Integer> traversal = new ArrayList<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(vertex);
        for (; !queue.isEmpty(); ) {
            int curr = queue.poll();
            traversal.add(curr);
            for (int i = 0; i < values[curr].size(); i++) {
                int neighbour = values[curr].get(i).vertex;
                if (visited[neighbour]) {
                    continue;
                }
                visited[neighbour] = true;
                queue.add(neighbour);
            }
        }
        return traversal;
    }

    /**
     * T=O(V+E)
     *
     * @return transpose of the graph
     */
    Graph transpose() {
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
     * t=O(V+E); same as DFS
     * Will not work in the case of multiple edges.
     *
     * @param graph graph whose bridges are to be found
     */
    public static List<Edge> getBridges(GraphBase graph) {
        assert graph != null;

        int vertices = graph.vertices();
        int[] parent = new int[vertices];
        Arrays.fill(parent, -1);
        // The ancestor with lowest discovery time which is found by the vertex or it's descendants during dfs of the
        // subtree rooted from here.
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];
        int[] discoveryTime = new int[vertices];
        DoublePointer<Integer> currentTime = new DoublePointer<>();
        currentTime.data = 0;
        List<Edge> bridges = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            if (visited[i]) {
                continue;
            }
            bridges.addAll(getBridgesUtil(parent, low, visited, discoveryTime, currentTime, i, graph));
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
                    // Updating root with it's descendants.
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
     * todo time complexity
     *
     * @param graph input graph
     * @return articulation points
     */
    public static Set<Integer> getArticulationPoints(GraphBase graph) {
        assert graph != null;

        int vertices = graph.vertices();
        int[] parent = new int[vertices];
        Arrays.fill(parent, -1);
        // The ancestor with lowest discovery time which is found by the vertex or it's descendants during dfs of the
        // subtree rooted from here.
        int[] low = new int[vertices];
        boolean[] visited = new boolean[vertices];
        boolean[] ap = new boolean[vertices];
        int[] discoveryTime = new int[vertices];
        DoublePointer<Integer> currentTime = new DoublePointer<>();
        currentTime.data = 0;
        for (int i = 0; i < vertices; i++) {
            if (visited[i]) {
                continue;
            }
            getArticulationPointsUtil(parent, low, visited, discoveryTime, currentTime, ap, i, graph);
        }
        Set<Integer> articulationPoints = new HashSet<>();
        for (int i = 0; i < ap.length; i++) {
            if (ap[i]) {
                articulationPoints.add(i);
            }
        }
        return articulationPoints;
    }

    private static void getArticulationPointsUtil(int[] parent, int[] low, boolean[] visited, int[] discoveryTime,
                                                  DoublePointer<Integer> currentTime, boolean[] ap, int source, GraphBase graph) {
        visited[source] = true;
        low[source] = discoveryTime[source] = currentTime.data++;
        int children = 0;
        List<GraphNode> neighbours = graph.values[source];
        for (GraphNode neighbour : neighbours) {
            if (!visited[neighbour.vertex]) {
                children++;
                parent[neighbour.vertex] = source;
                getArticulationPointsUtil(parent, low, visited, discoveryTime, currentTime, ap, neighbour.vertex, graph);
                // Updating root with it's descendants.
                low[source] = Math.min(low[source], low[neighbour.vertex]);
                // Only child which have no edge to neighbour subtree are counted.
                if (parent[source] == -1 && children > 1)
                    ap[source] = true;
                // Any child vertex which has no back edge above source.
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
            sb.append(i).append("=>").append(values[i]).append(System.lineSeparator());
        }
        sb.append('}');

        return sb.toString();
    }

    /**
     * Created By: Prashant Chaubey
     * Created On: 31-10-2018 01:18
     **/
    public static class GraphNode {
        public int vertex;
        public int weight;

        public GraphNode(int vertex) {
            this.vertex = vertex;
            this.weight = 0;
        }

        public GraphNode(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "GraphNode{" +
                    "vertex=" + vertex +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj instanceof GraphNode) {
                GraphNode other = (GraphNode) obj;
                return this.vertex == other.vertex && this.weight == other.weight;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(vertex, weight);
        }
    }

    /**
     * Created By: Prashant Chaubey
     * Created On: 14-10-2018 22:26
     **/
    public static class Edge {
        public int src;
        public int weight;
        public int dest;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        public Edge(int src, int dest) {
            this.src = src;
            this.dest = dest;
            this.weight = 0;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "src=" + src +
                    ", weight=" + weight +
                    ", dest=" + dest +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj instanceof Edge) {
                Edge other = (Edge) obj;
                return this.src == other.src && this.dest == other.dest && this.weight == other.weight;
            }
            return false;
        }

        @Override
        public int hashCode() {
            int result = src;
            result = 31 * result + weight;
            result = 31 * result + dest;
            return result;
        }
    }
}
