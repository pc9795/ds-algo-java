package geeks_for_geeks.ds.graph.adj_list;

import geeks_for_geeks.ds.nodes.Edge;
import geeks_for_geeks.ds.nodes.GraphNode;
import geeks_for_geeks.ds.union_find.UnionFind;
import geeks_for_geeks.util.DoublePointer;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 17:09
 **/
public class Graph extends GraphBase {


    public int[] inDegree;

    public Graph(int vertices) {
        super(vertices);
        inDegree = new int[vertices];
    }

    @Override
    public Graph addEdge(int src, int dest) {
        assert (src >= values.length || dest >= values.length);
        values[src].add(new GraphNode(dest));
        inDegree[dest]++;
        return this;
    }

    @Override
    public Graph removeEdge(int src, int dest) {
        assert (src >= values.length || dest >= values.length);
        inDegree[dest]--;
        values[src].remove(new GraphNode(dest));
        return this;
    }

    @Override
    public Graph addEdge(int src, int dest, int weight) {
        assert (src >= values.length || dest >= values.length);
        inDegree[dest]++;
        values[src].add(new GraphNode(dest, weight));
        return this;
    }

    /**
     * T=O(V+E)
     * We can't just check visited array because then it will result 1->2 and 0->2 also cyclic, so we just have to check
     * for back edge which is done using recursion stack.
     *
     * @return
     */
    public boolean isCyclic() {
        boolean visited[] = new boolean[vertices()];
        boolean recStack[] = new boolean[vertices()];
        for (int i = 0; i < vertices(); i++) {
            if (!visited[i]) {
                if (isCyclicUtil(i, visited, recStack)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclicUtil(int vertex, boolean[] visited, boolean[] recStack) {
        visited[vertex] = true;
        recStack[vertex] = true;
        for (int i = 0; i < values[vertex].size(); i++) {
            int neighbour = values[vertex].get(i).vertex;
            if (recStack[neighbour]) {
                return true;
            }
            if (!visited[neighbour]) {
                if (isCyclicUtil(neighbour, visited, recStack)) {
                    return true;
                }
            }
        }
        recStack[vertex] = false;
        return false;
    }

    /**
     * To make it work for undirected graphs the undirected edge must be read as one entry else it will result it as
     * true.
     *
     * @return
     */
    public boolean isCyclicUsingUnionFind() {
        boolean visited[] = new boolean[vertices()];
        UnionFind uf = new UnionFind(vertices());
        for (int i = 0; i < vertices(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                if (isCyclicUsingUnionFindUtil(i, visited, uf)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isCyclicUsingUnionFindUtil(int vertex, boolean[] visited, UnionFind uf) {
        for (int i = 0; i < values[vertex].size(); i++) {
            int neighbour = values[vertex].get(i).vertex;
            if (uf.find(vertex) == uf.find(neighbour)) {
                return true;
            } else {
                uf.union(i, neighbour);
            }
            if (!visited[neighbour]) {
                visited[neighbour] = true;
                if (isCyclicUsingUnionFindUtil(neighbour, visited, uf)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Only possible for a DAG.
     * first node have in degree 0;
     * We can look it as decreasing order of finish time.
     */
    public ArrayDeque<Integer> topologicalSort() {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[this.vertices()];
        for (int i = 0; i < this.vertices(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                topologicalSortUtil(visited, i, stack);
            }
        }
        return stack;
    }

    private void topologicalSortUtil(boolean[] visited, int vertex, ArrayDeque<Integer> stack) {
        for (int i = 0; i < this.values[vertex].size(); i++) {
            GraphNode neighbour = this.values[vertex].get(i);
            if (!visited[neighbour.vertex]) {
                visited[neighbour.vertex] = true;
                topologicalSortUtil(visited, neighbour.vertex, stack);
            }
        }
        stack.push(vertex);
    }

    public int longestPath(int source) {
        int[] dist = new int[this.vertices()];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[source] = 0;
        ArrayDeque<Integer> stack = this.topologicalSort();
        for (; !stack.isEmpty(); ) {
            int vertex = stack.peek();
//           It will make sure that we start from the source vertex and pick those who are updated
//           ; Only those will be updated which are linked from source vertex.
            if (dist[vertex] != Integer.MIN_VALUE) {
                for (int i = 0; i < this.values[vertex].size(); i++) {
                    GraphNode neighbour = this.values[vertex].get(i);
                    dist[neighbour.vertex] = dist[neighbour.vertex] > dist[vertex] + neighbour.weight ?
                            dist[neighbour.vertex] : dist[vertex] + neighbour.weight;
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] > max) {
                max = dist[i];
            }
        }
        return max;
    }

    public boolean checkBipartite() {
        int[] colour = new int[vertices()];
        Arrays.fill(colour, -1);
        for (int i = 0; i < vertices(); i++) {
            if (colour[i] == -1) {
                colour[i] = 1;
                if (!colorGraph(colour, i, 0)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean colorGraph(int[] colour, int vertex, int c) {
        for (int i = 0; i < this.values[vertex].size(); i++) {
            GraphNode neighbour = this.values[vertex].get(i);
            if (colour[neighbour.vertex] == -1) {
                colour[neighbour.vertex] = c;
                if (!colorGraph(colour, neighbour.vertex, 1 - c)) {
                    return false;
                }
            } else if (colour[neighbour.vertex] != c) {
                return false;
            }
        }
        return true;
    }

    /**
     * T=O(V+E)
     * Same as DFS.
     * Will not work in the case of multiple edges.
     *
     * @param graph
     */
    public static List<Edge> getBridges(Graph graph) {
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
                                             DoublePointer<Integer> currentTime, int source, Graph graph) {
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
    public static Set<Integer> getArticulationPoints(Graph graph) {
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
            DoublePointer<Integer> currentTime, boolean[] ap, int source, Graph graph) {
        visited[source] = true;
        low[source] = discoveryTime[source] = currentTime.data++;
        int children = 0;
        List<GraphNode> neighbours = graph.values[source];
        for (GraphNode neighbour : neighbours) {
            if (!visited[neighbour.vertex]) {
                children++;
                parent[neighbour.vertex] = source;
                if (!visited[neighbour.vertex]) {
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
                }
            } else if (parent[source] != neighbour.vertex) {
                low[source] = Math.min(low[source], discoveryTime[neighbour.vertex]);
            }
        }
    }


    /**
     * DFS approach will not work
     * ex- 0->1->2->3 , dfs will call it connected but it is not.
     * <p>
     * Kosaraju's Algorithm(Strongly connected)(Single SCC)
     * T=O(V+E)
     *
     * @return
     */
    @Override
    public boolean isStronglyConnected() {
        int vertices = this.vertices();
        boolean[] visited = new boolean[vertices];
        dfsUtil(0, visited);
        for (boolean i : visited) {
            if (!i) {
                return false;
            }
        }
        Arrays.fill(visited, false);
        Graph transposedGraph = this.transpose();
        transposedGraph.dfsUtil(0, visited);
        for (boolean i : visited) {
            if (!i) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEulerian() {
        throw new NotImplementedException();
    }

    /**
     * Fluery's Algorithm
     * T=O(V+E)
     *
     * @param graph
     */
    public static void printEulerPath(Graph graph) {
        assert graph == null;
        if (!graph.isEulerian()) {
            System.out.println("Graph is not eulerain");
            return;
        }
//        We can't directly use getBridges because one's the graph's one edge is removed we have to again recalculate the
//        bridges.
        int source = 0;
        int vertices = graph.vertices();
        for (int i = 0; i < vertices; i++) {
            if (graph.values[i].size() % 2 == 1) {
                source = i;
                break;
            }
        }
        printEulerPathUtil(graph, source);
    }

    /**
     * T=O(V+E)
     *
     * @param source
     * @param visited
     * @return
     */
    private int dfsCountUtil(int source, boolean[] visited) {
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

    private static void printEulerPathUtil(Graph graph, int source) {
        List<GraphNode> neighbours = graph.values[source];
        for (int i = 0; i < neighbours.size(); i++) {
            GraphNode neighbour = neighbours.get(i);
            if (isValidEdgeForEulerianPath(graph, source, neighbour.vertex)) {
                System.out.print(source + " " + neighbour.vertex + "=>");
                graph.removeEdge(source, neighbour.vertex);
                printEulerPathUtil(graph, neighbour.vertex);
            }
        }
    }

    private static boolean isValidEdgeForEulerianPath(Graph graph, int source, int dest) {
        if (graph.values[source].size() == 1) {
            return true;
        }
        boolean[] visited = new boolean[graph.vertices()];
        int count1 = graph.dfsCountUtil(source, visited);
        graph.removeEdge(source, dest);
        Arrays.fill(visited, false);
        int count2 = graph.dfsCountUtil(source, visited);
//        If count2 is decreased then it this edge is a bridge.
        return count1 == count2;
    }


}
