package gfg.ds.graph.adj_list;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import utils.Pair;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;

/**
 * Created By: Prashant Chaubey
 * Created On: 15-09-2018 17:09
 **/
public class Graph extends GraphBase {
    private int[] inDegree;

    public Graph(int vertices) {
        super(vertices);
        inDegree = new int[vertices];
    }

    @Override
    public Graph addEdge(int src, int dest) {
        return addEdge(src, dest, 0);
    }

    @Override
    public void removeEdge(int src, int dest) {
        assert (src >= 0 && dest >= 0 && src < values.length && dest < values.length);

        inDegree[dest]--;
        values[src].remove(new GraphNode(dest));
    }

    @Override
    public Graph addEdge(int src, int dest, int weight) {
        assert (src >= 0 && dest >= 0 && src < values.length && dest < values.length);

        inDegree[dest]++;
        values[src].add(new GraphNode(dest, weight));
        return this;
    }

    /**
     * t=O(V+E)
     * We can't just check visited array because then it will result (1->2, 0->2) also cyclic, so we just have to check
     * for back edge which is done using recursion stack.
     * The edge that connects current vertex to the vertex in the recursion stack is a back edge. We can also modify it
     * to return all back edges.
     *
     * @return true if the graph has cycle.
     */
    public boolean isCyclic() {
        boolean visited[] = new boolean[vertices()];
        boolean recStack[] = new boolean[vertices()];
        for (int i = 0; i < vertices(); i++) {
            if (isCyclicUtil(i, visited, recStack)) {
                return true;
            }
        }
        return false;
    }

    private boolean isCyclicUtil(int vertex, boolean[] visited, boolean[] recStack) {
        if (recStack[vertex]) {
            return true;
        }
        if (visited[vertex]) {
            return false;
        }
        visited[vertex] = true;
        recStack[vertex] = true;

        for (int i = 0; i < values[vertex].size(); i++) {
            int neighbour = values[vertex].get(i).vertex;
            if (isCyclicUtil(neighbour, visited, recStack)) {
                return true;
            }
        }
        recStack[vertex] = false;
        return false;
    }


    /**
     * t=O(V+E)
     * Only possible for a DAG.
     * first node have in degree 0;
     * We can look it as decreasing order of finish time.
     *
     * @return topological sort of the graph
     */
    public ArrayDeque<Integer> topologicalSort() {
        assert !this.isCyclic() : "Graph is not a DAG";

        ArrayDeque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[this.vertices()];
        for (int i = 0; i < this.vertices(); i++) {
            topologicalSortUtil(visited, i, stack);
        }
        return stack;
    }

    private void topologicalSortUtil(boolean[] visited, int vertex, ArrayDeque<Integer> stack) {
        if (visited[vertex]) {
            return;
        }
        visited[vertex] = true;
        for (GraphNode neighbour : this.values[vertex]) {
            topologicalSortUtil(visited, neighbour.vertex, stack);
        }
        stack.push(vertex);
    }

    /**
     * t=O(V+E)
     *
     * @param source vertex from which to find the longest path.
     * @return longest path
     */
    @SuppressWarnings("ConstantConditions")
    public int[] longestPath(int source) {
        int[] dist = new int[this.vertices()];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[source] = 0;
        ArrayDeque<Integer> stack = this.topologicalSort();

        for (; !stack.isEmpty(); ) {
            int vertex = stack.poll();
            // It will make sure that we start from the source vertex and pick updated ones.
            // Only those will be updated which are linked from source vertex.
            if (dist[vertex] == Integer.MIN_VALUE) {
                continue;
            }
            for (GraphNode neighbour : this.values[vertex]) {
                dist[neighbour.vertex] = dist[neighbour.vertex] > dist[vertex] + neighbour.weight ?
                        dist[neighbour.vertex] : dist[vertex] + neighbour.weight;
            }
        }
        return dist;
    }

    /**
     * t=O(V+E)
     *
     * @return true if graph is bipartite. Graph can be coloured with two colors.
     */
    public boolean isBipartite() {
        int[] colour = new int[vertices()];
        Arrays.fill(colour, -1);

        for (int i = 0; i < vertices(); i++) {
            if (colour[i] != -1) {
                continue;
            }
            colour[i] = 1;
            if (isNotBipartite(colour, i, 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotBipartite(int[] colour, int vertex, int c) {
        for (GraphNode neighbour : this.values[vertex]) {
            if (colour[neighbour.vertex] == -1) {
                colour[neighbour.vertex] = c;
                if (isNotBipartite(colour, neighbour.vertex, 1 - c)) {
                    return true;
                }
            } else if (colour[neighbour.vertex] != c) {
                return true;
            }
        }
        return false;
    }


    /**
     * t=O(V+E)
     * DFS approach will not work; ex- 0->1->2->3 , dfs will call it connected but it is not.
     * Kosaraju's Algorithm(Strongly connected)(Single SCC)
     *
     * @return true if strongly connected
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
        return isEulerianCircuitExists() || isEulerianPathExists();
    }

    @Override
    public List<Pair<Integer, Integer>> getEulerPath() {
        throw new NotImplementedException();
    }

    /**
     * t=O(V+E)
     *
     * @return true if eulerian circuit exists.
     */
    private boolean isEulerianCircuitExists() {
        //Assuming that there is no vertex with 0 edges. A graph with no edges is also eulerian. If there are such edges
        //then the below line will fail as it doesn't follow such discrimination. USE CAREFULLY WITH USE-CASE.
        assert isStronglyConnected() : "Graph is not connected";
        int vertices = vertices();
        //Out-degree == in-degree
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] != values[i].size()) {
                return false;
            }
        }
        return true;
    }

    private boolean isEulerianPathExists() {
        boolean isOutGreaterFound = false;
        int source = -1;
        boolean isInGreaterFound = false;
        int vertices = vertices();
        for (int i = 0; i < vertices; i++) {
            int outDegree = this.values[i].size();
            if (outDegree > inDegree[i]) {
                if (outDegree - inDegree[i] == 1 && !isOutGreaterFound) {
                    isOutGreaterFound = true;
                    source = i;
                } else {
                    return false;
                }
            } else if (inDegree[i] > outDegree) {
                if (inDegree[i] - outDegree == 1 && !isInGreaterFound) {
                    isInGreaterFound = true;
                } else {
                    return false;
                }
            }
        }
        if (isOutGreaterFound && isInGreaterFound) {
            return isAllVerticesReachableFrom(source);
        }
        return false;
    }

    /**
     * todo check that whether checking SCC will work then no need for extra method. The advantage over SCC here is that we are doing only one DFS.
     *
     * @param source source vertex from where to start dfs
     * @return true if all vertex are reachable from this
     */
    private boolean isAllVerticesReachableFrom(int source) {
        int vertices = vertices();
        boolean[] visited = new boolean[vertices];
        dfsUtil(source, visited);
        for (int i = 0; i < visited.length; i++) {
            if ((inDegree[i] > 0 || this.values[i].size() > 0) && !visited[i]) {
                return false;
            }
        }
        return true;
    }
}
